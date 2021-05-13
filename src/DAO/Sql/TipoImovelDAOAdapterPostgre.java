package DAO.Sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ITipoimovel;
import DTO.TipoImovelDTO;
/**
 * 
 * @author agemiro
 *
 */

public class TipoImovelDAOAdapterPostgre implements ITipoimovel{
	
	private Connection con = null;
	PreparedStatement pst = null;
	private TipoImovelDTO tipo;
	
	public TipoImovelDAOAdapterPostgre() {
		tipo = new TipoImovelDTO();
	}

	@Override
	public void cadastrar(TipoImovelDTO novo){
			
			try {
				con = ConexaoSingleton.getInstance();	
			pst = con.prepareStatement("INSERT INTO tipoimovel (nome,porcentagem) VALUES (?,?)");
			pst.setString(1, novo.getNome());
			pst.setFloat(2, novo.getPocetagem());
			pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			

	}

	@Override
	public void excluir(int cod) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM tipoimovel WHERE idtipo = ?");
			pst.setInt(1, cod);
			
			pst.execute();
			
			
		}catch(SQLException e) {
			
		}
	}

	@Override
	public void editar(TipoImovelDTO editado) {
		try {
			String sql = "UPDATE tipoimovel SET nome=?,porcentagem=? WHERE idtipo = ?";
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement(sql);
			pst.setString(2, editado.getNome());
			pst.setFloat(3, editado.getPocetagem());
			
			pst.setInt(1, editado.getId());
			pst.execute();
			System.out.println("editado com sucesso");
			
		}catch(SQLException e) {
			e.getStackTrace();
			
		}
	}

	@Override
	public TipoImovelDTO pesquisar(String nome) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM tipoimovel where nome=?");
			pst.setString(1, nome);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				tipo = new TipoImovelDTO();
				tipo.setId(rs.getInt("idtipo"));
				tipo.setNome(rs.getString("nome"));
				tipo.setPocetagem(rs.getFloat("porcentagem"));
			
			}
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		return tipo;
	}


	@Override
	public TipoImovelDTO lista() {
		
		ArrayList<TipoImovelDTO> lista = new ArrayList<>();
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT * FROM tipoimovel");
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				tipo = new TipoImovelDTO();
				tipo.setId(rs.getInt("idtipo"));
				tipo.setNome(rs.getString("nome"));
				tipo.setPocetagem(rs.getFloat("porcentagem"));
				lista.add(tipo);
				
			}
			tipo.setLista(lista);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		
		return tipo;
	}


	public int tamanho() {
		return lista().getLista().size();
	}

	public 	TipoImovelDTO pesquisarInt(int id) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM tipoimovel where idtipo=?");
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				tipo = new TipoImovelDTO();
				tipo.setId(rs.getInt("idtipo"));
				tipo.setNome(rs.getString("nome"));
				tipo.setPocetagem(rs.getFloat("porcentagem"));
			
			}
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		return tipo;
	}
	
}
