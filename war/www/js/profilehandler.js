
$(document).ready(function(){

	
	$('#updateprofilebutton').click(function(){
		
		if($('#updateprofileform')[0].checkValidity()){
		
				var number = window.localStorage.getItem("user");
				var shopname = $("#shopname").val();
				var shopnumber = $("#shopnumber").val();
				var shopdetails = $("#shopdetails").val();
				var shopaddress = $("#shopaddress").val();
				var email = $("#shopemail").val();
				
				$.post("https://7-dot-btp-app.appspot.com/EditProfile",
						//"EditProfile",
					   {number: number,
						shopname: shopname,
						shopnumber: shopnumber,
						shopdetails: shopdetails,
						shopaddress: shopaddress,
					    email: email,
						state : 0},
					   function(data,status,xhr){
						  if(xhr.getResponseHeader("AUTH")==1){
							  var json = JSON.parse(data);	
							  window.localStorage.setItem("shopname", json.shopname);
							  window.localStorage.setItem("shopnumber", json.shopnumber);
							  window.localStorage.setItem("shopdetails", json.shopdetails);
							  window.localStorage.setItem("shopaddress", json.shopaddress);
							  window.localStorage.setItem("shopemail", json.email);
							  alert(json.message);
							  window.location.href="profileshop.html";
					   }else{
						   alert(data);
					   }
					 }
					);	
		}else{
			
			event.preventDefault();
			
			$('#updateprofileform :input:visible[required="required"]').each(function(index)
					{
					    if(!this.validity.valid)
					    {
					    	$(this).focus();
					    	
					    	if(index==0){
					    		 $("#err_name").show();
						    	 $("#err_shopnumber").hide();
						    	 $("#err_shopdetails").hide();
						    	 $("#err_shopaddress").hide();
						    	 $("#err_shopemail").hide();
						    	
					    	}else if(index==1){
					    		 $("#err_name").hide();
						    	 $("#err_shopnumber").show();
						    	 $("#err_shopdetails").hide();
						    	 $("#err_shopaddress").hide();
						    	 $("#err_shopemail").hide();
						    	 						    	
					    	}else if(index==2){
					    		 $("#err_name").hide();						    	
						    	 $("#err_shopnumber").hide();
						    	 $("#err_shopdetails").show();
						    	 $("#err_shopaddress").hide();
						    	 $("#err_shopemail").hide();
						    	
					    	}else if(index==3){
					    		 $("#err_name").hide();						    	
						    	 $("#err_shopnumber").hide();
						    	 $("#err_shopdetails").hide();
						    	 $("#err_shopaddress").show();
						    	 $("#err_shopemail").hide();
						    	 					    		
					    	}else if(index==4){
					    		 $("#err_name").hide();						    	 
						    	 $("#err_shopnumber").hide();
						    	 $("#err_shopdetails").hide();
						    	 $("#err_shopaddress").hide();
						    	 $("#err_shopemail").show();
						    						    		
					    	}	        
					        
					        return false;
					    }
					    
					});
			
			
			
			
			
			
		}
				
				
	});
	
	$("#changepasswordbutton").click(function(){
		
		if($('#updatepassform')[0].checkValidity()){
		
				var number = window.localStorage.getItem("user");
				var password = $("#password").val();	
						$.ajax({  
							type: "POST",  
							url: "https://7-dot-btp-app.appspot.com/EditProfile",
							//url:"EditProfile",
							data: {number:number,
								   password: password,
								   state : 1},
							success: function(data,status,xhr){
										if(xhr.getResponseHeader("AUTH")==1){
											alert(data);
											window.location.href="profileshop.html";
										}else{
											alert(data);
										}  
			    					 }					
						}); 	
						
		}else{
			
			event.preventDefault();
			
			$('#updatepassform :input:visible[required="required"]').each(function(index)
					{
					    if(!this.validity.valid)
					    {
					    	$(this).focus();
					    	
					    	if(index==0){
					    		 $("#err_password").show();
						    	 $("#err_repassword").hide();
						    	
					    	}else if(index==1){
					    		 $("#err_password").hide();
						    	 $("#err_repassword").show();	
					    	}        
					        
					        return false;
					    }
					    
					});			
		}
		
	});
	
	
});
        		