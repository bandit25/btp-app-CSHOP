package com.example.CSHOP;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CSHOP.Order;
import com.example.CSHOP.OrderAPI;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class OrderHandler extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String number = req.getParameter("user");
		String cursorString = null;
		
		try{
			OrderAPI oapi = new OrderAPI();
			CollectionResponse<Order> orders = oapi.getReceivedOrders(cursorString);
			
			JSONObject json = new JSONObject();
			Gson gson = new Gson();
			String str = gson.toJson(orders.getItems());
			json.put("nextPageToken",orders.getNextPageToken());
			json.put("orderslist", str);
			PrintWriter out= resp.getWriter();
	        out.print(json);
	        resp.addHeader("AUTH", "1");
			
			
		}catch(Exception e){
			throw new ServletException(e);
		}
	}
	//function to handle modification of order
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String shop_id = req.getParameter("user");
		String orderx = req.getParameter("orderid");
		String statex = req.getParameter("state");		
		int state = Integer.parseInt(statex);
		Long orderid = Long.parseLong(orderx);
		
		try{
			OrderAPI oapi = new OrderAPI();
			Order order = oapi.getOrder(orderid);
			order.setState(state);
			Order o = oapi.updateOrder(order);
			if(o!=null){
				
				PrintWriter out= resp.getWriter();
				resp.addHeader("AUTH", "1");
				if(state==1){					
					out.println("Order State: Acknowledged");
				}else if(state==2){					
					out.println("Order State: Delivered");
				}else if(state==3){					
					out.println("Order State: Completed");
				}
	        }else{
	        	PrintWriter out= resp.getWriter();
	            out.println("Error.Please try again");
	            resp.addHeader("AUTH", "0");
			}
			
		}catch(Exception e){
			throw new ServletException(e);
		}
		
	}
		

}
