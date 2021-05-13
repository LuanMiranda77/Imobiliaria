package Control;

import DTO.ImovelDTO;
import Model.Imovel;

public class ImovelControl {
	
	private final static Imovel i = new Imovel();;

	public static void  cadastrarImovel(ImovelDTO cdto) {
		 i.cadastrarImovel(cdto);
	}
	public static void excluirImovel(ImovelDTO cdto) {
		 i.excluirImovel(cdto);
	}
	public static  ImovelDTO listaImovelDTO(){
		return i.listaImovelDTO();
	}
	public static ImovelDTO pesquisarImovel(ImovelDTO cdto) {
		return i.pesquisarImovel(cdto);
	}
	public static void editar(ImovelDTO imovel) {
		i.editarImovel(imovel);
	}
	

}
