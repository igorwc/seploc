package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

import br.seploc.util.SIMNAO01;

@Entity
@Table(name = "tbl_papel")
@SqlResultSetMapping(name = "Papel.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Papel.class))
@NamedNativeQueries( {
		@NamedNativeQuery(name = "Papel.RetornaPapeis", 
						 query = " SELECT p.intCodPap, p.vcrNome, "
							   + "p.dblImpMono, p.dblImpColor, p.dblImpShade,p.blEhPapel, p.tspVersao "
							   + "FROM tbl_papel p "  , resultSetMapping = "Papel.implicit"),
		@NamedNativeQuery(name = "Papel.RetornaSohPapeis", 
						 query = " SELECT p.intCodPap, p.vcrNome, "
							   + "p.dblImpMono, p.dblImpColor, p.dblImpShade,p.blEhPapel, p.tspVersao "
							   + "FROM tbl_papel p " 
							   + "WHERE p.blEhPapel = 1", resultSetMapping = "Papel.implicit"),
		@NamedNativeQuery(name = "Papel.RetornaLonaAdesivos", 
				          query = " SELECT p.intCodPap, p.vcrNome, "
				                + "p.dblImpMono, p.dblImpColor, p.dblImpShade,p.blEhPapel, p.tspVersao "
				                + "FROM tbl_papel p " 
				                + "WHERE p.blEhPapel = 0", resultSetMapping = "Papel.implicit"),
		@NamedNativeQuery(name = "Papel.RetornaPapeisPorNome", 
				          query = " SELECT * "
				        	  	+ "FROM tbl_papel p " 
				        	  	+ "WHERE p.blEhPapel = 1 AND p.vcrNome like :nome "
				        	  	, resultSetMapping = "Papel.implicit"), 
	     @NamedNativeQuery(name = "Papel.RetornaLonasPorNome", 
						   query = " SELECT * "
							   	 + "FROM tbl_papel p " 
								 + "WHERE p.blEhPapel = 1 AND p.vcrNome like :nome "
								 , resultSetMapping = "Papel.implicit") 
})
public class Papel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "papel_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "papel_id", table = "ID_GEN", initialValue = 1, 
			        allocationSize = 1, pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", 
			        pkColumnValue = "PAPEL_GEN")
	@Column(name = "intCodPap")
	private Integer codPapel;

	@Column(name = "vcrNome", nullable = false)
	private String nome;

	@Column(name = "dblImpMono")
	private Double ImpMono;

	@Column(name = "dblImpColor")
	private Double ImpColor;

	@Column(name = "dblImpShade")
	private Double ImpShade;
	
	@Column(name = "blEhPapel")
	private Integer ehpapel;

	@Version
	@Column(name = "tspVersao")
	private Timestamp versao;

	@OneToMany(mappedBy = "papel", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<LinhaRequisicao> linhaRequisicao;

	@OneToMany(mappedBy = "papelPadrao", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Cliente> clientes;

	
	
	public Papel() {
		setLinhaRequisicao(new ArrayList<LinhaRequisicao>());
		setClientes(new ArrayList<Cliente>());
		setEhpapel(SIMNAO01.SIM);
	}

	public Papel(String nome, Double impMono, Double impColor, Double impShade) {
		this();
		this.nome = nome;
		ImpMono = impMono;
		ImpColor = impColor;
		ImpShade = impShade;
	}

	public Integer getCodPapel() {
		return codPapel;
	}

	public void setCodPapel(Integer codPapel) {
		this.codPapel = codPapel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public Double getImpMono() {
		return ImpMono;
	}

	public void setImpMono(Double impMono) {
		ImpMono = impMono;
	}

	public Double getImpColor() {
		return ImpColor;
	}

	public void setImpColor(Double impColor) {
		ImpColor = impColor;
	}

	public Double getImpShade() {
		return ImpShade;
	}

	public void setImpShade(Double impShade) {
		ImpShade = impShade;
	}

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public List<LinhaRequisicao> getLinhaRequisicao() {
		return linhaRequisicao;
	}

	public void setLinhaRequisicao(List<LinhaRequisicao> linhaRequisicao) {
		this.linhaRequisicao = linhaRequisicao;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getEhpapel() {
		return ehpapel;
	}

	public void setEhpapel(Integer ehpapel) {
		this.ehpapel = ehpapel;
	}

	 
	
}
