<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@  taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt"%>
<%@  taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c_rt:set var="staticUrl" value="http://s3.amazonaws.com/ertele" />
<c_rt:set var="isLogin" value="${sessionScope.user != null }" />

	<title>Okuma, video, müzik sayfalarını tek tıkla ertele gitsin | ertele.net</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="description" content="ertele.net bazen ertelemk gerekir">
	
	<link href='http://fonts.googleapis.com/css?family=Nova+Square' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="${staticUrl}/menubutton.css" type="text/css" media="screen">
	
	<script type="text/javascript">
		function onloadmore(){
			
		}
	
	</script>
	
	<style>
		body {
			background-color: #F1F1F1;
			font: 16px "Nova Square", Helvetica, sans-serif;
			color: #505050;
			line-height: 2em}
		a {
			text-decoration: none;
			color: #1f1f1f}
		span {
			font-size: 12px}
		
		img{border:none}
		.mylist-outer {
			position: absolute;
			left: 15%;
			right: 15%;
			top: 20px;
			border-radius: 20px;
			background: #fff;
			width: 70%}
		table {
			margin-bottom: 20px}
		
		tr:hover {
			background-color: #F9F9F9}
		th{
			text-align: left;
			padding: 10px;
			color: #888;
			border-bottom: 0.1em solid #CDCDCD}
		td{
			padding: 10px;
			border-bottom: 0.1em dashed #CDCDCD;
			border-right:3px solid #E7E7E7}
		td.titlerow:hover{
			font-size: 18px}
		td img:hover{
			width: 40px;
			height: 40px}
		#menu-button{
			background:#e6e6e6;
			border:#bbb 4px double;
		}
		#menu-button:hover{
			background:#6D6E71;
			border:#aaa 4px double;
		}
		#left-menu{
			position: absolute;
			left: 10px;
			top: 10px;
			width: 100px;
		}
		#right-menu{
			position: absolute;
			right: 10px;
			top: 10px;
			width: 100px;
		}
		
		.loadmore{
			font-size: 18px;
			border:#bbb 4px double;
		}
		td.loadmore:hover{
			font-size: 20px;
			background:#e6e6e6;
			border:#bbb 4px double;
		}
			
	</style>
</head>
<body>

<div class="mylist-outer" align="center">
<c_rt:choose>
	<c_rt:when test="${readlist == null }"> 
		<p align="center">
			liste bos
		</p>
	</c_rt:when>
	<c_rt:otherwise>
		<table summary="Okuma Listem">
			<thead>
			<tr style="color:#1f1f1f">
				<th >Okuma Listem</th>
				<th >Tarih</th>
				<th >Arşivle</th>
				<th >Paylaş</th>
			</tr>
			</thead>
			<tbody>
			<c_rt:forEach var="read" items="${readlist}">
				<tr>
					<td class="titlerow">
					<c_rt:url value="/readscreen.jsp" var="displayUrl">
						<c_rt:param name="url" value="${read.fullUrl}"></c_rt:param>
						<c_rt:param name="title" value="${read.title}"></c_rt:param>
						<c_rt:param name="id" value="${read.id}"></c_rt:param>
					</c_rt:url>
						<a href="${displayUrl}"><div id="tablerow">${read.title}<br/> <span class="full-url">${read.fullUrl}</span></div></a>
					</td> 
					<td><span>${read.date}</span></td> 
					<td><a href="${pageContext.request.contextPath}/toArchive?id=${read.id}" ><img src="${staticUrl}/clock.png" alt="Arşive Ekle" width="35px" height="35px"/></a></td> 
					<td style="width: 140px">
					<c_rt:url value="https://twitter.com/share" var="twitterUrl">
						<c_rt:param name="url" value="${read.fullUrl}"></c_rt:param>
						<c_rt:param name="text" value="${read.title}"></c_rt:param>
						<c_rt:param name="related" value="isikfatih"></c_rt:param>
						<c_rt:param name="via" value="isikfatih"></c_rt:param>
					</c_rt:url>
					<c_rt:url value="http://www.facebook.com/sharer.php" var="facebookUrl">
						<c_rt:param name="u" value="${read.fullUrl}"></c_rt:param>
						<c_rt:param name="t" value="${read.title}"></c_rt:param>
					</c_rt:url>
					<c_rt:url value="http://www.linkedin.com/shareArticle" var="linkedinUrl">
						<c_rt:param name="url" value="${read.fullUrl}"></c_rt:param>
						<c_rt:param name="title" value="${read.title}"></c_rt:param>
						<c_rt:param name="source" value="ertele.net"></c_rt:param>
						<c_rt:param name="mini" value="true"></c_rt:param>
					</c_rt:url>
						<a href="${facebookUrl}" target="blank" ><img src="${staticUrl}/facebook64.png" alt="Facebookta Paylaş" width="35px" height="35px"/></a>
						<a href="${twitterUrl}" target="blank"><img src="${staticUrl}/twitter64.png" alt="Twitterda Paylaş" width="35px" height="35px"/></a>
						<a href="${linkedinUrl}" target="blank"><img src="${staticUrl}/linkedin64.png" alt="Linkedinde Paylaş" width="35px" height="35px"/></a>
					</td> 
				</tr>
			</c_rt:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4" class="loadmore" align="center" onclick="onloadmore(this);"><em>2. Sayfayıda Göster</em></td>
				</tr>
			</tfoot>
		</table>
	
	</c_rt:otherwise>
</c_rt:choose>
</div>

	    <div id="right-menu" >
			<a id="menu-button" href="${pageContext.request.contextPath}/" title="Okuma Listem">Okuma</a>
			<a id="menu-button" href="${pageContext.request.contextPath}/" title="Video Listem">Video</a>
			<a id="menu-button" href="${pageContext.request.contextPath}/" title="Müzik Listem">Müzik</a>
	    </div>
	    <div id="left-menu" >
			<a id="menu-button" href="${pageContext.request.contextPath}/" title="Anasayfa">Anasayfa</a>
			<a id="menu-button" href="${pageContext.request.contextPath}/profile" title="Profilim">Profilim</a>
	    </div>

</body>
</html>