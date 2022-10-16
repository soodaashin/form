package com.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.PreparedQuery;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	private static final String INSERT_QUERY = "INSERT INTO  USER (email, password, firstname, lastname, address, pincode, state,  number, feedback) values(?,?,?,?,?,?,?,?,?)";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get Printwriter
		PrintWriter pw  = resp.getWriter();
		
		resp.setContentType("text/html");
		//read form values
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String firstname = req.getParameter("fname");
		String lastname= req.getParameter("lname");
		String Address = req.getParameter("address");
		String  pincode = req.getParameter("pincode");
		String state  = req.getParameter("state");
		String number  = req.getParameter("number");
		String feedback  = req.getParameter("feedback");
		
	
		//load jdbc driver 
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//conection
		
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///firstdb","root", "sood");
				PreparedStatement ps = 	con.prepareStatement(INSERT_QUERY);  ){
			//SET VALUES
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, firstname);
			ps.setString(4, lastname);
			ps.setString(5, Address);
			ps.setString(6, pincode);
			ps.setString(7, state);
			ps.setString(8, number);
			ps.setString(9, feedback);
			
			
			//execute
			
			int count =ps.executeUpdate();
			if(count==0) {
				pw.println("not submitted try again");
			}else {
				pw.println("record stored into database");
			}
			
		}catch (SQLException se) {
			pw.println(se.getMessage());
			se.printStackTrace();	
			// TODO: handle exception
		}catch (Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
			// TODO: handle exception
		}
 		
		//closing
		pw.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
