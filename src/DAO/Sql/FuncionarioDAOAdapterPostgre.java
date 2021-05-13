package DAO.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.IFuncionarioDAO;
import DTO.FuncionarioDTO;
import View.ExceptionFuncionario;
/**
 * 
 * @author agemiro
 *
 */
public class FuncionarioDAOAdapterPostgre implements IFuncionarioDAO{
	private Connection con = null;
	PreparedStatement pst = null;
	private FuncionarioDTO func;
	
	public FuncionarioDAOAdapterPostgre() {
		func = new FuncionarioDTO();
	
	}

	@Override
	public void cadastrarFuncionario(FuncionarioDTO novo) throws ExceptionFuncionario{
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("INSERT INTO FUNCIONARIO (nome,cargo,senha,salario) VALUES (?,?,?,?)");
			pst.setString(1, novo.getNome());
			pst.setString(2, novo.getCargo());
			pst.setString(3, novo.getSenha());
			pst.setFloat(4, novo.getSalario());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void excluirFuncionario(FuncionarioDTO lixo) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM FUNCIONARIO WHERE idfunc = ?");
			pst.setInt(1, lixo.getId());
			
			pst.execute();
			
			
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public void editarFuncionario(FuncionarioDTO editado) {
		try {
			String sql = "UPDATE FUNCIONARIO SET nome=?,cargo=?, senha=?,salario=? WHERE idfunc = ?";
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement(sql);
			pst.setString(1, editado.getNome());
			pst.setString(2, editado.getCargo());
			pst.setString(3, editado.getSenha());
			pst.setFloat(4, editado.getSalario());
			
			pst.setInt(5, editado.getId());
			pst.execute();

			
		}catch(Exception e) {
			e.getStackTrace();
			
		}
	}

	@Override
	public FuncionarioDTO pesquisarFuncionario(FuncionarioDTO novo) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM funcionario where idfunc=?");
			pst.setInt(1, novo.getId());
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				func = new FuncionarioDTO();
				func.setId(rs.getInt("idfunc"));
				func.setNome(rs.getString("nome"));
				func.setCargo(rs.getString("cargo"));
				func.setSenha(rs.getString("senha"));
				func.setSalario(rs.getFloat("salario"));
			}
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return func;
	}


	@Override
	public FuncionarioDTO listaDeFuncionarioDTO() {
		ArrayList<FuncionarioDTO> lista = new ArrayList<>();
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT * FROM funcionario");
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				func = new FuncionarioDTO();
				func.setId(rs.getInt("idfunc"));
				func.setNome(rs.getString("nome"));
				func.setCargo(rs.getString("cargo"));
				func.setSenha(rs.getString("senha"));
				func.setSalario(rs.getFloat("salario"));
				lista.add(func);
				
			}
			func.setLista(lista);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		
		return func;
	}
	
	@Override
	public FuncionarioDTO verificaLogin(FuncionarioDTO fdto) {
		
		FuncionarioDTO f2= new FuncionarioDTO(); 
		for (FuncionarioDTO f :listaDeFuncionarioDTO().getLista()) {
			if(f.getNome().equals(fdto.getNome()) && f.getSenha().equals(fdto.getSenha())) {
				return f2=f;
			}
		}
		
		f2.setId(-1);
		return f2;
	}
	

}
