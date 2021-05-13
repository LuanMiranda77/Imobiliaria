package DAO;



import DTO.CompraDTO;

public interface ICompraDAO {
	public void cadastrar(CompraDTO cdto);
	public void excluir(int id);
	public CompraDTO listaCompraDTO();
	public CompraDTO pesquisar(int id);
	public void editar(CompraDTO cdto);
}
