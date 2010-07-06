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
@Table(name = "tbl_cobrador")
@SqlResultSetMapping(name = "Cobrador.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Cobrador.class))
@NamedNativeQueries( {
		@NamedNativeQuery(name = "Cobrador.RetornaCobradores", query = " SELECT intCodCobr, vcrNome, "
				+ "vcrFoneCon, tspVersao " + "FROM tbl_cobrador c", resultSetMapping = "Cobrador.implicit")
		,
		@NamedNativeQuery(name = "Cobrador.FiltraCobradores", query = " SELECT c.intCodCobr, c.vcrNome, "
				+ "c.vcrFoneCon, c.tspVersao "
				+ "FROM tbl_cobrador c"
				+ " WHERE c.vcrNome like :nome", resultSetMapping = "Cobrador.implicit")
 , @NamedNativeQuery(name = "Status.ContaStatus", query =
 "SELECT  count(*) FROM Status s ", resultSetMapping = "Status.implicit"),
 @NamedNativeQuery(name = "Status.BuscaPorNome", query =
 "SELECT * FROM Status s where "
 + "s.DESCRICAO = :status", resultSetMapping = "Status.implicit")
 ,@NamedNativeQuery(name = "Status.ContaEvolucoesStatus", query =
 "SELECT count(*) FROM Status s"
 +
 "where s.STS_ID = :status and s.STS_ID in (select EVDE_STS_ID from evolucao_demanda e)")
})
public class Cobrador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "cob_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "cob_id", table = "ID_GEN", allocationSize = 1, 
			        initialValue = 1, pkColumnName = "NOME_ID", 
			        valueColumnName = "VAL_ID", pkColumnValue = "COBRADOR_GEN")
	@Column(name = "intCodCobr")
	private Integer codCobrador;

	@Column(name = "vcrNome", nullable = false, length = 60)
	private String nome;

	@Column(name = "vcrFoneCon", length = 20)
	private String foneContato;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@OneToMany(mappedBy = "cobrador")
	private List<SaidaMotoqueiro> saidaMotoqueiros;

	@OneToMany(mappedBy = "cobrador")
	private List<StatusCobranca> statusCobrancas;

	public Cobrador() {
		setSaidaMotoqueiros(new ArrayList<SaidaMotoqueiro>());
		setStatusCobrancas(new ArrayList<StatusCobranca>());
	}

	
	public Cobrador(String nome, String foneContato) {
		this();
		this.nome = nome;
		this.foneContato = foneContato;
	}


	public Integer getCodCobrador() {
		return codCobrador;
	}

	public void setCodCobrador(Integer codCobrador) {
		this.codCobrador = codCobrador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFoneContato() {
		return foneContato;
	}

	public void setFoneContato(String foneContato) {
		this.foneContato = foneContato;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public List<SaidaMotoqueiro> getSaidaMotoqueiros() {
		return saidaMotoqueiros;
	}

	public void setSaidaMotoqueiros(List<SaidaMotoqueiro> saidaMotoqueiros) {
		this.saidaMotoqueiros = saidaMotoqueiros;
	}

	public List<StatusCobranca> getStatusCobrancas() {
		return statusCobrancas;
	}

	public void setStatusCobrancas(List<StatusCobranca> statusCobrancas) {
		this.statusCobrancas = statusCobrancas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codCobrador == null) ? 0 : codCobrador.hashCode());
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
		Cobrador other = (Cobrador) obj;
		if (codCobrador == null) {
			if (other.codCobrador != null)
				return false;
		} else if (!codCobrador.equals(other.codCobrador))
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
		return "Cobrador ["
				+ (codCobrador != null ? "codCobrador=" + codCobrador + ", "
						: "")
				+ (foneContato != null ? "foneContato=" + foneContato + ", "
						: "") + (nome != null ? "nome=" + nome : "") + "]";
	}

}
