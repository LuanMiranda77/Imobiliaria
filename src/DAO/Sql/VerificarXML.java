package DAO.Sql;

import DAO.ClienteDAO;
import DAO.CompraDAO;
import DAO.DividaDAO;
import DAO.FuncionarioDAO;
import DAO.ImovelDAO;
import DAO.TipoImovelDAO;
import DAO.ZerarPersistencia;
import DTO.ClienteDTO;
import DTO.CompraDTO;
import DTO.DividaDTO;
import DTO.FuncionarioDTO;
import DTO.ImovelDTO;
import DTO.TipoImovelDTO;
import View.ExceptionCliente;
import View.ExceptionFuncionario;

public class VerificarXML {
	private static FuncionarioDAO listafun = new  FuncionarioDAO();
	private static FuncionarioDAOAdapterPostgre fun = new FuncionarioDAOAdapterPostgre();
	
	private static ClienteDAO listacli = new  ClienteDAO();
	private static ClienteDAOPostgre cli = new ClienteDAOPostgre();
	
	private static ImovelDAO listaimovel = new  ImovelDAO();
	private static ImovelDAOAdapterPostgre imovel = new ImovelDAOAdapterPostgre();
	
	private static CompraDAO listaCompra= new  CompraDAO();
	private static CompraDAOAdapterPostgre ped = new CompraDAOAdapterPostgre();
	
	private static TipoImovelDAO listaTipo = new  TipoImovelDAO();
	private static TipoImovelDAOAdapterPostgre ingred = new TipoImovelDAOAdapterPostgre();
	
	private static DividaDAO listaDivida = new  DividaDAO();
	private static DividaDAOAdapterPostgre2 divida = new DividaDAOAdapterPostgre2();
	
	public static void verificar() {
		
	if(listafun.listaDeFuncionarioDTO().getLista().size()!=0) {
		for(int cont=2;cont<=listafun.listaDeFuncionarioDTO().getLista().size();cont++) {
			FuncionarioDTO novo = new FuncionarioDTO();
			novo.setId(cont);
			novo=listafun.pesquisarFuncionario(novo);
            		try {
						fun.cadastrarFuncionario(novo);
					} catch (ExceptionFuncionario e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
		    ZerarPersistencia.zerar("FUNCIONARIO");
	}  
	}
		
		if(listacli.tamanho()!=0) {
			for(int cont=1;cont<=listacli.tamanho();cont++) {
				ClienteDTO novo =listacli.pesquisar(cont);
	            		try {
							cli.cadastrar(novo);
						} catch (ExceptionCliente e) {
							e.printStackTrace();
						}
				}
			ZerarPersistencia.zerar("CLIENTE");	
		
		}
		
		if(listaimovel.tamanho()!=0) {
			for(int cont=1;cont<=listaimovel.tamanho();cont++) {
				ImovelDTO novo =listaimovel.pesquisarImovel(cont);
	       
								imovel.cadastrarImovel(novo);
							
				}
			ZerarPersistencia.zerar("IMOVEL");	
		
		}
		
		if(listaCompra.tamanho()!=0) {
			for(int cont=1;cont<=listaCompra.tamanho();cont++) {
				CompraDTO novo =listaCompra.pesquisar(cont);
	       
					ped.cadastrar(novo);
		
				}
			ZerarPersistencia.zerar("COMPRA");	
		
		}
		
		if(listaTipo.tamanho()!=0) {
			for(int cont=1;cont<=listaTipo.tamanho();cont++) {
				
				TipoImovelDTO novo =listaTipo.pesquisarInt(cont);
						ingred.cadastrar(novo);
		
				}
			ZerarPersistencia.zerar("TIPO");	
		
		}
		
		if(listaDivida.tamanho()!=0) {
			for(int cont=1;cont<=listaDivida.tamanho();cont++) {
				
				DividaDTO novo =listaDivida.pesquisar(cont);
						divida.cadastrar(novo);
		
				}
			ZerarPersistencia.zerar("DIVIDA");	
		
		}
		
		
		
		
	}//chave do metodo
	
	
}//chave class