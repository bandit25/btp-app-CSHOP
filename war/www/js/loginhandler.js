$(document).ready(function(){	
	
	
	$("#loginbutton").click(function(){
		
		var number = $("#username").val();
		var password = $("#password").val();
		
		$.post("https://7-dot-btp-app.appspot.com/Login",
			  {number: number , 
			   password: password},
			  function(data,status,xhr){
				  if(xhr.getResponseHeader("AUTH")==1){
					  var json = JSON.parse(data);					  
					  window.localStorage.setItem("user", json.number);
					  window.localStorage.setItem("shopname", json.shopname);
					  window.localStorage.setItem("shopnumber", json.shopnumber);
					  window.localStorage.setItem("shopdetails", json.shopdetails);
					  window.localStorage.setItem("shopaddress", json.shopaddress);
					  window.localStorage.setItem("shopemail", json.email);
					  window.location.href = "home.html";
					  
			      }else{
			    	  alert(data);
			      }
			 }
		);		
	});	
	
	
	$("#forgotpasswordbutton").click(function(){
		
		var user = $("#user").val();	
				$.ajax({  
					type: "POST",  
					url: "https://7-dot-btp-app.appspot.com/ForgotPassword",
					data: {user:user },
					success:function(data,status,xhr){
								if(data){
									alert(data);
								}else{
									alert(data);
								}  
	    					 }					
				}); 						
	});
	
	
});