package br.seploc.migracao.beans;

public class Menu {
	private Character chrCodMenu;
	private String vcrImagem;
	private String vcrArquivo;
	private String vcrRotulo;
	private String vcrComentario;
	private String vcrTextoAlt;

	public Menu() {
	}

	public Menu(Character chrCodMenu, String vcrImagem, String vcrArquivo,
			String vcrRotulo, String vcrComentario, String vcrTextoAlt) {
		this.chrCodMenu = chrCodMenu;
		this.vcrImagem = vcrImagem;
		this.vcrArquivo = vcrArquivo;
		this.vcrRotulo = vcrRotulo;
		this.vcrComentario = vcrComentario;
		this.vcrTextoAlt = vcrTextoAlt;
	}

	public Character getChrCodMenu() {
		return chrCodMenu;
	}

	public void setChrCodMenu(Character chrCodMenu) {
		this.chrCodMenu = chrCodMenu;
	}

	public String getVcrImagem() {
		return vcrImagem;
	}

	public void setVcrImagem(String vcrImagem) {
		this.vcrImagem = vcrImagem;
	}

	public String getVcrArquivo() {
		return vcrArquivo;
	}

	public void setVcrArquivo(String vcrArquivo) {
		this.vcrArquivo = vcrArquivo;
	}

	public String getVcrRotulo() {
		return vcrRotulo;
	}

	public void setVcrRotulo(String vcrRotulo) {
		this.vcrRotulo = vcrRotulo;
	}

	public String getVcrComentario() {
		return vcrComentario;
	}

	public void setVcrComentario(String vcrComentario) {
		this.vcrComentario = vcrComentario;
	}

	public String getVcrTextoAlt() {
		return vcrTextoAlt;
	}

	public void setVcrTextoAlt(String vcrTextoAlt) {
		this.vcrTextoAlt = vcrTextoAlt;
	}

}
