package Control;

import DTO.ClienteDTO;
import Model.Cliente;
import View.ExceptionCliente;
import View.ExceptionDivida;

public class ClienteControl {
	private final static Cliente c= new Cliente();;
	 
	
	public static void cadastrarCliente(ClienteDTO cdto) throws ExceptionCliente {
		 c.cadastrar(cdto);
	}
	public static void excluirCliente(int id) throws ExceptionDivida {
		 c.excluir(id);
	}
	public static ClienteDTO listaDeClientesDTO() {
		return c.lista();
	}
	public static  ClienteDTO pesquisarCliente(int id) {
		return c.pesquisar(id);
	}
	public static void editar(ClienteDTO cliente) {
		c.editar(cliente);
	}
}
