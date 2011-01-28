package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.seploc.migracao.beans.Usuario;

public class MigraUsuario extends Migra<Usuario> {
	private static MigraUsuario obj;
	private static String[] bancoOrigem;
	private static String[] bancoDestino;

	private MigraUsuario() {
	}

	public static MigraUsuario getInstance(Connection copytec, Connection seploc)
			throws Exception {
		if (obj == null) {
			obj = new MigraUsuario();
		}
		if (copytec == null || seploc == null) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	public static MigraUsuario getInstance(String[] bancoOrigem,
			String[] bancoDestino) throws Exception {
		if (obj == null) {
			obj = new MigraUsuario();
		}
		if (bancoDestino.length != 3 || bancoOrigem.length != 3) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		MigraUsuario.bancoDestino = bancoDestino;
		MigraUsuario.bancoOrigem = bancoOrigem;
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
					.prepareStatement("SELECT count(*) FROM tbl_usuario");
			PreparedStatement stmtseploc = seplocConnection
					.prepareStatement("SELECT count(*) FROM tbl_usuario");
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
			saida += "REGISTROS COPYTEC TBL_USUARIO: " + regCopytec + "\n"
					+ "REGISTROS SEPLOC TBL_USUARIO: " + regSeploc + "\n"
					+ "-----------------------------\n"
					+ "DIFERENCA REGISTROS TBL_USUARIO: "
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
		String sql = "INSERT INTO tbl_usuario (intCodUsr, vcrLogin, vcrPassword, vcrCpf, intPermissao, vcrIpMaquina, intGrupo, vcrNome, tspVersao)VALUES (? , ? ,? , ? ,? , ? ,? , ? ,CURRENT_TIMESTAMP)";
		registrosInseridos = 0;
		int id = 1;
		for (Usuario u : lista) {
			PreparedStatement stmt = seplocConnection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setString(2, u.getVcrLogin());
			if (u.getVcrPassword() == null) {
				stmt.setNull(3, java.sql.Types.VARCHAR);
			} else {
				stmt.setString(3, u.getVcrPassword());
			}
			stmt.setString(4, u.getVcrCpf());
			stmt.setInt(5, u.getIntPermissao());
			if (u.getVcrIpMaquina() == null) {
				stmt.setNull(6, java.sql.Types.VARCHAR);
			} else {
				stmt.setString(6, u.getVcrIpMaquina());
			}
			if (u.getIntGrupo() == null) {
				stmt.setNull(7, java.sql.Types.INTEGER);
			} else {
				stmt.setInt(7, u.getIntGrupo());
			}
			if (u.getVcrNome() == null) {
				stmt.setNull(8, java.sql.Types.VARCHAR);
			} else {
				stmt.setString(8, u.getVcrNome());
			}
			stmt.execute();
			id++;
			registrosInseridos++;
			stmt.close();
		}

	}

	@Override
	protected void seleciona() throws Exception {
		String sql = "SELECT  vcrLogin, vcrPassword, vcrCpf, intPermissao, vcrIpMaquina, intGrupoAcesso, vcrNome FROM tbl_usuario";
		lista = new ArrayList<Usuario>();
		// pega o Statement
		PreparedStatement stmt = copytecConnection.prepareStatement(sql);
		// System.out.println(sql);
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {   
			Usuario u = new Usuario( rs.getString("vcrLogin"), rs.getString("vcrPassword"),rs.getString("vcrCpf"),rs.getInt("intPermissao"), rs.getString("vcrIpMaquina"),rs.getInt("intGrupoAcesso"), rs.getString("vcrNome"));
			lista.add(u);
		}
		stmt.close();

	}
	public static void main(String args[]) {
		String[] origem = { "dbcopytec", "root", "" };
		String[] destino = { "seploc2", "root", "" };

		try {
			MigraUsuario migra = MigraUsuario.getInstance(origem, destino);
			migra.migraDados();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
}
