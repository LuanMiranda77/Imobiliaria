package Control;

import DTO.TipoImovelDTO;
import Model.TipoImovel;

public class TipoControl {


	public static void  cadastrar(TipoImovelDTO cdto) {
		 TipoImovel.cadastrar(cdto);
	}
	public static void excluir(int id) {
		TipoImovel.excluir(id);
	}
	public static  TipoImovelDTO listaTipoImovelDTO(){
		return TipoImovel.listaTipo();
	}
	public static TipoImovelDTO pesquisar(TipoImovelDTO cdto) {
		return TipoImovel.pesquisar(cdto);
	}
	public static void editar(TipoImovelDTO cdto) {
		  TipoImovel.editar(cdto);
	}
	public static TipoImovelDTO pesquisarInt(int id) {
		return TipoImovel.pesquisarInt(id);
	}

}
