package br.seploc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.seploc.reports.RelListImpressaoReqServGenerator;
import br.seploc.util.SessionObjectsManager;

/**
 * Servlet implementation class RelClienteReqList
 */
public class RelClienteReqList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RelClienteReqList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String d = getServletContext().getRealPath(
				"/WEB-INF/reports/relReqServPorCobrador.html").substring(
				0,
				getServletContext().getRealPath(
						"/WEB-INF/reports/relReqServPorCobrador.html").indexOf(
						"relReqServPorCobrador.html"));
		int clienteID = (Integer)request.getSession().getAttribute("clientID");//SessionObjectsManager.recuperaObjetoSessao("clientID");
		List<Integer> listaids = (ArrayList<Integer>)request.getSession().getAttribute("ReqServIDs");
		RelListImpressaoReqServGenerator rr = new RelListImpressaoReqServGenerator();
		rr.setConnection(null);
		rr.setClienteID(clienteID);
		rr.setListaReqServIds(listaids);
		String xml = getServletContext().getRealPath(
		"/WEB-INF/config/empresa.xml").substring(
		0,
		getServletContext().getRealPath(
				"/WEB-INF/config/empresa.xml").indexOf(
				"empresa.xml"))+"empresa.xml";
		rr.setXmlPath(xml);
		rr.geraDados();
		out.print(rr.imprimeDadosWeb2(d));
		try {
			out.flush();
			out.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
