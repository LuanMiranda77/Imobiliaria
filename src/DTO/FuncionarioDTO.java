package DTO;

import java.util.ArrayList;



public class FuncionarioDTO {

	private int id;
	private String nome, cargo, senha;
	private float salario;
	private ArrayList<FuncionarioDTO> lista;
	
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
	public ArrayList<FuncionarioDTO> getLista() {
		return lista;
	}
	public void setLista(ArrayList<FuncionarioDTO> lista) {
		this.lista = lista;
	}
	
	
	
}
