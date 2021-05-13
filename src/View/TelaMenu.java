

package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.sun.xml.internal.ws.api.pipe.Fiber.Listener;

import Control.ContabilidadeControl;
import Control.ControlCentral;
import DAO.Sql.Bacukp;
import Model.contabilidade.ContabilidadeClienteDivida;

/**
 * @author luanp
 */

public class TelaMenu extends Principal {
	private JMenuItem item,item1,item2,item3,item4,item5,item6;
	JLabel data1=  new JLabel();

	private static final long serialVersionUID = 1L;
	public TelaMenu(String cargo) {
		setTitle("MENU PRINCIPAL");
	   setLayout(null);
	   setSize(1800,750);
	   setLocationRelativeTo(null);
	   setResizable(false);
	   adicionarlogo(this);
	   adicionarbutton(cargo);
	   bcl();
	   blackgroud();
	   setVisible(true);
	   repaint();
	   
	   

	}
	public void bcl() {
		JLabel barra = new JLabel();
		barra.setBounds(1,-20,1700,150);
		barra.setBackground(Color.GRAY);
		barra.setOpaque(isBackgroundSet());
		add(barra);
		
		
	}
	public class ouvinteLinter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		 String op = e.getActionCommand();
		 
		 if(op.equals("Fazer Backup")) {
			 Bacukp b =new Bacukp();
			String t= b.gerarBackup();
			JOptionPane.showMessageDialog(null, t,"backup",JOptionPane.INFORMATION_MESSAGE);
		 }
		 else if(op.equals("Cadastar tipo de Imovel")) {
			 new TelaCadastroTipoImovel();
			
		}
		 else if(op.equals("Cadastar Cliente")) {
			 new TelaCadastroCliente(true, null);
		}
		 
		 else if(op.equals("Cadastar Funcionario")) {
			 new TelaCadastroFuncionario(true, null);
		}
		 else if(op.equals("Cadastar Imovel")) {
			 new TelaCadastroImoveis(true, null);
		}
		 else if(op.equals("Consultar compra")) {
			 new ViewlPedido();
		}
		 else if(op.equals("Dividas de Clientes")) {
			ContabilidadeControl c = new ContabilidadeControl();
			c.clienteComDivida();
		}
		
	}
	}
	public void blackgroud() {
		JLabel contabil = new JLabel(new ImageIcon("icon/menu.jpg"));
		contabil.setBounds(1,1,1380,730);
		add(contabil);
		
	}
	public void adicionarbutton(String cargo) {
		OuvinteMenu ouvinte= new OuvinteMenu();
		
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		JMenu menu = new JMenu("Operacional");
		barraMenu.add(menu);
		
		JMenu cad = new JMenu("Cadastro");
		barraMenu.add(cad);
		
		JMenu rel = new JMenu("Relatorio");
		barraMenu.add(rel);
		
		item = new JMenuItem("Fazer Backup");
		menu.add(item);
		
		item5 = new JMenuItem("Consultar compra");
		menu.add(item5);
		
		 item1 = new JMenuItem("Cadastar tipo de Imovel");
		cad.add(item1);
		
		item2 = new JMenuItem("Cadastar Cliente");
		cad.add(item2);
		
		item3 = new JMenuItem("Cadastar Funcionario");
		cad.add(item3);
		
		item4 = new JMenuItem("Cadastar Imovel");
		cad.add(item4);
		
		item6 = new JMenuItem("Dividas de Clientes");
		rel.add(item6);
		
		
		ouvinteLinter ouvi = new ouvinteLinter();
		item.addActionListener(ouvi);
		item1.addActionListener(ouvi);
		item2.addActionListener(ouvi);
		item3.addActionListener(ouvi);
		item4.addActionListener(ouvi);
		item5.addActionListener(ouvi);
		item6.addActionListener(ouvi);
		
		BotoesGeral pedido = new BotoesGeral("PDV",new ImageIcon("Icon/PEDIDO.png"),2,20,100,100);
		pedido.repaint();
		add(pedido);
		pedido.addActionListener(ouvinte);
		pedido.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				pedido.setIcon(new ImageIcon("Icon/PEDIDO.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				pedido.setIcon(new ImageIcon("Icon/PEDIDO2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		BotoesGeral cliente = new BotoesGeral("<html>Clientes<html>",new ImageIcon("Icon/clientes.png"),101,20,100,100);
		cliente.repaint();
		add(cliente);
		cliente.addActionListener(ouvinte);
		cliente.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				cliente.setIcon(new ImageIcon("Icon/clientes.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				cliente.setIcon(new ImageIcon("Icon/clientes2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		BotoesGeral imovel = new BotoesGeral("<html>Imoveis<html>",new ImageIcon("Icon/imovel.png"), 202,20,100,100);
		imovel.repaint();
		add(imovel);
		imovel.addActionListener(ouvinte);
		imovel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				imovel.setIcon(new ImageIcon("Icon/imovel.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				imovel.setIcon(new ImageIcon("Icon/imovel2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JLabel barra = new JLabel();
		barra.setBounds(305,9,2,110);
		barra.setBackground(Color.LIGHT_GRAY);
		barra.setOpaque(isBackgroundSet());
		add(barra);
		
		JLabel barra2 = new JLabel();
		barra2.setBounds(620,9,2,110);
		barra2.setBackground(Color.LIGHT_GRAY);
		barra2.setOpaque(isBackgroundSet());
		add(barra2);
		
		BotoesGeral vendido = new BotoesGeral("<html>Vendido<html>",new ImageIcon("Icon/imovelVendido.png"),313,20,100,100);
		vendido.repaint();
		add(vendido);
		vendido.addActionListener(ouvinte);
		vendido.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				vendido.setIcon(new ImageIcon("Icon/imovelVendido.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				vendido.setIcon(new ImageIcon("Icon/imovelVendido2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		BotoesGeral relatorio = new BotoesGeral("<html>Relatorio<html>",new ImageIcon("Icon/relatorio.png"), 415,20,100,100);
		relatorio.repaint();
		add(relatorio);
		relatorio.addActionListener(ouvinte);
		relatorio.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				relatorio.setIcon(new ImageIcon("Icon/relatorio.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				relatorio.setIcon(new ImageIcon("Icon/relatorio2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		BotoesGeral func = new BotoesGeral("<html>Funcionarios<html>",new ImageIcon("Icon/func.png"),515,20,100,100);
		func.repaint();
		add(func);
		func.addActionListener(ouvinte);
			func.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				func.setIcon(new ImageIcon("Icon/func.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				func.setIcon(new ImageIcon("Icon/func2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		BotoesGeral cont = new BotoesGeral("<html>Recibo<html>",new ImageIcon("Icon/recibo.png"),625,20,100,100);
		add(cont);
		cont.addActionListener(ouvinte);
			cont.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				cont.setIcon(new ImageIcon("Icon/recibo.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				cont.setIcon(new ImageIcon("Icon/recibo2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		BotoesGeral config = new BotoesGeral("<html>Configuração<html>",new ImageIcon("Icon/confg.png"),708,20,110,100);
		//add(config);
		config.addActionListener(ouvinte);
		
		BotoesGeral sair = new BotoesGeral("<html>Sair<html>",new ImageIcon("Icon/sair.png"),730,20,100,100);
		add(sair);
		sair.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				sair.setIcon(new ImageIcon("Icon/sair.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				sair.setIcon(new ImageIcon("Icon/sair2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		sair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			dispose();
			new ViewLogin();
			}
			});
		
		
	    switch (cargo) {
	    
		case "Corretor":
			vendido.setEnabled(false);
			relatorio.setEnabled(false);
			imovel.setEnabled(false);
			func.setEnabled(false);
			cont.setEnabled(false);
			config.setEnabled(false);
			item1.setEnabled(false);
			item3.setEnabled(false);
			item4.setEnabled(false);
			item6.setEnabled(false);
			
			break;
			
		case "Secretaria":
			pedido.setEnabled(false);
			imovel.setEnabled(false);
			func.setEnabled(false);
			config.setEnabled(false);
			item1.setEnabled(false);
			item3.setEnabled(false);
			item4.setEnabled(false);
			item5.setEnabled(false);
			
			break;

		default:
			break;
		}
	}
	public void adicionarlogo(JFrame frame) {
		
		
		 Date d = new Date();
	         String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		
	    Timer timer = new Timer(1000, new hora());
	    timer.start();
	    
		JLabel data=  new JLabel("Data: "+hoje);
		data.setBounds(1100,580,400,100);
		data.setFont(new Font("Magneto",Font.BOLD,25));
		data.setForeground(Color.WHITE);
		frame.add(data);
		
		
		data1.setBounds(1100,615,400,100);
		data1.setFont(new Font("Magneto",Font.BOLD,25));
		data1.setForeground(Color.WHITE);
		frame.add(data1);
		
		
		JLabel user=  new JLabel("Usuario Logado: "+ControlCentral.getLogado().getNome());
		user.setBounds(10,580,400,100);
		user.setFont(new Font("Masque",Font.BOLD,20));
		user.setForeground(Color.WHITE);
		frame.add(user);
		
		JLabel user1=  new JLabel("Cargo: "+ControlCentral.getLogado().getCargo());
		user1.setBounds(10,610,400,100);
		user1.setFont(new Font("Magneto",Font.BOLD,20));
		user1.setForeground(Color.WHITE);
		frame.add(user1);
		
		
		
	}
	class hora implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			Calendar now = Calendar.getInstance();
			data1.setText(String.format("Hora: "+"%1$tH:%1$tM:%1$tS", now));
		}
	}
	public static void main(String[] args) {
		/*FuncionarioDTO gerente = new FuncionarioDTO();
		 gerente.setId(1);
		 gerente.setNome("lmb");
		 gerente.setCargo("Administrador");
		 gerente.setSenha("1");
		 FuncionarioControl.cadastrarFuncionario(gerente);*/
		new TelaMenu("Administrador");
	}
	
}
