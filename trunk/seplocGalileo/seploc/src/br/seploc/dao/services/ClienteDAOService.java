package br.seploc.dao.services;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.ClienteDAO;
import br.seploc.dao.exceptions.FieldNotNullException;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.dao.exceptions.UniqueKeyException;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.FoneCliente;
import br.seploc.util.GenericDAO;
import br.seploc.util.GenericService;

@SuppressWarnings("unchecked")
public class ClienteDAOService extends GenericService implements Serializable {

	private static final long serialVersionUID = 6405992181071838426L;

	private ClienteDAO clienteDAO;

	public ClienteDAOService() {
		clienteDAO = new ClienteDAO();
	}

	public void adiciona(Cliente t) throws Exception {
		em.getTransaction().begin();
		try {
			clienteDAO.adiciona(t);
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
		em.getTransaction().commit();

	}

	public Cliente altera(Cliente t) {
		em.getTransaction().begin();
		clienteDAO.altera(t);
		em.getTransaction().commit();
		return t;
	}

	public Cliente recupera(Integer id) {
		Cliente cliente = clienteDAO.recupera(id);
		return cliente;
	}

	public Cliente recupera(String fantasia) {
		em.getTransaction().begin();
		Cliente c = clienteDAO.recupera(fantasia);
		em.getTransaction().commit();
		return c;
	}

	public Cliente remove(Integer id) throws Exception {
		Cliente cliente = null;
		try {
			em.getTransaction().begin();
			cliente = clienteDAO.remove(id);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().commit();
			throw e;
		}
		return cliente;
	}

	public Cliente remove(Cliente c) throws Exception {
		remove(c.getIdCliente());
		return c;
	}

	/*
	 * protected boolean verificaFilhos(Integer id) throws ParentDeleteException
	 * { Number contagemProjetos = 0;
	 * 
	 * Query q = em.createQuery(
	 * "SELECT count(p.cliente) FROM br.seploc.pojos.Projeto p" +
	 * " where p.cliente.idCliente = :clienteID").setParameter( "clienteID",
	 * id); contagemProjetos = (Number) q.getSingleResult(); if
	 * (contagemProjetos.intValue() != 0) return true; return false; }
	 */

	public List<Cliente> getLista() {
		em.getTransaction().begin();
		List<Cliente> lista = clienteDAO.getLista();
		em.getTransaction().commit();
		return lista;
	}

	public List<Cliente> getListaClientesPorNomeFantasia(String nome) {
		em.getTransaction().begin();
		List<Cliente> lista = clienteDAO.getListaClientesPorNomeFantasia(nome);
		em.getTransaction().commit();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getListaCache() {
		em.getTransaction().begin();
		List<Cliente> lista = clienteDAO.getListaCache();
		em.getTransaction().commit();
		return lista;
	}

	public List<Cliente> getListaClientesCadastrados() {
		em.getTransaction().begin();
		List<Cliente> lista = clienteDAO.getListaClientesCadastrados();
		em.getTransaction().commit();
		return lista;
	}

	public List<Cliente> getClientesPorNome(String nome) {
		em.getTransaction().begin();
		List<Cliente> lista = clienteDAO.getClientesPorNome(nome);
		em.getTransaction().commit();
		return lista;
	}

	public List<Cliente> getClientesPorFantasia(String nome) {
		em.getTransaction().begin();
		List<Cliente> lista = clienteDAO.getClientesPorFantasia(nome);
		em.getTransaction().commit();
		return lista;
	}

	public List<Cliente> getClientesPorCPF(String CPF) {
		em.getTransaction().begin();
		List<Cliente> lista = clienteDAO.getClientesPorCPF(CPF);
		em.getTransaction().commit();
		 
		return lista;
	}

	public List<Cliente> getClientesPorCNPJ(String CNPJ) {
		em.getTransaction().begin();
		List<Cliente> lista = clienteDAO.getClientesPorCNPJ(CNPJ);
		em.getTransaction().commit();
		 
		return lista;
		 
	}

	/*@Override
	protected void ajustaPojo(Cliente c) throws FieldNotNullException,
			UniqueKeyException {
		if (c.getFantasia() == null)
			throw new FieldNotNullException("Nome Fantasia não pode ser nulo");
		if (c.getRazao() == null)
			c.setRazao(c.getFantasia());
		if (c.getBalcao() == null)
			c.setBalcao(0);
		if ((c.getCpf() == null || c.getCpf().trim().equals(""))
				&& (c.getCnpj() == null || c.getCnpj().trim().equals(""))) {
			throw new FieldNotNullException("O CPF/CNPJ deve ser preenchido");
		}
		if (c.getCnpj() == null || c.getCnpj().trim().equals("")) {
			if (!(c.getCpf() == null) || !c.getCpf().trim().equals("")
					|| !c.getCpf().trim().equals("0")) {
				List<Cliente> list = getClientesPorCPF(c.getCpf());
				if (list != null && !list.isEmpty()) {
					throw new UniqueKeyException("CPF Já cadastrado!");
				}
			}
		} else {
			if (!(c.getCnpj() == null) || !c.getCnpj().trim().equals("")) {
				if (c.getCnpj() != "0") {
					List<Cliente> list = getClientesPorCNPJ(c.getCnpj());
					if (list != null && !list.isEmpty()) {
						throw new UniqueKeyException("CNPJ Já cadastrado!");
					}
				}
			}
		}
	}*/

	public List<String> getNomesCliente() {
		em.getTransaction().begin();
		List<String> lista = clienteDAO.getNomesCliente();
		em.getTransaction().commit();
		 
		return lista;
		 
	}
}
