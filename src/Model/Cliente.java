package Model;


import DAO.Sql.FabFactory;
import DAO.Sql.TestConexao;
import DTO.ClienteDTO;
import View.ExceptionCliente;
import View.ExceptionDivida;

public class Cliente {
	private int id;
	private String nome, cpf, endereco, telefone;
	private Divida divida;
	
	
	
	public Divida getDivida() {
		return divida;
	}

	public void setDivida(Divida divida) {
		this.divida = divida;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void cadastrar(ClienteDTO cdto) throws ExceptionCliente {
		 TestConexao.retornar().fabricarClie().cadastrar(cdto);
	}
	public void editar(ClienteDTO cdto) {
		TestConexao.retornar().fabricarClie().editar(cdto);
	}
	public void excluir(int id) throws ExceptionDivida {
		TestConexao.retornar().fabricarClie().excluir(id);
	}
	public ClienteDTO lista(){
		return TestConexao.retornar().fabricarClie().lista();
	}
	public ClienteDTO pesquisar(int id) {
		return  TestConexao.retornar().fabricarClie().pesquisar(id);
	}
	public int tamanho() {
		return TestConexao.retornar().fabricarClie().lista().getArrayDTO().size();
	}
	

}
