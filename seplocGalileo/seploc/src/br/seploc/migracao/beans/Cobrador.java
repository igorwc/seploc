package br.seploc.migracao.beans;

public class Cobrador {
	private Integer codCobrador;
	private String nome;
	private String fone;
	private Character inAtivo;

	public Cobrador() {
		// TODO Auto-generated constructor stub
	}

	public Cobrador(Integer codCobrador, String nome, String fone,
			Character inAtivo) {
		super();
		this.codCobrador = codCobrador;
		this.nome = nome;
		this.fone = fone;
		this.inAtivo = inAtivo;
	}

	public Integer getCodCobrador() {
		return codCobrador;
	}

	public void setCodCobrador(Integer codCobrador) {
		this.codCobrador = codCobrador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public Character getInAtivo() {
		return inAtivo;
	}

	public void setInAtivo(Character inAtivo) {
		this.inAtivo = inAtivo;
	}

}
