package View;

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

import Control.FuncionarioControl;
import DTO.FuncionarioDTO;
/**
 * 
 * @author André
 * 
 */
public class TelaFuncionario extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TabelaFuncionario tabela;
	FuncionarioDTO fdto =  new FuncionarioDTO();
	private JTextField campo; 

	public TelaFuncionario() {
		setTitle("Gerenciamento de Funcionários");
		setSize(1000, 650);
		getContentPane().setBackground(new Color(240, 240, 220));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		botoes();
		label();
		campo();
		tabela(this);
		setVisible(true);
		repaint();		
	}
	
	public void label() {
		//titulo da tela
				JLabel titulo = new LabelPadrao("GERENCIAMENTO DE FUNCIONÁRIOS", 250, -5,600, 50);
		        titulo.setFont(new Font("Masque",Font.BOLD,25));
		        titulo.setForeground(Color.darkGray);
				add(titulo);
				
				JLabel barra = new JLabel();
				barra.setBounds(1,-20,1000,60);
				barra.setBackground(Color.GRAY);
				barra.setOpaque(isBackgroundSet());
				add(barra);
		
		JLabel logo = new JLabel(new ImageIcon("ICON/logo.png"),JLabel.CENTER);
		logo.setBounds(730, 400, 250, 200);
		add(logo);
		
		JLabel a = new JLabel();
		add(a);
	}
	
	public void botoes() {
		JButton cadastrar =  new JButton("CADASTRAR", new ImageIcon("icon/add_icon.png"));
		cadastrar.setBounds(10, 60, 150, 35);
		cadastrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaCadastroFuncionario(true, fdto);
			}
		});
		add(cadastrar);
		
		JButton editar = new JButton("EDITAR", new ImageIcon("icon/editar_icon.png"));
		editar.setBounds(170, 60, 150, 35);
		editar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = tabela.seletctID();
				if(linha ==-1) {
					JOptionPane.showMessageDialog(null, "Selecione uma linha!", "Atenção", 2);	
				}else {
					fdto.setId(linha);
					fdto=FuncionarioControl.pesquisarFuncionario(fdto);
					System.out.println(fdto.getNome()+fdto.getSalario());
					dispose();
					new TelaCadastroFuncionario(false, fdto);
				}
			}
		});
		add(editar);
		
		JButton excluir = new JButton("EXCLUIR", new ImageIcon("icon/remove_icon.png"));
		excluir.setBounds(330, 60, 150, 35);
		excluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int linha =  tabela.seletctID();
				if(linha ==-1) {
					JOptionPane.showMessageDialog(null, "Selecione uma linha!", "Atenção", 2);
				}else {
					
					tabela.excluirLinha(tabela.selectLinha());
					fdto.setId(linha);
					FuncionarioControl.excluirFuncionario(fdto);
					JOptionPane.showMessageDialog(null, "Funcionario excluindo com sucesso", "Atenção", 2);
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
				if(tabela.filtroNome(campo.getText()));
				
				else {
					JOptionPane.showMessageDialog(null, "Funcionario não encontrado", "Atenção", 2);
				}
				
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
		tabela = new TabelaFuncionario();
		tabela.gerarTabela(janela);
		tabela.repaint();
		janela.add(tabela);
	}
}
