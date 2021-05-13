package View.iterator;

import java.util.ArrayList;
import java.util.List;

import DTO.ImovelDTO;

public class IteratorImovelDTO implements IIteratorImovel{

	private List<ImovelDTO> lista;
	private int quantidade = 0;
	
	public IteratorImovelDTO(ArrayList<ImovelDTO> lista) {
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
	public ImovelDTO next() {
		ImovelDTO dto = lista.get(quantidade);
		quantidade++;
		return dto;
	}

}
