package Model;

import DAO.Sql.TestConexao;
import DTO.DividaDTO;

public class Divida {
	
	private int id;
	private float preco;
	private String data;
	private Cliente cliente;
	
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public static void cadastrar(DividaDTO cdto) {
		TestConexao.retornar().fabricarDvida().cadastrar(cdto);
	}
	public static void excluir(int id) {
		TestConexao.retornar().fabricarDvida().excluir(id);
	}
	public static DividaDTO lista(){
		return TestConexao.retornar().fabricarDvida().lista();
	}
	public static DividaDTO pesquisar(int id) {
		return TestConexao.retornar().fabricarDvida().pesquisar(id);
	}
	public static void editar(DividaDTO cdto) {
		TestConexao.retornar().fabricarDvida().editar(cdto);
	}
	
	

}
