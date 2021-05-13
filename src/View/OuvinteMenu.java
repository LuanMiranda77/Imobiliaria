package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Central;





public class OuvinteMenu implements ActionListener {
	TelaMenu t;
	
	public void actionPerformed(ActionEvent e) {
		
		String tela=e.getActionCommand();
		
		switch (tela) {
		
			
		case"PDV":
			new VIewPDV2();
				
			break;
			
		case"<html>Clientes<html>":
			new TelaGerenciarCliente();
			
			break;
			
		case"<html>Imoveis<html>":
			new TelaGerenciarImovel();
			

			break;
			
		case"<html>Funcionarios<html>":
			new TelaFuncionario();
			break;
			
		
	   case"<html>Relatorio<html>":
		   new TelaContabilidade();
		
		 break;	
		
	   case"<html>Vendido<html>":
		   new TelaImoveisVendido();
		 break;
		 
	   default:
		   new ReciboView();
		 
		}
	
		
		
	}

}
