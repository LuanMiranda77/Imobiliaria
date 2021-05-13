package Model;



import DAO.CentralDAOXml;
import DAO.Sql.TestConexao;
import DTO.FuncionarioDTO;
import View.ExceptionFuncionario;

public class Funcionario {

	private int id;
	private String nome, cargo, senha;
	private float salario;
	private static CentralDAOXml cont = new CentralDAOXml();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public static void cadastrarFuncionario(FuncionarioDTO fdto) throws ExceptionFuncionario {
		TestConexao.retornar().fabricarFun().cadastrarFuncionario(fdto);
	}
	public static void editarFuncionario(FuncionarioDTO fdto) {
		TestConexao.retornar().fabricarFun().editarFuncionario(fdto);
	}
	
	public static void excluirFuncionario(FuncionarioDTO fdto) {
		TestConexao.retornar().fabricarFun().excluirFuncionario(fdto);
	}
	public static FuncionarioDTO listaDeFuncionarioDTO(){
		return TestConexao.retornar().fabricarFun().listaDeFuncionarioDTO();
	}
	public static FuncionarioDTO pesquisarFuncionario(FuncionarioDTO fdto) {
		return TestConexao.retornar().fabricarFun().pesquisarFuncionario(fdto);
	}
	public static FuncionarioDTO verificaLogin(FuncionarioDTO fdto) {
		return TestConexao.retornar().fabricarFun().verificaLogin(fdto);
	}
	
	
}
