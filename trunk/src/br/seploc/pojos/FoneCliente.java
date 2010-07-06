package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_fonecli")
public class FoneCliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer IntClientId;

	@Column(name = "vcrFoneRes")
	private String foneResidencial;

	@Column(name = "vcrFoneCom")
	private String foneComercial;

	@Column(name = "vcrFax")
	private String fax;

	@Column(name = "vcrCelular")
	private String celular;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IntClientId", nullable = false)
	private Cliente cliente;

	
	public FoneCliente() {
	}

	public Integer getIntClientId() {
		return IntClientId;
	}

	public void setIntClientId(Integer intClientId) {
		IntClientId = intClientId;
	}

	public String getFoneResidencial() {
		return foneResidencial;
	}

	public void setFoneResidencial(String foneResidencial) {
		this.foneResidencial = foneResidencial;
	}

	public String getFoneComercial() {
		return foneComercial;
	}

	public void setFoneComercial(String foneComercial) {
		this.foneComercial = foneComercial;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((IntClientId == null) ? 0 : IntClientId.hashCode());
		result = prime * result + ((versao == null) ? 0 : versao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoneCliente other = (FoneCliente) obj;
		if (IntClientId == null) {
			if (other.IntClientId != null)
				return false;
		} else if (!IntClientId.equals(other.IntClientId))
			return false;
		if (versao == null) {
			if (other.versao != null)
				return false;
		} else if (!versao.equals(other.versao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FoneCliente ["
				+ (IntClientId != null ? "IntClientId=" + IntClientId + ", "
						: "")
				+ (celular != null ? "celular=" + celular + ", " : "")
				+ (foneComercial != null ? "foneComercial=" + foneComercial
						+ ", " : "")
				+ (foneResidencial != null ? "foneResidencial="
						+ foneResidencial : "") + "]";
	}

	
	
	
}