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
import javax.swing.JTextField;

public class TelaImoveisVendido extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TabelaImoveis tabela;
	private JTextField campo; 
	
	public TelaImoveisVendido() {
		setTitle("Imóveis Vendidos");
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
		JLabel titulo = new LabelPadrao("IMÓVEIS VENDIDOS", 350, -5,600, 50);
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
		
		
		JButton pesquisar = new JButton("PESQUISAR", new ImageIcon("icon/lupa_icon.png"));
		pesquisar.setBounds(220,102,150,35);
		pesquisar.setToolTipText("Digite um nome para pesquisa");
		pesquisar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tabela.limparTabela2();
				tabela.filtroNome2(campo.getText());
				
			}
		});
		add(pesquisar);
	}
	public void campo() {
		campo =  new JTextField();
		campo.setBounds(10, 105, 200, 30);
		add(campo);
	}
	public void tabela(JFrame janela) {
		tabela = new TabelaImoveis();
		tabela.gerarTabela(janela,"");
		tabela.repaint();
		janela.add(tabela);
	}

	public static void main(String[] args) {
		new TelaImoveisVendido();
	}

}
