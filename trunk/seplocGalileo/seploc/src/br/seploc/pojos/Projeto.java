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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

@Entity
@Table(name="tbl_projetos")
@SqlResultSetMapping(name = "Projeto.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Projeto.class))
@NamedNativeQueries( {
		@NamedNativeQuery(name = "Projeto.RetornaProjetos", query = " SELECT * "
				+ "FROM tbl_projetos p", resultSetMapping = "Projeto.implicit"),
		@NamedNativeQuery(name = "Projeto.BuscaProjetosPorNome", query = " SELECT * "
					+ "FROM tbl_projetos p where vcrProjeto like :nome", resultSetMapping = "Projeto.implicit"),
		@NamedNativeQuery(name = "Projeto.ListaProjetosPorCliente", query = " SELECT * "
					+ "FROM tbl_projetos p where intClienteId = :cliente", resultSetMapping = "Projeto.implicit")
})
//@NamedQueries({
//	@NamedQuery(name="Projeto.ListaProjetosPorCliente", query="select p " +
//			                                                  "from br.seploc.pojos.Projeto " +
//			                                                  "where p.cliente.idCliente = :codCliente ")
//})

public class Projeto implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "projeto_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "projeto_id", table = "ID_GEN", 
			        allocationSize = 1, initialValue = 1, pkColumnName = "NOME_ID", 
			        valueColumnName = "VAL_ID", pkColumnValue = "PROJETO_GEN")
	@Column(name = "intCodProj")
	private Integer codProj;

	@Column(name = "vcrProjeto", nullable = false)
	private String projeto;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intClienteId", referencedColumnName = "intClienteId", nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "projeto", fetch = FetchType.LAZY)
	private List<RequisicaoServico> requisicoes;

	public Projeto() {
		setRequisicoes(new ArrayList<RequisicaoServico>());
	}

	public Projeto(String projeto) {
		this.projeto = projeto;
	}

	public Integer getCodProj() {
		return codProj;
	}

	public void setCodProj(Integer codProj) {
		this.codProj = codProj;
	}

	public String getProjeto() {
		return projeto;
	}

	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<RequisicaoServico> getRequisicoes() {
		return requisicoes;
	}

	public void setRequisicoes(List<RequisicaoServico> requisicoes) {
		this.requisicoes = requisicoes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codProj == null) ? 0 : codProj.hashCode());
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
		Projeto other = (Projeto) obj;
		if (codProj == null) {
			if (other.codProj != null)
				return false;
		} else if (!codProj.equals(other.codProj))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Projeto ["
				+ (codProj != null ? "codProj=" + codProj + ", " : "")
				+ (projeto != null ? "projeto=" + projeto : "") + "]";
	}


}