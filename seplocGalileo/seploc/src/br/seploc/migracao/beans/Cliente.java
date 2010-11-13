package br.seploc.migracao.beans;

public class Cliente {
	private Integer id;
	private String cnpj;
	private String razao;
	private String cpf;
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String email;
	private String inscricao;
	private Integer balcao;
	private String fantasia;
	private String obs;
	private String mapa;
	private Integer entrega;
	private Integer papel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public Integer getBalcao() {
		return balcao;
	}

	public void setBalcao(Integer balcao) {
		this.balcao = balcao;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getMapa() {
		return mapa;
	}

	public void setMapa(String mapa) {
		this.mapa = mapa;
	}

	public Integer getEntrega() {
		return entrega;
	}

	public void setEntrega(Integer entrega) {
		this.entrega = entrega;
	}

	public Integer getPapel() {
		return papel;
	}

	public void setPapel(Integer papel) {
		this.papel = papel;
	}

	@Override
	public String toString() {
		return "Cliente [bairro=" + bairro + ", balcao=" + balcao + ", cep="
				+ cep + ", cidade=" + cidade + ", cnpj=" + cnpj + ", cpf="
				+ cpf + ", email=" + email + ", endereco=" + endereco
				+ ", entrega=" + entrega + ", estado=" + estado + ", fantasia="
				+ fantasia + ", id=" + id + ", inscricao=" + inscricao
				+ ", mapa=" + mapa + ", obs=" + obs + ", papel=" + papel
				+ ", razao=" + razao + "]";
	}
	
}
