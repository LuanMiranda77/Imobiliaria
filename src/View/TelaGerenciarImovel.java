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

import Control.ImovelControl;
import DTO.ImovelDTO;

public class TelaGerenciarImovel extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TabelaImoveis tabela;
	private JButton cadastrar;
	private JButton cadastrarTipo;
	private JTextField campo; 
	
	public TelaGerenciarImovel() {
		setTitle("Gerencimento de Imóveis");
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
		labels();
		
		this.setVisible(true);
	}
	
	public void labels() {
		
		//titulo da tela
		JLabel titulo = new LabelPadrao("GERENCIAMENTO DE IMÓVEIS", 270, -5, 600, 50);
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

		JLabel logo = new JLabel(new ImageIcon("Icon/logo.png"));
        logo.setBounds(730, 430, 250, 200);
        add(logo);
		
		JLabel a = new JLabel();
		add(a);
		
	}
	
	public void botoes() {
		
		ImovelDTO dto = new ImovelDTO();
		
		cadastrar =  new JButton("CADASTRAR", new ImageIcon("icon/add_icon.png"));
		cadastrar.setBounds(10, 60, 150, 35);
		cadastrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaCadastroImoveis(true, dto);
			}
		});
		add(cadastrar);
		
		cadastrarTipo =  new JButton("CADASTRAR TIPO", new ImageIcon("icon/add_icon.png"));
		cadastrarTipo.setBounds(700, 60, 180, 35);
		cadastrarTipo.setToolTipText("Clique para cadastrar um novo tipo de imóvel");
		cadastrarTipo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaCadastroTipoImovel();
			}
		});
		add(cadastrarTipo);
		
		JButton editar = new JButton("EDITAR", new ImageIcon("icon/editar_icon.png"));
		editar.setBounds(170, 60, 150, 35);
		editar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			int linha = tabela.retornarId();
				if(linha ==-1) {
					JOptionPane.showMessageDialog(null, "Selecione uma linha!", "Aviso", 2);
					
				}else {
					dto.setId(linha);
					dispose();
					new TelaCadastroImoveis(false, ImovelControl.pesquisarImovel(dto));
					
				}
			}
		});
		add(editar);
		
		JButton excluir = new JButton("EXCLUIR", new ImageIcon("icon/remove_icon.png"));
		excluir.setBounds(330, 60, 150, 35);
		excluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = tabela.retornarId();
				if(linha ==-1) {
					JOptionPane.showMessageDialog(null, "Selecione uma linha!", "Aviso", 2);
					
				}else {
					tabela.limparTabela(tabela.selecionarLinha());
					dto.setId(linha);
					ImovelControl.excluirImovel(dto);
					JOptionPane.showMessageDialog(null, "Imóvel excluído com sucesso!", "Excluído", 1);
					
				}
			}
		});
		add(excluir);
		
		JButton pesquisar = new JButton("PESQUISAR", new ImageIcon("icon/lupa_icon.png"));
		pesquisar.setBounds(220,102,150,35);
		pesquisar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tabela.limparTabela2();
				if(tabela.filtroNome(campo.getText())) {
				}
				else
					JOptionPane.showMessageDialog(null, "Imóvel não encontrado!", "Aviso", 2);
					
			}
		});
		add(pesquisar);
	}
	public void campo() {
		campo =  new JTextField();
		campo.setToolTipText("Digite um nome para pesquisar");
		campo.setBounds(10, 105, 200, 30);
		add(campo);
	}
	public void tabela(JFrame janela) {
		tabela = new TabelaImoveis();
		tabela.gerarTabela(janela,"estoque");
		tabela.repaint();
		janela.add(tabela);
	}

}
