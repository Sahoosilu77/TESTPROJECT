package com.RegistrationFromPooling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/registrationForm")
public class RegistrationForm extends HttpServlet {
	Connection con=null;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PreparedStatement ps=null;
		//get PrintWriter Object..
		PrintWriter pw=null;
		pw=res.getWriter();
		//SetContent Type..
		res.setContentType("text/html");
		//Read input from Html File;
		String UserName=req.getParameter("uname");
		String Usermail=req.getParameter("Uemail");
		String UserMob=req.getParameter("Umob");
		String UserAddress=req.getParameter("Uadd");
		try 
		{
		//getConnection Object From Database
		con=makeConnection();
		//prepare Statement..
		ps=con.prepareStatement("insert into RegisterData values(?,?,?,?)");
		ps.setString(1, UserName);
		ps.setString(2, Usermail);
		ps.setString(3, UserMob);
		ps.setString(4, UserAddress);
		ps.executeUpdate();
		pw.println("Registration SuccessFull");
		con.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}
	
	//get pooledConnection from underlying Server Managed...
	private Connection makeConnection() 
	{
		DataSource ds=null;
		InitialContext ic=null;
		try 
		{
			//locate JNDI Registry
			ic=new InitialContext();  
			//Get DataSource Object From JNDI Registry
			ds=(DataSource)ic.lookup("java:comp/env/DsJndi");
			
			con=ds.getConnection();
		} // try
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return con;
		
	}//make Connection

}
