<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@  taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt"%>
	<c_rt:set var="staticUrl" value="http://s3.amazonaws.com/ertele" />
	<c_rt:set var="isLogin" value="${sessionScope.user != null }" />
	<c_rt:if test="${isLogin }"><c_rt:set var="user" value="${sessionScope.user}" /></c_rt:if>
	
	
	<title>Okuma, video, müzik sayfalarını tek tıkla ertele gitsin | ertele.net</title>
	
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="description" content="ertele.net bazen ertelemk gerekir">
    <!--<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>-->

    <link href='http://fonts.googleapis.com/css?family=Nova+Square' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${staticUrl}/main.css" type="text/css" media="screen">
    
    <script type="text/javascript" src="http://www.google.com/jsapi"></script>
    <script type="text/javascript">google.load("jquery", "1.5.1");</script>
    <script src="${staticUrl}/colorbox/jquery.colorbox.js"></script>
    <script type="text/javascript" src="${staticUrl}/ga.js"></script>
    
    <script type="text/javascript">
		var currentScreen = 1;
		var screens = [];
		screens[0] = 'http://s3.amazonaws.com/ertele/screen1.gif';
		screens[1] = 'http://s3.amazonaws.com/ertele/screen2.gif';
		screens[2] = 'http://s3.amazonaws.com/ertele/screen3.gif';
		screens[3] = 'http://s3.amazonaws.com/ertele/screen4.gif';
		screens[4] = 'http://s3.amazonaws.com/ertele/screen5.gif';
		screens[5] = 'http://s3.amazonaws.com/ertele/screen6.gif';
		
		var lastTimeout = 0;
		var isSliding = false;
	    
	    
		$(document).ready(function(){
		    
		  lastTimeout = setTimeout("slide(500)", 4000);
		  $(".ajax").colorbox();
		  $(".iframe").colorbox({iframe:true, width:"500px", height:"500px"});
		  $(".registerloginbox").colorbox({iframe:true, width:"500px", height:"500px", onClosed:function(){ location.reload(true); }});
		  
		  var currentPosition = 0;
		  var slideWidth = 560;
		  var slides = $('.slide');
		  var numberOfSlides = slides.length;
		
		  // Remove scrollbar in JS
		  $('#slidesContainer').css('overflow', 'hidden');
		
		  // Wrap all .slides with #slideInner div
		  slides
		    .wrapAll('<div id="slideInner"></div>')
		    // Float left to display horizontally, readjust .slides width
			.css({
		      'float' : 'left',
		      'width' : slideWidth
		    });
		
		  // Set #slideInner width equal to total width of all slides
		  $('#slideInner').css('width', slideWidth * numberOfSlides);
		
		  // Insert controls in the DOM
		  $('#slideshow')
		    .prepend('<span class="control" id="leftControl">Clicking moves left</span>')
		    .append('<span class="control" id="rightControl">Clicking moves right</span>');
		
		  // Hide left arrow control on first load
		  manageControls(currentPosition);
		
		  // Create event listeners for .controls clicks
		  $('.control')
		    .bind('click', function(){
		    // Determine new position
			currentPosition = ($(this).attr('id')=='rightControl') ? currentPosition+1 : currentPosition-1;
		    
			// Hide / show controls
		    manageControls(currentPosition);
		    // Move slideInner using margin-left
		    $('#slideInner').animate({
		      'marginLeft' : slideWidth*(-currentPosition)
		    });
		  });
		
		  // manageControls: Hides and Shows controls depending on currentPosition
		  function manageControls(position){
		    // Hide left arrow if position is first slide
			if(position==0){ $('#leftControl').hide() } else{ $('#leftControl').show() }
			// Hide right arrow if position is last slide
		    if(position==numberOfSlides-1){ $('#rightControl').hide() } else{ $('#rightControl').show() }
		  }	
		});
	    
	    
	
		function switchToSlide(screen) {
		    if (!isSliding) {
			$('#screen2').attr('src', screens[screen]);
			currentScreen = screen;
			clearTimeout(lastTimeout);
			slide(500);
		    }
		}
		
		function slide(speed) {
		    isSliding = true;
		    $('#screenImages').animate({left: '-=253'}, speed, 'swing', function() {
			$('#screen1').attr('src', screens[currentScreen]);
			setTimeout("preloadSlide()", 100);
		    });
		}
		
		function preloadSlide() {
		    $('#screenImages').css('left','-25px');
		    currentScreen++;
		    if (currentScreen >= screens.length)
			currentScreen = 0;
		    $('#screen2').attr('src', screens[currentScreen]);
		    lastTimeout = setTimeout("slide(500)", 4000);
		    isSliding = false;
		}
    </script>
    
</head>
<body>
<div id="wrap">
		<div id="header">
		    <div id="screenshots">
				<div id="iphoneScreen" style="overflow: hidden; width: 253px; height: 500px; margin-left: 25px;">
				    <div id="screenImages" style="position: relative; left: -25px; ">
					<img id="screen1" src="${staticUrl}/screen1.gif" alt="Ertele.net">
					<img id="screen2" src="${staticUrl}/screen2.gif" alt="Ertele.net" style="left: 278px">
				    </div>
				</div>
		    </div> <!-- end #screenshots -->
		    
		    <div id="features">
				<h1>Okuma, video, müzik sayfalarını tek tıkla ertele gitsin, ertele.net</h1>
				<div id="slideshow">
				    <div id="slidesContainer">
				      <div class="slide">
					<h2>Ertele.net Yakında Aktif </h2>
					<p><a href="#"><img src="${staticUrl}/s_ertele.jpg" width="215" height="145" alt="Ertele.net yakında aktif" /></a>Şu an site üzerinde çalışmalarımız devam etmektedir, sitemize üye alımı yapılmamaktadır.</p>
					<p>Chrome ve firefox pluginlerinin de geliştirilmesinin bitmesi ile beta olarak ertele.net yayına girecek. Saygılarımızla</p>
				      </div>
				      <div class="slide">
					<h2>Ertele çok yakında Android Market'te</h2>
					<p><a href="#"><img src="${staticUrl}/s_androidmarket.jpg" alt="Ertele Android Uygulaması" width="215" height="145" /></a>Android uygulamamız mobil olarak ilk yayınlayacağımız uygulamamız. Bazı eksiklikler ve test aşamlarından sonra android markette yayınlamış olacağız.</p>
				      </div>
				      <div class="slide">
					<h2>Ertele iPhone uygulaması çok yakında AppStore'da </h2>
					<p><a href="#"><img src="${staticUrl}/s_appstore.jpg" width="215" height="145" alt="Ertele iPhone Uygulaması" /></a>iPhone uygulamamız için biraz daha zaman var gibi ama en kısa sürede geliştirmeye başlamış olacağız.</p>
					<p> 
				      </div>
				      <div class="slide">
					<h2>Chrome Plugini çok yakında Chrome Store'da</h2>
					<p><a href="#"><img src="${staticUrl}/s_chromestore.jpg" width="215" height="145" alt="Ertele Chrome plugini" /></a>Chrome eklentisinin geliştirilmesi de bitmek üzre, bazı eksikliklerin tamamlanması ile chrome storeda yerini alacak.</p>
				      </div>
				    </div>
				</div><!-- Slideshow HTML -->
		    </div>
	</div>
    </div>
    <div id="login-buttons" >
    	<c_rt:if test="${isLogin}">
    		<a id="menu-button" href="${pageContext.request.contextPath}/mylist" title="Listelerim">Listelerim</a>
			<a id="menu-button" class='profile' href="${pageContext.request.contextPath}/logout">Çıkış</a>
    	</c_rt:if>
    	<c_rt:if test="${!isLogin}">
			<a id="menu-button" class='registerloginbox' href="${pageContext.request.contextPath}/login" title="Giriş Yap">Giriş Yap</a>
			<a id="menu-button" class='registerloginbox' href="${pageContext.request.contextPath}/register" title="Üye Ol">Üye Ol</a>
		</c_rt:if>		
		<a id="menu-button" class='ajax' href="${pageContext.request.contextPath}/help.jsp">Yardım</a>		
		<a id="menu-button" class='ajax' href="${pageContext.request.contextPath}/contact.jsp">İletişim</a>		
		<a id="menu-button" class='ajax' href="${pageContext.request.contextPath}/about.jsp">Biz Kim</a>		
    </div>
    <div id="facetwit-buttons" >
		<a id="menu-button" href="#"><img align="middle" src="${staticUrl}/androidmarket.png" /></a>		
		<a id="menu-button" href="#"><img align="middle" src="${staticUrl}/appstore.png" /></a>	
		<a id="menu-button" href="#"><img align="middle" src="${staticUrl}/blackberry.png" /></a>	
		<a id="menu-button" href="#"><img align="middle" src="${staticUrl}/chrome.png" /></a>	
		<a id="menu-button" href="#"><img align="middle" src="${staticUrl}/firefox.png" /></a>	
		<a id="menu-button" href="#"><img align="middle" src="${staticUrl}/facebook.png" /></a>	
		<a id="menu-button" href="#"><img align="middle" src="${staticUrl}/twitter.png" /></a>	
    </div>
    <c_rt:if test="${isLogin}">
	    <div id="profile-screen" >
			<a id="menu-button" class='ajax' href="${pageContext.request.contextPath}/profile" title="Profilim">Profilim</a>
	    </div>
	</c_rt:if>
	
	


</body>
</html>

<%-- <c_rt:choose>
	<c_rt:when test="${sessionScope.user == null }"> 
		<p align="center">
			<a href="${pageContext.request.contextPath}/login" >Login</a>
			<a href="${pageContext.request.contextPath}/register" >Register</a>
		</p>
	</c_rt:when>
	<c_rt:otherwise>
		welcome ${sessionScope.user.u }
		
	<a href="${pageContext.request.contextPath}/readlist" > Readlist</a>
	</c_rt:otherwise>
	</c_rt:choose>--%>