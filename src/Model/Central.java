package Model;

import DAO.CentralDAOXml;
import DAO.ICentralDAO;
import DTO.FuncionarioDTO;

public class Central {
	private static ICentralDAO conexao = new CentralDAOXml();
	
	
	public static void setLogado(FuncionarioDTO novo) {
		conexao.setLogado(novo);
	}
	public static  FuncionarioDTO getLogado() {
		return conexao.getLogado();
		
	}
}
