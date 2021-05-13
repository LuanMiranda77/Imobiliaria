package DAO;
import DTO.ClienteDTO;
import View.ExceptionCliente;
import View.ExceptionDivida;

public interface IClienteDAO {

	public void cadastrar(ClienteDTO cdto)throws ExceptionCliente ;
	public void excluir(int id)throws ExceptionDivida;
	public ClienteDTO lista();
	public ClienteDTO pesquisar(int id);
	public void editar(ClienteDTO editado);
	public int tamanho();
}
