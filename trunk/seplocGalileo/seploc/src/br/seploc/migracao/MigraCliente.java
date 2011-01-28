package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.seploc.migracao.beans.Cliente;
import br.seploc.migracao.beans.FoneCli;
import br.seploc.migracao.beans.OpcionalReqServ;
import br.seploc.migracao.beans.Projeto;
import br.seploc.util.UtilsArquivo;

public class MigraCliente extends Migra<Cliente> {

	List<Cliente> listaClientes;
	List<FoneCli> listaTel;
	List<Projeto> listaProj;
	Integer idCliente = 1;
	private static MigraCliente obj;

	public static MigraCliente getInstance(Connection copytec, Connection seploc)
			throws Exception {
		if (obj == null) {
			obj = new MigraCliente();
		}
		if (copytec == null || seploc == null) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	public static MigraCliente getInstance(String[] bancoOrigem,
			String[] bancoDestino) throws Exception {
		if (obj == null) {
			obj = new MigraCliente();
		}
		if (bancoDestino.length != 3 || bancoOrigem.length != 3) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		Connection copytec = new ConnectionFactory().getConnection(
				bancoOrigem[0], bancoOrigem[1], bancoOrigem[2]);
		Connection seploc = new ConnectionFactory().getConnection(
				bancoDestino[0], bancoDestino[1], bancoDestino[2]);
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	private MigraCliente() {
		listaClientes = new ArrayList<Cliente>();
		listaTel = new ArrayList<FoneCli>();
		listaProj = new ArrayList<Projeto>();
	}

	@Override
	public void setConexoes(Connection copytec, Connection seploc) {
		copytecConnection = copytec;
		seplocConnection = seploc;

	}

	protected void seleciona() throws Exception {
		// pega o Statement
		PreparedStatement stmt = copytecConnection
				.prepareStatement("SELECT vcrCnpj, vcrRazao, vcrCpf, vcrEnder, vcrBairro, "
						+ "vcrCidade, vcrEstado, vcrCep, vcrEmail, vcrInscricao, intBalcao, "
						+ "vcrFantasia, txtobs, vcrMapa, intEntregaPadrao, intPapelPadrao FROM tbl_clientes ");
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {
			Cliente cl = new Cliente();
			cl.setId(idCliente);
			cl.setCnpj(rs.getString(1));
			cl.setRazao(rs.getString(2));
			cl.setCpf(rs.getString(3));
			cl.setEndereco(rs.getString(4));
			cl.setBairro(rs.getString(5));
			cl.setCidade(rs.getString(6));
			cl.setEstado(rs.getString(7));
			cl.setCep(rs.getString(8));
			cl.setEmail(rs.getString(9));
			cl.setInscricao(rs.getString(10));
			cl.setBalcao(rs.getInt(11));
			cl.setFantasia(rs.getString(12));
			cl.setObs(rs.getString(13));
			cl.setMapa(rs.getString(14));
			cl.setEntrega(rs.getInt(15));
			if (rs.getInt(16) == 0) {
				cl.setPapel(null);
			} else {
				cl.setPapel(rs.getInt(16));
			}
			listaClientes.add(cl);
			PreparedStatement stFone = copytecConnection
					.prepareStatement("SELECT  vcrCnpj, vcrFoneRes, vcrFoneCom, vcrFax, vcrCelular "
							+ "FROM tbl_fonecli where vcrCnpj = ? ");
			stFone.setString(1, cl.getCnpj());
			ResultSet rsFone = stFone.executeQuery();
			while (rsFone.next()) {
				FoneCli fone = new FoneCli();
				fone.setId(idCliente);
				fone.setFoneR(rsFone.getString(2));
				fone.setFoneCom(rsFone.getString(3));
				fone.setFax(rsFone.getString(4));
				fone.setCel(rsFone.getString(5));
				listaTel.add(fone);
			}
			stFone.close();
			PreparedStatement stProj = copytecConnection
					.prepareStatement("SELECT vcrCnpj, intCodProj, vcrProjeto FROM tbl_projetos where vcrCnpj = ? ");
			stProj.setString(1, cl.getCnpj());
			ResultSet rsProj = stProj.executeQuery();
			while (rsProj.next()) {
				Projeto projeto = new Projeto();
				projeto.setId(idCliente);
				projeto.setCodProj(rsProj.getInt(2));
				projeto.setDescProj(rsProj.getString(3));
				listaProj.add(projeto);
			}
			idCliente++;
		}
		stmt.close();
		stmt = copytecConnection
				.prepareStatement("SELECT count(*) "
						+ " FROM tbl_clientes "
						+ "where intPapelPadrao not in (SELECT intCodPap FROM tbl_papel)");
		rs = stmt.executeQuery();
		if (rs.next()) {
			String papelProblema = "";
			int regs = rs.getInt(1);
			if (regs != 0) {
				rs.close();
				stmt.close();
				papelProblema += "Registros Problematicos (" + regs + ")\n";
				System.out.println("Registros Problematicos (" + regs + "): ");
				stmt = copytecConnection
						.prepareStatement("SELECT  vcrCnpj, vcrRazao "
								+ " FROM tbl_clientes "
								+ "where intPapelPadrao not in (SELECT intCodPap FROM tbl_papel) "
								+ " order by vcrCnpj asc");

				rs = stmt.executeQuery();
				int cont = 1;
				while (rs.next()) {
					papelProblema += cont + ") CNPJ: "
							+ rs.getString("vcrCnpj") + "\t\t\tRazao: "
							+ rs.getString("vcrRazao") + "\n";
//					System.out.println("CNPJ: " + rs.getString("vcrCnpj")
//							+ " Razao: " + rs.getString("vcrRazao"));
				}

				rs.close();
				stmt.close();
				UtilsArquivo.salvar("c:\\temp\\clienteSemPappel.txt",
						papelProblema, false);
			}
		} else {
			rs.close();
			stmt.close();
		}

		// verifica projetos sem clientes
		stmt = copytecConnection.prepareStatement("SELECT count(*) "
				+ "FROM tbl_projetos " + "WHERE vcrCnpj NOT IN "
				+ "( SELECT vcrCnpj FROM tbl_clientes )");
		rs = stmt.executeQuery();
		if (rs.next()) {
			String projetosProblema = "";
			int regs = rs.getInt(1);
			if (regs != 0) {
				rs.close();
				stmt.close();
				projetosProblema += "Registros Problematicos (" + regs + ")\n";
				System.out.println("Registros Problematicos (" + regs + "): ");
				stmt = copytecConnection
						.prepareStatement("SELECT  intCodProj, vcrProjeto, vcrCnpj "
								+ "FROM tbl_projetos "
								+ "WHERE vcrCnpj NOT IN "
								+ "( SELECT vcrCnpj FROM tbl_clientes )"
								+ " order by intCodProj asc");

				rs = stmt.executeQuery();
				while (rs.next()) {
					projetosProblema += "Codigo Projeto : "
							+ rs.getInt("intCodProj") + "\t\t\tDESCRICAO: "
							+ rs.getString("vcrProjeto") + "\t\t\tCNPJ: "
							+ rs.getString("vcrCnpj") + "\n";
//					System.out.println("Codigo Projeto : "
//							+ rs.getInt("intCodProj") + "\tDESCRICAO: "
//							+ rs.getString("vcrProjeto") + "\tcnpj: "
//							+ rs.getString("vcrCnpj"));
				}

				rs.close();
				stmt.close();
				UtilsArquivo.salvar("c:\\temp\\projetosSemCliente.txt",
						projetosProblema, false);
			}
		} else {
			rs.close();
			stmt.close();
		}
	}

	protected void insereDados() throws Exception {
		Cliente controleCliente = null;
//		Map< String, Integer> conversao = new HashMap<String, Integer>();
		try {
			String sql = "INSERT INTO  tbl_clientes (intClienteId, vcrCnpj, vcrRazao, "
					+ "vcrCpf, vcrEnder, vcrBairro, vcrCidade, vcrEstado, vcrCep, vcrEmail,"
					+ "vcrInscricao, intBalcao, vcrFantasia, txtobs, vcrMapa, intEntregaPadrao, "
					+ "intPapelPadrao, tspVersao) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP)";

			for (Cliente cl : listaClientes) {
				PreparedStatement stmt = seplocConnection.prepareStatement(sql);
				controleCliente = cl;
//				if (cl.getCnpj() == null) {
//					cl.setCnpj(" ");
//				}
				if (cl.getCnpj().length() == 19 && cl.getCnpj().endsWith("99")) {
					cl.setCnpj(null);
				}
				if (cl.getCnpj() != null && cl.getCnpj().equals(cl.getCpf())) {
					cl.setCpf(null);
				}
				stmt.setInt(1, cl.getId());
				if (cl.getCnpj() == null) {
					stmt.setNull(2, java.sql.Types.VARCHAR);
				} else if (cl.getCnpj().length() > 20) {
//					conversao.put(cl.getCnpj(),cl.getId());
					stmt.setNull(2, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(2, cl.getCnpj());
				}
//				stmt.setString(2, cl.getCnpj());
				stmt.setString(3, cl.getRazao());
				if (cl.getCpf() == null) {
					stmt.setNull(4, java.sql.Types.VARCHAR);
				} else if (cl.getCpf().length() > 20) {
//					conversao.put(cl.getCnpj(),cl.getId());
					stmt.setNull(4, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(4, cl.getCpf());
				}
//				stmt.setString(4, cl.getCpf());
				if (cl.getEndereco() == null) {
					stmt.setNull(5, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(5, cl.getEndereco());
				}
				if (cl.getBairro() == null) {
					stmt.setNull(6, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(6, cl.getBairro());
				}
				if (cl.getCidade() == null) {
					stmt.setNull(7, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(7, cl.getCidade());
				}
				if (cl.getEstado() == null) {
					stmt.setNull(8, java.sql.Types.CHAR);
				} else {
					stmt.setString(8, cl.getEstado());
				}
				if (cl.getCep() == null) {
					stmt.setNull(9, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(9, cl.getCep());
				}
				if (cl.getEmail() == null) {
					stmt.setNull(10, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(10, cl.getEmail());
				}
				if (cl.getInscricao() == null) {
					stmt.setNull(11, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(11, cl.getInscricao());
				}
				if (cl.getBalcao() == null) {
					stmt.setNull(12, java.sql.Types.INTEGER);
				} else {
					stmt.setInt(12, cl.getBalcao());
				}
				if (cl.getFantasia() == null) {
					stmt.setNull(13, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(13, cl.getFantasia());
				}
				if (cl.getObs() == null) {
					stmt.setNull(14, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(14, cl.getObs());
				}
				if (cl.getMapa() == null) {
					stmt.setNull(15, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(15, cl.getMapa());
				}
				if (cl.getEntrega() == null) {
					stmt.setNull(16, java.sql.Types.INTEGER);
				} else {
					stmt.setInt(16, cl.getEntrega());
				}
				if (cl.getPapel() == null || cl.getPapel() == 0) {
					stmt.setNull(17, java.sql.Types.INTEGER);
				} else {
					stmt.setInt(17, cl.getPapel());
				}
				stmt.execute();

				stmt.close();
			}
			sql = " INSERT INTO tbl_fonecli (intClienteId ,vcrFoneRes "
					+ ",vcrFoneCom ,vcrFax ,vcrCelular ,tspVersao) "
					+ "VALUES (?, ? , ? , ? , ? ,CURRENT_TIMESTAMP) ";
			for (br.seploc.migracao.beans.FoneCli fone : listaTel) {
				PreparedStatement stmt = seplocConnection.prepareStatement(sql);

				stmt.setInt(1, fone.getId());
				if (fone.getFoneR() == null) {
					stmt.setNull(2, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(2, fone.getFoneR());
				}
				if (fone.getFoneCom() == null) {
					stmt.setNull(3, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(3, fone.getFoneCom());
				}
				if (fone.getFax() == null) {
					stmt.setNull(4, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(4, fone.getFax());
				}
				if (fone.getCel() == null) {
					stmt.setNull(5, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(5, fone.getCel());
				}
				stmt.execute();
				stmt.close();
			}

			sql = "INSERT INTO  tbl_projetos (intCodProj ,intClienteId ,vcrProjeto ,tspVersao)"
					+ " VALUES (?, ?, ? ,CURRENT_TIMESTAMP)";
			for (Projeto p : listaProj) {
				PreparedStatement stmt = seplocConnection.prepareStatement(sql);
				stmt.setInt(1, p.getCodProj());
				stmt.setInt(2, p.getId());
				if ( p.getDescProj() == null) {
					stmt.setNull(3, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(3,  p.getDescProj());
				}
				stmt.setString(3, p.getDescProj());
				stmt.execute();
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Deu Erro nesse objeto: " + controleCliente);
		}
	}

	public static void main(String args[]) {
		String[] origem = { "dbcopytec", "root", "" };
		String[] destino = { "seploc2", "root", "" };
		// MigraCliente migra = MigraCliente.getInstance(new ConnectionFactory()
		// .getConnection("dbcopytec2", "root", ""),
		// new ConnectionFactory().getConnection("seploc2", "root", ""));
		try {
			MigraCliente migra = MigraCliente.getInstance(origem, destino);
			migra.migraDados();

			System.out.println("terminou");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	private void cqMigracao(String tabela) throws Exception {
		int regSeploc = 0, regCopytec = 0, diferenca = 0;
		try {
			PreparedStatement stmtCopytec = copytecConnection
					.prepareStatement("SELECT count(*) FROM " + tabela + " ");
			PreparedStatement stmtseploc = seplocConnection
					.prepareStatement("SELECT count(*) FROM " + tabela + " ");
			ResultSet rsCopytec = stmtCopytec.executeQuery();
			ResultSet rsSeploc = stmtseploc.executeQuery();
			if (rsCopytec.next()) {
				regCopytec = rsCopytec.getInt(1);
			}
			if (rsSeploc.next()) {
				regSeploc = rsSeploc.getInt(1);
			}
			rsCopytec.close();
			rsSeploc.close();
			stmtCopytec.close();
			stmtseploc.close();
			String saida = "";
			saida += "REGISTROS COPYTEC " + tabela + ": " + regCopytec + "\n"
					+ "REGISTROS SEPLOC " + tabela + ": " + regSeploc + "\n"
					+ "-----------------------------\n"
					+ "DIFERENCA REGISTROS " + tabela + ": "
					+ (regCopytec - regSeploc) + "\n" + "REGISTROS INSERIDOS: "
					+ registrosInseridos + "\n";
			System.out.println(saida);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void cqMigracao() throws Exception {
		cqMigracao("tbl_clientes");
		cqMigracao("tbl_fonecli");
		cqMigracao("tbl_projetos");
	}

}
