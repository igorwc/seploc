package br.seploc.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
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
import javax.servlet.http.HttpSession;

import br.seploc.util.SessionObjectsManager;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// obtém a conexão com o banco de dados
		ServletOutputStream servletOutputStream = response.getOutputStream();
		ServletContext context = getServletContext();
		InputStream reportStream = getServletConfig().getServletContext()
				.getResourceAsStream(
						"/WEB-INF/reports/RelatorioRequisicao.jasper");

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

		// gera o relatório
		byte[] bytes = null;
		try {

			// carrega os arquivos jasper
			// JasperReport relatorioJasper = (JasperReport)JRLoader.loadObject(
			// context.getRealPath("/WEB-INF/reports/RelatioRequisicao.jasper"));
			HttpSession session = request.getSession();
			Date dataInicio = (Date) session.getAttribute("clientDataInicio");
			Date dataFim = (Date) session.getAttribute("clientDataFim");
			Integer clientID = (Integer) session.getAttribute("clientID");
			Double clientDesconto = (Double) session
					.getAttribute("clientDesconto");
			// new Date(new GregorianCalendar(2007,
			// GregorianCalendar.AUGUST, 01).getTimeInMillis());
			if (dataFim == null) {
				GregorianCalendar dd = new GregorianCalendar();
				dd.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
				dataFim =new Date(dd.getTimeInMillis());
			}
			// parâmetros, se houverem
			Map parametros = new HashMap();
			parametros.put("cliente_id", clientID.intValue());
			parametros.put("data_inicio", dataInicio);
			parametros.put("data_fim", dataFim);
			parametros.put("porcentagem", clientDesconto.doubleValue());
			// JasperPrint impressao = JasperFillManager.fillReport(
			// caminhoRelatorioJasper,parametros,ds);
			// // direciona a saída do relatório para um stream
			// System.out.println(context.getRealPath("/WEB-INF/reports/RelatorioRequisicao.jasper"));
			// bytes =
			// JasperRunManager.runReportToPdf(context.getRealPath("/WEB-INF/reports/RelatorioRequisicao.jasper"),parametros,conn);
			System.out
					.println(context
							.getRealPath("/WEB-INF/reports/RelatorioRequisicao.jasper"));
			JasperRunManager.runReportToPdfStream(reportStream,
					servletOutputStream, parametros, conn);

//			 response.setContentType("application/pdf");
			 response.setContentType("application/octet-stream");
			// servletOutputStream.flush();
			// servletOutputStream.close();
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-control", "must-revalidate");
			// response.setHeader("Pragma", "");
			// response.setHeader("Cache-Control", "");
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			response.setHeader("Expires", "");
//			response.setHeader("Content-Disposition", "attachment inline; filename="
//					+ "test.pdf");
			response.addHeader("content-disposition", "inline;filename=relatorio.pdf");
			response.setContentLength(arrayOutputStream.size());

			OutputStream out = response.getOutputStream();
			arrayOutputStream.writeTo(out);
			out.flush();
			out.close();
			//
			// JasperPrint impressao = JasperFillManager
			// .fillReport(
			// context.getRealPath("/WEB-INF/reports/RelatorioRequisicao.jasper"),
			// parametros, con);
			// ByteArrayOutputStream arrayOutputStream = new
			// ByteArrayOutputStream();
			// JasperExportManager.exportReportToPdfStream(impressao,
			// arrayOutputStream);
			// response.setContentType("application/pdf");
			// // testando
			// response.setHeader("Pragma", "public");
			// response.setHeader("Cache-control", "must-revalidate");
			// // response.setHeader("Pragma", "");
			// // response.setHeader("Cache-Control", "");
			// response.setHeader("Expires", "");
			// response.setHeader("Content-Disposition", "inline; filename="
			// + "RelatorioRequisicao" + "." + "pdf");
			// response.setContentLength(arrayOutputStream.size());
			//
			// OutputStream out = response.getOutputStream();
			// arrayOutputStream.writeTo(out);
			// out.flush();
			// out.close();
			// // if (bytes != null && bytes.length > 0) {
			// // // envia o relatório em formato PDF para o browser
			// // response.setContentType("application/pdf");
			// //
			// // response.setContentLength(bytes.length);
			// // ServletOutputStream ouputStream = response.getOutputStream();
			// // ouputStream.write(bytes, 0, bytes.length);
			// // ouputStream.flush();
			// // ouputStream.close();
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// geraPdf(request, response);
	}

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
