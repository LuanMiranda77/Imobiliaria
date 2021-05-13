package View;

/**
 * @author Agemiro
 */

import java.awt.Color;

import javax.swing.JTextField;

public class TextFieldPadrao extends JTextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TextFieldPadrao(int x, int y, int w, int z) {
		
		OuvinteExternoDeFoco ouvinteDeFoco = new OuvinteExternoDeFoco();
		setBounds(x, y, w, z);
		setForeground(Color.BLACK);
		addFocusListener(ouvinteDeFoco);
		
	}

}
