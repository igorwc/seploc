package br.seploc.migracao.beans;

public class Papel {
	public Integer cod;
	public String nome;
	public Double mono;
	public Double color;
	public Double shade;

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getMono() {
		return mono;
	}

	public void setMono(Double mono) {
		this.mono = mono;
	}

	public Double getColor() {
		return color;
	}

	public void setColor(Double color) {
		this.color = color;
	}

	public Double getShade() {
		return shade;
	}

	public void setShade(Double shade) {
		this.shade = shade;
	}
}
