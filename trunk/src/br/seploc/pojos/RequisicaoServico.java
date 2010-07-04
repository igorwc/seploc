package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the tbl_reqserv database table.
 * 
 */
//@Entity
//@Table(name="tbl_reqserv")
public class RequisicaoServico implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@Id
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

	//bi-directional many-to-one association to LinhaReq
//	@OneToMany(mappedBy="tblReqserv")
//	private Set<LinhaReq> tblLinhareqs;
//
//	//bi-directional many-to-one association to Cliente
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="vcrCnpj")
//	private Cliente tblCliente;
//
//	//bi-directional many-to-one association to Projeto
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="intCodProj")
//	private Projeto tblProjeto;
//
//	//bi-directional many-to-one association to Entrega
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="intCodEnt")
//	private Entrega tblEntrega;
//
//	//bi-directional many-to-one association to Cobrador
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="intCodCobr")
//	private Cobrador tblCobrador;
//
//	//bi-directional many-to-one association to ReqServicosOpcionais
//	@OneToMany(mappedBy="tblReqserv")
//	private Set<ReqServicosOpcionais> tblReqservopcionais;
//
//	//bi-directional many-to-one association to ReqServUsuario
//	@OneToMany(mappedBy="tblReqserv")
//	private Set<ReqServUsuario> tblReqservusuarios;
//
//	//bi-directional many-to-one association to TblSaidamotoqueiro
//	@OneToMany(mappedBy="tblReqserv")
//	private Set<TblSaidamotoqueiro> tblSaidamotoqueiros;
//
//	//bi-directional many-to-one association to StatusCobranca
//	@OneToMany(mappedBy="tblReqserv")
//	private Set<StatusCobranca> tblStatuscobrancas;

    public RequisicaoServico() {
    }

	public Integer getNumReq() {
		return this.numReq;
	}

	public void setNumReq(Integer numReq) {
		this.numReq = numReq;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValorEnt() {
		return this.valorEnt;
	}

	public void setValorEnt(Double valorEnt) {
		this.valorEnt = valorEnt;
	}

	public Double getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getVisivelNf() {
		return this.visivelNf;
	}

	public void setVisivelNf(Integer visivelNf) {
		this.visivelNf = visivelNf;
	}

	public Integer getVisivelReq() {
		return this.visivelReq;
	}

	public void setVisivelReq(Integer visivelReq) {
		this.visivelReq = visivelReq;
	}

	public Timestamp getVersao() {
		return this.versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

//	public Set<LinhaReq> getTblLinhareqs() {
//		return this.tblLinhareqs;
//	}
//
//	public void setTblLinhareqs(Set<LinhaReq> tblLinhareqs) {
//		this.tblLinhareqs = tblLinhareqs;
//	}
//	
//	public Cliente getTblCliente() {
//		return this.tblCliente;
//	}
//
//	public void setTblCliente(Cliente tblCliente) {
//		this.tblCliente = tblCliente;
//	}
	
//	public Projeto getTblProjeto() {
//		return this.tblProjeto;
//	}
//
//	public void setTblProjeto(Projeto tblProjeto) {
//		this.tblProjeto = tblProjeto;
//	}
//	
//	public Entrega getTblEntrega() {
//		return this.tblEntrega;
//	}
//
//	public void setTblEntrega(Entrega tblEntrega) {
//		this.tblEntrega = tblEntrega;
//	}
//	
//	public Cobrador getTblCobrador() {
//		return this.tblCobrador;
//	}
//
//	public void setTblCobrador(Cobrador tblCobrador) {
//		this.tblCobrador = tblCobrador;
//	}
//	
//	public Set<ReqServicosOpcionais> getTblReqservopcionais() {
//		return this.tblReqservopcionais;
//	}
//
//	public void setTblReqservopcionais(Set<ReqServicosOpcionais> tblReqservopcionais) {
//		this.tblReqservopcionais = tblReqservopcionais;
//	}
//	
//	public Set<ReqServUsuario> getTblReqservusuarios() {
//		return this.tblReqservusuarios;
//	}
//
//	public void setTblReqservusuarios(Set<ReqServUsuario> tblReqservusuarios) {
//		this.tblReqservusuarios = tblReqservusuarios;
//	}
//	
//	public Set<TblSaidamotoqueiro> getTblSaidamotoqueiros() {
//		return this.tblSaidamotoqueiros;
//	}
//
//	public void setTblSaidamotoqueiros(Set<TblSaidamotoqueiro> tblSaidamotoqueiros) {
//		this.tblSaidamotoqueiros = tblSaidamotoqueiros;
//	}
//	
//	public Set<StatusCobranca> getTblStatuscobrancas() {
//		return this.tblStatuscobrancas;
//	}
//
//	public void setTblStatuscobrancas(Set<StatusCobranca> tblStatuscobrancas) {
//		this.tblStatuscobrancas = tblStatuscobrancas;
//	}
*/	
}