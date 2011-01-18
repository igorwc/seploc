package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.seploc.migracao.beans.Entrega;

public class MigraEntrega extends Migra<Entrega> {

	private MigraEntrega() {
		lista = new ArrayList<Entrega>();
	}

	public static MigraEntrega getInstance(Connection copytec, Connection seploc) {
		MigraEntrega obj = new MigraEntrega();
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	@Override
	public void setConexoes(Connection copytec, Connection seploc) {
		copytecConnection = copytec;
		seplocConnection = seploc;

	}

	@Override
	protected void insereDados() throws Exception {
		String sql = "INSERT INTO tbl_entrega (intCodEnt ,vcrLocal ,dblPreco ,tspVersao)VALUES (? , ?, ? ,CURRENT_TIMESTAMP)";
		registrosInseridos = 0;
		for (Entrega e : lista) {
			PreparedStatement stmt = seplocConnection.prepareStatement(sql);
			stmt.setInt(1, e.getCod());
			stmt.setString(2, e.getLocal());
			stmt.setDouble(3, e.getPreco());
			stmt.execute();
			registrosInseridos++;
			stmt.close();
		}
	}

	@Override
	protected void seleciona() throws Exception {
		lista = new ArrayList<Entrega>();
		// pega o Statement
		PreparedStatement stmt = copytecConnection
				.prepareStatement("SELECT intCodEnt, vcrLocal, dblPreco FROM tbl_entrega where intCodEnt not in (select intCodEnt from seplocteste.tbl_entrega)");
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {
			Entrega e = new Entrega();
			e.setCod(rs.getInt("intCodEnt"));
			e.setLocal(rs.getString("vcrLocal"));
			e.setPreco(rs.getDouble("dblPreco"));
			lista.add(e);
		}
		stmt.close();
	}

	protected void cqMigracao() {
		int regSeploc = 0, regCopytec = 0, diferenca = 0;
		try {
			PreparedStatement stmtCopytec = copytecConnection
					.prepareStatement("SELECT count(*) FROM tbl_entrega");
			PreparedStatement stmtseploc = seplocConnection
					.prepareStatement("SELECT count(*) FROM tbl_entrega");
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
			saida += "REGISTROS COPYTEC TBL_ENTREGA: " + regCopytec + "\n"
					+ "REGISTROS SEPLOC TBL_ENTREGA: " + regSeploc + "\n"
					+ "-----------------------------\n"
					+ "DIFERENCA REGISTROS TBL_ENTREGA: "
					+ (regCopytec - regSeploc) + "\n" +
					"REGISTROS INSERIDOS: "+registrosInseridos;
			System.out.println(saida);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		MigraEntrega migra = MigraEntrega.getInstance(new ConnectionFactory()
				.getConnection("dbcopytec2", "root", ""),
				new ConnectionFactory()
						.getConnection("seplocteste", "root", ""));

		try {

			migra.migraDados();
			for (Entrega e : migra.getLista()) {
				System.out.println(e.getCod() + " " + e.getLocal());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}