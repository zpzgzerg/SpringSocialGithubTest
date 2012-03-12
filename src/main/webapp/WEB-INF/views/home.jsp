<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="social" uri="http://www.springframework.org/spring-social/social/tags" %>
<html>
<head>
<meta content="text/html; charset=utf-8" />
</head>
	<body>
		<c:url var="connectUrl" value="/connect" />
		<c:url var="githubUrl" value="/connect/github" />
		
		
		<section>
		    <h2>GitHub</h2>
		    <social:connected provider="github">
		        <p>GitHub에 연결되었습니다.</p>
		        <div class="profilePhoto">
		        	<img src="${userProfile.profileImageUrl}">
		        </div>
		        <div class="well">
		            <table class="grid">
		            	<tr>
		            		<td>E-Mail</td>
		            		<td>${userProfile.email}</td>
		            	</tr>
		            		<tr>
		            		<td>Name</td>
		            		<td>${userProfile.name}</td>
		            	</tr>
		               <tr>
		               	<td>Follower</td>
						<td>
							<c:forEach items="${followers}" var="follower"> 
								<div>
									<img src="${follower.avatarUrl}" width="30px" height="30px"> ${follower.name} ${follower.email} 
								</div>
							</c:forEach>
						</td>
		               </tr>
		            </table>
		        </div>
		        <form method="post" action="${githubUrl}">
		            <input type="hidden" name="_method" value="delete" />
		            <input class="btn btn-danger" type="submit" value="Disconnect from GitHub" />
		        </form>
		    </social:connected>
		
		    <social:notConnected provider="github">
		        <p>GitHub에 연결되지 않았습니다.</p>
		        <form method="post" action="${githubUrl}">
		            <input type="hidden" name="scope" value="user, repo, gist" />
		            <input class="btn btn-primary" type="submit" value="Connect to GitHub" />
		        </form>
		    </social:notConnected>
		</section>
	</body>
</html>


