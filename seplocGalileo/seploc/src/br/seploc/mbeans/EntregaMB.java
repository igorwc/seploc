package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.EntregaDAO;
import br.seploc.pojos.Entrega;
import br.seploc.util.Utils;

public class EntregaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	static int quantidade = 0;
	private Entrega entrega;
	private EntregaDAO entregaDAO;	
	private FacesContext context;
	private String msg;
	
	//CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public EntregaMB(){
		init();
	}
	
	/**
	 * Metodo de inicializa��o
	 */
	public void init(){
		quantidade++;		
		entrega = new Entrega();
		entregaDAO = new EntregaDAO();
		System.out.println("\n\n\n\n\n\n\nContrui EntregaMB\n\n\n\n\n\n\n\n\n\n\n");		
		msg = "";
	}

	// SETTERS AND GETTERS
	/**
	 * Buscar Entrega
	 * @return Entrega
	 */
	public Entrega getEntrega() {
		return entrega;
	}
	
	/**
	 * Atribuir Entrega
	 * @param entrega
	 */
	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}
	
	/**
	 * Buscar EntregaDAO
	 * @return EntregaDAO
	 */
	public EntregaDAO getEntregaDAO() {
		return entregaDAO;
	}
	
	/**
	 * Atribuir EntregaDAO
	 * @param entregaDAO
	 */
	public void setEntregaDAO(EntregaDAO entregaDAO) {
		this.entregaDAO = entregaDAO;
	}	
	
	/**
	 * Recupera lista de entregas
	 * @return List<Entrega> 
	 */
	public List<Entrega> getLista() {
		return entregaDAO.getLista();
	}
	
	// METODOS
	/**
	 * Cadastrar ou alterar o entrega
	 */
	public void cadastrar() {
		context = FacesContext.getCurrentInstance();
		if (entrega.getCodEntrega() == null || entrega.getCodEntrega() == 0) {
			try {
				if(!existe(entrega)){
					entregaDAO.adiciona(entrega);
					msg = Utils.getMessageResourceString("messages",
							"mensagens.inclusao.sucesso", null, context.getViewRoot()
									.getLocale());				
					//addGlobalMessage("Inclus�o feita com sucesso!");
				} else {
					msg = Utils.getMessageResourceString("messages",
							"mensagens.existe.nome", null, context.getViewRoot()
									.getLocale());					
				}		
				addGlobalMessage(msg);
			} catch (Exception e) {
				addGlobalMessage(e.getMessage());
			}
		} else {
			Entrega temp;
			temp = entregaDAO.recupera(entrega.getCodEntrega());
			if (temp != null) {
				temp.setCodEntrega(entrega.getCodEntrega());
				temp.setLocal(entrega.getLocal().trim());
				temp.setPreco(entrega.getPreco());	
				
				try {
					entregaDAO.altera(temp);
					msg = Utils.getMessageResourceString("messages",
							"mensagens.atualizacao.sucesso", null, context.getViewRoot()
									.getLocale());
					addGlobalMessage(msg);
					//addGlobalMessage("Atualiza��o feita com sucesso!");					
				} catch (Exception e) {
					addGlobalMessage(e.getMessage());
				}
			}
		}
		this.limpar();
	}

	/**
	 * Limpar entrega
	 */
	public void limpar() {
		entrega = new Entrega();
	}
	
	/**
	 * Editar entrega
	 */
	public void editar(){
		try {
			entrega = entregaDAO.recupera(entrega.getCodEntrega());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}		
	}
	
	/**
	 * Excluir entrega
	 */
	public void apagar() {
		context = FacesContext.getCurrentInstance();
		try {
			entregaDAO.remove(entrega.getCodEntrega());
			msg = Utils.getMessageResourceString("messages",
					"mensagens.exclusao.sucesso", null, context.getViewRoot()
							.getLocale());	
			addGlobalMessage(msg);
			//addGlobalMessage("Exclu�do com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		entrega = new Entrega();
	}	
	
	public boolean existe(Entrega entrega) {
		return entregaDAO.existe(entrega);
	}
	
	public List<Entrega> getListaPorLocal(){
		List<Entrega> retorno = entregaDAO.getEntregasPorLocal(entrega.getLocal());
		return retorno;
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
