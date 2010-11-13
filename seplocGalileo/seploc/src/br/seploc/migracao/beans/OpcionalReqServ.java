package br.seploc.migracao.beans;

public class OpcionalReqServ {

	private Integer numReq;
	private String mnemonicoOp;
	private String nome;
	private Integer qtd;
	private Integer idOP;

	public OpcionalReqServ() {
	}

	public Integer getNumReq() {
		return numReq;
	}

	public void setNumReq(Integer numReq) {
		this.numReq = numReq;
	}

	public String getMnemonicoOp() {
		return mnemonicoOp;
	}

	public void setMnemonicoOp(String mnemonicoOp) {
		this.mnemonicoOp = mnemonicoOp;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Integer getIdOP() {
		return idOP;
	}

	public void setIdOP(Integer idOP) {
		this.idOP = idOP;
	}

	@Override
	public String toString() {
		String s = "Req: "+ numReq +" codOP:" + idOP;
		return s;
	}
}
