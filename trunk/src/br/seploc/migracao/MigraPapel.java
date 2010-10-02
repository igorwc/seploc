package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MigraPapel {
	public List<Papel> seleciona(Connection c) throws Exception {
		List<Papel> retorno = new ArrayList<Papel>();
		// pega o Statement

		PreparedStatement stmt = c
				.prepareStatement("SELECT  intCodPap, vcrNome, dblImpMono, dblImpColor, dblImpShade FROM tbl_papel");
		// executaumselect
		ResultSet rs = stmt.executeQuery();
		// iteranoResultSet
		while (rs.next()) {
			Papel p = new Papel();
			p.setCod(rs.getInt("intCodPap"));
			p.setNome(rs.getString("vcrNome"));
			p.setMono(rs.getDouble("dblImpMono"));
			p.setShade(rs.getDouble("dblImpShade"));
			p.setColor(rs.getDouble("dblImpColor"));
			retorno.add(p);
		}
		stmt.close();
		return retorno;
	}

	public void insereDados(Connection c, List<Papel> lista) throws Exception {
		String sql = "INSERT INTO  tbl_papel (intCodPap, vcrNome, dblImpMono, dblImpColor, dblImpShade, tspVersao) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP);";
		for (Papel p : lista) {
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, p.getCod());
			stmt.setString(2, p.getNome());
			stmt.setDouble(3, p.getMono());
			stmt.setDouble(4, p.getColor());
			stmt.setDouble(5, p.getShade());
			stmt.execute();
			stmt.close();
		}

	}

	public static void main(String args[]) {
		MigraPapel migra = new MigraPapel();
		try {
			Connection c = new ConnectionFactory().getConnection("copytec",
					"root", "");
			List<Papel> lista = migra.seleciona(c);
			for (Papel p : lista) {
				System.out.println(p.getCod() + " " + p.getNome());
			}
			c.close();
			c = new ConnectionFactory().getConnection("seploc2", "root", "");
			migra.insereDados(c, lista);
			// c.commit();
			c.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Papel {
	public Integer cod;
	public String nome;
	public Double mono;
	public Double color;
	public Double shade;

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getMono() {
		return mono;
	}

	public void setMono(Double mono) {
		this.mono = mono;
	}

	public Double getColor() {
		return color;
	}

	public void setColor(Double color) {
		this.color = color;
	}

	public Double getShade() {
		return shade;
	}

	public void setShade(Double shade) {
		this.shade = shade;
	}

}