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
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tbl_bairros")
@SqlResultSetMapping(name = "Bairro.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Bairro.class))
@NamedNativeQueries({
		@NamedNativeQuery(name = "Bairro.RetornaBairros", query = " SELECT * "
				+ "FROM tbl_bairros b", resultSetMapping = "Bairro.implicit"),
		@NamedNativeQuery(name = "Bairro.RetornaBairrosPorCidade", query = "SELECT * "
				+ "FROM tbl_bairros b " 
				+ "WHERE b.intCodCidade = :codigo", resultSetMapping = "Bairro.implicit") })
public class Bairro implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "intCod")
	private Integer id;
	
	@Column(name = "vcrNome")
	private String nome;
	
	@Column(name = "intCodCidade")
	private Integer cidade;
	
	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	public Bairro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cidade
	 */
	public Integer getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(Integer cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the versao
	 */
	public Timestamp getVersao() {
		return versao;
	}

	/**
	 * @param versao the versao to set
	 */
	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bairro [id=" + id + ", nome=" + nome + "]";
	}
	
	
}
