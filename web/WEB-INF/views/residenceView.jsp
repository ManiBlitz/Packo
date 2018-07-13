<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
    <!---I HATE HTML-->
   <head>
      <meta charset="UTF-8">
      <title>Packo - Residence</title>
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
                    <h4>Enter new residence informations</h4>
                </div>
                <div class="form-body">

                    <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/residence">

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
                                        <input type="tel" class="form-control1" placeholder="Residence Phone" name="residence_num" value="${sessionScope.residence.residence_phone}">
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
                                <label class="col-md-2 control-label">Residence Address</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-map-marker"></i>
                                        </span>
                                        <input type="text" class="form-control1" placeholder="Residence Address" name="residence_address" value="${sessionScope.residence.residence_email}">
                                    </div>
                                </div>
                            </div>
                                    
                            <div class="form-group">
                                <label class="col-md-2 control-label">Residence State</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-map"></i>
                                        </span>
                                        <select class="form-control1" name="residence_state">
                                            <option selected="true" value="AL">Alabama</option>
                                                <option value="AK">Alaska</option>
                                                <option value="AZ">Arizona</option>
                                                <option value="AR">Arkansas</option>
                                                <option value="CA">California</option>
                                                <option value="CO">Colorado</option>
                                                <option value="CT">Connecticut</option>
                                                <option value="DE">Delaware</option>
                                                <option value="DC">District Of Columbia</option>
                                                <option value="FL">Florida</option>
                                                <option value="GA">Georgia</option>
                                                <option value="HI">Hawaii</option>
                                                <option value="ID">Idaho</option>
                                                <option value="IL">Illinois</option>
                                                <option value="IN">Indiana</option>
                                                <option value="IA">Iowa</option>
                                                <option value="KS">Kansas</option>
                                                <option value="KY">Kentucky</option>
                                                <option value="LA">Louisiana</option>
                                                <option value="ME">Maine</option>
                                                <option value="MD">Maryland</option>
                                                <option value="MA">Massachusetts</option>
                                                <option value="MI">Michigan</option>
                                                <option value="MN">Minnesota</option>
                                                <option value="MS">Mississippi</option>
                                                <option value="MO">Missouri</option>
                                                <option value="MT">Montana</option>
                                                <option value="NE">Nebraska</option>
                                                <option value="NV">Nevada</option>
                                                <option value="NH">New Hampshire</option>
                                                <option value="NJ">New Jersey</option>
                                                <option value="NM">New Mexico</option>
                                                <option value="NY">New York</option>
                                                <option value="NC">North Carolina</option>
                                                <option value="ND">North Dakota</option>
                                                <option value="OH">Ohio</option>
                                                <option value="OK">Oklahoma</option>
                                                <option value="OR">Oregon</option>
                                                <option value="PA">Pennsylvania</option>
                                                <option value="RI">Rhode Island</option>
                                                <option value="SC">South Carolina</option>
                                                <option value="SD">South Dakota</option>
                                                <option value="TN">Tennessee</option>
                                                <option value="TX">Texas</option>
                                                <option value="UT">Utah</option>
                                                <option value="VT">Vermont</option>
                                                <option value="VA">Virginia</option>
                                                <option value="WA">Washington</option>
                                                <option value="WV">West Virginia</option>
                                                <option value="WI">Wisconsin</option>
                                                <option value="WY">Wyoming</option>
                                        </select>				
                                    </div>
                                </div>
                            </div>
                                    
                            <div class="form-group">
                                <label class="col-md-2 control-label">Residence city</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-building"></i>
                                        </span>
                                        <input type="text" class="form-control1" placeholder="Residence City" name="residence_city" value="${sessionScope.residence.residence_email}">
                                    </div>
                                </div>
                            </div>
                                    
                            <div class="form-group">
                                <label class="col-md-2 control-label">Residence Zip Code</label>
                                <div class="col-md-8">
                                    <div class="input-group">							
                                        <span class="input-group-addon">
                                            <i class="fa fa-location-arrow"></i>
                                        </span>
                                        <input type="text" class="form-control1" placeholder="Residence Zip Code" name="residence_zip_code" value="${sessionScope.residence.residence_email}">
                                    </div>
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

