<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Login page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Glance Design Dashboard Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>


<link rel="shortcut icon" type="image/x-icon" href="STATIC/images/favicon.ico" />
<!-- Bootstrap Core CSS -->
<link href="STATIC/css/bootstrap.css" rel='stylesheet' type='text/css' />

<!-- Custom CSS -->
<link href="STATIC/css/style.css" rel='stylesheet' type='text/css' />

<!-- font-awesome icons CSS-->
<link href="STATIC/css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons CSS-->

 <!-- side nav css file -->
 <link href='STATIC/css/SidebarNav.min.css' media='all' rel='stylesheet' type='text/css'/>
 <!-- side nav css file -->
 
 <!-- js-->
<script src="STATIC/js/jquery-1.11.1.min.js"></script>
<script src="STATIC/js/modernizr.custom.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>

<script>
        $(window).load(function() {
                // Animate loader off screen
                $(".se-pre-con").fadeOut("slow");;
        });
</script>

<!--webfonts-->
<link href="//fonts.googleapis.com/css?family=PT+Sans:400,400i,700,700i&amp;subset=cyrillic,cyrillic-ext,latin-ext" rel="stylesheet">
<!--//webfonts-->
 
<!-- Metis Menu -->
<script src="STATIC/js/metisMenu.min.js"></script>
<script src="STATIC/js/custom.js"></script>
<link href="STATIC/css/custom.css" rel="stylesheet">
<!--//Metis Menu -->

</head> 
<body>
    <div class="se-pre-con"></div>
<div class="main-content">
	
	</div>
		<!-- main content start-->
		<div id="page-wrapper">
			<div class="main-page login">

				<!---Beginning of the login form-->


				<div class="forms widget-shadow col-md-6 col-md-offset-3" data-example-id="basic-forms"> 
					<div class="form-title">
						<h4 class="title1"><span><i class="fa fa-cube"></i></span> Packo - Login</h4>
					</div>
					<div class="form-body">
						<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/login">

							<div class="form-group has-feedback" >
								<label class="col-md-2 control-label">Username</label>
								<div class="col-md-8">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="fa fa-user"></i>
										</span>
										<input type="text" class="form-control1" name="admin_code" value= "${user.admin_code}" placeholder="Admin name" data-error="The username entered is invalid">
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label">Password</label>
								<div class="col-md-8">
									<div class="input-group">
										<span class="input-group-addon">
											<i class="fa fa-lock"></i>
										</span>
										<input type="password" class="form-control1" name="password" value= "${user.admin_password}" placeholder="Password">
									</div>
								</div>
							</div>

							<div class="col-sm-offset-2"> <button type="submit"  class="btn btn-default">Sign in</button> </div>

							<div class="registration">
								Don't have an account ?
								<a class="" href="${pageContext.request.contextPath}/residence">
									Create an account
								</a>
							</div>
						</form>
					</div>

					<c:if test="${errorString!=null}">
						<div class="alert alert-danger" role="alert">
							${errorString}
						</div>
					</c:if>

				</div>
				

				<!---End of the login form-->

			</div>
			
		</div>
		<!--footer-->
		<div class="footer">
		   <p>&copy; 2018 Glance Design Dashboard. All Rights Reserved | Design by <a href="https://w3layouts.com/" target="_blank">w3layouts</a></p>		</div>
        <!--//footer-->
	</div>
	
	<!-- side nav js -->
	<script src='STATIC/js/SidebarNav.min.js' type='text/javascript'></script>
	<script>
      $('.sidebar-menu').SidebarNav()
    </script>
	<!-- //side nav js -->
	
	<!-- Classie --><!-- for toggle left push menu script -->
		<script src="STATIC/js/classie.js"></script>
		<script>
			var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),
				showLeftPush = document.getElementById( 'showLeftPush' ),
				body = document.body;
				
			showLeftPush.onclick = function() {
				classie.toggle( this, 'active' );
				classie.toggle( body, 'cbp-spmenu-push-toright' );
				classie.toggle( menuLeft, 'cbp-spmenu-open' );
				disableOther( 'showLeftPush' );
			};
			
			function disableOther( button ) {
				if( button !== 'showLeftPush' ) {
					classie.toggle( showLeftPush, 'disabled' );
				}
			}
		</script>
	<!-- //Classie --><!-- //for toggle left push menu script -->
		
	<!--scrolling js-->
	<script src="STATIC/js/jquery.nicescroll.js"></script>
	<script src="STATIC/js/scripts.js"></script>
	<!--//scrolling js-->
	
	<!-- Bootstrap Core JavaScript -->
   <script src="STATIC/js/bootstrap.js"> </script>
	<!-- //Bootstrap Core JavaScript -->
   
</body>
</html>