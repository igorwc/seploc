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
import br.seploc.util.HtmlManipulator;
import br.seploc.util.UtilsArquivo;
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
	private String dir;
	private int paginas;
	private double valorTotal;
	private double valorTotalDesconto;
	private int hasDesconto;

	public ReportImpressaoReqServGenerator() {
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
		dir = "";
		paginas = 0;
		valorTotal = 0.0;
		valorTotalDesconto = 0.0;
		hasDesconto = 0;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
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

	private void geraDadosOperador() {
		String sql = "SELECT vcrLoginAlter AS login, datdataAlter AS data "
				+ "FROM tbl_reqservusuario " + "WHERE intNumReq = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, numRequisicao);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String loginTemp = rs.getString("login");
				Date data = rs.getDate("data");

				if (loginTemp != null) {
					operador = loginTemp;
				}
				if (data != null) {
					dataAlteracao = new Date(data.getTime());
				} else {
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
			// TODO: handle exception
		}

	}
	
	private void calculaTotalRequisicao() {
		if (numRequisicao == 0) {
			return;
		}
		verificaEntrega();
		String sql = "SELECT dblValorTotal as vtotal, (dblValorTotal- dblValorTotal *intOrcamento/100) as total" +
					 " FROM tbl_reqserv"+
				     " WHERE  intNumreq = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, numRequisicao);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Double flag = rs.getDouble("vtotal");
				Double flag2 = rs.getDouble("total");
				if (flag != null) {
					this.valorTotal = flag;
				} else {
					this.valorTotal = 0.0;
				}
				if (flag2 != null) {
					this.valorTotalDesconto = flag2;
				} else {
					this.valorTotalDesconto = 0.0;
				}
				System.out.println("Valores:");
				System.out.println("Total:"+valorTotal );
				System.out.println("Totald:"+valorTotalDesconto );
				System.out.println("flag.doubleValue():"+flag.doubleValue() );
				System.out.println("flag2.doubleValue():"+flag2.doubleValue() );
				System.out.println("flag.doubleValue() == flag2.doubleValue():"+(flag.doubleValue() == flag2.doubleValue() ));
				if(flag.doubleValue() != flag2.doubleValue()){
					hasDesconto = 1;
				} else {
					hasDesconto = 0;
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
	public Map getDataModel() {
		Map map = new HashMap();
		map.put("cliente", cliente);
		map.put("dados", dados);
		map.put("subs", subtotais);
		System.out.println(dados.size() + "" + subtotais.size());
		System.out.println(subtotais);
		map.put("paginacao", 1);
		NumberFormat formatter = new DecimalFormat("0.00");
		map.put("entrega", formatter.format(entrega));
		map.put("operador", operador);
		map.put("data_alteracao", dataAlteracao);
		map.put("current_date", new Date());
		map.put("paginacao_total", paginas);
		calculaTotalRequisicao();
		map.put("valor_total", valorTotal);
		map.put("valor_totald", valorTotalDesconto);
		map.put("desconto", hasDesconto);
		map.put("numRequisicao", numRequisicao);
		map.put("nomeProjeto", nomeProjeto);

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

	@SuppressWarnings("unchecked")
	public void geraDados() {
		int contador = 1;
		int flag = 1;
		int quantidadeReq = 0;
		int paginas = 0;
		double subtotal = 0.0;
		double total = 0.0;
		int paginacao = 0;
		int complemento = 0;
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
		String sqlCount = "select count(*) from "
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
				+ "WHERE " + "rso.intNumReq = r.intNumReq and "
				+ "rso.intCodOp = op.intCod and " + "r.intNumReq = ?  ) "
				+ ") as tabela3";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			PreparedStatement stmtCount = connection.prepareStatement(sqlCount);
			stmtCount.setInt(1, numRequisicao);
			stmtCount.setInt(2, numRequisicao);
			ResultSet rsCount = stmtCount.executeQuery();
			if (rsCount.next()) {
				quantidadeReq = rsCount.getInt(1);
			}
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
			stmt.setInt(1, numRequisicao);
			stmt.setInt(2, numRequisicao);
			ResultSet rs = stmt.executeQuery();
			int aux = 1;
			ArrayList<ImpressaoBean> pagina = new ArrayList<ImpressaoBean>();
			subtotais = new ArrayList();
			for (int i = paginas; i > 0; i--) {

				while (rs.next()) {
					NumberFormat formatter = new DecimalFormat("0.00");
					ImpressaoBean bean = new ImpressaoBean();
					bean.setNomePapel(rs.getString("nomePapel"));
					bean.setNumReq(rs.getInt("numReq") + "");
					bean.setSeq((contador++) + "");
					String temp = rs.getString("item");
					if (temp.length() > 35) {
						bean.setItem(temp.substring(0, 35));
					} else {
						bean.setItem(rs.getString("item"));
					}
					bean.setFormato(formatter.format(rs.getDouble("formato"))
							+ "");
					bean.setDimensao(formatter.format(rs.getDouble("dimensao"))
							+ "");
					bean.setImpressao(rs.getString("impressao"));
					bean.setLinha(rs.getInt("linha") + "");
					bean.setQtd(rs.getInt("qtd") + "");
					bean.setSubTotal(formatter.format(rs.getDouble("subTotal"))
							+ "");
					bean.setValorItem(formatter.format(rs
							.getDouble("valorItem"))
							+ "");
					subtotal += rs.getDouble("subTotal");
					// total += subtotal;

					pagina.add(bean);
					aux++;
					if (aux > 12) {
						aux = 1;
						break;
					}

				}
//				this.valorTotal += subtotal;

				dados.add(pagina);
				pagina = new ArrayList<ImpressaoBean>();
				subtotais.add(total);
				subtotal = 0.0;

			}
//			this.valorTotal += this.entrega;
			ArrayList<ImpressaoBean> ultimaPagina = dados.get(dados.size() - 1);
			if (ultimaPagina != null) {
				ImpressaoBean temp = ultimaPagina.get(ultimaPagina.size() - 1);
				if (temp != null) {
					int seq = Integer.parseInt(temp.getSeq());
					if (ultimaPagina.size() < 12) {
						while (ultimaPagina.size() != 12) {
							seq++;
							ultimaPagina.add(generateDummyImpressaoBean(seq));
							
						}
					}
				}
			}
			// qtdItens = --contador;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					"impressaoReqServ.html", dir);
			retorno = HtmlManipulator.converteParaHtml(s);
			;
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;

	}

	public String imprimeDados() {
		String retorno = "";
		// System.out.println("Código Projeto: " + codProjeto);
		// System.out.println("Nome Projeto: " + nomeProjeto);
		// System.out.println("Tem opcionais: "+ hasOpcionais);
		// System.out.println("Preço da entrega: "+ entrega);
		// System.out.println("Quantidade de Itens: "+ qtdItens);
		// System.out.println("Operador: "+operador);
		// System.out.println("Data Ultima Alteracao:" + dataAlteracao);
		// System.out.println(cliente);
		// for (ArrayList<ImpressaoBean> linha : dados) {
		// for (ImpressaoBean elemento : linha) {
		// System.out.println(elemento);
		// }
		// System.out
		// .println("\n\n\n\n\n----------------Página---------------\n\n\n\n\n");
		// }
		try {
			String s = FreemarkerUtils.parseTemplate(getDataModel(),
					"impressaoReqServ.html");
			// OutputStream os = new
			// FileOutputStream("src/relatorios/impressaoReqServ.pdf");
			// System.out.println(s);
			// Html2Pdf.convert(s, os);
			retorno = HtmlManipulator.converteParaHtml(s);
			// UtilsArquivo.salvar("src/relatorios/impressaoReqServ2.html",HtmlManipulator.converteParaHtml(s),
			// false);
			// os.close();
			System.out.println(s);
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// OutputStream os = new FileOutputStream(outputFile);
		return retorno;

	}

	// public String geraPaginaHTML() {
	// try {
	// String s = FreemarkerUtils.parseTemplate(getDataModel(),
	// "impressaoReqServ.html");
	// OutputStream os = new
	// FileOutputStream("src/relatorios/impressaoReqServ.pdf");
	// UtilsArquivo.salvar("src/relatorios/impressaoReqServ2.html",HtmlManipulator.converteParaHtml(s),
	// false);
	// } catch (TemplateException e) {
	// e.printStackTrace();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// OutputStream os = new FileOutputStream(outputFile);

	// }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReportImpressaoReqServGenerator rr = new ReportImpressaoReqServGenerator();
		Connection conexao = new ConnectionFactory().getConnection("seploc2",
				"root", "");
		rr.setConnection(conexao);
//		rr.setNumRequisicao(59668);
		rr.setNumRequisicao(109251);
//		rr.setNumRequisicao(76430);
 
		
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
