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

import Controladora.Controlador;
import Logica.Item;
import Logica.Tipo;

public class ModificarItem extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField modiNombre;
	private JTextField modiDesc;
	private JTextField modiCodigo;
	private JComboBox combTipo;
	private JButton bConfirm;
	private JButton bCancel;
	private Item itemEditar;

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
		modiNombre.setBounds(10, 37, 86, 20);
		contentPanel.add(modiNombre);
		modiNombre.setColumns(10);

		modiDesc = new JTextField();
		modiDesc.setBounds(10, 82, 86, 20);
		contentPanel.add(modiDesc);
		modiDesc.setColumns(10);

		modiCodigo = new JTextField();
		modiCodigo.setBounds(10, 127, 86, 20);
		contentPanel.add(modiCodigo);
		modiCodigo.setColumns(10);

		combTipo = new JComboBox();
		combTipo.setBounds(10, 177, 86, 22);
		contentPanel.add(combTipo);

		JLabel labelNombre = new JLabel("Modificar nombre aqui");
		labelNombre.setBounds(140, 40, 131, 14);
		contentPanel.add(labelNombre);

		JLabel labelDesc = new JLabel("Modificar descripcion aqui");
		labelDesc.setBounds(136, 85, 161, 14);
		contentPanel.add(labelDesc);

		JLabel labelCodigo = new JLabel("Modificar codigo aqui");
		labelCodigo.setBounds(140, 130, 131, 14);
		contentPanel.add(labelCodigo);

		JLabel labelTipo = new JLabel("Modificar tipo aqui");
		labelTipo.setBounds(140, 181, 131, 14);
		contentPanel.add(labelTipo);

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

	
		if (itemEditar != null) {
			modiNombre.setText(itemEditar.getNombre());
			modiDesc.setText(itemEditar.getDescripcion());
			modiCodigo.setText(String.valueOf(itemEditar.getCodigo()));
			combTipo.setSelectedItem(itemEditar.getTipo().getDescripcion());
		}


		bConfirm.addActionListener(e -> {
			try {
				String nombre = modiNombre.getText().trim();
				String desc = modiDesc.getText().trim();
				int codigo = Integer.parseInt(modiCodigo.getText().trim());
				String tipo = (String) combTipo.getSelectedItem();

				ctrl.editarItem(itemEditar.getCodigo(), desc, tipo, nombre, codigo);
				JOptionPane.showMessageDialog(this, "Ítem modificado exitosamente");
				dispose();
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "El código debe ser un número entero");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});

		bCancel.addActionListener(e -> dispose());
	}
}