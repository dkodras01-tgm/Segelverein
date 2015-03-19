package kodras;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class DatabaseDialog extends JDialog {

	private static final long serialVersionUID = 5975029054977319574L;
	
	private final JPanel contentPanel = new JPanel();
	private JLabel lblHost;
	private JLabel lblDatenbank;
	private JLabel lblBenutzer;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JLabel lblPasswort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DatabaseDialog dialog = new DatabaseDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DatabaseDialog(DatabaseDialogOnSuccessListener listener) {
		setBounds(100, 100, 244, 202);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblHost = new JLabel("Host:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblHost, 10, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblHost, 10, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblHost);
		}
		{
			lblDatenbank = new JLabel("Datenbank:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblDatenbank, 6, SpringLayout.SOUTH, lblHost);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblDatenbank, 0, SpringLayout.WEST, lblHost);
			contentPanel.add(lblDatenbank);
		}
		{
			lblBenutzer = new JLabel("Benutzer:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblBenutzer, 6, SpringLayout.SOUTH, lblDatenbank);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblBenutzer, 0, SpringLayout.WEST, lblHost);
			contentPanel.add(lblBenutzer);
		}
		{
			lblPasswort = new JLabel("Passwort:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblPasswort, 6, SpringLayout.SOUTH, lblBenutzer);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblPasswort, 0, SpringLayout.WEST, lblHost);
			contentPanel.add(lblPasswort);
		}
		
		textField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.WEST, textField, 50, SpringLayout.EAST, lblHost);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, lblHost);
		contentPanel.add(textField);
		textField.setText("localhost");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, textField_1, -3, SpringLayout.NORTH, lblDatenbank);
		sl_contentPanel.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField);
		contentPanel.add(textField_1);
		textField_1.setText("segelverein");
		textField_1.setColumns(10);
		{
			textField_2 = new JTextField();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, textField_2, 0, SpringLayout.NORTH, lblBenutzer);
			sl_contentPanel.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, textField);
			contentPanel.add(textField_2);
			textField_2.setText("segelverein");
			textField_2.setColumns(10);
		}
		
		passwordField = new JPasswordField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, passwordField, 7, SpringLayout.SOUTH, textField_2);
		sl_contentPanel.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, textField);
		sl_contentPanel.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, textField);
		passwordField.setText("segelverein");
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(listener!=null) {
							listener.onSuccess(textField.getText(), textField_1.getText(), textField_2.getText(), new String (passwordField.getPassword()));
						}
						DatabaseDialog.this.dispose();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(e -> DatabaseDialog.this.dispose());
			}
		}
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
