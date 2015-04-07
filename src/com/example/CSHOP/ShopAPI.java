package com.example.CSHOP;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.example.CSHOP.PMF;
import com.example.CSHOP.Shop;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

@Api(name="shopapi", version ="v1", description = "cshopapi")
public class ShopAPI {
	
	
	@ApiMethod(name ="insertShop")
	public Shop insertShop(Shop c){
		PersistenceManager mgr = getPersistenceManager();
		try {
			if(c.getNumber()!=null){
				if (existsShop(c)) {
					return null;
				}
			}
			mgr.makePersistent(c);
		}finally {
			mgr.close();
		}
		return c;
	}
	
	@ApiMethod(name="getShop")
	public Shop getShop(@Named("no")String no){
		PersistenceManager mgr = getPersistenceManager();
		Shop c = mgr.getObjectById(Shop.class,no);
		try{
			if(!existsShop(c)){
				return null;
			}
		}finally{
			mgr.close();
		}
		return c;
	}
	
	@ApiMethod(name ="updateShop")
	public Shop updateShop(Shop c){
		PersistenceManager mgr = getPersistenceManager();
		try{
			if(!existsShop(c)){
				return null;
			}
			mgr.makePersistent(c);
		}finally{
			mgr.close();
		}
		return c;
	}
	
	@ApiMethod(name = "deleteShop")
	public void deleteShop(@Named("no") String no){
		PersistenceManager mgr = getPersistenceManager();
		try{
			Shop c = mgr.getObjectById(Shop.class, no); 
			mgr.deletePersistent(c);
		}finally{
			mgr.close();
		}
	}
	
	
	
	private boolean existsShop(Shop c){
		PersistenceManager mgr = getPersistenceManager();
		boolean res = true;
		try {
			mgr.getObjectById(Shop.class, c.getNumber());
		} catch (javax.jdo.JDOObjectNotFoundException ex) {
			res = false;
		} finally {
			mgr.close();
		}
		return res;
	}
	
	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}
	
			
}

