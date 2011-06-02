package br.seploc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.seploc.reports.RelReqServPorCobrador;

/**
 * Servlet implementation class RelCobradorImpressaoReqServ
 */
public class RelCobradorImpressaoReqServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RelCobradorImpressaoReqServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String d = getServletContext().getRealPath(
				"/WEB-INF/reports/impressaoListaReqServ.html").substring(
				0,
				getServletContext().getRealPath(
						"/WEB-INF/reports/impressaoListaReqServ.html").indexOf(
						"impressaoListaReqServ.html"));
		int cobID = (Integer)request.getSession().getAttribute("cobID");
		List<Integer> listaids = (ArrayList<Integer>)request.getSession().getAttribute("ReqServIDs");
		request.getSession().removeAttribute("ReqServIDs");
		request.getSession().removeAttribute("cobID");
		RelReqServPorCobrador rr = new RelReqServPorCobrador();
		rr.setCobradorID(cobID);
		String xml = getServletContext().getRealPath(
		"/WEB-INF/config/empresa.xml").substring(
		0,
		getServletContext().getRealPath(
				"/WEB-INF/config/empresa.xml").indexOf(
				"empresa.xml"))+"empresa.xml";
		rr.setXmlPath(xml);
		rr.setListaReqServIds(listaids);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
