package br.seploc.dao.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.seploc.dao.ClienteDAO;
import br.seploc.dao.ProjetoDAO;
import br.seploc.dao.exceptions.FieldNotNullException;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.Projeto;

public class ProjetoDAOTest {

	@Test
	public final void testAdicionaProjeto()  {
		ProjetoDAO dao = new ProjetoDAO();
		ClienteDAO daoCliente = new ClienteDAO();
		Projeto p = new Projeto();
		p.setProjeto("Projeto Teste");
		p.setCliente(daoCliente.recupera(31));
		
		try {
			dao.adiciona(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p = null;
		p = ((List<Projeto>)dao.getListaProjetosPorNome("Projeto Teste")).get(0);
		Assert.assertNotNull(p);
		Assert.assertTrue(p.getProjeto().equals("Projeto Teste"));
		Assert.assertTrue(p.getCliente().getIdCliente().intValue() == 31);
		
		System.out.println(p);
	}
	
	@Test
	public final void testAdicionaProjetoFieldNotNullException()  {
		ProjetoDAO dao = new ProjetoDAO();
		Projeto p = new Projeto();
		p.setProjeto("Projeto Teste");
//		p.setCliente(daoCliente.recupera(31));
		
		try {
			dao.adiciona(p);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof FieldNotNullException );
			System.err.println(e.getMessage());
		}
	}

	@Test
	public final void testAlteraProjeto() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRecuperaInteger() throws Exception {
		ProjetoDAO dao = new ProjetoDAO();
		Projeto p = dao.recupera(100);
		Assert.assertNotNull(p);
		Assert.assertTrue(p.getProjeto().equals("Projeto Inicial"));
		Assert.assertNotNull(p.getCliente());
		Assert.assertTrue(p.getCliente().getFantasia().equals("Empresa Fantasia2"));
		
		System.out.println(p);
	}

	@Test
	public final void testRemoveInteger() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRemoveProjeto() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetLista() {
		ProjetoDAO dao = new ProjetoDAO();
		List<Projeto> p = dao.getLista();

		Assert.assertNotNull(p);
		Assert.assertTrue(p.size() == 5);

		for (Projeto pp : p) {
			System.out.println(pp);
		}
	}

	@Test
	public final void testGetListaProjetosPorNome() {
		ProjetoDAO dao = new ProjetoDAO();
		List<Projeto> lista = dao.getListaProjetosPorNome("Inicial");
		Projeto p = lista.get(0);
		
		Assert.assertNotNull(lista);
		Assert.assertTrue(lista.size() == 2);
		Assert.assertNotNull(p);

		for (Projeto pp : lista) {
			System.out.println(pp);
		}
	}

	@Test
	public final void testGetListaProjetoPorCliente() {
		ProjetoDAO dao = new ProjetoDAO();
		ClienteDAO daoCliente = new ClienteDAO();
		Cliente c = daoCliente.recupera(23);
		List<Projeto> lista = dao.getListaProjetoPorCliente(c);
		Projeto p = lista.get(0);
		
		Assert.assertNotNull(lista);
		Assert.assertTrue(lista.size() == 2);
		Assert.assertNotNull(p);

		for (Projeto pp : lista) {
			System.out.println(pp);
		}
	}

}

//Carga para os testes:
/*
 * 
 * INSERT INTO  `seploc2`.`tbl_projetos` (

`intCodProj` ,
`intClienteId` ,
`vcrProjeto` ,
`tspVersao`
)
VALUES (
'100',  '24',  'Projeto Inicial', 
CURRENT_TIMESTAMP
), (
'101',  '24',  'Projeto Inicial 2', 
CURRENT_TIMESTAMP
), (
'102',  '23',  'Projeto de Coisas', 
CURRENT_TIMESTAMP
), (
'103',  '23',  'Projeto de Coisas 2', 
CURRENT_TIMESTAMP
), (
'104',  '2',  'ddddd', 
CURRENT_TIMESTAMP
)
 */
