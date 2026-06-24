package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import Controladora.Controlador;
import Logica.Item;

public class EliminarItemPrestamo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> eliminarItem;
	private JButton okButton;
	private JButton cancelButton;
	private int idPrestamo;

	public static void main(String[] args) {
		try {
			EliminarItemPrestamo dialog = new EliminarItemPrestamo(null, 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EliminarItemPrestamo(JFrame parent, int idPrestamo) {
		super(parent, true);
		this.idPrestamo = idPrestamo;
		setTitle("Eliminar un item:" + idPrestamo);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("seleccione un item para eliminar");
		lblNewLabel.setBounds(10, 11, 250, 14);
		contentPanel.add(lblNewLabel);

		eliminarItem = new JComboBox<>();
		eliminarItem.setBounds(10, 33, 300, 22);
		contentPanel.add(eliminarItem);

		Controlador control = Controlador.getInstancia();
		try {
		    Logica.Prestamo prestamo = control.buscarPrestamo(idPrestamo);
		    for (Item item : prestamo.getItems().values()) {
		        eliminarItem.addItem(item.getNombre() + " - " + item.getCodigo());
		    }
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

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

		okButton.addActionListener(e -> {
			try {
				if (eliminarItem.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(this, "no hay items disponibles", "error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String seleccion = (String) eliminarItem.getSelectedItem();
				int codigo = Integer.parseInt(seleccion.split(" - ")[1]);
				control.eliminarItemDePrestamo(idPrestamo, codigo);
				JOptionPane.showMessageDialog(this, "item eliminado");
				dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
			}
		});

		cancelButton.addActionListener(e -> dispose());
	}
}
