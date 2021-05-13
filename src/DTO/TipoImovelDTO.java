package DTO;

import java.util.ArrayList;

public class TipoImovelDTO {
	private int id;
	private String nome; 
	private float pocetagem;
	private ArrayList<TipoImovelDTO> lista = new ArrayList<>();
	
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
	public float getPocetagem() {
		return pocetagem;
	}
	public void setPocetagem(float pocetagem) {
		this.pocetagem = pocetagem;
	}
	public ArrayList<TipoImovelDTO> getLista() {
		return lista;
	}
	public void setLista(ArrayList<TipoImovelDTO> lista) {
		this.lista = lista;
	}

}
