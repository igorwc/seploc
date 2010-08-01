package br.seploc.mbeans;

import java.util.List;

import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.pojos.OpcionaisReqServ;


public class OpcionalBean {

	private OpcionaisReqServ opcional;
	private OpcionaisReqServDAO opcionalDAO;
	
	
	public OpcionalBean() {	
		opcional = new OpcionaisReqServ();
		opcionalDAO = new OpcionaisReqServDAO();
	}
	public OpcionaisReqServ getOpcional() {
		return opcional;
	}
	public void setOpcional (OpcionaisReqServ op) {
		this.opcional = op;
	}
	public OpcionaisReqServDAO getOpcionalDAO() {
		return opcionalDAO;
	}
	public void setOpcionalDAO(OpcionaisReqServDAO opcionalDAO) {
		this.opcionalDAO = opcionalDAO;
	}
	
	public void cadastra(){
		try {
		opcionalDAO.adiciona(opcional);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public List<OpcionaisReqServ> getLista() {
		return opcionalDAO.getLista();
	}
	
}
