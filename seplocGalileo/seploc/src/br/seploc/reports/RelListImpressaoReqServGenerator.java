package br.seploc.reports;

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

import javax.xml.xpath.XPathConstants;

import br.seploc.dao.ClienteDAO;
import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.migracao.ConnectionFactory;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.reports.beans.ClienteBean;
import br.seploc.reports.beans.ImpressaoBean;
import br.seploc.reports.beans.ReqServImpBean;
import br.seploc.util.HtmlManipulator;
import br.seploc.util.xmlConfig.XPathReader;
import freemarker.template.TemplateException;

public class RelListImpressaoReqServGenerator {
	private List<ArrayList<ReqServImpBean>> dados;
	private List<Integer> listaReqServIds;
	private List<RequisicaoServico> listaRequisicoes;
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
	private String dir;
	private int paginas;
	private double valorTotal;
	private double valorTotalDesconto;
	private int hasDesconto;
	private Integer clienteID;
	private String linha1,linha2,linha3,linha4;

	public RelListImpressaoReqServGenerator() {
		dados = new ArrayList<ArrayList<ReqServImpBean>>();
		listaReqServIds = new ArrayList<Integer>(0);
		listaRequisicoes = new ArrayList<RequisicaoServico>();
		nomeProjeto = "";
		codProjeto = 0;
		numRequisicao = 0;
		cliente = new ClienteBean();
		hasOpcionais = false;
		entrega = 0.0;
		qtdItens = 0;
		dataAlteracao = new Date(Calendar.getInstance().getTimeInMillis());
		operador = "";
		dir = "";
		paginas = 0;
		valorTotal = 0.0;
		valorTotalDesconto = 0.0;
		hasDesconto = 0;
		clienteID = 0;
		linha1 ="";
		linha2 ="";
		linha3 ="";
		linha4 ="";
	}

	public Integer getClienteID() {
		return clienteID;
	}

	public void setClienteID(Integer clienteID) {
		this.clienteID = clienteID;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public List<ArrayList<ReqServImpBean>> getDados() {
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

	private void verificaEntrega() {
		if (numRequisicao == 0) {
			return;
		}
		String sql = "SELECT dblPreco  AS preco " + "FROM  tbl_entrega "
				+ "WHERE intCodEnt = (SELECT intCodEnt " + "FROM tbl_reqserv "
				+ "WHERE intNumreq = ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, numRequisicao);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Double flag = rs.getDouble("preco");
				if (flag != null) {
					this.entrega = flag;
				} else {
					this.entrega = 0.0;
				}
			}
		} catch (Exception e) {
		}

	}

	private void calculaTotalRequisicao() {
		if (numRequisicao == 0) {
			return;
		}
		verificaEntrega();
		try {
			RequisicaoServicoDAO dao = new RequisicaoServicoDAO();

			Double valorTotal = dao.calculaTotalRequisicao(numRequisicao);
			RequisicaoServico rs = dao.recupera(numRequisicao);
			if (rs == null) {
				return;
			}

			this.valorTotal = valorTotal + this.entrega;
			System.out.println("Valor Total:" + valorTotal);
			System.out.println("Entrega:" + this.entrega);
			this.valorTotalDesconto = (valorTotal - (valorTotal
					* rs.getDesconto() / 100))
					+ this.entrega;
			if (this.valorTotal != this.valorTotalDesconto) {
				hasDesconto = 1;
			} else {
				hasDesconto = 0;
			}
		} catch (Exception e) {
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
		}
	}

	private void recuperaDadosCliente() {
		ClienteDAO dao = new ClienteDAO();
		Cliente c = dao.recupera(clienteID);
		if (c != null) {
			String cpf = c.getCpf();
			String cnpj = c.getCnpj();
			if (cnpj == null || cnpj.equals("") || cnpj.endsWith("0001/99")) {
				this.cliente.setNi(cpf);
			} else {
				this.cliente.setNi(cnpj);
			}
			this.cliente.setNome(c.getRazao());
			this.cliente.setEndereco(c.getEndereco());
			this.cliente.setBairro(c.getBairro());
			this.cliente.setCidade(c.getCidade());
			this.cliente.setUf(c.getEstado());
			this.cliente.setCep(c.getCep());
		}
	}

	@SuppressWarnings("unchecked")
	public Map getDataModel() {
		Map map = new HashMap();
		map.put("cliente", cliente);
		map.put("dados", dados);
		map.put("paginacao", 1);
		NumberFormat formatter = new DecimalFormat("0.00");
		map.put("entrega", formatter.format(entrega));
		map.put("operador", operador);
		map.put("data_alteracao", dataAlteracao);
		map.put("current_date", new Date());
		map.put("paginacao_total", paginas);
		calculaTotalRequisicao();
		map.put("valor_total", valorTotal);
		System.out.println("Valor Total:" + valorTotal);
		map.put("valor_totald", valorTotalDesconto);
		map.put("desconto", hasDesconto);
		map.put("numRequisicao", numRequisicao);
		map.put("nomeProjeto", nomeProjeto);
		geraCabecalho();
		map.put("linha1", linha1);
		map.put("linha2", linha2);
		map.put("linha3", linha3);
		map.put("linha4", linha4);
		return map;
	}

	private ImpressaoBean generateDummyImpressaoBean(int seq) {
		ImpressaoBean bean = new ImpressaoBean();
		bean.setNomePapel("");
		bean.setNumReq("");
		bean.setSeq(seq + "");
		bean.setItem("");
		bean.setFormato("");
		bean.setDimensao("");
		bean.setImpressao("");
		bean.setLinha("");
		bean.setQtd("");
		bean.setSubTotal("");
		;
		bean.setValorItem("");

		return bean;
	}

	public void geraDados() {
		int contador = 1;
		int flag = 1;
		int quantidadeReq = 0;
		int paginas = 0;
		double subtotal = 0.0;
		double total = 0.0;
		int paginacao = 0;
		int complemento = 0;
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		recuperaDadosCliente();
		if (listaReqServIds.size() == 0) {
			ArrayList<ReqServImpBean> paginaVazia = new ArrayList<ReqServImpBean>(
					12);
			int i = 0;
			while (paginaVazia.size() != 12) {
				i++;
				paginaVazia.add(new ReqServImpBean(i + ""));

			}
			valorTotal = 0.0;
			dados.add(paginaVazia);
			hasDesconto = 0;
			return;
		}
		for (Integer i : listaReqServIds) {
			try {
				RequisicaoServico temp = dao.recupera(i);
				if (temp != null) {
					listaRequisicoes.add(temp);
					valorTotal += temp.getValorTotal();
					valorTotalDesconto += temp.getValorTotalComDesconto();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (valorTotal != valorTotalDesconto) {
			hasDesconto = 1;
		} else {
			hasDesconto = 0;
		}
		quantidadeReq = listaRequisicoes.size();
		if (quantidadeReq > 12) {
			paginas = quantidadeReq / 12;
			if (quantidadeReq % 12 > 0) {
				paginas++;
			}
		} else {
			paginas = 1;
		}
		complemento = quantidadeReq % 12;
		this.paginas = paginas;
		int aux = 1;
		ArrayList<ReqServImpBean> pagina = new ArrayList<ReqServImpBean>();
		int indexador = 0;
		for (int i = paginas; i > 0; i--) {
			//
			while(indexador < listaRequisicoes.size() ) {
				RequisicaoServico r = listaRequisicoes.get(indexador++);
				NumberFormat formatter = new DecimalFormat("0.00");
				ReqServImpBean bean = new ReqServImpBean();
				if (r.getProjeto().getProjeto().length() > 35) {
					bean.setProjeto(r.getProjeto().getProjeto()
							.substring(0, 35));
				} else {
					bean.setProjeto(r.getProjeto().getProjeto());
				}
				bean.setNumReq(r.getNumReq() + "");
				bean.setSeq((contador++) + "");
				bean.setData(r.getData());
				
				bean.setSubtotal(formatter.format(r.getValorTotal()) + "");
				bean.setSubtotalDesc(formatter.format(r.getValorTotalComDesconto()) + "");
//				formatter = new DecimalFormat("00");
				Double d = new Double(0.);
				if (r.getDesconto() != null){
					d = r.getDesconto();
				}
				bean.setDesconto(formatter.format(d) + "%");
				pagina.add(bean);
				aux++;
				if (aux > 12) {
					aux = 1;
					break;
				}
			}
			dados.add(pagina);
			pagina = new ArrayList<ReqServImpBean>();

		}
		ArrayList<ReqServImpBean> ultimaPagina = dados.get(dados.size() - 1);
		if (ultimaPagina != null) {
			ReqServImpBean temp = ultimaPagina.get(ultimaPagina.size() - 1);
			if (temp != null) {
				int seq = Integer.parseInt(temp.getSeq());
				if (ultimaPagina.size() < 12) {
					while (ultimaPagina.size() != 12) {
						seq++;
						ultimaPagina.add(new ReqServImpBean(seq + ""));

					}
				}
			}
		}
	}

	public String imprimeDadosWeb() {
		String retorno = "";
		try {
			String s = FreemarkerUtils.parseTemplateWeb(getDataModel(),
					"impressaoReqServ.html", "/WEB-INF/reports/");
			System.out.println(s);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;

	}

	public String imprimeDadosWeb2(String dir) {
		String retorno = "";
		try {
			String s = FreemarkerUtils.parseTemplateWeb(getDataModel(),
					"impressaoListaReqServ.html", dir);
			retorno = HtmlManipulator.converteParaHtml(s);
			;
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;

	}
	private void geraCabecalho(){
		XPathReader reader = new XPathReader("src/META-INF/empresa.xml" );
		String path = "/empresa/linha1";
		linha1 = reader.read(path, 	XPathConstants.STRING) + "";
		path = "/empresa/linha2";
		linha2 = reader.read(path, XPathConstants.STRING) + "";
		path = "/empresa/linha3";
		linha3 = reader.read(path, XPathConstants.STRING) + "";
		path = "/empresa/linha4";
		linha4 = reader.read(path, XPathConstants.STRING) + "";
	}
	public String imprimeDados() {
		String retorno = "";
		try {
			String s = FreemarkerUtils.parseTemplate(getDataModel(),
					"impressaoListaReqServ.html");
			retorno = HtmlManipulator.converteParaHtml(s);
			System.out.println(s);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;

	}

	public List<Integer> getListaReqServIds() {
		return listaReqServIds;
	}

	public void setListaReqServIds(List<Integer> listaReqServIds) {
		this.listaReqServIds = listaReqServIds;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RelListImpressaoReqServGenerator rr = new RelListImpressaoReqServGenerator();
		Connection conexao = new ConnectionFactory().getConnection("seploc2",
				"root", "");
		rr.setConnection(conexao);

		
		List<Integer> lista = new ArrayList<Integer>();
		/* TESTE 1 */
		/* CLIENTE ATTO ENGENHARIA */
//		rr.setClienteID(26);
//		lista.add(59668);
//		lista.add(109251);
//		lista.add(76430);
//		lista.add(73635);
		
		/* TESTE 2 */
		/* CLIENTE ALCOA */
		rr.setClienteID(36);
		lista.add(78105);
		lista.add(78109);
		lista.add(78735);
		lista.add(79209);
		lista.add(80507);
		lista.add(80598);
		lista.add(80765);
		lista.add(81722);
		lista.add(81725);
		lista.add(82339);
		lista.add(82342);
		lista.add(82446);
		lista.add(82480);
		lista.add(82702);
		lista.add(82967);
		lista.add(82970);
		rr.setListaReqServIds(lista);

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
