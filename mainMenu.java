package capytec;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Button;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class mainMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public LoginProcess loggedInUser;
	 
	 /**
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loggedInUser = new LoginProcess();
					mainMenu frame = new mainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public mainMenu(LoginProcess user) {
		loggedInUser = user;
		
		setTitle("Capytec Task Allocation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 402, 84);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome: ");
		lblWelcome.setBounds(10, 11, 68, 14);
		panel.add(lblWelcome);
		
		JLabel lblJohnEvans = new JLabel(loggedInUser.getLoggedInFirstName() + " " + loggedInUser.getLoggedInLastName());
		lblJohnEvans.setBounds(77, 11, 162, 14);
		panel.add(lblJohnEvans);
		
		textField = new JTextField();
		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date today = Calendar.getInstance().getTime(); 
		textField.setText(formatter.format(today));
		textField.setEditable(false);
		textField.setBounds(261, 8, 131, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("You currently have 3 uncompleted tasks today!");
		lblNewLabel.setBounds(68, 47, 324, 14);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(422, 11, 334, 84);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblStaffId = new JLabel("Staff ID:");
		lblStaffId.setBounds(30, 22, 46, 14);
		panel_1.add(lblStaffId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(86, 19, 86, 20);
		panel_1.add(textField_1);
		textField_1.setEditable(false);
		textField_1.setText("15020113");
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChangePassword accountMan = new ChangePassword(loggedInUser.getLoggedInId());
				accountMan.setVisible(true);
			}
		});
		btnNewButton.setBounds(135, 50, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(235, 50, 89, 23);
		panel_1.add(btnNewButton_1);
		
		if (loggedInUser.getLoggedInAdmin()) {
			JButton btnAdmin = new JButton("Admin");
			btnAdmin.setBounds(23, 50, 89, 23);
			btnAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AdminPanel adminPanel = new AdminPanel();
					adminPanel.setVisible(true);
				}
			});
			panel_1.add(btnAdmin);
		}
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.setBounds(10, 106, 362, 374);
		contentPane.add(tabbedPane);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Task Information", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(153, 5, 2, 2);
		panel_3.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Allocated Task 4");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 11, 119, 22);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblTaskDescription = new JLabel("Task description:");
		lblTaskDescription.setBounds(20, 47, 119, 14);
		panel_3.add(lblTaskDescription);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(SystemColor.info);
		textPane.setBounds(10, 72, 289, 102);
		panel_3.add(textPane);
		
		JLabel lblDateAllocated = new JLabel("Date allocated:");
		lblDateAllocated.setBounds(20, 198, 88, 14);
		panel_3.add(lblDateAllocated);
		
		textField_2 = new JTextField();
		textField_2.setBounds(118, 195, 181, 20);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Date due:");
		lblNewLabel_2.setBounds(20, 235, 74, 14);
		panel_3.add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(118, 232, 181, 20);
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Allocated by:");
		lblNewLabel_3.setBounds(20, 272, 74, 14);
		panel_3.add(lblNewLabel_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(118, 269, 181, 20);
		panel_3.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnRemove = new JButton("Remove Task");
		btnRemove.setBounds(20, 310, 119, 23);
		panel_3.add(btnRemove);
		
		JButton btnMarkCompleted = new JButton("Mark Completed");
		btnMarkCompleted.setBounds(170, 310, 129, 23);
		panel_3.add(btnMarkCompleted);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Daily Tasks", null, panel_4, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Task Allocation", null, panel_5, null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(382, 106, 374, 374);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(10, 33, 354, 330);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Allocated Task 1", "Allocated Task 2", "Allocated Task 3", "Allocated Task 4"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panel_2.add(list);
		
		JLabel lblAllocatedTasks = new JLabel("Allocated Tasks:");
		lblAllocatedTasks.setBounds(10, 8, 137, 14);
		panel_2.add(lblAllocatedTasks);
	}
}
