package br.seploc.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 * Servlet implementation class JasperReportsBrowserDemoServlet2
 */
public class JasperReportsBrowserDemoServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JasperReportsBrowserDemoServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream servletOutputStream = response.getOutputStream();
		ServletContext context = getServletContext();
	    InputStream reportStream = getServletConfig().getServletContext()
	        .getResourceAsStream( "/WEB-INF/reports/RelatorioRequisicao.jasper");

	    Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/seploc2", // coloque o IP se
															// não for usado
															// localhost
					"root", // mude para o nome do usuário do seu bd
					""); // mude para a senha deste usuário
		} catch (Exception e) {
			System.out.println("Erro ao obter conexao via DriverManager: "
					+ e.getMessage());
		}
		Date dataInicio = new Date(new GregorianCalendar(2007,
				GregorianCalendar.AUGUST, 01).getTimeInMillis());
		Date dataFim = new Date(new GregorianCalendar(2010,
				GregorianCalendar.AUGUST, 01).getTimeInMillis());
		// parâmetros, se houverem
		Map parametros = new HashMap();
		parametros.put("cliente_id", 35);
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		parametros.put("porcentagem", new Double(10.0));
	    try
	    {
	    	
			
	      JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,
	    		  parametros, conn);
	       
//	      response.setContentType("application/pdf");
//	      servletOutputStream.flush();
//	      servletOutputStream.close();
	      response.setHeader("Pragma", "public");
			response.setHeader("Cache-control", "must-revalidate");
			// response.setHeader("Pragma", "");
			// response.setHeader("Cache-Control", "");
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			response.setHeader("Expires", "");
			response.setHeader("Content-Disposition", "inline; filename="
					+ "test.pdf");
			response.setContentLength(arrayOutputStream.size());

			OutputStream out = response.getOutputStream();
			arrayOutputStream.writeTo(out);
			out.flush();
			out.close();
	    }
	    catch (Exception e)
	    {
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
