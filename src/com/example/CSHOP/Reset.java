package com.example.CSHOP;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Reset extends HttpServlet {
	
	static PersistenceManager mgr = PMF.get().getPersistenceManager();
	static String id;	
	static Token tokenobj = null;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String token = req.getParameter("token");
		
		Extent<Token> extent = mgr.getExtent(Token.class, true);
		for (Token t : extent) {
			if(t.getUUID().equals(token)){
					tokenobj = t;
			}
		}
		extent.closeAll();
		
		id = tokenobj.getUserID();		
		if(System.currentTimeMillis()<tokenobj.getTimer()){
			
			resp.setContentType("text/html;charset=UTF-8");
			resp.sendRedirect("reset.html");
			
		}else{
				
				resp.setContentType("text/html");
			    PrintWriter out = resp.getWriter();
			    out.println("<html>");
			    out.println("<head>");
			    out.println("<title>Link Expired</title>");
			    out.println("</head>");
			    out.println("<body>");
			    out.println("<h1> Link has been expired</h1>");
			    out.println("<h2> Try again to get a new link</h2>");
			    out.println("</body>");
			    out.println("</html>");
				
			    TokenAPI tapi = new TokenAPI();
				tapi.removeToken(id);
				mgr.close();
			}			
					
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String password = req.getParameter("pwd1");
		
		
		String hash = null;
		try {
			hash = PasswordHash.createHash(password);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			e1.printStackTrace();
		}
		
		
	    
	    ShopAPI sapi = new ShopAPI();
		Shop s = sapi.getShop(id);
		s.setPassword(hash);
		Shop shop = sapi.updateShop(s);
		
		TokenAPI tapi = new TokenAPI();
		tapi.removeToken(id);
		mgr.close();
		
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Successfully Changed password</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1> Password Changed Successfully</h1>");
			out.println("<h2> Please login with your new password</h2>");
			out.println("</body>");
			out.println("</html>");
		
	}
	
	
}
