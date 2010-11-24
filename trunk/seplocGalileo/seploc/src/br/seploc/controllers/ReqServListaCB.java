package br.seploc.controllers;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import br.seploc.mbeans.ReqServListaMB;
import br.seploc.pojos.RequisicaoServico;

public class ReqServListaCB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ReqServListaMB reqServListaMB;
	
	// CONSTRUTOR
	public ReqServListaCB(){
		this.setReqServListaMB(this.loadReqServList());
	}
	
	public ReqServListaMB getReqServListaMB() {
		return reqServListaMB;
	}

	public void setReqServListaMB(ReqServListaMB reqServListaMB) {
		this.reqServListaMB = reqServListaMB;
	}

	public ReqServListaMB loadReqServList(){
		FacesContext context = FacesContext.getCurrentInstance();
		ReqServListaMB reqServListaMB = (ReqServListaMB) context.getApplication()
		.evaluateExpressionGet(context, "#{reqServListaMB}", ReqServListaMB.class);
		
		return reqServListaMB;
	}
	
	/**
	 * Metodo ira formatar o numero existente no banco para um numero de formato 000009  
	 * @param num
	 * @return
	 */
	public String getNumReqServFormatado(Integer num){
		String retorno = num.toString();
		StringBuffer str = new StringBuffer(retorno);
		
		while (str.length() < 6) {
			str.append(0);			
			retorno = str.reverse().toString();			
		}		
		
		return retorno;
	}		

	public List<RequisicaoServico> getGridReqServ(){
		List<RequisicaoServico> lista = new ArrayList<RequisicaoServico>();
		Date dataInicio = (Date) reqServListaMB.getDataInicio();
		Date dataFim = (Date) reqServListaMB.getDataFim();
		int numeroReqServ = reqServListaMB.getNumReqBusca();
		int clienteID = reqServListaMB.getClienteID();
		int projetoID = reqServListaMB.getProjetoID();

		lista = reqServListaMB.getReqServicoDAO().filtraReqServ(projetoID , numeroReqServ, clienteID, dataInicio, dataFim);
				
		return lista;
	}	
}
