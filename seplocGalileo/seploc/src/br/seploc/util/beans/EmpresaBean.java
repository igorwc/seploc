package br.seploc.util.beans;

public class EmpresaBean {

	private String razao;
	private String endereco;
	private String cep;
	private String foneFax;
	private String identificacao;
	
	public EmpresaBean() {
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFoneFax() {
		return foneFax;
	}

	public void setFoneFax(String foneFax) {
		this.foneFax = foneFax;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}
	
	@Override
	public String toString() {
		String dados = "Razão: "+ razao+"\n"+
		"Endereço: "+ endereco+"\n"+
		 cep+" - "+foneFax+"\n"+
		"Razão: "+ razao+"\n";
		

		return dados;
	}
	
}
