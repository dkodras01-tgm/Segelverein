package kodras;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import model.Boot;

public class HinzufuegeDialog extends JDialog {

	private static final long serialVersionUID = 5629319498971332860L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the dialog.
	 */
	public HinzufuegeDialog(final DatabaseList<Boot> dbl) {
		setVisible(true);
		setResizable(false);
		setTitle("Boot hinzufuegen");
		setBounds(100, 100, 387, 208);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new GridLayout(3, 2));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblName = new JLabel("    Name");
		lblName.setBounds(10, 13, 74, 14);
		
		JLabel lblPersonen = new JLabel("    Personen");
		lblPersonen.setBounds(10, 44, 74, 14);
		
		JLabel lblTiefgang = new JLabel("    Tiefgang");
		lblTiefgang.setBounds(10, 75, 74, 14);
		
		textField_1 = new JTextField();
		textField_1.setBounds(274, 10, 86, 20);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(274, 41, 86, 20);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(274, 72, 86, 20);
		textField_3.setColumns(10);
		
		contentPanel.add(lblName);
		contentPanel.add(textField_1);
		contentPanel.add(lblPersonen);
		contentPanel.add(textField_2);
		contentPanel.add(lblTiefgang);
		contentPanel.add(textField_3);
		
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
					public void actionPerformed(ActionEvent arg0) {
						HinzufuegeDialog.this.setVisible(false);
						Boot boot = new Boot();
						boot.setName(getName());
						boot.setPersonen(getPersonen());
						boot.setTiefgang(getTiefgang());
						
						dbl.add(boot);
						dispose();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener () {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
			}
		}
	}
	
	public String getValue() {
		String rueck = "INSERT INTO boot (name, personen, tiefgang) VALUES ('" + getName() + "', " + getPersonen() + ", " + getTiefgang() + ")";
		return rueck;
	}
	
	public String getName() {
		return textField_1.getText();
	}
	
	private int getPersonen() {
		try {
			return Integer.parseInt(textField_2.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"Falsche Eingabe! Bitte geben Sie beim Feld \"Personen\" eine Zahl ein.");
		}
		return 0;
	}
	
	private double getTiefgang() {
		try {
			return Double.parseDouble(textField_3.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"Falsche Eingabe! Bitte geben Sie den Tiefgang in cm an!");
		}
		return 0.0;
	}
}