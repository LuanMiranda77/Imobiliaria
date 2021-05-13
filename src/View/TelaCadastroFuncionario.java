package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Control.FuncionarioControl;
import DTO.FuncionarioDTO;


/**
 * 
 * @author André
 * 
 */

public class TelaCadastroFuncionario extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("serial")
		private JLabel titulo1;
		private JTextField nome, salario;
		private JPasswordField senha;
		private JComboBox<String> cargo;
		
		public TelaCadastroFuncionario(Boolean cond, FuncionarioDTO fdto) {
			setTitle("Cadastro de Funcionário");
			setBounds(400, 50, 500, 650);
			setLayout(null);
			getContentPane().setBackground(new Color(240, 240, 220));
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			label();
			textField(cond, fdto);
			botoes(cond, fdto);
			
			setVisible(true);
			repaint();		
		}
		
		public void botoes(Boolean cond, FuncionarioDTO funcionario) {
			if(cond) {
				JButton salvar = new JButton("Salvar");
				salvar.setBounds(80,500,100,60);
				salvar.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
			            funcionario.setNome(nome.getText().toUpperCase());
			            funcionario.setSenha(senha.getText());
			            funcionario.setSalario(Float.parseFloat(salario.getText()));
			            funcionario.setCargo(""+ cargo.getSelectedItem());
						FuncionarioControl.cadastrarFuncionario(funcionario);
						} catch (ExceptionFuncionario | NullPointerException | NumberFormatException e) {
						
							e.printStackTrace();
						}
						if(FuncionarioControl.listaDeFuncionarioDTO().getLista().size()==1) {
							dispose();
							new ViewLogin();
				        }
						else {
			            dispose();
			            new TelaFuncionario();
						}
					}
				});
				add(salvar);
			}
			
			else {
				setTitle("Editar Funcionario");
				titulo1.setText("EDIÇÃO DE FUNCIONARIOS");
				nome.setText(funcionario.getNome());
				senha.setText(funcionario.getSenha());
				salario.setText(""+funcionario.getSalario());
				cargo.setSelectedItem(funcionario.getCargo());
				JButton editar = new JButton("Editar");
				editar.setBounds(80,500,100,60);
				editar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
			            funcionario.setNome(nome.getText().toUpperCase());
			            funcionario.setSenha(senha.getText());
			            funcionario.setSalario(Float.parseFloat(salario.getText()));
			            funcionario.setCargo(""+ cargo.getSelectedItem());
			            FuncionarioControl.editar(funcionario);
			            dispose();
			            new TelaFuncionario();
					}
				});
				add(editar);
			}
			JButton sair = new JButton("Sair");
			sair.setBounds(300,500,100,60);
			sair.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					new TelaFuncionario();
				}
			});
			add(sair);
		}
		
		public void label() {
			JLabel titulo = new JLabel(new ImageIcon("icon/logo.png"),JLabel.CENTER);
			titulo.setBounds(168, 45, 200, 160);
			add(titulo);
			
			JLabel label2 = new JLabel("NOME");
			label2.setBounds(15 , 200, 150, 30);
			add(label2);
			
			JLabel label5  = new JLabel("CARGO");
			label5.setBounds(300, 200, 150, 30);
			add(label5);
			
			JLabel label3  = new JLabel("SENHA");
			label3.setBounds(300, 270, 150, 30);
			add(label3);
			
			JLabel label4  = new JLabel("SALÁRIO");
			label4.setBounds(15, 270, 150, 30);
			add(label4);
			
			JLabel nivel  = new JLabel(new ImageIcon("icon/casa.png"));
			nivel.setBounds(20, 350, 450, 450);
			add(nivel);
			
			//titulo da tela
			titulo1 = new LabelPadrao("CADASTRO DE FUNCIONARIOS", 10, -5,500, 50);
	        titulo1.setFont(new Font("Masque",Font.BOLD,25));
	        titulo1.setForeground(Color.darkGray);
			add(titulo1);
			
			JLabel barra = new JLabel();
			barra.setBounds(1,-20,800,60);
			barra.setBackground(Color.GRAY);
			barra.setOpaque(isBackgroundSet());
			add(barra);
			
			JLabel borda = new JLabel();
			borda.setBounds(0,0,500, 45);
			borda.setBackground(new Color(220,220,200));
			borda.setOpaque(true);
			add(borda);
			
			JLabel a = new JLabel();
			add(a);
		}
		
		public void textField(boolean cond, FuncionarioDTO fdto) {
			nome = new JTextField();
			nome.setBounds(15, 225, 200, 28);
			add(nome);
			
			
			salario = new JTextField();
			salario.setBounds(15, 295, 200, 28);
			add(salario);
			
			cargo = new JComboBox<String>();
			cargo.setModel(new DefaultComboBoxModel<String>(new String[] { "Gerente", "Corretor", "Servicos Gerais", "Secretaria"}));
			cargo.setBounds(300, 225, 150, 28);
			if(FuncionarioControl.listaDeFuncionarioDTO().getLista().size()==0) {
				cargo.setModel(new DefaultComboBoxModel<String>(new String[] { "Administrador"}));
			   cargo.setEnabled(false);
	        }
			add(cargo);
			
			senha = new JPasswordField();
			senha.setBounds(300, 295, 180, 28);
			add(senha);
		}
}
