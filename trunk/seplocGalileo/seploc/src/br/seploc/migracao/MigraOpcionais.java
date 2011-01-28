package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.seploc.migracao.beans.Cliente;
import br.seploc.migracao.beans.Opcional;

public class MigraOpcionais extends Migra<Cliente> {
	
	public List<Opcional> seleciona(Connection c) throws Exception {
		int cont = 1;
		List<Opcional> retorno = new ArrayList<Opcional>();
		// pega o Statement
		PreparedStatement stmt = c
				.prepareStatement("SELECT vcrCod,vcrNomeItem,dblValorItem " +
								  "FROM tbl_opcionaisreqserv");
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {
			Opcional op = new Opcional();
			op.setId(cont++);
			op.setVcrCod(rs.getString("vcrCod"));
			op.setVcrNomeItem(rs.getString("vcrNomeItem"));
			op.setDblValorItem(rs.getDouble("dblValorItem"));
			retorno.add(op);
		}
		stmt.close();
		return retorno;
	}
	
	public void insereDados(Connection c, List<Opcional> lista) throws Exception {
		String sql = "INSERT INTO  tbl_opcionaisreqserv (intCod,vcrNomeItem,dblValorItem, tspVersao) VALUES (?, ?, ?, CURRENT_TIMESTAMP);";
		for (Opcional op : lista) {
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, op.getId());
			stmt.setString(2, op.getVcrNomeItem());
			stmt.setDouble(3, op.getDblValorItem());
			stmt.execute();
			stmt.close();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MigraOpcionais migra = new MigraOpcionais();
		try {
			Connection c = new ConnectionFactory().getConnection("dbcopytec",
					"root", "");
			List<Opcional> lista = migra.seleciona(c);
			for (Opcional op : lista) {
				System.out.println(op.getId()+ " " + op.getVcrNomeItem());
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

	@Override
	protected void cqMigracao() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void insereDados() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void seleciona() throws Exception {
		// TODO Auto-generated method stub
		
	}

}

