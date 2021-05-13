package View.iterator;

import java.util.ArrayList;
import java.util.List;

import DTO.DividaDTO;

public class IteratorDividaDTO implements IIteratorDivida{

	private List<DividaDTO> lista;
	private int quantidade = 0;
	
	public IteratorDividaDTO(ArrayList<DividaDTO> lista) {
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
	public DividaDTO next() {
		DividaDTO dto = lista.get(quantidade);
		quantidade++;
		return dto;
	}

}
