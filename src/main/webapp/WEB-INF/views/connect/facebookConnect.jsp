<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Facebook에 연결되지 않았습니다.</p>
	<form method="post" action="/${pagecontext.request.contextpath}/connect/github">
	    <input type="hidden" name="scope" value="user_about_me, friends_about_me" />
	    <input class="btn btn-primary" type="submit" value="Connect to GitHub" />
	</form>
</body>
</html>