<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
	<head>
	
		<title>My Home page</title>
		<#include "css.ftl">
		<#include "scripts.ftl">
	</head>
	<!--<div class="container" style="background-image: url('/icons/food3.jpg');">-->
	<body bgcolor="grey" >
	<div class="container" style="background-image: url('/icons/fruit_vegetables_fish_garnish_lemon_food_.jpg');">
		<#include "header.ftl">
	  	<#include "help-overlay.ftl">
	  	<#include "login-overlay.ftl">
	  	 <#if !home>
	  	   <#include "slides.ftl">
	  	 <#else>
	  	 	<#include "locations.ftl">
	  	</#if>
	 </div>
	</body>
	<!--</div>-->

	<footer>
		<#include "footer.ftl">
			<script type="text/javascript" src="/scripts/grab-breakfast.js"></script>
	 </footer>
</html>