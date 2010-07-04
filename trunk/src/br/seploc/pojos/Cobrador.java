package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
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
	// @Column(name = "intCodCobr")
	@GeneratedValue(generator = "cob_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "cob_id", table = "ID_GEN", allocationSize = 1, initialValue=1,
			pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", pkColumnValue = "COB_GEN")
	@Column(name="intCodCobr")
	private Integer codCobrador;

	@Column(name="vcrNome")
	private String nome;
	
	@Column(name="vcrFoneCon")
	private String foneContato;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	
	@OneToMany(mappedBy="cobrador")
	private List<RequisicaoServico> requisicoes;


	@OneToMany(mappedBy="cobrador")
	private List<SaidaMotoqueiro> saidamotoqueiros;
//
	@OneToMany(mappedBy="cobrador")
	private List<StatusCobranca> statusCobrancas;

  }
