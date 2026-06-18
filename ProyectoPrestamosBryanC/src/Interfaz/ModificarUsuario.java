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
import Logica.Usuario;

public class ModificarUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField modiNombreP;
	private JTextField modiTelefonoP;
	private JTextField modiCorreoP;
	private JButton okButton;
	private JButton cancelButton;
	private Usuario usuarioEditar;

	public static void main(String[] args) {
		try {
			ModificarUsuario dialog = new ModificarUsuario(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ModificarUsuario(JFrame parent, Usuario usuario) {
		super(parent, true);
		this.usuarioEditar = usuario;
		setTitle("Modificar Usuario");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		modiNombreP = new JTextField();
		modiNombreP.setBounds(10, 37, 86, 20);
		contentPanel.add(modiNombreP);
		modiNombreP.setColumns(10);

		JLabel lblNewLabel = new JLabel("Modificar nombre aqui");
		lblNewLabel.setBounds(151, 40, 180, 14);
		contentPanel.add(lblNewLabel);

		modiTelefonoP = new JTextField();
		modiTelefonoP.setBounds(10, 104, 86, 20);
		contentPanel.add(modiTelefonoP);
		modiTelefonoP.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Modificar telefono aqui");
		lblNewLabel_1.setBounds(151, 107, 180, 14);
		contentPanel.add(lblNewLabel_1);

		modiCorreoP = new JTextField();
		modiCorreoP.setBounds(10, 171, 86, 20);
		contentPanel.add(modiCorreoP);
		modiCorreoP.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Modificar correo aqui");
		lblNewLabel_2.setBounds(151, 174, 180, 14);
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

		if (usuarioEditar != null) {
			modiNombreP.setText(usuarioEditar.getNombre());
			modiTelefonoP.setText(usuarioEditar.getTelefono());
			modiCorreoP.setText(usuarioEditar.getCorreo());
		}

		okButton.addActionListener(e -> {
			try {
				String nombre = modiNombreP.getText().trim();
				String telefono = modiTelefonoP.getText().trim();
				String correo = modiCorreoP.getText().trim();

				if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
					return;
				}

				Controlador.getInstancia().editarUsuario(
					usuarioEditar.getTelefono(), nombre, telefono, correo);
				JOptionPane.showMessageDialog(this, "Usuario modificado exitosamente");
				dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		cancelButton.addActionListener(e -> dispose());
	}
}