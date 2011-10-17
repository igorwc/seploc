package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.CobradorDAO;
import br.seploc.pojos.Cobrador;
import br.seploc.util.Utils;

public class CobradorMB implements Serializable {

	private static final long serialVersionUID = 1L;
	static int quantidade = 0;
	private Cobrador cobrador;
	private CobradorDAO cobradorDAO;
	private String filtroCobrador;
	private FacesContext context;
	private String msg;
	
	//CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public CobradorMB(){
		init();
	}
	
	/**
	 * Metodo de inicializa��o
	 */
	public void init(){
		quantidade++;
		System.out.println("quantidade:  " +quantidade);
		cobrador = new Cobrador();
		cobradorDAO = new CobradorDAO();
		System.out.println("\n\n\n\n\n\n\nContrui CobradorMB\n\n\n\n\n\n\n\n\n\n\n");		
		msg = "";
	}

	public void setFiltroCobrador(String filtroCobrador) {
		this.filtroCobrador = filtroCobrador;
	}

	public String getFiltroCobrador() {
		return filtroCobrador;
	}

	// SETTERS AND GETTERS
	/**
	 * Buscar Cobrador
	 * @return Cobrador
	 */
	public Cobrador getCobrador() {
		return cobrador;
	}
	
	/**
	 * Atribuir Cobrador
	 * @param cobrador
	 */
	public void setCobrador(Cobrador cobrador) {
		this.cobrador = cobrador;
	}
	
	/**
	 * Buscar CobradorDAO
	 * @return CobradorDAO
	 */
	public CobradorDAO getCobradorDAO() {
		return cobradorDAO;
	}
	
	/**
	 * Atribuir CobradorDAO
	 * @param cobradorDAO
	 */
	public void setCobradorDAO(CobradorDAO cobradorDAO) {
		this.cobradorDAO = cobradorDAO;
	}	
	
	/**
	 * Recupera lista de cobradores
	 * @return List<Cobrador> 
	 */
	public List<Cobrador> getLista() {
		return cobradorDAO.getLista();
	}
	
	// METODOS
	/**
	 * Cadastrar ou alterar o cobrador
	 */
	public void cadastrar() {
		context = FacesContext.getCurrentInstance();
		if (cobrador.getCodCobrador() == null || cobrador.getCodCobrador() == 0) {
			if (!existe(cobrador)){
				Cobrador c = new Cobrador(cobrador.getNome(),cobrador.getFoneContato(),cobrador.getAtivo());
				cobradorDAO.adiciona(c);
				msg = Utils.getMessageResourceString("messages",
						"mensagens.inclusao.sucesso", null, context.getViewRoot()
								.getLocale());				
			} else {
				msg = Utils.getMessageResourceString("messages",
						"mensagens.existe.nome", null, context.getViewRoot()
								.getLocale());				
			}
			addGlobalMessage(msg);
			//addGlobalMessage("Inclusao feita com sucesso!");
		} else {
			Cobrador temp;
			temp = cobradorDAO.recupera(cobrador.getCodCobrador());
			if (temp != null) {
				temp.setCodCobrador(cobrador.getCodCobrador());
				temp.setAtivo("S");
				temp.setNome(cobrador.getNome().trim());
				temp.setFoneContato(cobrador.getFoneContato());	
				
				cobradorDAO.altera(temp);
				msg = Utils.getMessageResourceString("messages",
						"mensagens.atualizacao.sucesso", null, context.getViewRoot()
								.getLocale());
				addGlobalMessage(msg);
				//addGlobalMessage("Atualização feita com sucesso!");
			}
		}
		cobrador = new Cobrador();
	}

	/**
	 * Limpar o cobrador
	 */
	public void limpar() {
		cobrador = new Cobrador();
		System.out.println("Limpar Cobrador");
	}
	
	/**
	 * Editar cobrador
	 */
	public void editar(){
		try {
			cobrador = cobradorDAO.recupera(cobrador.getCodCobrador());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Metodo para incluir mensagens globais no formulario de cadastro
	 * 
	 * @param String
	 *            message
	 */
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	/**
	 * Excluir o cobrador
	 */
	public void apagar() {
		context = FacesContext.getCurrentInstance();
		try {
			cobradorDAO.remove(cobrador.getCodCobrador());
			msg = Utils.getMessageResourceString("messages",
					"mensagens.exclusao.sucesso", null, context.getViewRoot()
							.getLocale());	
			addGlobalMessage(msg);
			//addGlobalMessage("Excluido com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		cobrador = new Cobrador();
	}
	
	private boolean existe(Cobrador c){
		return cobradorDAO.existe(c);
	}
}
