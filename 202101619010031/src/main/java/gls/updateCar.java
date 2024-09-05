package gls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateCar
 */
@WebServlet("/updateCar")
public class updateCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateCar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			Integer cid = Integer.parseInt(request.getParameter("cid"));
			String cname = request.getParameter("cname");
			String cbrand = request.getParameter("cbrand");
			Integer cspeed = Integer.parseInt(request.getParameter("cspeed"));
			Integer cprice = Integer.parseInt(request.getParameter("cprice"));
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/kishan","root","admin");
			
			PreparedStatement pst = con.prepareStatement("UPDATE car_details SET cname=?,cbrand=?,cspeed=?,cprice=? WHERE cid=?");
			pst.setString(1, cname);
			pst.setString(2, cbrand);
			pst.setInt(3, cspeed);
			pst.setInt(4, cprice);
			pst.setInt(5, cid);
			pst.executeUpdate();
			
			out.println("<html><body><h1>Data Update In Table Successfully.</h1>");
			out.println("<a href='index.html'>Back To Home</a>");
			out.println("</body></html>");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
