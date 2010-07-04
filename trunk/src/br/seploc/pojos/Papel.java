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
import javax.persistence.TableGenerator;
import javax.persistence.Version;

@Entity
@Table(name = "tbl_papel")
@SqlResultSetMapping(name = "Papel.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Papel.class))
		@NamedNativeQueries( { 
			@NamedNativeQuery(name = "Papel.RetornaPapeis", 
					query = " SELECT p.intCodPap, p.vcrNome, "
							+ "p.dblImpMono, p.dblImpColor, p.dblImpShade, p.tspVersao "
							+ "FROM tbl_papel p", resultSetMapping = "Papel.implicit"),
			@NamedNativeQuery(name = "Papel.ContaPapelLinhaRequisicao", 
					query = " SELECT count(*) as contagem "
							+ "FROM tbl_papel p "
							+ "WHERE intCodPap in (" 
							                      +"SELECT intCodPap " 
							                      +"FROM tbl_linhareq lr " 
							                      +"WHERE lr = :codPapel " 
							+")", resultSetMapping = "Papel.implicit"),
			@NamedNativeQuery(name = "Papel.RetornaPapeisPorNome", 
					query = " SELECT p.intCodPap, p.vcrNome, "
			     			+ "p.dblImpMono, p.dblImpColor, p.dblImpShade, p.tspVersao "
							+ "FROM tbl_papel p where p.vcrNome like :nome ", resultSetMapping = "Papel.implicit"),
		})
public class Papel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "papel_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "papel_id", table = "ID_GEN", initialValue = 1, allocationSize = 1, pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", pkColumnValue = "PAPEL_GEN")
	@Column(name = "intCodPap")
	private Integer codPapel;

	@Column(name = "vcrNome")
	private String nome;

	@Column(name = "dblImpMono")
	private Double ImpMono;

	@Column(name = "dblImpColor")
	private Double ImpColor;

	@Column(name = "dblImpShade")
	private Double ImpShade;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	// @OneToMany(mappedBy="tblPapel")
	// private Set<LinhaReq> tblLinhareqs;

	public Papel() {
	}

	public Papel(String nome, Double impMono,
			Double impColor, Double impShade) {
		this.nome = nome;
		ImpMono = impMono;
		ImpColor = impColor;
		ImpShade = impShade;
	}

	public Integer getCodPapel() {
		return codPapel;
	}

	public void setCodPapel(Integer codPapel) {
		this.codPapel = codPapel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getImpMono() {
		return ImpMono;
	}

	public void setImpMono(Double impMono) {
		ImpMono = impMono;
	}

	public Double getImpColor() {
		return ImpColor;
	}

	public void setImpColor(Double impColor) {
		ImpColor = impColor;
	}

	public Double getImpShade() {
		return ImpShade;
	}

	public void setImpShade(Double impShade) {
		ImpShade = impShade;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ImpColor == null) ? 0 : ImpColor.hashCode());
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
		Papel other = (Papel) obj;
		if (ImpColor == null) {
			if (other.ImpColor != null)
				return false;
		} else if (!ImpColor.equals(other.ImpColor))
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
		return "Papel ["
				+ (codPapel != null ? "codPapel=" + codPapel + ", " : "")
				+ (nome != null ? "nome=" + nome + ", ": "")
				+ (ImpColor != null ? "ImpColor=" + ImpColor + ", " : "")
				+ (ImpMono != null ? "ImpMono=" + ImpMono + ", " : "")
				+ (ImpShade != null ? "ImpShade=" + ImpShade : "") + "]";
	}

}
