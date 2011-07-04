package br.seploc.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import br.seploc.dao.exceptions.FieldNotNullException;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.dao.exceptions.UniqueKeyException;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.FoneCliente;
import br.seploc.util.GenericDAO;

public class ClienteDAO extends GenericDAO<Cliente, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6405992181071838426L;

	@Override
	public void adiciona(Cliente t) throws Exception {
		em.getTransaction().begin();
		try{
		ajustaPojo(t);
		if (t.getFoneCliente() == null) {
			em.persist(t);
		} else {
			FoneCliente fc = t.getFoneCliente();
			t.setFoneCliente(null);
			fc.setCliente(null);
			em.persist(t);
			t.setFoneCliente(fc);
			fc.setCliente(t);
			fc.setIntClientId(t.getIdCliente());
			em.merge(t);

		}}catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
		em.getTransaction().commit();

	}

	@Override
	public Cliente altera(Cliente t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public Cliente recupera(Integer id) {
		Cliente cliente = em.find(Cliente.class, id);
		return cliente;
	}
	
	public Cliente recupera(String fantasia) {		
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cliente.RetornaClientePorFantasia").setParameter(
				"nome", fantasia);
		em.getTransaction().commit();
		return (Cliente) q.getSingleResult();
	}	

	@Override
	public Cliente remove(Integer id) throws Exception {
		em.getTransaction().begin();
		Cliente cliente = em.find(Cliente.class, id);
		if (cliente == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Cliente Inexistente");
		} else {
			if (verificaFilhos(id)) {
				em.getTransaction().rollback();
				throw new ParentDeleteException(
						"Cliente tem registros depedentes...");
			} else {
				em.remove(cliente);
			}
		}
		em.getTransaction().commit();

		return cliente;
	}

	public Cliente remove(Cliente c) throws Exception {
		remove(c.getIdCliente());
		return c;
	}

	@Override
	protected boolean verificaFilhos(Integer id) throws ParentDeleteException {
		Number contagemProjetos = 0;

		Query q = em.createQuery(
				"SELECT count(p.cliente) FROM br.seploc.pojos.Projeto p"
						+ " where p.cliente.idCliente = :clienteID").setParameter(
				            "clienteID", id);
		contagemProjetos = (Number) q.getSingleResult();
		if (contagemProjetos.intValue() != 0)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getLista() {
//		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cliente.RetornaClientes");
//		em.getTransaction().commit();
		return (List<Cliente>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getListaClientesPorNomeFantasia(String nome){
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cliente.BuscaClientesPorFantasia").setParameter(
				 "nome", "%" + nome + "%");
		em.getTransaction().commit();
		return (List<Cliente>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> getListaCache() {
		Query q = em.createNamedQuery("Cliente.RetornaClientes");
		return (List<Cliente>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> getListaClientesCadastrados() {
		Query q = em.createNamedQuery("Cliente.BuscaClientesCadastrados");
		return (List<Cliente>) q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Cliente> getClientesPorNome(String nome) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cliente.BuscaClientes").setParameter(
				"nome", "%" + nome + "%");
		em.getTransaction().commit();
		return (List<Cliente>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> getClientesPorFantasia(String nome) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cliente.BuscaClientesPorFantasia").setParameter(
				"nome", "%" + nome + "%");
		em.getTransaction().commit();
		return (List<Cliente>) q.getResultList();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Cliente> getClientesPorCPF(String CPF) {
		boolean flag = false;
		if(!em.getTransaction().isActive()){
			em.getTransaction().begin();
			flag = true;
		}
		Query q = em.createNamedQuery("Cliente.BuscaClientesPorCPF").setParameter(
				"CPF", "%" + CPF + "%");
		if(flag){
			em.getTransaction().commit();
		}
		return (List<Cliente>) q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Cliente> getClientesPorCNPJ(String CNPJ) {
		boolean flag = false;
		if(!em.getTransaction().isActive()){
			em.getTransaction().begin();
			flag = true;
		}
		Query q = em.createNamedQuery("Cliente.BuscaClientesPorCNPJ").setParameter(
				"CNPJ", "%" + CNPJ + "%");
		if(flag){
			em.getTransaction().commit();
		}
		return (List<Cliente>) q.getResultList();
	}
	@Override
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
			if (!(c.getCpf() == null) || !c.getCpf().trim().equals("") || !c.getCpf().trim().equals("0")) {
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
	}

	@SuppressWarnings("unchecked")	
	public List<String> getNomesCliente() {
		em.getTransaction().begin();
		Query q = em.createNativeQuery("select vcrFantasia from tbl_clientes");
		em.getTransaction().commit();
		return (List<String>) q.getResultList();
	}	
}
