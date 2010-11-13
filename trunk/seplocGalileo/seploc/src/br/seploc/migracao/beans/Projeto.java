package br.seploc.migracao.beans;

public class Projeto {
	private Integer id;
	private Integer codProj;
	private String descProj;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodProj() {
		return codProj;
	}

	public void setCodProj(Integer codProj) {
		this.codProj = codProj;
	}

	public String getDescProj() {
		return descProj;
	}

	public void setDescProj(String descProj) {
		this.descProj = descProj;
	}
}
