package View;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.log.SysoCounter;

import Control.ClienteControl;
import Control.CompraControl;
import Control.ControlCentral;
import Control.DividaControl;
import Control.FuncionarioControl;
import Control.ImovelControl;
import Control.TipoControl;
import DTO.ClienteDTO;
import DTO.CompraDTO;
import DTO.DividaDTO;
import DTO.FuncionarioDTO;
import DTO.ImovelDTO;
import DTO.TipoImovelDTO;
import Model.CalcularData;



public class VIewPDV2  extends Principal{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//## atributos
	private static JTextField cod;
	private JTextField desc;
	private JTextField precouni;
	private JTextField  subtotal;
	private ImovelDTO imovel = new ImovelDTO();
	private JTextField idf;
	private JComboBox<String> pagamento;
	private JComboBox<String>parcelado;
	private float total;
	private  int cont=0;
	private JLabel totalGeral;
	private static int codcli;

	
	 private DefaultTableModel modelo;
	 private JScrollPane contener;
	 private JTable tabela;
	
	

	
	
	public VIewPDV2() {
		setTitle("Tela de PDV");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 50, 1100, 650);
		campos();
		botao();
		label();
		barra();
		tabelaIgre();
		setVisible(true);
		repaint();
	}
	public void barra() {
		
		
		
		JLabel logo = new JLabel("",new ImageIcon("Icon/logo.png"),JLabel.LEFT);
		logo.setBounds(-10, 440, 250, 200);
		add(logo);
		
		totalGeral = new JLabel();
		totalGeral.setBounds(650, 380, 350, 90);
		totalGeral.setFont(new Font("Times New Roman",Font.BOLD, 45));
		totalGeral.setFont(new Font("Times New Roman",Font.ITALIC, 45));
		totalGeral.setForeground(Color.BLACK);
		add(totalGeral);
		
		JLabel barra5 = new JLabel("R$");
		barra5.setOpaque(true);
		barra5.setBackground(Color.GREEN);
		barra5.setBounds(580, 380, 350, 90);
		barra5.setFont(new Font("Times New Roman",Font.BOLD, 45));
		barra5.setFont(new Font("Times New Roman",Font.ITALIC, 45));
		barra5.setForeground(Color.BLACK);
		add(barra5);
		
		
		
		JLabel barra = new JLabel();
		barra.setOpaque(true);
		barra.setBackground(Color.GRAY);
		barra.setBounds(0, 5, 980, 90);
		add(barra);
	
		JLabel barra2 = new JLabel();
		barra2.setOpaque(true);
		barra2.setBackground(Color.DARK_GRAY);
		barra2.setBounds(0, 380
				, 980, 90);
		add(barra2);
		
		
		
		JLabel barra3 = new JLabel();
		barra3.setOpaque(true);
		barra3.setBackground(Color.WHITE);
		barra3.setBounds(0, 450, 250, 200);
		add(barra3);
		
		JLabel barra6 = new JLabel();
		barra6.setOpaque(true);
		barra6.setBackground(new Color(65,129,68));
		barra6.setBounds(470, 490, 5, 128);
		add(barra6);
		
		
		
		
		JLabel barraBotao = new JLabel();
		barraBotao .setOpaque(true);
		barraBotao .setBackground(new Color(143,38,29));
		barraBotao .setBounds(980, 5, 120, 650);
		add(barraBotao);
		
		JLabel barra4 = new JLabel();
		barra4.setOpaque(true);
		barra4.setBackground(new Color(43,107,62));
		barra4.setBounds(250, 485, 1100, 200);
		add(barra4);
		
	}
	public void campos() {
		
		cod =new JTextField();
		cod.setBounds(20,35,80,35);
		add(cod);
		cod.requestFocus();
		cod.setEditable(false);
		cod.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				
			}
			
			public void keyReleased(KeyEvent e) {
				proximo(e);
				
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				addImovel(e);
				
			}
		});
		String [] lista={"Especie","Parcelado"};
		 
		pagamento = new JComboBox<>(lista);
		pagamento.setBounds(130,35,100,35);
		add(pagamento);
		pagamento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String opcao = (String) pagamento.getSelectedItem();
				
				if(opcao.equals("Parcelado")) {
					parcelado.setEnabled(true);
				}
				else {
					parcelado.setEnabled(false);
				}
				
			}
		});
		
		String [] vezes={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
		parcelado = new JComboBox<>(vezes);
		parcelado.setBounds(260, 35, 45, 35);
		add(parcelado);
		parcelado.setEnabled(false);
		
				   
		
		
		    MaskFormatter p;
			try {
				p = new MaskFormatter("****");
				p.setValidCharacters("0123456789,.");
				desc = new JFormattedTextField(p);
				desc.setBounds(330, 35, 120, 35);
				add(desc);
				desc.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					public void keyReleased(KeyEvent e) {
						proximo(e);
						addImovel(e);
						
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			} catch (ParseException e) {
			}
			
		
		
			precouni = new JTextField();
			precouni.setBounds(490, 35, 120, 35);
			add(precouni);
			precouni.setEnabled(false);;
			precouni.repaint();
			precouni.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				public void keyReleased(KeyEvent e) {
					addImovel(e);
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
				
				
			
			subtotal = new JTextField();
			subtotal.setBounds(500, 35, 80, 40);
			add(subtotal);
			subtotal.setVisible(false);
			subtotal.repaint();
			
			
		  
}
	public static void receb(int c) {
		cod.setText(""+c);
	}
	
	public static void recCliente(int c) {
		codcli=c;
	}
public void botao() {
	
		
		BotoesGeral botcad = new  BotoesGeral("<HTML>Finalizar<HTML>",new ImageIcon("Icon/salvarped.png"),985,410, 100, 100);
	
		botcad.setToolTipText("Finalizar Pedido");
		botcad.addActionListener(new Ouvir());
		botcad.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				botcad.setIcon(new ImageIcon("Icon/salvarped.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				botcad.setIcon(new ImageIcon("Icon/salvarped2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		BotoesGeral botpesquisa = new  BotoesGeral("<HTML>Cancelar<HTML>",new ImageIcon("Icon/canped.png"),985,280,100, 100);
		botpesquisa.addActionListener(new Ouvir());
		botpesquisa.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				botpesquisa.setIcon(new ImageIcon("Icon/canped.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				botpesquisa.setIcon(new ImageIcon("Icon/canped2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		BotoesGeral pes = new  BotoesGeral("Pesquisar",new ImageIcon("Icon/pesquisaped.png"),985,150,100, 100);
		pes.addActionListener(new Ouvir());
		pes.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				pes.setIcon(new ImageIcon("Icon/pesquisaped.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				pes.setIcon(new ImageIcon("Icon/pesquisaped2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		BotoesGeral cli= new  BotoesGeral("<html>Lista de Cliente<html>",new ImageIcon("Icon/cadcli.png"),985,20,100, 100);
		cli.addActionListener(new Ouvir());
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
		add(pes);
		add(botpesquisa);
		add(botcad);
		
		
	//botcad.addActionListener(new ActionListener() {
	}
	public void label() {
		
		
		add(new FontesGeral("Cod",20,-90,70,30));
		add(new FontesGeral("Pagamento",132,-90,100,30));
		add(new FontesGeral("Desconto",330,-90,100,30));
		add(new FontesGeral("Preço",490,-90,50,30));
		add(new FontesGeral("Parcelas",255,-90,100,30));

		
	    JLabel total = new JLabel("Total----------------------------------->");
	    total.setBounds(10,380,900,100);
	    total.setFont(new Font("Times New Roman",Font.BOLD, 40));
	    total.setFont(new Font("Times New Roman",Font.ITALIC, 40));
	    total.setForeground(Color.WHITE);
	    add(total);
	    
	    JLabel caixa = new JLabel("Atendimento Nº 1");
	    caixa.setBounds(255,455,300,100);
	    caixa.setFont(new Font("Times New Roman",Font.BOLD, 30));
	    caixa.setFont(new Font("Times New Roman",Font.ITALIC, 30));
	    caixa.setForeground(Color.WHITE);
	    add(caixa);
	    
	    JLabel at = new JLabel("Atendente: "+ControlCentral.getLogado().getNome());
	    at.setBounds(255,520,300,30);
	    at.setFont(new Font("Times New Roman",Font.BOLD, 20));
	    at.setFont(new Font("Times New Roman",Font.ITALIC, 20));
	    at.setForeground(Color.WHITE);
	    add(at);
	    
	    JLabel at3 = new JLabel("Lista de Comandos:");
	    at3.setBounds(550,480,300,50);
	    at3.setFont(new Font("Times New Roman",Font.BOLD, 32));
	    at3.setFont(new Font("Times New Roman",Font.ITALIC, 32));
	    at3.setForeground(Color.BLACK);
	    add(at3);
	    
	    JLabel at4 = new JLabel("1-Pesquisar Item--> Tecle F1");
	    at4.setBounds(500,510,350,50);
	    at4.setFont(new Font("Times New Roman",Font.BOLD, 25));
	    at4.setFont(new Font("Times New Roman",Font.ITALIC, 25));
	    at4.setForeground(Color.BLACK);
	    add(at4);
	    
	    JLabel at5 = new JLabel("2-Adicionar Item--> Tecle Enter");
	    at5.setBounds(500,540,350,50);
	    at5.setFont(new Font("Times New Roman",Font.BOLD, 25));
	    at5.setFont(new Font("Times New Roman",Font.ITALIC, 25));
	    at5.setForeground(Color.BLACK);
	    add(at5);
	    
	    JLabel at6 = new JLabel("3-Deletar Item--> Tecle Del");
	    at6.setBounds(500,570,300,50);
	    at6.setFont(new Font("Times New Roman",Font.BOLD, 25));
	    at6.setFont(new Font("Times New Roman",Font.ITALIC, 25));
	    at6.setForeground(Color.BLACK);
	    add(at6);
	    
	    JLabel ped0 = new JLabel("");
	    ped0.setBounds(710,470,400,50);
	    ped0.setFont(new Font("Times New Roman",Font.BOLD, 25));
	    ped0.setFont(new Font("Times New Roman",Font.ITALIC, 25));
	    ped0.setForeground(Color.BLACK);
	    
	    JLabel ped = new JLabel("");
	    ped.setBounds(710,505,400,50);
	    ped.setFont(new Font("Times New Roman",Font.BOLD, 25));
	    ped.setFont(new Font("Times New Roman",Font.ITALIC, 25));
	    ped.setForeground(Color.BLACK);
	    
	    JLabel ped1 = new JLabel("");
	    ped1.setBounds(710,535,400,50);
	    ped1.setFont(new Font("Times New Roman",Font.BOLD, 18));
	    ped1.setFont(new Font("Times New Roman",Font.ITALIC, 18));
	    ped1.setForeground(Color.BLACK);
	    
	    JLabel ped2 = new JLabel("");
	    ped2.setBounds(710,565,400,50);
	    ped2.setFont(new Font("Times New Roman",Font.BOLD, 20));
	    ped2.setFont(new Font("Times New Roman",Font.ITALIC, 20));
	    ped2.setForeground(Color.BLACK);
	    
	    add(ped0);
	    add(ped);
	    add(ped1);
	    add(ped2);
	    

	
}
	
public void proximo(java.awt.event.KeyEvent even) {
	   
		
		if(even.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER || even.getKeyCode() == java.awt.event.KeyEvent.VK_TAB) {
			//ImovelDTO imovel =  new ImovelDTO();
			imovel.setId(Integer.parseInt(cod.getText()));
			imovel=ImovelControl.pesquisarImovel(imovel);
			precouni.setText(""+imovel.getPreco());
			
			if(cont==0) {
				desc.requestFocus();
				cont=2;
			    
			}
			else if(cont==2) {
				if(!desc.getText().equals("0.00")) {
					 float t=Float.parseFloat(precouni.getText());
					  float t1=Float.parseFloat(desc.getText());
					  t=t-t1;
					  precouni.setText(String.valueOf(t));
				}
				precouni.requestFocus();
			    subtotal.setText(precouni.getText());
			    cont=0; 
			
			}
		}
		}
	public void addImovel(java.awt.event.KeyEvent even) {
		
		if(even.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
			try {
					//ImovelDTO imovel =  new ImovelDTO();
					
					/*imovel.setId(Integer.parseInt(cod.getText()));
					imovel=ImovelControl.pesquisarImovel(imovel);*/
					imovel.setPreco(Float.parseFloat(precouni.getText()));
					TipoImovelDTO tipo = new TipoImovelDTO();
					tipo=TipoControl.pesquisarInt(imovel.getTipo().getId());
					imovel.setTipo(tipo);
					addlinha(imovel);
					total=Float.parseFloat(subtotal.getText());
					totalGeral.setText(String.valueOf(total));
							
							//limpar campo
							cod.setText(null);
							desc.setText("");
							precouni.setText(null);
							subtotal.setText(null);
							cod.setEnabled(false);
			} catch (NumberFormatException e) {
			}
					
					
		}
		else if(even.getKeyCode() == java.awt.event.KeyEvent.VK_F1) {
		    new ViewEstoque();
	}
		else if(even.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
				   int linha = tabela.getSelectedRow();
				   totalGeral.setText("");
				   modelo.removeRow(linha);
				   cod.setEnabled(true);
				
		        
	}	
		
		
	}
	public void tabelaIgre() {



			modelo = new DefaultTableModel();
		    modelo.addColumn("Cod");
		    modelo.addColumn("Imovel");
		    modelo.addColumn("Descrição");
		    modelo.addColumn("Endereço");
		    modelo.addColumn("Preço");
			
			
			tabela = new  JTable(modelo);
			tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(350);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(300);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(137);
				
			contener = new JScrollPane(tabela);
			
			
			
			
		
			contener.setBounds(0,95,980,275);
			add(contener);
			tabela.repaint();
			
			tabela.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				public void keyReleased(KeyEvent e) {
					
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					addImovel(e);
					
				}
			});
			}
	public void limparTabela() {
		while(tabela.getModel().getRowCount()>0) {
					modelo.removeRow(0);
				
		}
		}
	int item =0;
	public void addlinha(ImovelDTO e) {
		NumberFormat moeda = NumberFormat.getCurrencyInstance();
		item++;
		modelo.addRow(new Object[] {item,
				                    e.getTipo().getNome(),
                                    e.getEstado(),
                                    e.getEnderco(),
                                    moeda.format(e.getPreco()),
												});
		
	}
	
	private class Ouvir implements ActionListener{		
		
		public void actionPerformed(ActionEvent event) {
			String nome = event.getActionCommand();
		 
			//ControlClientes cliente = new ControlClientes();
			
			switch (nome) {	
		
			
			
			case"<HTML>Finalizar<HTML>":
				//serador de codigo de pedido
				//int cod1=;
				
				if(codcli>0) {
			try {
				 Date d = new Date();
		         String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		        FuncionarioControl fc = new FuncionarioControl();
				ClienteDTO p=ClienteControl.pesquisarCliente((codcli));
				CompraDTO novo = new CompraDTO ();
				novo.setCliente(p);
				imovel.setStatus("Vendido");
				ImovelControl.editar(imovel);
				novo.setIdImovel(imovel);
				novo.setDataCompra(hoje);
				String pag =(String) pagamento.getSelectedItem();
				int parc = Integer.parseInt((String) parcelado.getSelectedItem());
				novo.setPagamento(pag+"-"+parc);
				novo.setPorcentagemCompra(""+imovel.getTipo().getPocetagem());
				novo.setIdFuncionario(ControlCentral.getLogado());
				if(pag.equals("Parcelado")) {
					int data =0;
					double valor = imovel.getPreco();
					valor=valor/parc;
					for(int cont=0;cont<parc;cont++) {
						CalcularData calc= new CalcularData();
						String dataFinal=calc.calcularSoma(data);
					    data+=30;
						DividaDTO divida = new DividaDTO();
						divida.setCliente(p);
						divida.setData(dataFinal);
						divida.setPreco(valor);
						DividaControl.cadastrar(divida);
					}
					
				}
				JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);   
					new contrato(p, imovel);
					new GeradorBoleto().gerarBoleto(novo);
					CompraControl.cadastrarCompra(novo);
					dispose();
					new VIewPDV2();
				} catch (DocumentException | IOException e) {
					JOptionPane.showMessageDialog(null, "DOCUMENTO ABERTO FECHE PRA CONTINUAR","ERRO",JOptionPane.ERROR_MESSAGE);
				}
				 
				catch (NumberFormatException | NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Cliente nao cadastardo !","Aviso", JOptionPane.ERROR_MESSAGE);
					  
				}
				}
				else
					JOptionPane.showMessageDialog(null, "Escolha o Cliente pra gerar o pedido !","Aviso", JOptionPane.ERROR_MESSAGE);
			
			
				break;
				
			case"Pesquisar":
				new ViewlPedido();
				
				break;
			    

				
			case"<HTML>Cancelar<HTML>":
				dispose();
				break;
				
			case"<html>Lista de Cliente<html>":
				new ViewlClientes("");
				break;	
				
			}
		}
		
	
	}
	public static void main(String[] args) {
		new VIewPDV2();
	}
}
