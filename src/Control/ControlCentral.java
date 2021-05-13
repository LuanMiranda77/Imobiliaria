package Control;

import DTO.FuncionarioDTO;
import Model.Central;

public class ControlCentral {
	
	public static void setLogado(FuncionarioDTO novo) {
		Central.setLogado(novo);
	}
	public static FuncionarioDTO getLogado() {
		return Central.getLogado();
		
	}

}
