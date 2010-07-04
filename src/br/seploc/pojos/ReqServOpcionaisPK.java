package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the tbl_reqservopcionais database table.
 * 
 */
@Embeddable
public class ReqServOpcionaisPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "intNumReq", nullable = false)
	private String intNumReq;

	@Column(name = "intCodOp", nullable = false)
	private int intCodOp;
}