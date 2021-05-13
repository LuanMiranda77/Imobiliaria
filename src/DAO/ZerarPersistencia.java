package DAO;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ZerarPersistencia  {
	public static void zerar(String pers) {
		switch (pers) {
		
		case "FUNCIONARIO":
        FuncionarioDAO fun = new FuncionarioDAO();
        ArrayList<ArrayList<String>>lista = new ArrayList<ArrayList<String>>();
		fun.salvarCentral(lista,"xml/fun.xml");
		break;
		
		case "CLIENTE" :
		ClienteDAO pres1 = new ClienteDAO();
		ArrayList<ArrayList<String>>cdi1 = new ArrayList<ArrayList<String>>();
		pres1.salvarCentral(cdi1,"xml/cliente.xml");
		break;
		
		case "IMOVEL" :
		ImovelDAO pres2 = new ImovelDAO();
		ArrayList<ArrayList<String>>cdi2 = new ArrayList<ArrayList<String>>();
		pres2.salvarCentral(cdi2,"xml/imovel.xml");
		break;
		
		case "TIPO":
		TipoImovelDAO ingred = new TipoImovelDAO();
		ArrayList<ArrayList<String>>lista1 = new ArrayList<ArrayList<String>>();
		ingred.salvarCentral(lista1,"xml/tipo.xml");
		break;
		
		case "COMPRA":
		CompraDAO pres7 = new CompraDAO();
		ArrayList<ArrayList<String>> cdi7 = new ArrayList<>();
		pres7.salvarCentral(cdi7,"xml/compra.xml");
		break;
		
		case "DIVIDA":
			CompraDAO pres8 = new CompraDAO();
			ArrayList<ArrayList<String>> cdi8 = new ArrayList<>();
			pres8.salvarCentral(cdi8,"xml/divida.xml");
			break;
		}
		
	}
	public static void main(String[] args) {
		String para="";
		while(!para.equals("para")){
		String[] lista= {"COMPRA","FUNCIONARIO","CLIENTE","TIPO","IMOVEL","para"};
		Object result = JOptionPane.showInputDialog(null,"Qual banco dados voce quer zera ?","lista",
													JOptionPane.QUESTION_MESSAGE,null, lista, lista[0]);
		para=(String)result;
		ZerarPersistencia.zerar((String)result);
		JOptionPane.showMessageDialog(null,"banco de dados zerado!");
		}		
	} 
   
		

	

}
