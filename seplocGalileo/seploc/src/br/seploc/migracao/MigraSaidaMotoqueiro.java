package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.seploc.migracao.beans.SaidaMotoqueiro;

public class MigraSaidaMotoqueiro extends Migra<SaidaMotoqueiro> {
	private static MigraSaidaMotoqueiro obj;
	private static String[] bancoOrigem;
	private static String[] bancoDestino;

	private MigraSaidaMotoqueiro() {
	}

	public static MigraSaidaMotoqueiro getInstance(Connection copytec,
			Connection seploc) throws Exception {
		if (obj == null) {
			obj = new MigraSaidaMotoqueiro();
		}
		if (copytec == null || seploc == null) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	public static MigraSaidaMotoqueiro getInstance(String[] bancoOrigem,
			String[] bancoDestino) throws Exception {
		if (obj == null) {
			obj = new MigraSaidaMotoqueiro();
		}
		if (bancoDestino.length != 3 || bancoOrigem.length != 3) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		MigraSaidaMotoqueiro.bancoDestino = bancoDestino;
		MigraSaidaMotoqueiro.bancoOrigem = bancoOrigem;
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
					.prepareStatement("SELECT count(*) FROM tbl_saidamotoqueiro");
			PreparedStatement stmtseploc = seplocConnection
					.prepareStatement("SELECT count(*) FROM tbl_saidamotoqueiro");
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
			saida += "REGISTROS COPYTEC TBL_SAIDAMOTOQUEIRO: " + regCopytec
					+ "\n" + "REGISTROS SEPLOC TBL_SAIDAMOTOQUEIRO: "
					+ regSeploc + "\n" + "-----------------------------\n"
					+ "DIFERENCA REGISTROS TBL_SAIDAMOTOQUEIRO: "
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
		String sql = "INSERT INTO tbl_saidamotoqueiro ( intNumSaida, intCodCobr, intNumReq, datDataCobr, datDataPag, horCobranca, horPagamen, vcrCliente, vcrObs, tspVersao) "
				+ "VALUES (? , ?, ?, ? ,? , ?, ?, ? , ?,CURRENT_TIMESTAMP)";
		registrosInseridos = 0;
		for (SaidaMotoqueiro sm : lista) {
			PreparedStatement stmt = seplocConnection.prepareStatement(sql);
			stmt.setInt(1, sm.getIntNumSaida());
			if (sm.getIntCodCobr() == null) {
				stmt.setNull(2, java.sql.Types.INTEGER);
			} else {
				stmt.setInt(2, sm.getIntCodCobr());
			}
			if (sm.getIntNumReq() == null) {
				stmt.setNull(3, java.sql.Types.INTEGER);
			} else {
				stmt.setInt(3, sm.getIntNumReq());
			}
			if (sm.getDatDataCobr() == null) {
				stmt.setNull(4, java.sql.Types.DATE);
			} else {
				stmt.setDate(4, sm.getDatDataCobr());
			}
			if (sm.getDatDataPag() == null) {
				stmt.setNull(5, java.sql.Types.DATE);
			} else {
				stmt.setDate(5, sm.getDatDataCobr());
			}
			if (sm.getHorCobranca() == null) {
				stmt.setNull(6, java.sql.Types.TIME);
			} else {
				stmt.setTime(6, sm.getHorCobranca());
			}
			if (sm.getHorPagamen() == null) {
				stmt.setNull(7, java.sql.Types.TIME);
			} else {
				stmt.setTime(7, sm.getHorPagamen());
			}
			if (sm.getVcrCliente() == null) {
				stmt.setNull(8, java.sql.Types.VARCHAR);
			} else {
				stmt.setString(8, sm.getVcrCliente());
			}
			if (sm.getVcrObs() == null) {
				stmt.setNull(9, java.sql.Types.VARCHAR);
			} else {
				stmt.setString(9, sm.getVcrObs());
			}
			stmt.execute();
			registrosInseridos++;
			stmt.close();
		}

	}

	@Override
	protected void seleciona() throws Exception {
		String sql = "SELECT intNumSaida, intCodCobr, intNumReq, datDataCobr, datDataPag, horCobranca, horPagamen, vcrCliente, vcrObs "
				+ "FROM tbl_saidamotoqueiro "
				+ "WHERE intCodCobr "
				+ "IN ( "
				+ "SELECT intCodCobr "
				+ "FROM "
				+ bancoDestino[0]
				+ ".tbl_cobrador " + ") ";

		lista = new ArrayList<SaidaMotoqueiro>();
		// pega o Statement
		PreparedStatement stmt = copytecConnection.prepareStatement(sql);
		// System.out.println(sql);
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {
			SaidaMotoqueiro sm = new SaidaMotoqueiro(rs.getInt("intNumSaida"),
					rs.getInt("intCodCobr"), rs.getInt("intNumReq"), rs
							.getDate("datDataCobr"), rs.getDate("datDataPag"),
					rs.getTime("horCobranca"), rs.getTime("horPagamen"), rs
							.getString("vcrCliente"), rs.getString("vcrObs"));
			lista.add(sm);
		}
		stmt.close();

	}

	public static void main(String args[]) {
		String[] origem = { "dbcopytec", "root", "" };
		String[] destino = { "seplocteste", "root", "" };

		try {
			MigraSaidaMotoqueiro migra = MigraSaidaMotoqueiro.getInstance(
					origem, destino);
			migra.migraDados();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

}
