<div class="form-group">
  <label for="city">Select City</label>
  <select class="form-control" id="city")
  	onchange="getAreaList();">
    <option>--- City ---</option>
  </select>
</div>
<div class="form-group">
  <label for="area">Area</label>
  <select class="form-control" id="area" disabled onchange="getRestoList()">
  	<option>--- Area ---</option>
  </select>
</div>

<div id="restaurants" class="hide">
	<label for="restList">Restaurants</label>
</div>

<script type="text/javascript" src="/scripts/search-rest.js"></script>