package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tbl_projetos database table.
 * 
 */
//@Entity
//@Table(name="tbl_projetos")
public class Projeto implements Serializable  {
	private static final long serialVersionUID = 1L;
/*
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="intCodProj")
	private Integer codProj;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	@Column(name="vcrProjeto")
	private String projeto;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vcrCnpj")
	private Cliente tblCliente;

//	//bi-directional many-to-one association to RequisicaoServico
//	@OneToMany(mappedBy="tblProjeto")
//	private Set<RequisicaoServico> tblReqservs;

    public Projeto() {
    }

	public Integer getCodProj() {
		return this.codProj;
	}

	public void setCodProj(Integer codProj) {
		this.codProj = codProj;
	}

	public Timestamp getVersao() {
		return this.versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public String getProjeto() {
		return this.projeto;
	}

	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

	public Cliente getTblCliente() {
		return this.tblCliente;
	}

	public void setTblCliente(Cliente tblCliente) {
		this.tblCliente = tblCliente;
	}
	

*/	
}