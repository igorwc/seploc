package br.seploc.mbeans;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.dao.ClienteDAO;
import br.seploc.dao.ProjetoDAO;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.Projeto;

public class ProjetoClienteBean {

	private Projeto projeto;	
	private Cliente cliente;
	private ProjetoDAO projetoDAO;	
	private ClienteDAO clienteDAO;
	private HtmlInputText inputProjeto;
	private HtmlInputText inputCliente;
	
	private String clienteCorrete;
	private Cliente clienteEscolhido;	
	private Integer codCliente;

	public ProjetoClienteBean() {	
		System.out.println("Objeto Instaciado");
		clienteDAO = new ClienteDAO();
		projeto = new Projeto();
		projetoDAO = new ProjetoDAO();
		clienteCorrete = "";
		cliente = new Cliente();		
	}
		
	public Projeto getProjeto() {
		System.out.println("Get Projeto");
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	public ProjetoDAO getProjetoDAO() {
		return projetoDAO;
	}

	public void setProjetoDAO(ProjetoDAO projetoDAO) {
		this.projetoDAO = projetoDAO;
	}

	public void cadastra() {
		try{
			if (cliente == null){	
				cliente = clienteDAO.recupera(Integer.parseInt(inputCliente.getValue().toString()));
				projeto.setCliente(cliente);
			}
				projetoDAO.adiciona(projeto);
				
				addGlobalMessage("Inclusão feita com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
			e.printStackTrace();
		}			
		limpa();
	}
	
	public void edita(){
		
	}
	
	public void apaga(){
		
	}
	
	public void limpa() {
		//projeto =  new Projeto();
		inputProjeto.setValue("");
		System.out.println("Limpar Projeto");
	}		
	
	public HtmlInputText getInputProjeto() {
		return inputProjeto;
	}

	public void setInputCliente(HtmlInputText inputCliente) {
		this.inputCliente = inputCliente;
	}
	
	public HtmlInputText getInputCliente() {
		return inputCliente;
	}

	public void setInputProjeto(HtmlInputText inputProjeto) {
		this.inputProjeto = inputProjeto;
	}
	
	public List<Projeto> getLista(){
		Cliente c = projeto.getCliente();
		if (c == null){
			return null;
		} else if(c.getProjetos().isEmpty() || c.getProjetos().size() == 0){
			return null;
		}else{
			return c.getProjetos();
		}
	}
	
	public List<Cliente> getTodosClientes(){
		List<Cliente> retorno = this.clienteDAO.getLista();
		return retorno;
	}
	
	public String getClienteCorrete() {
		return clienteCorrete;
	}

	public void setClienteCorrete(String clienteCorrete) {
		this.clienteCorrete = clienteCorrete;
	}	
	
	public Cliente getClienteEscolhido() {
		return clienteEscolhido;
	}

	public void setClienteEscolhido(Cliente clienteEscolhido) {
		this.clienteEscolhido = clienteEscolhido;
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
		this.clienteEscolhido = clienteDAO.recupera(codCliente);
		System.out.println("ClienteEscolhido = "+ this.clienteEscolhido.getFantasia());
	}
	
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public void validateNomeProj(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null)
			return;
		String nome;

		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			FacesMessage message = new FacesMessage("Nome do Projeto Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 5) {
			FacesMessage message = new FacesMessage(
					"O Nome do Projeto deve ter 5 letras no mínimo");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() >= 80) {
			FacesMessage message = new FacesMessage(
					"O Nome do Projeto deve ter entre 5 e 80 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			FacesMessage message = new FacesMessage(
					"O Nome do Projeto só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}		
}
