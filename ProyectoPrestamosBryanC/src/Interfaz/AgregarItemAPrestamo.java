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

public class AgregarItemAPrestamo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> agregarItem;
	private JButton okButton;
	private JButton cancelButton;
	private int idPrestamo;

	public static void main(String[] args) {
		try {
			AgregarItemAPrestamo dialog = new AgregarItemAPrestamo(null, 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AgregarItemAPrestamo(JFrame parent, int idPrestamo) {
		super(parent, true);
		this.idPrestamo = idPrestamo;
		setTitle("Agregar Ítem a Préstamo #" + idPrestamo);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Seleccione un Item para agregar:");
		lblNewLabel.setBounds(10, 11, 250, 14);
		contentPanel.add(lblNewLabel);

		agregarItem = new JComboBox<>();
		agregarItem.setBounds(10, 33, 300, 22);
		contentPanel.add(agregarItem);

		Controlador control = Controlador.getInstancia();
		for (Item item : control.listarItemsDisponibles().values()) {
		    agregarItem.addItem(item.getNombre() + " - " + item.getCodigo());
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
				if (agregarItem.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(this, "no hay items disponibles", "error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String seleccion = (String) agregarItem.getSelectedItem();
				int codigo = Integer.parseInt(seleccion.split(" - ")[1]);
				control.agregarItemAPrestamo(idPrestamo, codigo);
				JOptionPane.showMessageDialog(this, "item agregado");
				dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
			}
		});

		cancelButton.addActionListener(e -> dispose());
	}
}