package DAO.Sql;

import DAO.IClienteDAO;
import DAO.ICompraDAO;
import DAO.IDividaDAO;
import DAO.IFuncionarioDAO;
import DAO.IImovelDAO;
import DAO.ITipoimovel;


public interface FabFactory {
	
	public IClienteDAO fabricarClie();
	
	public IFuncionarioDAO fabricarFun();
	
	public ICompraDAO fabricarComp();
	
	public IImovelDAO fabricarImovel();
	
	public ITipoimovel fabricarTipo();
	
	public IDividaDAO fabricarDvida();
	
}
