package br.seploc.reports.beans;

public class CobradorBeanGrid implements Comparable<CobradorBeanGrid>{
	private int seq;
	private String nome;
	private int quantidade;
	private int id;

	public CobradorBeanGrid() {
		// TODO Auto-generated constructor stub
	}

	public CobradorBeanGrid(int seq, String nome, int quantidade) {
		this.seq = seq;
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public CobradorBeanGrid(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public CobradorBeanGrid(String nome, int quantidade, int id) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.id = id;
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

	@Override
	public int compareTo(CobradorBeanGrid o) {
		// TODO Auto-generated method stub
		return o.getQuantidade() - this.quantidade;
	}

	@Override
	public String toString() {
		return "CobradorBeanGrid [nome=" + nome + ", quantidade=" + quantidade
				+ ", seq=" + seq + "]";
	}

}
