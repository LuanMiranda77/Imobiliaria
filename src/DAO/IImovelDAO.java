package DAO;

import DTO.ImovelDTO;

public interface IImovelDAO {
	
	public void cadastrarImovel(ImovelDTO cdto);
	public void excluirImovel(int id);
	public ImovelDTO listaImovelDTO();
	public ImovelDTO pesquisarImovel(int id);
	public void editarImovel(ImovelDTO cdto);

}
