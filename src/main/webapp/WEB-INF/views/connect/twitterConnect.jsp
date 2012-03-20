<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>twitter Connect</title>
</head>
<body>
	<p>twitter에 연결되지 않았습니다.</p>
	<form method="post" action="${pageContext.request.contextPath}/connect/twitter">
	    <input type="hidden" name="oauth_callback" value="localhost:8080/SpringSocialTest/twitter" />
	    <input class="btn btn-primary" type="submit" value="Connect to twitter" />
	</form>
</body>
</html>