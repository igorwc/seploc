package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.pojos.OpcionaisReqServ;
import br.seploc.util.Utils;

public class OpcionalMB implements Serializable {

	private static final long serialVersionUID = 1L;
	static int quantidade = 0;
	private OpcionaisReqServ opcional;
	private OpcionaisReqServDAO opcionalDAO;
	private String filtroOpcional;
	private FacesContext context;
	private String msg; 
	
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
		msg = "";
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
		context = FacesContext.getCurrentInstance();
		try {
			if (opcional.getCodOpReqServ() == null	|| opcional.getCodOpReqServ() == 0) {
				if (!existe(opcional)){
					opcionalDAO.adiciona(opcional);
					msg = Utils.getMessageResourceString("messages",
							"mensagens.inclusao.sucesso", null, context.getViewRoot()
									.getLocale());
					//addGlobalMessage("Inclusao feita com sucesso!");			
				} else {
					msg = Utils.getMessageResourceString("messages",
							"mensagens.existe.nome", null, context.getViewRoot()
									.getLocale());	
					//addGlobalMessage("Nome do opcional já existe!");
				}
			} else {
				OpcionaisReqServ temp;
				temp = opcionalDAO.recupera(opcional.getCodOpReqServ());
				if (temp != null) {
					temp.setCodOpReqServ(opcional.getCodOpReqServ());
					temp.setNomeItem(opcional.getNomeItem().trim().toUpperCase());
					temp.setValorItem(opcional.getValorItem());
					opcionalDAO.altera(temp);
					//addGlobalMessage("Atualizacao feita com sucesso!");
					msg = Utils.getMessageResourceString("messages",
							"mensagens.atualizacao.sucesso", null, context.getViewRoot()
									.getLocale());					
				}
			}
			// apresentar mensagem
			addGlobalMessage(msg);
		} catch (Exception e) {
			addGlobalMessage("Nao foi possivel realizar a operacao!");			
		}
		this.limpar();

	}

	/**
	 * Excluir opcional
	 */
	public void apagar() {
		context = FacesContext.getCurrentInstance();
		try {
			opcionalDAO.remove(opcional.getCodOpReqServ());
			msg = Utils.getMessageResourceString("messages",
					"mensagens.exclusao.sucesso", null, context.getViewRoot()
							.getLocale());	
			addGlobalMessage(msg);
			//addGlobalMessage("Opcional exclu�do com sucesso!");
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

	public boolean existe(OpcionaisReqServ op) {
		return opcionalDAO.existe(op);
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
