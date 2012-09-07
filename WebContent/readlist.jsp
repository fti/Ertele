<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reading List</title>
<style type="text/css">
table {
	border-spacing: 2px;
	display: table;
	border-collapse: separate;
	border-spacing: 2px;
	-webkit-border-horizontal-spacing: 2px;
	-webkit-border-vertical-spacing: 2px;
	border-color: gray;
	border-top-color: gray;
	border-right-color: gray;
	border-bottom-color: gray;
	border-left-color: gray;
}

#rounded-corner th {
	font-weight: normal;
	font-size: 13px;
	color: #039;
	background: #B9C9FE;
	padding: 8px;
}

#rounded-corner thead th.reading {
	background: #B9C9FE url("/images/left.png") left -1px no-repeat;
}

#rounded-corner thead th.readit {
	background: #B9C9FE url("/images/right.png") right -1px no-repeat;
}

tfoot {
	display: table-footer-group;
	vertical-align: middle;
	border-color: inherit;
}

tbody {
	display: table-row-group;
	vertical-align: middle;
	border-color: inherit;
}

tr {
	display: table-row;
	vertical-align: inherit;
	border-color: inherit;
}

td,th {
	display: table-cell;
	vertical-align: inherit;
}

#rounded-corner td {
	background: #E8EDFF;
	border-top: 1px solid white;
	color: #669;
	padding: 8px;
}
#rounded-corner tbody tr:hover td {
	background: #D0DAFD;
}
#rounded-corner {
	font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
	font-size: 12px;
	width: 80%;
	text-align: left;
	border-collapse: collapse;
	margin: 20px;
}
#rounded-corner tfoot td.rounded-foot-left {
	background: #E8EDFF url("/images/botleft.png") left bottom no-repeat;
}
#rounded-corner tfoot td.rounded-foot-right {
	background: #E8EDFF url("/images/botright.png") right bottom no-repeat;
}
</style>
</head>
<body>
<%@  taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt" %>
<c_rt:choose>
	<c_rt:when test="${readlist == null }"> 
		<p align="center">
			liste bos
		</p>
	</c_rt:when>
	<c_rt:otherwise>
		<table align="center"  id="rounded-corner" summary="2007 Major IT Companies' Profit">
			<thead>
			<tr style="color:#ff0000">
				<th scope="col" class="reading">Reading List</th>
				<th scope="col" class="archive">Archive</th>
				<th scope="col" class="readit">Read</th>
			</tr>
			</thead>
			<tbody>
			<c_rt:forEach var="read" items="${readlist}">
				<tr>
					<td>${read.title }<br/> ${read.rootUrl }</td> 
					<td>${read.archive }</td> 
					<td><a href="${pageContext.request.contextPath}/readscreen?url=${read.url }" ></a>${read.archive }</td> 
				</tr>
			</c_rt:forEach>
			</tbody>
			<tfoot>
				<tr>
				<td colspan="2" class="rounded-foot-left"><em>The above data were fictional and made up, please do not sue me</em></td>
				<td class="rounded-foot-right">&nbsp;</td>
				</tr>
			</tfoot>
		</table>
	
	</c_rt:otherwise>
</c_rt:choose>


</body>
</html>