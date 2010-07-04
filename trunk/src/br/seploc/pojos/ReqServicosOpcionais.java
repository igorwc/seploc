package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the tbl_reqservopcionais database table.
 * 
 */
@Entity
@Table(name="tbl_reqservopcionais")
public class ReqServicosOpcionais implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReqServicosOpcionaisPK id;

	@Column(name="intQuant")
	private Integer quantidade;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "intNumReq", referencedColumnName = "intNumReq", updatable = false, insertable= false)
	private RequisicaoServico reqServico;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intCod", referencedColumnName = "intCod", updatable = false, insertable= false)
	private OpcionaisReqServ opcionaisReqServ;

}