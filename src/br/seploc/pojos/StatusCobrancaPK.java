package br.seploc.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the tbl_statuscobranca database table.
 * 
 */
@Embeddable
public class StatusCobrancaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "intNumLin", nullable = false)
	private int intCodCobr;

	@Column(name = "intNumLin", nullable = false)
	private int intNumreq;


}