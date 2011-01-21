package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.seploc.migracao.beans.Papel;

public class MigraPapel extends Migra<Papel> {

	private MigraPapel() {
		// TODO Auto-generated constructor stub
	}

	private static MigraPapel obj;

	public static MigraPapel getInstance(Connection copytec, Connection seploc)
			throws Exception {
		if (obj == null) {
			obj = new MigraPapel();
		}
		if (copytec == null || seploc == null) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	public static MigraPapel getInstance(String[] bancoOrigem,
			String[] bancoDestino) throws Exception {
		if (obj == null) {
			obj = new MigraPapel();
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

	protected void seleciona() throws Exception {
		lista = new ArrayList<Papel>();
		// pega o Statement

		PreparedStatement stmt = copytecConnection
				.prepareStatement("SELECT  intCodPap, vcrNome, dblImpMono, dblImpColor, dblImpShade FROM tbl_papel");
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {
			Papel p = new Papel();
			p.setCod(rs.getInt("intCodPap"));
			p.setNome(rs.getString("vcrNome"));
			p.setMono(rs.getDouble("dblImpMono"));
			p.setShade(rs.getDouble("dblImpShade"));
			p.setColor(rs.getDouble("dblImpColor"));
			lista.add(p);
		}
		stmt.close();
	}

	protected void insereDados() throws Exception {
		String sql = "INSERT INTO  tbl_papel (intCodPap, vcrNome, dblImpMono, dblImpColor, dblImpShade, tspVersao) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP);";
		for (Papel p : lista) {
			PreparedStatement stmt = seplocConnection.prepareStatement(sql);
			stmt.setInt(1, p.getCod());
			stmt.setString(2, p.getNome());
			stmt.setDouble(3, p.getMono());
			stmt.setDouble(4, p.getColor());
			stmt.setDouble(5, p.getShade());
			stmt.execute();
			stmt.close();
		}

	}

	@Override
	public void setConexoes(Connection copytec, Connection seploc) {
		copytecConnection = copytec;
		seplocConnection = seploc;

	}

	public static void main(String args[]) {
		String[] origem = { "dbcopytec", "root", "" };
		String[] destino = { "seplocteste", "root", "" };
		try {
			MigraPapel migra = MigraPapel.getInstance(origem, destino);
			migra.migraDados();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void cqMigracao() throws Exception {
		int regSeploc = 0, regCopytec = 0, diferenca = 0;
		try {
			PreparedStatement stmtCopytec = copytecConnection
					.prepareStatement("SELECT count(*) FROM tbl_papel");
			PreparedStatement stmtseploc = seplocConnection
					.prepareStatement("SELECT count(*) FROM tbl_papel");
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
			saida += "REGISTROS COPYTEC TBL_PAPEL: " + regCopytec + "\n"
					+ "REGISTROS SEPLOC TBL_PAPEL: " + regSeploc + "\n"
					+ "-----------------------------\n"
					+ "DIFERENCA REGISTROS TBL_PAPEL: "
					+ (regCopytec - regSeploc) + "\n" + "REGISTROS INSERIDOS: "
					+ registrosInseridos;
			System.out.println(saida);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
