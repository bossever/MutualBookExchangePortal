package servlets;

import java.sql.*;
import javax.servlet.*;

import sql.IUserContants;

import java.io.*;

public class UserProfileServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String uName = req.getParameter(IUserContants.COLUMN_USERNAME);
		
		try {
			RequestDispatcher rd = req.getRequestDispatcher("UserProfile.html");
			rd.include(req, res);
			pw.println("<div class=\"container-title\"><h1>Welcome, " + uName + "</h1></div>");
			
			Connection con = DBConnection.getCon();
//			PreparedStatement ps = con.prepareStatement(
//					"");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
