package br.seploc.migracao.beans;

public class Grupo {
	private Integer intGrupo;
	private String vcrGrupo;

	public Grupo() {
	}

	public Grupo(Integer intGrupo, String vcrGrupo) {
		this.intGrupo = intGrupo;
		this.vcrGrupo = vcrGrupo;
	}

	public Integer getIntGrupo() {
		return intGrupo;
	}

	public void setIntGrupo(Integer intGrupo) {
		this.intGrupo = intGrupo;
	}

	public String getVcrGrupo() {
		return vcrGrupo;
	}

	public void setVcrGrupo(String vcrGrupo) {
		this.vcrGrupo = vcrGrupo;
	}

}
