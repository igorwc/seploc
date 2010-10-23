package br.seploc.mbeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.seploc.dao.RequisicaoServicoDAO;
import br.seploc.pojos.Cliente;
import br.seploc.pojos.RequisicaoServico;

public class ReqServClientePeriodoMB implements Serializable{

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private Date dataInicio;
	private Date dataFim;
	private Double desconto = 0.;
	private List<RequisicaoServico> listaRequisicoes;
	private String dataInicioStr="", dataFimStr= "";
	private Double valorTotalRequisicoes;
	private Double valorTotalDesconto;

	//METODOS NEGOCIO
	public List<RequisicaoServico> buscaRequisicoes() {
		RequisicaoServicoDAO dao = new RequisicaoServicoDAO();
		List<RequisicaoServico> retorno = null;
		System.out.println("Valor datainicio busca: "+dataInicio);
		System.out.println("Valor dataInicioStr busca: "+dataInicioStr);
		if (dataInicio != null && dataFim != null && cliente != null
				&& cliente.getIdCliente() != null
				&& cliente.getIdCliente() != 0){
			retorno = dao.getListaPorPeriodo(new java.sql.Date(dataInicio.getTime()), new java.sql.Date(dataFim.getTime()),
					cliente.getIdCliente());
			System.out.println(dataInicio);
		}
		if (retorno == null || retorno.isEmpty()) {
			listaRequisicoes = new ArrayList<RequisicaoServico>();
			atualizaValorTotalRequisicoes();
			return listaRequisicoes;
		}
		listaRequisicoes = retorno;
		atualizaValorTotalRequisicoes();
		for (RequisicaoServico r : retorno ) {
			System.out.println(r);
		}
		return retorno;
	}
	//CONSTRUTOR PADRÃO
	public ReqServClientePeriodoMB() {
		cliente = new Cliente();
		dataInicio  = new Date(Calendar.getInstance().getTimeInMillis());
		dataFim = new Date(Calendar.getInstance().getTimeInMillis());
		desconto = 0.0;
	}

	public Double atualizaValorTotalRequisicoes() {
		Double retorno = 0.0;
		NumberFormat formatter = new DecimalFormat("#.##");
		if (listaRequisicoes != null) {
			for (RequisicaoServico r : listaRequisicoes) {
				retorno += r.getValorTotal();
			}
		}
		valorTotalRequisicoes = retorno;
		if(retorno == 0.0){
			valorTotalDesconto = 0.0;
		}else{
			valorTotalDesconto = retorno - ((retorno*desconto)/100);
			valorTotalDesconto = Double.parseDouble(formatter.format(valorTotalDesconto).replace(',', '.'));
		}
		
		return retorno;
	}
	public void putReportParameters(){
		
	}
	//GETTERS AND SETTERS
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
		System.out.println("Setou data "+this.dataInicio);
	}

	public void setDataInicio(String dataInicio)   {
		   DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		    Date date = null;
		    System.out.println("nova data"+dataInicio);
			try {
				date = (Date)formatter.parse(dataInicio);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		this.dataInicio =date;
		System.out.println("Setou datadddd");
	}
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = new Date (Date.parse(dataFim));;
	}
	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public List<RequisicaoServico> getListaRequisicoes() {
		if(listaRequisicoes == null || listaRequisicoes.isEmpty()){
			return new ArrayList<RequisicaoServico>();
		}
		return listaRequisicoes;
	}

	public void setListaRequisicoes(List<RequisicaoServico> listaRequisicoes) {
		this.listaRequisicoes = listaRequisicoes;
	}
	public String getDataInicioStr() {
		return dataInicioStr;
	}
	public void setDataInicioStr(String dataInicioStr) {
		setDataInicio(dataInicioStr);
		this.dataInicioStr = dataInicioStr;
	}
	public String getDataFimStr() {
		return dataFimStr;
	}
	public void setDataFimStr(String dataFimStr) {
		System.out.println(dataFimStr);
		this.dataFimStr = dataFimStr;
	}
	public Double getValorTotalRequisicoes() {
		return valorTotalRequisicoes;
	}
	public void setValorTotalRequisicoes(Double valorTotalRequisicoes) {
		this.valorTotalRequisicoes = valorTotalRequisicoes;
	}
	public Double getValorTotalDesconto() {
		return valorTotalDesconto;
	}
	public void setValorTotalDesconto(Double valorTotalDesconto) {
		this.valorTotalDesconto = valorTotalDesconto;
	}
	
	
	
}
