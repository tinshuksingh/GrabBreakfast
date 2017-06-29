<ul class="nav navbar-nav navbar-right">
	<li><a href="#" class="glyphicon glyphicon-question-sign" onclick="openNav()">Help</a></li>
	 <#if home>
		<li>	
			<a id="user">${name}</a>
		</li>
	 </#if>
	 
	<li class="dropdown">
		<a href="#" class="dropdown-toggle glyphicon glyphicon-user" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
			<span class="caret"></span>
		</a>
		
		  <#if home>
		   		<ul class="dropdown-menu">
					<li><a href="/">Logout</a></li>
				</ul>
			<#else>
				<ul class="dropdown-menu">
					<li><a href="#loginScreen">Login</a></li>
					<li><a href="#">Sign Up</a></li>
				</ul>
		   </#if>
		
		
	</li>
</ul>