$(document).ready(function() {
    var panels = $('.user-infos');
    var panelsButton = $('.dropdown-user');
    panels.hide();

    //Click dropdown
    panelsButton.click(function() {
        //get data-for attribute
        var dataFor = $(this).attr('data-for');
        var idFor = $(dataFor);

        //current button
        var currentButton = $(this);
        idFor.slideToggle(400, function() {
            //Completed slidetoggle
            if(idFor.is(':visible'))
            {
                currentButton.html('<i class="glyphicon glyphicon-chevron-up text-muted"></i>');
            }
            else
            {
                currentButton.html('<i class="glyphicon glyphicon-chevron-down text-muted"></i>');
            }
        })
    });


    $('[data-toggle="tooltip"]').tooltip();

    $('button').click(function(e) {
        e.preventDefault();
        
    });
});
var local = window.localStorage;
document.getElementById("title").innerHTML = window.localStorage.getItem("shopname");
document.getElementById("user_no").innerHTML = window.localStorage.getItem("user");
document.getElementById("shop_name").innerHTML = window.localStorage.getItem("shopname");
document.getElementById("shop_number").innerHTML = window.localStorage.getItem("shopnumber");
document.getElementById("shop_details").innerHTML = window.localStorage.getItem("shopdetails");
document.getElementById("shop_address").innerHTML = window.localStorage.getItem("shopaddress");
document.getElementById("shop_email").innerHTML = window.localStorage.getItem("shopemail");
