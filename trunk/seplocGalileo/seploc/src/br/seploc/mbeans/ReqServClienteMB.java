package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.seploc.pojos.Cliente;
import br.seploc.pojos.LinhaRequisicao;
import br.seploc.pojos.OpcionaisReqServ;
import br.seploc.pojos.Entrega;
import br.seploc.pojos.Papel;
import br.seploc.pojos.Projeto;
import br.seploc.pojos.ReqServicosOpcionais;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.dao.ClienteDAO;
import br.seploc.dao.EntregaDAO;
import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.dao.PapelDAO;
import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.dao.exceptions.RecordNotFound;

public class ReqServClienteMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private RequisicaoServico reqServico;
	private RequisicaoServicoDAO reqServicoDAO;
	private Cliente cliente;
	private OpcionaisReqServ opcional;
	private Entrega entrega;
	private Papel papel;
	private Projeto projeto;
	private LinhaRequisicao linhaReqServ;
	private ReqServicosOpcionais opcionalReqServ;
	private String nomePapel;
	private String filtroOpcional;
	private String filtroEntrega;
	private String filtroProjeto;
	private String filtroCliente;
	private double valorTotalReq;
	private int quantidadeOpcional;
	private int numReqAtual;
	private int numReqBusca;
	

	// CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public ReqServClienteMB() {
		cliente = new Cliente();
		cliente.setIdCliente(0);
		opcional = new OpcionaisReqServ();
		entrega = new Entrega();
		papel = new Papel();
		projeto = new Projeto();
		linhaReqServ = new LinhaRequisicao();
		reqServico = new RequisicaoServico();
		reqServicoDAO = new RequisicaoServicoDAO();
		filtroCliente = "";
		valorTotalReq = 0;		
	}

	// GETTERS E SETTERS
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public OpcionaisReqServ getOpcional() {
		return opcional;
	}

	public void setOpcional(OpcionaisReqServ opcional) {
		this.opcional = opcional;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public String getFiltroOpcional() {
		return filtroOpcional;
	}

	public void setFiltroOpcional(String filtroOpcional) {
		this.filtroOpcional = filtroOpcional;
	}

	public String getFiltroEntrega() {
		return filtroEntrega;
	}

	public void setFiltroEntrega(String filtroEntrega) {
		this.filtroEntrega = filtroEntrega;
	}

	public String getNomePapel() {
		return nomePapel;
	}

	public void setNomePapel(String filtroPapel) {
		this.nomePapel = filtroPapel;
	}

	public String getFiltroProjeto() {
		return filtroProjeto;
	}

	public void setFiltroProjeto(String filtroProjeto) {
		this.filtroProjeto = filtroProjeto;
	}

	public String getFiltroCliente() {
		return filtroCliente;
	}

	public void setFiltroCliente(String filtroCliente) {
		this.filtroCliente = filtroCliente;
	}

	public int getQuantidadeOpcional() {
		return quantidadeOpcional;
	}

	public void setQuantidadeOpcional(int quantidadeOpcional) {
		this.quantidadeOpcional = quantidadeOpcional;
	}

	public void setValorTotalReq(double valorTotalReq) {
		this.valorTotalReq = valorTotalReq;
	}

	public double getValorTotalReq() {
		return valorTotalReq;
	}

	public RequisicaoServico getReqServico() {
		return reqServico;
	}

	public void setReqServico(RequisicaoServico reqServico) {
		this.reqServico = reqServico;
	}

	public void setLinhaReqServ(LinhaRequisicao linhaReqServ) {
		this.linhaReqServ = linhaReqServ;
	}

	public LinhaRequisicao getLinhaReqServ() {
		return linhaReqServ;
	}

	public void setOpcionalReqServ(ReqServicosOpcionais opcionalReqServ) {
		this.opcionalReqServ = opcionalReqServ;
	}

	public ReqServicosOpcionais getOpcionalReqServ() {
		return opcionalReqServ;
	}

	public void setNumReqAtual(int numReqSelecionado) {
		this.numReqAtual = numReqSelecionado;
	}

	public int getNumReqAtual() {
		return numReqAtual;
	}

	public int getNumReqBusca() {
		return numReqBusca;
	}

	public void setNumReqBusca(int numReqBusca) {
		this.numReqBusca = numReqBusca;
	}

	public RequisicaoServicoDAO getReqServicoDAO() {
		return reqServicoDAO;
	}

	public void setReqServicoDAO(RequisicaoServicoDAO reqServicoDAO) {
		this.reqServicoDAO = reqServicoDAO;
	}

	public List<Cliente> getTodosClientes() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> retorno = clienteDAO.getLista();
		filtroCliente = "";
		return retorno;
	}

	public List<Papel> getTodosPapeis() {
		PapelDAO papelDAO = new PapelDAO();
		List<Papel> retorno = papelDAO.getLista();

		return retorno;
	}

	public List<OpcionaisReqServ> getTodosOpcionais() {
		OpcionaisReqServDAO opcionalDAO = new OpcionaisReqServDAO();
		List<OpcionaisReqServ> retorno = opcionalDAO.getLista();

		return retorno;
	}

	public List<Entrega> getTodasEntregas() {
		EntregaDAO entregaDAO = new EntregaDAO();
		List<Entrega> retorno = entregaDAO.getLista();

		return retorno;
	}

	public List<Projeto> getTodosProjetos() {

		List<Projeto> retorno = null;
		if (cliente == null || cliente.getIdCliente().intValue() == 0) {
			retorno = new ArrayList<Projeto>();
		} else {
			if(cliente.getProjetos().isEmpty()){
				retorno = new ArrayList<Projeto>();
				Projeto p = new Projeto();
				p.setCodProj(0);
				p.setProjeto("Cliente n�o tem projetos");
				retorno.add(p);
			}else{
				retorno = cliente.getProjetos();
			}
		}
		return retorno;
	}

	public List<RequisicaoServico> getListaReqServ() {
		// setar data de 60 dias atr�s
		Calendar calendarData = Calendar.getInstance();
		  int numeroDiasParaSubtrair = -60;
		  calendarData.add(Calendar.DATE, numeroDiasParaSubtrair);
		  java.sql.Date dias60 = new java.sql.Date(calendarData.getTimeInMillis());		  
		
		List<RequisicaoServico> retorno = reqServicoDAO.getListaSinceDate(dias60);
		return retorno;
	}
	
	public List<RequisicaoServico> getTodasReqServPorCliente(Cliente cliente) {
		List<RequisicaoServico> retorno = reqServicoDAO
				.getListaPorProjeto(cliente);

		return retorno;
	}
	
	public void cadastrar() {
		if (reqServico.getNumReq() == null || reqServico.getNumReq() == 0) {
			try {
				//A linha ou o opcional item obrigatorio da requisicao
				boolean existeLinha = false;
				boolean existeOpcional = false;
				
				if (quantidadeOpcional >= 1) existeOpcional = true;
				if (linhaReqServ.getQuant() >= 1) existeLinha = true;
				
				reqServico.setValorTotal(0.0);
				// setar data de criacao da requisicao
				java.util.Date data = new java.util.Date();
				java.sql.Date hoje = new java.sql.Date(data.getTime());
				reqServico.setData(hoje);
				// adicionar projeto
				reqServico.setProjeto(projeto);
				// adicionar a entrega
				if (entrega.getCodEntrega() != null	){
					reqServico.setEntrega(entrega);
					reqServico.setValorEnt(entrega.getPreco());
					reqServico.setValorTotal(reqServico.getValorTotal()
							+ entrega.getPreco());					
				} else {
					reqServico.setValorEnt(0.0);
				}
				// inserir valores iniciais
				reqServico.setStatus(0);
				reqServico.setVisivelNf(0);
				reqServico.setVisivelReq(0);
				
				// adicionar a requisicao de servico se um dos itens obrigatoriso existirem
				if (existeLinha || existeOpcional){
					reqServicoDAO.adiciona(reqServico);
				} else {
					throw new Exception("Requisição de Serviço precisa de uma linha ou de um opcional!"); 
				}

				// recuperar a requisicao				
				int numReq = reqServico.getNumReq();				
				//reqServico = reqServicoDAO.recupera(numReq);
				RequisicaoServico temp0 = reqServicoDAO.recupera(numReq);
				
				//reqServicoDAO.refresh(reqServico);
				
				// adicionar o opcional
				if (existeOpcional) {
					reqServicoDAO.addOpcional(temp0, opcional,
							quantidadeOpcional);
					temp0.setValorTotal(temp0.getValorTotal()
							+ (opcional.getValorItem() * quantidadeOpcional));					
				}
				// adicionar a linha
				if (existeLinha) {
					// transformar o nomePapel em Objeto Papel
					//papel = this.converterToPapel(this.nomePapel);
					PapelDAO papelDAO = new PapelDAO();
					papel = papelDAO.recupera(papel.getCodPapel());
					linhaReqServ.setPapel(papel);
					double valorPapel = 0.0;
					// verificar a cor em uso
					if (linhaReqServ.getImpressao().equalsIgnoreCase("Mono"))
						valorPapel = papel.getImpMono();
					if (linhaReqServ.getImpressao().equalsIgnoreCase("Color"))
						valorPapel = papel.getImpColor();
					if (linhaReqServ.getImpressao().equalsIgnoreCase("Shade"))
						valorPapel = papel.getImpShade();

					double valorUnit = (linhaReqServ.getDimensao() * linhaReqServ
							.getFormato()) + valorPapel;
					linhaReqServ.setValorSubUnit(valorUnit);
					linhaReqServ.setValorUnit(valorUnit
							* linhaReqServ.getQuant());

					reqServicoDAO.addLinha(reqServico, linhaReqServ);
					reqServico.setValorTotal(reqServico.getValorTotal()
							+ linhaReqServ.getValorUnit());					
				}				
				//reqServicoDAO.altera(reqServico);				
				setValorTotalReq(temp0.getValorTotal());
				addGlobalMessage("Inclusão feita com sucesso!");
			} catch (ValidatorException e) {
				addGlobalMessage(e.getMessage());
			} catch (Exception e) {
				addGlobalMessage(e.getMessage());
			}
		} else {
			RequisicaoServico temp;
			temp = null;
			try {
				temp = reqServicoDAO.recupera(reqServico.getNumReq());

				if (temp != null) {
					// adicionar o opcional
					if (quantidadeOpcional >= 1) {
						reqServicoDAO.addOpcional(temp, opcional,
								quantidadeOpcional);
					}
					// adicionar a linha
					if (linhaReqServ.getQuant() >= 1) {
						// transformar o nomePapel em Objeto Papel
						//papel = this.converterToPapel(this.nomePapel);
						PapelDAO papelDAO = new PapelDAO();
						papel = papelDAO.recupera(papel.getCodPapel());						
						linhaReqServ.setPapel(papel);
						double valorPapel = 0.0;
						// verificar a cor em uso
						if (linhaReqServ.getImpressao().equalsIgnoreCase("Mono"))
							valorPapel = papel.getImpMono();
						if (linhaReqServ.getImpressao().equalsIgnoreCase("Color"))
							valorPapel = papel.getImpColor();
						if (linhaReqServ.getImpressao().equalsIgnoreCase("Shade"))
							valorPapel = papel.getImpShade();

						double valorUnit = (linhaReqServ.getDimensao() * 
											linhaReqServ.getFormato()) + valorPapel;
						linhaReqServ.setValorSubUnit(valorUnit);
						linhaReqServ.setValorUnit(valorUnit	* linhaReqServ.getQuant());

						reqServicoDAO.addLinha(temp, linhaReqServ);
					}
					// verificar se existe alteracao na entrega
					if (entrega.getCodEntrega() != null){
						if (temp.getEntrega() == null){
							temp.setEntrega(entrega);
						} else if (!temp.getEntrega().equals(entrega)){
							temp.setEntrega(entrega);
						}					
					}
					// verificar se foi alterado o projeto
					if (!temp.getProjeto().equals(projeto)){
						temp.setProjeto(projeto);
					}					
					temp.setValorTotal(this.calcularTotal(temp));					
					reqServicoDAO.altera(temp);
					reqServico = reqServicoDAO.recupera(temp.getNumReq());
					setValorTotalReq(reqServico.getValorTotal());
					temp.setProjeto(reqServico.getProjeto());
					addGlobalMessage("Atualização feita com sucesso!");
				}
				
			} catch (Exception e) {
				addGlobalMessage(e.getMessage());
			}

		}
		this.limparLinhaOpcional();
	}
	
	protected double calcularTotal(RequisicaoServico reqServ){
		double retorno = 0;
		//opcionais
		if (reqServ.getOpcionais() != null){
			for (ReqServicosOpcionais op : reqServ.getOpcionais()){
				retorno = retorno + (op.getQuantidade() * op.getOpcionaisReqServ().getValorItem());
			}			 
		}
		//linhas
		if (reqServ.getLinhaRequisicao() != null){
			for (LinhaRequisicao lr : reqServ.getLinhaRequisicao()){
				retorno = retorno + lr.getValorUnit();
			}
		}
		//entrega
		if (reqServ.getEntrega() != null){
			retorno = retorno + reqServ.getEntrega().getPreco();
		}
		
		return retorno;
	}

	public void editarLinha() {
		try {
			papel = linhaReqServ.getPapel(); 
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}
	
	public void editarOpcional() {
		try {
			opcional = opcionalReqServ.getOpcionaisReqServ();
			quantidadeOpcional = opcionalReqServ.getQuantidade();
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}	

	public void apagar() {
		try {
			reqServicoDAO.remove(reqServico.getNumReq());
			addGlobalMessage("Excluído com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		this.limpar();
	}
	
	public void apagarLinha(){
		try {
			reqServicoDAO.removeLinha(linhaReqServ);
			reqServico.setValorTotal(reqServico.getValorTotal() - linhaReqServ.getValorSubUnit());
			reqServicoDAO.altera(reqServico);
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}	

	public void apagarOpcional(){
		try {
			reqServicoDAO.removeOpcionais(opcionalReqServ.getId());
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}	
	
	public void limpar() {
		cliente = new Cliente();
		cliente.setIdCliente(0);
		opcional = new OpcionaisReqServ();
		entrega = new Entrega();
		papel = new Papel();
		projeto = new Projeto();
		linhaReqServ = new LinhaRequisicao();
		opcionalReqServ = new ReqServicosOpcionais();
		reqServico = new RequisicaoServico();
		reqServicoDAO = new RequisicaoServicoDAO();
		filtroCliente = "";
		valorTotalReq = 0;	
	}

	public void limparLinhaOpcional() {
		opcional = new OpcionaisReqServ();
		papel = new Papel();
		nomePapel = "";
		quantidadeOpcional = 0;
		linhaReqServ = new LinhaRequisicao();
	}

	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
