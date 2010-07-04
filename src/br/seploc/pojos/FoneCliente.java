package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_fonecli database table.
 * 
 */
@Entity
@Table(name="tbl_fonecli")
public class FoneCliente implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
	private String vcrCnpj;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	@Column(name="vcrCelular")
	private String celular;

	@Column(name="vcrFax")
	private String fax;

	@Column(name="vcrFoneCom")
	private String foneComercial;

	@Column(name="vcrFoneRes")
	private String foneResidencial;

	//bi-directional one-to-one association to Cliente
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vcrCnpj",nullable=false)
	private Cliente cliente;
	
	public FoneCliente() {
	}

	
	
	public FoneCliente(String vcrCnpj, String celular, String fax,
			String foneComercial, String foneResidencial, Cliente cliente) {
		this.vcrCnpj = vcrCnpj;
		this.celular = celular;
		this.fax = fax;
		this.foneComercial = foneComercial;
		this.foneResidencial = foneResidencial;
		this.cliente = cliente;
	}



	public FoneCliente(String vcrCnpj, String celular, String fax,
			String foneComercial, String foneResidencial) {
		this.vcrCnpj = vcrCnpj;
		this.celular = celular;
		this.fax = fax;
		this.foneComercial = foneComercial;
		this.foneResidencial = foneResidencial;
	}



	public String getVcrCnpj() {
		return vcrCnpj;
	}

	public void setVcrCnpj(String vcrCnpj) {
		this.vcrCnpj = vcrCnpj;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFoneComercial() {
		return foneComercial;
	}

	public void setFoneComercial(String foneComercial) {
		this.foneComercial = foneComercial;
	}

	public String getFoneResidencial() {
		return foneResidencial;
	}

	public void setFoneResidencial(String foneResidencial) {
		this.foneResidencial = foneResidencial;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}