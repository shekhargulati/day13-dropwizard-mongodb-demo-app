<#-- @ftlvariable name="" type="com.shekhar.reader30.views.IndexView" -->
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="/assets/css/bootstrap.css">
		<style type="text/css">
    	body {
      		padding-top:60px;
      		padding-bottom: 60px;
    	}
  		</style>
	</head>
    <body>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Reader 30</a>
        </div>
        
    </div>
  </div>

	<div class="container">
		<h1>List of Blogs</h1>
        <ul class="list-unstyled">
        <#list getBlogs() as blog>
        	<li>
  				<a href="${blog.getUrl()}" target="_blank">${blog.getTitle()}</a>
  			</li>
		</#list>
		</ul>
	</div>
        
        
    </body>
</html>