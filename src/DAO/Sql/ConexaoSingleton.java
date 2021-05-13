package DAO.Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoSingleton {
    
	private final static String URL = "jdbc:postgresql://localhost:5433/bdImo"; // incica o caminho do banco de dados
	private final static String USER = "postgres"; // aqui vai o nome usuario que vc quer acessar
	private final static String PASS = "ads54321"; // aqui a senha do seu banco
	private static Connection conexao;
	private volatile static Connection instance;
	
	private ConexaoSingleton() {
	
	}
	
	public  static Connection getInstance() {
		if( instance == null ){
			synchronized (ConexaoSingleton.class){
		if( instance == null ) {
		   instance = new ConexaoSingleton().criarConcexao();
			}
		}
	}
		return instance;
		}
	
	
	private Connection criarConcexao() {
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("conectado");
			return conexao= DriverManager.getConnection(URL, USER, PASS);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
			
		}
	}

	
	public static void sairConexao(){
	
		try {
			   conexao.close(); 
			   conexao=null;
		   System.out.println("Finalizado...");
		} catch (SQLException e) {
		   // Tratar Exceptions
		} 
	}
	public static void main(String[] args) {
		for(int cont=0;cont<10;cont++) {
			ConexaoSingleton.sairConexao();
		}
	}
	

}
