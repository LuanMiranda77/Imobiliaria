package Control;

import DTO.DividaDTO;
import Model.Divida;


public class DividaControl {
	

	public static void  cadastrar(DividaDTO cdto) {
		 Divida.cadastrar(cdto);
	}
	public static void excluir(int id) {
		Divida.excluir(id);
	}
	public static DividaDTO pesquisar(int id) {
		return Divida.pesquisar(id);
	}
	public static void editar(DividaDTO cdto) {
		  Divida.editar(cdto);
	}
	public static DividaDTO lista() {
		  return Divida.lista();
	}

}
