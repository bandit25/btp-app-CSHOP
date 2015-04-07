function name_validate()
{
	if(document.getElementById('name').value.length==0)
	{
		document.getElementById('err_name').innerHTML="!! PLEASE WRITE YOUR FULL NAME !!";
	}
	else if(!isNaN(document.getElementById('name').value))
	{
		document.getElementById('err_name').innerHTML="!! ONLY ALPHABETS ARE ALLOWED !!";
	}
	else
	{	
		document.getElementById('err_name').innerHTML="";
	}
}

function mobile_validate()
{
	if(document.getElementById('mobile').value.length==0)
	{	
		document.getElementById('err_mobile').innerHTML="!! PLEASE WRITE YOUR PHONE NUMBER !!";
	}
	else if(isNaN(document.getElementById('mobile').value))
	{	
		document.getElementById('err_mobile').innerHTML="!! ONLY NUMBERs ALLOWED !!";
	}
	else if(document.getElementById('mobile').value.length!=10)
	{
		document.getElementById('err_mobile').innerHTML="!! PHONE NUMBER SHOULD BE OF 10 DIGIT  !!";
	}
	else
	{	
		document.getElementById('err_mobile').innerHTML="";
	}	
}


function email_validate()
{

      var x=document.getElementById('email').value;
      var atpos=x.indexOf("@");
      var dotpos=x.lastIndexOf(".");
      if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
      {
		document.getElementById('err_email').innerHTML="!! NOT A VALID E-MAIL  !!";
      }
	  else{
		document.getElementById('err_email').innerHTML="";
	  }
}

function address_validate(){
	if(document.getElementById('address').value.length==0)
	{	
		document.getElementById('err_address').innerHTML="!! PLEASE ENTER YOUR ADDRESS !!";
	}else
	{
		document.getElementById('err_address').innerHTML="";
	}
}

function password_validate()
{
	if(document.getElementById('password').value.length==0)
	{
		document.getElementById('err_password').innerHTML="!! PLEASE ENTER YOUR PASSWORD !!";
	}
	else if(document.getElementById('password').value.length<6)
	{	
		document.getElementById('err_password').innerHTML="!! PASSWORD SHOULD BE ATLEAST 6 DIGITS  !!";
	}
	else
	{
		document.getElementById('err_password').innerHTML="";
	}
}

function repassword_validate()
{
	if(document.getElementById('repassword').value.length==0)
	{
		document.getElementById('err_repassword').innerHTML="!! PLEASE RE-ENTER YOUR PASSWORD !!";
	}
	else if(document.getElementById('password').value!=document.getElementById('repassword').value)
	{
		document.getElementById('err_repassword').innerHTML="!! WRONG PASSWORD ENTERED !!";
	}
	else
	{
		document.getElementById('err_repassword').innerHTML="";
	}
}
