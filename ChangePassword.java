package capytec;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField password;
	private JPasswordField rePassword;
	
	private DatabaseConn database = new DatabaseConn();

	/**
	 * Create the frame.
	 */
	public ChangePassword(int id) {
		setTitle("Change Password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 367, 157);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setBounds(21, 21, 124, 14);
		contentPane.add(lblNewPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(21, 59, 124, 14);
		contentPane.add(lblConfirmPassword);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Arrays.equals(password.getPassword(), rePassword.getPassword())) {
					System.out.println(id);
					if (database.changePassword(id, new String(password.getPassword()))) {
						JOptionPane.showMessageDialog(null, "Password successfully Changed!");
					} else {
						JOptionPane.showMessageDialog(null, "Password was not changed!", "error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					
				}
			}
		});
		btnChangePassword.setBounds(168, 87, 149, 23);
		contentPane.add(btnChangePassword);
		
		password = new JPasswordField();
		password.setBounds(136, 18, 181, 20);
		contentPane.add(password);
		
		rePassword = new JPasswordField();
		rePassword.setBounds(136, 56, 181, 20);
		contentPane.add(rePassword);
	}
}
