package DAO.Sql;

import DAO.ClienteDAO;
import DAO.CompraDAO;
import DAO.DividaDAO;
import DAO.FuncionarioDAO;
import DAO.IClienteDAO;
import DAO.ICompraDAO;
import DAO.IDividaDAO;
import DAO.IFuncionarioDAO;
import DAO.IImovelDAO;
import DAO.ITipoimovel;
import DAO.ImovelDAO;
import DAO.TipoImovelDAO;



public class BDarquivo implements FabFactory {

	public IClienteDAO fabricarClie() {
		return  new ClienteDAO();
	}

	public  IFuncionarioDAO fabricarFun() {
		return new FuncionarioDAO();
	}

	public ICompraDAO fabricarComp() {
		return new CompraDAO();
	}

	public IImovelDAO fabricarImovel() {
		return new ImovelDAO();
	}
	
	public ITipoimovel fabricarTipo() {
		return new TipoImovelDAO();
	}

	@Override
	public IDividaDAO fabricarDvida() {
		// TODO Auto-generated method stub
		return new DividaDAO();
	}


}
