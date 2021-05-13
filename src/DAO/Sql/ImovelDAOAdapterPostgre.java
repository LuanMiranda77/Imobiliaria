package DAO.Sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.IImovelDAO;
import DTO.ImovelDTO;
import DTO.TipoImovelDTO;
/**
 * 
 * @author agemiro
 *
 */
public class ImovelDAOAdapterPostgre  implements IImovelDAO{
	private Connection con = null;
	PreparedStatement pst = null;
	private ImovelDTO imovel;
	private TipoImovelDTO tipo;
	private TipoImovelDAOAdapterPostgre conexao = new TipoImovelDAOAdapterPostgre();
	
	public ImovelDAOAdapterPostgre() {
		imovel = new ImovelDTO();
	}

	@Override
	public void cadastrarImovel(ImovelDTO novo) {
		String []end =novo.getEnderco().split("-"); 
			
			try {
			con = ConexaoSingleton.getInstance();	
			pst = con.prepareStatement("INSERT INTO imovel (idtipo, descricao, frente, fundo, area, rua, num, bairro,cidade, preco, status) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			pst.setInt(1, novo.getTipo().getId());
			pst.setString(2, novo.getEstado());
			pst.setDouble(3, novo.getFrente());
			pst.setDouble(4, novo.getFundo());
			pst.setDouble(5, novo.getAreaQuadrada());
			pst.setString(6, end[0]);
			pst.setString(7, end[1]);
			pst.setString(8, end[2]);
			pst.setString(9, end[3]);
			pst.setDouble(10, novo.getPreco());
			pst.setString(11, novo.getStatus());
			pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void editarImovel(ImovelDTO novo) {
		String []end =novo.getEnderco().split("-"); 
		try {
			String sql = "UPDATE imovel SET idtipo=?, descricao=?, frente=?, fundo=?, area=?, rua=?,num=?,bairro=?,cidade=?, preco=?, status=? WHERE idimovel = ?";
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement(sql);
			pst.setInt(1, novo.getTipo().getId());
			pst.setString(2, novo.getEstado());
			pst.setDouble(3, novo.getFrente());
			pst.setDouble(4, novo.getFundo());
			pst.setDouble(5, novo.getAreaQuadrada());
			pst.setString(6, end[0]);
			pst.setString(7, end[1]);
			pst.setString(8, end[2]);
			pst.setString(9, end[3]);
			pst.setDouble(10, novo.getPreco());
			pst.setString(11, novo.getStatus());
			
			pst.setInt(12, novo.getId());
			pst.execute();
			
		}catch(SQLException e) {
			e.getStackTrace();
			
		}
		
	}

	@Override
	public ImovelDTO pesquisarImovel(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM imovel where idimovel=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				String end ="";
				imovel = new ImovelDTO();
				tipo=conexao.pesquisarInt(rs.getInt("idtipo"));
				imovel.setTipo(tipo);
				imovel.setId(rs.getInt("idimovel"));
				imovel.setEstado(rs.getString("descricao"));
				imovel.setFrente(rs.getFloat("frente"));
				imovel.setFundo(rs.getFloat("fundo"));
				imovel.setAreaQuadrada(rs.getFloat("area"));
				end=rs.getString("rua")+"-";
				end+=rs.getString("num")+"-";
				end+=rs.getString("bairro")+"-";
				end+=rs.getString("cidade");
				imovel.setEnderco(end);
				imovel.setPreco(rs.getDouble("preco"));
				imovel.setStatus(rs.getString("status"));
				
			}
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		return imovel;
	}

	@Override
	public ImovelDTO listaImovelDTO() {
		
		ArrayList<ImovelDTO> lista = new ArrayList<>();
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM imovel");
			ResultSet rs= pst.executeQuery();
			
			while(rs.next()) {
				String end ="";
				imovel = new ImovelDTO();
				tipo=conexao.pesquisarInt(rs.getInt("idtipo"));
				imovel.setTipo(tipo);
				imovel.setId(rs.getInt("idimovel"));
				imovel.setEstado(rs.getString("descricao"));
				imovel.setFrente(rs.getFloat("frente"));
				imovel.setFundo(rs.getFloat("fundo"));
				imovel.setAreaQuadrada(rs.getFloat("area"));
				end=rs.getString("rua")+"-";
				end+=rs.getString("num")+"-";
				end+=rs.getString("bairro")+"-";
				end+=rs.getString("cidade");
				imovel.setEnderco(end);
				imovel.setPreco(rs.getDouble("preco"));
				imovel.setStatus(rs.getString("status"));
				lista.add(imovel);
			}
			imovel.setArrayDTO(lista);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return imovel;
	}

	@Override
	public void excluirImovel(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM imovel WHERE idimovel = ?");
			pst.setInt(1, cod);
			
			pst.execute();
			
			
		}catch(SQLException e) {
			
		}
		
	}

	public int tamanho() {
		return listaImovelDTO().getArrayDTO().size();
	}

}
