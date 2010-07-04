package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;


/**
 * The persistent class for the tbl_entrega database table.
 * 
 */
@Entity
@Table(name="tbl_entrega")
public class Entrega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "entrega_id", strategy = GenerationType.TABLE)
	@TableGenerator(name = "entrega_id", table = "ID_GEN", allocationSize = 1, initialValue=1,
			pkColumnName = "NOME_ID", valueColumnName = "VAL_ID", pkColumnValue = "ENTREGA_GEN")
	@Column(name="intCodEnt")
	private Integer codEntrega;

	@Column(name="vcrLocal")
	private String local;
	
	@Column(name="dblPreco")
	private Double preco;

	@Version
	@Column(name="tspVersao")
	private Timestamp versao;

	@OneToMany(mappedBy="entrega")
	private List<RequisicaoServico> reqServico;

  }