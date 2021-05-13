package View.iterator;

import java.util.ArrayList;
import java.util.List;

import DTO.ClienteDTO;

public class IteratorClienteDTO implements IIteratorCliente{
	
	private List<ClienteDTO> lista;
	private int quantidade = 0;
	
	public IteratorClienteDTO(ArrayList<ClienteDTO> lista) {
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
	public ClienteDTO next() {
		ClienteDTO dto = lista.get(quantidade);
		quantidade++;
		return dto;
	}

}
