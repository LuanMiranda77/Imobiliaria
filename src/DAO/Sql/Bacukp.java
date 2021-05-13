package DAO.Sql;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.ClienteDAO;
import DAO.FuncionarioDAO;
import DAO.ImovelDAO;
import DAO.ZerarPersistencia;
import DTO.ClienteDTO;
import DTO.FuncionarioDTO;
import Model.Imovel;
import View.ExceptionCliente;
import View.ExceptionFuncionario;

/*
 * @ autor dessa luan marinate 
 */

public class Bacukp  {
	private File origem;
	private File destino; 
	BufferedInputStream copiar = null;
	FileOutputStream colar = null;
	
	public String gerarBackup() {
		       final List<String> comandos = new ArrayList<String>();    
		       String c ="";
		       //comandos.add("C:\\Program Files (x86)\\PostgreSQL\\8.4\\bin\\pg_dump.exe");     // esse é meu caminho
		      comandos.add("C:\\postgre94\\bin\\pg_dump.exe");  
		      
		       comandos.add("-i");    
		       comandos.add("-h");    
		       comandos.add("localhost");    
		       comandos.add("-p");    
		       comandos.add("5433");    
		       comandos.add("-U");    
		       comandos.add("postgres");    
		       comandos.add("-F");    
		       comandos.add("c");    
		       comandos.add("-b");    
		       comandos.add("-v");    
		       comandos.add("-f");    
		       
		       comandos.add("d:\\banco_dados_bkp.sql");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.
		       comandos.add("bdimo");    
		   
		       ProcessBuilder pb = new ProcessBuilder(comandos);    
		       
		       pb.environment().put("PGPASSWORD", "ads54321");            
		       
		       try {    
		           final Process process = pb.start();    
		   
		           final BufferedReader r = new BufferedReader(    
		               new InputStreamReader(process.getErrorStream()));    
		           String line = r.readLine();    
		           while (line != null) {    
		          // System.err.println(line); 
		           line = r.readLine(); 
		           }    
		           r.close();    
		   
		           process.waitFor();
		           
		   
		       } catch (IOException e) {    
		           e.printStackTrace();    
		       } catch (InterruptedException ie) {    
		           ie.printStackTrace();    
		       }
			return "backup realizado com sucesso.";      
				
	}
	private void bCliente() {
		ClienteDAOPostgre con = new ClienteDAOPostgre();
		ClienteDAO lista = new ClienteDAO();
		origem = new File("C:\\");
		destino = new File("D:\\pica");
		
		if(lista.lista().getArrayDTO().size()==0) {
			for(ClienteDTO c: con.lista().getArrayDTO()) {
				ClienteDTO novo = c;
				try {
					lista.cadastrar(novo);
					
				} catch (ExceptionCliente e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			copiar(origem);
			colar(destino);
			ZerarPersistencia.zerar("CLIENTE");
			
		}
		else {
			copiar(origem);
			colar(destino);
			
		}
	}
		private void bFuncionario() {
			FuncionarioDAOAdapterPostgre con = new FuncionarioDAOAdapterPostgre();
			FuncionarioDAO lista = new FuncionarioDAO();
			origem = new File("xml/fun.xml");
			destino = new File("backup/funCopia.xml");
			
			if(lista.listaDeFuncionarioDTO().getLista().size()==0) {
				for(FuncionarioDTO c: con.listaDeFuncionarioDTO().getLista()) {
					FuncionarioDTO novo = c;
						try {
							lista.cadastrarFuncionario(novo);
						} catch (ExceptionFuncionario e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				copiar(origem);
				colar(destino);
				ZerarPersistencia.zerar("FUNCIONARIO");
			}

			else {
				copiar(origem);
				colar(destino);
				
			}
			
	}
	
		/*private void bImovel() {
			ImovelDAOAdapterPostgre con = new 	ImovelDAOAdapterPostgre();
			ImovelDAO lista = new 	ImovelDAO();
			origem = new File("xml/fun.xml");
			destino = new File("backup/funCopia.xml");
			
			if(lista.listaDeFuncionarioDTO().getLista().size()==0) {
				for(FuncionarioDTO c: con.listaDeFuncionarioDTO().getLista()) {
					FuncionarioDTO novo = c;
						try {
							lista.cadastrarFuncionario(novo);
						} catch (ExceptionFuncionario e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				copiar(origem);
				colar(destino);
				ZerarPersistencia.zerar("FUNCIONARIO");
			}

			else {
				copiar(origem);
				colar(destino);
				
			}
			*/
		
	public void copiar(File origem) {
		try {
			copiar = new BufferedInputStream(new FileInputStream(origem));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void colar(File destino) {
		try {
			colar = new FileOutputStream(destino);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int cont = 0;
		byte[]bytes = new byte[1024];
		try {
			while((cont=copiar.read(bytes))>=0) {
				colar.write(bytes,0,cont);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		Bacukp c =new Bacukp();
		c.bCliente();
		System.out.println();
	}

}
