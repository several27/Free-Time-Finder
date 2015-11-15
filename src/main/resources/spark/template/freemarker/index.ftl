<!DOCTYPE html>
<html>
<head>
	<#include "header.ftl">
</head>

<body>

	<#include "nav.ftl">

	<div class="jumbotron text-center">
	    <div class="container">
	        <#--<a href="/" class="lang-logo">-->
	            <#--<img src="/lang-logo.png">-->
	        <#--</a>-->

	        <h1>Free Time Finder</h1>
		    <h2>For Societies</h2>

	        <#--<p></p>-->

	        <#--<a type="button" class="btn btn-lg btn-default" href="/login">-->
		        <#--<span class="glyphicon"></span> Login-->
	        <#--</a>-->
	        <#--<a type="button" class="btn btn-lg btn-primary" href="/singup">-->
		        <#--<span class="glyphicon glyphicon-user"></span> Signup-->
	        <#--</a>-->
	    </div>
	</div>
	<div class="container">
		<div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">

                <#if errorMessage??>
	                <div class="alert alert-danger text-center" role="alert">
	                    ${errorMessage}
	                </div>
                </#if>

                <#if successMessage??>
                    <div class="alert alert-success text-center" role="alert">
	                ${successMessage}
                    </div>
                </#if>

				<#if userEmail??>
	                <form class="form-horizontal" role="form" method="post" action="login">
	                    <div class="form-group">
	                        <label class="control-label col-sm-2" for="email">Email:</label>
	                        <div class="col-sm-10">
	                            <input type="email" name="email" class="form-control" id="email" placeholder="Enter email">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="control-label col-sm-2" for="pwd">Password:</label>
	                        <div class="col-sm-10">
	                            <input type="password" name="password" class="form-control" id="password" placeholder="Enter password">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="col-sm-offset-2 col-sm-10">
	                            <div class="checkbox">
	                                <label><input type="checkbox"> Remember me</label>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="col-sm-offset-2 col-sm-10">
	                            <button type="submit" class="btn btn-default">Submit</button>
	                        </div>
	                    </div>
	                </form>
				</#if>
            </div>
            <div class="col-md-3"></div>
		</div>

	    <hr>
	    <#--<div class="row">-->
	        <#--<div class="col-md-6">-->
	            <#--<h3><span class="glyphicon glyphicon-info-sign"></span> How this sample app works</h3>-->
	            <#--<ul>-->
	                <#--<li>This app was deployed to Heroku, either using Git or by using <a-->
	                        <#--href="https://github.com/heroku/java-getting-started">Heroku Button</a> on the repository.-->
	                <#--</li>-->

	                <#--<li>When Heroku received the source code, it grabbed all the dependencies in the <a-->
	                        <#--href="https://github.com/heroku/java-getting-started/blob/master/pom.xml">pom.xml</a>.-->
	                <#--</li>-->
	                <#--<li>The platform then spins up a dyno, a lightweight container that provides an isolated environment in-->
	                    <#--which the slug can be mounted and executed.-->
	                <#--</li>-->
	                <#--<li>You can scale your app, manage it, and deploy over <a href="https://addons.heroku.com/">150 add-on-->
	                    <#--services</a>, from the Dashboard or CLI.-->
	                <#--</li>-->
	                <#--<li>Check out the <a href="https://devcenter.heroku.com/articles/getting-started-with-java">Getting-->
	                    <#--Started</a> guide to learn more!-->
	                <#--</li>-->
	            <#--</ul>-->
	        <#--</div>-->
	        <#--<div class="col-md-6">-->
	            <#--<h3><span class="glyphicon glyphicon-link"></span> Helpful Links</h3>-->
	            <#--<ul>-->
	                <#--<li><a href="https://www.heroku.com/home">Heroku</a></li>-->
	                <#--<li><a href="https://devcenter.heroku.com/">Heroku Dev Center</a></li>-->
	                <#--<li><a href="https://devcenter.heroku.com/articles/getting-started-with-java">Getting Started with Java-->
	                    <#--on Heroku</a></li>-->
	                <#--<li><a href="https://devcenter.heroku.com/articles/deploying-java">Deploying Java Apps on Heroku</a>-->
	                <#--</li>-->
	            <#--</ul>-->
	        <#--</div>-->
	    <#--</div> <!-- row &ndash;&gt;-->
	    <#--<div class="alert alert-info text-center" role="alert">-->
	        <#--Please do work through the Getting Started guide, even if you do know how to build such an application. The-->
	        <#--guide covers the basics of working with Heroku, and will familiarize you with all the concepts you need in order-->
	        <#--to build and deploy your own apps.-->
	    <#--</div>-->
	</div>

</body>
</html>
