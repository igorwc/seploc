package br.seploc.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPathConstants;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.seploc.migracao.ConnectionFactory;
import br.seploc.reports.ReportImpressaoReqServGenerator;
import br.seploc.util.xmlConfig.XPathReader;

/**
 * Servlet implementation class RelatorioServlet
 */
public class RelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static Connection con;
	public static String banco = "seploc2"; // Nome do banco de dados
	public static String usuario = "root"; // Usuario do banco
	public static String senha = ""; // Senha

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RelatorioServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.getRealPath("/WEB-INF/reports/");
		;
		System.out.println("\n\n\n\n\n\n\n"
				+ getServletContext().getRealPath(
						"/WEB-INF/reports/impressaoReqServ.html")
				+ "\n\n\n\n\n\n\n\n");
		String d = getServletContext().getRealPath(
				"/WEB-INF/reports/impressaoReqServ.html").substring(
				0,
				getServletContext().getRealPath(
						"/WEB-INF/reports/impressaoReqServ.html").indexOf(
						"impressaoReqServ.html"));
		System.out.println("\n\n\n\n\n\n\n"
				+d
				+ "\n\n\n\n\n\n\n\n");
		ReportImpressaoReqServGenerator rr = new ReportImpressaoReqServGenerator();
		XPathReader reader = new XPathReader(getServletContext().getRealPath("/WEB-INF/config/JDBC.xml") );
		String host = "/conexoes/conexao[1]/host";
		host = reader.read(host, 	XPathConstants.STRING) + "";
		String db = "/conexoes/conexao[1]/db";
		db = reader.read(db, XPathConstants.STRING) + "";
		String user = "/conexoes/conexao[1]/user";
		user = reader.read(user, XPathConstants.STRING) + "";
		String passwd = "/conexoes/conexao[1]/passwd";
		passwd = reader.read(passwd, XPathConstants.STRING) + "";
		Connection conexao = new ConnectionFactory().getConnection(host,db,
				user, passwd);
		int reqID = Integer.parseInt(request.getParameter("reqID"));
		System.out.println("Numero da requisicao da solicitação: "+reqID);
		rr.setConnection(conexao);
		rr.setNumRequisicao(reqID);
		rr.geraDados();
		// rr.imprimeDados();
		out.print(rr.imprimeDadosWeb2(d)); 
		try {
			out.flush();
			out.close();
			if (!conexao.isClosed()) {
				conexao.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @SuppressWarnings("unchecked")
	// protected void doGet(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// // obt�m a conex�o com o banco de dados
	// ServletOutputStream servletOutputStream = response.getOutputStream();
	// ServletContext context = getServletContext();
	// InputStream reportStream = getServletConfig().getServletContext()
	// .getResourceAsStream(
	// "/WEB-INF/reports/RelatorioRequisicao.jasper");
	//
	// Connection conn = null;
	// try {
	// Class.forName("com.mysql.jdbc.Driver");
	// conn = DriverManager.getConnection(
	// "jdbc:mysql://localhost:3306/seploc2", // coloque o IP se
	// // n�o for usado
	// // localhost
	// "root", // mude para o nome do usu�rio do seu bd
	// ""); // mude para a senha deste usu�rio
	// } catch (Exception e) {
	// System.out.println("Erro ao obter conexao via DriverManager: "
	// + e.getMessage());
	// }
	//
	// // gera o relat�rio
	// byte[] bytes = null;
	// try {
	//
	// // carrega os arquivos jasper
	// // JasperReport relatorioJasper = (JasperReport)JRLoader.loadObject(
	// // context.getRealPath("/WEB-INF/reports/RelatioRequisicao.jasper"));
	// HttpSession session = request.getSession();
	// Date dataInicio = (Date) session.getAttribute("clientDataInicio");
	// Date dataFim = (Date) session.getAttribute("clientDataFim");
	// Integer clientID = (Integer) session.getAttribute("clientID");
	// Double clientDesconto = (Double) session
	// .getAttribute("clientDesconto");
	// // new Date(new GregorianCalendar(2007,
	// // GregorianCalendar.AUGUST, 01).getTimeInMillis());
	// if (dataFim == null) {
	// GregorianCalendar dd = new GregorianCalendar();
	// dd.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
	// dataFim =new Date(dd.getTimeInMillis());
	// }
	// // par�metros, se houverem
	// Map parametros = new HashMap();
	// parametros.put("cliente_id", clientID.intValue());
	// parametros.put("data_inicio", dataInicio);
	// parametros.put("data_fim", dataFim);
	// parametros.put("porcentagem", clientDesconto.doubleValue());
	// // JasperPrint impressao = JasperFillManager.fillReport(
	// // caminhoRelatorioJasper,parametros,ds);
	// // // direciona a sa�da do relat�rio para um stream
	// //
	// System.out.println(context.getRealPath("/WEB-INF/reports/RelatorioRequisicao.jasper"));
	// // bytes =
	// //
	// JasperRunManager.runReportToPdf(context.getRealPath("/WEB-INF/reports/RelatorioRequisicao.jasper"),parametros,conn);
	// System.out
	// .println(context
	// .getRealPath("/WEB-INF/reports/RelatorioRequisicao.jasper"));
	// JasperRunManager.runReportToPdfStream(reportStream,
	// servletOutputStream, parametros, conn);
	//
	// // response.setContentType("application/pdf");
	// response.setContentType("application/octet-stream");
	// // servletOutputStream.flush();
	// // servletOutputStream.close();
	// response.setHeader("Pragma", "public");
	// response.setHeader("Cache-control", "must-revalidate");
	// // response.setHeader("Pragma", "");
	// // response.setHeader("Cache-Control", "");
	// ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
	// response.setHeader("Expires", "");
	// // response.setHeader("Content-Disposition",
	// "attachment inline; filename="
	// // + "test.pdf");
	// response.addHeader("content-disposition",
	// "inline;filename=relatorio.pdf");
	// response.setContentLength(arrayOutputStream.size());
	//
	// OutputStream out = response.getOutputStream();
	// arrayOutputStream.writeTo(out);
	// out.flush();
	// out.close();
	// //
	// // JasperPrint impressao = JasperFillManager
	// // .fillReport(
	// // context.getRealPath("/WEB-INF/reports/RelatorioRequisicao.jasper"),
	// // parametros, con);
	// // ByteArrayOutputStream arrayOutputStream = new
	// // ByteArrayOutputStream();
	// // JasperExportManager.exportReportToPdfStream(impressao,
	// // arrayOutputStream);
	// // response.setContentType("application/pdf");
	// // // testando
	// // response.setHeader("Pragma", "public");
	// // response.setHeader("Cache-control", "must-revalidate");
	// // // response.setHeader("Pragma", "");
	// // // response.setHeader("Cache-Control", "");
	// // response.setHeader("Expires", "");
	// // response.setHeader("Content-Disposition", "inline; filename="
	// // + "RelatorioRequisicao" + "." + "pdf");
	// // response.setContentLength(arrayOutputStream.size());
	// //
	// // OutputStream out = response.getOutputStream();
	// // arrayOutputStream.writeTo(out);
	// // out.flush();
	// // out.close();
	// // // if (bytes != null && bytes.length > 0) {
	// // // // envia o relat�rio em formato PDF para o browser
	// // // response.setContentType("application/pdf");
	// // //
	// // // response.setContentLength(bytes.length);
	// // // ServletOutputStream ouputStream = response.getOutputStream();
	// // // ouputStream.write(bytes, 0, bytes.length);
	// // // ouputStream.flush();
	// // // ouputStream.close();
	// // }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		System.out.println("\n\n\n\n\n\n\n"
				+ getServletContext().getRealPath(
						"/WEB-INF/reports/impressaoReqServ.html")
				+ "\n\n\n\n\n\n\n\n");
		String d = getServletContext().getRealPath(
				"/WEB-INF/reports/impressaoReqServ.html").substring(
				0,
				getServletContext().getRealPath(
						"/WEB-INF/reports/impressaoReqServ.html").indexOf(
						"impressaoReqServ.html"));
		System.out.println("\n\n\n\n\n\n\n"
				+d
				+ "\n\n\n\n\n\n\n\n");
		ReportImpressaoReqServGenerator rr = new ReportImpressaoReqServGenerator();
		Connection conexao = new ConnectionFactory().getConnection("seploc2",
				"root", "");
		rr.setConnection(conexao);
		rr.setNumRequisicao(109251);
		rr.geraDados();
		// rr.imprimeDados();
		out.print(rr.imprimeDadosWeb2(d)); 
		try {
			out.flush();
//			out.close();
			if (!conexao.isClosed()) {
				conexao.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Override
//	protected void service(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//		// super.service(arg0, arg1);
//	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Integer operacao = Integer.parseInt(request.getParameter("operacao"));
		switch (operacao) {
		case 1:
			geraPdf(request, response);
			break;
		case 2:
			// geraHtml(request, response);
			break;
		default:
			break;
		}
	}

	// Cria a conexao
	private void conexao() {
		try {
			if (con == null || con.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/" + banco, usuario, senha);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void geraPdf(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		conexao();
		byte[] bytes = null;
		String pathJasper = getServletContext().getRealPath(
				"/reports/WEB-INF/reports/")
				+ "/";

		String path = getServletContext().getRealPath("/");

		Map parametros = new HashMap();
		System.out.println(pathJasper + "RelatioRequisicao.jasper");
		// DOMConfigurator.configure(pathJasper + "/log4j.xml");
		Date dataInicio = new Date(new GregorianCalendar(2007,
				GregorianCalendar.AUGUST, 01).getTimeInMillis());
		Date dataFim = new Date(new GregorianCalendar(2010,
				GregorianCalendar.AUGUST, 01).getTimeInMillis());

		parametros.put("cliente_id", 35);
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		parametros.put("porcentagem", new Double(10.0));
		try {

			JasperPrint impressao = JasperFillManager.fillReport(pathJasper
					+ "RelatioRequisicao.jasper", parametros, con);
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(impressao,
					arrayOutputStream);
			String reportName = "RelatioRequisicao", reportType = "pdf";

			response.setContentType("application/pdf");
			// testando
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-control", "must-revalidate");
			// response.setHeader("Pragma", "");
			// response.setHeader("Cache-Control", "");
			response.setHeader("Expires", "");
			response.setHeader("Content-Disposition", "inline; filename="
					+ reportName.toUpperCase() + "." + reportType);
			response.setContentLength(arrayOutputStream.size());

			OutputStream out = response.getOutputStream();
			arrayOutputStream.writeTo(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			response.getWriter().println("Erro ao gerar o relatrio: " + e);
		}
	}
}
