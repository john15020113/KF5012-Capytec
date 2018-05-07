/**
 * 
 * @author Johnathan Evans - 15020113
 *
 */

package capytec;

public class LoginProcess {
	private User user;
	private DatabaseConn dbConn = new DatabaseConn();
	
	// Method to validate login details against the database
	public boolean validateDetals(String staffID, String password) {
		int userID = dbConn.checkUser(staffID, password);
		if (userID != 0) {
			createUser(userID);
			return true;
		} else {
			return false;
		}
		
	}
	
	// Creates a new user for the login
	private void createUser(int id) {
		String[] userDetails = dbConn.getUserDetails(id);
		boolean isAdmin = dbConn.isUserAdmin(id);
		user = new User(id, userDetails[0], userDetails[1], userDetails[2], userDetails[3], userDetails[4], userDetails[5], isAdmin);
	}
	
	// Checks if a user is logged in
	public boolean isUserLoggedIn() {
		if (user.equals(null)) {
			return false;
		} else {
			return true;
		}
	}
	
	// Removes the logged in user
	public void logoutUser() {
		if (user.equals(null)) {
			
		} else {
			user = null;
		}
	}
	
	public int getLoggedInId() {
		return user.getId();
	}
	
	public String getLoggedInFirstName() {
		return user.getFirstName();
	}
	
	public String getLoggedInLastName() {
		return user.getLastName();
	}
	
	public String getLoggedInAddress() {
		return user.getAddress();
	}
	
	public String getLoggedInAddress2() {
		return user.getAddress2();
	}
	
	public String getLoggedInPostcode() {
		return user.getPostcode();
	}
	
	public String getLoggedInContact() {
		return user.getContact();
	}
	
	public boolean getLoggedInAdmin() {
		return user.getAdmin();
	}
}
