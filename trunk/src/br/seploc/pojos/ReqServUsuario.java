package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * The persistent class for the tbl_reqservusuario database table.
 * 
 */
@Entity
@Table(name = "tbl_reqservusuario")
public class ReqServUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReqServUsuarioPK id;

	@Temporal(TemporalType.DATE)
	@Column(name = "datData")
	private Date data;
	
	@Column(name = "vcrLoginAlter")
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

}