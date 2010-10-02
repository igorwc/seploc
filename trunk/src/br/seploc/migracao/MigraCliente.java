package br.seploc.migracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MigraCliente {
	
	List<Cliente> listaClientes;
	List<FoneCli> listaTel;
	List<Projeto> listaProj;
	Integer idCliente = 1;
	
	public void seleciona(Connection c) throws Exception {
//		List<Cliente> retorno = new ArrayList<Cliente>();
//		Integer id = 1;
		// pega o Statement

		PreparedStatement stmt = c
				.prepareStatement("SELECT vcrCnpj, vcrRazao, vcrCpf, vcrEnder, vcrBairro, " +
						"vcrCidade, vcrEstado, vcrCep, vcrEmail, vcrInscricao, intBalcao, " +
						"vcrFantasia, txtobs, vcrMapa, intEntregaPadrao, intPapelPadrao FROM tbl_clientes ");
		// executaumselect
		ResultSet rs = stmt.executeQuery();
		// iteranoResultSet
		while (rs.next()) {
			Cliente cl = new Cliente();
			cl.setId(idCliente);
			cl.setCnpj(rs.getString(1));
			cl.setRazao(rs.getString(2));
			cl.setCpf(rs.getString(3));
			cl.setEndereco(rs.getString(4));
			cl.setBairro(rs.getString(5));
			cl.setCidade(rs.getString(6));
			cl.setEstado(rs.getString(7));
			cl.setCep(rs.getString(8));
			cl.setEmail(rs.getString(9));
			cl.setInscricao(rs.getString(10));
			cl.setBalcao(rs.getInt(11));
			cl.setFantasia(rs.getString(12));
			cl.setObs(rs.getString(13));
			cl.setMapa(rs.getString(14));
			cl.setEntrega(rs.getInt(15));
			cl.setPapel(rs.getInt(16));
			listaClientes.add(cl);
			PreparedStatement stFone = c
			.prepareStatement("SELECT  vcrCnpj, vcrFoneRes, vcrFoneCom, vcrFax, vcrCelular " +
							  "FROM tbl_fonecli where vcrCnpj = ? ");
			stFone.setString(1,cl.getCnpj());
			ResultSet rsFone = stFone.executeQuery();
			while (rsFone.next()){
				FoneCli fone = new FoneCli();
				fone.setId(idCliente);
				fone.setFoneR(rsFone.getString(2));
				fone.setFoneCom(rsFone.getString(3));
				fone.setFax(rsFone.getString(4));
				fone.setCel(rsFone.getString(5));
				listaTel.add(fone);
			}
			stFone.close();
			PreparedStatement stProj = c
			.prepareStatement("SELECT vcrCnpj, intCodProj, vcrProjeto FROM tbl_projetos where vcrCnpj = ? ");
			stProj.setString(1,cl.getCnpj());
			ResultSet rsProj = stProj.executeQuery();
			while (rsProj.next()){
				Projeto projeto = new Projeto();
				projeto.setId(idCliente);
				projeto.setCodProj(rsProj.getInt(2));
				projeto.setDescProj(rsProj.getString(3)); 
				listaProj.add(projeto);
			}
			idCliente++;
		}
		stmt.close();
	}
	public void insereDados(Connection c) throws Exception {
		String sql = "INSERT INTO  tbl_clientes (intClienteId, vcrCnpj, vcrRazao, " +
					"vcrCpf, vcrEnder, vcrBairro, vcrCidade, vcrEstado, vcrCep, vcrEmail," +
					"vcrInscricao, intBalcao, vcrFantasia, txtobs, vcrMapa, intEntregaPadrao, " +
					"intPapelPadrao, tspVersao) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP)";
		for (Cliente cl : listaClientes) {
			PreparedStatement stmt = c.prepareStatement(sql);
			if(cl.getCnpj().length() == 19 && cl.getCnpj().endsWith("99")){
				cl.setCnpj(null);
			}
			if(cl.getCnpj() != null &&cl.getCnpj().equals(cl.getCpf())){
				cl.setCpf(null);
			}
			stmt.setInt(1, cl.getId());
			stmt.setString(2, cl.getCnpj());
			stmt.setString(3,cl.getRazao());
			stmt.setString(4, cl.getCpf());
			stmt.setString(5, cl.getEndereco());
			stmt.setString(6, cl.getBairro());
			stmt.setString(7,cl.getCidade());
			stmt.setString(8,cl.getEstado());
			stmt.setString(9, cl.getCep());
			stmt.setString(10, cl.getEmail());
			stmt.setString(11,cl.getInscricao());
			stmt.setInt(12,cl.getBalcao());
			stmt.setString(13,cl.getFantasia());
			stmt.setString(14,cl.getObs());
			stmt.setString(15,cl.getMapa());
			stmt.setInt(16,cl.getEntrega());
			stmt.setInt(17,cl.getPapel());
			stmt.execute();
			stmt.close();
		}
		sql = " INSERT INTO tbl_fonecli (intClienteId ,vcrFoneRes " +
			  ",vcrFoneCom ,vcrFax ,vcrCelular ,tspVersao) " +
			  "VALUES (?, ? , ? , ? , ? ,CURRENT_TIMESTAMP) ";
		for(FoneCli fone : listaTel){
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, fone.getId());
			stmt.setString(2, fone.getFoneR());
			stmt.setString(3, fone.getFoneCom());
			stmt.setString(4, fone.getFax());
			stmt.setString(5, fone.getCel());
			stmt.execute();
			stmt.close();
		}
		
		sql = "INSERT INTO  tbl_projetos (intCodProj ,intClienteId ,vcrProjeto ,tspVersao)" +
			  " VALUES (?, ?, ? ,CURRENT_TIMESTAMP)";
		for(Projeto p : listaProj){
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, p.getCodProj());
			stmt.setInt(2,p.getId());
			stmt.setString(3, p.getDescProj());
			stmt.execute();
			stmt.close();
		}

	}

	public MigraCliente() {
		listaClientes = new ArrayList<Cliente>();
		listaTel = new ArrayList<FoneCli>();
		listaProj = new ArrayList<Projeto>();
	}
	
	public static void main(String args[]){
		MigraCliente migra = new MigraCliente();
		try {
			Connection c = new ConnectionFactory().getConnection("copytec", "root", "");
			migra.seleciona(c);
			
			c.close();
			c = new ConnectionFactory().getConnection("seploc2", "root", "");
			migra.insereDados(c);
//			c.commit();
			c.close();
			System.out.println("terminou");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

//SELECT vcrCnpj, intCodProj, vcrProjeto FROM tbl_projetos
class Projeto{
	private Integer id;
	private Integer codProj;
	private String descProj;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodProj() {
		return codProj;
	}
	public void setCodProj(Integer codProj) {
		this.codProj = codProj;
	}
	public String getDescProj() {
		return descProj;
	}
	public void setDescProj(String descProj) {
		this.descProj = descProj;
	}
	
	
}
class Cliente{
	private Integer id;
	private String cnpj;
	private String razao;
	private String cpf;
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String email;
	private String inscricao;
	private Integer balcao;
	private String fantasia;
	private String obs;
	private String mapa;
	private Integer entrega;
	private Integer papel;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRazao() {
		return razao;
	}
	public void setRazao(String razao) {
		this.razao = razao;
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
		this.fantasia = fantasia;
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
	public Integer getEntrega() {
		return entrega;
	}
	public void setEntrega(Integer entrega) {
		this.entrega = entrega;
	}
	public Integer getPapel() {
		return papel;
	}
	public void setPapel(Integer papel) {
		this.papel = papel;
	}
}
//SELECT  vcrCnpj, vcrFoneRes, vcrFoneCom, vcrFax, vcrCelular FROM tbl_fonecli
class FoneCli{
	private Integer id;
	private String foneR;
	private String foneCom;
	private String fax;
	private String cel;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFoneR() {
		return foneR;
	}
	public void setFoneR(String foneR) {
		this.foneR = foneR;
	}
	public String getFoneCom() {
		return foneCom;
	}
	public void setFoneCom(String foneCom) {
		this.foneCom = foneCom;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getCel() {
		return cel;
	}
	public void setCel(String cel) {
		this.cel = cel;
	}
	
}