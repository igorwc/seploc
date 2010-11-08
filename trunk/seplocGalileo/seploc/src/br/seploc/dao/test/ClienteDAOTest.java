package br.seploc.dao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.seploc.dao.ClienteDAO;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.FoneCliente;

public class ClienteDAOTest {

	@Test
	public final void testAdicionaCliente() throws Exception {
		ClienteDAO dao = new ClienteDAO();
		Cliente c = new Cliente();
		c.setCnpj("123456789-0001/12");
		c.setFantasia("Empresa Fantasia");
		c.setBairro("Bairro Fantastico");
		c.setBalcao(0);
		c.setCep("52060-000");
		c.setCidade("Hellcife");
		c.setEmail("empresa@empresa.com.br");
		c.setEndereco("R. das Creoulas");
		c.setEstado("PE");

		dao.adiciona(c);

		c = null;
		c = dao.recupera(2);
		Assert.assertNotNull(c);
		Assert.assertTrue(c.getCnpj().equals("123456789-0001/12"));
		Assert.assertTrue(c.getCep().equals("52060-000"));
		Assert.assertTrue(c.getRazao().equals("Empresa Fantasia"));
		System.out.println(c);
	}

	@Test
	public final void testAdicionaClienteComTelefone() throws Exception {
		ClienteDAO dao = new ClienteDAO();
		Cliente c = new Cliente();
		FoneCliente fc = new FoneCliente();
		c.setCnpj("123456789-1111/12");
		c.setFantasia("Empresa Fantasia23");
		c.setBairro("Bairro Fantastico2");
		c.setCep("52060-1111");
		c.setCidade("Recifilis");
		c.setEmail("empresa2@empresa2.com.br");
		c.setEndereco("R. das Morenas");
		c.setEstado("PE");

		fc.setCelular("9999-9999");
		fc.setCliente(c);
		fc.setFax("111-11111");
		fc.setFoneComercial("2222-2222");
		fc.setFoneResidencial("3333-3333");
		c.setFoneCliente(fc);
		dao.adiciona(c);

		c = null;
		List<Cliente> lista = dao.getClientesPorNome("Fantasia23");
		Assert.assertNotNull(lista);
		// Assert.assertTrue(lista.size() == 1);
		c = lista.get(0);
		Assert.assertTrue(c.getCnpj().equals("123456789-1111/12"));
		Assert.assertTrue(c.getCep().equals("52060-1111"));
		Assert.assertTrue(c.getRazao().equals("Empresa Fantasia23"));
		Assert.assertNotNull(c.getFoneCliente());
		fc = c.getFoneCliente();
		Assert.assertTrue(fc.getFoneComercial().equals("2222-2222"));
		System.out.println(c);
	}

	@Test
	public final void testAlteraCliente() {

		ClienteDAO dao = new ClienteDAO();
		Cliente c = null;
		List<Cliente> lista = dao.getClientesPorNome("Fantasia23");
		c = lista.get(0);
		c.setBairro("Torre");
		c.getFoneCliente().setCelular("8888-8888");

		dao.altera(c);
		c = null;
		lista = dao.getClientesPorNome("Fantasia23");
		c = lista.get(0);
		Assert.assertTrue(c.getBairro().equals("Torre"));
		Assert.assertTrue(c.getFoneCliente().getCelular().equals("8888-8888"));
	}

	@Test
	public final void testRecuperaInteger() {
		ClienteDAO dao = new ClienteDAO();
		Cliente c = dao.recupera(1);
		System.out.println(c);
	}

	@Test
	public final void testRecuperaString() {
		ClienteDAO dao = new ClienteDAO();
		Cliente c = dao.recupera("Solutiona");
		System.out.println(c);
	}

	@Test
	public final void testRemoveInteger() throws Exception {
		ClienteDAO dao = new ClienteDAO();
		Cliente c = null;
		List<Cliente> lista = dao.getClientesPorNome("Fantasia23");
		c = lista.get(0);
		dao.remove(c.getIdCliente());
		lista = dao.getClientesPorNome("Fantasia23");
		Assert.assertTrue(lista.isEmpty());

	}

	@Test
	public final void testRemoveParentDeleteException() {
		ClienteDAO dao = new ClienteDAO();
		Cliente c = dao.recupera(22);

		try {
			dao.remove(c.getIdCliente());
			c = dao.recupera(22);
			Assert.assertNull(c);

			c = dao.recupera(23);
			dao.remove(c.getIdCliente());
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ParentDeleteException);
		}

	}

	@Test
	public final void testGetLista() throws Exception {
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> lista =  dao.getLista();

		// Assert.assertNotNull(c);
		// Assert.assertTrue(c.size() == 6);

		for (Cliente cc : lista) {
			System.out.println(cc);
		}
		System.out.println("\n\n\n\n\nmeio da execução\n\n\n\n\n");
		lista = dao.getListaCache();
		for (Cliente cc : lista) {
			System.out.println(cc);
		}
		Cliente c = new Cliente();
		c.setCnpj("123456789-0001/12");
		c.setFantasia("Empresa Fantasia");
		c.setBairro("Bairro Fantastico");
		c.setBalcao(0);
		c.setCep("52060-000");
		c.setCidade("Hellcife");
		c.setEmail("empresa@empresa.com.br");
		c.setEndereco("R. das Creoulas");
		c.setEstado("PE");

		dao.adiciona(c);
		System.out.println("\n\n\n\n\nmeio da execução\n\n\n\n\n");
		lista = dao.getListaCache();
		for (Cliente cc : lista) {
			System.out.println(cc);
		}
	}

	@Test
	public final void testGetNomesCliente() {
		ClienteDAO dao = new ClienteDAO();
		List<String> c = dao.getNomesCliente();

		Assert.assertNotNull(c);
		Assert.assertTrue(c.size() == 6);

		for (String cc : c) {
			System.out.println(cc);
		}
	}

	@Test
	public final void testGetClientesPorNome() {
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> c = dao.getClientesPorNome("Wan");

		Assert.assertNotNull(c);
		Assert.assertTrue(c.size() == 1);

		for (Cliente cc : c) {
			System.out.println(cc);
		}

	}

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

	public void executaSQL(String sql) {

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
}
