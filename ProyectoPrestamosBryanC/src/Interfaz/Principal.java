package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Principal {

	private JFrame frame;
	private JButton crearI;

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
		frame.setBounds(100, 100, 790, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabedMain = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabedMain, BorderLayout.CENTER);
		
		JPanel pAdministracion = new JPanel();
		tabedMain.addTab("Administracion", null, pAdministracion, null);
		pAdministracion.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 11, 759, 345);
		pAdministracion.add(tabbedPane);
		
		JPanel pItem = new JPanel();
		tabbedPane.addTab("Item", null, pItem, null);
		tabbedPane.setEnabledAt(0, true);
		pItem.setLayout(null);
		
		crearI = new JButton("Crear");
		crearI.setBounds(10, 11, 89, 23);
		pItem.add(crearI);
		
		JButton BorrarI = new JButton("Borrar");
		BorrarI.setBounds(10, 45, 89, 23);
		pItem.add(BorrarI);
		
		JButton ModificarI = new JButton("Modificar");
		ModificarI.setBounds(10, 79, 89, 23);
		pItem.add(ModificarI);
		
		JButton ConsultarI = new JButton("Consulta");
		ConsultarI.setBounds(10, 118, 89, 23);
		pItem.add(ConsultarI);
		
		JPanel pPersona = new JPanel();
		tabbedPane.addTab("Persona", null, pPersona, null);
		
		JPanel pTipo = new JPanel();
		tabbedPane.addTab("Tipo", null, pTipo, null);
		
		JPanel pCategoria = new JPanel();
		tabbedPane.addTab("Categoria", null, pCategoria, null);
		
		JPanel pPrestamos = new JPanel();
		tabedMain.addTab("Prestamos", null, pPrestamos, null);
		
		JPanel pReportes = new JPanel();
		tabedMain.addTab("Reportes", null, pReportes, null);
		frame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frame.getContentPane(), tabedMain, pAdministracion, tabbedPane, pItem, crearI, BorrarI, ModificarI, ConsultarI, pPersona, pTipo, pCategoria, pPrestamos, pReportes}));
	}
}
