


package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.ldap.ControlFactory;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;

import Control.FuncionarioControl;
import DAO.Sql.TestConexao;
import DTO.FuncionarioDTO;




public class TelaSplesh extends JWindow {
	private static String porc="10%";
	

	    /**
	 * @autor luan miranda
	 */
	private static final long serialVersionUID = 1L;
		private int duration;

	    public TelaSplesh(int d) {
	        duration = d;
	    }

	// Este é um método simples para mostrar uma tela de apresentção

	// no centro da tela durante a quantidade de tempo passada no construtor

	    public void showSplash() {        
	        JPanel content = (JPanel)getContentPane();
	        content.setBackground(Color.darkGray);

	        // Configura a posição e o tamanho da janela
	        int width = 600;
	        int height =420;
	        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	        int x = (screen.width-width)/2;
	        int y = (screen.height-height)/2;
	        setBounds(x,y,width,height);

	        // Constrói o splash screen
	        JLabel label = new JLabel(new ImageIcon("icon/load.gif"));
	        JLabel logo = new JLabel(new ImageIcon("Icon/logo.png"));
	        logo.setBounds(300, 150, 150, 150);
	        label.setBounds(2,150,150, 150);
	        JLabel load = new JLabel
	                (porc+"Carregando...");
	        load.setFont(new Font("Sans-Serif", Font.BOLD, 40));
	        load.setBounds(0,-50, 100, 250);
	        
	       content.add(label,BorderLayout.CENTER);
	        content.add(logo);
	       // content.add(load);
	   
	        Color oraRed = new Color(20, 128, 128,  20);
	        content.setBorder(BorderFactory.createLineBorder(oraRed, 10));        
	        // Torna visível
	        setVisible(true);

	        // Espera ate que os recursos estejam carregados
	        
	        try { Thread.sleep(duration); } catch (Exception e) {}        
	        setVisible(false);
	    }

	    public void showTela() {  
	        showSplash();
	        
	    }

	    public static void main(String[] args) throws SQLException, ClassNotFoundException {        
	        // Mostra uma imagem com o título da aplicação 
	    	
	     
	    	TelaSplesh splash = new TelaSplesh(3000);
	        splash.showTela();
	        
	        try {
				for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						javax.swing.UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
			} catch (ClassNotFoundException ex) {
				java.util.logging.Logger.getLogger(TelaContabilidade.class.getName()).log(java.util.logging.Level.SEVERE, null,
						ex);
			} catch (InstantiationException ex) {
				java.util.logging.Logger.getLogger(TelaContabilidade.class.getName()).log(java.util.logging.Level.SEVERE, null,
						ex);
			} catch (IllegalAccessException ex) {
				java.util.logging.Logger.getLogger(TelaContabilidade.class.getName()).log(java.util.logging.Level.SEVERE, null,
						ex);
			} catch (javax.swing.UnsupportedLookAndFeelException ex) {
				java.util.logging.Logger.getLogger(TelaContabilidade.class.getName()).log(java.util.logging.Level.SEVERE, null,
						ex);
			}
			
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
				
					
					if(FuncionarioControl.listaDeFuncionarioDTO().getLista().size()==0) {
						FuncionarioDTO c = new FuncionarioDTO();
			        	new TelaCadastroFuncionario(true,c);
			        	System.out.println("iniciazr");
			        }
			        else	
			        new ViewLogin();
				}
			});
	    }
}
