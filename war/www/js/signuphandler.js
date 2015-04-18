$(document).ready(function(){

	$("#registerbutton").click(function(){
		
		if($('#regform')[0].checkValidity()){
			
				$.post("https://7-dot-btp-app.appspot.com/Register",
						//"Register",
					  {number : $("#mobile").val() ,
					   shopname : $("#shopname").val() ,
					   shopnumber: $("#shopnumber").val() ,
					   shopdetails: $("#shopdetails").val() ,
					   shopaddress: $("#shopaddress").val() ,			   
					   email : $("#email").val() ,
					   password: $("#password").val() },
					  function(data,status,xhr){
						  if(xhr.getResponseHeader("AUTH")==1){
							  var json = JSON.parse(data);					  
							  window.localStorage.setItem("user", json.number);
							  window.localStorage.setItem("shopname", json.shopname);
							  window.localStorage.setItem("shopnumber", json.shopnumber);
							  window.localStorage.setItem("shopdetails", json.shopdetails);
							  window.localStorage.setItem("shopaddress", json.shopaddress);
							  window.localStorage.setItem("shopemail", json.email);
							  window.location.href= "home.html";
					    }else{
						   alert(data);
					    }
					 }
				);
				
		}else{
			event.preventDefault();
			$('#regform :input:visible[required="required"]').each(function(index)
					{
					    if(!this.validity.valid)
					    {
					    	$(this).focus();
					    	
					    	if(index==0){
					    		 $("#err_name").show();
					    		 $("#err_mobile").hide();
						    	 $("#err_shopnumber").hide();
						    	 $("#err_shopdetails").hide();
						    	 $("#err_shopaddress").hide();
						    	 $("#err_shopemail").hide();
						    	 $("#err_password").hide();
						    	 $("#err_repassword").hide();
					    	}else if(index==1){
					    		 $("#err_name").hide();
						    	 $("#err_mobile").show();
						    	 $("#err_shopnumber").hide();
						    	 $("#err_shopdetails").hide();
						    	 $("#err_shopaddress").hide();
						    	 $("#err_shopemail").hide();
						    	 $("#err_password").hide();
						    	 $("#err_repassword").hide();
						    	
					    	}else if(index==2){
					    		 $("#err_name").hide();
						    	 $("#err_mobile").hide();
						    	 $("#err_shopnumber").show();
						    	 $("#err_shopdetails").hide();
						    	 $("#err_shopaddress").hide();
						    	 $("#err_shopemail").hide();
						    	 $("#err_password").hide();
						    	 $("#err_repassword").hide();
					    	}else if(index==3){
					    		 $("#err_name").hide();
						    	 $("#err_mobile").hide();
						    	 $("#err_shopnumber").hide();
						    	 $("#err_shopdetails").show();
						    	 $("#err_shopaddress").hide();
						    	 $("#err_shopemail").hide();
						    	 $("#err_password").hide();
						    	 $("#err_repassword").hide();
					    		
					    	}else if(index==4){
					    		 $("#err_name").hide();
						    	 $("#err_mobile").hide();
						    	 $("#err_shopnumber").hide();
						    	 $("#err_shopdetails").hide();
						    	 $("#err_shopaddress").show();
						    	 $("#err_shopemail").hide();
						    	 $("#err_password").hide();
						    	 $("#err_repassword").hide();
					    		
					    	}else if(index==5){
					    		 $("#err_name").hide();
						    	 $("#err_mobile").hide();
						    	 $("#err_shopnumber").hide();
						    	 $("#err_shopdetails").hide();
						    	 $("#err_shopaddress").hide();
						    	 $("#err_shopemail").show();
						    	 $("#err_password").hide();
						    	 $("#err_repassword").hide();
					    		
					    	}else if(index==6){
					    		 $("#err_name").hide();
						    	 $("#err_mobile").hide();
						    	 $("#err_shopnumber").hide();
						    	 $("#err_shopdetails").hide();
						    	 $("#err_shopaddress").hide();
						    	 $("#err_shopemail").hide();
						    	 $("#err_password").show();
						    	 $("#err_repassword").hide();
					    		
					    	}else if(index==7){
					    		 $("#err_name").hide();
						    	 $("#err_mobile").hide();
						    	 $("#err_shopnumber").hide();
						    	 $("#err_shopdetails").hide();
						    	 $("#err_shopaddress").hide();
						    	 $("#err_shopemail").hide();
						    	 $("#err_password").hide();
						    	 $("#err_repassword").show();
					    	}					        
					        
					        return false;
					    }
					    
					});
		}	
		
		
	});
	
	
	
	
});
