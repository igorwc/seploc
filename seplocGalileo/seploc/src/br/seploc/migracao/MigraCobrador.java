package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.seploc.migracao.beans.Cobrador;

public class MigraCobrador extends Migra<Cobrador> {

	private static MigraCobrador obj;
	private static String[] bancoOrigem;
	private static String[] bancoDestino;

	private MigraCobrador() {
		lista = new ArrayList<Cobrador>();
	}

	public static MigraCobrador getInstance(Connection copytec,
			Connection seploc) throws Exception {
		if (obj == null) {
			obj = new MigraCobrador();
		}
		if (copytec == null || seploc == null) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	public static MigraCobrador getInstance(String[] bancoOrigem,
			String[] bancoDestino) throws Exception {
		if (obj == null) {
			obj = new MigraCobrador();
		}
		if (bancoDestino.length != 3 || bancoOrigem.length != 3) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		MigraCobrador.bancoDestino = bancoDestino;
		MigraCobrador.bancoOrigem = bancoOrigem;
		Connection copytec = new ConnectionFactory().getConnection(
				bancoOrigem[0], bancoOrigem[1], bancoOrigem[2]);
		Connection seploc = new ConnectionFactory().getConnection(
				bancoDestino[0], bancoDestino[1], bancoDestino[2]);
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	@Override
	protected void cqMigracao() throws Exception {
		int regSeploc = 0, regCopytec = 0, diferenca = 0;
		try {
			PreparedStatement stmtCopytec = copytecConnection
					.prepareStatement("SELECT count(*) FROM tbl_cobrador");
			PreparedStatement stmtseploc = seplocConnection
					.prepareStatement("SELECT count(*) FROM tbl_cobrador");
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
			saida += "REGISTROS COPYTEC TBL_COBRADOR: " + regCopytec + "\n"
					+ "REGISTROS SEPLOC TBL_COBRADOR: " + regSeploc + "\n"
					+ "-----------------------------\n"
					+ "DIFERENCA REGISTROS TBL_COBRADOR: "
					+ (regCopytec - regSeploc) + "\n" + "REGISTROS INSERIDOS: "
					+ registrosInseridos;
			System.out.println(saida);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

	}

	@Override
	protected void insereDados() throws Exception {
		String sql = "INSERT INTO tbl_cobrador (intCodCobr, vcrNome, vcrFoneCon, chrAtivo, tspVersao)VALUES (? , ?, ?, ? ,CURRENT_TIMESTAMP)";
		registrosInseridos = 0;
		for (Cobrador c : lista) {
			PreparedStatement stmt = seplocConnection.prepareStatement(sql);
			stmt.setInt(1, c.getCodCobrador());
			stmt.setString(2, c.getNome());
			if (c.getFone() == null) {
				stmt.setNull(3, java.sql.Types.VARCHAR);
			} else {
				stmt.setString(3, c.getFone());
			}
			stmt.setString(4, c.getInAtivo().toString());
			stmt.execute();
			registrosInseridos++;
			stmt.close();
		}

	}

	@Override
	protected void seleciona() throws Exception {
		String sql = "SELECT intCodCobr, vcrNome, vcrFoneCon FROM tbl_cobrador  where intCodCobr not in (select intCodCobr from   "
			+ bancoDestino[0] + ".tbl_cobrador)";
		lista = new ArrayList<Cobrador>();
		// pega o Statement
		PreparedStatement stmt = copytecConnection
				.prepareStatement(sql);
//		System.out.println(sql);
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {
			Cobrador c = new Cobrador(rs.getInt("intCodCobr"), rs
					.getString("vcrNome"), rs.getString("vcrFoneCon"),
					new Character('S'));
			lista.add(c);
		}
		stmt.close();

	}

	public static void main(String args[]) {
		String[] origem = { "dbcopytec", "root", "" };
		String[] destino = { "seplocteste", "root", "" };

		try {
			MigraCobrador migra = MigraCobrador.getInstance(origem, destino);
			migra.migraDados();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
}