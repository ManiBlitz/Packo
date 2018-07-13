<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
    <!---I HATE HTML-->
    <head>
        <meta charset="UTF-8">
        <title>Packo - Add new Administrator</title>
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
    <body>
        <div class="se-pre-con"></div>
        
    <div class="main-content">
    <!-- main content start-->
    
    <div id="page-wrapper">
        
        <div class="main-page"> 
            <div class="forms">
         
            <c:if test="${errorString!=null}">
            <div class="alert alert-danger" role="alert">
                ${errorString}
            </div>
            </c:if>
         
            <div class="row">
            <div class="form-three widget-shadow">
                <div class="form-title">
                    <h4>Enter administrator information</h4>
                </div>
                <div class="form-body">

                    <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/admin">

                            <div class="form-group">
                                <label class="col-md-2 control-label">Administrator Name</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-user"></i>
                                        </span>
                                        <input type="text" class="form-control1" placeholder="Administrator Name" name="name">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Administrator Username</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-check-circle-o"></i>
                                        </span>
                                        <input type="text" class="form-control1" placeholder="Administrator Username" name="admin_code">
                                    </div>
                                </div>
                            </div>
                            

                            <div class="form-group">
                                <label class="col-md-2 control-label">Administrator Password</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-lock"></i>
                                        </span>
                                        <input type="password" class="form-control1" placeholder="Administrator Password" name="password">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Administrator Email</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-envelope"></i>
                                        </span>
                                        <input type="email" class="form-control1" placeholder="Administrator Email" name="email">
                                    </div>
                                </div>
                            </div>
                        
                            <div class="form-group">
                                <label class="col-md-2 control-label">Administrator Phone Number</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-phone"></i>
                                        </span>
                                        <input type="tel" class="form-control1" placeholder="Administrator Phone Number" name="phone_number">
                                    </div>
                                </div>
                            </div>
                        
                            <div class="form-group">
                                <label class="col-md-2 control-label">Administrator Address</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-building-o"></i>
                                        </span>
                                        <input type="text" class="form-control1" placeholder="Administrator Address" name="address">
                                    </div>
                                </div>
                            </div>
                        
                            <div class="form-group">
                                <label class="col-md-2 control-label">Administrator Birth Date</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </span>
                                        <input type="date" class="form-control1" placeholder="Administrator Birth Date" name="date_of_birth">
                                    </div>
                                </div>
                            </div>
                        
                            <input type="hidden" name="priority" value="1"/>
                            <input type="hidden" name="role" value="Super Admin"/>
                            <input type="hidden" name="residence_code" value="${sessionScope.complex_data.residence_code}"/>
                            
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
        <jsp:include page="_footer.jsp"></jsp:include>
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



