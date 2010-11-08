package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MigraReqServ {
	List<Requisicao> listaRequisicoes;

	public void seleciona(Connection c) throws Exception {
		// pega o Statement

		PreparedStatement stmt = c
				.prepareStatement("SELECT  intNumreq, datData, vcrCnpj, intCodProj, "
						+ "intCodEnt, dblValorTotal, intStatus,intCodCobr, "
						+ "intVisivelNf, intVisivelReq, dblValorEnt FROM tbl_reqserv "
						+ "where intCodProj in (select intCodProj from seploc2.tbl_projetos )");
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {
			Requisicao rq = new Requisicao();
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
			listaRequisicoes.add(rq);

		}
		stmt.close();
	}

	public void insereDados(Connection c) throws Exception {
		String sql = " INSERT INTO tbl_reqserv (intNumreq ,datData,"
				+ "intCodProj ,intCodEnt ,dblValorTotal ,intStatus ,intVisivelNf"
				+ " ,intVisivelReq ,dblValorEnt ,tspVersao) "
				+ "VALUES (?, ? , ?, ? , ? , ? , ? , ? , ? ,CURRENT_TIMESTAMP) ";
		for (Requisicao r : listaRequisicoes) {
			PreparedStatement stmt = c.prepareStatement(sql);
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

	public static void main(String args[]) {
		MigraReqServ migra = new MigraReqServ();
		try {
			Connection c = new ConnectionFactory().getConnection("copytec",
					"root", "");
			migra.seleciona(c);

			c.close();
			c = new ConnectionFactory().getConnection("seploc2", "root", "");
			migra.insereDados(c);
			// c.commit();
			c.close();
			System.out.println("terminou");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MigraReqServ() {
		listaRequisicoes = new ArrayList<Requisicao>();
	}
}

class Requisicao {
	private Integer numReq;
	private Date data;
	private String cnpj;
	private Integer projeto;
	private Integer entrega;;
	private Double total;
	private Integer status;
	private Integer cobrador;
	private Integer visivelNF;
	private Integer visivelReq;
	private Double vlEntrega;

	public Integer getNumReq() {
		return numReq;
	}

	public void setNumReq(Integer numReq) {
		this.numReq = numReq;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Integer getProjeto() {
		return projeto;
	}

	public void setProjeto(Integer projeto) {
		this.projeto = projeto;
	}

	public Integer getEntrega() {
		return entrega;
	}

	public void setEntrega(Integer entrega) {
		this.entrega = entrega;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCobrador() {
		return cobrador;
	}

	public void setCobrador(Integer cobrador) {
		this.cobrador = cobrador;
	}

	public Integer getVisivelNF() {
		return visivelNF;
	}

	public void setVisivelNF(Integer visivelNF) {
		this.visivelNF = visivelNF;
	}

	public Integer getVisivelReq() {
		return visivelReq;
	}

	public void setVisivelReq(Integer visivelReq) {
		this.visivelReq = visivelReq;
	}

	public Double getVlEntrega() {
		return vlEntrega;
	}

	public void setVlEntrega(Double vlEntrega) {
		this.vlEntrega = vlEntrega;
	}

}