package com.example.CSHOP;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.CSHOP.Shop;
import com.example.CSHOP.ShopAPI;
import com.example.CSHOP.PasswordHash;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		 	String number = req.getParameter("number");
		    String password = req.getParameter("password");

		    ShopAPI sapi = new ShopAPI();
		    Shop s = sapi.getShop(number);
		    boolean res = false;
		    
		    try {
				if(s!=null){
					String correctHash = s.getPassword();
					res = PasswordHash.validatePassword(password, correctHash);
				}
				
		    } catch (NoSuchAlgorithmException e1) {
		    	e1.printStackTrace();
		    } catch (InvalidKeySpecException e1) {
		    	e1.printStackTrace();
		    }
		    
		    try{
		    	if(res){
		              
		    		JSONObject json = new JSONObject();
		        	json.put("number", s.getNumber());
		        	json.put("shopname", s.getShopName());
		        	json.put("shopnumber", s.getShopNumber());
		        	json.put("shopdetails", s.getShopDetails());
		        	json.put("shopaddress", s.getShopAddress());
		        	json.put("email", s.getEmail());
		            PrintWriter out= resp.getWriter();
		              out.print(json);
		              resp.addHeader("AUTH", "1");
		        }else{
		        	PrintWriter out= resp.getWriter();
		            out.println("Invalid Username or Password");
		            resp.addHeader("AUTH", "0");
                }        
		       
		    }catch(Exception e){
		        throw new ServletException(e);
		    }		
	}
	
}