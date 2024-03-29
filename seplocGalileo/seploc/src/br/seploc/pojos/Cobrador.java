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
		@NamedNativeQuery(name = "Cobrador.RetornaCobradores", query = " SELECT * " +
				"FROM tbl_cobrador c", resultSetMapping = "Cobrador.implicit"),
		@NamedNativeQuery(name = "Cobrador.RetornaCobradoresAtivos", query = " SELECT * " +
				"FROM tbl_cobrador c" +
				" WHERE c.chrAtivo = 'S'", resultSetMapping = "Cobrador.implicit"),				
		@NamedNativeQuery(name = "Cobrador.FiltraCobradores", query = " SELECT * "
				+ "FROM tbl_cobrador c"
				+ " WHERE c.vcrNome like :nome", resultSetMapping = "Cobrador.implicit")
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
	
	@Column(name = "chrAtivo", length = 1)
	private String ativo;	

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@OneToMany(mappedBy = "cobrador")
	private List<SaidaMotoqueiro> saidaMotoqueiros;

	public Cobrador() {
		setSaidaMotoqueiros(new ArrayList<SaidaMotoqueiro>());
	}

	
	public Cobrador(String nome, String foneContato) {
		this();
		setNome(nome);
		this.foneContato = foneContato;
	}

	public Cobrador(String nome, String foneContato, String ativo) {
		this();
		setNome(nome);
		this.foneContato = foneContato;
		this.ativo = ativo;
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
		this.nome = nome.toUpperCase();
	}

	public String getFoneContato() {
		return foneContato;
	}

	public void setFoneContato(String foneContato) {
		this.foneContato = foneContato;
	}

	public String getAtivo(){
		return ativo;
	}
	
	public void setAtivo(String ativo){
		this.ativo = ativo;
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
						: "") 
				+ (ativo != null ? "ativo=" + ativo + ", "
						: "")
				+ (nome != null ? "nome=" + nome : "") + "]" ;		
	}

}
