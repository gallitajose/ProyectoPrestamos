package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import Controladora.Controlador;

public class NuevoPrestamo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBox;
	private JButton okButton;
	private JButton cancelButton;

	public static void main(String[] args) {
		try {
			NuevoPrestamo dialog = new NuevoPrestamo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NuevoPrestamo(JFrame parent) {
		super(parent, true);
		setTitle("Nuevo Préstamo");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblPersona = new JLabel("Seleccione la persona para el préstamo:");
		lblPersona.setBounds(10, 14, 280, 20);
		contentPanel.add(lblPersona);

		comboBox = new JComboBox<>();
		comboBox.setBounds(10, 45, 300, 27);
		contentPanel.add(comboBox);

		// Cargar usuarios en el combo (nombre - telefono)
		Controlador ctrl = Controlador.getInstancia();
		for (Logica.Usuario u : ctrl.listarUsuarios().values()) {
			comboBox.addItem(u.getNombre() + " - " + u.getTelefono());
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

		// Lógica botón OK
		okButton.addActionListener(e -> {
			try {
				if (comboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(this, "Debe seleccionar una persona", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String seleccion = (String) comboBox.getSelectedItem();
				String telefono = seleccion.split(" - ")[1];

				int idPrestamo = ctrl.generarIdPrestamo();
				ctrl.hacerPrestamo(telefono, idPrestamo);

				// Preguntar si desea agregar alerta
				int respuesta = JOptionPane.showConfirmDialog(
					this, "¿Desea agregar una alerta a este préstamo?",
					"Alerta", JOptionPane.YES_NO_OPTION);

				if (respuesta == JOptionPane.YES_OPTION) {
					String mensaje = JOptionPane.showInputDialog(this, "Ingrese el mensaje de la alerta:");
					if (mensaje != null && !mensaje.trim().isEmpty()) {
						String[] opciones = {"Una sola vez", "Recurrente"};
						int tipo = JOptionPane.showOptionDialog(
							this, "¿Qué tipo de alerta desea?", "Tipo de alerta",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
							null, opciones, opciones[0]);

						int frecuencia = 0;
						if (tipo == 1) {
							String dias = JOptionPane.showInputDialog(this, "¿Cada cuántos días?");
							if (dias != null && !dias.trim().isEmpty()) {
								frecuencia = Integer.parseInt(dias.trim());
							}
						}
						ctrl.agregarAlertaPrestamo(idPrestamo, mensaje.trim(), frecuencia);
					}
				}

				JOptionPane.showMessageDialog(this, "Préstamo #" + idPrestamo + " creado exitosamente");
				dispose();
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "La frecuencia debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		cancelButton.addActionListener(e -> dispose());
	}
}