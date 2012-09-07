<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@  taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt"%>
<c_rt:set var="staticUrl" value="http://s3.amazonaws.com/ertele" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${param.title}</title>
    <script type="text/javascript" src="http://www.google.com/jsapi"></script>
    <script type="text/javascript">google.load("jquery", "1.5.1");</script>
    <script type="text/javascript" src="${staticUrl}/auto-height.js"></script>

<style type="text/css">
	.topFrame{
		margin: 0;
		padding: 0;
	}
	.topFrame:hover{
		background: #D0DAFD;
	}
	.bottomFrame{
		margin-top: 0;
		width: 100%;	
	}
</style>
</head>
<c_rt:url value="/readscreentop.jsp" var="displayUrl">
	<c_rt:param name="url" value="${param.url}"></c_rt:param>
	<c_rt:param name="title" value="${param.title}"></c_rt:param>
	<c_rt:param name="id" value="${param.id}"></c_rt:param>
</c_rt:url>
<frameset rows="50,*" frameborder="no">
	<frame class="topFrame" name="menu" target="ana" src="${displayUrl}" scrolling="no">
	<frame class="bottomFrame" name="ana" src="${param.url}" scrolling="auto">
  	<noframes>
	   <body>
	    <h1>ya hacı tarayıcında frame desteği yok, ne mi yapacan, biz pek anlamayız bu işlerden ama chrome için iyi diyorlar.</h1>
	   </body>
	 </noframes>
</frameset>
<body>
</body>
</html>