package View;

/**
 * @author Agemiro
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Control.ClienteControl;
import DTO.ClienteDTO;
import DTO.DividaDTO;

import javax.swing.text.MaskFormatter;

public class TelaCadastroCliente extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campoNome;
	private JTextField campoTipoCadastro;
	private JTextField campoTelefone;
	private JTextField campoCidade;
	private JTextField campoRua;
	private JTextField campoNumero;
	private JTextField campoBairro;
	private JButton botaoCadastrar;
	private JButton botaoVoltar;
	
	public TelaCadastroCliente(boolean boo, ClienteDTO dto) {
		
		setTitle("Cadastro de Cliente");
		setSize(750, 650);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		getContentPane().setBackground(new Color(240, 240, 220));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fields();
		botoes(boo, dto);
		labels();
		this.setVisible(true);
		
	}

	public void labels() {
		
		JLabel titulo = new LabelPadrao("CADASTRO DE CLIENTE", 200, -5,500, 50);
		titulo.setFont(new Font("Masque",Font.BOLD,25));
		titulo.setForeground(Color.darkGray);
		add(titulo);
				
		JLabel barra = new JLabel();
		barra.setBounds(1,-20,800,60);
		barra.setBackground(Color.GRAY);
		barra.setOpaque(isBackgroundSet());
		add(barra);
		
		JLabel tipoPessoa = new LabelPadrao("Tipo Pessoa", 30, 40, 80, 30);
		add(tipoPessoa);
		JLabel tipoPessoa2 = new LabelPadrao("CPF ou CNPJ", 130, 40, 80, 30);
		add(tipoPessoa2);
		JLabel nome = new LabelPadrao("NOME", 20, 120, 50, 30);
		add(nome);
		JLabel telefone = new LabelPadrao("TELEFONE", 20, 280, 70, 30);
		add(telefone);		
		JLabel cidade = new LabelPadrao("CIDADE", 350, 200, 80, 30);
		add(cidade);	
		JLabel rua = new LabelPadrao("RUA / AVENIDA", 350, 120, 100, 30);
		add(rua);	
		JLabel numero = new LabelPadrao("NÚMERO", 670, 120, 110, 30);
		add(numero);	
		JLabel bairro = new LabelPadrao("BAIRRO", 20, 200, 50, 30);
		add(bairro);	
		JLabel imagem = new JLabel(new ImageIcon("icon/casa.png"));
		imagem.setBounds(0, 323, 512, 512);
		add(imagem);
		JLabel logo = new JLabel(new ImageIcon("Icon/logo.png"));
        logo.setBounds(480, 400, 250, 200);
        add(logo);
		JLabel quadro = new JLabel(new ImageIcon("icon/quadro.png"));
		quadro.setBounds(22, 44, 86, 67);
		add(quadro);
		
	}

	public void fields() {
		
		OuvinteExternoDeFoco ouvinteDeFoco = new OuvinteExternoDeFoco();
		campoNome = new TextFieldPadrao(20, 150, 250, 35);
		add(campoNome);

		JRadioButton rbCPF = new JRadioButton("CPF", false);
		JRadioButton rbCNPJ = new JRadioButton("CNPJ", false);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbCPF);
		bg.add(rbCNPJ);
		rbCPF.setBounds(30, 70, 50, 15);
		rbCNPJ.setBounds(30, 90, 60, 15);
		add(rbCNPJ);
		add(rbCPF);
		
		campoTipoCadastro = new JTextField();
		campoTipoCadastro.setBounds(130, 70, 130, 35);
		campoTipoCadastro.setEnabled(false);
		
		add(campoTipoCadastro); 
		
		rbCPF.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				MaskFormatter cpf;
				try {
					campoTipoCadastro.setEnabled(true);
					cpf =  new MaskFormatter(format("CPF"));
					remove(campoTipoCadastro);
					cpf.setValidCharacters("0123456789");
					campoTipoCadastro = new JFormattedTextField(cpf);
					campoTipoCadastro.setBounds(130, 70, 130, 35);
				}catch (ParseException e1) {}
				add(campoTipoCadastro);    
			}
		});
		
		rbCNPJ.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				MaskFormatter cnpj;
				try {
					campoTipoCadastro.setEnabled(true);
					cnpj =  new MaskFormatter(format("CNPJ"));
					remove(campoTipoCadastro);
					cnpj.setValidCharacters("0123456789");
					campoTipoCadastro = new JFormattedTextField(cnpj);
					campoTipoCadastro.setBounds(130, 70, 130, 35);
				}catch (ParseException e1) {}
				add(campoTipoCadastro);  
			}
		});
		  
		try {
			MaskFormatter telefone = new MaskFormatter("(**) * ****-****");
			telefone.setValidCharacters("0123456789");		
			campoTelefone = new JFormattedTextField(telefone);
			campoTelefone.setBounds(20, 310, 120, 35);
			campoTelefone.addFocusListener(ouvinteDeFoco);
		}catch (ParseException e2) {}
		add(campoTelefone);
					
		campoRua = new TextFieldPadrao(350, 150, 250, 35);
		add(campoRua);
		campoNumero = new TextFieldPadrao(670, 150, 50, 35);
		add(campoNumero);
		campoCidade = new TextFieldPadrao(350, 230, 250, 35);
		add(campoCidade);
		campoBairro = new TextFieldPadrao(20, 230, 250, 35);
		add(campoBairro);
		
	}
	
	private void botoes(boolean condicao, ClienteDTO dto) {
		
		if(condicao) {

			botaoCadastrar = new JButton("CADASTRAR", new ImageIcon("icon/ok.png"));
			botaoCadastrar.setBounds(195, 380, 150, 35);
			botaoCadastrar.setToolTipText("Clique para cadastrar o imóvel");
			
			OuvinteInternoCadastroCliente ouvinteCliente = new OuvinteInternoCadastroCliente();
	
			botaoCadastrar.addActionListener(ouvinteCliente);
	
			add(botaoCadastrar);
		
		}else {
			setTitle("Edição de Cadastro de Cliente");

			JButton botaoEditar = new JButton("SALVAR", new ImageIcon("icon/ok.png"));
			botaoEditar.setBounds(195, 380, 150, 35);
			botaoEditar.setToolTipText("Clique para salvar a edição do cadastro do cliente");
			
			//seta com o dto de cliente
	     	campoNome.setText(dto.getNome());//dto.getNome();
			campoTipoCadastro.setText(dto.getCpf()); // e assim por diante
			campoTelefone.setText(dto.getTelefone());
			String [] end = dto.getEndereco().split("-");
			campoRua.setText(end[0]);
			campoNumero.setText(end[1]);
			campoBairro.setText(end[2]);
			campoCidade.setText(end[3]);
			
			botaoEditar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
		            dto.setNome(campoNome.getText().toUpperCase());
		            dto.setCpf(campoTipoCadastro.getText().toUpperCase());
		            dto.setTelefone(campoTelefone.getText().toUpperCase());
		            dto.setEndereco(campoRua.getText().toUpperCase() + "-"
		            + campoNumero.getText().toUpperCase() + "-" + campoBairro.getText().toUpperCase()+"-"+campoCidade.getText().toUpperCase());
		            //salvar edicao
					ClienteControl.editar(dto);
					JOptionPane.showMessageDialog(null, "Edição realizada com sucesso!", "Feito", JOptionPane.INFORMATION_MESSAGE);
		            dispose();
		            new TelaGerenciarCliente();
				}
			});
			add(botaoEditar);
		}
		
		botaoVoltar = new JButton("VOLTAR", new ImageIcon("icon/voltar.png"));
		botaoVoltar.setBounds(430, 380, 150, 35);
		botaoVoltar.setToolTipText("Clique para voltar");
			
		botaoVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaGerenciarCliente();
			}
		});
		add(botaoVoltar);

	}
	
	private String format(String type) {
	    switch(type) {
	        case "CNPJ":
	            return "***.***.***/****-**";
	        default:
	             return "***.***.***-**";
	    }
	}

	
	public class OuvinteInternoCadastroCliente implements ActionListener {

		public void actionPerformed(ActionEvent evento) {
			
			String label = evento.getActionCommand();
			
			switch (label) {
			
				case "CADASTRAR":
					
					String tipoPessoa = campoTipoCadastro.getText();
					String nome = campoNome.getText().toUpperCase();
					String telefone = campoTelefone.getText().toUpperCase();
					String rua = campoRua.getText().toUpperCase();
					String numero = campoNumero.getText().toUpperCase();
					String bairro = campoBairro.getText().toUpperCase();
					String cidade = campoCidade.getText().toUpperCase();
					String endereco = rua + "-" + numero + "-" + bairro + "-" + cidade;
			
					boolean confere = true;
					
					if(tipoPessoa.equals("   .   .   -  ") || tipoPessoa.equals("   .   .   /    -  ") || tipoPessoa.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o CPF ou CNPJ do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}				
					if (nome.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o nome do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}					
					if(rua.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite a rua do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}			
					if(numero.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o numero residencial do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}			
					if(bairro.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o bairro do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}
					if(cidade.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite a cidade do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}
					if(telefone.equals("(  )       -    ")) {
						JOptionPane.showMessageDialog(null, "Por favor, digite o telefone do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
						confere = false;
						break;
					}

					if (confere) {
				
						ClienteDTO dto = new ClienteDTO();
						DividaDTO divida = new DividaDTO();
						divida.setId(0);					
				
						dto.setNome(nome);
						dto.setCpf(tipoPessoa);
						dto.setTelefone(telefone);
						dto.setEndereco(endereco);
						dto.setDivida(divida);
				
						try {
							ClienteControl.cadastrarCliente(dto);
							JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Cadastrado", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							new TelaGerenciarCliente();
						} catch (ExceptionCliente e) {
							campoTipoCadastro.setRequestFocusEnabled(true);
							campoTipoCadastro.setBackground(Color.ORANGE);
						}
				
					}
					break;
			}
		}	
	} 
	public static void main(String[] args) {
		new TelaCadastroCliente(true, null);
	}
}
