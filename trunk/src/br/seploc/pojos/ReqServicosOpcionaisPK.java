package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_reqservopcionais database table.
 * 
 */
@Embeddable
public class ReqServicosOpcionaisPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "intNumReq", nullable = false)
	private Integer intNumReq;

	@Column(name = "intCodOp", nullable = false)
	private Integer intCodOp;

}