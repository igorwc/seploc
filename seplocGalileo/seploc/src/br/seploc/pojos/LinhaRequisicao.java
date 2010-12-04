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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;


/**
 * The persistent class for the tbl_linhareq database table.
 * 
 */
@Entity
@Table(name="tbl_linhareq")
@SqlResultSetMapping(name = "LinhaRequisicao.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.LinhaRequisicao.class))
@NamedNativeQueries( {
		@NamedNativeQuery(name = "LinhaReqServ.RetornaLinhasReqServs", query = " SELECT * "
				+ "FROM tbl_linhareq l", resultSetMapping = "LinhaRequisicao.implicit") 
})

public class LinhaRequisicao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LinhaRequisicaoPK id;

	@Column(name="vcrNomeArq", nullable = false)
	private String nomeArquivo;
	
	@Column(name="dblFormato")
	private Double formato;
	
	@Column(name="dblDimensao")
	private Double dimensao;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="intCodPap", referencedColumnName = "intCodPap", nullable = false)
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
	@JoinColumn(name="intNumreq", referencedColumnName = "intNumreq", updatable = false, insertable= false)
	private RequisicaoServico reqServico;

	
	
	public LinhaRequisicao() {
	}

	
	public LinhaRequisicao(LinhaRequisicaoPK id) {
		this.id = id;
	}

	public LinhaRequisicao(LinhaRequisicaoPK id, Papel papel,
			RequisicaoServico reqServico) {
		this.id = id;
		this.papel = papel;
		this.reqServico = reqServico;
	}


	@Transient
	public Integer getNumLinha() {
		return getId().getNumLinha();
	}

	@Transient
	public Integer getNumRequisicao() {
		return getId().getNumRequisicao();
	}
	public LinhaRequisicaoPK getId() {
		return id;
	}

	public void setId(LinhaRequisicaoPK id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo.toUpperCase();
	}

	public Double getFormato() {
		return formato;
	}

	public void setFormato(Double formato) {
		this.formato = formato;
	}

	public Double getDimensao() {
		return dimensao;
	}

	public void setDimensao(Double dimensao) {
		this.dimensao = dimensao;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public String getImpressao() {
		return impressao;
	}

	public void setImpressao(String impressao) {
		this.impressao = impressao;
	}

	public Integer getQuant() {
		return quant;
	}

	public void setQuant(Integer quant) {
		this.quant = quant;
	}

	public Double getValorUnit() {
		return valorUnit;
	}

	public void setValorUnit(Double valorUnit) {
		this.valorUnit = valorUnit;
	}

	public Double getValorSubUnit() {
		return valorSubUnit;
	}

	public void setValorSubUnit(Double valorSubUnit) {
		this.valorSubUnit = valorSubUnit;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public RequisicaoServico getReqServico() {
		return reqServico;
	}

	public void setReqServico(RequisicaoServico reqServico) {
		this.reqServico = reqServico;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "LinhaRequisicao [" + (id != null ? "id=" + id : "") + "]";
	}

	

}