package View.iterator;

import java.util.ArrayList;
import java.util.List;

import DTO.FuncionarioDTO;

public class IteratorFuncionarioDTO implements IIteratorFuncionario{
	
	private List<FuncionarioDTO> lista;
	private int quantidade = 0;
	
	public IteratorFuncionarioDTO(ArrayList<FuncionarioDTO> lista) {
		this.lista = lista;
	}

	@Override
	public boolean hasNext() {
		if(quantidade >= lista.size() || lista.get(quantidade) == null) 
			return false;
		else
			return true;
	}

	@Override
	public FuncionarioDTO next() {
		FuncionarioDTO dto = lista.get(quantidade);
		quantidade++;
		return dto;
	}

}
