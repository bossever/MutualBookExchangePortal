package servlets;

import javax.servlet.*;
import constants.IOnlineBookStoreConstants;
import sql.IUserContants;

import java.io.*;
import java.sql.*;

public class UserListServlet extends GenericServlet{

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("Select * from " + IUserContants.TABLE_USERS + " WHERE  "
					+ IUserContants.COLUMN_USERTYPE + "=2");
			ResultSet rs = ps.executeQuery();
			RequestDispatcher rd = req.getRequestDispatcher("UserList.html");
			rd.include(req, res);
			pw.println("<div class=\"tab\">Users Available</div>");
			
			pw.println("<div class=\"tab\">\r\n" + 
					"		<table>\r\n" + 
					"			<tr>\r\n" + 
					"				\r\n" + 
					"				<th>User ID</th>\r\n" + 
					"				<th>Name</th>\r\n" + 
					"				<th>Action</th>\r\n" + 
					"			</tr>");
			while(rs.next())
			{
				pw.println("<div class=\"tab\"><form action=\"delete\" method=\"post\">");
				String username = rs.getString(1); //contains the user-id
				String name = rs.getString(3);
				pw.println("<tr><td>"+username+"</td>");
				pw.println("<td>"+name+"</td>");
				pw.println("<td>"+"<input type=\"submit\" value=\" Delete \">"+"</td>");
				pw.println("<input type=\"hidden\" name=\"username\" value=" + username + ">");
				pw.println("</form");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	

}
