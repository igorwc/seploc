package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tbl_entrega database table.
 * 
 */
//@Entity
//@Table(name="tbl_entrega")
public class Entrega implements Serializable {
	private static final long serialVersionUID = 1L;
/*
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="intCodEnt")
	private Integer codEntrega;

	@Column(name="dblPreco")
	private Double preco;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	@Column(name="vcrLocal")
	private String local;

	//bi-directional many-to-one association to RequisicaoServico
//	@OneToMany(mappedBy="tblEntrega")
//	private Set<RequisicaoServico> tblReqservs;

    public Entrega() {
    }

	public Integer getCodEntrega() {
		return this.codEntrega;
	}

	public void setCodEntrega(Integer codEntrega) {
		this.codEntrega = codEntrega;
	}

	public Double getPreco() {
		return this.preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Timestamp getVersao() {
		return this.versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public String getLocal() {
		return this.local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

//	public Set<RequisicaoServico> getTblReqservs() {
//		return this.tblReqservs;
//	}
//
//	public void setTblReqservs(Set<RequisicaoServico> tblReqservs) {
//		this.tblReqservs = tblReqservs;
//	}
//	
*/}