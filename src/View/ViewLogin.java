package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Control.ControlCentral;
import DAO.Sql.ConexaoSingleton;
import DAO.Sql.TestConexao;
import DTO.FuncionarioDTO;
import Model.Funcionario;




public class ViewLogin extends Principal {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField email;
	private JPasswordField senha;
	private FuncionarioDTO fun = new FuncionarioDTO();
	
	private TelaMenu janela;
	
	
	
	public ViewLogin() {
	setSize(400,350);
	setResizable(false);
	setLocationRelativeTo(null);
	setTitle("Login");
	camposDoLogin(this);
	botoesDoLogin(this);
	labelDoLogin();
	blackgroud();
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	repaint();
	
	}
	
	public void blackgroud() {
		JLabel contabil = new JLabel(new ImageIcon("Icon/fundologin.png"));
		contabil.setBounds(1,1,400,400
				);
		add(contabil);
		
	}
	private void labelDoLogin() {
		JLabel nome  = new JLabel("LOGIN");
		nome.setFont(new Font("Times New Roman",Font.BOLD,18));
		nome.setForeground(Color.RED);
		nome.setBounds(170, -3, 500, 30);
		nome.requestFocus();
		add(nome);
	
		JLabel label2 = new JLabel("Usuario", JLabel.HORIZONTAL);
		label2.setForeground(Color.black);
		label2.setBounds(-10, 85, 200, 200);
		add(label2);
		
		JLabel label3 = new JLabel("Senha", JLabel.HORIZONTAL);
		label3.setForeground(Color.black);
		label3.setBounds(-10, 117, 200, 200);
		add(label3);
		
		JLabel logo = new JLabel(new ImageIcon("Icon/login.png"));
		logo.setBounds(110, 15, 150, 150);
		add(logo);
	}

	public void botoesDoLogin(JFrame frame) {
		
		JButton botao2 = new JButton("Entrar");
		botao2.setBounds(130, 245, 130, 35);
		botao2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean band = false;
				
				
				if(email.getText().equals("")) {
					JOptionPane.showMessageDialog(janela, "Campo usuario vazio", "Aviso",JOptionPane.INFORMATION_MESSAGE);
					email.requestFocus();
					band=true;
				}
				else if(senha.getText().equals("")) {
					JOptionPane.showMessageDialog(janela, "Campo do senha vazio", "Aviso",JOptionPane.INFORMATION_MESSAGE);
				    senha.requestFocus();
				    band=true;
				}
				else {
					String n =email.getText().toUpperCase();
					fun.setNome(n);
					fun.setSenha(senha.getText());
					fun=Funcionario.verificaLogin(fun);
					
					 if(fun.getId()==-1){
						JOptionPane.showMessageDialog(janela, "Senha ou Login invalido", "Aviso",JOptionPane.ERROR_MESSAGE);
					}
					else {
							JOptionPane.showMessageDialog(janela, "Logado com sucesso!", "Logado",JOptionPane.INFORMATION_MESSAGE);
							band=true;
							ControlCentral.setLogado(fun);
							dispose();
							new TelaMenu(fun.getCargo());
						    }
					}
				
			}
		});
		frame.add(botao2);
		
	}
	
	public void camposDoLogin(JFrame frame) {
		email = new JTextField();
		email.setBounds(120,170,150,30);
		frame.add(email);
		
		senha = new JPasswordField();
		senha.setBounds(120,205,150,30);
		frame.add(senha);
		
		JTextField senha2 = new JTextField();
		senha2.setBounds(120,205,150,25);
		add(senha2);
		
		JLabel olho = new JLabel(new ImageIcon("Icon/olhof.png"));
		olho.setBounds(282,188,50,50);
		add(olho);
		olho.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent e) {
				olho.setIcon(new ImageIcon("Icon/olhoa.png"));
				senha.setVisible(false);
				senha2.setText(senha.getText());
				senha2.setVisible(true);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				olho.setIcon(new ImageIcon("Icon/olhof.png"));
				senha.setVisible(true);
				senha2.setVisible(false);
			}
			
		});
		
	}
	
		
	public void fechar() {
		
		 System.out.println("DESCONECTADO");
	}

}
