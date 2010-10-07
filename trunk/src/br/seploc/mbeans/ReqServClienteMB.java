package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
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
import br.seploc.pojos.RequisicaoServico;
import br.seploc.dao.ClienteDAO;
import br.seploc.dao.EntregaDAO;
import br.seploc.dao.OpcionaisReqServDAO;
import br.seploc.dao.PapelDAO;
import br.seploc.dao.ProjetoDAO;
import br.seploc.dao.RequisicaoServicoDAO;

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
	private String nomePapel;
	private String filtroOpcional;
	private String filtroEntrega;
	private String filtroProjeto;
	private String filtroCliente;
	private int quantidadeOpcional;
	private int numReqSelecionado;

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
		ClienteDAO clienteDAO = new ClienteDAO();
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

	public void setNumReqSelecionado(int numReqSelecionado) {
		this.numReqSelecionado = numReqSelecionado;
	}

	public int getNumReqSelecionado() {
		return numReqSelecionado;
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
		// List<Projeto> retorno = new ArrayList<Projeto>();
		// if (cliente.getIdCliente().intValue() != 0) {
		// ProjetoDAO projetoDAO = new ProjetoDAO();
		// retorno = projetoDAO.getListaProjetoPorCliente(cliente);
		// }
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

	public List<RequisicaoServico> getLista() {
		List<RequisicaoServico> retorno = reqServicoDAO.getLista();
		return retorno;
	}

	public List<RequisicaoServico> getTodasReqServPorCliente(Cliente cliente) {
		List<RequisicaoServico> retorno = reqServicoDAO
				.getListaPorPorjeto(cliente);

		return retorno;
	}

	public void cadastrar() {
		if (reqServico.getNumReq() == null || reqServico.getNumReq() == 0) {
			try {
				reqServico.setValorTotal(0.0);
				// setar data de cria��o da requisi��o
				java.util.Date data = new java.util.Date();
				java.sql.Date hoje = new java.sql.Date(data.getTime());
				reqServico.setData(hoje);
				// adicionar projeto
				reqServico.setProjeto(projeto);
				// adicionar a entrega
				if (entrega.getCodEntrega() >= 1) {
					reqServico.setEntrega(entrega);
					reqServico.setValorEnt(entrega.getPreco());
					reqServico.setValorTotal(reqServico.getValorTotal()
							+ entrega.getPreco());
				}
				// adicionar a requisi��o de servi�o
				reqServicoDAO.adiciona(reqServico);

				// recuperar a requisicao
				reqServico = reqServicoDAO.recupera(reqServico.getNumReq());

				// adicionar o opcional
				if (quantidadeOpcional >= 1) {
					reqServicoDAO.addOpcional(reqServico, opcional,
							quantidadeOpcional);
					reqServico.setValorTotal(reqServico.getValorTotal()
							+ (opcional.getValorItem() * quantidadeOpcional));
				}
				// adicionar a linha
				if (linhaReqServ.getQuant() >= 1) {
					// transformar o nomePapel em Objeto Papel
					papel = this.converterToPapel(this.nomePapel);
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
				reqServicoDAO.altera(reqServico);
				addGlobalMessage("Inclus�o feita com sucesso!");
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
						temp.setValorTotal(temp.getValorTotal()
								+ (opcional.getValorItem() * quantidadeOpcional));
					}
					// adicionar a linha
					if (linhaReqServ.getQuant() >= 1) {
						// transformar o nomePapel em Objeto Papel
						papel = this.converterToPapel(this.nomePapel);
						linhaReqServ.setPapel(papel);
						double valorPapel = 0.0;
						// verificar a cor em uso
						if (linhaReqServ.getImpressao()
								.equalsIgnoreCase("Mono"))
							valorPapel = papel.getImpMono();
						if (linhaReqServ.getImpressao().equalsIgnoreCase(
								"Color"))
							valorPapel = papel.getImpColor();
						if (linhaReqServ.getImpressao().equalsIgnoreCase(
								"Shade"))
							valorPapel = papel.getImpShade();

						double valorUnit = (linhaReqServ.getDimensao() * linhaReqServ
								.getFormato()) + valorPapel;
						linhaReqServ.setValorSubUnit(valorUnit);
						linhaReqServ.setValorUnit(valorUnit
								* linhaReqServ.getQuant());

						reqServicoDAO.addLinha(temp, linhaReqServ);

						temp.setValorTotal(temp.getValorTotal()
								+ linhaReqServ.getValorUnit());
					}
					reqServicoDAO.altera(temp);
					temp.setProjeto(reqServico.getProjeto());
					addGlobalMessage("Atualiza��o feita com sucesso!");
				}
			} catch (Exception e) {
				addGlobalMessage(e.getMessage());
			}

		}
		this.limparLinhaOpcional();
	}

	public void editar() {
		try {
			reqServico = reqServicoDAO.recupera(reqServico.getNumReq());

		} catch (Exception e) {
			e.printStackTrace();
			addGlobalMessage(e.getMessage());
		}
	}

	public void apagar() {
		try {
			reqServicoDAO.remove(reqServico.getNumReq());
			addGlobalMessage("Exclu�do com sucesso!");
		} catch (Exception e) {
			addGlobalMessage(e.getMessage());
		}
		this.limpar();
	}

	public void limpar() {
		cliente = new Cliente();
		cliente.setIdCliente(0);
		opcional = new OpcionaisReqServ();
		entrega = new Entrega();
		papel = new Papel();
		projeto = new Projeto();
		linhaReqServ = new LinhaRequisicao();
		reqServico = new RequisicaoServico();
		reqServicoDAO = new RequisicaoServicoDAO();
	}

	public void limparLinhaOpcional() {
		opcional = new OpcionaisReqServ();
		papel = new Papel();
		nomePapel = "";
		quantidadeOpcional = 0;
		linhaReqServ = new LinhaRequisicao();
	}

	private Papel converterToPapel(String nome) throws ValidatorException,
			Exception {
		Papel papel = new Papel();

		// verificar se o nome do papel informado � v�lido
		if (this.validatePapeis(nome)) {
			PapelDAO papelDAO = new PapelDAO();

			if (papelDAO.getListaPapelPorNome(nome).size() > 1) {
				throw new Exception(
						"Existe mais de um papel como o mesmo nome!");
			} else {
				for (Papel p : papelDAO.getListaPapelPorNome(nome)) {
					papel = p;
				}
			}
		}

		return papel;
	}

	private boolean validatePapeis(String nomePapel) {
		PapelDAO papelDAO = new PapelDAO();
		List<String> papeis = papelDAO.getPapeis();

		String nome = nomePapel.toString();
		boolean flag = false;
		for (String s : papeis) {
			if (s.toUpperCase().startsWith(nome.toUpperCase())) {
				flag = true;
			}
		}
		if (!flag) {
			FacesMessage message = new FacesMessage("Nome Papel Inv�lido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

		return flag;

	}

	public static void addGlobalMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
