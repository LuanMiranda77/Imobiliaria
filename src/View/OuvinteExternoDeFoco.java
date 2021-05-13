package View;

/**
 * @author Agemiro
 */

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class OuvinteExternoDeFoco implements FocusListener{
	
	public void focusGained(FocusEvent arg0) {
	}

	public void focusLost(FocusEvent event) {
		
		JTextField tf = (JTextField) event.getSource();
		
		if (tf.getText().equals("") || tf.getText().equals("   .   .   -  ") || tf.getText().equals("(  )       -    "))
			tf.setBackground(Color.RED);
		else
			tf.setBackground(Color.WHITE);
		
		tf.repaint();
		
	}
	
}
