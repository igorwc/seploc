package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tbl_opcionaisreqserv database table.
 * 
 */
//@Entity
//@Table(name="tbl_opcionaisreqserv")
public class OpcionaisReqServ implements Serializable  {
	private static final long serialVersionUID = 1L;
/*
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="vcrCod")
	private String cod;

	@Column(name="dblValorItem")
	private Double valorItem;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	@Column(name="vcrNomeItem")
	private String nomeItem;

	//bi-directional many-to-one association to ReqServicosOpcionais
	@OneToMany(mappedBy="tblOpcionaisreqserv")
	private Set<ReqServicosOpcionais> tblReqservopcionais;

    public OpcionaisReqServ() {
    }

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public Double getValorItem() {
		return this.valorItem;
	}

	public void setValorItem(Double valorItem) {
		this.valorItem = valorItem;
	}

	public Timestamp getVersao() {
		return this.versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public String getNomeItem() {
		return this.nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public Set<ReqServicosOpcionais> getTblReqservopcionais() {
		return this.tblReqservopcionais;
	}

	public void setTblReqservopcionais(Set<ReqServicosOpcionais> tblReqservopcionais) {
		this.tblReqservopcionais = tblReqservopcionais;
	}
*/	
}