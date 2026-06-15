package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class Principal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabedMain = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabedMain, BorderLayout.CENTER);
		
		JPanel pAdministracion = new JPanel();
		tabedMain.addTab("Administracion", null, pAdministracion, null);
		
		JPanel pPrestamos = new JPanel();
		tabedMain.addTab("Prestamos", null, pPrestamos, null);
		
		JPanel pReportes = new JPanel();
		tabedMain.addTab("Reportes", null, pReportes, null);
	}
}
