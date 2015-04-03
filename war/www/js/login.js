$(document).ready(function(){	
	
	
	$("#loginbutton").click(function(){
		$.post("Login",
			  {number: $("#username").val() , 
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
					  window.location.replace("/home.html");
			   }else{
				   alert(data);
			   }
			 }
		);		
	});	
	
	
});