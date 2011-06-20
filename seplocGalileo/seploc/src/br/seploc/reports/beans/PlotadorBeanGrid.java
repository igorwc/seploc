package br.seploc.reports.beans;

public class PlotadorBeanGrid implements Comparable<PlotadorBeanGrid>{
	private int seq;
	private String nome;
	private int quantidade;
	private int id;
	private double valorMoeda;
	
	public PlotadorBeanGrid() {
		// TODO Auto-generated constructor stub
	}

	public PlotadorBeanGrid(int seq, String nome, int quantidade) {
		this.seq = seq;
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public PlotadorBeanGrid(int seq, String nome, int quantidade, double valorMoeda) {
		this.seq = seq;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valorMoeda = valorMoeda;
	}
	
	public PlotadorBeanGrid(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public PlotadorBeanGrid(String nome, int quantidade, double valorMoeda) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.valorMoeda = valorMoeda;
	}

	public PlotadorBeanGrid(String nome, int quantidade, int id) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.id = id;
	}

	public PlotadorBeanGrid(String nome, int quantidade, int id, double valorMoeda) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.id = id;
		this.valorMoeda = valorMoeda;		
	}
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValorMoeda() {
		return valorMoeda;
	}

	public void setValorMoeda(double valorMoeda) {
		this.valorMoeda = valorMoeda;
	}

	@Override
	public int compareTo(PlotadorBeanGrid o) {
		// TODO Auto-generated method stub
		return (int) Math.round(o.getValorMoeda()) - (int) Math.round(this.valorMoeda);
	}

	@Override
	public String toString() {
		return "PlotadorBeanGrid [nome=" + nome + ", quantidade=" + quantidade
				+ ", seq=" + seq + ", valorMoeda=" + valorMoeda +"]";
	}	
}