package Interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controladora.Controlador;
import Logica.Item;

import java.util.List;
import javax.swing.JScrollPane;

public class Principal {
	private JFrame frame;
	private JButton crearI;
	private JTable tablaItems;
	private JScrollPane scrollPaneItems;

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

	public Principal() {
		initialize();
	}

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
		crearI.addActionListener(e -> crearItem());
		pItem.add(crearI);

		JButton borrarI = new JButton("Borrar");
		borrarI.setBounds(10, 45, 89, 23);
		pItem.add(borrarI);

		JButton modificarI = new JButton("Modificar");
		modificarI.setBounds(10, 79, 89, 23);
		pItem.add(modificarI);

		JButton consultarI = new JButton("Consulta");
		consultarI.setBounds(10, 118, 89, 23);
		pItem.add(consultarI);
		
		scrollPaneItems = new JScrollPane();
		scrollPaneItems.setBounds(143, 34, 584, 267);
		pItem.add(scrollPaneItems);
		
		tablaItems = new JTable();
		scrollPaneItems.setViewportView(tablaItems);
		tablaItems.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Descripcion", "Codigo", "Tipo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaItems.getColumnModel().getColumn(0).setPreferredWidth(168);
		tablaItems.getColumnModel().getColumn(1).setPreferredWidth(167);
		tablaItems.getColumnModel().getColumn(2).setPreferredWidth(148);
		tablaItems.getColumnModel().getColumn(3).setPreferredWidth(139);

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
	}

	private void crearItem() {
		CrearItem dialog = new CrearItem(frame);
		dialog.setVisible(true);
	}
	private void cargarItems() {
	    Controlador ctrl = Controlador.getInstancia();
	    DefaultTableModel model = (DefaultTableModel) tablaItems.getModel();
	    model.setRowCount(0);

	    for (Item item : ctrl.listarItems().values()) {
	        model.addRow(new Object[] {
	            item.getNombre(),
	            item.getDescripcion(),
	            item.getCodigo(),
	            item.getTipo().getDescripcion()
	        });
	    }
	}
}
