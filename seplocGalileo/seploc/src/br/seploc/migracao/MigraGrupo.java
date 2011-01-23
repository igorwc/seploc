package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.seploc.migracao.beans.Grupo;

public class MigraGrupo extends Migra<Grupo> {
	private static MigraGrupo obj;
	private static String[] bancoOrigem;
	private static String[] bancoDestino;

	private MigraGrupo() {
	}

	public static MigraGrupo getInstance(Connection copytec, Connection seploc)
			throws Exception {
		if (obj == null) {
			obj = new MigraGrupo();
		}
		if (copytec == null || seploc == null) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	public static MigraGrupo getInstance(String[] bancoOrigem,
			String[] bancoDestino) throws Exception {
		if (obj == null) {
			obj = new MigraGrupo();
		}
		if (bancoDestino.length != 3 || bancoOrigem.length != 3) {
			throw new Exception("Não foi possível criar a instancia do objeto");
		}
		MigraGrupo.bancoDestino = bancoDestino;
		MigraGrupo.bancoOrigem = bancoOrigem;
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
					.prepareStatement("SELECT count(*) FROM tbl_grupoacesso");
			PreparedStatement stmtseploc = seplocConnection
					.prepareStatement("SELECT count(*) FROM tbl_grupo");
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
			saida += "REGISTROS COPYTEC TBL_GRUPO: " + regCopytec + "\n"
					+ "REGISTROS SEPLOC TBL_GRUPO: " + regSeploc + "\n"
					+ "-----------------------------\n"
					+ "DIFERENCA REGISTROS TBL_GRUPO: "
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
		String sql = "INSERT INTO tbl_grupo (intGrupo, vcrGrupo, tspVersao)VALUES (? , ? ,CURRENT_TIMESTAMP)";
		registrosInseridos = 0;
		for (Grupo g : lista) {
			PreparedStatement stmt = seplocConnection.prepareStatement(sql);
			stmt.setInt(1, g.getIntGrupo());
			if (g.getVcrGrupo() == null) {
				stmt.setNull(2, java.sql.Types.VARCHAR);
			} else {
				stmt.setString(2, g.getVcrGrupo());
			}
			stmt.execute();
			registrosInseridos++;
			stmt.close();
		}

	}

	@Override
	protected void seleciona() throws Exception {
		String sql = "SELECT  intGrupoAcesso, vcrGrupoMenu FROM tbl_grupoacesso  ";
		lista = new ArrayList<Grupo>();
		// pega o Statement
		PreparedStatement stmt = copytecConnection.prepareStatement(sql);
		// System.out.println(sql);
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {
			Grupo g = new Grupo(rs.getInt("intGrupoAcesso"), rs.getString("vcrGrupoMenu"));
			lista.add(g);
		}
		stmt.close();

	}

	public static void main(String args[]) {
		String[] origem = { "dbcopytec", "root", "" };
		String[] destino = { "seplocteste", "root", "" };

		try {
			MigraGrupo migra = MigraGrupo.getInstance(origem, destino);
			migra.migraDados();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
}
