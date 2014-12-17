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
	
    
    public AuthFilter() {
         // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
	    try{
		Class.forName("com.mysql.jdbc.Driver");
	    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/user666","root","root");
	    stm=conn.createStatement();
	   rs=stm.executeQuery("Select username from userinfo where username='req.getParameter('Username')'");
	   while(rs.next()){
	   System.out.println(rs.getString("username"));
	   
	   if(rs.getString("role").matches("Administrator"))
	    {
	    	
	    	chain.doFilter(req, res);	
	    }
	    else{
	    	res.getWriter().println("Invalid Username"); 
	    	req.getRequestDispatcher("Welcome.html").include(req, res);
	    	
	    	
	    }
	   }
	    }catch(ClassNotFoundException |SQLException e){
	    	e.printStackTrace();
	    }
		

	}

		public void init(FilterConfig fConfig) throws ServletException {
		  
	}

}
