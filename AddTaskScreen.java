package GUIEntry.views;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddTaskScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textTypeofTask;
	private JTextField textDuration;
	private JTextField textImportance;
	private JTextField textFrequency;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTaskScreen frame = new AddTaskScreen();
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
	public AddTaskScreen() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 299, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTypeOfTask = new JLabel("Type of task");
		lblTypeOfTask.setBounds(10, 21, 107, 14);
		contentPane.add(lblTypeOfTask);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(10, 59, 107, 14);
		contentPane.add(lblDuration);
		
		JLabel lblImportance = new JLabel("Importance");
		lblImportance.setBounds(10, 96, 107, 14);
		contentPane.add(lblImportance);
		
		JLabel lblFrequency = new JLabel("Frequency");
		lblFrequency.setBounds(10, 135, 107, 14);
		contentPane.add(lblFrequency);
		
		textTypeofTask = new JTextField();
		textTypeofTask.setBounds(85, 18, 188, 20);
		contentPane.add(textTypeofTask);
		textTypeofTask.setColumns(10);
		
		textDuration = new JTextField();
		textDuration.setColumns(10);
		textDuration.setBounds(85, 56, 188, 20);
		contentPane.add(textDuration);
		
		textImportance = new JTextField();
		textImportance.setColumns(10);
		textImportance.setBounds(85, 93, 188, 20);
		contentPane.add(textImportance);
		
		textFrequency = new JTextField();
		textFrequency.setColumns(10);
		textFrequency.setBounds(85, 132, 188, 20);
		contentPane.add(textFrequency);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////////////
				// CONFIRM BUTTON WRITE TO DATABASE
				////////////
			}
		});
		
		
		
		btnConfirm.setBounds(184, 166, 89, 23);
		contentPane.add(btnConfirm);
	}

}
