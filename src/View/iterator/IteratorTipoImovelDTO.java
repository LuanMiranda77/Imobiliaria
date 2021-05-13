package View.iterator;

import java.util.ArrayList;
import java.util.List;

import DTO.TipoImovelDTO;

public class IteratorTipoImovelDTO implements IIteratorTipoImovel{

	private List<TipoImovelDTO> lista;
	private int quantidade = 0;
	
	public IteratorTipoImovelDTO(ArrayList<TipoImovelDTO> lista) {
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
	public TipoImovelDTO next() {
		TipoImovelDTO dto = lista.get(quantidade);
		quantidade++;
		return dto;
	}

}
