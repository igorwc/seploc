package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "tbl_statuscobranca")
@SqlResultSetMapping(name = "StatusCobranca.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.StatusCobranca.class))
@NamedNativeQueries({ @NamedNativeQuery(name = "StatusCobranca.RetornaStatusCobrancas", query = " SELECT * "
		+ "FROM tbl_statuscobranca sc", resultSetMapping = "StatusCobranca.implicit") })
public class StatusCobranca implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StatusCobrancaPK id;

	// @Temporal( TemporalType.DATE)
	@Column(name = "datDataCobr")
	private Date dataCobranca;

	// @Temporal( TemporalType.DATE)
	@Column(name = "datDataPag")
	private Date dataPagamento;

	// @Temporal( TemporalType.TIMESTAMP)
	@Column(name = "horCobranca")
	private Time horaCobranca;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intCodCobr", referencedColumnName = "intCodCobr", updatable = false, insertable = false)
	private Cobrador cobrador;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intNumreq", referencedColumnName = "intNumreq", updatable = false, insertable = false)
	private RequisicaoServico reqServico;

	public StatusCobranca() {
	}

	public StatusCobranca(StatusCobrancaPK id) {
		this.id = id;
	}

	public StatusCobranca(Integer intCodCobr, Integer intNumreq) {
		this.id = new StatusCobrancaPK(intCodCobr, intNumreq);
	}

	@Transient
	public Integer getIntCodCobr() {
		return getId().getIntCodCobr();
	}

	@Transient
	public Integer getIntNumreq() {
		return getId().getIntNumreq();
	}

	public StatusCobrancaPK getId() {
		return id;
	}

	public void setId(StatusCobrancaPK id) {
		this.id = id;
	}

	public Date getDataCobranca() {
		return dataCobranca;
	}

	public void setDataCobranca(Date dataCobranca) {
		this.dataCobranca = dataCobranca;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Time getHoraCobranca() {
		return horaCobranca;
	}

	public void setHoraCobranca(Time horaCobranca) {
		this.horaCobranca = horaCobranca;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public Cobrador getCobrador() {
		return cobrador;
	}

	public void setCobrador(Cobrador cobrador) {
		this.cobrador = cobrador;
	}

	public RequisicaoServico getReqServico() {
		return reqServico;
	}

	public void setReqServico(RequisicaoServico reqServico) {
		this.reqServico = reqServico;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		StatusCobranca other = (StatusCobranca) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatusCobranca ["
				+ (id != null ? "id=" + id + ", " : "")
				+ (dataCobranca != null ? "dataCobranca=" + dataCobranca + ", "
						: "")
				+ (dataPagamento != null ? "dataPagamento=" + dataPagamento
						+ ", " : "")
				+ (horaCobranca != null ? "horaCobranca=" + horaCobranca : "")
				+ "]";
	}

}