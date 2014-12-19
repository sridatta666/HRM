package com.hrm;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



public class LoginFilter implements Filter {

    FilterConfig fConfig=null;
    
    public LoginFilter() {
    
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {  
		String Username=req.getParameter("Username");
		
		if(Username.isEmpty()==false & req.getParameter("Password").isEmpty()==false){
				chain.doFilter(req, res);
				
		}
		else{
			res.getWriter().println("Sorry !!! Please Enter the credentials again.");
			req.getRequestDispatcher("Welcome.html").include(req, res);
		    
		}	
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	this.fConfig=fConfig;	
	}

}
