package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.util.List;

import Logica.Categoria;
import Controladora.Controlador;
import Logica.Tipo;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class CrearItem extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField texNombre;
	private JTextField texDescripcion;
	private JTextField texCodigo;
	private JComboBox<String> combTipo;
	private JButton okButton;
	private JButton cancelButton;
	private JList<String> listaCategorias;

	public static void main(String[] args) {
		try {
			CrearItem dialog = new CrearItem(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CrearItem(JFrame parent) {
		super(parent, true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		texNombre = new JTextField();
		texNombre.setBounds(10, 27, 86, 20);
		contentPanel.add(texNombre);
		texNombre.setColumns(10);

		texDescripcion = new JTextField();
		texDescripcion.setBounds(10, 58, 86, 20);
		contentPanel.add(texDescripcion);
		texDescripcion.setColumns(10);

		texCodigo = new JTextField();
		texCodigo.setBounds(10, 89, 86, 20);
		contentPanel.add(texCodigo);
		texCodigo.setColumns(10);

		combTipo = new JComboBox<>();
		combTipo.setBounds(10, 133, 86, 22);
		contentPanel.add(combTipo);

		JLabel Nombre = new JLabel("Ingrese el nombre aqui");
		Nombre.setBounds(123, 30, 208, 14);
		contentPanel.add(Nombre);

		JLabel descripcion = new JLabel("Ingrese la descripcion del Item aqui");
		descripcion.setBounds(123, 61, 182, 14);
		contentPanel.add(descripcion);

		JLabel Codigo = new JLabel("Ingrese el codigo aqui");
		Codigo.setBounds(123, 92, 176, 14);
		contentPanel.add(Codigo);

		JLabel Tipo = new JLabel("Seleccione el tipo");
		Tipo.setBounds(123, 137, 131, 14);
		contentPanel.add(Tipo);
		
		DefaultListModel<String> modeloCategorias = new DefaultListModel<>();
		listaCategorias = new JList<>(modeloCategorias);
		listaCategorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		javax.swing.JScrollPane scrollCategorias = new javax.swing.JScrollPane(listaCategorias);
		scrollCategorias.setBounds(10, 166, 208, 51);
		contentPanel.add(scrollCategorias);
		
		JLabel lblNewLabel = new JLabel("Seleccione las categorias");
		lblNewLabel.setBounds(244, 182, 166, 14);
		contentPanel.add(lblNewLabel);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		Controlador ctrl = Controlador.getInstancia();
		for (Tipo t : ctrl.listarTipos().values()) {
			combTipo.addItem(t.getDescripcion());
		}
		for (Categoria c : ctrl.listarCategorias().values()) {
		    modeloCategorias.addElement(c.getNombre());
		}

		okButton.addActionListener(e -> {
			try {
				String nombre = texNombre.getText().trim();
				String desc = texDescripcion.getText().trim();
				int codigo = Integer.parseInt(texCodigo.getText().trim());
				String tipo = (String) combTipo.getSelectedItem();

				List<String> categoriasSeleccionadas = listaCategorias.getSelectedValuesList();
				ctrl.crearItem(desc, tipo, nombre, codigo, categoriasSeleccionadas);
				JOptionPane.showMessageDialog(this, "Ítem creado exitosamente");
				dispose();
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "El código debe ser un número entero");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});

		cancelButton.addActionListener(e -> dispose());
	}
}