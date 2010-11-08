/**
 * 
 */
package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.persistence.Version;

/**
 * @author Igor
 * 
 */
@Entity
@Table(name = "tbl_estados")
@SqlResultSetMapping(name = "Estado.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Estado.class))
@NamedNativeQueries({
		@NamedNativeQuery(name = "Estado.RetornaEstados", query = " SELECT * "
				+ "FROM tbl_estados e", resultSetMapping = "Estado.implicit"),
		@NamedNativeQuery(name = "Estado.RetornaEstadoPorCodigo", query = "SELECT * "
				+ "FROM tbl_estados " + "WHERE intCod = :codigo", resultSetMapping = "Estado.implicit") })
public class Estado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "intCod")
	private Integer id;
	
	@Column(name = "vcrNome")
	private String nome;
	
	@Column(name = "vcrSigla")
	private String sigla;
	
	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@OneToMany(mappedBy = "uf", fetch = FetchType.LAZY)
	private List<Cidade> cidades;
	
	public Estado() {
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
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
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
	
	
}
