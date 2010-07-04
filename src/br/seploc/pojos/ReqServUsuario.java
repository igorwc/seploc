package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the tbl_reqservusuario database table.
 * 
 */
//@Entity
//@Table(name="tbl_reqservusuario")
public class ReqServUsuario implements Serializable{
	private static final long serialVersionUID = 1L;

/*	@EmbeddedId
	private ReqServUsuarioPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="datData")
	private Date data;

    @Temporal( TemporalType.DATE)
	@Column(name="datdataAlter")
	private Date dataAlteracao;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	@Column(name="vcrLoginAlter")
	private String loginAlteracao;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vcrLogin")
	private Usuario tblUsuario;

	//bi-directional many-to-one association to RequisicaoServico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="intNumReq")
	private RequisicaoServico tblReqserv;

    public ReqServUsuario() {
    }

	public ReqServUsuarioPK getId() {
		return this.id;
	}

	public void setId(ReqServUsuarioPK id) {
		this.id = id;
	}
	
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataAlteracao() {
		return this.dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Timestamp getVersao() {
		return this.versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public String getLoginAlteracao() {
		return this.loginAlteracao;
	}

	public void setLoginAlteracao(String loginAlteracao) {
		this.loginAlteracao = loginAlteracao;
	}

	public Usuario getTblUsuario() {
		return this.tblUsuario;
	}

	public void setTblUsuario(Usuario tblUsuario) {
		this.tblUsuario = tblUsuario;
	}
	
	public RequisicaoServico getTblReqserv() {
		return this.tblReqserv;
	}

	public void setTblReqserv(RequisicaoServico tblReqserv) {
		this.tblReqserv = tblReqserv;
	}
*/	
}