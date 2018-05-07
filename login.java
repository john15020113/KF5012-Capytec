/**
 * 
 * @author Johnathan Evans - 15020113
 *
 */

package capytec;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {
	
	// Creates a new login process to handle the login	
	private JPanel contentPane;
	private JTextField textFieldStaffID;
	private JPasswordField passwordField;
	private static login frame;
	
	private LoginProcess loginProcess = new LoginProcess();

	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new login();
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
	public login() {
		setTitle("Capytec Task Allocation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStaffLogin = new JLabel("Staff Login");
		lblStaffLogin.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblStaffLogin.setBounds(127, 11, 99, 35);
		contentPane.add(lblStaffLogin);
		
		textFieldStaffID = new JTextField();
		textFieldStaffID.setBounds(137, 57, 155, 20);
		contentPane.add(textFieldStaffID);
		textFieldStaffID.setColumns(10);
		
		JLabel lblStaffId = new JLabel("Staff ID:");
		lblStaffId.setBounds(64, 61, 46, 14);
		contentPane.add(lblStaffId);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(64, 104, 75, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(loginProcess.validateDetals(textFieldStaffID.getText(), new String(passwordField.getPassword()))){
					System.out.println("Login Successful");
					System.out.println(loginProcess.getLoggedInFirstName());
					openMainMenu();
				} else {
					JOptionPane.showMessageDialog(null, "Login was invalid!", "error", JOptionPane.ERROR_MESSAGE);
				}
			};
		});
		btnLogin.setBounds(202, 140, 89, 23);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(137, 101, 155, 20);
		contentPane.add(passwordField);
	}
	
	private void openMainMenu() {
		frame.setVisible(false);
		mainMenu menuFrame = new mainMenu(loginProcess);
		menuFrame.setVisible(true);		
	}
}
