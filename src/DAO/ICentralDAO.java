package DAO;
import DTO.FuncionarioDTO;

public interface ICentralDAO {

	public void setLogado(FuncionarioDTO cdto);
	
	public FuncionarioDTO getLogado();
}
