package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.QueryHint;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tbl_clientes")
@SqlResultSetMapping(name = "Cliente.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Cliente.class))
@NamedNativeQueries( {
		@NamedNativeQuery(name = "Cliente.RetornaClientes", query = " SELECT * "
				+ "FROM tbl_clientes c", resultSetMapping = "Cliente.implicit",hints={@QueryHint(name = "org.hibernate.cacheable", value = "true")}),
		@NamedNativeQuery(name = "Cliente.RetornaClientePorFantasia", query = " SELECT * "
				+ "FROM tbl_clientes c where vcrFantasia = :nome", resultSetMapping = "Cliente.implicit"),				
		@NamedNativeQuery(name = "Cliente.BuscaClientesPorFantasia", query = " SELECT * "
				+ "FROM tbl_clientes c where  UPPER(vcrFantasia) like :nome and intBalcao = 0", resultSetMapping = "Cliente.implicit"),				
		@NamedNativeQuery(name = "Cliente.BuscaClientesPorRazao", query = " SELECT * "
				+ "FROM tbl_clientes c where vcrRazao like :nome", resultSetMapping = "Cliente.implicit"),	
		@NamedNativeQuery(name = "Cliente.BuscaClientesCadastrados", query = " SELECT * "
					+ "FROM tbl_clientes c where intBalcao = 0", resultSetMapping = "Cliente.implicit",hints={@QueryHint(name = "org.hibernate.cacheable", value = "true")}),					
		@NamedNativeQuery(name = "Cliente.BuscaClientesPorCPF", query = " SELECT * "
				+ "FROM tbl_clientes c where vcrCpf like :CPF", resultSetMapping = "Cliente.implicit"),				
		@NamedNativeQuery(name = "Cliente.BuscaClientesPorCNPJ", query = " SELECT * "
				+ "FROM tbl_clientes c where vcrCnpj like :CNPJ", resultSetMapping = "Cliente.implicit")/*,
		@NamedNativeQuery(name = "Cliente.ContaClientes", query = " SELECT count(*) "
					+ "FROM tbl_clientes c", resultClass = Long.class)*/
}) 
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "cliente_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "cliente_id", table = "ID_GEN", 
			        allocationSize = 1, initialValue = 1, pkColumnName = "NOME_ID", 
			        valueColumnName = "VAL_ID", pkColumnValue = "CLIENTE_GEN")
	@Column(name = "intClienteId")
	private Integer idCliente;
	
	@Column(name = "vcrCnpj") 
	private String cnpj;

	@Column(name = "vcrRazao", nullable = false)
	private String razao;

	@Column(name = "vcrCpf")
	private String cpf;

	@Column(name = "vcrEnder")
	private String endereco;

	@Column(name = "vcrBairro")
	private String bairro;

	@Column(name = "vcrCidade")
	private String cidade;

	@Column(name = "vcrEstado")
	private String estado;

	@Column(name = "vcrCep")
	private String cep;

	@Column(name = "vcrEmail")
	private String email;

	@Column(name = "vcrInscricao")
	private String inscricao;

	@Column(name = "intBalcao")
	private Integer balcao;

	@Column(name = "vcrFantasia", nullable = false)
	private String fantasia;

	@Lob()
	@Column(name = "txtobs")
	private String obs;

	@Column(name = "vcrMapa")
	private String mapa;
	
	@Column(name = "chrPendente")
	private String pendente;	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intEntregaPadrao", referencedColumnName = "intCodEnt")
	private Entrega entregaPadrao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intPapelPadrao", referencedColumnName = "intCodPap")
	private Papel papelPadrao;
	
	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;
	
	@OneToOne(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = 
			CascadeType.ALL )
	private FoneCliente foneCliente;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Projeto> projetos;

	public Cliente() {
		setProjetos(new ArrayList<Projeto>());
	}

	
	public Cliente(String razao) {
		this();
		this.razao = razao;
		this.fantasia = razao;
	}	
	
	public Cliente(String cnpj, String razao, String cpf, String endereco,
			String bairro, String cidade, String estado, String cep,
			String email, String fantasia) {
		this();
		this.cnpj = cnpj;
		this.razao = razao;
		this.cpf = cpf;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.email = email;
		this.fantasia = fantasia;
	}


	public Cliente(String cnpj, String razao, String cpf, String endereco,
			String bairro, String cidade, String estado, String cep,
			String email, String inscricao, Integer balcao, String fantasia,
			String obs, String mapa, Entrega entregaPadrao, Papel papelPadrao) {
		this();
		this.cnpj = cnpj;
		this.razao = razao;
		this.cpf = cpf;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.email = email;
		this.inscricao = inscricao;
		this.balcao = balcao;
		this.fantasia = fantasia;
		this.obs = obs;
		this.mapa = mapa;
		this.entregaPadrao = entregaPadrao;
		this.papelPadrao = papelPadrao;
	}


	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = (cnpj == null)?"":cnpj;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = (razao == null)?"":razao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public Integer getBalcao() {
		return balcao;
	}

	public void setBalcao(Integer balcao) {
		this.balcao = balcao;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = (fantasia == null)?"":fantasia;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getMapa() {
		return mapa;
	}

	public void setMapa(String mapa) {
		this.mapa = mapa;
	}

	public void setPendente(String pendente) {
		this.pendente = pendente;
	}


	public String getPendente() {
		return pendente;
	}


	public Entrega getEntregaPadrao() {
		return entregaPadrao;
	}

	public void setEntregaPadrao(Entrega entregaPadrao) {
		this.entregaPadrao = entregaPadrao;
	}

	public Papel getPapelPadrao() {
		return papelPadrao;
	}

	public void setPapelPadrao(Papel papelPadrao) {
		this.papelPadrao = papelPadrao;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public FoneCliente getFoneCliente() {
		return foneCliente;
	}

	public void setFoneCliente(FoneCliente foneCliente) {
		this.foneCliente = foneCliente;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCliente == null) ? 0 : idCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
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
		return "Cliente ["
		        + (idCliente != null ? "idCliente=" + idCliente + ", " : "")
				+ (fantasia != null ? "fantasia=" + fantasia + ", " : "")
				+ (razao != null ? "razao=" + razao : "") + "]";
	}

	
}