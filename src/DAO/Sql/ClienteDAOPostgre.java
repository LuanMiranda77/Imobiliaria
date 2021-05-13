package DAO.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.IClienteDAO;
import DTO.ClienteDTO;
import DTO.DividaDTO;
import View.ExceptionCliente;
import View.ExceptionDivida;
/**
 * 
 * @author agemiro
 *
 */
public class ClienteDAOPostgre implements IClienteDAO{
	private Connection con = null;
	PreparedStatement pst = null;
	private ClienteDTO cliente;
	private DividaDTO divida;
	private DividaDAOAdapterPostgre2  ddao;
	
	public ClienteDAOPostgre() {
		cliente = new ClienteDTO();
	}

	@Override
	public void cadastrar(ClienteDTO novo) throws ExceptionCliente {
		//codigo pra conexao de banco de daos pSostgre
		String []end =novo.getEndereco().split("-"); 
		

			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement("INSERT INTO cliente (cpf,nome,rua,num,bairro,cidade,telefone) VALUES (?,?,?,?,?,?,?)");
				pst.setString(1, novo.getCpf());
				pst.setString(2, novo.getNome());
				pst.setString(3, end[0]);
				pst.setString(4, end[1]);
				pst.setString(5, end[2]);
				pst.setString(6, end[3]);
				pst.setString(7, novo.getTelefone());
				pst.execute();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	
				
			}
			
			
	}

	@Override
	public void excluir(int cod) throws ExceptionDivida {
		//codigo pra conexao de banco de daos postgre
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM cliente WHERE idcliente = ?");
			pst.setInt(1, cod);
			pst.execute();
		}catch(SQLException e) {
			
		}
	}

	@Override
	public void editar(ClienteDTO editado) {
			String []end =editado.getEndereco().split("-"); 
			String sql = "UPDATE cliente SET nome=?,cpf=?,rua=?,num=?,bairro=?,cidade=?,telefone=? WHERE idcliente = ?";
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, editado.getNome());
				pst.setString(2, editado.getCpf());
				pst.setString(3, end[0]);
				pst.setString(4, end[1]);
				pst.setString(5, end[2]);
				pst.setString(6, end[3]);
				pst.setString(7, editado.getTelefone());
				pst.setInt(8, editado.getId());
				pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("editado com sucesso");
			
	
	}

	@Override
	public ClienteDTO pesquisar(int cod) {
	
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM cliente where idcliente=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				String end ="";
				cliente = new ClienteDTO();
				cliente.setId(rs.getInt("idcliente"));
				cliente.setNome(rs.getString("nome"));
				end=rs.getString("rua")+"-";
				end+=rs.getString("num")+"-";
				end+=rs.getString("bairro")+"-";
				end+=rs.getString("cidade");
				cliente.setEndereco(end);
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cliente;
		
	}

	@Override
	public ClienteDTO lista() {
		 
		ArrayList<ClienteDTO> lista = new ArrayList<>();
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT * FROM cliente");
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				String end ="";
				cliente = new ClienteDTO();
				cliente.setId(rs.getInt("idcliente"));
				cliente.setNome(rs.getString("nome"));
				end=rs.getString("rua")+"-";
				end+=rs.getString("num")+"-";
				end+=rs.getString("bairro")+"-";
				end+=rs.getString("cidade");
				cliente.setEndereco(end);
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
				lista.add(cliente);
				
			}
			cliente.setArrayDTO(lista);
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return cliente;
	}

	@Override
	public int tamanho() {
		
		return lista().getArrayDTO().size();
	}

	

}
