package View;

/**
 * @author Luan Miranda;
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.itextpdf.text.DocumentException;

import Control.ClienteControl;
import Control.DividaControl;
import DTO.ClienteDTO;
import DTO.DividaDTO;
import View.iterator.IIteratorCliente;
import View.iterator.IIteratorDivida;
import View.iterator.IteratorClienteDTO;
import View.iterator.IteratorDividaDTO;

public class ReciboView extends JFrame{
	private static DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField campoCliente;
	private JTextArea campoReferente;
	private JTextField campoValor;
	private JButton botaoCadastrar;
	private JButton botaoVoltar;
	private double preco;
	private static ClienteDTO cliente;
	private static double total;
	private static JLabel divida;
	private static NumberFormat moeda = NumberFormat.getCurrencyInstance();
	private static String g;
	

	
	public ReciboView() {
		
		setTitle("Gerador de Recibo");
		setSize(700, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		getContentPane().setBackground(new Color(240, 240, 220));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tabela(this);
		labels();
		fields();
		botoes();
		
		
		this.setVisible(true);
		
	}

	public void labels() {
		//titulo da tela
		JLabel titulo = new LabelPadrao("GERADOR DE RECIBO", 190, -5,400, 50);
        titulo.setFont(new Font("Masque",Font.BOLD,25));
        titulo.setForeground(Color.darkGray);
		add(titulo);
		
		JLabel barra = new JLabel();
		barra.setBounds(1,-20,1000,60);
		barra.setBackground(Color.GRAY);
		barra.setOpaque(isBackgroundSet());
		add(barra);
		
		JLabel tipoPessoa = new LabelPadrao("Cliente", 10, 40, 80, 30);
		add(tipoPessoa);
		
		JLabel nome = new LabelPadrao("Valor", 10, 100, 50, 30);
		add(nome);
		
		JLabel referencia = new LabelPadrao("Referente", 10, 130, 250, 100);
		add(referencia);
		
		divida = new LabelPadrao("Total de Divida R$"+total, 450, 280, 250, 100);
		add(divida);
	
		
		JLabel imagem = new JLabel(new ImageIcon("icon/casa.png"));
		imagem.setBounds(150, 320, 512, 512);
		add(imagem);
		
		
	}

	public void fields() {
		
		campoCliente = new TextFieldPadrao(10, 70, 250, 35);
		add(campoCliente);
		campoCliente.setEnabled(false);
		
		
	
		MaskFormatter valor;
		
			valor = new MaskFormatter();
			valor.setValidCharacters("0123456789");
	        campoValor=new JFormattedTextField();
    	campoValor.setBounds(10, 130, 150, 35);
		add(campoValor);
		campoValor.setEnabled(false);
		campoReferente = new JTextArea();
		campoReferente.setBounds(10, 200, 250, 100);
		add(campoReferente);
		
		
		
	}
	public static ClienteDTO cliente(int id) {
		cliente = new ClienteDTO();
		cliente = ClienteControl.pesquisarCliente(id);
		campoCliente.setText(cliente.getNome());
		adLinha(cliente);
		
		return cliente;
		
	}
	
	private void botoes() {
		
		
		BotoesGeral cli= new  BotoesGeral("<html>Lista de Cliente<html>",new ImageIcon("Icon/cadcli.png"),450,50,120, 120);
		cli.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewlClientes("recibo");
				total=0.00;
				divida.setText("Total de Divida R$"+total);
				limparTabela();
			}
		});
		cli.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				cli.setIcon(new ImageIcon("Icon/cadcli.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				cli.setIcon(new ImageIcon("Icon/cadcli2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		add(cli);
		

		botaoCadastrar = new JButton("RECEBER", new ImageIcon("icon/ok.png"));
		botaoCadastrar.setBounds(450, 200, 150, 35);
		botaoCadastrar.setToolTipText("Clique para gerar o recibo");
		add(botaoCadastrar);
       //ouvinte ocuto
		botaoCadastrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id =(int) tabela.getValueAt(tabela.getSelectedRow(),0);
				campoValor.setText(""+tabela.getValueAt(tabela.getSelectedRow(),4));
				DividaDTO d=DividaControl.pesquisar(id);
				preco=d.getPreco();
				campoReferente.setText("REFERENTE AO PAGAMENTO DA PARCELA DE Nº "+id);
				
				if(campoCliente.getText().equals("")||campoValor.getText().equals("")||campoReferente.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Campo em branco");
				}
				else if(campoValor.getText().contains("0") || campoValor.getText().contains("1") || campoValor.getText().contains("2")
						 || campoValor.getText().contains("3") || campoValor.getText().contains("4") || campoValor.getText().contains("5")
						 || campoValor.getText().contains("6")|| campoValor.getText().contains("7")|| campoValor.getText().contains("8")|| campoValor.getText().contains("9")) {
					
					try {
						modelo.removeRow(tabela.getSelectedRow());
						DividaControl.excluir(id);
						JOptionPane.showMessageDialog(null, "Parcela paga com sucesso!");
						new GeradorRecibo(cliente,preco,campoReferente.getText());
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Só digitar numero");
					campoValor.setRequestFocusEnabled(true);
					campoValor.setBackground(Color.ORANGE);
				}
				//new Recibo();
				
			}
		});

		botaoVoltar = new JButton("VOLTAR", new ImageIcon("icon/voltar.png"));
		botaoVoltar.setBounds(450, 250, 150, 35);
		botaoVoltar.setToolTipText("Clique para voltar");
			
		botaoVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(botaoVoltar);

	}
	public void tabela(JFrame janela) {

			
			
		    modelo = new DefaultTableModel();
		    modelo.addColumn("Cod");
		    modelo.addColumn("Cliente");
			modelo.addColumn("Endereço");
			modelo.addColumn("Telefone");
			modelo.addColumn("Divida");
			modelo.addColumn("Data");
			
			tabela = new  JTable(modelo);
			tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(160);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
		    tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
		  
			
			contener = new JScrollPane(tabela);
			
			/*
			 * Iterator aplicado
			 */
			contener.setBounds(20, 350,650,200);
			janela.add(contener);
			tabela.repaint();
		

	}
	public static void adLinha(ClienteDTO dto) {
		boolean band = false;
		IIteratorDivida iterator = new IteratorDividaDTO(DividaControl.lista().getLista());
		
		while (iterator.hasNext()) {
			
			DividaDTO d = (DividaDTO) iterator.next();
			
			if(d.getCliente().getCpf().equals(cliente.getCpf())) {
				band = true;
				 g =moeda.format(total+=d.getPreco());
				divida.setText("Total de Divida R$"+g);
				modelo.addRow(new Object[] {d.getId(),
										cliente.getNome(),
										cliente.getEndereco(),					                 
										cliente.getTelefone(),
										moeda.format(d.getPreco()),
										d.getData()
				
                    });
			}
		}
		if(band==false) {
			JOptionPane.showMessageDialog(null, "Clinete nao tem divida");
		}
		
	}
	public void limparTabela() {
		while(tabela.getModel().getRowCount()>0) {
			modelo.removeRow(0);
		}
	}
	public static void main(String[] args) {
		new ReciboView();
	}

}
