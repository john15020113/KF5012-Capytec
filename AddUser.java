package capytec;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AddUser extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	
	private DatabaseConn database = new DatabaseConn();
	private String[] staff = database.getAllStaff();
	private JPasswordField password;
	private JPasswordField rePassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser frame = new AddUser();
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
	public AddUser() {
		setTitle("Add Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 322, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(27, 21, 97, 14);
		contentPane.add(lblUsername);
		
		username = new JTextField();
		username.setBounds(113, 18, 151, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(27, 56, 97, 14);
		contentPane.add(lblPassword);
		
		JLabel lblStaffId = new JLabel("Staff ID:");
		lblStaffId.setBounds(27, 122, 97, 14);
		contentPane.add(lblStaffId);
		
		JComboBox staffId = new JComboBox();
		// New String array for the combo box strings, staff.length/3 because the database returns data in 3s
		String[] comboBoxStaffModel = new String[staff.length/3];
		// Iterate through the staff members and format the combo box strings.
		for(int i = 0; i < staff.length; i+=3) {
			comboBoxStaffModel[i/3] = staff[i] + ", " + staff[i+1] + ", " + staff[i+2];
		}
		staffId.setModel(new DefaultComboBoxModel(comboBoxStaffModel));
		staffId.setBounds(113, 119, 151, 20);
		contentPane.add(staffId);
		
		JCheckBox adminCheck = new JCheckBox("Admin Account");
		adminCheck.setBounds(153, 146, 111, 23);
		contentPane.add(adminCheck);
		
		JButton btnAddAccount = new JButton("Add Account");
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the staff id from the combo box string by splitting on comma and taking the id in index 0
				String selectedStaffId = staffId.getSelectedItem().toString().split(",")[0];
				// Adds a new user to the database using the information in the form
				System.out.println(new String(password.getPassword()));
				if (Arrays.equals(password.getPassword(), rePassword.getPassword())) {
					if (database.createAccount(Integer.parseInt(selectedStaffId), username.getText(), password.getPassword().toString(), adminCheck.isSelected())) {
						JOptionPane.showMessageDialog(null, "Account was successfully added!");
					} else {
						JOptionPane.showMessageDialog(null, "Username must be unique!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddAccount.setBounds(153, 176, 111, 23);
		contentPane.add(btnAddAccount);
		
		JLabel rePasswordLabel = new JLabel("Confirm:");
		rePasswordLabel.setBounds(27, 84, 89, 14);
		contentPane.add(rePasswordLabel);
		
		password = new JPasswordField();
		password.setBounds(113, 53, 151, 20);
		contentPane.add(password);
		
		rePassword = new JPasswordField();
		rePassword.setBounds(113, 81, 151, 20);
		contentPane.add(rePassword);
	}
}
