package capytec;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddStaff extends JFrame {

	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField addressLine1;
	private JTextField addressLine2;
	private JTextField postcode;
	private JTextField contact;
	
	private DatabaseConn database = new DatabaseConn();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStaff frame = new AddStaff();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddStaff() {
		setTitle("Add Staff");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 366, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddStaff = new JButton("Add Staff");
		btnAddStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Adds a new staff member to the database using the information in the form
				if (database.createStaff(firstname.getText(), lastname.getText(), addressLine1.getText(), addressLine2.getText(), postcode.getText(), contact.getText())) {
					JOptionPane.showMessageDialog(null, "Staff was successfully added!");
				}
			}
		});
		btnAddStaff.setBounds(195, 201, 111, 23);
		contentPane.add(btnAddStaff);
		
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setBounds(37, 23, 78, 14);
		contentPane.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setBounds(37, 48, 78, 14);
		contentPane.add(lblLastname);
		
		JLabel lblAddressLine = new JLabel("Address Line 1:");
		lblAddressLine.setBounds(37, 82, 111, 14);
		contentPane.add(lblAddressLine);
		
		firstname = new JTextField();
		firstname.setBounds(103, 20, 151, 20);
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		lastname = new JTextField();
		lastname.setBounds(103, 48, 151, 20);
		contentPane.add(lastname);
		lastname.setColumns(10);
		
		addressLine1 = new JTextField();
		addressLine1.setBounds(128, 79, 178, 20);
		contentPane.add(addressLine1);
		addressLine1.setColumns(10);
		
		JLabel lblAddressLine_1 = new JLabel("Address Line 2:");
		lblAddressLine_1.setBounds(37, 113, 111, 14);
		contentPane.add(lblAddressLine_1);
		
		addressLine2 = new JTextField();
		addressLine2.setBounds(128, 110, 178, 20);
		contentPane.add(addressLine2);
		addressLine2.setColumns(10);
		
		JLabel lblPostcode = new JLabel("Postcode:");
		lblPostcode.setBounds(37, 148, 78, 14);
		contentPane.add(lblPostcode);
		
		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setBounds(37, 173, 111, 14);
		contentPane.add(lblContactNumber);
		
		postcode = new JTextField();
		postcode.setBounds(138, 145, 168, 20);
		contentPane.add(postcode);
		postcode.setColumns(10);
		
		contact = new JTextField();
		contact.setBounds(138, 170, 168, 20);
		contentPane.add(contact);
		contact.setColumns(10);
	}

}
