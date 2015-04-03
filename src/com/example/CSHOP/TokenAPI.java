package com.example.CSHOP;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import com.example.CSHOP.PMF;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

import javax.inject.Named;
import javax.jdo.Extent;
import java.util.Iterator;
import javax.jdo.PersistenceManager;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Api(name = "tokenapi", version ="v1", description = "tokenapi")
public class TokenAPI {

	

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getToken")
	public Token getToken(@Named("id") String id) {
		PersistenceManager mgr = getPersistenceManager();
		Token token = null;
		try {
			token = mgr.getObjectById(Token.class, id);
		} finally {
			mgr.close();
		}
		return token;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param token the entity to be inserted.
	 * @return The tokenid.
	 */
	@ApiMethod(name = "insertToken")
	public Token insertToken(@Named("userId")String userId) {
		PersistenceManager mgr = getPersistenceManager();
		Token token = new Token();
		try {
			
			if(userId!=null){
			}
			UUID uuid = UUID.randomUUID();
		    String tokenid = uuid.toString();
		    Long currenttime = System.currentTimeMillis();
		    Long timer  = currenttime + 24*60*60*1000;    //expiration after 24 hours
			token.setUserID(userId);
			token.setUUID(tokenid);
			token.setTimer(timer);
			mgr.makePersistent(token);
		} finally {
			mgr.close();
		}
		return token;
	}

		
	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeToken")
	public void removeToken(@Named("id") String id) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			Token token = mgr.getObjectById(Token.class, id);
			mgr.deletePersistent(token);
		} finally {
			mgr.close();
		}
	}

	/**
	 * This function sends an email to the user with password reset link.
	 * @param email
	 * @param token
	 */
	@ApiMethod(name = "sendEmail")
	public void sendEmail(@Named("email")String email, @Named("token")String token){
		
		String link = "<a href="+"https://7-dot-btp-app.appspot.com/Reset?token="+token+">" + "https://7-dot-btp-app.appspot.com/reset/"+ token + "</a> ";
		String subject = "RESET Your CSHOP Password";
		String emailBody =  "Dear User,"
							+"<br><br>To reset your password, click this link."
							+"<br>"+ link
							+"<br><br>Please note:"
							+"<br>For security purposes, this is one time usage link and will expire 24 hours from the time it was sent"
							+"<br><br>If you cannot access this link, copy and paste the entire URL into your browser."
							+"<br><br>The CSHOP Team";
				
			try{
			//Step1		1st ===> setup Mail Server Properties
					Properties mailServerProperties = System.getProperties();
					mailServerProperties.put("mail.smtp.ssl.enable", "true");
			 
			//Step2		2nd ===> get Mail Session
					Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
					Message msg = new MimeMessage(getMailSession);
					
					msg.setFrom(new InternetAddress("banditdolphin@gmail.com","admin"));
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
					msg.setSentDate(new Date());
					msg.setSubject(subject);
					msg.setContent(emailBody,"text/html");
					
			 
			//Step3	 3rd ===> Get Session and Send mail
					Transport.send(msg);
			
			}catch(AddressException e){
				e.printStackTrace();
				
			}catch(MessagingException e){
				e.printStackTrace();
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
	} 
	
	private boolean containsToken(Token token) {
		PersistenceManager mgr = getPersistenceManager();
		boolean contains = true;
		try {
			mgr.getObjectById(Token.class, token.getUserID());
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

