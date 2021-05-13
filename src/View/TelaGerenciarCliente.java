package View;
/**
 * @author Agemiro 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Control.ClienteControl;
import Control.DividaControl;
import DAO.DividaDAO;
import DTO.ClienteDTO;
import DTO.DividaDTO;

public class TelaGerenciarCliente extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TableClientes tabela;
	private JButton cadastrar;
	private JTextField campo; 
	
	public TelaGerenciarCliente() {
		setTitle("Gerencimento de Clientes");
		setSize(1000, 650);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		getContentPane().setBackground(new Color(240, 240, 220));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		botoes();
		labels();
		campo();
		tabela(this);
		
		this.setVisible(true);
	}
	
	public void labels() { 
		//titulo da tela
		JLabel titulo = new LabelPadrao("GERENCIAMENTO DE CLIENTES", 250, -5,600, 50);
        titulo.setFont(new Font("Masque",Font.BOLD,25));
        titulo.setForeground(Color.darkGray);
		add(titulo);
		
		JLabel barra = new JLabel();
		barra.setBounds(1,-20,1000,60);
		barra.setBackground(Color.GRAY);
		barra.setOpaque(isBackgroundSet());
		add(barra);
		
		JLabel borda = new JLabel();
		borda.setBounds(0,0,1000, 45);
		borda.setBackground(new Color(220,220,200));
		borda.setOpaque(true);
		add(borda);
		
		JLabel logo = new JLabel(new ImageIcon("icon/logo.png"));
        logo.setBounds(730, 430, 250, 200);
        add(logo);
		
		JLabel a = new JLabel();
		add(a);
		
	}
	
	public void botoes() {
		
		cadastrar =  new JButton("CADASTRAR", new ImageIcon("icon/add_icon.png"));
		cadastrar.setBounds(10, 60, 150, 35);
		cadastrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaCadastroCliente(true,null);
			}
		});
		add(cadastrar);
		
		JButton editar = new JButton("EDITAR", new ImageIcon("icon/editar_icon.png"));
		editar.setBounds(170, 60, 150, 35);
		editar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClienteDTO cliente = new ClienteDTO();
				int linha =  tabela.seletctID();
				if(linha >=0) {
					cliente=ClienteControl.pesquisarCliente(linha);
					       
					dispose();
					new TelaCadastroCliente(false,cliente);
				}else {
					JOptionPane.showMessageDialog(null, "Selecione uma linha!", "Aviso", 2);
				}
			}
		});
		add(editar);
		
		JButton excluir = new JButton("EXCLUIR", new ImageIcon("icon/remove_icon.png"));
		excluir.setBounds(330, 60, 150, 35);
		excluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id=tabela.seletctID();
				int linha=tabela.selecionarLinha();
				if(id >=0) {
					boolean band =false;
				    try {
				    	ClienteDTO cliente = ClienteControl.pesquisarCliente(id);
					
				        for(DividaDTO d : DividaControl.lista().getLista()) {
				        	if(d.getCliente().getId()==cliente.getId()) {
				        		JOptionPane.showMessageDialog(null, "Cliente não pode ser excluindo por ter divida", "Aviso",JOptionPane.ERROR_MESSAGE);
				        		band=true;
				        		break;
				    		}
				        	
				        }
				        if(band==false) {
				        tabela.excluirLinha(linha);
						ClienteControl.excluirCliente(id);
						JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso", "Aviso", 2);
				        }
					} catch (ExceptionDivida e1) {
						
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Selecione uma linha!", "Aviso", 2);
				}
			}
		});
		add(excluir);
		
		JButton pesquisar = new JButton("PESQUISAR", new ImageIcon("icon/lupa_icon.png"));
		pesquisar.setBounds(220,102,150,35);
		pesquisar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tabela.limparTabela();
				if(tabela.filtroNome(campo.getText().toUpperCase())==false) {
					JOptionPane.showMessageDialog(null, "Cliente não Cadastrado!", "Aviso", 2);
				}
				 tabela.repaint();
			
				
			}
		});
		add(pesquisar);
	}
	public void campo() {
		campo =  new JTextField();
		campo.setBounds(10, 105, 200, 30);
		campo.setToolTipText("Digite um nome para pesquisar");
		add(campo);
	}
	public void tabela(JFrame janela) {
		tabela = new TableClientes();
		tabela.gerarTabela(janela,"mercado");
		tabela.repaint();
		janela.add(tabela);
	}
	public static void main(String[] args) {
		new TelaGerenciarCliente();
	}

}
