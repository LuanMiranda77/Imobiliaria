package View.iterator;

import java.util.ArrayList;
import java.util.List;

import DTO.CompraDTO;

public class IteratorCompraDTO implements IIteratorCompra{

	private List<CompraDTO> lista;
	private int quantidade = 0;
	
	public IteratorCompraDTO(ArrayList<CompraDTO> lista) {
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
	public CompraDTO next() {
		CompraDTO dto = lista.get(quantidade);
		quantidade++;
		return dto;
	}
	
}
