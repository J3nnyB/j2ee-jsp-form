/*
	Name: Jennifer Byrne
	Date: February 23, 2020
	Notes: ENTD481 Week 3 Assignment - Servlet configured to take customer data from index.html and insert it
		   into the customer.contact table in mySQL. 
*/
//Package statement
package week3jsp;

// Import the following Java libraries
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/week3jsp.ContactListServlet")
// Class declaration
public class ContactListServlet extends HttpServlet {

	// Was required to prevent warning in Eclipse - not sure the purpose yet
	// Used default value
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) 
						  throws ServletException, IOException {
		// Defines the url
		String url = "/index.html";
		
		// Defines the action object from the parameter passed in the hidden input type on index.html
		String action = request.getParameter("action");
		// If action is set to null, then action will be set to contact
		if (action == null) {
			action = "contact";
		}
		// If action is set to contact, then the url will be set to index.html
		if (action.contentEquals("contact")) {
			url = "/index.html";
		}
		// If action is set to add, then get passed parameters
		else if (action.contentEquals("add")) {
			String customerid = request.getParameter("customerId");		// Object for customerId
			int customerId = Integer.parseInt(customerid);				// customerId changed to integer
			String fName = request.getParameter("fName");				// Object for first name
			String lName = request.getParameter("lName");				// Object for last name
			String email = request.getParameter("email");				// Object for email address
			String pHone = request.getParameter("phone");				// Object for phone number
			int phone = Integer.parseInt(pHone); 						// phone changed to integer
			
			// Try statement
			try {
				// Establishes connection to my database
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customers", "root", "Rhys1116!!");
				
				// Creates a statement object
				Statement myStmt = myConn.createStatement();
				// Executes a query for contact table
				ResultSet myRS = myStmt.executeQuery("select * from contact");
				myRS.moveToInsertRow();							// Moves the cursor to new insert row
				myRS.updateInt("customerId", customerId);		// Updates customerId column with passed parameter
				myRS.updateString("fName", fName);				// Updates fName column with passed parameter
				myRS.updateString("lName", lName);				// Updates lName column with passed parameter
				myRS.updateString("email", email);				// Updates email column with passed parameter
				myRS.updateInt("phone", phone);					// Updates phone column with passed parameter
				myRS.insertRow();								// Adds values to a row in contact table
				// Updates url
				url = "/thanks.jsp";
			}
			// Catch block
			catch (Exception e){
					
				e.printStackTrace();

			}		
			
		}
		
		getServletContext()
			.getRequestDispatcher(url)
			.forward(request, response);
	}
	// doGet method - if called then it calls the doPost method
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
						 throws ServletException, IOException {
		doPost(request, response);
	}

}
