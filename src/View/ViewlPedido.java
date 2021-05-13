package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Control.CompraControl;
import DTO.CompraDTO;


public class ViewlPedido extends Principal {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TableCompra tabela 	;
	CompraDTO compra = new CompraDTO();

	
	public ViewlPedido() {
		setTitle("Lista de Clientes");
		setBounds(400, 50, 700, 500);
		setResizable(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tabela(this);
		botoes();
		barra();
		setVisible(true);
		repaint();
		
	}
	public void barra() {
		JLabel barra = new JLabel();
		barra.setOpaque(true);
		barra.setBackground(Color.GRAY);
		barra.setBounds(0, 10, 700, 120);
		add(barra);
	}
	public void botoes() {
	
	 BotoesGeral editar = new BotoesGeral("Editar",new ImageIcon("Icon/PEDIDO.png"), 10, 10, 100, 110);
	 add(editar);
	 editar.setVisible(false);
	 editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String tela=e.getActionCommand();
				int linha1 =tabela.selectLinha();
				linha1++;
				dispose();
				//new TelaCadCliente(false,tela,linha1);
				}catch (Exception d) {
					
				}
			
			}
		});
	 
	 BotoesGeral excluir = new BotoesGeral("Excluir",new ImageIcon("Icon/canped.png"), 112, 10, 100, 110);
	 add(excluir);
	 excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha1 =tabela.seletctID();
			
			    if(linha1<0) {
			    	JOptionPane.showMessageDialog(null, "Selecione a linha pra exclusão", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			    	}	
			    else {
		    		tabela.excluirLinha(tabela.selectLinha());
		    		//int id = tabela.seletctID();
		    		System.out.println(linha1);
		    		compra.setId(linha1-1);
		    		CompraControl.excluirCompra(compra);
		    		JOptionPane.showMessageDialog(null, "pedido excluido com sucesso !", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			    	}
			}
		});
	 
	 BotoesGeral pes = new BotoesGeral("Pesquisar",new ImageIcon("Icon/pesquisaped.png"), 213, 10, 100, 110);
	 add(pes);
	 pes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	 try {
	     String n= JOptionPane.showInputDialog(null, "Digite o nome do cliente do pra pesquisa", "Pesquisar",
		 JOptionPane.INFORMATION_MESSAGE);
	     n=n.toUpperCase();
	     
	     if (tabela.filtroNome(n)==false) {
	    	 JOptionPane.showMessageDialog(null, "Cliente não cadastrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	     }
	     else
	     tabela.limparTabela();
	     tabela.filtroNome(n);
	     tabela.repaint();
	     
	}catch(NullPointerException k) {
		
	}
	}
	});
	 
	}
	public void tabela(JFrame janela) {
		tabela= new TableCompra();
		tabela.adicionarTabela(janela,"Atendente");
		tabela.revalidate();
		janela.add(tabela);
	}

}
