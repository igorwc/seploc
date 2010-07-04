package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_parametroprog database table.
 * 
 */
@Entity
@Table(name="tbl_parametroprog")
public class ParametroProg implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="vcrCodParametro")
	private String codParametro;

	@Column(name="vcrDescricao")
	private String descricao;

	@Column(name="vcrValor")
	private String valor;
	
	@Version
	@Column(name="tspVersao")
	private Timestamp versao;



}