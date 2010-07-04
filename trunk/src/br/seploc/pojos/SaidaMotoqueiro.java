package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


/**
 * The persistent class for the tbl_saidamotoqueiro database table.
 * 
 */
@Entity
@Table(name="tbl_saidamotoqueiro")
public class SaidaMotoqueiro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="intNumSaida")
	private Integer numSaida;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intCodCobr", referencedColumnName = "intCodCobr")
	private Cobrador cobrador;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intNumReq", referencedColumnName = "intNumReq")
	private RequisicaoServico reqServico;
    
    @Temporal( TemporalType.DATE)
    @Column(name="datDataCobr")
	private Date dataCobranca;

    @Temporal( TemporalType.DATE)
    @Column(name="datDataPag")
	private Date dataPagamento;

    @Temporal( TemporalType.TIME)
    @Column(name="horCobranca")
	private Time horaCobranca;

    @Temporal( TemporalType.TIME)
    @Column(name="horPagamen")
	private Time horaPagamento;

    @Column(name="vcrCliente")
	private String descCliente;

    @Column(name="vcrObs")
	private String observacoes;
    
	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

  
}