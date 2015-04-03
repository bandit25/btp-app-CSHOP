package com.example.CSHOP;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Token {
	
	@PrimaryKey
	@Persistent	
	String userid;
	@Persistent
	String uuid;
	@Persistent
	Long timer;
	
	public Token(){
		
	}

	public String getUserID(){
		return userid;
	}	
	public void setUserID(String userId){
		this.userid = userId;
	}
	
	public String getUUID(){
		return uuid;
	}	
	public void setUUID(String uuId){
		this.uuid = uuId;
	}
	
	public Long getTimer(){
		return timer;
	}	
	public void setTimer(Long Timer){
		this.timer = Timer;
	}
	
}
