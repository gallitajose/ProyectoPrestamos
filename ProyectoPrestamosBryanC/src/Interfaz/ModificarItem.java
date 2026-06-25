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
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.util.List;
import java.util.ArrayList;
 
import Controladora.Controlador;
import Logica.Item;
import Logica.Tipo;
import Logica.Categoria;
 
public class ModificarItem extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField modiNombre;
	private JTextField modiDesc;
	private JTextField modiCodigo;
	private JComboBox<String> combTipo;
	private JButton bConfirm;
	private JButton bCancel;
	private Item itemEditar;
	private JList<String> listaCategorias;
 
	public static void main(String[] args) {
		try {
			ModificarItem dialog = new ModificarItem(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	public ModificarItem(JFrame parent, Item item) {
		super(parent, true);
		this.itemEditar = item;
		setTitle("Modificar Item");
 
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
 
		modiNombre = new JTextField();
		modiNombre.setBounds(10, 11, 86, 20);
		contentPanel.add(modiNombre);
		modiNombre.setColumns(10);
 
		modiDesc = new JTextField();
		modiDesc.setBounds(10, 42, 86, 20);
		contentPanel.add(modiDesc);
		modiDesc.setColumns(10);
 
		modiCodigo = new JTextField();
		modiCodigo.setBounds(10, 73, 86, 20);
		contentPanel.add(modiCodigo);
		modiCodigo.setColumns(10);
 
		combTipo = new JComboBox<>();
		combTipo.setBounds(10, 117, 86, 22);
		contentPanel.add(combTipo);
 
		JLabel labelNombre = new JLabel("Modificar nombre aqui");
		labelNombre.setBounds(136, 14, 131, 14);
		contentPanel.add(labelNombre);
 
		JLabel labelDesc = new JLabel("Modificar descripcion aqui");
		labelDesc.setBounds(136, 45, 161, 14);
		contentPanel.add(labelDesc);
 
		JLabel labelCodigo = new JLabel("Modificar codigo aqui");
		labelCodigo.setBounds(136, 76, 131, 14);
		contentPanel.add(labelCodigo);
 
		JLabel labelTipo = new JLabel("Modificar tipo aqui");
		labelTipo.setBounds(136, 121, 131, 14);
		contentPanel.add(labelTipo);
 
		DefaultListModel<String> modeloCategorias = new DefaultListModel<>();
 
		JLabel labelCategorias = new JLabel("Seleccione las categorias");
		labelCategorias.setBounds(244, 226, 166, 14);
		contentPanel.add(labelCategorias);
		
		JScrollPane scrollCategorias = new JScrollPane();
		scrollCategorias.setBounds(10, 168, 206, 49);
		contentPanel.add(scrollCategorias);
		DefaultListModel<String> modeloCategorias1 = new DefaultListModel<>();
		listaCategorias = new JList<>(modeloCategorias1);
		listaCategorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollCategorias.setViewportView(listaCategorias);
		
		JLabel lblNewLabel = new JLabel("Modificar categorias aqui");
		lblNewLabel.setBounds(251, 185, 141, 14);
		contentPanel.add(lblNewLabel);
 
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
 
		bConfirm = new JButton("Aplicar cambios");
		buttonPane.add(bConfirm);
 
		bCancel = new JButton("Cancelar");
		buttonPane.add(bCancel);
 
		
		Controlador ctrl = Controlador.getInstancia();
		for (Tipo t : ctrl.listarTipos().values()) {
			combTipo.addItem(t.getDescripcion());
		}
		for (Categoria c : ctrl.listarCategorias().values()) {
			modeloCategorias1.addElement(c.getNombre());
		}
 
	
		if (itemEditar != null) {
			modiNombre.setText(itemEditar.getNombre());
			modiDesc.setText(itemEditar.getDescripcion());
			modiCodigo.setText(String.valueOf(itemEditar.getCodigo()));
			combTipo.setSelectedItem(itemEditar.getTipo().getDescripcion());
 
			List<Integer> indicesSeleccionados = new ArrayList<>();
			for (Categoria c : itemEditar.getCategorias().values()) {
				int idx = modeloCategorias1.indexOf(c.getNombre());
				if (idx != -1) {
					indicesSeleccionados.add(idx);
				}
			}
			int[] indicesArray = indicesSeleccionados.stream().mapToInt(Integer::intValue).toArray();
			listaCategorias.setSelectedIndices(indicesArray);
		}
 
 
		bConfirm.addActionListener(e -> {
			try {
				String nombre = modiNombre.getText().trim();
				String desc = modiDesc.getText().trim();
				int codigo = Integer.parseInt(modiCodigo.getText().trim());
				String tipo = (String) combTipo.getSelectedItem();
 
				List<String> categoriasSeleccionadas = listaCategorias.getSelectedValuesList();
				ctrl.editarItem(itemEditar.getCodigo(), desc, tipo, nombre, codigo, categoriasSeleccionadas);
				JOptionPane.showMessageDialog(this, "item modificado");
				dispose();
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "El codigo debe ser un numero entero");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});
 
		bCancel.addActionListener(e -> dispose());
	}
}