package misservlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class LoginUsr extends HttpServlet{
	
	private Hashtable logins = new Hashtable();
	private String user;
	private String pass;
	private boolean okUser = false;
	private boolean okAdmin = false;
	
	public void init (ServletConfig config) throws ServletException{
		logins.put("user", config.getInitParameter("user"));
		logins.put("userPass", config.getInitParameter("userPass"));
		logins.put("admin", config.getInitParameter("admin"));
		logins.put("adminPass", config.getInitParameter("adminPass"));
	}
	
		
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws 
		ServletException, IOException {
		
		user=req.getParameter("user");
		pass=req.getParameter("pass");
		
				
		if(logins.containsKey("user")){
			if (logins.get("user").equals(user)){
				if (logins.get("userPass").equals(pass)){
					okUser = true;
				}
			}
			else{
				if (logins.get("admin").equals(user)){
					if(logins.get("adminPass").equals(pass)){
						okAdmin = true;
					}
				}
			}
		}

		
		if (okUser){
			res.sendRedirect("/JRuteros/userView.html");
		}
		else{
			if(okAdmin){
				res.sendRedirect("/JRuteros/adminView.html");
			}
			else{
				res.sendRedirect("/JRuteros/login.html");
			}
		}
		
	}

}


