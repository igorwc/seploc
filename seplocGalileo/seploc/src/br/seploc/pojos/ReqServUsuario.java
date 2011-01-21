package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "tbl_reqservusuario")
public class ReqServUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

//	@Id
//	@Column(name = "intNumReq", nullable = false)
//	private Integer numReqServ;
	
	@Id	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intNumReq", referencedColumnName = "intNumReq", updatable = false, insertable = false)
	private RequisicaoServico reqServico;	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datData")
	private Date data;
		
	@Temporal(TemporalType.DATE)
	@Column(name = "datdataAlter")
	private Date dataAlteracao;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "vcrLogin", referencedColumnName = "vcrLogin", updatable = false, insertable = false)
//	private Usuario usuario;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intCodUsr", referencedColumnName = "intCodUsr", updatable = false, insertable = false)
	private Usuario usuario;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intCodUsrAlter", referencedColumnName = "intCodUsr", updatable = false, insertable = false)
	private Usuario usuarioAlteracao;

	public ReqServUsuario() {

	}

	public ReqServUsuario(RequisicaoServico requisicao) {
		this.setReqServico(requisicao);		
	}

	public ReqServUsuario(Usuario usuario, RequisicaoServico reqServ) {
		this.setReqServico(reqServ);
		this.setUsuario(usuario);
	}
	
//	public void setNumReqServ(Integer numReqServ) {
//		this.numReqServ = numReqServ;
//	}
//
//	public Integer getNumReqServ() {
//		return numReqServ;
//	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
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
		result = prime * result + ((reqServico == null) ? 0 : reqServico.hashCode());
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
		if (reqServico == null) {
			if (other.reqServico != null)
				return false;
		} else if (!reqServico.equals(other.reqServico))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReqServUsuario ["
				+ (getReqServico().getNumReq() != null ? "getNumReqServ()=" + getReqServico().getNumReq() : "") + "]";
	}

}