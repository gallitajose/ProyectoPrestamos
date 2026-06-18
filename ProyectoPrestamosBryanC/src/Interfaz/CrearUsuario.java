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
import javax.swing.JLabel;

import Controladora.Controlador;

public class CrearUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField crearNombreP;
	private JTextField crearTelefonoP;
	private JTextField crearCorreoP;
	private JButton okButton;
	private JButton cancelButton;

	public static void main(String[] args) {
		try {
			CrearUsuario dialog = new CrearUsuario(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CrearUsuario(JFrame parent) {
		super(parent, true);
		setTitle("Crear Usuario");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		crearNombreP = new JTextField();
		crearNombreP.setBounds(10, 37, 86, 20);
		contentPanel.add(crearNombreP);
		crearNombreP.setColumns(10);

		crearTelefonoP = new JTextField();
		crearTelefonoP.setBounds(10, 97, 86, 20);
		contentPanel.add(crearTelefonoP);
		crearTelefonoP.setColumns(10);

		crearCorreoP = new JTextField();
		crearCorreoP.setBounds(10, 155, 86, 20);
		contentPanel.add(crearCorreoP);
		crearCorreoP.setColumns(10);

		JLabel lblNewLabel = new JLabel("Digite el nombre del usuario");
		lblNewLabel.setBounds(132, 40, 166, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Digite el telefono del usuario");
		lblNewLabel_1.setBounds(132, 100, 166, 14);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Digite el correo del usuario");
		lblNewLabel_2.setBounds(132, 158, 166, 14);
		contentPanel.add(lblNewLabel_2);

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
				String nombre = crearNombreP.getText().trim();
				String telefono = crearTelefonoP.getText().trim();
				String correo = crearCorreoP.getText().trim();

				if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
					return;
				}

				Controlador.getInstancia().agregarUsuario(nombre, telefono, correo);
				JOptionPane.showMessageDialog(this, "Usuario creado exitosamente");
				dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		cancelButton.addActionListener(e -> dispose());
	}
}
