$(document).ready(function(){

	$("#submitbutton").click(function(){
		$.post("Register",
			  {number : $("#number").val() ,
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
					  window.location.replace("/home.html");
			    }else{
				   alert(data);
			    }
			 }
		);
		
	});
});
