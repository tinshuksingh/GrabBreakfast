 
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
	      		 $.each(result, function(index) {
	                $("#restList").remove();
	 	   			$("#restaurants").removeClass("hide");
	 	            var $input = $("<div class='list-group' id='restList'></div> ");
	     			$('#restaurants').append($input);
	     			recordFound= true;
     				for (i = 0; i < result.length; i++) {
	     					createField(result[i].restName);
	     			}
	                 
	             });
	      		 if(!recordFound){
	      			 $("#restList").remove();
	      			 var $input = $("<div class='list-group' id='restList'></div>");
	      			 $('#restaurants').append($input);
	      			 createField("No Restaurant Found.");
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
    
    function createField(value) {
      
       	var $input = $("<a href='#' class='list-group-item' id='restName'>"+value+"</a>");
    	$('#restList').append($input);
    }
