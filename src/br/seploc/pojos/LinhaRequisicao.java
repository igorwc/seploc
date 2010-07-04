package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 * The persistent class for the tbl_linhareq database table.
 * 
 */
@Entity
@Table(name="tbl_linhareq")
@SqlResultSetMapping(name = "LinhaRequisicao.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.LinhaRequisicao.class))
public class LinhaRequisicao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LinhaRequisicaoPK id;

	@Column(name="vcrNomeArq")
	private String nomeArquivo;
	
	@Column(name="dblFormato")
	private Double formato;
	
	@Column(name="dblDimensao")
	private Double dimensao;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="intCodPap", referencedColumnName = "intCodPap")
	private Papel papel;
	
	@Column(name="vcrImpressao")
	private String impressao;

	@Column(name="intQuant")
	private Integer quant;

	@Column(name="dblValorUnit")
	private Double valorUnit;
	
	@Column(name="dblValorSubUnit")
	private Double valorSubUnit;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="intNumreq", referencedColumnName = "intGrupo", updatable = false, insertable= false)
	private RequisicaoServico reqServico;


}