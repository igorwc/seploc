package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.pojos.OpcionaisReqServ;

public class OpcionalMB implements Serializable {

	private static final long serialVersionUID = 1L;
	static int quantidade = 0;
	private OpcionaisReqServ opcional;
	private OpcionaisReqServDAO opcionalDAO;
	private String filtroOpcional;
	
	//CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public OpcionalMB(){
		init();
	}
	
	/**
	 * Metodo de inicializa��o
	 */
	public void init(){
		quantidade++;
		opcional = new OpcionaisReqServ();
		opcionalDAO = new OpcionaisReqServDAO();
	}

	public void setFiltroOpcional(String filtroOpcional) {
		this.filtroOpcional = filtroOpcional;
	}

	public String getFiltroOpcional() {
		return filtroOpcional;
	}

	// SETTERS AND GETTERS
	/**
	 * Recuperar Opcional
	 */
	public OpcionaisReqServ getOpcional() {
		return opcional;
	}

	/**
	 * Atribuir Opcional
	 * @param opcional
	 */
	public void setOpcional(OpcionaisReqServ opcional) {
		this.opcional = opcional;
	}
	
	/**
	 * ecuperar Opcional
	 * @return OpcionaisReqServDAO 
	 */
	public OpcionaisReqServDAO getOpcionalDAO() {
		return opcionalDAO;
	}

	/**
	 * Atribuir OpcionaisReqServDAO
	 * @param opcionalDAO
	 */
	public void setOpcionalDAO(OpcionaisReqServDAO opcionalDAO) {
		this.opcionalDAO = opcionalDAO;
	}	
	
	/**
	 * Recuperar Lista de Opcionais
	 * @return List<OpcionaisReqServ>
	 */
	public List<OpcionaisReqServ> getLista(){
		return opcionalDAO.getLista();
	}
	
	// METODOS
	/**
	 * Cadastrar o Opcional
	 */
	public void cadastrar() {
		try {
			if (opcional.getCodOpReqServ() == null	|| opcional.getCodOpReqServ() == 0) {
				opcionalDAO.adiciona(opcional);
				addGlobalMessage("Inclus�o feita com sucesso!");
			} else {
				OpcionaisReqServ temp;
				temp = opcionalDAO.recupera(opcional.getCodOpReqServ());
				if (temp != null) {
					temp.setCodOpReqServ(opcional.getCodOpReqServ());
					temp.setNomeItem(opcional.getNomeItem().trim());
					temp.setValorItem(opcional.getValorItem());
					opcionalDAO.altera(temp);
					addGlobalMessage("Atualiza��o feita com sucesso!");
				}
			}
		} catch (Exception e) {
			addGlobalMessage("N�o foi poss�vel realizar a opera��o!");
		}
		this.limpar();

	}

	/**
	 * Excluir opcional
	 */
	public void apagar() {
		try {
			opcionalDAO.remove(opcional.getCodOpReqServ());
			addGlobalMessage("Opcional exclu�do com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		this.limpar();
	}

	/**
	 * Editar o opcional
	 */
	public void editar() {
		try {			
			opcional = opcionalDAO.recupera(opcional.getCodOpReqServ());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Limpar os OpcionaisReqServ
	 */
	public void limpar() {
		opcional = new OpcionaisReqServ();
	}	

	
	/**
	 * M�todo para incluir mensagens globais no formul�rio de cadastro
	 * 
	 * @param String
	 *            message
	 */
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
}
