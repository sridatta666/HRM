package com.hrm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
	
    
    public AuthFilter() {
         // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
	    try{
		Class.forName("com.jdbc.mysql.Driver");
	    conn=DriverManager.getConnection("http://localhost:3306/asd","root","root");
	    stm=conn.createStatement();
	    if(stm.execute("Select Username")){
	    	
	    	
	    }
	    }catch(ClassNotFoundException |SQLException e){
	    	e.printStackTrace();
	    }
		chain.doFilter(request, response);

	}

		public void init(FilterConfig fConfig) throws ServletException {
		  
	}

}
