<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.packo.app.beans.Parcel"%>
 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Packo - Home</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Glance Design Dashboard Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
    SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

    <link rel="shortcut icon" type="image/x-icon" href="STATIC/images/favicon.ico" />
    
    <!-- Bootstrap Core CSS -->
    <link href="STATIC/css/bootstrap.css" rel='stylesheet' type='text/css' />

    <!-- Custom CSS -->
    <link href="STATIC/css/style.css" rel='stylesheet' type='text/css' />

    <!-- font-awesome icons CSS -->
    <link href="STATIC/css/font-awesome.css" rel="stylesheet"> 
    <!-- //font-awesome icons CSS -->

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
    <body class="cbp-spmenu-push">
        <div class="se-pre-con"></div>
    <div class="main-content">
        <jsp:include page="_header.jsp"></jsp:include>
    <!-- main content start-->
    <div id="page-wrapper">
        <div class="main-page"> 
        <div class="tables">

        <h2 class="title1">Home</h2>
        <ol class="breadcrumb">
            <li class="active">Home</li>
        </ol>

       
        <c:if test="${errorString!=null}">
        <div class="alert alert-danger" role="alert">
            ${errorString}
        </div>
        </c:if>
        
        <c:if test="${successIndicator != false}">
        <div class="alert alert-success" role="alert">
            ${successMessage}
        </div>
        </c:if>
       
        <form method="POST" action="${pageContext.request.contextPath}/home">
            <div class="bs-example col-md-6 widget-shadow widget1 nicescroll-rails" style="max-height:600px; overflow-style: scrollbar; overflow-x:auto; overflow: scroll;" data-example-id="hoverable-table"> 
                <h4 class="title2">Parcels</h4>
                <hr class="widget-separator">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Package code</th>
                        <th>Receiver Name</th>
                        <th>Sender name</th>
                        <th>Apartment Number</th>
                        <th>Arrival time</th>
                        <th>Hold expiration</th>
                        <th>Select</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${packagesList}" var="pack" >
                    <tr>
                        <td>${pack.pickup_code}</td>
                        <td>${pack.receiver_name}</td>
                        <td>${pack.sender_name}</td>
                        <td>${pack.apt_number}</td>
                        <td>${pack.delivery_time.toString()}</td>
                        <td>${pack.expiration_time.toString()}</td>
                        <td><input type="radio" name="picked_package" value="${pack.parcel_id}" <c:if test="${pack.picked_up}"> disabled="true"> </c:if></td>
                    </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                        <input class="btn btn-success" type="submit" name="action" value='Pickup'>
                        <c:if test="${sessionScope.loginedAdmin.admin_priority == 1}"> 
                            <input class = "btn btn-warning" type="submit" name="action" value="Delete"> 
                        </c:if>
                        <input class = "btn btn-info" type="submit" name="action" value="HoldExtension">
                    </tfoot>

                </table>
            </div>
            
            <div class="bs-example col-md-5 widget-shadow widget1"> 
                
                <div class="stats-info-agileits">
                    <h4 class="title2">Stats</h4>
                <hr class="widget-separator">
                    <div class="stats-body">
                        <ul class="list-unstyled">
                            <li>
                                <i class="fa fa-cubes"></i> Remaining parcels <span class="label label-info1 pull-right">${packagesToPick}</span>  
                            </li>
                            <hr class="widget-separator">
                            <li>
                               <i class="fa fa-calendar-times-o"></i> Overdue Parcels <span class="label label-primary pull-right">${packagesOverdue}</span>  
                            </li>
                            <hr class="widget-separator">
                            <li>
                                <i class="fa fa-truck"></i> Total deliveries to residence <span class="label label-default pull-right">${packageTotal}</span>  
                            </li>
                            <hr class="widget-separator">
                            <li>
                                <i class="fa fa-user"></i> Total picked packages <span class="label label-success pull-right">${packagePicked}</span>  
                            </li>
                        </ul>
                    </div>
                </div>


                
            </div>
            
            
        </form>
            
        </div>
        </div>
    </div>
    <!--footer-->
        <jsp:include page="_footer.jsp"></jsp:include>
    <!--//footer-->
        
        
        
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
        
   </body>
</html>
