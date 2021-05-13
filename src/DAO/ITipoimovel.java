package DAO;
import DTO.TipoImovelDTO;

public interface ITipoimovel {
	
	public void cadastrar(TipoImovelDTO cdto);
	public void excluir(int id);
	public TipoImovelDTO lista();
	public TipoImovelDTO pesquisar(String id);
	public void editar(TipoImovelDTO cdto);
	public TipoImovelDTO pesquisarInt(int id);

}
