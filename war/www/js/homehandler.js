
$(document).ready(function(){
	
	var user = window.localStorage.getItem("user");
	if(user==null){
		window.location.href ="index.html";	
	}else{
			$.ajax({  
				type: "GET",  
				url: "https://7-dot-btp-app.appspot.com/OrderHandler",
				data: {user:user },
				success:function(data,status,xhr){
							if(xhr.getResponseHeader("AUTH")==1){
								var json = JSON.parse(data);
								var cursorstring = json.nextPageToken;
								alert(json.orderslist);
							}else{
								alert("Error.Please Try Again");
							}  
	    				 }					
			}); 						
	}

	$("#logout").click(function(){
		
		  window.localStorage.setItem("user", null);
		  window.localStorage.setItem("shopname", null);
		  window.localStorage.setItem("shopnumber", null);
		  window.localStorage.setItem("shopdetails", null);
		  window.localStorage.setItem("shopaddress", null);
		  window.localStorage.setItem("shopemail", null);
			window.location.href ="index.html";	
		
	});
	
	
	$("#modifyorderbutton").click(function(){
		
		var user = window.localStorage.getItem("user");
			if(user==null){
				window.location.href ="index.html";
			}else{
				
				$.ajax({  
					type: "POST",  
					url: "https://7-dot-btp-app.appspot.com/OrderHandler",
					data: { user: user,
						    orderid: "5147289865682944",
						    state: "1" },
					success:function(data,status,xhr){
						if(xhr.getResponseHeader("AUTH")==1){
									alert(data);
								}else{
									alert(data);
								}  
	    					 }					
				}); 						
			}
	 });

});