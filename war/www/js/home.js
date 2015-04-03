
$(document).ready(function(){

	$("#logoutbutton").click(function(){
		
		var user = window.localStorage.getItem("user");
		if(user==null){
			alert("Login first");
		}else{		
			window.localStorage.setItem("user", null);
			window.localStorage.clear();
			window.location.replace("/index.html");	
		}
		
	});
	
	$("#forgotpasswordbutton").click(function(){
		
		var user = window.localStorage.getItem("user");
			if(user==null){
				alert("Login first");
			}else{
				
				$.ajax({  
					type: "POST",  
					url: "ForgotPassword",
					data: {user:user },
					success:function(data,status,xhr){
								if(data){
									alert(data);
								}else{
									alert(data);
								}  
	    					 }					
				}); 						
			}
	 });
	
	$("#getordersbutton").click(function(){
		
		var user = window.localStorage.getItem("user");
			if(user==null){
				alert("Login first");
			}else{
				
				$.ajax({  
					type: "GET",  
					url: "OrderHandler",
					data: {user:user },
					success:function(data,status,xhr){
								if(xhr.getResponseHeader("AUTH")==1){
									var json = JSON.parse(data);
									var cursorstring = json.nextPageToken;
									alert(json.orderlist);
								}else{
									alert("error");
								}  
	    					 }					
				}); 						
			}
	 });
	
	$("#modifyorderbutton").click(function(){
		
		var user = window.localStorage.getItem("user");
			if(user==null){
				alert("Login first");
			}else{
				
				$.ajax({  
					type: "POST",  
					url: "OrderHandler",
					data: { user: user,
						    orderid: "121333112121212121",
						    state: "1" },
					success:function(data,status,xhr){
								if(data){
									alert(data);
								}else{
									alert(data);
								}  
	    					 }					
				}); 						
			}
	 });

});