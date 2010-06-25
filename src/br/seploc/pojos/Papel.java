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

@Entity(name = "tbl_papel")
@SqlResultSetMapping(name = "Papel.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Papel.class))
@NamedNativeQueries( { @NamedNativeQuery(name = "Papel.RetornaPapeis", query = " SELECT p.intCodPap, p.vcrNome, "
		+ "p.dblImpMono, p.dblImpColor, p.dblImpShade, p.tspVersao "
		+ "FROM tbl_papel p", resultSetMapping = "Papel.implicit") })
public class Papel implements Serializable {

	private static final long serialVersionUID = 697520411748021115L;
	@Id
	// @Column(name = "intCodCobr")
	@GeneratedValue(generator = "papel_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "papel_id", table = "ID_GEN", initialValue = 1, allocationSize = 1, pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", pkColumnValue = "PAPEL_GEN")
	private Integer intCodPap;
	@Column(name = "vcrNome", nullable = true)
	private String nome;
	@Column(name = "dblImpMono", nullable = true)
	private Double impMono;
	@Column(name = "dblImpColor", nullable = true)
	private Double impColor;
	@Column(name = "dblImpShade", nullable = true)
	private Double impShade;
	@Column(name = "tspVersao")
	@Version
	private Timestamp versao;

	public Papel(String nome, Double impMono, Double impColor, Double impShade) {
		this.nome = (nome == null) ? "" : nome;
		;
		this.impMono = impMono;
		this.impColor = impColor;
		this.impShade = impShade;
	}

	public Papel(Integer intCodPap, String nome, Double impMono,
			Double impColor, Double impShade) {
		this.intCodPap = intCodPap;
		this.nome = (nome == null) ? "" : nome;
		this.impMono = impMono;
		this.impColor = impColor;
		this.impShade = impShade;
	}

	public Papel() {

	}

	public Integer getIntCodPap() {
		return intCodPap;
	}

	public void setIntCodPap(Integer intCodPap) {
		this.intCodPap = intCodPap;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getImpMono() {
		return impMono;
	}

	public void setImpMono(Double impMono) {
		this.impMono = impMono;
	}

	public Double getImpColor() {
		return impColor;
	}

	public void setImpColor(Double impColor) {
		this.impColor = impColor;
	}

	public Double getImpShade() {
		return impShade;
	}

	public void setImpShade(Double impShade) {
		this.impShade = impShade;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((impColor == null) ? 0 : impColor.hashCode());
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
		Papel other = (Papel) obj;
		if (impColor == null) {
			if (other.impColor != null)
				return false;
		} else if (!impColor.equals(other.impColor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Papel ["
				+ (impColor != null ? "impColor=" + impColor + ", " : "")
				+ (impMono != null ? "impMono=" + impMono + ", " : "")
				+ (impShade != null ? "impShade=" + impShade + ", " : "")
				+ (intCodPap != null ? "intCodPap=" + intCodPap + ", " : "")
				+ (nome != null ? "nome=" + nome : "") + "]";
	}

}
