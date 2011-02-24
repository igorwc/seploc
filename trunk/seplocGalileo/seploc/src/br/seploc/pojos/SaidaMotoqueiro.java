package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="tbl_saidamotoqueiro")
@SqlResultSetMapping(name = "SaidaMotoqueiro.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.SaidaMotoqueiro.class))
@NamedNativeQueries({ 
	@NamedNativeQuery(name = "SaidaMotoqueiro.RetornaSaidaMotoqueiro", query = " SELECT * "
		+ "FROM tbl_saidamotoqueiro sm where datDataCobr between :dataInicio and :dataFinal", resultSetMapping = "SaidaMotoqueiro.implicit"),
	@NamedNativeQuery(name = "SaidaMotoqueiro.FiltraCliente", query = " SELECT * "
		+ "FROM tbl_saidamotoqueiro sm where vcrCliente = :nome", resultSetMapping = "SaidaMotoqueiro.implicit"),	
	@NamedNativeQuery(name = "SaidaMotoqueiro.FiltraRequisicao", query = " SELECT * "
		+ "FROM tbl_saidamotoqueiro sm "
		+ " WHERE sm.intNumReq = :numReq ", resultSetMapping = "SaidaMotoqueiro.implicit")		
})

public class SaidaMotoqueiro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "motoqueiro_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "motoqueiro_id", table = "ID_GEN", initialValue = 1, 
			        allocationSize = 1, pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", 
			        pkColumnValue = "SAIDA_MOTOQUEIRO_GEN")
	@Column(name="intNumSaida")
	private Integer numSaida;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intCodCobr", referencedColumnName = "intCodCobr")
	private Cobrador cobrador;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intNumReq", referencedColumnName = "intNumReq")
	private RequisicaoServico reqServico;
    
    @Temporal( TemporalType.DATE)
    @Column(name="datDataCobr")
	private Date dataCobranca;

    @Temporal( TemporalType.DATE)
    @Column(name="datDataPag")
	private Date dataPagamento;

//    @Temporal( TemporalType.TIME)
    @Column(name="horCobranca")
	private Time horaCobranca;

//    @Temporal( TemporalType.TIME)
    @Column(name="horPagamen")
	private Time horaPagamento;

    @Column(name="vcrCliente", length=150)
	private String descCliente;

    @Column(name="vcrObs", length=200)
	private String observacoes;
    
	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	public SaidaMotoqueiro() {
	}

	public Integer getNumSaida() {
		return numSaida;
	}

	public void setNumSaida(Integer numSaida) {
		this.numSaida = numSaida;
	}

	public Cobrador getCobrador() {
		return cobrador;
	}

	public void setCobrador(Cobrador cobrador) {
		this.cobrador = cobrador;
	}

	public RequisicaoServico getReqServico() {
		return reqServico;
	}

	public void setReqServico(RequisicaoServico reqServico) {
		this.reqServico = reqServico;
	}

	public Date getDataCobranca() {
		return dataCobranca;
	}

	public void setDataCobranca(Date dataCobranca) {
		this.dataCobranca = dataCobranca;
	}
	public String getDataCobrancaFormatado(){
		SimpleDateFormat format =
            new SimpleDateFormat("dd-MM-yyyy");
		String saida = format.format(dataCobranca);
		return saida;

	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public String getDataPagamentoFormatado(){
		String saida;
		SimpleDateFormat format =
            new SimpleDateFormat("dd-MM-yyyy");
		if (dataPagamento != null) {
			saida = format.format(dataPagamento);
		} else {
			saida = null;
		}
		return saida;

	}
	
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Time getHoraCobranca() {
		return horaCobranca;
	}

	public void setHoraCobranca(Time horaCobranca) {
		this.horaCobranca = horaCobranca;
	}

	public Time getHoraPagamento() {
		return horaPagamento;
	}

	public void setHoraPagamento(Time horaPagamento) {
		this.horaPagamento = horaPagamento;
	}

	public String getDescCliente() {
		return descCliente;
	}

	public void setDescCliente(String descCliente) {
		this.descCliente = descCliente;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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
				+ ((numSaida == null) ? 0 : numSaida.hashCode());
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
		SaidaMotoqueiro other = (SaidaMotoqueiro) obj;
		if (numSaida == null) {
			if (other.numSaida != null)
				return false;
		} else if (!numSaida.equals(other.numSaida))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SaidaMotoqueiro ["
				+ (descCliente != null ? "descCliente=" + descCliente + ", "
						: "")
				+ (horaCobranca != null ? "horaCobranca=" + horaCobranca + ", "
						: "")
				+ (horaPagamento != null ? "horaPagamento=" + horaPagamento
						+ ", " : "")
				+ (numSaida != null ? "numSaida=" + numSaida : "") + "]";
	}

  
}