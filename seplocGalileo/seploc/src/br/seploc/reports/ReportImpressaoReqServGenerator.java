package br.seploc.reports;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lowagie.text.DocumentException;

import br.seploc.migracao.ConnectionFactory;
import br.seploc.reports.beans.ClienteBean;
import br.seploc.reports.beans.ImpressaoBean;
import freemarker.template.TemplateException;

public class ReportImpressaoReqServGenerator {
	private List<ArrayList<ImpressaoBean>> dados;
	private Connection connection;
	private int numRequisicao;
	private String nomeProjeto;
	private int codProjeto;
	private ClienteBean cliente;
	private boolean hasOpcionais;
	private double entrega;
	private int qtdItens;
	private Date dataAlteracao;
	private String operador;
	private ArrayList subtotais;

	private ReportImpressaoReqServGenerator() {
		dados = new ArrayList<ArrayList<ImpressaoBean>>();
		nomeProjeto = "";
		codProjeto = 0;
		numRequisicao = 0;
		cliente = new ClienteBean();
		hasOpcionais = false;
		entrega = 0.0;
		qtdItens = 0;
		dataAlteracao = new Date(Calendar.getInstance().getTimeInMillis());
		operador = "";
	}

	public List<ArrayList<ImpressaoBean>> getDados() {
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

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	private void verificaOpcionais() {
		if (numRequisicao == 0) {
			return;
		}
		String sql = "SELECT count( * ) AS contagem "
				+ "FROM tbl_reqservopcionais " + "WHERE intNumReq = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, numRequisicao);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int flag = rs.getInt("contagem");
				if (flag != 0) {
					this.hasOpcionais = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	private void geraDadosOperador(){
		String sql = "SELECT vcrLoginAlter AS login, datdataAlter AS data " +
					 "FROM tbl_reqservusuario " +
					 "WHERE intNumReq = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, numRequisicao);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String loginTemp = rs.getString("login");
				Date data = rs.getDate("data");
			
				if(loginTemp != null){
					operador = loginTemp;
				}
				if(data != null){
					dataAlteracao = new Date(data.getTime());
				}else{
					dataAlteracao = new Date();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void verificaEntrega() {
		if (numRequisicao == 0) {
			return;
		}
		String sql = "SELECT dblPreco  AS preco " +
					 "FROM  tbl_entrega " +
					 "WHERE intCodEnt = (SELECT intCodEnt " +
					 				    "FROM tbl_reqserv " +
					 				    "WHERE intNumreq = ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, numRequisicao);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Double flag = rs.getDouble("preco");
				if (flag != null ) {
					this.entrega = flag;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void recuperaNomeProjeto() {
		String sql = "select intCodProj, vcrProjeto as nome "
				+ "from tbl_projetos "
				+ "where intCodProj = (select intCodProj "
				+ "from tbl_reqserv " + "where intNumReq = ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, numRequisicao);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				this.nomeProjeto = rs.getString("nome");
				this.codProjeto = rs.getInt("intCodProj");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void recuperaDadosCliente() {
		String sql = "SELECT  intClienteId, vcrCnpj, vcrRazao, vcrCpf, vcrEnder, vcrBairro, vcrCidade, vcrEstado, vcrCep "
				+ "FROM tbl_clientes  "
				+ "WHERE intClienteId = (select intClienteId "
				+ "from tbl_projetos " + "where intCodProj= ?)";
		try {
			if (codProjeto == 0) {
				return;
			}
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, codProjeto);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String cpf = rs.getString("vcrCpf");
				String cnpj = rs.getString("vcrCnpj");
				if (cnpj == null || cnpj.equals("") || cnpj.endsWith("0001/99")) {
					this.cliente.setNi(cpf);
				} else {
					this.cliente.setNi(cnpj);
				}
				this.cliente.setNome(rs.getString("vcrRazao"));
				this.cliente.setEndereco(rs.getString("vcrEnder"));
				this.cliente.setBairro(rs.getString("vcrBairro"));
				this.cliente.setCidade(rs.getString("vcrCidade"));
				this.cliente.setUf(rs.getString("vcrEstado"));
				this.cliente.setCep(rs.getString("vcrCep"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@SuppressWarnings("unchecked")
	public Map getDataModel(){
		Map map = new HashMap();
		map.put("cliente",cliente);
		map.put("dados", dados);
		map.put("subs",subtotais);
		System.out.println(dados.size() +""+ subtotais.size());
		System.out.println(subtotais);
		map.put("paginacao", 1);
		NumberFormat formatter =  new DecimalFormat("0.00"); 
		map.put("entrega",formatter.format(entrega));
		map.put("operador", operador);
		map.put("data_alteracao", dataAlteracao);
		map.put("current_date", new Date());
		map.put("paginacao_total", dados.size());
		return map;
	}
	public void geraDados() {
		int contador = 1;
		int flag = 1;
		double subtotal = 0.0;
		int paginacao = 0;
		recuperaNomeProjeto();
		recuperaDadosCliente();
		verificaEntrega();
		verificaOpcionais();
		geraDadosOperador();
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
			int aux = 1;
			ArrayList<ImpressaoBean> pagina = new ArrayList<ImpressaoBean>();
			subtotais = new ArrayList ();
			while (rs.next()) {
				if (aux == 13) {
					dados.add(pagina);
					pagina = new ArrayList<ImpressaoBean>();
					subtotais.add( subtotal);
					subtotal = 0.0;
					aux = 1;
//					aux--;
//				} else if (aux == 1) {
////					aux = 12;
//					
//					paginacao++;
					
				} else {
					aux++;
				}
				// elemento.add((contador++) + "");
				// null as formato ,null as dimensao , null as nomePapel , null
				// as impressao,
				NumberFormat formatter =    new DecimalFormat("0.00"); 
				ImpressaoBean bean = new ImpressaoBean();
				bean.setNomePapel(rs.getString("nomePapel"));
				bean.setNumReq(rs.getInt("numReq") + "");
				bean.setSeq((contador++) + "");
				bean.setItem(rs.getString("item"));
				bean.setFormato(formatter.format(rs.getDouble("formato"))  + "");
				bean.setDimensao(formatter.format(rs.getDouble("dimensao")) + "");
				bean.setImpressao(rs.getString("impressao"));
				bean.setLinha(rs.getInt("linha") + "");
				bean.setQtd(rs.getInt("qtd") + "");
				bean.setSubTotal(formatter.format(rs.getDouble("subTotal")) + "");
				bean.setValorItem(formatter.format(rs.getDouble("valorItem")) + "");
				subtotal+= rs.getDouble("subTotal");
				pagina.add(bean);
				// elemento.add(rs.getInt("numReq") + "");
				// elemento.add(rs.getString("item"));
				// String formato = rs.getDouble("formato") + "";
				// if (formato.equals("0.0") && rs.getString("nomePapel") ==
				// null) {
				// elemento.add("----");
				// } else {
				// elemento.add(rs.getDouble("formato") + "");
				// }
				// String dimensao = rs.getDouble("dimensao") + "";
				// if (dimensao.equals("0.0") && rs.getString("nomePapel") ==
				// null) {
				// elemento.add("----");
				// } else {
				// elemento.add(rs.getDouble("dimensao") + "");
				// }
				// String nomePapel = rs.getString("nomePapel");
				// if (nomePapel == null || nomePapel.equals("null")) {
				// elemento.add("----");
				// } else {
				// elemento.add(nomePapel);
				// }
				// String impressao = rs.getString("impressao");
				// if (impressao == null || impressao.equals("null")) {
				// elemento.add("----");
				// } else {
				// elemento.add(impressao);
				// }

				// elemento.add(rs.getInt("qtd") + "");
				// elemento.add(rs.getDouble("subTotal") + "");
				// elemento.add(rs.getDouble("valorItem") + "");
				// String linha = rs.getInt("linha") + "";
				// if (linha.equals("0") && nomePapel == null) {
				// elemento.add("----");
				// } else {
				// elemento.add(rs.getInt("linha") + "");
				// }

			}
			qtdItens = --contador;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void imprimeDados()    {
//		System.out.println("Código Projeto: " + codProjeto);
//		System.out.println("Nome Projeto: " + nomeProjeto);
//		System.out.println("Tem opcionais: "+ hasOpcionais);
//		System.out.println("Preço da entrega: "+ entrega);
//		System.out.println("Quantidade de Itens: "+ qtdItens);
//		System.out.println("Operador: "+operador);
//		System.out.println("Data Ultima Alteracao:" + dataAlteracao);
//		System.out.println(cliente);
//		for (ArrayList<ImpressaoBean> linha : dados) {
//			for (ImpressaoBean elemento : linha) {
//				System.out.println(elemento);
//			}
//			System.out
//					.println("\n\n\n\n\n----------------Página---------------\n\n\n\n\n");
//		}
		try {
			String s = FreemarkerUtils.parseTemplate(getDataModel(), "impressaoReqServ.html");
			OutputStream os = new FileOutputStream("src/relatorios/impressaoReqServ.pdf");
			System.out.println(s);
			Html2Pdf.convert(s, os);
//			os.close();
			System.out.println(s);
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		OutputStream os = new FileOutputStream(outputFile);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReportImpressaoReqServGenerator rr = new ReportImpressaoReqServGenerator();
		Connection conexao = new ConnectionFactory().getConnection("seploc2",
				"root", "");
		rr.setConnection(conexao);
		rr.setNumRequisicao(4880);
		rr.geraDados();
		rr.imprimeDados();
		try {
			if (!conexao.isClosed()) {
				conexao.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
