package DAO.Sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.IDividaDAO;
import DTO.ClienteDTO;
import DTO.DividaDTO;
/**
 * 
 * @author agemiro
 *
 */

public class DividaDAOAdapterPostgre2 implements IDividaDAO{
	
	private Connection con = null;
	PreparedStatement pst = null;
	private DividaDTO divida;
	private ClienteDTO cliente ;
	private ClienteDAOPostgre conexao = new ClienteDAOPostgre();
	
	public DividaDAOAdapterPostgre2() {
		divida = new DividaDTO();
	}

	@Override
	public void cadastrar(DividaDTO novo) {
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement("INSERT INTO divida (idcliente,valor,datadiv) VALUES (?,?,?)");
				pst.setInt(1, novo.getCliente().getId());
				pst.setDouble(2, novo.getPreco());
				pst.setString(3, novo.getData());
				pst.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
			

	@Override
	public void excluir(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM divida WHERE iddivida = ?");
			pst.setInt(1, cod);
			
			pst.execute();
			
			
		}catch(Exception e) {
			
		}
	}

	@Override
	public void editar(DividaDTO editado) {
		try {
			String sql = "UPDATE divida SET data=?,valor=?, datadiv=? WHERE iddivida = ?";
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement(sql);
			pst.setString(2, editado.getData());
			pst.setDouble(3, editado.getPreco());
			
			pst.setInt(1, editado.getId());
			pst.execute();
			System.out.println("editado com sucesso");
			ordenarCres();
			
		}catch(Exception e) {
			e.getStackTrace();
			
		}
	}

	@Override
	public DividaDTO pesquisar(int cod) {
		conexao = new ClienteDAOPostgre();
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM divida where iddivida=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				cliente = new ClienteDTO();
				divida = new DividaDTO();
				divida.setId(rs.getInt("iddivida"));
				cliente=conexao.pesquisar(rs.getInt("idcliente"));
				divida.setCliente(cliente);
				divida.setPreco(rs.getDouble("valor"));
				divida.setData(rs.getString("datadiv"));
				
				
			}
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return divida;
	}


	@Override
	public DividaDTO lista() {
		
		ArrayList<DividaDTO> lista = new ArrayList<>();
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT * FROM divida order by iddivida asc");
			//ordenarCres();
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				cliente = new ClienteDTO();
				divida = new DividaDTO();
				divida.setId(rs.getInt("iddivida"));
				cliente=conexao.pesquisar(rs.getInt("idcliente"));
				divida.setCliente(cliente);
				divida.setPreco(rs.getDouble("valor"));
				divida.setData(rs.getString("datadiv"));
				divida.setCliente(cliente);
				lista.add(divida);
				
			}
			divida.setLista(lista);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		
		return divida;
	}
	public void ordenarCres() {
		try {
			String sql = "select * from divida order by iddivida asc";
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement(sql);
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
public DividaDTO listafinal() {
		
		ArrayList<DividaDTO> lista = new ArrayList<>();
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT * FROM dividacliente");
			//ordenarCres();
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				cliente = new ClienteDTO();
				divida = new DividaDTO();
				divida.setId(rs.getInt("iddivida"));
				cliente=conexao.pesquisar(rs.getInt("idcliente"));
				divida.setCliente(cliente);
				divida.setPreco(rs.getDouble("valor"));
				divida.setData(rs.getString("datadiv"));
				divida.setCliente(cliente);
				lista.add(divida);
				
			}
			divida.setLista(lista);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		
		return divida;
	}


	
}
