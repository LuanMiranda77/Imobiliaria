package DAO.Sql;
import java.sql.Connection;


public class TestConexao {
private static FabFactory criar;
	
	public static  FabFactory retornar() {
	
    
    	criar = new BDarquivo();

	return criar;
	}
	
	//test de finalizar conexão
	public static void main(String[] args) {
	for(int cont=0;cont<20;cont++) {	
		ConexaoSingleton.sairConexao();;
	}
	}
}
