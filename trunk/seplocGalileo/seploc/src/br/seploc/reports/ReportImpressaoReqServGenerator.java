package br.seploc.reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.seploc.migracao.ConnectionFactory;

public class ReportImpressaoReqServGenerator {
	private List<ArrayList<String>> dados;
	private Connection connection;
	private int numRequisicao;

	private ReportImpressaoReqServGenerator() {
		dados = new ArrayList<ArrayList<String>>();
	}

	public List<ArrayList<String>> getDados() {
		return dados;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public int getNumRequisicao() {
		return numRequisicao;
	}

	public void setNumRequisicao(int numRequisicao) {
		this.numRequisicao = numRequisicao;
	}

	public void geraDados() {
		int contador = 1;
		int flag = 1;
		String sql = "select numReq, item, formato,dimensao,nomePapel, impressao, qtd, subTotal ,valorItem,linha from "
				+ "((select "
				+ "li.intNumreq as numReq, li.vcrNomeArq as item, li.dblFormato as formato , "
				+ "li.dblDimensao as dimensao,pa.vcrNome as nomePapel,li.vcrImpressao as impressao,li.intQuant as qtd, li.dblValorUnit as subTotal, "
				+ " li.dblValorSubUnit as valorItem, li.intNumLin as linha "
				+ "from tbl_linhareq li,tbl_papel pa, tbl_reqserv rr "
				+ "where li.intCodPap = pa.intCodPap "
				+ "and rr.intNumReq = ? "
				+ "and rr.intNumReq = li.intNumReq) "
				+ "union "
				+ "(SELECT r.intNumreq as numReq,op.vcrNomeItem as item,null as formato ,null as dimensao , null as nomePapel ,  null as impressao, "
				+ "rso.intQuant as qtd ,  (op.dblValorItem*rso.intQuant) as subTotal, op.dblValorItem as valorItem, null as linha "
				+ "FROM tbl_reqserv r,tbl_opcionaisreqserv op, tbl_reqservopcionais rso "
				+ "WHERE "
				+ "rso.intNumReq = r.intNumReq and "
				+ "rso.intCodOp = op.intCod and "
				+ "r.intNumReq = ?  ) "
				+ ") as tabela3";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, numRequisicao);
			stmt.setInt(2, numRequisicao);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> elemento = new ArrayList<String>();
				elemento.add((contador++) + "");
				// null as formato ,null as dimensao , null as nomePapel , null
				// as impressao,
				elemento.add(rs.getInt("numReq") + "");
				elemento.add(rs.getString("item"));
				String formato = rs.getDouble("formato") + "";
				if (formato.equals("0.0") && rs.getString("nomePapel") == null) {
					elemento.add("----");
				} else {
					elemento.add(rs.getDouble("formato") + "");
				}
				String dimensao = rs.getDouble("dimensao") + "";
				if (dimensao.equals("0.0") && rs.getString("nomePapel") == null) {
					elemento.add("----");
				} else {
					elemento.add(rs.getDouble("dimensao") + "");
				}
				String nomePapel = rs.getString("nomePapel");
				if (nomePapel == null || nomePapel.equals("null")) {
					elemento.add("----");
				} else {
					elemento.add(nomePapel);
				}
				String impressao = rs.getString("impressao");
				if (impressao == null || impressao.equals("null")) {
					elemento.add("----");
				} else {
					elemento.add(impressao);
				}

				elemento.add(rs.getInt("qtd") + "");
				elemento.add(rs.getDouble("subTotal") + "");
				elemento.add(rs.getDouble("valorItem") + "");
				String linha = rs.getInt("linha") + "";
				if (linha.equals("0") && nomePapel == null) {
					elemento.add("----");
				} else {
					elemento.add(rs.getInt("linha") + "");
				}
				
				dados.add(elemento);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void imprimeDados() {
		for (ArrayList<String> linha : dados) {
			for (String elemento : linha) {
				System.out.print(elemento + ", ");
			}
			System.out.println();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReportImpressaoReqServGenerator rr = new ReportImpressaoReqServGenerator();
		rr.setConnection(new ConnectionFactory().getConnection("seploc2",
				"root", ""));
		rr.setNumRequisicao(4880);
		rr.geraDados();
		rr.imprimeDados();

	}

}
