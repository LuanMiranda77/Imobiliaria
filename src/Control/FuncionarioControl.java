package Control;
import DTO.FuncionarioDTO;
import Model.Funcionario;
import View.ExceptionFuncionario;

public class FuncionarioControl {
	
	public static void cadastrarFuncionario(FuncionarioDTO fdto) throws ExceptionFuncionario {
		 Funcionario.cadastrarFuncionario(fdto);
	}
	public  static void excluirFuncionario(FuncionarioDTO fdto) {
		 Funcionario.excluirFuncionario(fdto);
	}
	public static FuncionarioDTO listaDeFuncionarioDTO(){
		return Funcionario.listaDeFuncionarioDTO();
	}
	public static FuncionarioDTO pesquisarFuncionario(FuncionarioDTO fdto) {
		return Funcionario.pesquisarFuncionario(fdto);
	}
	public static FuncionarioDTO verificaLogin(FuncionarioDTO fdto) {
		return Funcionario.verificaLogin(fdto);
	}
	
	public static void editar(FuncionarioDTO fdto) {
		Funcionario.editarFuncionario(fdto);
	}

}
