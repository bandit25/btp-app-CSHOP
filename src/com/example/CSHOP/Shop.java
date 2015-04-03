package com.example.CSHOP;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Shop {
	
	@PrimaryKey
	@Persistent	
	String number;
    @Persistent
	String shopname; 	
	@Persistent
	String shopnumber;
	@Persistent
	String shopdetails;
	@Persistent
	String shopaddress;	
	@Persistent
	String email;	
	@Persistent
	String password;	
		
	public Shop(){
		
	}	
	
	public String getNumber(){
		return number;
	}
	public void setNumber(String Number){
		this.number = Number;
	}
	
	public String getShopName(){
		return shopname;
	}
	public void setShopName(String Shopname){
		this.shopname = Shopname;
	}
	
	public String getShopNumber(){
		return shopnumber;
	}
	public void setShopNumber(String Shopnumber){
		this.shopnumber = Shopnumber;
	}
	
	public String getShopAddress(){
		return shopaddress;
	}
	public void setShopAddress(String Shopaddress){
		this.shopaddress = Shopaddress;
	}
	
	public String getShopDetails(){
		return shopdetails;
	}
	public void setShopDetails(String Shopdetails){
		this.shopdetails = Shopdetails;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String Email){
		this.email = Email;
	}
	
	public String getPassword(){
		return password;
	}	
	public void setPassword(String Password){
		this.password = Password;
	}
		
}
