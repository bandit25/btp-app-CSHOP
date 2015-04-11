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
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class Register extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		String number = req.getParameter("number");
		String shopname = req.getParameter("shopname");
		String shopnumber = req.getParameter("shopnumber");
		String shopdetails = req.getParameter("shopdetails");
		String shopaddress = req.getParameter("shopaddress");
		String email = req.getParameter("email");		
	    String password = req.getParameter("password");		
	     
	    
		String hash = null;
		try {
			hash = PasswordHash.createHash(password);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			e1.printStackTrace();
		}
		
		Shop shop = new Shop();		
		shop.setNumber(number);
		shop.setShopName(shopname);
		shop.setShopNumber(shopnumber);
		shop.setShopDetails(shopdetails);
		shop.setShopAddress(shopaddress);
		shop.setEmail(email);
		shop.setPassword(hash);
		
		try{
			ShopAPI sapi = new ShopAPI();
	    	Shop s = sapi.insertShop(shop);
	    	if(s!=null){
	    		JSONObject json = new JSONObject();
	        	json.put("user", s.getNumber());
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
	            out.println("Username Already Exists");
	            resp.addHeader("AUTH", "0");
	        	
	        }
		}catch(JSONException e){
			throw new ServletException(e);
		}
		
	}
	

}
