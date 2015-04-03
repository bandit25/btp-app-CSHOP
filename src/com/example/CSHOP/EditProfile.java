package com.example.CSHOP;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class EditProfile extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if(req.getParameter("editprofile")!=null){
			
			String number = req.getParameter("number");
			String shopname = req.getParameter("shopname");
			String shopnumber = req.getParameter("shopnumber");
			String shopdetails = req.getParameter("shopdetails");
			String shopaddress = req.getParameter("shopaddress");
			String email = req.getParameter("email");		
		   		    	
			try{
				ShopAPI sapi = new ShopAPI();
		    	Shop shop = sapi.getShop(number);
				shop.setShopName(shopname);
				shop.setShopNumber(shopnumber);
				shop.setShopDetails(shopdetails);
				shop.setShopAddress(shopaddress);
				shop.setEmail(email);
		    	Shop s = sapi.updateShop(shop);
		    	
		    	if(s!=null){
		    		JSONObject json = new JSONObject();
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
		            out.println("No such user exists");
		            resp.addHeader("AUTH", "0");		        	
		        }
		    }catch(Exception e){
				System.out.println(e);
			}
		
		}
		
		else if(req.getParameter("editpassword")!=null){
			
			 String password = req.getParameter("password");
			 String number = req.getParameter("number");
			 
			 String hash = null;
			 try {
					hash = PasswordHash.createHash(password);
			 } catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
			 } catch (InvalidKeySpecException e1) {
					e1.printStackTrace();
			 }
							
				try{				
					ShopAPI sapi = new ShopAPI();
			    	Shop shop = sapi.getShop(number);
					shop.setPassword(hash);
					Shop s = sapi.updateShop(shop);
					
			    	if(s!=null){
			    		
			            PrintWriter out= resp.getWriter();
			            out.println("Password successfully updated");
			            resp.addHeader("AUTH", "1");
			            
			        }else{
			        	PrintWriter out= resp.getWriter();
			            out.println("No such user exists");
			            resp.addHeader("AUTH", "0");
			        	
			        }
			    }catch(Exception e){
					System.out.println(e);
				}
			
		 }
		
	}
	
}
