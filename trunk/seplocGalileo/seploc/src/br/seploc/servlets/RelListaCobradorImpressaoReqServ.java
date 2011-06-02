package br.seploc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.seploc.reports.RelListaReqServPorCobrador;
import br.seploc.reports.RelReqServPorCobrador;

/**
 * Servlet implementation class RelListaCobradorImpressaoReqServ
 */
public class RelListaCobradorImpressaoReqServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RelListaCobradorImpressaoReqServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String d = getServletContext().getRealPath(
				"/WEB-INF/reports/impressaoListaReqServ.html").substring(
				0,
				getServletContext().getRealPath(
						"/WEB-INF/reports/impressaoListaReqServ.html").indexOf(
						"impressaoListaReqServ.html"));
		Date dataInicio = (Date)request.getSession().getAttribute("dataInicio");
		Date dataFim = (Date)request.getSession().getAttribute("dataFim");
		request.getSession().removeAttribute("dataInicio");
		request.getSession().removeAttribute("dataFim");
		RelListaReqServPorCobrador rr = new RelListaReqServPorCobrador();
		rr.setDataInicio(dataInicio);
		rr.setDataFim(dataFim);
		String xml = getServletContext().getRealPath(
		"/WEB-INF/config/empresa.xml").substring(
		0,
		getServletContext().getRealPath(
				"/WEB-INF/config/empresa.xml").indexOf(
				"empresa.xml"))+"empresa.xml";
		rr.setXmlPath(xml);
		rr.geraDados();
		out.print(rr.imprimeDadosWeb(d) );
		try {
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
