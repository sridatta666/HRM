package com.hrm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class AuthFilter implements Filter {
    
	Connection conn;
	Statement stm;
	ResultSet rs;
	FilterConfig fConfig;
    
    public AuthFilter() {
         // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
	    try{
		Class.forName("com.mysql.jdbc.Driver");
	    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/user666","root","");
	    stm=conn.createStatement();
		rs=stm.executeQuery("Select * from userinfo where username='"+req.getParameter("Username")+"'");

	   
		   if(rs.next()){
	   if(rs.getString("password").matches(req.getParameter("Password")))
	    {	
		   if(rs.getString("role").matches("Administrator"))
		   {
	    	chain.doFilter(req, res);	
		   }
		   else
		   {
			   ////for other roles
			req.getRequestDispatcher("Welcome.html");
		   }
		}
	    else{
	    	res.getWriter().println("Invalid Credentials");  	
	    	req.getRequestDispatcher("Welcome.html").include(req, res);    
	    }
	  
		   
		   }
		   
		   else{
	   	   
		   res.getWriter().println("No employee with this username!!");
		   req.getRequestDispatcher("Welcome.html").include(req, res);
		   }
	    }catch(ClassNotFoundException |SQLException e){
	    	e.printStackTrace();
	    }
	    

	}

		public void init(FilterConfig fConfig) throws ServletException {
		  this.fConfig=fConfig;
	}

}
