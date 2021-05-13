package DAO;
import DTO.DividaDTO;

public interface IDividaDAO {
	
	public void cadastrar(DividaDTO cdto);
	public void excluir(int id);
	public DividaDTO lista();
	public DividaDTO pesquisar(int id);
	public void editar(DividaDTO cdto);

}
