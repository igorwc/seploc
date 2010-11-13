package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.seploc.migracao.beans.Cliente;
import br.seploc.migracao.beans.FoneCli;
import br.seploc.migracao.beans.OpcionalReqServ;
import br.seploc.migracao.beans.Projeto;

public class MigraCliente extends Migra<Cliente> {

	List<Cliente> listaClientes;
	List<FoneCli> listaTel;
	List<Projeto> listaProj;
	Integer idCliente = 1;

	public static MigraCliente getInstance(Connection copytec, Connection seploc) {
		MigraCliente obj = new MigraCliente();
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
		// iterano ResultSet
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
			int regs = rs.getInt(1);
			if (regs != 0) {
				stmt.close();
				System.out.println("Registros Problematicos (" + regs + "): ");
				stmt = copytecConnection
						.prepareStatement("SELECT  vcrCnpj, vcrRazao "
								+ " FROM tbl_clientes "
								+ "where intPapelPadrao not in (SELECT intCodPap FROM tbl_papel) "
								+ " order by vcrCnpj asc");

				rs = stmt.executeQuery();
				while (rs.next()) {
					System.out.println("CNPJ: " + rs.getInt("vcrCnpj")
							+ " Razao: " + rs.getString("vcrRazao"));
				}

			}
		}
	}

	protected void insereDados() throws Exception {
		Cliente controleCliente = null;
		try {
			String sql = "INSERT INTO  tbl_clientes (intClienteId, vcrCnpj, vcrRazao, "
					+ "vcrCpf, vcrEnder, vcrBairro, vcrCidade, vcrEstado, vcrCep, vcrEmail,"
					+ "vcrInscricao, intBalcao, vcrFantasia, txtobs, vcrMapa, intEntregaPadrao, "
					+ "intPapelPadrao, tspVersao) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP)";
			
			for (Cliente cl : listaClientes) {
				PreparedStatement stmt = seplocConnection.prepareStatement(sql);
				controleCliente = cl;
				if(cl.getCnpj() == null){
					cl.setCnpj(" ");
				}
				if (cl.getCnpj().length() == 19 && cl.getCnpj().endsWith("99")) {
					cl.setCnpj(null);
				}
				if (cl.getCnpj() != null && cl.getCnpj().equals(cl.getCpf())) {
					cl.setCpf(null);
				}
				stmt.setInt(1, cl.getId());
				stmt.setString(2, cl.getCnpj());
				stmt.setString(3, cl.getRazao());
				stmt.setString(4, cl.getCpf());
				stmt.setString(5, cl.getEndereco());
				stmt.setString(6, cl.getBairro());
				stmt.setString(7, cl.getCidade());
				stmt.setString(8, cl.getEstado());
				stmt.setString(9, cl.getCep());
				stmt.setString(10, cl.getEmail());
				stmt.setString(11, cl.getInscricao());
				stmt.setInt(12, cl.getBalcao());
				stmt.setString(13, cl.getFantasia());
				stmt.setString(14, cl.getObs());
				stmt.setString(15, cl.getMapa());
				stmt.setInt(16, cl.getEntrega());
				if (cl.getPapel() == null || cl.getPapel()== 0){
					stmt.setNull(17,java.sql.Types.INTEGER);
				}else{
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
				stmt.setString(2, fone.getFoneR());
				stmt.setString(3, fone.getFoneCom());
				stmt.setString(4, fone.getFax());
				stmt.setString(5, fone.getCel());
				stmt.execute();
				stmt.close();
			}

			sql = "INSERT INTO  tbl_projetos (intCodProj ,intClienteId ,vcrProjeto ,tspVersao)"
					+ " VALUES (?, ?, ? ,CURRENT_TIMESTAMP)";
			for (Projeto p : listaProj) {
				PreparedStatement stmt = seplocConnection.prepareStatement(sql);
				stmt.setInt(1, p.getCodProj());
				stmt.setInt(2, p.getId());
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
		MigraCliente migra = MigraCliente.getInstance(new ConnectionFactory()
				.getConnection("dbcopytec2", "root", ""),
				new ConnectionFactory().getConnection("seploc2", "root", ""));
		try {
			migra.migraDados();

			System.out.println("terminou");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
