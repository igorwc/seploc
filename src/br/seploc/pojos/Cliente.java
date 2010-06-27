package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_clientes database table.
 * 
 */
@Entity
@Table(name="tbl_clientes")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String vcrCnpj;

	private int intBalcao;

	private int intEntregaPadrao;

	private int intPapelPadrao;

    @Lob()
	private String txtobs;

	private String vcrBairro;

	private String vcrCep;

	private String vcrCidade;

	private String vcrCpf;

	private String vcrEmail;

	private String vcrEnder;

	private String vcrEstado;

	private String vcrFantasia;

	private String vcrInscricao;

	private String vcrMapa;

	private String vcrRazao;

    public Cliente() {
    }

	public String getVcrCnpj() {
		return this.vcrCnpj;
	}

	public void setVcrCnpj(String vcrCnpj) {
		this.vcrCnpj = vcrCnpj;
	}

	public int getIntBalcao() {
		return this.intBalcao;
	}

	public void setIntBalcao(int intBalcao) {
		this.intBalcao = intBalcao;
	}

	public int getIntEntregaPadrao() {
		return this.intEntregaPadrao;
	}

	public void setIntEntregaPadrao(int intEntregaPadrao) {
		this.intEntregaPadrao = intEntregaPadrao;
	}

	public int getIntPapelPadrao() {
		return this.intPapelPadrao;
	}

	public void setIntPapelPadrao(int intPapelPadrao) {
		this.intPapelPadrao = intPapelPadrao;
	}

	public String getTxtobs() {
		return this.txtobs;
	}

	public void setTxtobs(String txtobs) {
		this.txtobs = txtobs;
	}

	public String getVcrBairro() {
		return this.vcrBairro;
	}

	public void setVcrBairro(String vcrBairro) {
		this.vcrBairro = vcrBairro;
	}

	public String getVcrCep() {
		return this.vcrCep;
	}

	public void setVcrCep(String vcrCep) {
		this.vcrCep = vcrCep;
	}

	public String getVcrCidade() {
		return this.vcrCidade;
	}

	public void setVcrCidade(String vcrCidade) {
		this.vcrCidade = vcrCidade;
	}

	public String getVcrCpf() {
		return this.vcrCpf;
	}

	public void setVcrCpf(String vcrCpf) {
		this.vcrCpf = vcrCpf;
	}

	public String getVcrEmail() {
		return this.vcrEmail;
	}

	public void setVcrEmail(String vcrEmail) {
		this.vcrEmail = vcrEmail;
	}

	public String getVcrEnder() {
		return this.vcrEnder;
	}

	public void setVcrEnder(String vcrEnder) {
		this.vcrEnder = vcrEnder;
	}

	public String getVcrEstado() {
		return this.vcrEstado;
	}

	public void setVcrEstado(String vcrEstado) {
		this.vcrEstado = vcrEstado;
	}

	public String getVcrFantasia() {
		return this.vcrFantasia;
	}

	public void setVcrFantasia(String vcrFantasia) {
		this.vcrFantasia = vcrFantasia;
	}

	public String getVcrInscricao() {
		return this.vcrInscricao;
	}

	public void setVcrInscricao(String vcrInscricao) {
		this.vcrInscricao = vcrInscricao;
	}

	public String getVcrMapa() {
		return this.vcrMapa;
	}

	public void setVcrMapa(String vcrMapa) {
		this.vcrMapa = vcrMapa;
	}

	public String getVcrRazao() {
		return this.vcrRazao;
	}

	public void setVcrRazao(String vcrRazao) {
		this.vcrRazao = vcrRazao;
	}

}