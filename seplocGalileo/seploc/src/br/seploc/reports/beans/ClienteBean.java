package br.seploc.reports.beans;

public class ClienteBean {

	private String nome;
	private String endereco;
	private String cidade;
	private String uf;
	private String bairro;
	private String ni;
	private String cep;
	private String localidade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null || nome.equals("")) {
			this.nome = "----";
		} else {
			this.nome = nome;
		}
	}

	
	public String getLocalidade() {
		localidade = cidade+"/"+uf;
		return localidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		if (endereco == null || endereco.equals("")) {
			this.endereco = "----";
		} else {
			this.endereco = endereco;
		}
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		if (cidade == null || cidade.equals("")) {
			this.cidade = "----";
		} else {
			this.cidade = cidade;
		}
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		if (uf == null || uf.equals("")) {
			this.uf = "----";
		} else {
			this.uf = uf;
		}
		this.uf = uf;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		if (bairro == null || bairro.equals("")) {
			this.bairro = "----";
		} else {
			this.bairro = bairro;
		}
	}

	public String getNi() {
		return ni;
	}

	public void setNi(String ni) {
		if (ni == null || ni.equals("")) {
			this.ni = "----";
		} else {
			this.ni = ni;
		}
		this.ni = ni;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		if (cep == null || cep.equals("")) {
			this.cep = "----";
		} else {
			this.cep = cep;
		}
	}

	@Override
	public String toString() {
		return "ClienteBean [bairro=" + bairro + ", cep=" + cep + ", cidade="
				+ cidade + ", endereco=" + endereco + ", ni=" + ni + ", nome="
				+ nome + ", uf=" + uf + "]";
	}

}
