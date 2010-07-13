package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

@Entity
@Table(name = "tbl_opcionaisreqserv")
@SqlResultSetMapping(name = "OpcionaisReqServ.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.OpcionaisReqServ.class))
@NamedNativeQueries( {
		@NamedNativeQuery(name = "OpcionaisReqServ.RetornaOpcionais", query = " SELECT * "
				+ "FROM tbl_opcionaisreqserv op", resultSetMapping = "OpcionaisReqServ.implicit"),
		@NamedNativeQuery(name = "OpcionaisReqServ.BuscaOpcionaisPorNome", query = " SELECT * "
					+ "FROM tbl_opcionaisreqserv p where vcrNomeItem like :nome", resultSetMapping = "OpcionaisReqServ.implicit")
})
public class OpcionaisReqServ implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "opreqrerv_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "opreqrerv_id", table = "ID_GEN", initialValue = 1, 
			        allocationSize = 1, pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", 
			        pkColumnValue = "OP_REQ_SERV_GEN")
	@Column(name = "intCod")
	private Integer codOpReqServ;

	@Column(name = "vcrNomeItem", length = 40)
	private String nomeItem;

	@Column(name = "dblValorItem")
	private Double valorItem;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@OneToMany(mappedBy = "opcionaisReqServ")
	private List<ReqServicosOpcionais> reqServOpcionais;

	public OpcionaisReqServ() {
		setReqServOpcionais(new ArrayList<ReqServicosOpcionais>());
	}

	public OpcionaisReqServ(String nomeItem, Double valorItem) {
		this();
		this.nomeItem = nomeItem;
		this.valorItem = valorItem;
	}

	public Integer getCodOpReqServ() {
		return codOpReqServ;
	}

	public void setCodOpReqServ(Integer codOpReqServ) {
		this.codOpReqServ = codOpReqServ;
	}

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public Double getValorItem() {
		return valorItem;
	}

	public void setValorItem(Double valorItem) {
		this.valorItem = valorItem;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public List<ReqServicosOpcionais> getReqServOpcionais() {
		return reqServOpcionais;
	}

	public void setReqServOpcionais(List<ReqServicosOpcionais> reqServOpcionais) {
		this.reqServOpcionais = reqServOpcionais;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codOpReqServ == null) ? 0 : codOpReqServ.hashCode());
		result = prime * result + ((versao == null) ? 0 : versao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpcionaisReqServ other = (OpcionaisReqServ) obj;
		if (codOpReqServ == null) {
			if (other.codOpReqServ != null)
				return false;
		} else if (!codOpReqServ.equals(other.codOpReqServ))
			return false;
		if (versao == null) {
			if (other.versao != null)
				return false;
		} else if (!versao.equals(other.versao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OpcionaisReqServ ["
				+ (codOpReqServ != null ? "codOpReqServ=" + codOpReqServ + ", "
						: "")
				+ (nomeItem != null ? "nomeItem=" + nomeItem + ", " : "")
				+ (valorItem != null ? "valorItem=" + valorItem : "") + "]";
	}

}