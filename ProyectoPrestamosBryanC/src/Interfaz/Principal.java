package Interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controladora.Controlador;
import Logica.Item;
import Logica.Tipo;
import Logica.Usuario;
import Logica.Prestamo;

import java.util.List;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {
	private JFrame frame;
	private JButton crearI;
	private JTable tablaItems;
	private JScrollPane scrollPaneItems;
	private JTable tablaTipos;
	private JButton crearT;
	private JButton borrarT;
	private JButton modificarT;
	private JButton consultT;
	private JScrollPane scrollPane;
	private JButton crearC;
	private JButton borrarC;
	private JButton modiC;
	private JTable tablaCategorias;
	private JScrollPane scrollPane_1;
	private JButton crearP;
	private JTable table;
	private JScrollPane scrollPane_2;
	private JButton consultP;
	private JButton modiP;
	private JButton borrarP;

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
		crearI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearItem();
			}
		});
		pItem.add(crearI);

		JButton borrarI = new JButton("Borrar");
		borrarI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarItem();
			}
		});
		borrarI.setBounds(10, 45, 89, 23);
		pItem.add(borrarI);

		JButton modificarI = new JButton("Modificar");
		modificarI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarItem();
			}
		});
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
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Nombre", "Descripcion", "Codigo", "Tipo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaItems.getColumnModel().getColumn(0).setPreferredWidth(168);
		tablaItems.getColumnModel().getColumn(1).setPreferredWidth(167);
		tablaItems.getColumnModel().getColumn(2).setPreferredWidth(148);
		tablaItems.getColumnModel().getColumn(3).setPreferredWidth(139);
		tablaItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel pPersona = new JPanel();
		tabbedPane.addTab("Persona", null, pPersona, null);
		pPersona.setLayout(null);
		
		crearP = new JButton("Crear");
		crearP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearPersona();
			}
		});
		crearP.setBounds(10, 47, 89, 23);
		pPersona.add(crearP);
		
		borrarP = new JButton("Borrar");
		borrarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarPersona();
			}
		});
		borrarP.setBounds(10, 107, 89, 23);
		pPersona.add(borrarP);
		
		modiP = new JButton("Modificar");
		modiP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPersona();
			}
		});
		modiP.setBounds(10, 168, 89, 23);
		pPersona.add(modiP);
		
		consultP = new JButton("Consultar");
		consultP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarPersona();
			}
		});
		consultP.setBounds(10, 238, 89, 23);
		pPersona.add(consultP);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(158, 51, 495, 216);
		pPersona.add(scrollPane_2);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Telefono", "Correo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(174);
		table.getColumnModel().getColumn(1).setPreferredWidth(155);
		table.getColumnModel().getColumn(2).setPreferredWidth(220);
		scrollPane_2.setViewportView(table);

		JPanel pTipo = new JPanel();
		tabbedPane.addTab("Tipo", null, pTipo, null);
		pTipo.setLayout(null);
		
		crearT = new JButton("Crear");
		crearT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearTipo();
			}
		});
		crearT.setBounds(10, 30, 89, 23);
		pTipo.add(crearT);
		
		borrarT = new JButton("Borrar");
		borrarT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarTipo();
			}
		});
		borrarT.setBounds(10, 95, 89, 23);
		pTipo.add(borrarT);
		
		modificarT = new JButton("Modificar");
		modificarT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarTipo();
			}
		});
		modificarT.setBounds(10, 163, 89, 23);
		pTipo.add(modificarT);
		
		consultT = new JButton("Consultar");
		consultT.setBounds(10, 220, 89, 23);
		pTipo.add(consultT);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(149, 34, 564, 217);
		pTipo.add(scrollPane);
		
		tablaTipos = new JTable();
		tablaTipos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tablaTipos);

		JPanel pCategoria = new JPanel();
		tabbedPane.addTab("Categoria", null, pCategoria, null);
		pCategoria.setLayout(null);
		
		crearC = new JButton("Crear");
		crearC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCategoria();
			}
		});
		crearC.setBounds(10, 33, 89, 23);
		pCategoria.add(crearC);
		
		borrarC = new JButton("Borrar");
		borrarC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarCategoria();
			}
		});
		borrarC.setBounds(10, 92, 89, 23);
		pCategoria.add(borrarC);
		
		modiC = new JButton("Modificar");
		modiC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarCategoria();
			}
		});
		modiC.setBounds(10, 150, 89, 23);
		pCategoria.add(modiC);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(136, 37, 351, 229);
		pCategoria.add(scrollPane_1);
		
		tablaCategorias = new JTable();
		tablaCategorias.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre"
			}
		));
		scrollPane_1.setViewportView(tablaCategorias);

		JPanel pPrestamos = new JPanel();
		tabedMain.addTab("Prestamos", null, pPrestamos, null);

		JPanel pReportes = new JPanel();
		tabedMain.addTab("Reportes", null, pReportes, null);
		cargarPersonas();
		cargarCategorias();
	}
	private void crearPersona() {
		CrearUsuario dialog = new CrearUsuario(frame);
		dialog.setVisible(true);
		cargarPersonas();
	}
	private void modificarPersona() {
	    int fila = table.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(frame, "debe seleccionar una persona.", "error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    String telefono = (String) ((DefaultTableModel) table.getModel()).getValueAt(fila, 1);
	    try {
	        Logica.Usuario usuario = Controlador.getInstancia().buscarPersona(telefono);
	        ModificarUsuario dialog = new ModificarUsuario(frame, usuario);
	        dialog.setVisible(true);
	        cargarPersonas();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void borrarPersona() {
	    int fila = table.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(frame, "Debe seleccionar una persona.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    String nombre = (String) ((DefaultTableModel) table.getModel()).getValueAt(fila, 0);
	    String telefono = (String) ((DefaultTableModel) table.getModel()).getValueAt(fila, 1);
	    int respuesta = JOptionPane.showConfirmDialog(
	        frame, "Se va a eliminar la persona: " + nombre,
	        "Confirmar", JOptionPane.YES_NO_OPTION);
	    if (respuesta == JOptionPane.YES_OPTION) {
	        try {
	            Controlador.getInstancia().borrarUsuario(telefono);
	            cargarPersonas();
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	private void consultarPersona() {
	    int fila = table.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(frame, "Debe seleccionar una persona.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    String telefono = (String) ((DefaultTableModel) table.getModel()).getValueAt(fila, 1);
	    try {
	        Logica.Usuario usuario = Controlador.getInstancia().buscarPersona(telefono);
	        StringBuilder sb = new StringBuilder();
	        sb.append("Nombre: ").append(usuario.getNombre()).append("\n");
	        sb.append("Teléfono: ").append(usuario.getTelefono()).append("\n");
	        sb.append("Correo: ").append(usuario.getCorreo()).append("\n\n");

	        if (usuario.getPrestamos().isEmpty()) {
	            sb.append("No tiene préstamos activos.");
	        } else {
	            sb.append("Préstamos activos:\n");
	            for (Logica.Prestamo p : usuario.getPrestamos()) {
	                sb.append("  - Préstamo #").append(p.getIdPrestamo());
	                sb.append(" | Fecha: ").append(p.getFecha()).append("\n");
	            }
	        }
	        JOptionPane.showMessageDialog(frame, sb.toString(), "Consulta de persona", JOptionPane.INFORMATION_MESSAGE);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void cargarPersonas() {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);
	    for (Logica.Usuario u : Controlador.getInstancia().listarUsuarios().values()) {
	        model.addRow(new Object[] {
	            u.getNombre(),
	            u.getTelefono(),
	            u.getCorreo()
	        });
	    }
	}
	private void crearCategoria() {
	    String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre de la categoría:");
	    if (nombre != null && !nombre.trim().isEmpty()) {
	        try {
	            Controlador.getInstancia().crearCategoria(nombre.trim());
	            cargarCategorias();
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	private void modificarCategoria() {
	    int fila = tablaCategorias.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(frame, "Debe seleccionar una categoría.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    String nombreActual = (String) ((DefaultTableModel) tablaCategorias.getModel()).getValueAt(fila, 0);
	    String nuevoNombre = JOptionPane.showInputDialog(frame, "Cambiar nombre:", nombreActual);
	    if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
	        try {
	            Controlador.getInstancia().editarCategoria(nombreActual, nuevoNombre.trim());
	            cargarCategorias();
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	private void borrarCategoria() {
	    int fila = tablaCategorias.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(frame, "Debe seleccionar una categoría.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    String nombre = (String) ((DefaultTableModel) tablaCategorias.getModel()).getValueAt(fila, 0);
	    int respuesta = JOptionPane.showConfirmDialog(
	        frame, "Se va a eliminar la categoría: " + nombre,
	        "Confirmar", JOptionPane.YES_NO_OPTION);
	    if (respuesta == JOptionPane.YES_OPTION) {
	        try {
	            Controlador.getInstancia().borrarCategoria(nombre);
	            cargarCategorias();
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	private void cargarCategorias() {
	    DefaultTableModel model = (DefaultTableModel) tablaCategorias.getModel();
	    model.setRowCount(0);
	    for (Logica.Categoria c : Controlador.getInstancia().listarCategorias().values()) {
	        model.addRow(new Object[] { c.getNombre() });
	    }
	}
	private void crearTipo() {
	    String descripcion = JOptionPane.showInputDialog(frame, "ingrese la descripcion del tipo: ");
	    if (descripcion != null && !descripcion.trim().isEmpty()) {
	        try {
	            Controlador.getInstancia().crearTipo(descripcion.trim());
	            cargarTipos();
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(frame, e.getMessage(), "error:", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	private void modificarTipo() {
	    int fila = tablaTipos.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(frame, "debe seleccionar un tipo", "error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    String descripcionActual = (String) ((DefaultTableModel) tablaTipos.getModel()).getValueAt(fila, 0);
	    String nuevaDescripcion = JOptionPane.showInputDialog(frame, "cambiar descripcion:", descripcionActual);
	    if (nuevaDescripcion != null && !nuevaDescripcion.trim().isEmpty()) {
	        try {
	            Controlador.getInstancia().editarTipo(descripcionActual, nuevaDescripcion.trim());
	            cargarTipos();
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(frame, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	private void borrarTipo() {
	    int fila = tablaTipos.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(frame, "debe seleccionar un tipo.", "error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    String descripcion = (String) ((DefaultTableModel) tablaTipos.getModel()).getValueAt(fila, 0);
	    int respuesta = JOptionPane.showConfirmDialog(
	        frame, "se va a eliminar el tipo: " + descripcion,
	        "Seguro?", JOptionPane.YES_NO_OPTION);
	    if (respuesta == JOptionPane.YES_OPTION) {
	        try {
	            Controlador.getInstancia().borrarTipo(descripcion);
	            cargarTipos();
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(frame, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	private void cargarTipos() {
	    DefaultTableModel model = (DefaultTableModel) tablaTipos.getModel();
	    model.setRowCount(0);
	    for (Tipo t : Controlador.getInstancia().listarTipos().values()) {
	        model.addRow(new Object[] { t.getDescripcion() });
	    }
	}
	private void modificarItem() {
	    int numeroFila = tablaItems.getSelectedRow();
	    if (numeroFila == -1) {
	        JOptionPane.showMessageDialog(frame, "Debe seleccionar un ítem.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    int codigo = (int) ((DefaultTableModel) tablaItems.getModel()).getValueAt(numeroFila, 2);
	    try {
	        Item item = Controlador.getInstancia().buscarItem(codigo);
	        ModificarItem dialog = new ModificarItem(frame, item);
	        dialog.setVisible(true);
	        cargarItems();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void crearItem() {
		CrearItem dialog = new CrearItem(frame);
		dialog.setVisible(true);
		cargarItems();
	}
	
	private void borrarItem() {
		int numeroFila = tablaItems.getSelectedRow();
		if (numeroFila == -1) {
			JOptionPane.showMessageDialog(
					frame, "Debe seleccionar al menos 1 item.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tablaItems.getModel();
		    int codigo = (int) model.getValueAt(numeroFila, 2);
		    String nombreItem = (String) model.getValueAt(numeroFila, 0);

		    int respuesta = JOptionPane.showConfirmDialog(
		        frame,
		        "se va a eliminar el Item: " + nombreItem ,
		        "confirmar",
		        JOptionPane.YES_NO_OPTION );
		    if (respuesta == JOptionPane.YES_OPTION) {
		        Controlador control = Controlador.getInstancia();
		        try {
		            control.borrarItem(codigo);
		            cargarItems();
		        } catch (Exception e) {
		            JOptionPane.showMessageDialog(
		                frame,
		                "Error borrando item: " + e.toString(),
		                "Error",
		                JOptionPane.ERROR_MESSAGE
		            );
			
		        }
		    }
		}
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
