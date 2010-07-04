package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_reqservopcionais database table.
 * 
 */
@Entity
@Table(name="tbl_reqservopcionais")
public class ReqServOpcionais implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReqServOpcionaisPK id;

	private int intQuant;

	private String vcrCod;
	@JoinColumn(name = "vcrCod", referencedColumnName = "vcrCod", updatable = false, insertable= false)
	@ManyToOne(fetch = FetchType.EAGER)
	private OpcionaisReqServ opcionaisReqServ;
	
	@JoinColumn(name = "intNumreq", referencedColumnName = "intNumreq", updatable = false, insertable= false)
	@ManyToOne(fetch = FetchType.EAGER)
	private RequisicaoServico reqServico;
}