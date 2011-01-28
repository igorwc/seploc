package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.seploc.migracao.beans.Opcional;
import br.seploc.migracao.beans.OpcionalReqServ;

public class MigraOpcionaisReqServ {
	
	private List<Opcional> listaOpcionais ;
	private Connection seplocConnection;
	private Connection copytecConnection;
	
	public MigraOpcionaisReqServ(Connection c, Connection s) {
		seplocConnection = s;
		copytecConnection = c;
		listaOpcionais = new ArrayList<Opcional>();
		loadListaOpcionais();
	}

	public void fechaConexoes() {
		try {
			seplocConnection.close();
			copytecConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadListaOpcionais(){
		int cont = 1;
		List<Opcional> retorno = new ArrayList<Opcional>();
		// pega o Statement
		try{
		PreparedStatement stmt = copytecConnection
				.prepareStatement("SELECT vcrCod,vcrNomeItem,dblValorItem " +
								  "FROM tbl_opcionaisreqserv");
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {
			Opcional op = new Opcional();
			op.setId(cont++);
			op.setVcrCod(rs.getString("vcrCod"));
			op.setVcrNomeItem(rs.getString("vcrNomeItem"));
			op.setDblValorItem(rs.getDouble("dblValorItem"));
			listaOpcionais.add(op);
		}
		stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Integer convertOpcionalCopytecSeploc(String mnemonico){
		Integer retorno = 0;
		
		for(Opcional op : listaOpcionais){
			if(op.getVcrCod().equals(mnemonico)){
				retorno = op.getId();
				break;
			}
		}
		
		return retorno;
		
	}
	public List<OpcionalReqServ> seleciona() throws Exception {
		int cont = 1;
		List<OpcionalReqServ> retorno = new ArrayList<OpcionalReqServ>();
		// pega o Statement
		PreparedStatement stmt = copytecConnection
				.prepareStatement("SELECT  intNumReq, intCodOp, vcrCod, intQuant" +
								  " FROM tbl_reqservopcionais " +
								  "where intNumReq in (select intNumReq from seploc2.tbl_reqserv) " +
								  "order by intNumReq,intCodOp asc");
		// executa um select
		ResultSet rs = stmt.executeQuery();
		// itera no ResultSet
		while (rs.next()) {
			OpcionalReqServ op = new OpcionalReqServ();
			op.setMnemonicoOp(rs.getString("vcrCod"));
			op.setNumReq(rs.getInt("intNumReq"));
			op.setQtd(rs.getInt("intQuant"));
			op.setIdOP(convertOpcionalCopytecSeploc(op.getMnemonicoOp()));
			retorno.add(op);
		}
		stmt.close();
		stmt = copytecConnection
		.prepareStatement("SELECT  count(intNumReq )" +
						  " FROM tbl_reqservopcionais " +
						  "where intNumReq not in (select intNumReq from tbl_reqserv) " +
						  "order by intNumReq,intCodOp asc");
		  rs = stmt.executeQuery();
		if(rs.next()){
			int regs = rs.getInt(1);
		  if( regs != 0){
			  stmt.close();
			  System.out.println("Registros Problematicos (" + regs+"): ");
			  stmt = copytecConnection
				.prepareStatement("SELECT  intNumReq, intCodOp, vcrCod, intQuant " +
								  " FROM tbl_reqservopcionais " +
								  "where intNumReq not in (select intNumReq from tbl_reqserv) " +
								  "order by intNumReq,intCodOp asc");
				  rs = stmt.executeQuery();
//				  while(rs.next()){
//					  System.out.println("intNumReq: " + rs.getInt("intNumReq")
//								+" Mnemonico: "+ rs.getString("vcrCod")+" quantidade: "+rs.getInt("intQuant"));
//				  }
			  
		  }
		}
		return retorno;
	}
	
	public void insereDados(List<OpcionalReqServ> lista)  {
		String sql = "INSERT INTO  tbl_reqservopcionais (intNumReq, intCodOp, intQuant, tspVersao) VALUES (?, ?, ?, CURRENT_TIMESTAMP);";
		OpcionalReqServ controle = null;
		try{
		for (OpcionalReqServ op : lista) {
			controle = op;
			if(convertOpcionalCopytecSeploc(op.getMnemonicoOp()) == null 
					|| convertOpcionalCopytecSeploc(op.getMnemonicoOp()) == 0){
				System.out.println("Registro não inserido: intNumReq: " + op.getNumReq()
						+" Mnemonico: "+ op.getMnemonicoOp()+" quantidade: "+op.getQtd());
				continue;
			}
			PreparedStatement stmt = seplocConnection.prepareStatement(sql);
			stmt.setInt(1, op.getNumReq());
			stmt.setInt(2,  convertOpcionalCopytecSeploc(op.getMnemonicoOp()));
			stmt.setInt(3, op.getQtd());
			stmt.execute();
			stmt.close();
		}
		}catch (Exception e) {
           e.printStackTrace();
           System.out.println("Deu Erro nesse objeto: " + controle);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
				MigraOpcionaisReqServ migra = new MigraOpcionaisReqServ(new ConnectionFactory().getConnection("dbcopytec",
					"root", ""),new ConnectionFactory().getConnection("seploc2", "root", ""));
			List<OpcionalReqServ> lista = migra.seleciona();
//			for (OpcionalReqServ op : lista) {
//				System.out.println(op);
//			}
			  
			migra.insereDados(lista);
			// c.commit();
			migra.fechaConexoes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public List<Opcional> getListaOpcionais() {
		return listaOpcionais;
	}

	public void setListaOpcionais(List<Opcional> listaOpcionais) {
		this.listaOpcionais = listaOpcionais;
	}
	public Connection getSeplocConnection() {
		return seplocConnection;
	}
	public void setSeplocConnection(Connection seplocConnection) {
		this.seplocConnection = seplocConnection;
	}
	public Connection getCopytecConnection() {
		return copytecConnection;
	}
	public void setCopytecConnection(Connection copytecConnection) {
		this.copytecConnection = copytecConnection;
	}
}

