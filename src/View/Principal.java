package View;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Principal extends JFrame{
	
	
	public Principal(){
	    setTitle("principal");
	 	setLayout(null);
	 	
		setSize(getMaximumSize());
		getContentPane().setBackground(new Color(190, 190, 190));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
		ImageIcon imagemTituloJanela = new ImageIcon("Icon/icon.png");
		setIconImage(imagemTituloJanela.getImage());
		setVisible(true);
//		JLabel contabil  = new JLabel(new ImageIcon("Icon/fundo.png"));
//		contabil.setBounds(1,1,1360,750);
//		add(contabil);
		setVisible(false);
		repaint();
		
			
	}
	  
	
}
