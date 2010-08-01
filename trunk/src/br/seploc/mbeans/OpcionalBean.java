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

	public void setOpcional(OpcionaisReqServ op) {
		this.opcional = op;
	}

	public OpcionaisReqServDAO getOpcionalDAO() {
		return opcionalDAO;
	}

	public void setOpcionalDAO(OpcionaisReqServDAO opcionalDAO) {
		this.opcionalDAO = opcionalDAO;
	}

	public void cadastra() {
		try {
			try {
				if (opcional.getCodOpReqServ() == null
						|| opcional.getCodOpReqServ() == 0)
					opcionalDAO.adiciona(opcional);
				else {
					OpcionaisReqServ temp;
					temp = opcionalDAO.recupera(opcional.getCodOpReqServ());
					if (temp != null) {
						temp.setCodOpReqServ(opcional.getCodOpReqServ());
						temp.setNomeItem(opcional.getNomeItem());
						temp.setValorItem(opcional.getValorItem());
						opcionalDAO.altera(temp);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			opcional = new OpcionaisReqServ();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void apagar() {
		try {
			opcionalDAO.remove(opcional.getCodOpReqServ());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		opcional = new OpcionaisReqServ();
	}

	public void editar() {
		try {
			opcional = opcionalDAO.recupera(opcional.getCodOpReqServ());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void limpar() {
		opcional = new OpcionaisReqServ();
	}

	public List<OpcionaisReqServ> getLista() {
		return opcionalDAO.getLista();
	}

}
