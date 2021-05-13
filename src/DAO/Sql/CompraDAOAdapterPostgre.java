package DAO.Sql;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ICompraDAO;
import DTO.ClienteDTO;
import DTO.CompraDTO;
import DTO.FuncionarioDTO;
import DTO.ImovelDTO;
/**
 * 
 * @author agemiro
 *
 */


public class CompraDAOAdapterPostgre  implements ICompraDAO{
	
	private Connection con = null;
	PreparedStatement pst = null;
	private CompraDTO compra;
	private ClienteDAOPostgre pesquisa = new ClienteDAOPostgre();
	private FuncionarioDAOAdapterPostgre pesfunc = new FuncionarioDAOAdapterPostgre();
	private ImovelDAOAdapterPostgre pesimov = new ImovelDAOAdapterPostgre();
	private FuncionarioDTO func = new FuncionarioDTO();
	private ImovelDTO imovel;

	
	public CompraDAOAdapterPostgre() {
		compra = new CompraDTO();
	}


	@Override
	public void cadastrar(CompraDTO novo) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("INSERT INTO compra (idcliente, idimovel, datacomp, pagamento, pocentcomp, idfunc) VALUES (?,?,?,?,?,?)");
			pst.setInt(1, novo.getCliente().getId());
			pst.setInt(2, novo.getIdImovel().getId());
			pst.setString(3, novo.getDataCompra());
			pst.setString(4, novo.getPagamento());
			pst.setString(5, novo.getPorcentagemCompra());
			pst.setInt(6, novo.getIdFuncionario().getId());
			pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			

	
	}

	@Override
	public void excluir(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM compra WHERE idcomp = ?");
			pst.setInt(1, cod);
			
			pst.execute();
			
			
		}catch(Exception e) {
			
		}
	}

	@Override
	public CompraDTO pesquisar(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM compra where idcomp=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				compra = new CompraDTO();
				ClienteDTO novo = new ClienteDTO();
				compra.setId(rs.getInt("idcomp"));
				func.setId(rs.getInt("idfunc"));
				func=pesfunc.pesquisarFuncionario(func);
				
				//consultar compra
				novo = pesquisa.pesquisar(rs.getInt("idcliente"));
				compra.setCliente(novo);
				imovel=pesimov.pesquisarImovel(rs.getInt("idimovel"));
				compra.setIdImovel(imovel);
				compra.setDataCompra(rs.getString("datacomp"));
				compra.setPagamento(rs.getString("pagamento"));
				compra.setPorcentagemCompra(rs.getString("pocentcomp"));
				compra.setIdFuncionario(func);
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		return compra;
	}

	@Override
	public void editar(CompraDTO editado) {
		
	/*	try {
			String sql = "UPDATE compra SET status=? WHERE id = ?";
			//coneção com bd
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement(sql);
			//edição do estado
			pst.setString(1,status);
	
			pst.setInt(2, cod);
			pst.execute();
			System.out.println("editado com sucesso");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	*/
	}

	@Override
	public CompraDTO listaCompraDTO() {
			ArrayList<CompraDTO> lista = new ArrayList<>();
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM compra");
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				compra = new CompraDTO();
				compra = new CompraDTO();
				ClienteDTO novo = new ClienteDTO();
				compra.setId(rs.getInt("idcomp"));
				func.setId(rs.getInt("idfunc"));
				func=pesfunc.pesquisarFuncionario(func);
				
				//consultar compra
				novo = pesquisa.pesquisar(rs.getInt("idcliente"));
				compra.setCliente(novo);
				imovel=pesimov.pesquisarImovel(rs.getInt("idimovel"));
				compra.setIdImovel(imovel);
				compra.setDataCompra(rs.getString("datacomp"));
				compra.setPagamento(rs.getString("pagamento"));
				compra.setPorcentagemCompra(rs.getString("pocentcomp"));
				compra.setIdFuncionario(func);
				lista.add(compra);
			}
			compra.setArrayDTO(lista);
	
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return compra;
	}

	public CompraDTO consultaVendido() {
			ArrayList<CompraDTO> lista = new ArrayList<>();
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("select i.idimovel,t.nome,c.nome,i.preco from compra as p inner join imovel as i " + 
														"on(i.idimovel=p.idimovel)" + 
														"inner join tipoimovel as" + 
														"on(t.idtipo=i.idtipo)" + 
														"inner join cliente as" + 
														"on(p.idcliente=c.idcliente)");
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				lista.add(compra);
			}
			compra.setArrayDTO(lista);
	
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return compra;
	}
}
