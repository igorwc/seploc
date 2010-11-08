package br.seploc.controllers;

import java.io.Serializable;
import javax.faces.context.FacesContext;

import br.seploc.mbeans.ReqServClienteMB;

public class ReqServClienteCB implements Serializable{

	private static final long serialVersionUID = 1L;
	private ReqServClienteMB reqServMB;
	
	// CONSTRUTOR
	public ReqServClienteCB(){
		this.setReqServMB(this.loadReqServ());
	}
	
	// GETTERS E SETTERSS
	public ReqServClienteMB getReqServMB() {
		return reqServMB;
	}

	public void setReqServMB(ReqServClienteMB reqServMB) {
		this.reqServMB = reqServMB;
	}

	public ReqServClienteMB loadReqServ(){
		FacesContext context = FacesContext.getCurrentInstance();
		ReqServClienteMB reqServMB = (ReqServClienteMB) context.getApplication()
        .evaluateExpressionGet(context, "#{reqServClienteMB}", ReqServClienteMB.class);
		
		return reqServMB; 
	}

	/**
	 * Metodo irá formatar o numero existente no banco para um numero de formato 000009  
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
