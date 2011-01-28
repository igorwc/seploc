package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.seploc.migracao.beans.RequisicaoServico;

public class MigraReqServ extends Migra<RequisicaoServico>{
	 

	@Override
	public void setConexoes(Connection copytec, Connection seploc) {
		copytecConnection = copytec;
		seplocConnection = seploc;
		
	}

	private MigraReqServ() {
		lista = new ArrayList<RequisicaoServico>();
	}
	public static MigraReqServ getInstance(Connection copytec, Connection seploc) {
		MigraReqServ obj = new MigraReqServ();
		obj.setConexoes(copytec, seploc);
		return obj;
	}
	@Override
	protected void insereDados() throws Exception {
		String sql = " INSERT INTO tbl_reqserv (intNumreq ,datData,"
			+ "intCodProj ,intCodEnt ,dblValorTotal ,intStatus ,intVisivelNf"
			+ " ,intVisivelReq ,dblValorEnt ,tspVersao) "
			+ "VALUES (?, ? , ?, ? , ? , ? , ? , ? , ? ,CURRENT_TIMESTAMP) ";
	for (RequisicaoServico r : lista ) {
		PreparedStatement stmt = seplocConnection.prepareStatement(sql);
		stmt.setInt(1, r.getNumReq());
		stmt.setDate(2, r.getData());
		stmt.setInt(3, r.getProjeto());
		stmt.setInt(4, r.getEntrega());
		stmt.setDouble(5, r.getTotal());
		stmt.setInt(6, r.getStatus());
		stmt.setInt(7, r.getVisivelNF());
		stmt.setInt(8, r.getVisivelReq());
		stmt.setDouble(9, r.getVlEntrega());
		stmt.execute();
		stmt.close();
	}
	}

	@Override
	protected void seleciona() throws Exception {
		// pega o Statement
		PreparedStatement stmt = copytecConnection
				.prepareStatement("SELECT  intNumreq, datData, vcrCnpj, intCodProj, "
						+ "intCodEnt, dblValorTotal, intStatus,intCodCobr, "
						+ "intVisivelNf, intVisivelReq, dblValorEnt FROM tbl_reqserv "
						+ "where intCodProj in (select intCodProj from seploc2.tbl_projetos )");
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {
			RequisicaoServico rq = new RequisicaoServico();
			rq.setNumReq(rs.getInt("intNumreq"));
			rq.setData(rs.getDate("datData"));
			rq.setCnpj(rs.getString("vcrCnpj"));
			rq.setProjeto(rs.getInt("intCodProj"));
			rq.setEntrega(rs.getInt("intCodEnt"));
			rq.setTotal(rs.getDouble("dblValorTotal"));
			rq.setStatus(rs.getInt("intStatus"));
			rq.setCobrador(rs.getInt("intCodCobr"));
			rq.setVisivelNF(rs.getInt("intVisivelNf"));
			rq.setVisivelReq(rs.getInt("intVisivelReq"));
			rq.setVlEntrega(rs.getDouble("dblValorEnt"));
			lista.add(rq);

		}
		stmt.close();
	}
	public static void main(String args[]) {
		MigraReqServ migra = MigraReqServ.getInstance(new ConnectionFactory().getConnection("dbcopytec",
					"root", ""), new ConnectionFactory().getConnection("seploc2", "root", ""));
		try {
			migra.migraDados();
			System.out.println("terminou");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void cqMigracao() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
 