package com.example.CSHOP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@SuppressWarnings("serial")
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable ="true")
public class Order implements Serializable {	
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long id;
	@Persistent
	String customer_id;
	@Persistent
	String customer_name;
	@Persistent
	String list;
	@Persistent
	String deliveryno;
	@Persistent
	String deliveryaddress;
	@Persistent
	int state;
	@Persistent
	Long timestamp;
		
	public Order(){
		
	}
	public Long getId(){
		return id;
	}
	public void setId(Long Id){
		this.id = Id;
	}
	public String getCustomer_id(){
		return customer_id;
	}
	public void setCustomer_id(String Customer_id){
		this.customer_id = Customer_id;
	}
	
	public String getCustomer_name(){
		return customer_name;
	}
	public void setCustomer_name(String Customer_name){
		this.customer_name = Customer_name;
	}
	
	public String getList() {
        return list;
    }
	public void setList(String list) {
        this.list = list;
    }
    
	public String getDeliveryno(){
		return deliveryno;
	}
	public void setDeliveryno(String Deliveryno){
		this.deliveryno = Deliveryno;
	}
	public String getDeliveryaddress(){
		return deliveryaddress;
	}
	public void setDeliveryaddress(String Deliveryaddress){
		this.deliveryaddress = Deliveryaddress;
	}
	public int getState(){
		return state;
	}
	public void setState(int State){
		this.state = State;
	}
	public Long getTimestamp(){
		return timestamp;
	}
	public void setTimestamp(Long Timestamp){
		this.timestamp = Timestamp;
	}
	
}
