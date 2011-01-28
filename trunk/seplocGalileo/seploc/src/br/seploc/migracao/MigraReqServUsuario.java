package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.seploc.migracao.beans.Cobrador;
import br.seploc.migracao.beans.ReqServUsuario;
import br.seploc.migracao.beans.Usuario;

public class MigraReqServUsuario extends Migra<ReqServUsuario> {
	private static MigraReqServUsuario obj;
	private static String[] bancoOrigem;
	private static String[] bancoDestino;
	private Map<String, Integer> conversaoLogin;

	private MigraReqServUsuario() {
		
	}

	private void povoaUsuarios() throws Exception {
		String sql = "SELECT  intCodUsr, vcrLogin FROM tbl_usuario";
		conversaoLogin = new HashMap<String, Integer>();
		// pega o Statement
		PreparedStatement stmt = seplocConnection.prepareStatement(sql);
		// System.out.println(sql);
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {   
			conversaoLogin.put(rs.getString("vcrLogin"), rs.getInt("intCodUsr"));
		}
		stmt.close();

	}
	public static MigraReqServUsuario getInstance(Connection copytec,
			Connection seploc) throws Exception {
		if (obj == null) {
			obj = new MigraReqServUsuario();
		}
		if (copytec == null || seploc == null) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	public static MigraReqServUsuario getInstance(String[] bancoOrigem,
			String[] bancoDestino) throws Exception {
		if (obj == null) {
			obj = new MigraReqServUsuario();
		}
		if (bancoDestino.length != 3 || bancoOrigem.length != 3) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		MigraReqServUsuario.bancoDestino = bancoDestino;
		MigraReqServUsuario.bancoOrigem = bancoOrigem;
		Connection copytec = new ConnectionFactory().getConnection(
				bancoOrigem[0], bancoOrigem[1], bancoOrigem[2]);
		Connection seploc = new ConnectionFactory().getConnection(
				bancoDestino[0], bancoDestino[1], bancoDestino[2]);
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	@Override
	protected void cqMigracao() throws Exception {
		int regSeploc = 0, regCopytec = 0, diferenca = 0;
		try {
			PreparedStatement stmtCopytec = copytecConnection
					.prepareStatement("SELECT count(*) FROM tbl_reqservusuario");
			PreparedStatement stmtseploc = seplocConnection
					.prepareStatement("SELECT count(*) FROM tbl_reqservusuario");
			ResultSet rsCopytec = stmtCopytec.executeQuery();
			ResultSet rsSeploc = stmtseploc.executeQuery();
			if (rsCopytec.next()) {
				regCopytec = rsCopytec.getInt(1);
			}
			if (rsSeploc.next()) {
				regSeploc = rsSeploc.getInt(1);
			}
			rsCopytec.close();
			rsSeploc.close();
			stmtCopytec.close();
			stmtseploc.close();
			String saida = "";
			saida += "REGISTROS COPYTEC TBL_REQSERVUSUARIO: " + regCopytec + "\n"
					+ "REGISTROS SEPLOC TBL_REQSERVUSUARIO: " + regSeploc + "\n"
					+ "-----------------------------\n"
					+ "DIFERENCA REGISTROS TBL_REQSERVUSUARIO: "
					+ (regCopytec - regSeploc) + "\n" + "REGISTROS INSERIDOS: "
					+ registrosInseridos;
			System.out.println(saida);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}		
	}

	@Override
	protected void insereDados() throws Exception {
		String sql = "INSERT INTO tbl_reqservusuario (intCodUsr, intNumReq, datData, intCodUsrAlter, datdataAlter, tspVersao) " +
				     "VALUES (? , ?, ?, ?, ? ,CURRENT_TIMESTAMP)";
		registrosInseridos = 0;
		ReqServUsuario ss = null;
		try {
			
		
		for (ReqServUsuario c : lista) {
			ss = c;
			PreparedStatement stmt = seplocConnection.prepareStatement(sql);
			stmt.setInt(1, c.getIntCodUsr());
			stmt.setInt(2, c.getIntNumReq());
			if (c.getDatData() == null) {
				stmt.setNull(3, java.sql.Types.DATE);
			} else {
				stmt.setDate(3, c.getDatData());
			}
			if (c.getIntCodUsrAlter() == null) {
				stmt.setNull(4, java.sql.Types.INTEGER);
			} else {
				stmt.setInt(4, c.getIntCodUsrAlter());
			}
			if (c.getDatdataAlter() == null) {
				stmt.setNull(5, java.sql.Types.DATE);
			} else {
				stmt.setDate(5, c.getDatdataAlter());
			}
			stmt.execute();
			registrosInseridos++;
			stmt.close();
		}
		} catch (Exception e) {
			 System.out.println("Deu pau aqui: " + ss +"\n");
		}

		
	}

	@Override
	protected void seleciona() throws Exception {
		try {
			povoaUsuarios();
			System.out.println(conversaoLogin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT  vcrLogin, intNumReq, datData, vcrLoginAlter, datdataAlter " +
				     "FROM tbl_reqservusuario " +
				     "WHERE intNumReq in (select intNumReq from "+ bancoDestino[0] + ".tbl_reqserv) " +
				     		"order by intNumReq";
		lista = new ArrayList<ReqServUsuario>();
		// pega o Statement
		PreparedStatement stmt = copytecConnection
				.prepareStatement(sql);
//		System.out.println(sql);
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {
			ReqServUsuario c = new ReqServUsuario(conversaoLogin.get(rs.getString("vcrLogin")), rs.getInt("intNumReq"), 
					rs.getDate("datData"),conversaoLogin.get(rs.getString("vcrLoginAlter")),
					rs.getDate("datdataAlter"));
			lista.add(c);
		}
		stmt.close();
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] origem = { "dbcopytec", "root", "" };
		String[] destino = { "seploc2", "root", "" };

		try {
			MigraReqServUsuario migra = MigraReqServUsuario.getInstance(origem, destino);
			migra.migraDados();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

	}
}
