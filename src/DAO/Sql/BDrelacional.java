package DAO.Sql;

import DAO.IClienteDAO;
import DAO.ICompraDAO;
import DAO.IDividaDAO;
import DAO.IFuncionarioDAO;
import DAO.IImovelDAO;
import DAO.ITipoimovel;
import DAO.ImovelDAO;

public class BDrelacional implements FabFactory {

	@Override
	public IClienteDAO fabricarClie() {
		
		return new ClienteDAOPostgre();
	}

	@Override
	public IFuncionarioDAO fabricarFun() {
		// TODO Auto-generated method stub
		return new FuncionarioDAOAdapterPostgre();
	}

	@Override
	public ICompraDAO fabricarComp() {
		
		return new CompraDAOAdapterPostgre();
	}

	@Override
	public IImovelDAO fabricarImovel() {
		// TODO Auto-generated method stub
		return new ImovelDAOAdapterPostgre();
	}

	@Override
	public ITipoimovel fabricarTipo() {
		// TODO Auto-generated method stub
		return new TipoImovelDAOAdapterPostgre();
	}

	@Override
	public IDividaDAO fabricarDvida() {
		// TODO Auto-generated method stub
		return new DividaDAOAdapterPostgre2();
	}


}
