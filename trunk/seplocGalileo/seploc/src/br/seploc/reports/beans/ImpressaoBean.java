package br.seploc.reports.beans;

public class ImpressaoBean {

	String seq;
	String numReq;
	String item;
	String formato;
	String dimensao;
	String nomePapel;
	String impressao;
	String qtd;
	String subTotal;
	String valorItem;
	String linha;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getNumReq() {
		return numReq;
	}

	public void setNumReq(String numReq) {
		this.numReq = numReq;
	}

	public String getItem() {
		if (item == null) {
			return "";
		} else {
			return item;
		}
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getFormato() {
		if (formato == null) {
			return "";
		} else {
			return formato;
		}
	}

	public void setFormato(String formato) {
		if (formato.equals("0.0")
				&& (nomePapel == null || nomePapel.equals("----"))) {
			this.formato = "----";
		} else {
			this.formato = formato;
		}
	}

	public String getDimensao() {
		if (dimensao == null) {
			return "";
		} else {
			return dimensao;
		}
	}

	public void setDimensao(String dimensao) {
		if (dimensao.equals("0.0")
				&& (nomePapel == null || nomePapel.equals("----"))) {
			this.dimensao = "----";
		} else {
			this.dimensao = dimensao;
		}
	}

	public String getNomePapel() {
		if (nomePapel == null) {
			return "";
		} else {
			return nomePapel;
		}
	}

	public void setNomePapel(String nomePapel) {
		if (nomePapel == null) {
			this.nomePapel = "----";
		} else {
			this.nomePapel = nomePapel;
		}
	}

	public String getImpressao() {
		if (impressao == null) {
			return "";
		} else {
			return impressao;
		}
	}

	public void setImpressao(String impressao) {
		if (impressao == null) {
			this.impressao = "----";
		} else {
			this.impressao = impressao;
		}
	}

	public String getQtd() {
		return qtd;
	}

	public void setQtd(String qtd) {
		this.qtd = qtd;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getValorItem() {
		return valorItem;
	}

	public void setValorItem(String valorItem) {
		this.valorItem = valorItem;
	}

	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		if (linha.equals("0")
				&& (nomePapel == null || nomePapel.equals("----"))) {
			this.linha = "----";
		} else {
			this.linha = dimensao;
		}
	}

	@Override
	public String toString() {
		return "ImpressaoBean [seq=" + seq + ", formato=" + formato
				+ ", impressao=" + impressao + ", item=" + item + ", linha="
				+ linha + ", nomePapel=" + nomePapel + ", numReq=" + numReq
				+ ", qtd=" + qtd + ", dimensao=" + dimensao + ", subTotal="
				+ subTotal + ", valorItem=" + valorItem + "]";
	}

}
