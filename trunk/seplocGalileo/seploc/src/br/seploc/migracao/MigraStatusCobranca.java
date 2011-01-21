package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.seploc.migracao.beans.Cobrador;
import br.seploc.migracao.beans.StatusCobranca;

public class MigraStatusCobranca extends Migra<StatusCobranca> {

	private static MigraStatusCobranca obj;
	private static String[] bancoOrigem;
	private static String[] bancoDestino;
	
	private MigraStatusCobranca() {
		lista = new ArrayList<StatusCobranca>();
	}

	public static MigraStatusCobranca getInstance(Connection copytec,
			Connection seploc) throws Exception {
		if (obj == null) {
			obj = new MigraStatusCobranca();
		}
		if (copytec == null || seploc == null) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	public static MigraStatusCobranca getInstance(String[] bancoOrigem,
			String[] bancoDestino) throws Exception {
		if (obj == null) {
			obj = new MigraStatusCobranca();
		}
		if (bancoDestino.length != 3 || bancoOrigem.length != 3) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		MigraStatusCobranca.bancoDestino = bancoDestino;
		MigraStatusCobranca.bancoOrigem = bancoOrigem;
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
					.prepareStatement("SELECT count(*) FROM tbl_statuscobranca");
			PreparedStatement stmtseploc = seplocConnection
					.prepareStatement("SELECT count(*) FROM tbl_statuscobranca");
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
			saida += "REGISTROS COPYTEC TBL_STATUSCOBRANCA: " + regCopytec + "\n"
					+ "REGISTROS SEPLOC TBL_STATUSCOBRANCA: " + regSeploc + "\n"
					+ "-----------------------------\n"
					+ "DIFERENCA REGISTROS TBL_STATUSCOBRANCA: "
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

	}

	@Override
	protected void seleciona() throws Exception {
//		String sql = "SELECT intCodCobr, vcrNome, vcrFoneCon FROM tbl_cobrador  where intCodCobr not in (select intCodCobr from   "
//			+ bancoDestino[0] + ".tbl_cobrador)";
//		lista = new ArrayList<Cobrador>();
//		// pega o Statement
//		PreparedStatement stmt = copytecConnection
//				.prepareStatement(sql);
////		System.out.println(sql);
//		// executa um select
//		ResultSet rs = stmt.executeQuery();
//		// itera no ResultSet
//		while (rs.next()) {
//			Cobrador c = new Cobrador(rs.getInt("intCodCobr"), rs
//					.getString("vcrNome"), rs.getString("vcrFoneCon"),
//					new Character('S'));
//			lista.add(c);
//		}
//		stmt.close();
	}

}
