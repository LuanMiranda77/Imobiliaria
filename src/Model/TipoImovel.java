package Model;

import DAO.Sql.TestConexao;
import DTO.TipoImovelDTO;

public class TipoImovel {
	private int id;
	private String nome;
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
	public static void cadastrar(TipoImovelDTO cdto) {
		TestConexao.retornar().fabricarTipo().cadastrar(cdto);
	}
	public static void excluir(int id) {
		TestConexao.retornar().fabricarTipo().excluir(id);
	}
	public static TipoImovelDTO listaTipo(){
		return TestConexao.retornar().fabricarTipo().lista();
	}
	public static TipoImovelDTO pesquisar(TipoImovelDTO cdto) {
		return TestConexao.retornar().fabricarTipo().pesquisar(cdto.getNome());
	}
	public static void editar(TipoImovelDTO cdto) {
		TestConexao.retornar().fabricarTipo().editar(cdto);
	}
	public static TipoImovelDTO pesquisarInt(int id) {
		return TestConexao.retornar().fabricarTipo().pesquisarInt(id);
	}
	
	

}
