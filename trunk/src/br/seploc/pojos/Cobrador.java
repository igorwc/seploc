package br.seploc.pojos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

//@Entity(name = "tbl_cobrador")
//@SqlResultSetMapping(name = "Cobrador.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Cobrador.class))
//@NamedNativeQueries( {
//		@NamedNativeQuery(name = "Cobrador.RetornaCobradores", query = " SELECT intCodCobr, vcrNome, "
//				+ "vcrFoneCon, tspVersao " + "FROM tbl_cobrador c", resultSetMapping = "Cobrador.implicit")
//		,
//		@NamedNativeQuery(name = "Cobrador.FiltraCobradores", query = " SELECT c.intCodCobr, c.vcrNome, "
//				+ "c.vcrFoneCon, c.tspVersao "
//				+ "FROM tbl_cobrador c"
//				+ " WHERE c.vcrNome like :nome", resultSetMapping = "Cobrador.implicit")
// , @NamedNativeQuery(name = "Status.ContaStatus", query =
// "SELECT  count(*) FROM Status s ", resultSetMapping = "Status.implicit"),
// @NamedNativeQuery(name = "Status.BuscaPorNome", query =
// "SELECT * FROM Status s where "
// + "s.DESCRICAO = :status", resultSetMapping = "Status.implicit")
// ,@NamedNativeQuery(name = "Status.ContaEvolucoesStatus", query =
// "SELECT count(*) FROM Status s"
// +
// "where s.STS_ID = :status and s.STS_ID in (select EVDE_STS_ID from evolucao_demanda e)")
//})
public class Cobrador implements Serializable {
	private static final long serialVersionUID = 1L;
/*
	@Id
	// @Column(name = "intCodCobr")
	@GeneratedValue(generator = "cob_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "cob_id", table = "ID_GEN", allocationSize = 1, pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", pkColumnValue = "COB_GEN")
	@Column(name="intCodCobr")
	private Integer codCobrador;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	@Column(name="vcrFoneCon")
	private String foneContato;

	@Column(name="vcrNome")
	private String nome;

	//bi-directional many-to-one association to RequisicaoServico
//	@OneToMany(mappedBy="tblCobrador")
//	private Set<RequisicaoServico> tblReqservs;
//
//	//bi-directional many-to-one association to TblSaidamotoqueiro
//	@OneToMany(mappedBy="tblCobrador")
//	private Set<TblSaidamotoqueiro> tblSaidamotoqueiros;
//
//	//bi-directional many-to-one association to StatusCobranca
//	@OneToMany(mappedBy="tblCobrador")
//	private Set<StatusCobranca> tblStatuscobrancas;

    public Cobrador() {
    }

	public Integer getCodCobrador() {
		return this.codCobrador;
	}

	public void setCodCobrador(Integer codCobrador) {
		this.codCobrador = codCobrador;
	}

	public Timestamp getVersao() {
		return this.versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public String getFoneContato() {
		return this.foneContato;
	}

	public void setFoneContato(String foneContato) {
		this.foneContato = foneContato;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public Set<RequisicaoServico> getTblReqservs() {
//		return this.tblReqservs;
//	}
//
//	public void setTblReqservs(Set<RequisicaoServico> tblReqservs) {
//		this.tblReqservs = tblReqservs;
//	}
//	
//	public Set<TblSaidamotoqueiro> getTblSaidamotoqueiros() {
//		return this.tblSaidamotoqueiros;
//	}
//
//	public void setTblSaidamotoqueiros(Set<TblSaidamotoqueiro> tblSaidamotoqueiros) {
//		this.tblSaidamotoqueiros = tblSaidamotoqueiros;
//	}
//	
//	public Set<StatusCobranca> getTblStatuscobrancas() {
//		return this.tblStatuscobrancas;
//	}
//
//	public void setTblStatuscobrancas(Set<StatusCobranca> tblStatuscobrancas) {
//		this.tblStatuscobrancas = tblStatuscobrancas;
//	}
//	
*/}
