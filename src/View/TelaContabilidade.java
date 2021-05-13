package View;

/**
 * 
 * @author André
 * 
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

import Control.ClienteControl;
import Control.CompraControl;
import Control.ContabilidadeControl;
import DTO.ClienteDTO;
import DTO.CompraDTO;

public class TelaContabilidade extends JFrame{
	private TabelaCompra tabela;
	
	public TelaContabilidade() {
		setTitle("Tela Contabilidade");
		setSize(1400,750);
		getContentPane().setBackground(new Color(240, 240, 220));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tabela(this);
		botoes();
		labels();
		setVisible(true);
		repaint();
	}
	public void labels() {
		JLabel relatorios = new JLabel("RELATÓRIOS");
		relatorios.setBounds(20,10,200,30);
		relatorios.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,30));	
		add(relatorios);
		
		JLabel casa = new JLabel(new ImageIcon("Icon/casa.png"));
		casa.setBounds(210,420,600,500);
		add(casa);
		
		JLabel borda = new JLabel();
		borda.setBounds(0,0,230, 750);
		borda.setBackground(new Color(220,220,200));
		borda.setOpaque(true);
		add(borda);
		
		JLabel logo = new JLabel(new ImageIcon("Icon/logo.png"));
        logo.setBounds(960, 450, 250, 200);
        add(logo);
		
		JLabel n = new JLabel();
		add(n);
	}
	public void botoes() {
		ContabilidadeControl cont = new ContabilidadeControl();
		JButton excluir = new JButton("Excluir");
		excluir.setBounds(500,20,80,30);
		excluir.setToolTipText("Excluir dados de compra");
		excluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int index = tabela.selecionarLinha();
				if(index>=0) {
					int id = tabela.retornarId(index);
					CompraDTO cdto = new CompraDTO();
					cdto.setId(id);
					cdto = 	CompraControl.pesquisarCompra(cdto);
					CompraControl.excluirCompra(cdto);
			}
				else {
					JOptionPane.showMessageDialog(null,"Selecione uma linha da tabela","Atenção", 2);
				}
			}
			
		});
		add(excluir);
		
		JButton bairro = new JButton("Bairro mais vendido");
		bairro.setBounds(6, 50, 215, 30);
		bairro.setToolTipText("Relatório dos bairros mais vendidos.");
		bairro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String data = JOptionPane.showInputDialog(null,"Digite o mês e o ano (##/####)");
				cont.bairrosMaisVendidos(data);
			}
			
		});
		add(bairro);
		
		JButton funcionario = new JButton("Funcionário do mês");
		funcionario.setBounds(6, 90, 215, 30);
		funcionario.setToolTipText("Funcionário com mais vendas no mês");
		funcionario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String data = JOptionPane.showInputDialog(null,"Digite o mês e o ano (##/####)");
				cont.funcionarioDoMes(data);
			}
			
		});
		add(funcionario);
		JButton despesas = new JButton("Despesas dos funcionários");
		despesas.setBounds(6,130,215,30);
		despesas.setToolTipText("Soma dos salários dos funcionários");
		despesas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont.despesasFuncionarios();
			}
			
		});
		add(despesas);
		
		JButton lucro = new JButton("Lucro da empresa no mês");
		lucro.setBounds(6,170,215,30);
		lucro.setToolTipText("A soma de todas as vendas, vezes a porcentagem de lucro e menos as despesas dos funcionários");
		lucro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String data = JOptionPane.showInputDialog(null,"Digite o mês e o ano (##/####)");
				cont.lucroDoMes(data);
		
			}
			
		});
		add(lucro);
		
		JButton divida = new JButton("Clientes com dividas atrasadas");
		divida.setBounds(6,210,215,30);
		divida.setToolTipText("Monstra os clientes que estão com dividas atrasadas");
		divida.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont.clienteComDivida();
			}
			
		});
		add(divida);
		
		JButton porcentagem = new JButton("Venda com maior porcentagem");
		porcentagem.setBounds(6,250,215,30);
		porcentagem.setToolTipText("Mostra o imovel com maior margem de lucro");
		porcentagem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String data = JOptionPane.showInputDialog(null,"Digite o mês e o ano (##/####)");
				cont.vendaComMaiorPorcentagem(data);
			}
			
		});
		add(porcentagem);
		
		JButton imoveis = new JButton("Imoveis vendidos no mês");
		imoveis.setBounds(6,290,215,30);
		imoveis.setToolTipText("Lista todos os imoveis vendidos no mês");
		imoveis.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String data = JOptionPane.showInputDialog(null,"Digite o mês e o ano (##/####)");	
				cont.imoveisVendidos(data);
			}
			
		});
		add(imoveis);
		
		JButton compras = new JButton("Compras de cada cliente");
		compras.setBounds(6,330,215,30);
		compras.setToolTipText("Lista todas as compras de cada cliente cadastrado");
		compras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClienteControl c = new ClienteControl();
				String[] opcoes = new String[c.listaDeClientesDTO().getArrayDTO().size()];
				int n = 0;
				for(ClienteDTO a: c.listaDeClientesDTO().getArrayDTO()) {
					opcoes[n] = a.getCpf();
					n++;
				}
				String cpf = (String) JOptionPane.showInputDialog(null, "Escolha um item" , "Selecao de itens" ,JOptionPane.PLAIN_MESSAGE , null ,opcoes,"");	
				cont.compraCliente(cpf);
			}
			
		});
		add(compras);
		
		JButton cartaCobranca = new JButton("Carta de Cobrança");
		cartaCobranca.setBounds(240,30,215,30);
		cartaCobranca.setToolTipText("Cria uma carta de cobrança");
		cartaCobranca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont.cartaCobraca();
			}
			
		});
		add(cartaCobranca);
		
		JButton cartaDemissao = new JButton("Carta de Demissão");
		cartaDemissao.setBounds(240,70,215,30);
		cartaDemissao.setToolTipText("Cria uma carta de demissão");
		cartaDemissao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont.cartaDemissao();
			}
			
		});
		add(cartaDemissao);
	
	}
	
	public void tabela(JFrame janela) {
		tabela = new TabelaCompra();
		tabela.gerarTabela(janela);
		add(tabela);
		tabela.repaint();
	}
	
public static void main(String[] args) {
	new TelaContabilidade();
}
}
