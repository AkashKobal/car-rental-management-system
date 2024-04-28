package Login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		try{ 
		    Class.forName("com.mysql.jdbc.Driver"); 
		    Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3308/project","root","Akash@123"); 
		    PreparedStatement st = con.prepareStatement("select * from register where email=? and password=?");
		    
		  
		     st.setString(1, email);
		     st.setString(2, password);
		      
		     ResultSet rs = st.executeQuery();
				if (rs.next()) {
					response.setContentType("text/html");
					out.print("Welcome " + email);
					RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
					rd.include(request, response);

					
				} else {
					response.setContentType("text/html");
					out.print("Invalid email or password");
					RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
					rd.include(request, response);

				}
		
		}
		catch (Exception e) {
			System.out.println(e);
			response.setContentType("text/html");
			out.print("Invalid email or password");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.include(request, response);

		}
	}
}


