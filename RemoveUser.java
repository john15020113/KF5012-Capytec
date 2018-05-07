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

public class RemoveUser extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	
	private DatabaseConn database = new DatabaseConn();
	private String[] accounts = database.getAllAccounts(); 

	/**
	 * Create the frame.
	 */
	public RemoveUser() {
		setTitle("Remove Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 322, 166);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox accountId = new JComboBox();
		accountId.setBounds(113, 18, 151, 20);
		// New String array for the combo box strings, staff.length/2 because the database returns data in 3s
		String[] comboBoxAccountModel = new String[accounts.length/2];
		// Iterate through the staff members and format the combo box strings.
		for(int i = 0; i < accounts.length; i+=2) {
			comboBoxAccountModel[i/2] = accounts[i] + ", " + accounts[i+1];
		}
		accountId.setModel(new DefaultComboBoxModel(comboBoxAccountModel));
		accountId.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        username.setText(accountId.getSelectedItem().toString().split(",")[1]);
		    }
		});
		contentPane.add(accountId);
		
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setBounds(29, 21, 46, 14);
		contentPane.add(lblUserId);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(29, 59, 79, 14);
		contentPane.add(lblUsername);
		
		username = new JTextField();
		username.setEditable(false);
		username.setBounds(113, 56, 151, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton btnRemoveAccount = new JButton("Remove Account");
		btnRemoveAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(database.removeAccount(Integer.parseInt(accountId.getSelectedItem().toString().split(",")[0]))) {
					JOptionPane.showMessageDialog(null, "User was successfully Removed!");
				} else {
					JOptionPane.showMessageDialog(null, "Could not remove selected user!", "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRemoveAccount.setBounds(123, 93, 141, 23);
		contentPane.add(btnRemoveAccount);
	}

}
