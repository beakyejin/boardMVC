<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="jsp/css/common.css">
<link rel="stylesheet" type="text/css" href="jsp/css/${cssName}.css">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	<%-- ${param.btype } --%>
		<jsp:include page="top.jsp" />
		<jsp:include page="${content}.jsp" />
		<jsp:include page="bottom.jsp" />
	</div>
</body>
</html>