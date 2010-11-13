package br.seploc.migracao.beans;

public class FoneCli {
	private Integer id;
	private String foneR;
	private String foneCom;
	private String fax;
	private String cel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFoneR() {
		return foneR;
	}

	public void setFoneR(String foneR) {
		this.foneR = foneR;
	}

	public String getFoneCom() {
		return foneCom;
	}

	public void setFoneCom(String foneCom) {
		this.foneCom = foneCom;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}
}
