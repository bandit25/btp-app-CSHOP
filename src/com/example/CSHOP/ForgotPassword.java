package com.example.CSHOP;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ForgotPassword extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try{
			
			String userId = req.getParameter("user");
			ShopAPI sapi = new ShopAPI();
			Shop s = sapi.getShop(userId);
			String email = s.getEmail();
		
			TokenAPI tapi = new TokenAPI();
			Token tokenobj = tapi.insertToken(userId);
			String token = tokenobj.getUUID();
			tapi.sendEmail(email, token);
			PrintWriter out= resp.getWriter();
            out.println("email has been sent");
			
		}catch(Exception e){
			System.out.println(e);
		}	
	}

}
