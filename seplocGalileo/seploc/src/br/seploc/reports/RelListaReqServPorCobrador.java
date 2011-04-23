package br.seploc.reports;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.seploc.dao.CobradorDAO;
import br.seploc.reports.beans.CobradorBeanGrid;
import br.seploc.util.HtmlManipulator;
import br.seploc.util.Utils;
import freemarker.template.TemplateException;

public class RelListaReqServPorCobrador implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ArrayList<CobradorBeanGrid>> dados;
	private Date dataInicio;
	private Date dataFim;
	private int paginas;

	public RelListaReqServPorCobrador() {
		dados = new ArrayList<ArrayList<CobradorBeanGrid>>();
		paginas = 0;
		dataInicio = Utils.getDataInicioMesCorrente();
		dataFim = Utils.getDataFinalMesCorrente();
	}

	public void geraDados() {
		int contador = 1;
		int quantidadeCob = 0;
		int paginas = 0;
		int complemento = 0;
		CobradorDAO dao = new CobradorDAO();
		List<CobradorBeanGrid> listaCobradores = dao.getListaCobradoresGrid(
				dataInicio, dataFim);
		if (listaCobradores.size() == 0) {
			ArrayList<CobradorBeanGrid> paginaVazia = new ArrayList<CobradorBeanGrid>(
					12);
			int i = 0;
			while (paginaVazia.size() != 12) {
				i++;
				CobradorBeanGrid c = new CobradorBeanGrid();
				c.setSeq(i);
				paginaVazia.add(c);

			}
			dados.add(paginaVazia);
			return;
		}

		quantidadeCob = listaCobradores.size();
		if (quantidadeCob > 12) {
			paginas = quantidadeCob / 12;
			if (quantidadeCob % 12 > 0) {
				paginas++;
			}
		} else {
			paginas = 1;
		}
		complemento = quantidadeCob % 12;
		this.paginas = paginas;
		int aux = 1;
		ArrayList<CobradorBeanGrid> pagina = new ArrayList<CobradorBeanGrid>();
		int indexador = 0;
		for (int i = paginas; i > 0; i--) {
			//
			while (indexador < listaCobradores.size()) {
				NumberFormat formatter = new DecimalFormat("0.00");
				CobradorBeanGrid bean = listaCobradores.get(indexador++);
				pagina.add(bean);
				aux++;
				if (aux > 12) {
					aux = 1;
					break;
				}
			}
			dados.add(pagina);
			pagina = new ArrayList<CobradorBeanGrid>();

		}
		ArrayList<CobradorBeanGrid> ultimaPagina = dados.get(dados.size() - 1);
		if (ultimaPagina != null) {
			CobradorBeanGrid temp = ultimaPagina.get(ultimaPagina.size() - 1);
			if (temp != null) {
				int seq = temp.getSeq();
				if (ultimaPagina.size() < 12) {
					while (ultimaPagina.size() != 12) {
						seq++;
						CobradorBeanGrid c = new CobradorBeanGrid();
						c.setSeq(seq);
						ultimaPagina.add(c);

					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Map getDataModel() {
		Map map = new HashMap();
		map.put("dados", dados);
		map.put("paginacao", 1);
		map.put("current_date", new Date());
		map.put("paginacao_total", paginas);
		return map;
	}

	public String imprimeDadosWeb(String dir) {
		String retorno = "";
		try {
			String s = FreemarkerUtils.parseTemplateWeb(getDataModel(),
					"relGridReqServPorCobrador.html", dir);
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
					"relGridReqServPorCobrador.html");
			retorno = HtmlManipulator.converteParaHtml(s);
			System.out.println(s);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public static void main(String[] args) {
		RelListaReqServPorCobrador rr = new RelListaReqServPorCobrador();

		rr.setDataInicio(new Date(new GregorianCalendar(2009, Calendar.JUNE, 1)
				.getTimeInMillis()));
		rr.setDataFim(new Date(new GregorianCalendar(2009, Calendar.JUNE, 30)
				.getTimeInMillis()));
		rr.geraDados();
		rr.imprimeDados();

	}
}
