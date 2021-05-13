package View;

public class IniciarPrograma {

		public static void main(String[] args) {
			//Tema Nimbus achei no Google
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
						new TelaContabilidade
						();
				}
				
			});
		}
}

		

	
