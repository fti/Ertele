<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@  taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt"%>
<%@  taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c_rt:set var="staticUrl" value="http://s3.amazonaws.com/ertele" />
<c_rt:set var="isLogin" value="${sessionScope.user != null }" />

<link href='http://fonts.googleapis.com/css?family=Nova+Square' rel='stylesheet' type='text/css'>

<style type="text/css">
	body{
		background-color: #d4d4d4;
		border-bottom: #fff 5px dashed;
		margin-bottom: 0;
		height: 48px;
		font:  16px "Nova Square", cursive;
		color: #888;
	}
	body:hover {
		background-color: #f1f1f1;
		color: #999;
	}
	
	a, p, div{
		float: left;
	}
	a{
		margin: 5px;
	}
	.share{
		padding-right: 10px;
		border-right: #888 2px solid;
	}
	
	.closeframe{
		position: absolute;
		right: 1%;
		top: 1px;
		padding-left: 10px;
	}
	.toarchive{
		border-right: #888 2px solid;
		border-left: #888 2px solid;
		padding: 0 10px 0 10px;
	}

	.left-side{
		position: absolute;
		left: 0%;
		top: 1px;
	}

	.toback, .tohome{
		border-right: #888 2px solid;
		padding-right: 10px;
	}
	.tohome, .title{
		padding-left: 10px;
	}
	
	.flip-horizontal {
	    -moz-transform: scaleX(-1);
	    -webkit-transform: scaleX(-1);
	    -o-transform: scaleX(-1);
	    transform: scaleX(-1);
	    filter: fliph; /*IE*/
	}
	img{border:none}
	img:hover{
		height: 40px;
		width: 40px;
	}
</style>
</head>
<body>
<c_rt:url value="https://twitter.com/share" var="twitterUrl">
	<c_rt:param name="url" value="${param.url}"></c_rt:param>
	<c_rt:param name="text" value="${param.title}"></c_rt:param>
	<c_rt:param name="related" value="isikfatih"></c_rt:param>
	<c_rt:param name="via" value="isikfatih"></c_rt:param>
</c_rt:url>
<c_rt:url value="http://www.facebook.com/sharer.php" var="facebookUrl">
	<c_rt:param name="u" value="${param.url}"></c_rt:param>
	<c_rt:param name="t" value="${param.title}"></c_rt:param>
</c_rt:url>
<c_rt:url value="http://www.linkedin.com/shareArticle" var="linkedinUrl">
	<c_rt:param name="url" value="${param.url}"></c_rt:param>
	<c_rt:param name="title" value="${param.title}"></c_rt:param>
	<c_rt:param name="source" value="ertele.net"></c_rt:param>
	<c_rt:param name="mini" value="true"></c_rt:param>
</c_rt:url>
<div class="left-side">
	<div class="toback">
		<a href="#" title="Geri Gitmek için geriye basın" ><img alt="ertele.net" src="${staticUrl}/back.png" width="35px" height="35px"/></a>
	</div>
	<div class="tohome">
		<p style="float: left">Anasayfa: </p>
		<a href="${pageContext.request.contextPath}/" target="_top" title="Ana Sayfa" style="padding-right: 10px;"><img alt="ertele.net" src="${staticUrl}/home.png" width="35px" height="35px"/></a>
	</div>
	<div class="title">
		<p>Başlık: <c_rt:out value="${fn:substring(param.title,0,60)}"/>...</p>
	</div>

</div>

	
<div class="closeframe">
	<div class="share">
		<div class="toarchive">
			<p style="float: left">Arşivle: </p>
			<a href="${pageContext.request.contextPath}/toArchive?id=${param.id}" ><img src="${staticUrl}/clock.png" alt="Arşive Ekle" width="35px" height="35px"/></a>
		</div>
		<p style="float: left; padding-left: 10px;">Paylaş: </p>
		<a href="${facebookUrl}" target="blank" title="Facebookta Paylaş"><img src="${staticUrl}/facebook64.png" alt="Facebookta Paylaş" width="35px" height="35px"/></a>
		<a href="${twitterUrl}" target="blank" title="Twitter da Paylaş"><img src="${staticUrl}/twitter64.png" alt="Twitterda Paylaş" width="35px" height="35px"/></a>
		<a href="${linkedinUrl}" target="blank" title="Linkedin de Paylaş"><img src="${staticUrl}/linkedin64.png" alt="Linkedinde Paylaş" width="35px" height="35px"/></a>
	</div>
	<p style="padding-left: 10px;">Çerçeveyi Kaldır: </p>
	<a href="${param.url}" target="_top"><img src="${staticUrl}/closeframe.png" alt="Çerçeveyi Kaldır" width="35px" height="35px"/></a>
</div>
</body>
</html>