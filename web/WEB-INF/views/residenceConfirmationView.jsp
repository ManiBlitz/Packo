<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
    <!---I HATE HTML-->
   <head>
      <meta charset="UTF-8">
      <title>Packo - Residence Confirmation</title>
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
                    <h4>Enter hold extension informations</h4>
                </div>
                <div class="form-body">

                    <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/confirmation">

                            <div class="form-group">
                                <label class="col-md-2 control-label">Residence Code</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-barcode"></i>
                                        </span>
                                        <input class="form-control1" type="text" name="code_residence">
                                    </div>
                                </div>
                            </div>
                                    
                            <input class="btn btn-success" type="submit" value="Validate" />
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



