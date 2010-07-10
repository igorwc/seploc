package br.seploc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.FieldNotNullException;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.FoneCliente;
import br.seploc.util.GenericDAO;

public class ClienteDAO extends GenericDAO<Cliente, Integer> {

	private Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Conectando ao banco");
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/seploc2", "root", "");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	private void executaSQL(String sql) {
		
		try {
			Connection connection = getConnection();
			Statement stmt = connection.createStatement();
			// executa
			stmt.execute(sql);
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Entrou aqui");
			throw new RuntimeException(e);
			
		}
	}
	@Override
	public synchronized void  adiciona(Cliente t) throws Exception {
		em.getTransaction().begin();
		ajustaPojo(t);
		em.persist(t);
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

	@Override
	public Cliente remove(Integer id) throws Exception {
		em.getTransaction().begin();
		Cliente cliente = em.find(Cliente.class, id);
		if (cliente == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Cliente Inexistente");
		} else {
			em.remove(cliente);
		}
		em.getTransaction().commit();

		return cliente;
	}

	@Override
	protected boolean verificaFilhos(Integer id) throws ParentDeleteException {
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getLista() {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Cliente.RetornaClientes");
		em.getTransaction().commit();
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
	@Override
	protected void ajustaPojo(Cliente c) throws FieldNotNullException {
		if (c.getFantasia() == null)
			throw new FieldNotNullException("Nome Fantasia não pode ser nulo");
		if (c.getRazao() == null)
			c.setRazao(c.getFantasia());
		if(c.getBalcao() == null)
			c.setBalcao(0);
	}
}
