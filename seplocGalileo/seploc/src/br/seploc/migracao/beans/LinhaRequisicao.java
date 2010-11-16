package br.seploc.migracao.beans;

public class LinhaRequisicao {
	private Integer intNumreq;
	private Integer intNumLin;
	private String vcrNomeArq;
	private Double dblFormato; 
	private Double dblDimensao; 
	private Integer intCodPap;
	private String vcrImpressao; 
	private Integer intQuant;
	private Double dblValorUnit;
	private Double dblValorSubUnit;
	public Integer getIntNumreq() {
		return intNumreq;
	}
	public void setIntNumreq(Integer intNumreq) {
		this.intNumreq = intNumreq;
	}
	public Integer getIntNumLin() {
		return intNumLin;
	}
	public void setIntNumLin(Integer intNumLin) {
		this.intNumLin = intNumLin;
	}
	public String getVcrNomeArq() {
		return vcrNomeArq;
	}
	public void setVcrNomeArq(String vcrNomeArq) {
		this.vcrNomeArq = vcrNomeArq;
	}
	public Double getDblFormato() {
		return dblFormato;
	}
	public void setDblFormato(Double dblFormato) {
		this.dblFormato = dblFormato;
	}
	public Double getDblDimensao() {
		return dblDimensao;
	}
	public void setDblDimensao(Double dblDimensao) {
		this.dblDimensao = dblDimensao;
	}
	public Integer getIntCodPap() {
		return intCodPap;
	}
	public void setIntCodPap(Integer intCodPap) {
		this.intCodPap = intCodPap;
	}
	public String getVcrImpressao() {
		return vcrImpressao;
	}
	public void setVcrImpressao(String vcrImpressao) {
		this.vcrImpressao = vcrImpressao;
	}
	public Integer getIntQuant() {
		return intQuant;
	}
	public void setIntQuant(Integer intQuant) {
		this.intQuant = intQuant;
	}
	public Double getDblValorUnit() {
		return dblValorUnit;
	}
	public void setDblValorUnit(Double dblValorUnit) {
		this.dblValorUnit = dblValorUnit;
	}
	public Double getDblValorSubUnit() {
		return dblValorSubUnit;
	}
	public void setDblValorSubUnit(Double dblValorSubUnit) {
		this.dblValorSubUnit = dblValorSubUnit;
	}
	@Override
	public String toString() {
		return "LinhaRequisicao [dblDimensao=" + dblDimensao + ", dblFormato="
				+ dblFormato + ", dblValorSubUnit=" + dblValorSubUnit
				+ ", dblValorUnit=" + dblValorUnit + ", intCodPap=" + intCodPap
				+ ", intNumLin=" + intNumLin + ", intNumreq=" + intNumreq
				+ ", intQuant=" + intQuant + ", vcrImpressao=" + vcrImpressao
				+ ", vcrNomeArq=" + vcrNomeArq + "]";
	}
	
	
}
