package View.iterator;

import DTO.ClienteDTO;

public interface IIteratorCliente {
	
	public boolean hasNext();
	
	public ClienteDTO next();

}
