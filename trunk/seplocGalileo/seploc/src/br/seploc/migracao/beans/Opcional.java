package br.seploc.migracao.beans;

public class Opcional {
	private Integer id;
	private String vcrCod;
	private String vcrNomeItem;
	private Double dblValorItem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVcrCod() {
		return vcrCod;
	}

	public void setVcrCod(String vcrCod) {
		this.vcrCod = vcrCod;
	}

	public String getVcrNomeItem() {
		return vcrNomeItem;
	}

	public void setVcrNomeItem(String vcrNomeItem) {
		this.vcrNomeItem = vcrNomeItem;
	}

	public Double getDblValorItem() {
		return dblValorItem;
	}

	public void setDblValorItem(Double dblValorItem) {
		this.dblValorItem = dblValorItem;
	}

}
