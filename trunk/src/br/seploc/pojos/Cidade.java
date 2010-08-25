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
@Table(name = "tbl_cidades")
@SqlResultSetMapping(name = "Cidade.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Cidade.class))
@NamedNativeQueries({
		@NamedNativeQuery(name = "Cidade.RetornaCidades", query = " SELECT * "
				+ "FROM tbl_cidades c", resultSetMapping = "Cidade.implicit"),
		@NamedNativeQuery(name = "Cidade.RetornaCidadesPorEstado", query = "SELECT * "
				+ "FROM tbl_cidades c " + "WHERE c.intCodUF = :codigo", resultSetMapping = "Cidade.implicit") })
public class Cidade implements Serializable {

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

	@Column(name = "intCodUF")
	private Integer uf;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	public Cidade() {
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
	 * @param id
	 *            the id to set
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
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the uf
	 */
	public Integer getUf() {
		return uf;
	}

	/**
	 * @param uf
	 *            the uf to set
	 */
	public void setUf(Integer uf) {
		this.uf = uf;
	}

	/**
	 * @return the versao
	 */
	public Timestamp getVersao() {
		return versao;
	}

	/**
	 * @param versao
	 *            the versao to set
	 */
	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

}
