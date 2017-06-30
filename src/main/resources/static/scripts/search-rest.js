 
getCityList();
 function getCityList(){
	 $.ajax({
      	type: 'get',
      	url: '/getcities',
      	contentType:'application/json',
      	success: function(result) {
      		 $.each(result, function(index) {
                 createOption(document.getElementById('city'), result[index].cityName, result[index].cityId);
             });
      		 $("#restList").remove();
      	},
      	    
      	error: function (result) {
      		 $("#login-error").removeClass('hide');
    	    }
      	});
 }
 
 function getAreaList(){
	 $("#restList").remove();
	 var cityId=$('#city').val() 
	 $.ajax({
	      	type: 'post',
	      	url: '/getareas',
	      	data: JSON.stringify(cityId),
	      	contentType:'application/json',
	      	success: function(result) {
	     		var recordFound= false;
	      		area.options.length=0;
	      		createOption(document.getElementById('area'), "-- Area --","-- Area --");
	      		 $.each(result, function(index) {
	      			recordFound=true;
	      			 $('#area').prop("disabled", false); 
	                 createOption(document.getElementById('area'), result[index].areaName,result[index].areaId);
	             });
	      		 
	      		 if(!recordFound){
	      			 $('#area').prop("disabled", true); 
	      			 createOption(document.getElementById('area'), "-- Area --","-- Area --");
	      			$("#restaurants").addClass("hide");
	      		 }
	      	},
	      	    
	      	error: function (result) {
	      		 $("#login-error").removeClass('hide');
	    	    }
	      	});
 	}

 
 function getRestoList(){
	 var areaId= $('#area').val();
	 $.ajax({
	      	type: 'post',
	      	url: '/getrestos',
	      	data: JSON.stringify(areaId),
	      	contentType:'application/json',
	      	success: function(result) {
	      		var recordFound= false;
	      		$("#restList").remove();
 	   			$("#restaurants").removeClass("hide");
 	            var $input = $("<div class='list-group' id='restList'></div> ");
     			$('#restaurants').append($input);
     			
	      		 $.each(result, function(index) {
	     			recordFound= true;
	     			createField(result[index].restName,result[index].restId);
	             });
	      		 if(!recordFound){
	      			 $("#restList").remove();
	      			 var $input = $("<div class='list-group' id='restList'></div>");
	      			 $('#restaurants').append($input);
	      			 createField("No Restaurant Found.","");
	      		 }
	      	},
	      	    
	      	error: function (result) {
	      		 $("#login-error").removeClass('hide');
	    	    }
	      	});
 	}
 
    function createOption(ddl, text, value) {
        var opt = document.createElement('option');
        opt.value = value;
        opt.text = text;
        ddl.options.add(opt);
    }
    
    function createField(value,id) {
    	//value=value.toString();
    	//alert(value)
       	var $input = $("<div><a href='#' onclick=showMenu("+id+"); class='list-group-item' id='"+id+"'>"+value+"</a><div>");
    	$('#restList').append($input);
    }
    var totalCount=0;
    function createDishField(value,id,price) {
        
      /* 	var $input = $("<a href='#' class='list-group-item' size='50%' id='"+id+"'>"+value+" : "+price+"/- <span class='badge'>14</span></a>");
    	$('#dishList').append($input);*/
    	
    	var $input = $("<div  id='menu-item' class='menuHover'><a href='#' class='list-group-item'>"+
    			"<h4 class='list-group-item-heading'>"+value+" : "+price+"/- </h4>"+
    			"<p class='list-group-item-text'>...</p>"+
    			"<span class='badge'>"+totalCount+"</span></a></div>");
    	
    	$('#dishList').append($input);
    }
    
    $("#menu-item").click(function() {
    			  totalCount=totalCount+1;
    		  }
   		);
    
    function showMenu(restoId){
    
   	 $.ajax({
   	      	type: 'post',
   	      	url: '/getdishes',
   	      	data: JSON.stringify(restoId),
   	      	contentType:'application/json',
   	      	success: function(result) {
   	      		var recordFound= false;
   	      		
   	      		 $("#area-div").addClass("hide");
   	      		 $("#city-div").addClass("hide");
   	      		 $("#restaurants").addClass("hide");
   	      		 $("#dishList").remove()
     			  var $input = $("<div class='list-group' id='dishList'></div> ");
   	      		 $('#dish-div').append($input);
   	      		 $("#dish-div").removeClass("hide");
   	      		
   	      		 $.each(result, function(index) {
   	      			 recordFound=true;
     				 createDishField(result[index].dishName, result[index].dishId, result[index].dishPrice);
   	             });
   	      		 if(!recordFound){
   	      			 $("#dishList").remove();
   	      			 var $input = $("<div class='list-group' id='dishList'></div>");
   	      			 $('#dish-div').append($input);
   	      			 //createDishField("No Menu Found.","","");
   	      			 
   	      			 
   	      			 var $input = $("<div ><a class='list-group-item'>"+
   	     			"<h4 class='list-group-item-heading'>No Menu Found.</h4>"+
   	     			"</a></div>");
   	      			$('#dishList').append($input);	 
   	      			 
   	      		 }
   	      	},
   	      	    
   	      	error: function (result) {
   	      		 $("#login-error").removeClass('hide');
   	    	    }
   	      	});
    	
    }
    
    $( "#back-menu" ).click(function() {
    	$("#area-div").removeClass("hide");
    	$("#city-div").removeClass("hide");
    	$("#restaurants").removeClass("hide");
    	$("#dish-div").addClass("hide");
    	});

    
