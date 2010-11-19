package br.seploc.controllers;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import br.seploc.mbeans.ReqServListaMB;

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

}
