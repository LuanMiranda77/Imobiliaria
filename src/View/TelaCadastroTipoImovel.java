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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Control.TipoControl;
import DTO.TipoImovelDTO;

public class TelaCadastroTipoImovel extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton cadastrar;
	private JTextField campoTipo;
	private JComboBox<String> porc;
	private TabelaTipoImoveis tabela;
	
	public TelaCadastroTipoImovel() {
		setTitle("Cadastro Tipo de Imóveis");
		setSize(650, 550);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		getContentPane().setBackground(new Color(240, 240, 220));	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tabela(this);
		labels();
		campo();
		botoes();
		
		
		this.setVisible(true);
	}
	
	private void labels() {
		
		JLabel titulo = new LabelPadrao("CADASTRO DE TIPO DE IMÓVEIS", 82, -5, 600, 50);
		titulo.setFont(new Font("Masque",Font.BOLD,25));
	    titulo.setForeground(Color.darkGray);
		add(titulo);
				
		JLabel barra = new JLabel();
		barra.setBounds(1,-20,1000,60);
		barra.setBackground(Color.GRAY);
		barra.setOpaque(isBackgroundSet());
		add(barra);
				
		JLabel nome = new LabelPadrao("Nome (Tipo)", 25, 130, 400, 30);
		add(nome);
		
		/*JLabel porc = new LabelPadrao("% de Lucro", 200, 130, 400, 30);
		add(porc);*/
		
		JLabel imagem = new JLabel(new ImageIcon("icon/casa.png"));
		imagem.setBounds(190, 220, 512, 512);
		add(imagem);
	}
	
	private void campo() {
		campoTipo = new JTextField();
		campoTipo.setBounds(25, 160, 150, 30);
		campoTipo.setToolTipText("Digite aqui um novo tipo de imóvel");
		add(campoTipo);
		/*String [] lista = {"2.0","3.0","5.0","6.0"};
		porc = new JComboBox<String>(lista);
		porc.setBounds(200, 160, 50, 30);
		porc.setToolTipText("Digite aqui a porcetagem de lucro");
		add(porc);*/
	}
	
	private void botoes() {
		cadastrar =  new JButton("CADASTRAR", new ImageIcon("icon/ok.png"));
		cadastrar.setBounds(25, 80, 150, 35);
		cadastrar.setToolTipText("Clique para cadastrar um novo tipo de imóvel");
		add(cadastrar);
		cadastrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean confere = true;
				
				if(campoTipo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor, digite um nome referente ao tipo de imóvel!", "Aviso", JOptionPane.WARNING_MESSAGE);
					confere = false;
				}		
				
				if (confere) {

					TipoImovelDTO dto = new TipoImovelDTO();
			
					dto.setNome(campoTipo.getText().toUpperCase());
					/*String p =(String) porc.getSelectedItem();
					float res=0;
					if(p.equals("1.0")) {
						res=0.1f;
					}
					else if (p.equals("2.0")) {
						res=0.2f;
					}
					else if (p.equals("3.0")) {
						res=0.3f;
					}
					else if (p.equals("5.0")) {
						res=0.5f;
					}
					else if (p.equals("6.0")) {
						res=0.6f;
					}*/
					dto.setPocetagem(0.0f);
					TipoControl.cadastrar(dto);
					JOptionPane.showMessageDialog(null, "Tipo de imóvel cadastrado com sucesso!", "Cadastrado", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new TelaCadastroTipoImovel();
			
				}				
			}
		});
		JButton excluir =  new JButton("EXCLUIR", new ImageIcon("icon/remove_icon.png"));
		excluir.setBounds(240, 80, 150, 35);
		excluir.setToolTipText("Clique para excluir um tipo");
		excluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int linhaSelecionada = tabela.retornarId();
				
				if (linhaSelecionada == -1) {
					
					JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada!", "Aviso", JOptionPane.WARNING_MESSAGE);
					
				} else {			
		
					JOptionPane.showMessageDialog(null, "Tipo de imóvel removido com sucesso!", "Tipo de imóvel removido", JOptionPane.INFORMATION_MESSAGE);
					tabela.excluirLinha(tabela.selecionarLinha());
					TipoControl.excluir(linhaSelecionada);
					dispose();
					new TelaCadastroTipoImovel();

						
				}
			}
			
		});
		add(excluir);
		
		JButton voltar =  new JButton("VOLTAR", new ImageIcon("icon/voltar.png"));
		voltar.setBounds(450, 80, 150, 35);
		voltar.setToolTipText("Clique para voltar a página anterior");
		voltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaGerenciarImovel();
			}
		});
		add(voltar);
	}
	
	public void tabela(JFrame janela){
		tabela = new TabelaTipoImoveis();
		tabela.tabelaTipoImoveis(janela);
		tabela.repaint();
		janela.add(tabela);
	}
}
