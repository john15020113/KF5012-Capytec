package capytec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;


/**
 * 
 * @author Johnathan Evans - 15020113
 * 
 * Required jar files in build path:
 * 		- jBCrypt-0.4.jar
 * 		- sqlite-jdbc-3.21.0.jar
 *
 */

public class DatabaseConn {
	protected Connection connection;
	private String driver = "jdbc:sqlite:";
	
	// Destination of the database file
	private String url = "C:\\Users\\John\\eclipse-workspace\\capytec\\src\\capytec\\capytecDatabase.db";
	
	private String dbURL = driver + url;
	
	// User Details
	
	private boolean connect() {
		try {
			connection = DriverManager.getConnection(dbURL);
			System.err.println("connection successful\n");
		} catch (SQLException sqlex) {
			System.err.println("Connection unsuccessful\n" + sqlex.toString());
			return false;
		} catch (Exception ex) {
			System.err.println(ex.toString());
			return false;
		}
		return true;
	}
	
	private boolean close() {
		try {
			connection.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	private ResultSet queryDatabase (String query) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		return rs;		
	}
	
	// Creates a hash of the plain text password
	private String passwordHash(String password) {
		String salt = BCrypt.gensalt(12);
		String hashedPassword = BCrypt.hashpw(password, salt);
		return(hashedPassword);
	}
	
	// Checks a plain text password against the hashed password
	private boolean passwordCheck(String password, String hashedPassword) {
		boolean passwordValid = false;
		passwordValid = BCrypt.checkpw(password, hashedPassword);
		return passwordValid;
	}
	
	/**
	 * checkUser() - Checks login details against the users in the database
	 * username - Username given as login details
	 * password - Password given as login details
	 * return - int of the user id if the login was valid, 0 is returned if not valid */
	
	public int checkUser (String username, String password) {
		ResultSet result;
		PreparedStatement stmt;
		int validUser = 0;
		if (connect()) {
			try {
				stmt = connection.prepareStatement("SELECT IdStaff, Password FROM UserAccount WHERE Username = ?");
				stmt.setString(1, username);
				result = stmt.executeQuery();
				if (result.next()) {
					if (passwordCheck(password, result.getString(2))) {
						validUser = result.getInt(1);
					}
				}
			} catch (SQLException ex) {
				
			} finally {
				close();
			}
		}
		return validUser;
	}
	
	/**
	 * isUserAdmin() - Checks if a user has admin level
	 * id - Id of the user to check
	 * return - boolean value true if user has admin level false if not */
	
	public boolean isUserAdmin (int id) {
		boolean userIsAdmin = false;
		ResultSet result;
		PreparedStatement stmt;
			if (connect()) {
			try {
				stmt = connection.prepareStatement("SELECT Admin FROM UserAccount WHERE IdStaff = ?");
				stmt.setInt(1, id);
				result = stmt.executeQuery();
				if (result.next()) {
					userIsAdmin = true;
				}
			} catch (SQLException ex) {
				System.err.println(ex.toString());
			} finally {
				close();
			}
		}
		return userIsAdmin;
	}
	
	/**
	 * getUserDetails() - Gets the user details from the database
	 * id - The user id in the database
	 * return - String array of user details: FirstName, LastName, AddressLine1, AddressLine2, Postcode, ContactNumber */
	
	public String[] getUserDetails (int id) {
		ResultSet result;
		PreparedStatement stmt;
		String[] userDetails = new String[1];
		if (connect()) {
			try {
				stmt = connection.prepareStatement("SELECT FirstName, LastName, AddressLine1, AddressLine2, Postcode, ContactNumber FROM Staff WHERE IdStaff = ?");
				stmt.setInt(1, id);
				result = stmt.executeQuery();
				userDetails = new String [] {result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6)};
			} catch (SQLException ex) {
				
			} finally {
				close();
			}
		}
		return userDetails;
	}
	
	/**
	 * createStaff() - Creates a new staff in the database
	 * firstname - The firstname of the staff member
	 * lastname - The lastname of the staff member
	 * addr1 - The address line 1 of the staff member
	 * addr2 - The address line 2 of the staff member
	 * postcode - The postcode of the staff member
	 * contact - The contact number of the staff member
	 * return - boolean value true if record was created and false if not created */
	
	public boolean createStaff(String firstname, String lastname, String addr1, String addr2, String postcode, String contact) {
		int result;
		PreparedStatement stmt;
		if (connect()) {
			try {
				stmt = connection.prepareStatement("INSERT INTO Staff (FirstName, LastName, AddressLine1, AddressLine2, Postcode, ContactNumber) VALUES (?, ?, ?, ?, ?, ?)");
				stmt.setString(1, firstname);
				stmt.setString(2, lastname);
				stmt.setString(3, addr1);
				stmt.setString(4, addr2);
				stmt.setString(5, postcode);
				stmt.setString(6, contact);
				result = stmt.executeUpdate();
				return true;
			} catch (SQLException ex) {
				
			} finally {
				close();
			}
		}
		return false;
	}
	
	/**
	 * createAccount() - Creates a new account in the database
	 * idStaff - The staff id for the new account
	 * username - Username for the new account
	 * password - Password for the new account
	 * admin - true or false if the account has admin level
	 * return - boolean value true if record was created and false if not created */
	
	public boolean createAccount(int idStaff, String username, String password, boolean admin) {
		int result;
		PreparedStatement stmt;
		if (connect()) {
			try {
				stmt = connection.prepareStatement("INSERT INTO UserAccount (IdStaff, Username, Password, Admin) VALUES (?, ?, ?, ?)");
				stmt.setInt(1, idStaff);
				stmt.setString(2, username);
				stmt.setString(3, passwordHash(password));
				stmt.setInt(4, admin ? 1:0);
				result = stmt.executeUpdate();
				return true;
			} catch (SQLException ex) {
				
			} finally {
				close();
			}
		}
		return false;
	}
	
	/**
	 * createTask() - Creates a task in the database
	 * title - Title of the new task
	 * desc - Description of the new task
	 * startDate - Start date of the new task
	 * endDate - End date of the new task
	 * return - boolean value true if record was created and false if not created */
	
	public boolean createTask(String title, String desc, String startDate, String endDate, int typeOfTask, int duration, int importance, int frequency) {
		int result;
		PreparedStatement stmt;
		if (connect()) {
			try {
				stmt = connection.prepareStatement("INSERT INTO Tasks (Title, Description, StartDate, EndDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				stmt.setString(1, title);
				stmt.setString(2, desc);
				stmt.setString(3, startDate);
				stmt.setString(4, endDate);
				stmt.setInt(5, typeOfTask);
				stmt.setInt(6, duration);
				stmt.setInt(7, importance);
				stmt.setInt(8, frequency);
				result = stmt.executeUpdate();
				return true;
			} catch (SQLException ex) {
				System.err.println(ex.toString());
			} finally {
				close();
			}
		}
		return false;
	}
	
	/**
	 * createDailyTask() - Creates a daily task in the database
	 * title - Title of the new daily task
	 * desc - Description of the new  daily task
	 * return - boolean value true if record was created and false if not created */

	public boolean createDailyTask(String title, String desc, int typeOfTask, int duration, int importance, int frequency) {
		int result;
		PreparedStatement stmt;
		if (connect()) {
			try {
				stmt = connection.prepareStatement("INSERT INTO DailyTask (Title, Description) VALUES (?, ?, ?, ?, ?, ?)");
				stmt.setString(1, title);
				stmt.setString(2, desc);
				stmt.setInt(5, typeOfTask);
				stmt.setInt(6, duration);
				stmt.setInt(7, importance);
				stmt.setInt(8, frequency);
				result = stmt.executeUpdate();
				return true;
			} catch (SQLException ex) {
				System.err.println(ex.toString());
			} finally {
				close();
			}
		}
		return false;
	}
	
	/**
	 * createTaskAssignment() - Creates a task assignment in the database
	 * idStaff - The id of the staff linked to the assignment.
	 * idDailyTask - The id of the task
	 * assignData - The assignDate
	 * deadline - The deadline of the task completion
	 * return - boolean value true if record was created and false if not created */
	
	public boolean createTaskAssignment(int idStaff, int idTask, String assignDate, String deadline) {
		int result;
		PreparedStatement stmt;
		if (connect()) {
			try {
				stmt = connection.prepareStatement("INSERT INTO TaskAssignment (IdStaff, IdTask, AssignDate, Deadline) VALUES (?, ?, ?, ?)");
				stmt.setInt(1, idStaff);
				stmt.setInt(2, idTask);
				stmt.setString(3, assignDate);
				stmt.setString(4, deadline);
				result = stmt.executeUpdate();
				return true;
			} catch (SQLException ex) {
				System.err.println(ex.toString());
			} finally {
				close();
			}
		}
		return false;
	}
		
		
	/**
	 * createDailyTaskAssignment() - Creates a daily task assignment in the database
	 * idStaff - The id of the staff linked to the assignment.
	 * idDailyTask - The id of the daily task
	 * assignData - The assignDate
	 * deadline - The deadline of the task completion
	 * return - boolean value true if record was created and false if not created */
	
	public boolean createDailyTaskAssignment(int idStaff, int idDailyTask, String assignDate, String deadline) {
		int result;
		PreparedStatement stmt;
		if (connect()) {
			try {
				stmt = connection.prepareStatement("INSERT INTO DailyTaskAssignment (IdStaff, IdDailyTask, AssignDate, Deadline) VALUES (?, ?, ?, ?)");
				stmt.setInt(1, idStaff);
				stmt.setInt(2, idDailyTask);
				stmt.setString(3, assignDate);
				stmt.setString(4, deadline);
				result = stmt.executeUpdate();
				return true;
			} catch (SQLException ex) {
				System.err.println(ex.toString());
			} finally {
				close();
			}
		}
		return false;
	}
	
	/**
	 * getTask() - Gets a task from the database
	 * taskId - The id of the task to get.
	 * return - String array of the task details: IdTask, Title, Description, StartDate, EndDate
	 * If no record was found the String array will return a null */
		
	public String[] getTask(int taskId) {
		ResultSet result;
		PreparedStatement stmt;
		String[] details = new String[1];
		if (connect()) {
			try {
				stmt = connection.prepareStatement("SELECT IdTask, Title, Description, StartDate, EndDate, TypeOfTask, Duration, Importance, Frequency FROM Tasks WHERE IdTask = ?");
				stmt.setInt(1, taskId);
				result = stmt.executeQuery();
				details = new String [] {result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8), result.getString(9)};
				return details;
			} catch (SQLException ex) {
				System.err.println(ex.toString());
			} finally {
				close();
			}
		}
		return details;
	}
	
	/**
	 * getDailyTask() - Gets a daily task from the database
	 * dailyTaskId - The id of the daily task to get.
	 * return - String array of the daily task details: IdDailyTask, Title, Description 
	 * If no record was found the String array will return a null */
	
	public String[] getDailyTask(int dailyTaskId) {
		ResultSet result;
		PreparedStatement stmt;
		String[] details = new String[1];
		if (connect()) {
			try {
				stmt = connection.prepareStatement("SELECT IdDailyTask, Title, Description, TypeOfTask, Duration, Importance, Frequency FROM DailyTask WHERE IdDailyTask = ?");
				stmt.setInt(1, dailyTaskId);
				result = stmt.executeQuery();
				details = new String [] {result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7)};
				return details;
			} catch (SQLException ex) {
				System.err.println(ex.toString());
			} finally {
				close();
			}
		}
		return details;
	}
	
	/**
	 * getDailyTaskAssignment() - Gets a daily task assignment from the database
	 * idDailyTaskAssignment - The id of the daily task assignment to get.
	 * return - String array of the daily task assignment details: IdDailyTaskAssignment, IdStaff, IdDailyTask, AssignDate, Deadline, Completed 
	 * If no record was found the String array will return a null */
	
	public String[] getDailyTaskAssignment(int idDailyTaskAssignment) {
		ResultSet result;
		PreparedStatement stmt;
		String[] details = new String[1];
		if (connect()) {
			try {
				stmt = connection.prepareStatement("SELECT IdDailyTaskAssignment, IdStaff, IdDailyTask, AssignDate, Deadline, Completed FROM DailyTaskAssignment WHERE IdDailyTaskAssignment = ?");
				stmt.setInt(1, idDailyTaskAssignment);
				result = stmt.executeQuery();
				details = new String [] {result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)};
				return details;
			} catch (SQLException ex) {
				System.err.println(ex.toString());
			} finally {
				close();
			}
		}
		return details;
	}
	
	/**
	 * getTaskAssignment() - Gets a task assignment from the database
	 * idTaskAssignment - The id of the task assignment to get.
	 * return - String array of the task assignment details: IdTaskAssignment, IdStaff, IdTask, AssignDate, Deadline, Completed 
	 * If no record was found the String array will return a null */
	
	public String[] getTaskAssignment(int idTaskAssignment) {
		ResultSet result;
		PreparedStatement stmt;
		String[] details = new String[1];
		if (connect()) {
			try {
				stmt = connection.prepareStatement("SELECT IdTaskAssignment, IdStaff, IdTask, AssignDate, Deadline, Completed FROM TaskAssignment WHERE IdTaskAssignment = ?");
				stmt.setInt(1, idTaskAssignment);
				result = stmt.executeQuery();
				details = new String [] {result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)};
				return details;
			} catch (SQLException ex) {
				System.err.println(ex.toString());
			} finally {
				close();
			}
		}
		return details;
	}
	
}
