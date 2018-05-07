package capytec;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class EditStaff extends JFrame {

	private JPanel contentPane;
	
	private DatabaseConn database = new DatabaseConn();
	private String[] staff = database.getAllStaff();
	
	private JTextField firstname;
	private JTextField lastname;
	private JTextField addressLine1;
	private JTextField addressLine2;
	private JTextField postcode;
	private JTextField contact;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStaff frame = new EditStaff();
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
	public EditStaff() {
		setTitle("Edit Staff");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 366, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setBounds(24, 49, 78, 14);
		contentPane.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setBounds(24, 74, 78, 14);
		contentPane.add(lblLastname);
		
		JLabel lblAddressLine = new JLabel("Address Line 1:");
		lblAddressLine.setBounds(24, 108, 111, 14);
		contentPane.add(lblAddressLine);
		
		JLabel lblAddressLine_1 = new JLabel("Address Line 2:");
		lblAddressLine_1.setBounds(24, 139, 111, 14);
		contentPane.add(lblAddressLine_1);
		
		JLabel lblPostcode = new JLabel("Postcode:");
		lblPostcode.setBounds(24, 174, 78, 14);
		contentPane.add(lblPostcode);
		
		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setBounds(24, 199, 111, 14);
		contentPane.add(lblContactNumber);
		
		firstname = new JTextField();
		firstname.setBounds(103, 45, 164, 20);
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		lastname = new JTextField();
		lastname.setBounds(103, 73, 164, 20);
		contentPane.add(lastname);
		lastname.setColumns(10);
		
		addressLine1 = new JTextField();
		addressLine1.setBounds(125, 104, 179, 20);
		contentPane.add(addressLine1);
		addressLine1.setColumns(10);
		
		addressLine2 = new JTextField();
		addressLine2.setBounds(125, 135, 179, 20);
		contentPane.add(addressLine2);
		addressLine2.setColumns(10);
		
		postcode = new JTextField();
		postcode.setBounds(125, 170, 179, 20);
		contentPane.add(postcode);
		postcode.setColumns(10);
		
		contact = new JTextField();
		contact.setBounds(125, 195, 179, 20);
		contentPane.add(contact);
		contact.setColumns(10);
		
		JLabel lblChooseStaff = new JLabel("Choose Staff:");
		lblChooseStaff.setBounds(24, 12, 90, 14);
		contentPane.add(lblChooseStaff);
		
		JComboBox comboBox = new JComboBox();
		// New String array for the combo box strings, staff.length/3 because the database returns data in 3s
		String[] comboBoxStaffModel = new String[staff.length/3];
		// Iterate through the staff members and format the combo box strings.
		for(int i = 0; i < staff.length; i+=3) {
			comboBoxStaffModel[i/3] = staff[i] + ", " + staff[i+1] + ", " + staff[i+2];
		}
		comboBox.setModel(new DefaultComboBoxModel(comboBoxStaffModel));
		comboBox.setBounds(118, 8, 132, 20);
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	String[] selectedStaff = database.getUserDetails(Integer.parseInt(comboBox.getSelectedItem().toString().split(",")[0]));
		        firstname.setText(selectedStaff[0]);
		        lastname.setText(selectedStaff[1]);
		        addressLine1.setText(selectedStaff[2]);
		        addressLine2.setText(selectedStaff[3]);
		        postcode.setText(selectedStaff[4]);
		        contact.setText(selectedStaff[5]);
		    }
		});
		contentPane.add(comboBox);
		
		JButton btnEditStaff = new JButton("Edit Staff");
		btnEditStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Adds a new staff member to the database using the information in the form
				if (database.changeStaffDetails(Integer.parseInt(comboBox.getSelectedItem().toString().split(",")[0]),firstname.getText(), lastname.getText(), addressLine1.getText(), addressLine2.getText(), postcode.getText(), contact.getText())) {
					JOptionPane.showMessageDialog(null, "Staff was successfully changed!");
				}
			}
		});
		btnEditStaff.setBounds(193, 226, 111, 23);
		contentPane.add(btnEditStaff);
		
	}
}
