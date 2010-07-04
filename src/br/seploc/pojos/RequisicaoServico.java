package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


/**
 * The persistent class for the tbl_reqserv database table.
 * 
 */
@Entity
@Table(name="tbl_reqserv")
public class RequisicaoServico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="intNumreq")
	private Integer numReq;

    @Temporal( TemporalType.DATE)
	@Column(name="datData")
	private Date data;

	@Column(name="dblValorEnt")
	private Double valorEnt;

	@Column(name="dblValorTotal")
	private Double valorTotal;

	@Column(name="intStatus")
	private Integer status;

	@Column(name="intVisivelNf")
	private Integer visivelNf;

	@Column(name="intVisivelReq")
	private Integer visivelReq;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	@OneToMany(mappedBy="reqServico")
	private List<LinhaRequisicao> linhaRequisicao;
//
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "vcrCnpj", referencedColumnName = "vcrCnpj")
	private Cliente cliente;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "intCodProj", referencedColumnName = "intCodProj")
	private Projeto tblProjeto;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="intCodEnt", referencedColumnName = "intCodEnt")
	private Entrega entrega;
//
//	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="intCodCobr", referencedColumnName = "intCodCobr")
	private Cobrador cobrador;

	
	@OneToMany(mappedBy="reqServico")
	private List<ReqServOpcionais> tblReqservopcionais;
	
	@OneToMany(mappedBy="reqServico")
	private List<ReqServUsuario> tblReqservusuarios;

	@OneToMany(mappedBy="reqServico")
	private List<SaidaMotoqueiro> saidaMotoqueiros;
	
	@OneToMany(mappedBy="reqServico")
	private List<StatusCobranca> statusCobrancas;

   
}