package br.seploc.mbeans;

import java.io.Serializable;

import javax.faces.model.SelectItem;

public class NavigationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1027518204952836092L;
	private Integer opcaoDocId;
	private final Integer CNPJ = 1;
	private final Integer CPF = 2;
	
	/**
	 * @return the cNPJ
	 */
	public Integer getCNPJ() {
		return CNPJ;
	}

	/**
	 * @return the cPF
	 */
	public Integer getCPF() {
		return CPF; 
		
	}
	/**
	 * @return the opcaoDocId
	 */
	public Integer getOpcaoDocId() {
		return opcaoDocId;
	}

	/**
	 * @param opcaoDocId the opcaoDocId to set
	 */
	public void setOpcaoDocId(Integer opcaoDocId) {
		System.out.println("Setou Valor: "+ opcaoDocId);
		this.opcaoDocId = opcaoDocId;
	}
	
	public void resetarBean(){
		setOpcaoDocId(1);
	}
}
