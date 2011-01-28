package br.seploc.migracao.beans;

import java.sql.Date;

public class ReqServUsuario {

	private Integer intCodUsr;
	private Integer intNumReq;
	private Date datData;
	private Integer intCodUsrAlter;
	private Date datdataAlter;
	public ReqServUsuario(Integer intCodUsr, Integer intNumReq, Date datData,
			Integer intCodUsrAlter, Date datdataAlter) {
		this.intCodUsr = intCodUsr;
		this.intNumReq = intNumReq;
		this.datData = datData;
		this.intCodUsrAlter = intCodUsrAlter;
		this.datdataAlter = datdataAlter;
	}
	public ReqServUsuario() {
		// TODO Auto-generated constructor stub
	}
	public Integer getIntCodUsr() {
		return intCodUsr;
	}
	public void setIntCodUsr(Integer intCodUsr) {
		this.intCodUsr = intCodUsr;
	}
	public Integer getIntNumReq() {
		return intNumReq;
	}
	public void setIntNumReq(Integer intNumReq) {
		this.intNumReq = intNumReq;
	}
	public Date getDatData() {
		return datData;
	}
	public void setDatData(Date datData) {
		this.datData = datData;
	}
	public Integer getIntCodUsrAlter() {
		return intCodUsrAlter;
	}
	public void setIntCodUsrAlter(Integer intCodUsrAlter) {
		this.intCodUsrAlter = intCodUsrAlter;
	}
	public Date getDatdataAlter() {
		return datdataAlter;
	}
	public void setDatdataAlter(Date datdataAlter) {
		this.datdataAlter = datdataAlter;
	}
	@Override
	public String toString() {
		return "ReqServUsuario [datData=" + datData + ", datdataAlter="
				+ datdataAlter + ", intCodUsr=" + intCodUsr
				+ ", intCodUsrAlter=" + intCodUsrAlter + ", intNumReq="
				+ intNumReq + "]";
	}
	
	
}
