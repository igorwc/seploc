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

/**
 * The persistent class for the tbl_grupo database table.
 * 
 */
@Entity
@Table(name = "tbl_grupo")
@SqlResultSetMapping(name = "Grupo.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Grupo.class))
@NamedNativeQueries( {
		@NamedNativeQuery(name = "Grupo.RetornaGrupos", query = " SELECT * "
				+ "FROM tbl_grupo g", resultSetMapping = "Grupo.implicit") 
})
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "grupo_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "grupo_id", table = "ID_GEN", allocationSize = 1,initialValue= 1,
			pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", pkColumnValue = "GRUPO_GEN")
	@Column(name = "intGrupo")
	private Integer codGrupo;

	@Column(name = "vcrGrupo")
	private String nomeGrupo;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
	// "id.grupo")
	private List<GrupoMenu> grupoMenus;

	public Grupo() {
		setGrupoMenus(new ArrayList<GrupoMenu>());
	}

	public Grupo(String nomeGrupo) {
		this();
		this.nomeGrupo = nomeGrupo;
	}

	public Grupo(Integer codGrupo, String nomeGrupo, Timestamp versao,
			List<GrupoMenu> grupoMenus) {
		this.codGrupo = codGrupo;
		this.nomeGrupo = nomeGrupo;
		this.versao = versao;
		this.grupoMenus = grupoMenus;
	}

	public Integer getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(Integer codGrupo) {
		this.codGrupo = codGrupo;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public List<GrupoMenu> getGrupoMenus() {
		return grupoMenus;
	}

	public void setGrupoMenus(List<GrupoMenu> grupoMenus) {
		this.grupoMenus = grupoMenus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codGrupo;
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
		Grupo other = (Grupo) obj;
		if (codGrupo != other.codGrupo)
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
		return "[" + codGrupo + ", "
				+ (getNomeGrupo() != null ? "" + getNomeGrupo() : "") + "]";
	}

}