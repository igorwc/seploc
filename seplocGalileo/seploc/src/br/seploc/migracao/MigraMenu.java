package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.seploc.migracao.beans.Menu;
import br.seploc.migracao.beans.SaidaMotoqueiro;

public class MigraMenu extends Migra<Menu> {

	private static MigraMenu obj;
	private static String[] bancoOrigem;
	private static String[] bancoDestino;
	
	private MigraMenu() {
	}

	public static MigraMenu getInstance(Connection copytec,
			Connection seploc) throws Exception {
		if (obj == null) {
			obj = new MigraMenu();
		}
		if (copytec == null || seploc == null) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	public static MigraMenu getInstance(String[] bancoOrigem,
			String[] bancoDestino) throws Exception {
		if (obj == null) {
			obj = new MigraMenu();
		}
		if (bancoDestino.length != 3 || bancoOrigem.length != 3) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		MigraMenu.bancoDestino = bancoDestino;
		MigraMenu.bancoOrigem = bancoOrigem;
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
					.prepareStatement("SELECT count(*) FROM tbl_menu");
			PreparedStatement stmtseploc = seplocConnection
					.prepareStatement("SELECT count(*) FROM tbl_menu");
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
			saida += "REGISTROS COPYTEC TBL_MENU: " + regCopytec
					+ "\n" + "REGISTROS SEPLOC TBL_MENU: "
					+ regSeploc + "\n" + "-----------------------------\n"
					+ "DIFERENCA REGISTROS TBL_MENU: "
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
	for (Menu m : lista) {
		PreparedStatement stmt = seplocConnection.prepareStatement(sql);
		stmt.setString(1, m.getChrCodMenu().toString());
		
		stmt.execute();
		registrosInseridos++;
		stmt.close();
	}

	}

	@Override
	protected void seleciona() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
