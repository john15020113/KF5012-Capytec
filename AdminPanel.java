package capytec;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AdminPanel() {
		setTitle("Admin Panel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 352, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnEditStaff = new JButton("Edit Staff");
		btnEditStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditStaff editStaff = new EditStaff();
				editStaff.setVisible(true);
			}
		});
		btnEditStaff.setBounds(10, 45, 137, 23);
		contentPane.add(btnEditStaff);
		
		JButton btnAddStaff = new JButton("Add Staff");
		btnAddStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStaff addStaff = new AddStaff();
				addStaff.setVisible(true);
			}
		});
		btnAddStaff.setBounds(10, 11, 137, 23);
		contentPane.add(btnAddStaff);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUser addUser = new AddUser();
				addUser.setVisible(true);
			}
		});
		btnAddUser.setBounds(182, 11, 137, 23);
		contentPane.add(btnAddUser);
		
		JButton btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveUser removeUser = new RemoveUser();
				removeUser.setVisible(true);
			}
		});
		btnRemoveUser.setBounds(182, 45, 137, 23);
		contentPane.add(btnRemoveUser);
		
		JButton btnRemoveStaff = new JButton("Remove Staff");
		btnRemoveStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveStaff removeStaff = new RemoveStaff();
				removeStaff.setVisible(true);
			}
		});
		btnRemoveStaff.setBounds(10, 79, 137, 23);
		contentPane.add(btnRemoveStaff);
	}

}
