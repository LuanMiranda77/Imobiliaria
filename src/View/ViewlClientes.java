package View;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ViewlClientes extends Principal {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6100634636482570749L;
	TableClientes tabela;

	
	public ViewlClientes(String nome) {
		setTitle("Lista de Clientes");
		setBounds(400, 50, 800, 500);
		setResizable(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tabela(this);
		botoes(nome);
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
	public void botoes(String nome) {
	 
	 BotoesGeral pes = new BotoesGeral("Pesquisar",new ImageIcon("Icon/peesquisacli.png"), 120, 10, 100, 110);
	 add(pes);
	 pes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	     String n= JOptionPane.showInputDialog(null, "Digite o nome do pra pesquisa", "Pesquisar",
		 JOptionPane.INFORMATION_MESSAGE);
	     n=n.toUpperCase();
	     
	     if (tabela.filtroNome(n)==false) {
	    	 JOptionPane.showMessageDialog(null, "Cliente não cadastrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	     }
	     else
	     tabela.limparTabela();
	     tabela.filtroNome(n);
	     tabela.repaint();
	     
			}
	 });
	 
	 BotoesGeral adic = new BotoesGeral("Adicionar",new ImageIcon("Icon/salvarcli.png"), 10, 10, 100, 110);
	 add(adic);
	 adic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha=tabela.seletctID();
				if(linha==-1) {
					JOptionPane.showMessageDialog(null, "Selecionar uma cliente pra ser adicionado","Aviso", JOptionPane.ERROR_MESSAGE);
				}
				else {
				
				if(nome.equals("recibo")) {
					ReciboView.cliente(linha);
					dispose();
				}
				else {
					VIewPDV2.recCliente(linha);
					dispose();
				}
				
				
				}
				
			}
				
			});
	 
	}
	public void tabela(JFrame janela) {
		tabela = new TableClientes();
		tabela.gerarTabela(janela,"");
		tabela.repaint();
		janela.add(tabela);
	}

}
