package DTO;

import java.util.ArrayList;

public class DividaDTO {
	
	private int id;
	private double preco;
	private String data;
	private ClienteDTO ClienteDTO;
	private ArrayList<DividaDTO> lista = new ArrayList<>();
	
	public ClienteDTO getCliente() {
		return ClienteDTO;
	}
	public void setCliente(ClienteDTO ClienteDTO) {
		this.ClienteDTO = ClienteDTO;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public ArrayList<DividaDTO> getLista() {
		return lista;
	}
	public void setLista(ArrayList<DividaDTO> lista) {
		this.lista = lista;
	}
	
	
	

}
