package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_reqservusuario database table.
 * 
 */
@Embeddable
public class ReqServUsuarioPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "vcrLogin", nullable = false)
	private String vcrLogin;

	@Column(name = "intNumReq", nullable = false)
	private int intNumReq;
}