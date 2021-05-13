package DAO;



import DTO.FuncionarioDTO;
import View.ExceptionFuncionario;

public interface IFuncionarioDAO {

	public void cadastrarFuncionario(FuncionarioDTO fdto) throws ExceptionFuncionario;
	public void excluirFuncionario(FuncionarioDTO fdto);
	public FuncionarioDTO listaDeFuncionarioDTO();
	public FuncionarioDTO pesquisarFuncionario(FuncionarioDTO fdto);
	public FuncionarioDTO verificaLogin(FuncionarioDTO fdto);
	public void editarFuncionario(FuncionarioDTO fdto);
}
