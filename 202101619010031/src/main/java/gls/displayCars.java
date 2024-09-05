package gls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class displayCars
 */
@WebServlet("/displayCars")
public class displayCars extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public displayCars() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/kishan","root","admin");
			
			PreparedStatement pst = con.prepareStatement("SELECT * FROM car_details");
			ResultSet rs = pst.executeQuery();
			
			out.println("<html><body>");
			out.println("<h1>All Cars Data</h1>");
			out.println("<table border=1>");
			out.println("<tr><th>UserId</th><th>Car Name</th><th>Car Brand</th><th>Car Speed</th><th>Car Price</th></tr>");
			while(rs.next()) {
				out.println("<tr><td>"+rs.getString(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td>"+rs.getString(5)+"</td></tr>");
			}
			out.println("</table>");
			out.println("<br><br><a href='index.html'>Back To Home</a>");
			out.println("</body></html>");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
