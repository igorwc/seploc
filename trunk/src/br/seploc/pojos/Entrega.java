package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
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
@Table(name = "tbl_entrega")
@SqlResultSetMapping(name = "Entrega.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Entrega.class))
@NamedNativeQueries( {
		@NamedNativeQuery(name = "Entrega.RetornaEntregas", query = " SELECT * "
				+ "FROM tbl_entrega e", resultSetMapping = "Entrega.implicit"),
		@NamedNativeQuery(name = "Entrega.BuscaEntregas", query = " SELECT * "
					+ "FROM tbl_entrega e where vcrLocal like :nome", resultSetMapping = "Entrega.implicit")
})
public class Entrega implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "entrega_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "entrega_id", table = "ID_GEN", allocationSize = 1, initialValue=1,
			pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", pkColumnValue = "ENTREGA_GEN")
	@Column(name = "intCodEnt")
	private Integer codEntrega;

	@Column(name = "vcrLocal")
	private String local;

	@Column(name = "dblPreco")
	private Double preco;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@OneToMany(mappedBy = "entrega", fetch = FetchType.LAZY)
	private List<RequisicaoServico> reqServico;

	@OneToMany(mappedBy = "entregaPadrao", fetch = FetchType.LAZY)
	private List<Cliente> clientes;
	
	public Entrega() {
		setClientes(new ArrayList<Cliente>());
		setReqServico(new ArrayList<RequisicaoServico>());
	}

	
	public Entrega(String local, Double preco) {
		this();
		this.local = local;
		this.preco = preco;
	}


	public Entrega(String local, Double preco,
			List<RequisicaoServico> reqServico, List<Cliente> clientes) {
		this.local = local;
		this.preco = preco;
		this.reqServico = reqServico;
		this.clientes = clientes;
	}


	public Integer getCodEntrega() {
		return codEntrega;
	}

	public void setCodEntrega(Integer codEntrega) {
		this.codEntrega = codEntrega;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
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

	public List<RequisicaoServico> getReqServico() {
		return reqServico;
	}

	public void setReqServico(List<RequisicaoServico> reqServico) {
		this.reqServico = reqServico;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codEntrega == null) ? 0 : codEntrega.hashCode());
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
		Entrega other = (Entrega) obj;
		if (codEntrega == null) {
			if (other.codEntrega != null)
				return false;
		} else if (!codEntrega.equals(other.codEntrega))
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
		return "Entrega ["
				+ (codEntrega != null ? "codEntrega=" + codEntrega + ", " : "")
				+ (local != null ? "local=" + local + ", " : "")
				+ (preco != null ? "preco=" + preco : "") + "]";
	}

}