package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
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
import br.seploc.pojos.ReqServUsuario;
import br.seploc.pojos.ReqServicosOpcionais;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.pojos.Usuario;
import br.seploc.util.SessionObjectsManager;
import br.seploc.dao.ClienteDAO;
import br.seploc.dao.EntregaDAO;
import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.dao.PapelDAO;
import br.seploc.dao.ProjetoDAO;
import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.dao.UsuarioDAO;
import br.seploc.dao.pagedqueries.AllClientsPager;
import br.seploc.dao.pagedqueries.FilteredNameClientesPager;
import br.seploc.util.Utils;

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
	private String filtroClienteAnterior;
	private String clienteBalcao;
	private String projetoBalcao;
	private double valorTotalReq;
	private int quantidadeOpcional;
	private int numReqAtual;
	private int numReqBusca;
	private int orcamento;	
	private Integer numReqSessao;
	private ReqServUsuario reqServUsuario;
	private String quantLinha;
	private boolean resetaFiltroCliente;
	private int clienteCurrentPage;
	private int clientePages;	
	private AllClientsPager clientesPager = new AllClientsPager();
	private FilteredNameClientesPager clientePager;	

	// CONSTRUTOR
	/**
	 * Construtor da classe
	 */
	public ReqServClienteMB() {
		cliente = new Cliente();
		cliente.setIdCliente(0);
		opcional = new OpcionaisReqServ();
		opcionalReqServ = new ReqServicosOpcionais();
		entrega = new Entrega();
		papel = new Papel();
		projeto = new Projeto();
		linhaReqServ = new LinhaRequisicao();
		reqServico = new RequisicaoServico();
		reqServicoDAO = new RequisicaoServicoDAO();
		filtroCliente = "";
		valorTotalReq = 0;		
		filtroClienteAnterior = "";
		clientePager = new FilteredNameClientesPager();
		clientePager.init(10);
		resetaFiltroCliente = false;	
		clienteBalcao = "CLIENTE BALCAO";
		projetoBalcao = "GERAL";
		quantLinha = "";
	}

	// GETTERS E SETTERS
	
	public Cliente getCliente() {
		return cliente;
	}

	public String getQuantLinha() {
		return quantLinha;
	}

	public void setQuantLinha(String quantLinha) {
		this.quantLinha = quantLinha;
	}
	
	public void normalizaQuantidade(){
		if (quantLinha == null || quantLinha.trim().equals(""))
			quantLinha =  new String("");
		int i = 0;
		String saida = "";
		for (Character c : quantLinha.toCharArray()) {
			if (Character.isDigit(c)) {
				saida += c.toString();
				i++;
			} else {
				i++;
				continue;
			}
		}
		quantLinha = saida.trim();
		linhaReqServ.setQuant(Integer.parseInt(quantLinha));
	}

	public void setCliente(Cliente cliente) {
		this.cliente = new Cliente();
		this.projeto = new Projeto();
		this.cliente = cliente;
		this.filtroProjeto = "";
		System.out.println("Limpou o cliente");
		System.out.println("cliente: "+cliente.getFantasia().toString());		
	}

	public String getClienteBalcao() {
		return clienteBalcao;
	}

	public void setClienteBalcao(String clienteBalcao) {
		this.clienteBalcao = clienteBalcao;
	}

	public String getProjetoBalcao() {
		return projetoBalcao;
	}

	public void setProjetoBalcao(String projetoBalcao) {
		this.projetoBalcao = projetoBalcao;
	}

	public OpcionaisReqServ getOpcional() {
		return opcional;
	}

	public void setOpcional(OpcionaisReqServ opcional) {
		this.opcional = opcional;
	}

	public ReqServicosOpcionais getOpcionalReqServ() {
		return opcionalReqServ;
	}

	public void setOpcionalReqServ(ReqServicosOpcionais opcionalReqServ) {
		this.opcionalReqServ = opcionalReqServ;
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
		System.out.println("Projeto setado: "+projeto.getProjeto().toString());
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
		System.out.println("filtroProj: "+filtroProjeto.toString());
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

	public void setOrcamento(int orcamento) {
		this.orcamento = orcamento;
	}

	public int getOrcamento() {
		return orcamento;
	}

	public void setReqServUsuario(ReqServUsuario reqServUsuario) {
		this.reqServUsuario = reqServUsuario;
	}

	public ReqServUsuario getReqServUsuario() {
		return reqServUsuario;
	}

	public RequisicaoServicoDAO getReqServicoDAO() {
		return reqServicoDAO;
	}

	public void setReqServicoDAO(RequisicaoServicoDAO reqServicoDAO) {
		this.reqServicoDAO = reqServicoDAO;
	}

	public int getClienteCurrentPage() {
		return clienteCurrentPage;
	}

	public void setClienteCurrentPage(int clienteCurrentPage) {
		this.clienteCurrentPage = clienteCurrentPage;
	}

	public int getClientePages() {
		return clientePages;
	}	
	
	public void ultimaPaginaCliente() {
		clientePager.setCurrentPage(clientePager.getMaxPages());
		clienteCurrentPage = clientesPager.getMaxPages();
	}

	public void paginaAnteriorCliente() {
		clientePager.paginaAnterior();
		clienteCurrentPage--;
	}

	public void primeiraPaginaCliente() {
		clientePager.setCurrentPage(0);
		clienteCurrentPage = 0;
		
	}
	public AllClientsPager getClientesPager() {
		return clientesPager;
	}

	public void proximaPaginaCliente() {
		clientePager.proximaPagina();
		clienteCurrentPage++;
	}	

	public List<Cliente> getListaClientes() {
		List<Cliente> retorno = new ArrayList<Cliente>();
		if (resetaFiltroCliente) {
			clientePager = new FilteredNameClientesPager(filtroCliente);
			clientePager.init(10);
			retorno = clientePager.getCurrentResults();
			resetaFiltroCliente = false;
			return retorno;
		}
		if (filtroClienteAnterior.equals(filtroCliente)) {
			if (null != clientePager) {
				retorno = clientePager.getCurrentResults();
				return retorno;
			} else {
				clientePager = new FilteredNameClientesPager(filtroCliente);
				clientePager.init(10);
				retorno = clientePager.getCurrentResults();
				return retorno;
			}
		} else if (!filtroClienteAnterior.equals(filtroCliente)
				&& filtroCliente.length() < 3) {
			filtroClienteAnterior = filtroCliente;
			retorno = clientePager.getCurrentResults();
			return retorno;
		} else {
			filtroClienteAnterior = filtroCliente;
			clientePager = new FilteredNameClientesPager(filtroCliente);
			clientePager.init(10);
			retorno = clientePager.getCurrentResults();
			return retorno;
		}
	}

	public void atualizaFiltro() {
		System.out.println(filtroCliente);
	}	
	
	public void resetaFiltro() {
		filtroCliente = "";
		resetaFiltroCliente = true;
	}	
	
	public String getFiltroClienteAnterior() {
		return filtroClienteAnterior;
	}

	public void setFiltroClienteAnterior(String filtroClienteAnterior) {
		this.filtroClienteAnterior = filtroClienteAnterior;
	}

	public Integer getNumReqSessao() {
		return numReqSessao;
	}

	public void setNumReqSessao(Integer numReqSessao) {
		this.numReqSessao = numReqSessao;
	}

	public boolean isResetaFiltroCliente() {
		return resetaFiltroCliente;
	}

	public void setResetaFiltroCliente(boolean resetaFiltroCliente) {
		this.resetaFiltroCliente = resetaFiltroCliente;
	}

	public FilteredNameClientesPager getClientePager() {
		return clientePager;
	}

	public void setClientePager(FilteredNameClientesPager clientePager) {
		this.clientePager = clientePager;
	}

	public void setClientePages(int clientePages) {
		this.clientePages = clientePages;
	}

	public void setClientesPager(AllClientsPager clientesPager) {
		this.clientesPager = clientesPager;
	}

	public String getPaginacaoFormatadaCliente(){
			int paginacorrente = 0, maxpages = 0;
			if (!(clientePager == null)) {
				paginacorrente = clientePager.getCurrentPage() + 1;
			}
			if (!(clientePager == null)) {
				maxpages = clientePager.getMaxPages() + 1;
			}
			String retorno = "" + paginacorrente + "/" + maxpages;
			return retorno;
		}
	
	public List<Cliente> getTodosClientes() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> retorno = clienteDAO.getListaClientesCadastrados();
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
		if (this.cliente == null || this.cliente.getIdCliente().intValue() == 0) {
			retorno = new ArrayList<Projeto>();
		} else {
			if(this.cliente.getProjetos().isEmpty()){
				retorno = new ArrayList<Projeto>();
				Projeto p = new Projeto();
				p.setCodProj(0);
				p.setProjeto("Cliente não tem projetos");
				retorno.add(p);
			}else{
				retorno = this.cliente.getProjetos();
				System.out.println("cli: "+this.cliente.getFantasia().toString());
			}
		}
		return retorno;
	}

	public List<RequisicaoServico> getListaReqServ() {
		// setar data de 60 dias atras
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
	
	public void cadastrarBalcao(){
		Cliente c = null;		
		Projeto p = null;
		ProjetoDAO projDAO = new ProjetoDAO();
		ClienteDAO cliDAO = new ClienteDAO();
		try {
			//verificar se existe o projeto/cliente
			ClienteDAO cdao = new ClienteDAO();			
			try {
			c = cdao.recupera(clienteBalcao);
			} catch (Exception e) {
				System.out.println("Nao foi encontrado o cliente");							
				clienteBalcao = "";
				projetoBalcao = "";
			}
			
			if (c != null) {
				//pegar projeto
				for (Projeto pb : c.getProjetos()) {
					p = pb;
					System.out.println("Projeto: "+p.getProjeto()+", Cliente: "+c.getFantasia());
				}				
			} else {			
				//criar o cliente
				if (clienteBalcao == null || clienteBalcao == ""){
					c = new Cliente("CLIENTE BALCAO");
					clienteBalcao = c.getFantasia();
				} else {
					c = new Cliente(clienteBalcao.toUpperCase());				
				}
				//indicar que o cliente eh balcao
				c.setBalcao(1);
				//criar um cnpj ficticio
				c.setCpf("0");
				c.setCnpj("0");
				//cadastrar o cliente
				cliDAO.adiciona(c);
				
				// criar o projeto
				if (projetoBalcao == null || projetoBalcao == ""){
					p = new Projeto("GERAL");
					projetoBalcao = p.getProjeto();
				} else {
					p = new Projeto(projetoBalcao.toUpperCase());
				}		
				p.setCliente(c);
				projDAO = new ProjetoDAO();
			
				projDAO.adiciona(p);
			}
			this.projeto = p;	
			
		} catch (Exception e) {
			System.out.println("Nao foi possivel criar o projeto!");
			addGlobalMessage("Nao foi possivel criar o projeto!");
			e.printStackTrace();			
		}

		try {
			p = projDAO.recupera(projeto.getCodProj());
			this.projeto = p;
			this.cadastrar();
			
		} catch (Exception e) {
			addGlobalMessage("Ocorreu um erro ao criar a requisicao!");
			e.printStackTrace();
		}
	}
	
	public void cadastrar() {
		if (projeto.getCodProj() > 0){
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
					} else {
						reqServico.setValorEnt(0.0);
					}
					// inserir valores iniciais
					reqServico.setStatus(0);
					reqServico.setVisivelNf(0);
					reqServico.setVisivelReq(0);
					reqServico.setPago("N");
					
					// adicionar a requisicao de servico se um dos itens obrigatoriso existirem
					if (existeLinha || existeOpcional){
						reqServicoDAO.adiciona(reqServico);
					} else {
						throw new Exception("Requisição de Serviço precisa de uma linha ou de um opcional!"); 
					}
	
					// recuperar a requisicao							
					reqServico = reqServicoDAO.recupera(reqServico.getNumReq());
					
					// registrar usuario que criou a requisicao
					Usuario user = (Usuario) SessionObjectsManager.recuperaObjetoSessao("usuarioSessao");
					System.out.println("Usuario Criador: "+user.getLogin());
					reqServUsuario = new ReqServUsuario(user, reqServico);
					reqServUsuario.setData(hoje);
					reqServico.setRequisicaoUsuario(reqServUsuario);
					
					// adicionar o opcional
					if (existeOpcional) {
						reqServicoDAO.addOpcional(reqServico, opcional,
								quantidadeOpcional);				
					}
					// adicionar a linha
					if (existeLinha) {
						// transformar o nomePapel em Objeto Papel
						PapelDAO papelDAO = new PapelDAO();
						papel = papelDAO.recupera(papel.getCodPapel());
						linhaReqServ.setPapel(papel);
						double valorPapel = 0.0;
						
						//verificaar se eh papel ou lona/adesivo e alterar a impressao
						if (papel.getEhPapel() == "N"){						
							linhaReqServ.setImpressao("COLOR");
						}

						// verificar a cor em uso
						if (linhaReqServ.getImpressao().equalsIgnoreCase("Mono"))
							valorPapel = papel.getImpMono();
						if (linhaReqServ.getImpressao().equalsIgnoreCase("Color"))
							valorPapel = papel.getImpColor();
						if (linhaReqServ.getImpressao().equalsIgnoreCase("Shade"))
							valorPapel = papel.getImpShade();
	
						double valorUnit = (linhaReqServ.getDimensao() * linhaReqServ
								.getFormato()) * valorPapel;
						linhaReqServ.setValorSubUnit(valorUnit);
						linhaReqServ.setValorUnit(valorUnit
								* linhaReqServ.getQuant());
	
						reqServicoDAO.addLinha(reqServico, linhaReqServ);					
					}
					reqServico.setValorTotal(calcularTotal(reqServico));
					reqServico.setValorDesconto(calcularTotal(reqServico));
					reqServicoDAO.altera(reqServico);					
					setValorTotalReq(reqServico.getValorTotal());				
					addGlobalMessage("Inclusão feita com sucesso!");
				} catch (ValidatorException e) {
					addGlobalMessage(e.getMessage());
					e.printStackTrace();
				} catch (Exception e) {
					addGlobalMessage(e.getMessage());
					e.printStackTrace();
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
							PapelDAO papelDAO = new PapelDAO();
							papel = papelDAO.recupera(papel.getCodPapel());						
							linhaReqServ.setPapel(papel);
							double valorPapel = 0.0;
							
							//verificaar se eh papel ou lona/adesivo e alterar a impressao
							if (papel.getEhPapel() == "N"){						
								linhaReqServ.setImpressao("COLOR");
							}
							
							// verificar a cor em uso
							if (linhaReqServ.getImpressao().equalsIgnoreCase("Mono"))
								valorPapel = papel.getImpMono();
							if (linhaReqServ.getImpressao().equalsIgnoreCase("Color"))
								valorPapel = papel.getImpColor();
							if (linhaReqServ.getImpressao().equalsIgnoreCase("Shade"))
								valorPapel = papel.getImpShade();
	
							double valorUnit = (linhaReqServ.getDimensao() * 
												linhaReqServ.getFormato()) * valorPapel;
							linhaReqServ.setValorSubUnit(valorUnit);
							linhaReqServ.setValorUnit(valorUnit	* linhaReqServ.getQuant());
	
							reqServicoDAO.addLinha(temp, linhaReqServ);
						}
						// verificar se existe alteracao na entrega
						if (entrega != null) {
							if (entrega.getLocal() != null){  
								if (temp.getEntrega() == null){
									temp.setEntrega(entrega);									
								} else if (!temp.getEntrega().equals(entrega)){
									temp.setEntrega(entrega);									
								}					
							} else {
								temp.setEntrega(null);
							}
						} else {
							if (!temp.getEntrega().equals(entrega)){
								temp.setEntrega(null);
							}
						}
						
						// verificar se foi alterado o projeto
						Projeto p = this.projeto;
						if (!temp.getProjeto().equals(p)){
							temp.setProjeto(projeto);						
						}			
						
						// registrar usuario que alterou a requisicao
						Usuario user = (Usuario) SessionObjectsManager.recuperaObjetoSessao("usuarioSessao");					 
						System.out.println("Usuario Alterador: "+user.getLogin());
						reqServUsuario = temp.getRequisicaoUsuario();
						if (reqServUsuario == null) {
							// setar data de criacao da requisicao
							java.util.Date data = new java.util.Date();
							java.sql.Date hoje = new java.sql.Date(data.getTime());
							reqServUsuario = new ReqServUsuario(user, reqServico);
							reqServUsuario.setData(hoje);							
						} else {
							reqServUsuario.setUsuarioAlteracao(user);
						}
							
						temp.setRequisicaoUsuario(reqServUsuario);					
						
						temp.setValorTotal(this.calcularTotal(temp));					
						reqServicoDAO.altera(temp);
						reqServico = reqServicoDAO.recupera(temp.getNumReq());
						setValorTotalReq(reqServico.getValorTotal());
						//se o Desconto for igual a zero ou null atribuir valor do total
						if (reqServico.getDesconto() == null || reqServico.getDesconto() == 0){
							reqServico.setValorDesconto(reqServico.getValorTotal());
						}
						usuarioAlterouReqServ(reqServico);
						addGlobalMessage("Atualização feita com sucesso!");
					}
					
				} catch (Exception e) {
					addGlobalMessage(e.getMessage());
					e.printStackTrace();
				}
	
			}
		} else {
			addGlobalMessage("Precisa cadastrar um projeto para o cliente!");
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
		if ((reqServ.getEntrega() != null) && (reqServ.getEntrega().getCodEntrega() != null )){
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
			// remove a linha
			reqServico.getLinhaRequisicao().remove(linhaReqServ);
			reqServicoDAO.altera(reqServico);
			// remover do banco
			reqServicoDAO.removeLinha(linhaReqServ);
			// recalcula os valores			
			reqServico.setValorTotal(calcularTotal(reqServico));
			reqServicoDAO.altera(reqServico);
			// setar o valor do form
			valorTotalReq = reqServico.getValorTotal();
			addGlobalMessage("Linha removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}	

	public void apagarOpcional(){
		try {
			// remove o opcional do objeto
			reqServico.getOpcionais().remove(opcionalReqServ);
			reqServicoDAO.altera(reqServico);
			// remove o opcional do banco
			reqServicoDAO.removeOpcionais(opcionalReqServ.getId());
			// recalcula os valores			
			reqServico.setValorTotal(calcularTotal(reqServico));
			reqServicoDAO.altera(reqServico);
			// setar o valor do form
			valorTotalReq = reqServico.getValorTotal();
			addGlobalMessage("Opcional removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}	
	
	public void apagarEntrega(){
		try {
			//remove a entrega do objeto
			entrega = null;
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
	
	public void getReqServSessao(){
		try {			
			numReqSessao = (Integer) SessionObjectsManager.recuperaObjetoSessao("numReqServ");
			if (numReqSessao != null && numReqSessao > 0){
				reqServico = reqServicoDAO.recupera(numReqSessao);
				projeto = reqServico.getProjeto();
				cliente = projeto.getCliente();
				entrega = reqServico.getEntrega();
				valorTotalReq = reqServico.getValorTotal();
				
				// limpar a memória
				SessionObjectsManager.removeObjetoSessao("numReqServ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}
	
	public void usuarioCriouReqServ(RequisicaoServico r){
		Usuario user = (Usuario) SessionObjectsManager.recuperaObjetoSessao("usuarioSessao");		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lu = usuarioDAO.getListaUsuariosPorLogin(user.getLogin());
		for (Usuario u : lu){
			System.out.println("Usuario: "+u.getLogin());
			reqServicoDAO.registraUsuarioCriador(u, r);
		}				
	}
	
	public void usuarioAlterouReqServ(RequisicaoServico r){
		Usuario user = (Usuario) SessionObjectsManager.recuperaObjetoSessao("usuarioSessao");		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lu = usuarioDAO.getListaUsuariosPorLogin(user.getLogin());
		for (Usuario u : lu){
			System.out.println("Usuario: "+u.getLogin());
			reqServicoDAO.registraUsuarioAlterador(u, r);
		}
	}

	public double getGratificacao() {
		Usuario user = (Usuario) SessionObjectsManager.recuperaObjetoSessao("usuarioSessao");
		Date dataInicio = Utils.getDataInicioMesCorrente();
		Date dataFinal = Utils.getDataFinalMesCorrente();
		double gratificacao = reqServicoDAO.getGratificacao(user.getLogin(),dataInicio,dataFinal);
		System.out.println("Usuario: "+user.getLogin()+" tem gratificacao de "+gratificacao);
				
		return gratificacao;
	}

	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
