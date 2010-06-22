package br.seploc.pojos;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Version;

@Entity(name = "tbl_menu")
@SqlResultSetMapping(name = "Menu.implicit", entities = @EntityResult(entityClass = br.seploc.pojos.Menu.class))
@NamedNativeQueries( {
		@NamedNativeQuery(name = "Menu.RetornaMenus", query = " SELECT m.vcrImagem, m.vcrArquivo, m.vcrRotulo, " +
				                                              " m.vcrComentario, m.vcrTextoAlt, m.tspVersao, m.chrCodMenu " +
				                                              "FROM tbl_menu m", resultSetMapping = "Menu.implicit")
				                                              
//		,@NamedNativeQuery(name = "Status.FiltraStatus", query = "SELECT s.STS_ID, s.DESCRICAO FROM Status s "
//				+ " WHERE s.DESCRICAO like :STATUS", resultSetMapping = "Status.implicit"),
//		@NamedNativeQuery(name = "Status.ContaStatus", query = "SELECT  count(*) FROM Status s ", resultSetMapping = "Status.implicit"),
//		@NamedNativeQuery(name = "Status.BuscaPorNome", query = "SELECT * FROM Status s where "
//				+ "s.DESCRICAO = :status", resultSetMapping = "Status.implicit")
// ,@NamedNativeQuery(name = "Status.ContaEvolucoesStatus", query =
// "SELECT count(*) FROM Status s"
// +
// "where s.STS_ID = :status and s.STS_ID in (select EVDE_STS_ID from evolucao_demanda e)")
})
public class Menu implements Serializable {

	private static final long serialVersionUID = 4664257764918539236L;

	@Id
	@Column(name = "chrCodMenu", nullable = false)
	private Character codMenu;

	@Column(name = "vcrImagem", nullable = false)
	private String imagem;

	@Column(name = "vcrArquivo", nullable = false)
	private String arquivo;

	@Column(name = "vcrRotulo", nullable = false)
	private String rotulo;

	@Column(name = "vcrComentario", nullable = false)
	private String comentario;

	@Column(name = "vcrTextoAlt")
	private String textoAlt;

	@Column(name = "tspVersao")
	@Version
	private Timestamp versao;

	public Menu() {
	}

	public Menu(Character codMenu, String imagem, String arquivo,
			String rotulo, String comentario, String textoAlt) {
		this.setCodMenu(codMenu);
		this.setImagem(imagem);
		this.setArquivo(arquivo);
		this.setRotulo(rotulo);
		this.setComentario(comentario);
		this.setTextoAlt(textoAlt);
	}

	public Menu(Character codMenu, String imagem, String arquivo,
			String rotulo, String comentario) {
		this.setCodMenu(codMenu);
		this.setImagem(imagem);
		this.setArquivo(arquivo);
		this.setRotulo(rotulo);
		this.setComentario(comentario);
	}

	public Character getCodMenu() {
		return codMenu;
	}

	public void setCodMenu(Character codMenu) {
		this.codMenu = codMenu;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getTextoAlt() {
		return textoAlt;
	}

	public void setTextoAlt(String textoAlt) {
		this.textoAlt = textoAlt;
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
		result = prime * result + ((codMenu == null) ? 0 : codMenu.hashCode());
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
		Menu other = (Menu) obj;
		if (codMenu == null) {
			if (other.codMenu != null)
				return false;
		} else if (!codMenu.equals(other.codMenu))
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
		return "Menu [" + (arquivo != null ? "arquivo=" + arquivo + ", " : "")
				+ (codMenu != null ? "codMenu=" + codMenu + ", " : "")
				+ (comentario != null ? "comentario=" + comentario + ", " : "")
				+ (imagem != null ? "imagem=" + imagem + ", " : "")
				+ (rotulo != null ? "rotulo=" + rotulo + ", " : "")
				+ (textoAlt != null ? "textoAlt=" + textoAlt : "") + "]";
	}

}
