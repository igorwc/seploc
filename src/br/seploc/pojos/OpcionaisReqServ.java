package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 * The persistent class for the tbl_opcionaisreqserv database table.
 * 
 */
@Entity
@Table(name="tbl_opcionaisreqserv")
public class OpcionaisReqServ implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="intCod")
	private Integer cod;

	@Column(name="vcrNomeItem")
	private String nomeItem;
	
	@Column(name="dblValorItem")
	private Double valorItem;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	@OneToMany(mappedBy="opcionaisReqServ")
	private List<ReqServOpcionais> ReqServOpcionais;

}