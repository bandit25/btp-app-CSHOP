$(document).ready(function(){

	$("#registerbutton").click(function(){
		
		if($('#regform')[0].checkValidity()){
			
				$.post("https://7-dot-btp-app.appspot.com/Register",
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
			$('#regform :input:visible[required="required"]').each(function()
					{
					    if(!this.validity.valid)
					    {
					    	
					        $(this).focus();
					        // break
					        //alert($('#password').attr("title"));
					        return false;
					    }
					});
		}	
		
		
	});
	
	
	
	
});
