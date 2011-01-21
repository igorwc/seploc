package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.seploc.migracao.beans.LinhaRequisicao;

public class MigraLinhaReq extends Migra {

	List<LinhaRequisicao> lista;
	Integer idLinha = 1;

	private MigraLinhaReq() {
		lista = new ArrayList<LinhaRequisicao>();
	}

	public static MigraLinhaReq getInstance(Connection copytec,
			Connection seploc) {
		MigraLinhaReq obj = new MigraLinhaReq();
		obj.setConexoes(copytec, seploc);
		return obj;
	}

	@Override
	protected void insereDados() throws Exception {
		String sql = "INSERT INTO  "
				+ "tbl_linhareq (intNumreq, intNumLin, vcrNomeArq, dblFormato, dblDimensao, "
				+ "intCodPap, vcrImpressao, intQuant, dblValorUnit, dblValorSubUnit, tspVersao) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP);";
		for (LinhaRequisicao lr : lista) {
			try {
				PreparedStatement stmt = seplocConnection.prepareStatement(sql);
				stmt.setInt(1, lr.getIntNumreq());
				stmt.setInt(2, lr.getIntNumLin());
				stmt.setString(3, lr.getVcrNomeArq());
				if (lr.getDblFormato() == null) {
					stmt.setNull(4, java.sql.Types.DOUBLE);
				} else {
					stmt.setDouble(4, lr.getDblFormato());
				}
				if (lr.getDblDimensao() == null) {
					stmt.setNull(5, java.sql.Types.DOUBLE);
				} else {
					stmt.setDouble(5, lr.getDblDimensao());
				}
				stmt.setInt(6, lr.getIntCodPap());
				if (lr.getVcrImpressao() == null) {
					stmt.setNull(7, java.sql.Types.VARCHAR);
				} else {
					stmt.setString(7, lr.getVcrImpressao());
				}
				if (lr.getIntQuant() == null) {
					stmt.setNull(8, java.sql.Types.INTEGER);
				} else {
					stmt.setInt(8, lr.getIntQuant());
				}
				if (lr.getDblValorUnit() == null) {
					stmt.setNull(9, java.sql.Types.DOUBLE);
				} else {
					stmt.setDouble(9, lr.getDblValorUnit());
				}
				if (lr.getDblValorSubUnit() == null) {
					stmt.setNull(10, java.sql.Types.DOUBLE);
				} else {
					stmt.setDouble(10, lr.getDblValorSubUnit());
				}
				stmt.execute();
				stmt.close();
			} catch (Exception e) {
				System.out.println("Nao foi possível inserir o registro:");
				System.out.println(lr);
				System.out.println("Erro: "+ e.getMessage());
			}
		}
	}

	@Override
	protected void seleciona() throws Exception {
		lista = new ArrayList<LinhaRequisicao>();
		// pega o Statement
		PreparedStatement stmt = copytecConnection
				.prepareStatement("SELECT intNumreq, intNumLin, vcrNomeArq, dblFormato, dblDimensao,"
						+ " intCodPap, vcrImpressao, intQuant, dblValorUnit, dblValorSubUnit "
						+ "FROM tbl_linhareq "
						+ "where intNumReq in (select intNumReq from seplocteste.tbl_reqserv) "
						+ "order by intNumReq asc");
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {
			LinhaRequisicao lr = new LinhaRequisicao();
			lr.setIntNumreq(rs.getInt("intNumReq"));
			lr.setIntNumLin(rs.getInt("intNumLin"));
			lr.setVcrNomeArq(rs.getString("vcrNomeArq"));
			lr.setDblFormato(rs.getDouble("dblFormato"));
			lr.setDblDimensao(rs.getDouble("dblDimensao"));
			lr.setIntCodPap(rs.getInt("intCodPap"));
			lr.setVcrImpressao(rs.getString("vcrImpressao"));
			lr.setIntQuant(rs.getInt("intQuant"));
			lr.setDblValorUnit(rs.getDouble("dblValorUnit"));
			lr.setDblValorSubUnit(rs.getDouble("dblValorSubUnit"));
			lista.add(lr);
		}
		stmt.close();
		stmt = copytecConnection
				.prepareStatement("SELECT  count(intNumReq ) "
						+ "FROM tbl_linhareq "
						+ "where intNumReq not in (select intNumReq from seplocteste.tbl_reqserv) ");
		rs = stmt.executeQuery();
		if (rs.next()) {
			int regs = rs.getInt(1);
			if (regs != 0) {
				stmt.close();
				System.out.println("Registros que nao serao migrados (" + regs + "): ");
				stmt = copytecConnection
						.prepareStatement("SELECT intNumreq, intNumLin, vcrNomeArq, dblFormato, dblDimensao, "
								+ "intCodPap, vcrImpressao, intQuant, dblValorUnit, dblValorSubUnit "
								+ "FROM tbl_linhareq "
								+ "where intNumReq not in (select intNumReq from seplocteste.tbl_reqserv) "
								+ "order by intNumReq asc");
				rs = stmt.executeQuery();
				while (rs.next()) {
					LinhaRequisicao lr = new LinhaRequisicao();
					lr.setIntNumreq(rs.getInt("intNumReq"));
					lr.setIntNumLin(rs.getInt("intNumLin"));
					lr.setVcrNomeArq(rs.getString("vcrNomeArq"));
					lr.setDblFormato(rs.getDouble("dblFormato"));
					lr.setDblDimensao(rs.getDouble("dblDimensao"));
					lr.setIntCodPap(rs.getInt("intCodPap"));
					lr.setVcrImpressao(rs.getString("vcrImpressao"));
					lr.setIntQuant(rs.getInt("intQuant"));
					lr.setDblValorUnit(rs.getDouble("dblValorUnit"));
					lr.setDblValorSubUnit(rs.getDouble("dblValorSubUnit"));
					System.out.println(lr);
				}
			}
		}
		stmt.close();
	}

	public static void main(String args[]) {
		MigraLinhaReq migra = MigraLinhaReq.getInstance(new ConnectionFactory()
				.getConnection("dbcopytec", "root", ""),
				new ConnectionFactory().getConnection("seplocteste", "root", ""));
		try {
			migra.migraDados();

			System.out.println("terminou");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void cqMigracao() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
