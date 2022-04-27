package servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.IBookConstants;
import sql.IUserContants;

public class DeleteUserServlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		
		String uid = req.getParameter(IUserContants.COLUMN_USERNAME);
		System.out.println(uid);
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("DELETE FROM " + IUserContants.TABLE_USERS + " WHERE "
					+ IUserContants.COLUMN_USERNAME + "=?");
			ps.setString(1,uid);
			int k = ps.executeUpdate();
			if(k==1)
			{
				RequestDispatcher rd = req.getRequestDispatcher("DeleteUser.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">User Deleted Successfully!<br/></div>");
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("DeleteUser.html");
				pw.println("<div class=\"tab\">Failed to Delete User!</div>");
				rd.include(req, res);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
