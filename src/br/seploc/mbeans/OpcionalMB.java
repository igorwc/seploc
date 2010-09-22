package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.pojos.OpcionaisReqServ;

public class OpcionalMB implements Serializable {

	private static final long serialVersionUID = 1L;
	static int quantidade = 0;
	private OpcionaisReqServ opcional;
	private OpcionaisReqServDAO opcionalDAO;
	
	//CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public OpcionalMB(){
		init();
	}
	
	/**
	 * Metodo de inicialização
	 */
	public void init(){
		quantidade++;
		opcional = new OpcionaisReqServ();
		opcionalDAO = new OpcionaisReqServDAO();
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
				addGlobalMessage("Inclusão feita com sucesso!");
			} else {
				OpcionaisReqServ temp;
				temp = opcionalDAO.recupera(opcional.getCodOpReqServ());
				if (temp != null) {
					temp.setCodOpReqServ(opcional.getCodOpReqServ());
					temp.setNomeItem(opcional.getNomeItem().trim());
					temp.setValorItem(opcional.getValorItem());
					opcionalDAO.altera(temp);
					addGlobalMessage("Atualização feita com sucesso!");
				}
			}
		} catch (Exception e) {
			addGlobalMessage("Não foi possível realizar a operação!");
		}
		this.limpar();

	}

	/**
	 * Excluir opcional
	 */
	public void apagar() {
		try {
			opcionalDAO.remove(opcional.getCodOpReqServ());
			addGlobalMessage("Opcional excluído com sucesso!");
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
	 * Método para incluir mensagens globais no formulário de cadastro
	 * 
	 * @param String
	 *            message
	 */
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
}
