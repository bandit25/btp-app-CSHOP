package com.example.CSHOP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.jdo.FetchGroup;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

@Api(name = "orderapi", version ="v1", description = "orderapi")
public class OrderAPI {

	/*
	 *This method is used to retrieve all the orders placed by customer 
	*/
	@SuppressWarnings("unchecked")
	@ApiMethod(name = "getPlacedOrders")
	public CollectionResponse<Order> getPlacedOrders(@Named("no")String no,@Nullable @Named("cursor")String cursorString){
		
		Cursor cursor = null;
		PersistenceManager mgr = getPersistenceManager();
		Query q = mgr.newQuery(Order.class);
		q.setFilter("customer_id == noParam");
		q.declareParameters("String noParam");
		q.setOrdering("timestamp desc");
		//q.setRange(0, 7);
		try{
			
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				HashMap<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				q.setExtensions(extensionMap);
		    }
			
			List<Order> orders = (List<Order>)q.execute(no);
			cursor = JDOCursorHelper.getCursor(orders);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();
			
			if(!orders.isEmpty())
				return CollectionResponse.<Order> builder().setItems(orders).setNextPageToken(cursorString).build();				
			else
				return null;
		
		}catch(Exception e){
			
		}finally{
			q.closeAll();
			mgr.close();
		}
		return null;
		
	}
	/*
	 *This method is used to retrieve all the orders received by shopkeeper which are not yet delivered
	*/
	@SuppressWarnings("unchecked")
	@ApiMethod(name = "getReceivedOrders")
	public CollectionResponse<Order> getReceivedOrders(@Nullable @Named("cursor")String cursorString){
		
		Cursor cursor = null;
		PersistenceManager mgr = getPersistenceManager();
		Query q = mgr.newQuery(Order.class);
		q.setFilter("state <= stateParam");
		q.declareParameters("int stateParam");
		q.setOrdering("state asc, timestamp desc");
		//q.setRange(0, 7);
		try{
			
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				HashMap<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				q.setExtensions(extensionMap);
		    }
			List<Order> orders = (List<Order>)q.execute(3); //0= unacknowledged order, 1= acknowledged, 2= dispatched,3 = completed order
			cursor = JDOCursorHelper.getCursor(orders);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();
			
			if(!orders.isEmpty())
				return CollectionResponse.<Order> builder().setItems(orders).setNextPageToken(cursorString).build();				
			else
				return null;				
		}catch(Exception e){
			
		}finally{
			q.closeAll();
			mgr.close();
		}
		return null;
		
	}
	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getOrder")
	public Order getOrder(@Named("id")Long id) {
		PersistenceManager mgr = getPersistenceManager();
		Order order = null;
		try {
			order = mgr.getObjectById(Order.class, id);
		} catch(Exception e){
			
		}finally {
			mgr.close();
		}
		return order;
	}

	
	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param order the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertOrder")
	public Order insertOrder(Order order) {
		PersistenceManager mgr = getPersistenceManager();
		
		try {
			if(order.getId()!=null){
				if (containsOrder(order)) {
				return null;
				}
			}
			mgr.makePersistent(order);			
		} catch (Exception e) {
			   System.out.println(e);			
		} finally {
			mgr.close();
		}
		return order;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param order the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateOrder")
	public Order updateOrder(Order order) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (!containsOrder(order)) {
				return null;
			}
			mgr.makePersistent(order);
		}catch(Exception e){
			
		}finally {
			mgr.close();
		}
		return order;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeOrder")
	public void removeOrder(@Named("id")Long id) {
		PersistenceManager mgr = getPersistenceManager();
		try {			
			Order order = mgr.getObjectById(Order.class, id);
			mgr.deletePersistent(order);
		}catch(Exception e){
			
		} finally {
			mgr.close();
		}
	}

	private boolean containsOrder(Order order) {
		PersistenceManager mgr = getPersistenceManager();
		boolean contains = true;
		try {
			mgr.getObjectById(Order.class, order.getId());
		} catch (javax.jdo.JDOObjectNotFoundException ex) {
			contains = false;
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}

}
