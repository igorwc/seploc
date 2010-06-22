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

@Entity(name = "tbl_cobrador")
@SqlResultSetMapping(name = "Cobrador.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Cobrador.class))
@NamedNativeQueries( { @NamedNativeQuery(name = "Cobrador.RetornaCobradores", query = " SELECT intCodCobr, vcrNome, "
		+ "vcrFoneCon, tspVersao " + "FROM tbl_cobrador c", resultSetMapping = "Cobrador.implicit")

// ,@NamedNativeQuery(name = "Status.FiltraStatus", query =
// "SELECT s.STS_ID, s.DESCRICAO FROM Status s "
// + " WHERE s.DESCRICAO like :STATUS", resultSetMapping = "Status.implicit"),
// @NamedNativeQuery(name = "Status.ContaStatus", query =
// "SELECT  count(*) FROM Status s ", resultSetMapping = "Status.implicit"),
// @NamedNativeQuery(name = "Status.BuscaPorNome", query =
// "SELECT * FROM Status s where "
// + "s.DESCRICAO = :status", resultSetMapping = "Status.implicit")
// ,@NamedNativeQuery(name = "Status.ContaEvolucoesStatus", query =
// "SELECT count(*) FROM Status s"
// +
// "where s.STS_ID = :status and s.STS_ID in (select EVDE_STS_ID from evolucao_demanda e)")
})
public class Cobrador implements Serializable {

	private static final long serialVersionUID = -2529422977775910092L;

	@Id
//	@Column(name = "intCodCobr")
	@GeneratedValue(generator = "cob_id",strategy=GenerationType.TABLE)
	@TableGenerator(name = "cob_id", table = "ID_GEN", allocationSize=1,
			pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", pkColumnValue = "COB_GEN")
	private Integer intCodCobr;

	@Column(name = "vcrNome", nullable = false)
	private String nome;
	@Column(name = "vcrFoneCon", nullable = true)
	private String fone;
	@Column(name = "tspVersao")
	@Version
	private Timestamp versao;

	public Cobrador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cobrador(Integer intCodCobr, String nome, String fone,
			Timestamp versao) {
		this.setIntCodCobr(intCodCobr);
		this.setNome(nome);
		this.setFone(fone);
		this.setVersao(versao);
	}

	public Cobrador(Integer intCodCobr, String nome, String fone) {
		this.setIntCodCobr(intCodCobr);
		this.setNome(nome);
		this.setFone(fone);
	}

	public Integer getIntCodCobr() {
		return intCodCobr;
	}

	public void setIntCodCobr(Integer intCodCobr) {
		this.intCodCobr = intCodCobr;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
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
				+ ((intCodCobr == null) ? 0 : intCodCobr.hashCode());
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
		Cobrador other = (Cobrador) obj;
		if (intCodCobr == null) {
			if (other.intCodCobr != null)
				return false;
		} else if (!intCodCobr.equals(other.intCodCobr))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cobrador [" + (fone != null ? "fone=" + fone + ", " : "")
				+ (intCodCobr != null ? "intCodCobr=" + intCodCobr + ", " : "")
				+ (nome != null ? "nome=" + nome : "") + "]";
	}

}
