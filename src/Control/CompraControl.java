package Control;



import DTO.CompraDTO;
import Model.Compra;

public class CompraControl {
	private final static Compra c = new Compra();;

	public static void cadastrarCompra(CompraDTO cdto) {
		 c.cadastrarImovel(cdto);
	}
	public static void  excluirCompra(CompraDTO cdto) {
		 c.excluirImovel(cdto);
	}
	public static  CompraDTO listaCompraDTO(){
		return c.listaCompraDTO();
	}
	public static  CompraDTO pesquisarCompra(CompraDTO cdto) {
		return c.pesquisarImovel(cdto);
	}

}
