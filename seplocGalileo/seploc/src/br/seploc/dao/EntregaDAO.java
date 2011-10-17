package br.seploc.dao;

import java.util.List;

import javax.persistence.Query;

import br.seploc.dao.exceptions.FieldNotNullException;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.Entrega;
import br.seploc.util.GenericDAO;

public class EntregaDAO extends GenericDAO<Entrega,Integer> {

	@Override
	public void adiciona(Entrega t) throws FieldNotNullException {
		em.getTransaction().begin();
		ajustaPojo(t);
		em.persist(t);
		em.getTransaction().commit();

	}

	@Override
	public Entrega altera(Entrega t) throws FieldNotNullException {
		em.getTransaction().begin();
		ajustaPojo(t);
		em.merge(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public Entrega recupera(Integer id) {
		Entrega entrega = em.find(Entrega.class, id);
		return entrega;
	}

	@Override
	public Entrega remove(Integer id) throws Exception {
		em.getTransaction().begin();
		Entrega entrega = em.find(Entrega.class, id);
		if (entrega == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Regiao de Entrega Inexistente");
		} else {
			if (verificaFilhos(id)) {
				em.getTransaction().rollback();
				throw new ParentDeleteException(
						"O registro de entrega tem registros depedentes...");
			} else {
				em.remove(entrega);
			}
		}
		em.getTransaction().commit();

		return entrega;
	}

	@Override
	protected boolean verificaFilhos(Integer id) throws Exception {
		Number contagemClientes = 0;
		Number contagemRequisicoes = 0;

		Query q = em.createQuery(
				"SELECT count(c.entregaPadrao) FROM br.seploc.pojos.Cliente c"
						+ " where c.entregaPadrao.codEntrega = :EntregaID").setParameter(
				            "EntregaID", id);
		contagemClientes = (Number) q.getSingleResult();
//		System.out.println("Clientes: "+contagemClientes.intValue());
		q = em.createQuery(
				"SELECT count(rs.entrega) FROM br.seploc.pojos.RequisicaoServico rs"
						+ " where rs.entrega.codEntrega = :EntregaID").setParameter(
				            "EntregaID", id);
		contagemRequisicoes = (Number) q.getSingleResult();
//		System.out.println("Entregas: "+contagemRequisicoes.intValue());
		if (contagemClientes.intValue() != 0 || contagemRequisicoes.intValue() != 0)
			return true;
		return false;
	}

	@Override
	protected void ajustaPojo(Entrega pojo) throws FieldNotNullException {
		if(pojo.getLocal() == null){
			throw new FieldNotNullException("Local de Entrega nao pode ser nulo");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entrega> getLista() {
//		em.getTransaction().begin();
		Query q = em.createNamedQuery("Entrega.RetornaEntregas");
//		em.getTransaction().commit();
		return (List<Entrega>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Entrega> getEntregasPorLocal(String nome) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Entrega.BuscaEntregas").setParameter(
				"nome", "%" + nome + "%");
		em.getTransaction().commit();
		return (List<Entrega>) q.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<String> getLocaisEntrega() {
		em.getTransaction().begin();
		Query q = em.createNativeQuery("select vcrLocal from tbl_entrega");
		em.getTransaction().commit();
		return (List<String>) q.getResultList();
	}

	public boolean existe(Entrega entrega) {
		boolean retorno = false;
		Number count = 0;		
		Query q = em.createNativeQuery(" SELECT count(1) "
				+ "FROM tbl_entrega e "
				+ "WHERE e.vcrLocal = :nome")
		.setParameter("nome", entrega.getLocal());
		
		count = (Number) q.getSingleResult();
		// se retornar diferente de zero setar para verdadeiro
		if (count.intValue() != 0) {
			retorno = true;
		}
		
		return retorno;
	}	
}
