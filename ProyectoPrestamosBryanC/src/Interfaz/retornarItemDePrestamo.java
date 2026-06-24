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

public class retornarItemDePrestamo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> retornarItem;
	private JButton okButton;
	private JButton cancelButton;
	private int idPrestamo;

	public static void main(String[] args) {
		try {
			retornarItemDePrestamo dialog = new retornarItemDePrestamo(null, 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public retornarItemDePrestamo(JFrame parent, int idPrestamo) {
		super(parent, true);
		this.idPrestamo = idPrestamo;
		setTitle("Retornar un item:" + idPrestamo);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("seleccione un item para retornar");
		lblNewLabel.setBounds(10, 11, 250, 14);
		contentPanel.add(lblNewLabel);

		retornarItem = new JComboBox<>();
		retornarItem.setBounds(10, 33, 300, 22);
		contentPanel.add(retornarItem);

		Controlador control = Controlador.getInstancia();
		try {
		    Logica.Prestamo prestamo = control.buscarPrestamo(idPrestamo);
		    for (Item item : prestamo.getItems().values()) {
		        retornarItem.addItem(item.getNombre() + " - " + item.getCodigo());
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
				if (retornarItem.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(this, "no hay items disponibles", "error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String seleccion = (String) retornarItem.getSelectedItem();
				int codigo = Integer.parseInt(seleccion.split(" - ")[1]);
				control.retornarItemPrestamo(idPrestamo, codigo);
				JOptionPane.showMessageDialog(this, "item regresado");
				dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
			}
		});

		cancelButton.addActionListener(e -> dispose());
	}
}

