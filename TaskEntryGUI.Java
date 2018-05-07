package GUIEntry.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.sql.*;
import javax.swing.*;

public class TaskEntryGUI extends JFrame 
{
	private JPanel contentPane;
	private JTable tblAssignedTasks;
    private JTable tblAvailableTasks;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 
				{
					TaskEntryGUI frame = new TaskEntryGUI();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	
	
	/**
	 * Create the frame.
	 */
	public TaskEntryGUI()
	{
		connection=DatabaseConn.getConnection();
		initComponents();
		createEvents();
		
	}
	
	
	
    /////////////////////////////////////////
	//Method Containing code for creating events
	/////////////////////////////////////
	private void createEvents() {
		
	
		// TODO Auto-generated method stub
		
	}
	
	/////////////////////////////////////////
	//Method Containing code for initialising components
	/////////////////////////////////////
	private void initComponents() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 613, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnAddNewTask = new JButton("Add New Task");
		btnAddNewTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTaskScreen taskscreen=new AddTaskScreen();
				taskscreen.setVisible(true);
			}
		});
		
		JButton btnEditTask = new JButton("Edit Task");
		btnEditTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		tblAssignedTasks = new JTable();
		
		JButton btnAssignTask = new JButton("Assign Task");
		btnAssignTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton = new JButton("New button");
		
		JButton btnNewButton_1 = new JButton("New button");
		
		JLabel lblAvailableTasks = new JLabel("Available Tasks");
		
		JLabel lblAssignedTasks = new JLabel("Assigned Tasks");
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					String queryDatabase="select * from Tasks";
					PreparedStatement pst=connection.prepareStatement(queryDatabase);
					ResultSet rs=pst.executeQuery();
					tblAvailableTasks.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		tblAvailableTasks = new JTable();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblAvailableTasks, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLoad)
					.addPreferredGap(ComponentPlacement.RELATED, 326, Short.MAX_VALUE)
					.addComponent(lblAssignedTasks, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(2)
					.addComponent(tblAvailableTasks, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAssignTask, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(tblAssignedTasks, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
					.addGap(5))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(btnAddNewTask, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnEditTask, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(5))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAvailableTasks)
						.addComponent(lblAssignedTasks)
						.addComponent(btnLoad))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(122)
							.addComponent(btnAssignTask)
							.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(tblAssignedTasks, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
							.addComponent(tblAvailableTasks, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnAddNewTask)
						.addComponent(btnEditTask)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
