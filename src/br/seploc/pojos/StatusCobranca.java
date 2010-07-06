package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.sql.Time;
import java.util.Date;


@Entity
@Table(name="tbl_statuscobranca")
public class StatusCobranca implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StatusCobrancaPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="datDataCobr")
	private Date dataCobranca;

    @Temporal( TemporalType.DATE)
	@Column(name="datDataPag")
	private Date dataPagamento;

    @Temporal( TemporalType.TIME)
	@Column(name="horCobranca")
	private Time horaCobranca;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intCodCobr", referencedColumnName = "intCodCobr", updatable = false, insertable= false)
	private Cobrador cobrador;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intNumreq", referencedColumnName = "intNumreq", updatable = false, insertable= false)
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
	
}