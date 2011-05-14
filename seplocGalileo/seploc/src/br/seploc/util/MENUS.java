package br.seploc.util;

public enum MENUS {
	 SEPLOC("SEPLOC"), 
	 ATENDIMENTO ("ATENDIMENTO"),
	 CADASTROS("CADASTROS"),
	 RELATORIOS("RELATORIOS"), 
	 ACESSO_SISTEMA ("ACESSO_SISTEMA"),
	 GRUPO_ACESSO ("GRUPO_ACESSO"),
	 PAPEL ("PAPEL"),
	 OPCIONAL ("OPCIONAL"),
	 ENTREGA ("ENTREGA"),
	 COBRADOR ("COBRADOR"),
	 USUARIO ("USUARIO"),
	 CLIENTE ("CLIENTE"),
	 PROJETO ("PROJETO"),
	 SAIR ("SAIR"),
	 GRUPO ("GRUPO"),
	 MENU ("MENU"),
	 ASSOCIAR_USUARIO ("ASSOCIAR_USUARIO"),
	 BALCAO ("BALCAO"),
	 REQ_CLIENTE ("REQ_CLIENTE"),	 
	 REQUISICOES ("REQUISICOES"),
	 SAIDA_REQ ("SAIDA_REQ"),
	 PARAMETROS ("PARAMETROS"),
	 REL_CLIENTE ("REL_CLIENTE"),
	 REL_COBRADOR ("REL_COBRADOR"),
	 REL_PLOTADOR ("REL_PLOTADOR"),
	 REL_PRODUCAO ("REL_PRODUCAO");
	 
	 private final String nome;

	private MENUS(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	} 
	 

}
