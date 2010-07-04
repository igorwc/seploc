package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the tbl_statuscobranca database table.
 * 
 */
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
	
}