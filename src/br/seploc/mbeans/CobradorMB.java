package br.seploc.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.seploc.dao.CobradorDAO;
import br.seploc.pojos.Cobrador;

public class CobradorMB {

	private static final long serialVersionUID = 1L;
	static int quantidade = 0;
	private Cobrador cobrador;
	private CobradorDAO cobradorDAO;
	
	//CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public CobradorMB(){
		init();
	}
	
	/**
	 * Metodo de inicialização
	 */
	public void init(){
		quantidade++;
		System.out.println("quantidade:  " +quantidade);
		cobrador = new Cobrador();
		cobradorDAO = new CobradorDAO();
		System.out.println("\n\n\n\n\n\n\nContrui CobradorMB\n\n\n\n\n\n\n\n\n\n\n");
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
	public void cadastra() {
		if (cobrador.getCodCobrador() == null || cobrador.getCodCobrador() == 0) {
			cobradorDAO.adiciona(cobrador);
			addGlobalMessage("Inclusão feita com sucesso!");
		} else {
			Cobrador temp;
			temp = cobradorDAO.recupera(cobrador.getCodCobrador());
			if (temp != null) {
				temp.setCodCobrador(cobrador.getCodCobrador());
				temp.setAtivo("S");
				temp.setNome(cobrador.getNome().trim());
				temp.setFoneContato(cobrador.getFoneContato());	
				
				cobradorDAO.altera(temp);
				addGlobalMessage("Atualização feita com sucesso!");
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
		this.cadastra();
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

	/**
	 * Excluir o cobrador
	 */
	public void apaga() {
		try {
			cobradorDAO.remove(cobrador.getCodCobrador());
			addGlobalMessage("Excluído com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		cobrador = new Cobrador();
	}
	
}
