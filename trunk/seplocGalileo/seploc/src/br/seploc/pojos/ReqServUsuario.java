package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "tbl_reqservusuario")
public class ReqServUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "intNumReq", nullable = false)
	private Integer numReqServ;
	
	@Column(name = "vcrLogin", nullable = false, length = 30)
	private String login;

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

	public ReqServUsuario(Integer numReqServ) {
		this.setNumReqServ(numReqServ);
	}

	public ReqServUsuario(String vcrLogin, Integer intNumReq) {
		this.setNumReqServ(intNumReq);
		this.setLogin(vcrLogin);
	}
	
	public void setNumReqServ(Integer numReqServ) {
		this.numReqServ = numReqServ;
	}

	public Integer getNumReqServ() {
		return numReqServ;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
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
		result = prime * result + ((numReqServ == null) ? 0 : numReqServ.hashCode());
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
		if (numReqServ == null) {
			if (other.numReqServ != null)
				return false;
		} else if (!numReqServ.equals(other.numReqServ))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReqServUsuario ["
				+ (getNumReqServ() != null ? "getNumReqServ()=" + getNumReqServ() : "") + "]";
	}

}