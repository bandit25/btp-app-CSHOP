
$(document).ready(function(){

	
	$('#updateprofilebutton').click(function(){
		
		var number = window.localStorage.getItem("user");
		var shopname = $("#shopname").val();
		var shopnumber = $("#shopnumber").val();
		var shopdetails = $("#shopdetails").val();
		var shopaddress = $("#shopaddress").val();
		var email = $("#shopemail").val();
		
		$.post("https://7-dot-btp-app.appspot.com/EditProfile",
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
			   }else{
				   alert(data);
			   }
			 }
			);	
	});
	
	$("#changepasswordbutton").click(function(){
		
		var number = window.localStorage.getItem("user");
		var password = $("#password").val();	
				$.ajax({  
					type: "POST",  
					url: "https://7-dot-btp-app.appspot.com/EditProfile",
					data: {number:number,
						   password: password,
						   state : 1},
					success: function(data,status,xhr){
								if(xhr.getResponseHeader("AUTH")==1){
									alert(data);
								}else{
									alert(data);
								}  
	    					 }					
				}); 						
	});
	
	
});
        		