package br.seploc.reports;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathConstants;

import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.dao.UsuarioDAO;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.pojos.Usuario;
import br.seploc.reports.beans.ReqServImpBean;
import br.seploc.util.HtmlManipulator;
import br.seploc.util.xmlConfig.XPathReader;
import freemarker.template.TemplateException;

public class RelReqServPorPlotador implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ArrayList<ReqServImpBean>> dados;
	private List<Integer> listaReqServIds;
	private List<RequisicaoServico> listaRequisicoes;
	private Integer plotadorID;
	private String plotador;
	private double valorTotal;
	private int paginas;
	private String linha1,linha2,linha3,linha4;
	private String xmlPath;

	public RelReqServPorPlotador() {
		dados = new ArrayList<ArrayList<ReqServImpBean>>();
		listaReqServIds = new ArrayList<Integer>(0);
		listaRequisicoes = new ArrayList<RequisicaoServico>();
		paginas = 0;
		valorTotal = 0.0;
		linha1 ="";
		linha2 ="";
		linha3 ="";
		linha4 ="";
		xmlPath = "src/META-INF/empresa.xml";
	}

	private void recuperaPlotador() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario u = dao.recupera(plotadorID);
		this.plotador = u.getNome();
	}

	private void geraCabecalho() {
		XPathReader reader = new XPathReader(xmlPath);
		String path = "/empresa/linha1";
		linha1 = reader.read(path, XPathConstants.STRING) + "";
		path = "/empresa/linha2";
		linha2 = reader.read(path, XPathConstants.STRING) + "";
		path = "/empresa/linha3";
		linha3 = reader.read(path, XPathConstants.STRING) + "";
		path = "/empresa/linha4";
		linha4 = reader.read(path, XPathConstants.STRING) + "";
	}
	
	public void geraDados() {
		int contador = 1;
		int quantidadeReq = 0;
		int paginas = 0;
		recuperaPlotador();
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
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
			return;
		}
		for (Integer i : listaReqServIds) {
			try {
				RequisicaoServico temp = dao.recupera(i);
				if (temp != null) {
					listaRequisicoes.add(temp);
					valorTotal += temp.getValorTotalComDesconto(); 

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
				if (r.getProjeto().getCliente().getRazao().length() > 35) {
					bean.setCliente(r.getProjeto().getCliente().getRazao()
							.substring(0, 35));
				} else {
					bean.setCliente(r.getProjeto().getCliente().getRazao());
				}
				bean.setNumReq(r.getNumReq() + "");
				bean.setSeq((contador++) + "");
				bean.setData(r.getData());				
				bean.setSubtotal(formatter.format(r.getValorTotalComDesconto()) + "");
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
	
	@SuppressWarnings("unchecked")
	public Map getDataModel() {
		Map map = new HashMap();
		map.put("plotador", plotador);
		map.put("dados", dados);
		map.put("paginacao", 1);
		map.put("current_date", new Date());
		map.put("paginacao_total", paginas);
		map.put("valor_total", valorTotal);
		geraCabecalho();
		map.put("linha1", linha1);
		map.put("linha2", linha2);
		map.put("linha3", linha3);
		map.put("linha4", linha4);
		return map;
	}

	public String imprimeDadosWeb(String dir) {
		String retorno = "";
		try {
			String s = FreemarkerUtils.parseTemplateWeb(getDataModel(),
					"relReqServPorCobrador.html", dir);
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
		try {
			String s = FreemarkerUtils.parseTemplate(getDataModel(),
					"relReqServPorPlotador.html");
			retorno = HtmlManipulator.converteParaHtml(s);
			System.out.println(s);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public void setPlotadorID(Integer plotadorID) {
		this.plotadorID = plotadorID;
	}

	public void setListaReqServIds(List<Integer> listaReqServIds) {
		this.listaReqServIds = listaReqServIds;
	}
	
	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public static void main(String[] args) {
		RelReqServPorPlotador rr = new RelReqServPorPlotador();
		List<Integer> lista = new ArrayList<Integer>();
		rr.setPlotadorID(5);
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

	}
}
