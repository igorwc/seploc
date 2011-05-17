package br.seploc.util;

public enum MESES{		
	JAN("Janeiro",1 ),
	FEV("Fevereiro",2 ),
	MAR("MarÃ§o",3 ),
	ABR("Abril",4 ),
	MAI("Maio",5 ),
	JUN("Junho",6 ),
	JUL("Julho",7 ),
	AGO("Agosto",8 ),
	SET("Setembro",9 ),
	OUT("Outubro",10 ),
	NOV("Novembro",11 ),
	DEZ("Dezembro",12 );
	
	private String nome;
	private int seq;
	
	public static MESES getMes(int mes){
		for(MESES d : MESES.values()){
			if(d.getSeq() == mes){
				return d;
			}
		}
		return MESES.JAN;
	}
	
	private MESES(String nome,int seq ) {
		this.nome = nome;
		this.seq = seq;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
}
