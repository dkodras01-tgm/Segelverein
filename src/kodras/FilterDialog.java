package kodras;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class FilterDialog extends JDialog {

	private static final long serialVersionUID = -597674621357258426L;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblName;
	private JTextField textField_2;
	private JLabel lblPersonen;
	private JTextField textField_3;
	private JLabel lblTiefgang;
	JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	private JComboBox<String> comboBox_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FilterDialog dialog = new FilterDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FilterDialog() {
		setVisible(true);
		setBounds(100, 100, 792, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		textField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, textField, 34, SpringLayout.NORTH, contentPanel);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblId, 10, SpringLayout.NORTH, contentPanel);
		contentPanel.add(lblId);
		{
			lblName = new JLabel("Name");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblName, 0, SpringLayout.NORTH, lblId);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblName, 188, SpringLayout.EAST, lblId);
			contentPanel.add(lblName);
		}
		{
			textField_1 = new JTextField();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, textField_1, 0, SpringLayout.NORTH, textField);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			lblPersonen = new JLabel("Personen");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblPersonen, 0, SpringLayout.NORTH, lblId);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblPersonen, 174, SpringLayout.EAST, lblName);
			contentPanel.add(lblPersonen);
		}
		{
			textField_2 = new JTextField();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, textField_2, 0, SpringLayout.NORTH, textField);
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			lblTiefgang = new JLabel("Tiefgang");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblTiefgang, 0, SpringLayout.NORTH, lblId);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblTiefgang, 133, SpringLayout.EAST, lblPersonen);
			contentPanel.add(lblTiefgang);
		}
		{
			textField_3 = new JTextField();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, textField_3, 0, SpringLayout.NORTH, textField);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}
		
		comboBox = new JComboBox<String>();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, comboBox, 10, SpringLayout.SOUTH, lblId);
		sl_contentPanel.putConstraint(SpringLayout.WEST, comboBox, 10, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblId, 0, SpringLayout.WEST, comboBox);
		sl_contentPanel.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, comboBox);
		contentPanel.add(comboBox);
		
		comboBox.addItem("=");
		comboBox.addItem(">");
		comboBox.addItem("<");
		comboBox.addItem(">=");
		comboBox.addItem("<=");
		comboBox.addItem("!=");
		{
			comboBox_1 = new JComboBox<String>();
			sl_contentPanel.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, comboBox_1);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, comboBox_1, 0, SpringLayout.NORTH, textField);
			sl_contentPanel.putConstraint(SpringLayout.WEST, comboBox_1, 0, SpringLayout.WEST, lblName);
			contentPanel.add(comboBox_1);
			{
				comboBox_2 = new JComboBox<String>();
				sl_contentPanel.putConstraint(SpringLayout.WEST, textField_2, 6, SpringLayout.EAST, comboBox_2);
				sl_contentPanel.putConstraint(SpringLayout.NORTH, comboBox_2, 0, SpringLayout.NORTH, textField);
				sl_contentPanel.putConstraint(SpringLayout.WEST, comboBox_2, 0, SpringLayout.WEST, lblPersonen);
				contentPanel.add(comboBox_2);
			}
			{
				comboBox_3 = new JComboBox<String>();
				sl_contentPanel.putConstraint(SpringLayout.WEST, textField_3, 6, SpringLayout.EAST, comboBox_3);
				sl_contentPanel.putConstraint(SpringLayout.NORTH, comboBox_3, 0, SpringLayout.NORTH, textField);
				sl_contentPanel.putConstraint(SpringLayout.WEST, comboBox_3, 0, SpringLayout.WEST, lblTiefgang);
				contentPanel.add(comboBox_3);
			}
			
			comboBox_1.addItem("=");
			comboBox_1.addItem(">");
			comboBox_1.addItem("<");
			comboBox_1.addItem(">=");
			comboBox_1.addItem("<=");
			comboBox_1.addItem("!=");
			comboBox_1.addItem("LIKE");
			comboBox_2.addItem("=");
			comboBox_2.addItem(">");
			comboBox_2.addItem("<");
			comboBox_2.addItem(">=");
			comboBox_2.addItem("<=");
			comboBox_2.addItem("!=");
			comboBox_3.addItem("=");
			comboBox_3.addItem(">");
			comboBox_3.addItem("<");
			comboBox_3.addItem(">=");
			comboBox_3.addItem("<=");
			comboBox_3.addItem("!=");
		}
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
						
						dispose();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
			}
		}
//		setModal(true);
	}
	
	public String getValue() {
		String rueck = "SELECT * FROM boot";
		ArrayList<String> array = new ArrayList<String>();
		if(!textField.getText().equals("")) {
			array.add("id" + comboBox.getSelectedItem() + textField.getText());
		}
		if(!textField_1.getText().equals("")) {
			array.add("name" + comboBox.getSelectedItem() + "'" + textField_1.getText() + "'");
		}
		if(!textField_2.getText().equals("")) {
			array.add("personen" + comboBox.getSelectedItem() + textField_2.getText());
		}
		if(!textField_3.getText().equals("")) {
			array.add("tiefgang" + comboBox.getSelectedItem() + textField_3.getText());
		}
		
		if(array.size()>0) {
			rueck += " WHERE ";
			int i=0;
			for(; i<array.size()-1; i++) {
				rueck += array.get(i) + " AND ";
			}
			rueck += array.get(i);
		}
		return rueck;
	}
}
