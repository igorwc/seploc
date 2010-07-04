package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * The persistent class for the tbl_clientes database table.
 * 
 */

@Entity
@Table(name = "tbl_clientes")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "vcrCnpj")
	private String cnpj;
	
	@Column(name = "vcrRazao")
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
	
	@Column(name = "vcrFantasia")
	private String fantasia;
	
	@Lob()
	@Column(name = "txtobs")
	private String obs;
	
	@Column(name = "vcrMapa")
	private String mapa;
	
	@Column(name = "intEntregaPadrao")
	private Integer entregaPadrao;
	
	@Column(name = "intPapelPadrao")
	private Integer papelPadrao;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@OneToOne(mappedBy = "cliente", fetch = FetchType.LAZY)
	private FoneCliente foneCliente;

	
	 @OneToMany(mappedBy="cliente")
	 private List<Projeto> projetos;
	  
	 @OneToMany(mappedBy="cliente") 
	 private List<RequisicaoServico> requisicoes;

}