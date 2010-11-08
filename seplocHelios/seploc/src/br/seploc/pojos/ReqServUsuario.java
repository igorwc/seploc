package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "tbl_reqservusuario")
public class ReqServUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReqServUsuarioPK id;

	@Temporal(TemporalType.DATE)
	@Column(name = "datData")
	private Date data;
	
	@Column(name = "vcrLoginAlter", length=30)
	private String loginAlteracao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datdataAlter")
	private Date dataAlteracao;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vcrLogin", referencedColumnName = "vcrLogin", updatable = false, insertable = false)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intNumReq", referencedColumnName = "intNumReq", updatable = false, insertable = false)
	private RequisicaoServico reqServico;

	
	public ReqServUsuario() {

	}

	public ReqServUsuario(ReqServUsuarioPK id) {
		this.id = id;
	}

	public ReqServUsuario(String vcrLogin, int intNumReq) {
		this.id = new ReqServUsuarioPK(vcrLogin, intNumReq);
	}
	@Transient
	public Integer getIntNumReq() {
		return getId().getIntNumReq();
	}

	@Transient
	public String getVcrLogin() {
		return getId().getVcrLogin();
	}
	public ReqServUsuarioPK getId() {
		return id;
	}

	public void setId(ReqServUsuarioPK id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getLoginAlteracao() {
		return loginAlteracao;
	}

	public void setLoginAlteracao(String loginAlteracao) {
		this.loginAlteracao = loginAlteracao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		ReqServUsuario other = (ReqServUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReqServUsuario ["
				+ (getId() != null ? "getId()=" + getId() : "") + "]";
	}

}