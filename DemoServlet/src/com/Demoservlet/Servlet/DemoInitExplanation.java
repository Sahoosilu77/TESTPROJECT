package com.Demoservlet.Servlet;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
public class DemoInitExplanation extends HttpServlet{
	private String usr,pwd;
	public void init(ServletConfig cg)
	{
		
		this.usr=cg.getInitParameter("User");
		this.pwd=cg.getInitParameter("password");
	}
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		System.out.println(usr+"---->"+pwd);
	}
	public void destroy()
	{
		
	}
}
