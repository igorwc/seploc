package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

@Entity(name = "tbl_entrega")
@SqlResultSetMapping(name = "Entrega.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Papel.class))
@NamedNativeQueries( { @NamedNativeQuery(name = "Entrega.RetornaEntregas", query = " SELECT e.intCodEnt, e.vcrLocal, "
		+ "e.dblPreco, e.tspVersao "
		+ "FROM tbl_entrega e", resultSetMapping = "Entrega.implicit") })
public class Entrega implements Serializable{

	private static final long serialVersionUID = -7267594499691860223L;
	@Id
	// @Column(name = "intCodCobr")
	@GeneratedValue(generator = "entrega_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "entrega_id", table = "ID_GEN", initialValue = 1, allocationSize = 1, pkColumnName = "NOME_ID", 
			        valueColumnName = "VAL_ID", pkColumnValue = "ENTREGA_GEN")
	private Integer codigoEntrega;
	@Column(name = "vcrLocal", nullable = true)
	private String local;
	@Column(name = "dblPreco", nullable = true)
	private Double preco;
	@Column(name = "tspVersao")
	@Version
	private Timestamp versao;
	
	
	
	public Entrega(Integer codigoEntrega, String local, Double preco,
			Timestamp versao) {
		this.setCodigoEntrega(codigoEntrega);
		this.setLocal(local);
		this.setPreco(preco);
		this.setVersao(versao);
	}
	public Entrega(String local, Double preco) {
		this.setLocal(local);
		this.setPreco(preco);
	}
	public Entrega() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCodigoEntrega() {
		return codigoEntrega;
	}
	public void setCodigoEntrega(Integer codigoEntrega) {
		this.codigoEntrega = codigoEntrega;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = (local == null)?"":local;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Timestamp getVersao() {
		return versao;
	}
	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}

/*
 * 
 * CREATE TABLE IF NOT EXISTS tbl_entrega (
  intCodEnt int(4) NOT NULL DEFAULT '0',
  vcrLocal varchar(20) NOT NULL DEFAULT '',
  dblPreco double(10,2) DEFAULT NULL,
  PRIMARY KEY (intCodEnt)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
 * 
 * 
 */
