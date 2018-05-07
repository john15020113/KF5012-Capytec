package capytec;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RemoveStaff extends JFrame {

	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	
	private DatabaseConn database = new DatabaseConn();
	private String[] staff = database.getAllStaff();

	/**
	 * Create the frame.
	 */
	public RemoveStaff() {
		setTitle("Remove Staff");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 322, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox staffId = new JComboBox();
		staffId.setBounds(113, 18, 151, 20);
		// New String array for the combo box strings, staff.length/3 because the database returns data in 3s
		String[] comboBoxAccountModel = new String[staff.length/3];
		// Iterate through the staff members and format the combo box strings.
		for(int i = 0; i < staff.length; i+=3) {
			comboBoxAccountModel[i/3] = staff[i] + ", " + staff[i+1] + ", " + staff[i+2];
		}
		staffId.setModel(new DefaultComboBoxModel(comboBoxAccountModel));
		staffId.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	// Extracting the firstname and lastname from the selected string using split
		        firstname.setText(staffId.getSelectedItem().toString().split(",")[1]);
		        lastname.setText(staffId.getSelectedItem().toString().split(",")[2]);
		    }
		});
		contentPane.add(staffId);
		
		JLabel staffIdLabel = new JLabel("Staff ID:");
		staffIdLabel.setBounds(29, 21, 46, 14);
		contentPane.add(staffIdLabel);
				
		JButton btnRemoveAccount = new JButton("Remove Account");
		btnRemoveAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (database.removeStaff(Integer.parseInt(staffId.getSelectedItem().toString().split(",")[0]))) {
					JOptionPane.showMessageDialog(null, "Staff was successfully removed!");
				} else {
					JOptionPane.showMessageDialog(null, "Staff was not removed!", "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRemoveAccount.setBounds(123, 114, 141, 23);
		contentPane.add(btnRemoveAccount);
		
		JLabel lblFirstname = new JLabel("Firstname: ");
		lblFirstname.setBounds(29, 52, 89, 14);
		contentPane.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setBounds(29, 83, 104, 14);
		contentPane.add(lblLastname);
		
		firstname = new JTextField();
		firstname.setBounds(113, 49, 151, 20);
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		lastname = new JTextField();
		lastname.setBounds(113, 80, 152, 20);
		contentPane.add(lastname);
		lastname.setColumns(10);
	}

}
