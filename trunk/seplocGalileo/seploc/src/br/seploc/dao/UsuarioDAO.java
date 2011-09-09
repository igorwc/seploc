package br.seploc.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import br.seploc.dao.exceptions.LoginExistenteException;
import br.seploc.dao.exceptions.ParentDeleteException;
import br.seploc.dao.exceptions.RecordNotFound;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.pojos.Usuario;
import br.seploc.reports.beans.PlotadorBeanGrid;
import br.seploc.util.DesEncrypter;
import br.seploc.util.GenericDAO;

public class UsuarioDAO extends GenericDAO<Usuario, Integer> {

	@Override
	public void adiciona(Usuario t) throws LoginExistenteException {
		em.getTransaction().begin();
		Usuario temp = getUsuarioPorLogin(t.getLogin());
		if (temp == null ) {
			em.persist(t);
			em.getTransaction().commit();
		} else {
			em.getTransaction().rollback();
			throw new LoginExistenteException("Login ja em uso");
		}		
	}

	@Override
	public Usuario altera(Usuario t) throws LoginExistenteException {
		em.getTransaction().begin();		
			em.merge(t);
			em.getTransaction().commit();
		return t;
	}

	@Override
	public Usuario recupera(Integer id) {
		Usuario usuario = em.find(Usuario.class, id);
		return usuario;
	}

	@Override
	public Usuario remove(Integer id) throws Exception {
		em.getTransaction().begin();
		Usuario usuario = em.find(Usuario.class, id);
		if (usuario == null) {
			em.getTransaction().rollback();
			throw new RecordNotFound("Usuario Inexistente");
		} else {
			usuario.setAtivo('N');
			em.merge(usuario);

/*
 * comentado pois nao sera mais removido e sim alterado o atributo Ativo para NAO
			if (verificaFilhos(id)) {
				em.getTransaction().rollback();
				throw new ParentDeleteException(
						"Usuario tem registros depedentes...");
			} else {
				em.remove(usuario);
			}
*/
		}
		em.getTransaction().commit();

		return usuario;
	}

	@Override
	protected boolean verificaFilhos(Integer id) throws ParentDeleteException {
		System.out.println("verificaFilhos");
		 Query q = em.createQuery(
		 "SELECT count(rsu.usuario) FROM br.seploc.pojos.ReqServUsuario rsu"
		 + " where rsu.usuario.intCodUsr = :id").setParameter(
		 "id", id);
		 int contagemReqUsuario = Integer.parseInt(q.getSingleResult().toString());
		 if (contagemReqUsuario != 0) return true;
		return false;
	}
	
	protected boolean existeLogin(String login) {
		boolean existe = false;
		
		if (this.getListaUsuariosPorLogin(login).size() > 0){
			existe = true;
		}
		
		return existe;
	}	

	@SuppressWarnings("unchecked")
	public List<Usuario> getLista() {
		boolean flag = em.getTransaction().isActive();
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RetornaUsuarios");
		if (!flag) {
			em.getTransaction().commit();
		}
		return (List<Usuario>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getListaUsuariosPorGrupo(Integer codGrupo) {
		boolean flag = em.getTransaction().isActive();
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RetornaUsuariosPorGrupo")
				.setParameter("grupo", codGrupo.intValue());
		if (!flag) {
			em.getTransaction().commit();
		}
		return (List<Usuario>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getListaUsuariosPorNome(String nome) {
		boolean flag = em.getTransaction().isActive();
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RetornaUsuariosPorNome")
				.setParameter("nome", "%" + nome + "%");
		if (!flag) {
			em.getTransaction().commit();
		}
		return (List<Usuario>) q.getResultList();
	}

	public Usuario getUsuariosPorID(int ID) {
		boolean flag = em.getTransaction().isActive();
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RetornaUsuariosPorID")
				.setParameter("id", ID);
		if (!flag) {
			em.getTransaction().commit();
		}
		return (Usuario) q.getSingleResult();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getListaUsuariosPorLogin(String login) {
		boolean flag = em.getTransaction().isActive();
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RetornaUsuariosPorLogin")
				.setParameter("login", "%" + login + "%");
		if (!flag) {
			em.getTransaction().commit();
		}
		return (List<Usuario>) q.getResultList();
	}
	
	public Usuario getUsuarioPorLogin(String login) {
		boolean flag = em.getTransaction().isActive();
		Usuario retorno = null;
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RetornaUsuarioPorLogin")
				.setParameter("login",  login  );
		if (!flag) {
			em.getTransaction().commit();
		}
		try {			
			retorno = (Usuario) q.getSingleResult();
		} catch (Exception e) {
			retorno = null;
		}
		return  retorno;		
	}

	public Usuario getUsuarioPorCpf(String cpf) {
		boolean flag = em.getTransaction().isActive();
		Usuario retorno = null;
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RetornaUsuarioPorCpf")
				.setParameter("cpf",  cpf  );
		if (!flag) {
			em.getTransaction().commit();
		}
		try {
			retorno = (Usuario) q.getSingleResult();
		} catch (Exception e) {
			retorno = null;
		}
		return  retorno;
	}
	public Usuario getUsuarioCriadorPorReqServ(Integer reqNum) {
		boolean flag = em.getTransaction().isActive();
		Usuario retorno = null;
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RecuperaCriadorReqServ")                                   
				.setParameter("reqNum",  reqNum  );
		try {
			retorno = (Usuario) q.getResultList().get(0);
		} catch (Exception e) {
			retorno = null;
		}
		if (!flag) {
			em.getTransaction().commit();
		}
		
		return  retorno;
	}
	
	public Usuario getUsuarioAlteradorPorReqServ(Integer reqNum) {
		boolean flag = em.getTransaction().isActive();
		Usuario retorno = null;
		if (!flag) {
			em.getTransaction().begin();
		}
		Query q = em.createNamedQuery("Usuario.RecuperaUserUltimaReqServ")                                   
				.setParameter("reqNum",  reqNum  );
		try {
			retorno =  (Usuario) q.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			retorno = null;
		}
		if (!flag) {
			em.getTransaction().commit();
		}
		
		return  retorno;
	}
	public List<Usuario> getListaRequisicoesPorUsuario() {
		// TODO Implementar
		// em.getTransaction().begin();
		// Query q =
		// em.createNamedQuery("Usuario.RetornaRequisicoesPorUsuario");
		// em.getTransaction().commit();
		// return (List<Usuario>) q.getResultList();
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public List<Usuario> getListaUsuarioSemGrupo(){		
		List<Usuario> retorno = new ArrayList<Usuario>();
		try{			
			em.getTransaction().begin();
			Query q = em.createNamedQuery("Usuario.RetornaUsuariosSemGrupo");
			em.getTransaction().commit();
			retorno = (List<Usuario>) q.getResultList();
		} catch (Exception e) {
			e.getMessage();			
		}
		return retorno;
	}
	
	public Usuario recupera (String login, String senha){
		boolean flag = false;
		Usuario retorno = null;
		if (!em.getTransaction().isActive()) {
			flag = true;
			em.getTransaction().begin();
		} 
		DesEncrypter encrypter = new DesEncrypter();
		System.out.println("Senha Recebida: "+ senha);
		System.out.println("Senha Encriptada: "+ encrypter.encrypt(senha));
		
		Query q = em.createNamedQuery("Usuario.RecuperaLogin").setParameter(1, login).setParameter(2, encrypter.encrypt(senha));

		try {
			retorno = (Usuario)q.getSingleResult();
			if (flag) {
				em.getTransaction().commit();
			}
			return retorno;
			
		} catch (Exception e) {
			if (flag) {
				em.getTransaction().rollback();
			}
			return null;
		}
		
	}
	
	public List<PlotadorBeanGrid> getListaPlotadoresGrid(Date dataInicio,
			Date dataFim) {
		List<Usuario> listaPlotadores = this.getListaPlotadores();
		List<PlotadorBeanGrid> listaOrdenada = new ArrayList<PlotadorBeanGrid>();
		for (int i = listaPlotadores.size() - 1; i >= 0; i--) {
			Usuario p = listaPlotadores.get(i);
			Integer resultadoQtd = this.getQuantidadeRequisicoesPlotador(p.getId().intValue(), dataInicio, dataFim);
			Double  resultadoVlr = this.getValorTotalRequisicoesPlotador(p.getId().intValue(), dataInicio, dataFim);
			Double  resultadoGra = this.getValorGratificacaoPlotador(p.getId().intValue(), dataInicio, dataFim);
			listaOrdenada.add(new PlotadorBeanGrid(p.getNome(), resultadoQtd, p.getId(), resultadoVlr, resultadoGra));
		}
		Collections.sort(listaOrdenada);
		int i = 1;
		for (PlotadorBeanGrid pb : listaOrdenada) {
			pb.setSeq(i++);
		}
		return listaOrdenada;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getListaPlotadores(){
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Usuario.RetornaUsuarios");
		em.getTransaction().commit();
		return (List<Usuario>) q.getResultList();		
	}
	
	public Integer getQuantidadeRequisicoesPlotador(Integer id, Date dataInicio, Date dataFim) {
		em.getTransaction().begin();
		Number contagemReqServUsuario = 0;

		Query q = em.createQuery(
				"SELECT count(sm) FROM br.seploc.pojos.ReqServUsuario sm"
						+ " where sm.usuario.id = :plotadorID and "
						+ "sm.data between :dataInicio and " 
						+ "  :dataFim  " 
						+ " and sm.reqServico.numReq in (SELECT s.numReq from br.seploc.pojos.RequisicaoServico s )" )
						.setParameter(
				            "plotadorID", id)
				        .setParameter(
				            "dataInicio", dataInicio,TemporalType.DATE)
				        .setParameter(
				            "dataFim", dataFim,TemporalType.DATE);
		contagemReqServUsuario = (Number) q.getSingleResult();
		em.getTransaction().commit();
		return contagemReqServUsuario.intValue();
	}	
	
	public Double getValorTotalRequisicoesPlotador(Integer id, Date dataInicio, Date dataFim) {
		em.getTransaction().begin();
		Double totalReqServUsuario = 0.0;

		Query q = em.createQuery(
				"SELECT sum(rs.valorDesconto) FROM br.seploc.pojos.RequisicaoServico rs"
					+ " where rs.numReq in (SELECT ru.numReqServ FROM br.seploc.pojos.ReqServUsuario ru " 
					+ "                      WHERE ru.usuario.id = :plotadorID) and " 
					+ " rs.data between :dataInicio and " 
					+ "  :dataFim  " )
						.setParameter(
				            "plotadorID", id)
				        .setParameter(
				            "dataInicio", dataInicio,TemporalType.DATE)
				        .setParameter(
				            "dataFim", dataFim,TemporalType.DATE);
		totalReqServUsuario = (Double) q.getSingleResult();
		em.getTransaction().commit();
		// caso o valor seja nulo atribuir valor padrao
		if (totalReqServUsuario == null) totalReqServUsuario = 0.0;
		return totalReqServUsuario;
	}	
	
	public Double getValorGratificacaoPlotador(Integer id, Date dataInicio, Date dataFim){
		double retorno;
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		Usuario u = this.getUsuariosPorID(id);  
		java.sql.Date dataI = new java.sql.Date(dataInicio.getTime());
		java.sql.Date dataF = new java.sql.Date(dataFim.getTime());
		retorno = dao.getGratificacao(u.getLogin(), dataI, dataF);
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<RequisicaoServico> getListaReqServPorPlotador(Integer plotadorID, Date dataInicio,
			Date dataFim) {
		List<RequisicaoServico> retorno = new ArrayList<RequisicaoServico>();
		em.getTransaction().begin();

		Query q = em.createQuery(
				"SELECT rs FROM br.seploc.pojos.RequisicaoServico rs"
						+ " where rs.data between :dataInicio and  :dataFim "						 
						+ " and rs.numReq in (SELECT ru.numReqServ FROM br.seploc.pojos.ReqServUsuario ru " 
					    + "                    WHERE ru.usuario.id = :plotadorID)" )
						.setParameter(
				            "plotadorID", plotadorID)
				        .setParameter(
				            "dataInicio", dataInicio,TemporalType.DATE)
				        .setParameter(
				            "dataFim", dataFim,TemporalType.DATE);
		List<RequisicaoServico> listaReqServs =  (List<RequisicaoServico>)q.getResultList();
		for(RequisicaoServico rs : listaReqServs){
			retorno.add(rs);
		}
		em.getTransaction().commit();
		return retorno; 
	}	
	
	@Override
	protected void ajustaPojo(Usuario pojo) {
		// TODO Auto-generated method stub
		
	}
}
