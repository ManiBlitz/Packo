<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
    <!---I HATE HTML-->
   <head>
      <meta charset="UTF-8">
      <title>Packo - Parameters</title>
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
            <div class="forms">

  
            <h2 class="title1">${sessionScope.residence.residence_name}</h2>

                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li class="active">Parameters</li>
                </ol>

         
            <c:if test="${errorString!=null}">
            <div class="alert alert-danger" role="alert">
                ${errorString}
            </div>
            </c:if>
         
            <div class="row">
            <div class="form-three widget-shadow">
                <div class="form-title">
                    <h4>Enter new residence informations</h4>
                </div>
                <div class="form-body">

                    <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/parameters">

                            <div class="form-group">
                                <label class="col-md-2 control-label">Residence Name</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-home"></i>
                                        </span>
                                        <input type="text" class="form-control1" placeholder="Residence Name" name="residence_name" value ="${sessionScope.residence.residence_name}">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Residence Phone</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-phone"></i>
                                        </span>
                                        <input type="tel" class="form-control1" placeholder="Residence Phone" name="residence_phone" value="${sessionScope.residence.residence_phone}">
                                    </div>
                                </div>
                            </div>
                            

                            <div class="form-group">
                                <label class="col-md-2 control-label">Residence Email</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-envelope-o"></i>
                                        </span>
                                        <input type="email" class="form-control1" placeholder="Residence Email" name="residence_email" value="${sessionScope.residence.residence_email}">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Residence Storage Space</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-cubes"></i>
                                        </span>
                                        <input type="number" min="1" class="form-control1" placeholder="Residence Storage Space" name="residence_storage_space" value="${sessionScope.residence.residence_storage_space}">
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <p class="help-block">Maximum number of packages that can be stored</p>
                                </div>

                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Hold Expiration Default</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-clock-o"></i>
                                        </span>
                                        <input type="number" min="1" class="form-control1" placeholder="Hold expiration default" name="hold_expiration_default" value="${sessionScope.residence.hold_expiration_default}">
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <p class="help-block">Minimal hold time available</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Max Package Weight</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-tachometer"></i>
                                        </span>
                                        <input type="number" step="any" min="0" class="form-control1" placeholder="Max package weight" name="max_package_weight" value="${sessionScope.residence.max_package_weight}">
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <p class="help-block">Maximal weight for single package</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Daily Hold Price</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-money"></i>
                                        </span>
                                        <input class="form-control1" placeholder="Daily Hold Price" type="number" step="any" min="0" name="daily_hold_price" value="${sessionScope.residence.daily_hold_price}">
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <p class="help-block">Taxation to residents per day of hold extension</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Max Hold Time</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-exclamation-circle"></i>
                                        </span>
                                        <input class="form-control1" placeholder="Max Hold Time" type="number" min="1" name="max_hold_time" value="${sessionScope.residence.max_hold_time}">
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <p class="help-block">Maximal hold length from package arrival date</p>
                                </div>
                            </div>
                            
                            
                            <input class="btn btn-success" type="submit" value="Submit" />
                            <a href="${pageContext.request.contextPath}/home">Cancel</a>
                </form>
            </div>
            </div>
            </div>
        </div>
    </div>  
    </div>
    <!--footer-->
    <div class="footer">
        <p>&copy; 2018 Glance Design Dashboard. All Rights Reserved | Design by <a href="https://w3layouts.com/" target="_blank">w3layouts</a></p>		
    </div>
    <!--//footer-->
    </div>

    <!-- side nav js -->
    <script src='js/SidebarNav.min.js' type='text/javascript'></script>
    <script>
    $('.sidebar-menu').SidebarNav()
    </script>
    <!-- //side nav js -->

    <!-- Classie --><!-- for toggle left push menu script -->
    <script src="js/classie.js"></script>
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
    <script src="js/jquery.nicescroll.js"></script>
    <script src="js/scripts.js"></script>
    <!--//scrolling js-->

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.js"> </script>
    <!-- //Bootstrap Core JavaScript -->

    </body>
</html>
