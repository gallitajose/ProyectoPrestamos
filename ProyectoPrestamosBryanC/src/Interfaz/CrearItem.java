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
import Logica.Tipo;

public class CrearItem extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField texNombre;
	private JTextField texDescripcion;
	private JTextField texCodigo;
	private JComboBox combTipo;
	private JButton okButton;
	private JButton cancelButton;

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
		texDescripcion.setBounds(10, 79, 86, 20);
		contentPanel.add(texDescripcion);
		texDescripcion.setColumns(10);

		texCodigo = new JTextField();
		texCodigo.setBounds(10, 133, 86, 20);
		contentPanel.add(texCodigo);
		texCodigo.setColumns(10);

		combTipo = new JComboBox();
		combTipo.setBounds(10, 182, 86, 22);
		contentPanel.add(combTipo);

		JLabel Nombre = new JLabel("Ingrese el nombre aqui");
		Nombre.setBounds(123, 30, 208, 14);
		contentPanel.add(Nombre);

		JLabel descripcion = new JLabel("Ingrese la descripcion del Item aqui");
		descripcion.setBounds(123, 82, 182, 14);
		contentPanel.add(descripcion);

		JLabel Codigo = new JLabel("Ingrese el codigo aqui");
		Codigo.setBounds(123, 136, 176, 14);
		contentPanel.add(Codigo);

		JLabel Tipo = new JLabel("Seleccione el tipo");
		Tipo.setBounds(123, 186, 131, 14);
		contentPanel.add(Tipo);

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

		okButton.addActionListener(e -> {
			try {
				String nombre = texNombre.getText().trim();
				String desc = texDescripcion.getText().trim();
				int codigo = Integer.parseInt(texCodigo.getText().trim());
				String tipo = (String) combTipo.getSelectedItem();

				ctrl.crearItem(desc, tipo, nombre, codigo);
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