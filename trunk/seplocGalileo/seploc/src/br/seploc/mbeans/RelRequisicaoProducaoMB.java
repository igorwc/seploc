package br.seploc.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Collections;
import java.util.List;

import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.migracao.beans.Cliente;
import br.seploc.pojos.RequisicaoServico;
import br.seploc.reports.beans.ReqServProducaoBeanGrid;
import br.seploc.util.Utils;

public class RelRequisicaoProducaoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private RequisicaoServico reqServico;
	private RequisicaoServicoDAO reqServicoDAO;
	private List<ReqServProducaoBeanGrid> listaProducaoCliCadastrado;
	private List<ReqServProducaoBeanGrid> listaProducaoCliBalcao;
	private Date dataInicio;
	private Date dataFim;
	
	public RelRequisicaoProducaoMB(){
		this.load();
		this.iniciarDatas();
	}
	
	private void load() {
		setReqServicoDAO(new RequisicaoServicoDAO());
		setReqServico(new RequisicaoServico());
		this.iniciarDatas();
	}	
	
	public void iniciarDatas() {
		dataInicio = new Date(Calendar.getInstance().getTimeInMillis());
		dataFim = new Date(Calendar.getInstance().getTimeInMillis());
	}	

	// GETTERS AND SETTERS
	public void setReqServico(RequisicaoServico reqServico) {
		this.reqServico = reqServico;
	}

	public RequisicaoServico getReqServico() {
		return reqServico;
	}

	public void setReqServicoDAO(RequisicaoServicoDAO reqServicoDAO) {
		this.reqServicoDAO = reqServicoDAO;
	}

	public RequisicaoServicoDAO getReqServicoDAO() {
		return reqServicoDAO;
	}
	
	public void setListaProducaoCliCadastrado(
			List<ReqServProducaoBeanGrid> listaProducaoCliCadastrado) {
		this.listaProducaoCliCadastrado = listaProducaoCliCadastrado;
	}

	public List<ReqServProducaoBeanGrid> getListaProducaoCliCadastrado() {
		listaProducaoCliCadastrado = this.buscaRequisicoes(0);
		return listaProducaoCliCadastrado;
	}

	public void setListaProducaoCliBalcao(List<ReqServProducaoBeanGrid> listaProducaoCliBalcao) {
		this.listaProducaoCliBalcao = listaProducaoCliBalcao;
	}

	public List<ReqServProducaoBeanGrid> getListaProducaoCliBalcao() {
		listaProducaoCliBalcao = this.buscaRequisicoes(1);
		return listaProducaoCliBalcao;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataFim() {
		return dataFim;
	}

	@SuppressWarnings("null")
	public List<ReqServProducaoBeanGrid> buscaRequisicoes(int balcao){
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();		
		List<ReqServProducaoBeanGrid> retorno = new ArrayList<ReqServProducaoBeanGrid>(12);		
		List<ReqServProducaoBeanGrid> rs = dao.getListaProducaoGrid(balcao);
		
		for (int i=1; i<=12; i++){
			retorno.add(new ReqServProducaoBeanGrid(i));		
		}
		
		for (ReqServProducaoBeanGrid r : rs){
			retorno.set(r.getMes()-1, r);
		}	
		
		System.out.println(retorno);
		return retorno;
	}

	public List<ReqServProducaoBeanGrid> getProducaoRequisicaoPeriodo(Date dataIni, Date dataFim, int balcao){
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		List<ReqServProducaoBeanGrid> retorno = null;
		
		return retorno;
	}
	
	public void buscaRequisicoes(){
		
	}
	
	public void limpar(){
		
	}

		
}
