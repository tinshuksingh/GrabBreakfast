<div class="form-group" id="city-div">
  <label for="city">Select City</label>
  <select class="form-control" id="city")
  	onchange="getAreaList();">
    <option>--- City ---</option>
  </select>
</div>
<div class="form-group" id="area-div">
  <label for="area">Area</label>
  <select class="form-control" id="area" disabled onchange="getRestoList()">
  	<option>--- Area ---</option>
  </select>
</div>

<div id="restaurants" class="hide">
	<label for="restList">Restaurants</label>
</div>

<div id="dish-div" class="hide">
	<!--<button onclick="goBack()" class="back">Go Back</button>-->
	<div href="#" class="glyphicon glyphicon-chevron-left" id="back-menu"></div>
	<label for="dishList">Menu </label>
</div>


<script>



function goBack() {
	$("#area-div").removeClass("hide");
	$("#city-div").removeClass("hide");
	$("#restaurants").removeClass("hide");
	 $("#dish-div").addClass("hide");
}
</script>
<!--<#include "food-menu.ftl">-->
<script type="text/javascript" src="/scripts/search-rest.js"></script>