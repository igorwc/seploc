package br.seploc.mbeans;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.dao.ClienteDAO;
import br.seploc.dao.EntregaDAO;
import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.dao.ProjetoDAO;
import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.Entrega;
import br.seploc.pojos.OpcionaisReqServ;
import br.seploc.pojos.Projeto;
import br.seploc.pojos.RequisicaoServico;

public class RequisicaoServicoBean {

	private Projeto projeto;
	private ProjetoDAO projetoDAO;	
	private Cliente cliente;
	private ClienteDAO clienteDAO;
	private Entrega entrega;
	private EntregaDAO entregaDAO;
	private OpcionaisReqServ opcional;
	private OpcionaisReqServDAO opcionalDAO;
	private RequisicaoServico reqServ;
	private RequisicaoServicoDAO reqServDAO;
	
	private String clienteCorrente;
	private String projetoCorrente;
	private String entregaCorrente;
	private String opcionalCorrente;
	private Cliente clienteEscolhido;
	private Projeto projetoEscolhido;	
	private Entrega entregaEscolhido;
	private OpcionaisReqServ opcionalEscolhido;
	private Integer codCliente;	
	private Integer codProjeto;
	private Integer codEntrega;
	private Integer codOpcional;
		
	public RequisicaoServicoBean() {	
		cliente = new Cliente();
		clienteDAO = new ClienteDAO();
		clienteCorrente = "";
		codCliente = new Integer(0);
		
		projeto = new Projeto();
		projetoDAO = new ProjetoDAO();
		projetoCorrente = "";
		codProjeto = new Integer(0);
		
		entrega = new Entrega();
		entregaDAO = new EntregaDAO();
		entregaCorrente = "";
		codEntrega = new Integer(0);

		opcional = new OpcionaisReqServ();
		opcionalDAO = new OpcionaisReqServDAO();
		opcionalCorrente = "";
		codOpcional = new Integer(0);
		
		reqServ = new RequisicaoServico();
		reqServDAO = new RequisicaoServicoDAO();
		
	}
		
	public Projeto getProjeto() {		
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

	//CLIENTE
	public String getClienteCorrente() {
		return clienteCorrente;
	}

	public void setClienteCorrente(String clienteCorrente) {
		this.clienteCorrente = clienteCorrente;
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
	}	
	
	public List<Cliente> getTodosClientes(){
		List<Cliente> retorno = this.clienteDAO.getLista();
		return retorno;
	}	
	
	//PROJETO
	public String getProjetoCorrente() {
		return projetoCorrente;
	}

	public void setProjetoCorrente(String projetoCorrente) {
		this.projetoCorrente = projetoCorrente;
	}	
	
	public Projeto getProjetoEscolhido() {
		return projetoEscolhido;
	}

	public void setProjetoEscolhido(Projeto projetoEscolhido) {
		this.projetoEscolhido = projetoEscolhido;
	}

	public Integer getCodProjeto() {
		return codProjeto;
	}

	public void setCodProjeto(Integer codProjeto) {
		this.codProjeto = codProjeto;		
		this.projetoEscolhido = projetoDAO.recupera(codProjeto);
	}	
	
	public List<Projeto> getTodosProjetosClientes(){
		Cliente c = clienteDAO.recupera(codCliente);
		if (c == null) {
			return null;
		} else if (c.getProjetos().isEmpty() || c.getProjetos().size() == 0) {
			return null;
		} else {
			return c.getProjetos();
		}
	}	
	
	//ENTREGA
	public String getEntregaCorrente() {
		return entregaCorrente;
	}

	public void setEntregaCorrente(String entregaCorrente) {
		this.entregaCorrente = entregaCorrente;
	}	
	
	public Entrega getEntregaEscolhido() {
		return entregaEscolhido;
	}

	public void setEntregaEscolhido(Entrega entregaEscolhido) {
		this.entregaEscolhido = entregaEscolhido;
	}

	public Integer getCodEntrega() {
		return codEntrega;
	}

	public void setCodEntrega(Integer codEntrega) {
		this.codEntrega = codEntrega;
		this.entregaEscolhido = entregaDAO.recupera(codEntrega);
	}	
	
	public List<Entrega> getTodasEntregas(){
		List<Entrega> retorno = this.entregaDAO.getLista();
		return retorno;
	}	
	
	//OPCIONAL
	public String getOpcionalCorrente() {
		return clienteCorrente;
	}

	public void setOpcionalCorrente(String opcionalCorrente) {
		this.opcionalCorrente = opcionalCorrente;
	}	
	
	public OpcionaisReqServ getOpcionalEscolhido() {
		return opcionalEscolhido;
	}

	public void setOpcionalEscolhido(OpcionaisReqServ opcionalEscolhido) {
		this.opcionalEscolhido = opcionalEscolhido;
	}

	public Integer getCodOpcional() {
		return codOpcional;
	}

	public void setCodOpcional(Integer codOpcional) {
		this.codOpcional = codOpcional;
		this.opcionalEscolhido = opcionalDAO.recupera(codOpcional);
	}	
	
	public List<OpcionaisReqServ> getTodosOpcionais(){
		List<OpcionaisReqServ> retorno = this.opcionalDAO.getLista();
		return retorno;
	}		
	//FUNCIONALIDADES
	public void cadastra() {
		
	}
	
	public void edita(){
		
	}
	
	public void apaga(){
		
	}
	
	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public void validateFantasia(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null)
			return;
		String nome;

		Pattern pattern = Pattern.compile("^\\s*\\s(\\s)$");
		Matcher m = pattern.matcher(value.toString());
		if (value instanceof String)
			nome = value.toString().trim();
		else {
			FacesMessage message = new FacesMessage("Nome Fantasia Inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() < 5) {
			FacesMessage message = new FacesMessage(
					"O Nome Fantasia deve ter 5 letras no mínimo");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (nome.length() >= 60) {
			FacesMessage message = new FacesMessage(
					"O Nome Fantasia deve ter entre 5 e 60 caracteres");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (m.matches()) {
			FacesMessage message = new FacesMessage(
					"O Nome Fantasia só tem espaços");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
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
