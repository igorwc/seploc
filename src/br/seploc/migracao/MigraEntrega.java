package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MigraEntrega {

	public List<Entrega> seleciona(Connection c) throws Exception {
		List<Entrega> retorno = new ArrayList<Entrega>();
		// pega o Statement

		PreparedStatement stmt = c
				.prepareStatement("SELECT intCodEnt, vcrLocal, dblPreco FROM tbl_entrega");
		// executaumselect
		ResultSet rs = stmt.executeQuery();
		// iteranoResultSet
		while (rs.next()) {
			Entrega e = new Entrega();
			e.setCod(rs.getInt("intCodEnt"));
			e.setLocal(rs.getString("vcrLocal"));
			e.setPreco(rs.getDouble("dblPreco"));
			retorno.add(e);
		}
		stmt.close();
		return retorno;
	}

	public void insereDados(Connection c, List<Entrega> lista) throws Exception {
		Integer id = new Integer(0);
		String sql = "INSERT INTO tbl_entrega (intCodEnt ,vcrLocal ,dblPreco ,tspVersao)VALUES (? , ?, ? ,CURRENT_TIMESTAMP)";
//		PreparedStatement stID = c
//				.prepareStatement("SELECT VAL_ID FROM id_gen where NOME_ID = ?");
//		stID.setString(1, "ENTREGA_GEN");
//		ResultSet rs = stID.executeQuery();
//		if (rs.next()) {
//			id = rs.getInt(1);
//		} else {
//			id = 1;
//		}
//		if (id == null) {
//			id = 1;
//		}
		for (Entrega e : lista) {
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, e.getCod());
			stmt.setString(2, e.getLocal());
			stmt.setDouble(3, e.getPreco());
			stmt.execute();
			stmt.close();
		}

	}

	public static void main(String args[]){
		MigraEntrega migra = new MigraEntrega();
		try {
			Connection c = new ConnectionFactory().getConnection("copytec", "root", "");
			 List<Entrega> lista = migra.seleciona(c);
			for(Entrega e: lista){
				System.out.println(e.getCod()+" "+  e.getLocal());
			}
			c.close();
			c = new ConnectionFactory().getConnection("seploc2", "root", "");
			migra.insereDados(c, lista);
//			c.commit();
			c.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Entrega {
	public Integer cod;
	public String local;
	public Double preco;

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
