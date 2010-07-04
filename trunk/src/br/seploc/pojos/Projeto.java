package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tbl_projetos database table.
 * 
// */
@Entity
@Table(name="tbl_projetos")
public class Projeto implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="intCodProj")
	private Integer codProj;
	
	@Column(name="vcrProjeto")
	private String projeto;
	
	@Version
	@Column(name="tspVersao")
	private Timestamp versao;



	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vcrCnpj")
	private Cliente cliente;

//	//bi-directional many-to-one association to RequisicaoServico
//	@OneToMany(mappedBy="tblProjeto")
//	private Set<RequisicaoServico> tblReqservs;

}