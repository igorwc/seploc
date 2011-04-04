package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "tbl_reqserv")
@SqlResultSetMapping(name = "RequisicaoServico.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.RequisicaoServico.class))
@NamedNativeQueries( {
		@NamedNativeQuery(name = "RequisicaoServico.RetornaRequisicoes", query = " SELECT * "
				+ "FROM tbl_reqserv", resultSetMapping = "RequisicaoServico.implicit"),
		@NamedNativeQuery(name = "RequisicaoServico.RetornaRequisicoesLimitadoTempo", query = " SELECT * "
				+ "FROM tbl_reqserv " + "where datData >= :data order by intNumReq desc", resultSetMapping = "RequisicaoServico.implicit"),
		@NamedNativeQuery(name = "RequisicaoServico.FiltraProjeto", query = " SELECT * "
				+ "FROM tbl_reqserv " + "where intCodProj = :projeto order by intNumReq desc", resultSetMapping = "RequisicaoServico.implicit"),
		@NamedNativeQuery(name = "RequisicaoServico.FiltraReqServ", query = " SELECT * "
				+ "FROM tbl_reqserv where intNumreq = :numReq order by intNumReq desc", resultSetMapping = "RequisicaoServico.implicit"),
		@NamedNativeQuery(name = "RequisicaoServico.FiltraCliente", query = " SELECT * "
				+ "FROM tbl_reqserv "
				+ "where datData between :dataInicio and :dataFinal "
				+ "and intCodProj in (select intCodProj FROM tbl_projetos where intClienteId = :clienteId)  order by intNumReq desc", resultSetMapping = "RequisicaoServico.implicit"),
		@NamedNativeQuery(name = "RequisicaoServico.RetornaRequisicoesPorPeriodo", query = " SELECT * "
				+ "FROM tbl_reqserv "
				+ "where datData >= :dataInicio and datData <= :dataFim "
				+ "and intCodProj in (select intCodProj FROM tbl_projetos where intClienteId = :clienteId) ", resultSetMapping = "RequisicaoServico.implicit") })
public class RequisicaoServico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "reqserv_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "reqserv_id", table = "ID_GEN", allocationSize = 1, initialValue = 1, pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", pkColumnValue = "REQ_SERV_GEN")
	@Column(name = "intNumReq")
	private Integer numReq;

	@Temporal(TemporalType.DATE)
	@Column(name = "datData")
	private Date data;

	@Column(name = "dblValorEnt")
	private Double valorEnt;

	@Column(name = "dblValorTotal")
	private Double valorTotal;

	@Column(name = "dblValorDesc")
	private Double valorDesconto;

	@Column(name = "intStatus")
	private Integer status;

	@Column(name = "intVisivelNf")
	private Integer visivelNf;

	@Column(name = "intVisivelReq")
	private Integer visivelReq;

	@Column(name = "intOrcamento")
	private Integer orcamento;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@OneToMany(mappedBy = "reqServico", cascade = { CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REMOVE })
	private List<LinhaRequisicao> linhaRequisicao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intCodProj", referencedColumnName = "intCodProj", nullable = false)
	private Projeto projeto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intCodEnt", referencedColumnName = "intCodEnt")
	private Entrega entrega;

	// @ManyToOne(fetch = FetchType.EAGER)
	// @JoinColumn(name = "intCodCobr", referencedColumnName = "intCodCobr")
	// private Cobrador cobrador;

	@OneToMany(mappedBy = "reqServico", cascade = { CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REMOVE })
	private List<ReqServicosOpcionais> opcionais;

	// @OneToMany(mappedBy = "reqServico")
	// private List<ReqServUsuario> requisicoesUsuarios;
	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL})
	@JoinColumn(name = "intNumReq", referencedColumnName = "intNumReq")
	private ReqServUsuario requisicaoUsuario;

	@OneToMany(mappedBy = "reqServico")
	private List<SaidaMotoqueiro> saidasMotoqueiros;

	public RequisicaoServico() {
		setOpcionais(new ArrayList<ReqServicosOpcionais>());
		setSaidasMotoqueiros(new ArrayList<SaidaMotoqueiro>());
		setLinhaRequisicao(new ArrayList<LinhaRequisicao>());
		setOrcamento(0);
	}

	public Integer getNumReq() {
		return numReq;
	}

	public void setNumReq(Integer numReq) {
		this.numReq = numReq;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValorEnt() {
		return valorEnt;
	}

	public void setValorEnt(Double valorEnt) {
		this.valorEnt = valorEnt;
	}

	public Double getValorTotal() {
		return valorTotal;
	}
	public Double getValorTotalComEntrega() {
		double total = 0;
		double entrega = 0;
		if(valorTotal == null){
			total = 0;
		}else{
			total = valorTotal;
		}
		if(valorEnt == null){
			entrega = 0;
		}else{
			entrega = valorEnt;
		}
		total = (valorTotal+entrega) ;
		return  total;
	}
	public Double getValorTotalComDesconto() {
		double total = 0;
		double desconto = 0;
		double entrega = 0;
		if(valorTotal == null){
			total = 0;
		}else{
			total = valorTotal;
		}
		if(valorEnt == null){
			entrega = 0;
		}else{
			entrega = valorEnt;
		}
		if(orcamento == null){
			desconto = 0;
		}else{
			desconto = orcamento;
		}
		total = ((total+entrega)- (total+entrega) *desconto/100);
		return total;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getVisivelNf() {
		return visivelNf;
	}

	public void setVisivelNf(Integer visivelNf) {
		this.visivelNf = visivelNf;
	}

	public Integer getVisivelReq() {
		return visivelReq;
	}

	public void setVisivelReq(Integer visivelReq) {
		this.visivelReq = visivelReq;
	}

	public void setOrcamento(Integer orcamento) {
		this.orcamento = orcamento;
	}

	public Integer getOrcamento() {
		return orcamento;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public List<LinhaRequisicao> getLinhaRequisicao() {
		return linhaRequisicao;
	}

	public void setLinhaRequisicao(List<LinhaRequisicao> linhaRequisicao) {
		this.linhaRequisicao = linhaRequisicao;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	// public Cobrador getCobrador() {
	// return cobrador;
	// }
	//
	// public void setCobrador(Cobrador cobrador) {
	// this.cobrador = cobrador;
	// }

	public List<ReqServicosOpcionais> getOpcionais() {
		return opcionais;
	}

	public void setOpcionais(List<ReqServicosOpcionais> opcionais) {
		this.opcionais = opcionais;
	}

	public void setRequisicaoUsuario(ReqServUsuario requisicaoUsuario) {
		this.requisicaoUsuario = requisicaoUsuario;
	}

	public ReqServUsuario getRequisicaoUsuario() {
		return requisicaoUsuario;
	}

	public List<SaidaMotoqueiro> getSaidasMotoqueiros() {
		return saidasMotoqueiros;
	}

	public void setSaidasMotoqueiros(List<SaidaMotoqueiro> saidasMotoqueiros) {
		this.saidasMotoqueiros = saidasMotoqueiros;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numReq == null) ? 0 : numReq.hashCode());
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
		RequisicaoServico other = (RequisicaoServico) obj;
		if (numReq == null) {
			if (other.numReq != null)
				return false;
		} else if (!numReq.equals(other.numReq))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RequisicaoServico ["
				+ (numReq != null ? "numReq=" + numReq : "") + "]";
	}

}