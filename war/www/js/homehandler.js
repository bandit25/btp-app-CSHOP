
$(document).ready(function(){
	
	var user = window.localStorage.getItem("user");
	
	$.ajax({  
				type: "GET",  
				url: "https://7-dot-btp-app.appspot.com/OrderHandler",
				//url: "OrderHandler",
				data: {user:user },
				success:function(data,status,xhr){
							if(xhr.getResponseHeader("AUTH")==1){
									var json = JSON.parse(data);
									var jsonObj = JSON.parse(json.orderslist);
									//console.log(json.orderslist);
									console.log(jsonObj);
									for(var obj in jsonObj){
										//console.log(jsonObj[obj]);
										var date = new Date(jsonObj[obj].timestamp);
										console.log(date.toString());
										var tabledata = JSON.parse(jsonObj[obj].list);
										var len = tabledata.length;
						                var txt = "";
						                if(len > 0){
						                    for(var i=0;i<len;i++){
						                        if(tabledata[i].Item && tabledata[i].Quantity){
						                            txt += "<tr><td>"+tabledata[i].Item+"</td><td>"+tabledata[i].Quantity+"</td></tr>";
						                        }
						                    }
						                    if(txt != ""){
						                    	console.log(txt);
						                    }
						                }																		
										$("#orderData").append('<div class="row">\
																	<div class="span5">\
																		<h3 align="center"><a data-toggle="modal" data-target="#modal'+jsonObj[obj].id+'">Order '+jsonObj[obj].id+'</a></h3>\
																		<h5 align="left"><a>Ordered By: '+jsonObj[obj].customer_name+'</a></h5>\
																		<h5 align="left"><a>Ordered On:'+date.toString()+'</a></h5>\
																			<label class="btn btn-info">\
																				<input type="checkbox" id="'+jsonObj[obj].id+1+'" name="rad" value="1" /> Acknowledged\
																			</label> \
																			<label class="btn btn-info">\
																				<input type="checkbox" id="'+jsonObj[obj].id+2+'" name="rad" value="2" /> Delivered\
																			</label> \
																			<label class="btn btn-info">\
																				<input type="checkbox" id="'+jsonObj[obj].id+3+'" name="rad" value="3" /> Completed\
																			</label> \
																	</div>\
																</div>\
														<div class="text-center center-block">\
																	<div class="modal fade" id="modal'+jsonObj[obj].id+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">\
																			<div class="modal-dialog">\
																				<div class="modal-content">\
																					<div class="modal-header">\
																						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>\
																						<h4 class="modal-title" id="myModalLabel">Order'+ jsonObj[obj].id +'</h4>\
																					</div>\
																					<div class="modal-body">\
																						<div class="container">\
																							<div class="row">\
																								<div class="text-left">\
																									<address>\
																										<strong>Shipped To:</strong><br>\
																											'+jsonObj[obj].customer_name+'<br>\
																											'+jsonObj[obj].deliveryaddress+'<br>\
																									</address>\
																								</div>\
																							</div>\
																							<div class="row">\
																								<div class="text-left">\
																									<address>\
																										<strong>Mobile Number</strong><br>\
																										'+jsonObj[obj].deliveryno+'<br>\
																									</address>\
																								</div>\
																								<div class="text-left">\
																									<address>\
																										<strong>Order Date:</strong><br>\
																											'+ date.toString()+'<br><br>\
																									</address>\
																								</div>\
																							</div>\
																						</div>\
																						<div class="row">\
																							<div class="col-md-12">\
																								<div class="panel panel-default">\
																									<div class="panel-heading">\
																										<h3 class="panel-title"><strong>Order summary</strong></h3>\
																									</div>\
																									<div class="panel-body">\
																										<div class="table-responsive">\
																											<table class="table table-condensed" id="table">\
																												<thead>\
																													<tr>\
																														<strong><th class="text-center">Item</th></strong>\
																														<strong><th class="text-center">Quantity</th></strong>\
																													</tr>\
																												</thead>\
																												<tbody>\
																													'+txt+'\
																												</tbody>\
																											</table>\
																										</div>\
																									</div>\
																								</div>\
																							</div>\
																						</div>\
																					</div>\
																					<div class="modal-footer">\
																						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>\
																					</div>\
																				</div>\
																			</div>\
																		</div>\
																	</div>'	);
											if(jsonObj[obj].state==0){
												$("#"+jsonObj[obj].id+2).prop("disabled",true);
												$("#"+jsonObj[obj].id+3).prop("disabled",true);
											}else if(jsonObj[obj].state==1){
												$("#"+jsonObj[obj].id+1).prop("checked",true);
												$("#"+jsonObj[obj].id+1).prop("disabled",true);
												$("#"+jsonObj[obj].id+3).prop("disabled",true);
											}else if(jsonObj[obj].state==2){
												$("#"+jsonObj[obj].id+1).prop("checked",true);
												$("#"+jsonObj[obj].id+1).prop("disabled",true);												
												$("#"+jsonObj[obj].id+2).prop("checked",true);
												$("#"+jsonObj[obj].id+2).prop("disabled",true);
											}else if(jsonObj[obj].state==3){
												$("#"+jsonObj[obj].id+1).prop("checked",true);
												$("#"+jsonObj[obj].id+1).prop("disabled",true);												
												$("#"+jsonObj[obj].id+2).prop("checked",true);
												$("#"+jsonObj[obj].id+2).prop("disabled",true);
												$("#"+jsonObj[obj].id+3).prop("checked",true);
												$("#"+jsonObj[obj].id+3).prop("disabled",true);
											}
										
										 }
												
							}else{
								alert("Error.Please Try Again");
							}
				}			
	    									
	}); 						

	$("#logout").click(function(){
		
		  window.localStorage.setItem("user", null);
		  window.localStorage.setItem("shopname", null);
		  window.localStorage.setItem("shopnumber", null);
		  window.localStorage.setItem("shopdetails", null);
		  window.localStorage.setItem("shopaddress", null);
		  window.localStorage.setItem("shopemail", null);
			window.location.href ="index.html";	
		
	});
	
	$("div").on("change","input[type='checkbox'][name='rad']",function(){
			    console.log(this.value);
			    console.log(this.id);
			    var state = this.value;
			    var orderid = (this.id)/10;
	     		
			$.ajax({  
					type: "POST",  
					url: "https://7-dot-btp-app.appspot.com/OrderHandler",
					//url:"OrderHandler",
					data: { user: user,
						    orderid: orderid,
						    state: state },
					success:function(data,status,xhr){
						if(xhr.getResponseHeader("AUTH")==1){								
									alert(data);
								}else{
									alert(data);
								}  
	    					 }					
				});		 						
	 });

});