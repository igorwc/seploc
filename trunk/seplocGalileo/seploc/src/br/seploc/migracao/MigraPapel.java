package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.seploc.migracao.beans.Papel;

public class MigraPapel extends Migra<Papel> {

	private MigraPapel() {
		// TODO Auto-generated constructor stub
	}

	public static MigraPapel getInstance(Connection copytec, Connection seploc) {
		MigraPapel obj = new MigraPapel();
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
		MigraPapel migra =  MigraPapel.getInstance(new ConnectionFactory().getConnection("dbcopytec2",
					"root", ""), new ConnectionFactory().getConnection("seploc2", "root", ""));
		try {
			 
			 migra.migraDados();
			for (Papel p : migra.getLista()) {
				System.out.println(p.getCod() + " " + p.getNome());
			}
			 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
 