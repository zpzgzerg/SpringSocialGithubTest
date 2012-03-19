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
		<section>
		    <h2>${socialType}</h2>
		    <social:connected provider="${socialType}">
		        <p>${socialType}에 연결되었습니다.</p>
		        <c:if test="${user.profileImageUrl != null}">
			        <div class="profilePhoto">
			        	<img src="${user.profileImageUrl}">
			        </div>
		        </c:if>
		        <div style="boder:1px solid black">
		            <table>
		            	<tr>
		            		<td>ID</td>
		            		<td>${user.id}</td>
		            	</tr>
		            	<tr>
		            		<td>E-Mail</td>
		            		<td>${user.email}</td>
		            	</tr>
		            		<tr>
		            		<td>Name</td>
		            		<td>${user.name}</td>
		            	</tr>
		               <tr>
		               	<td valign="top">Follower</td>
						<td>
							<c:forEach items="${user.followerList}" var="follower"> 
								<div style="boder:1px solid black">
									<c:if test="${follower.profileImageUrl != null}">
										<img src="${follower.profileImageUrl}" width="30px" height="30px">
									</c:if>									
									${follower.name} ${follower.email} 
								</div>
							</c:forEach>
						</td>
		               </tr>
		            </table>
		        </div>
		        <form method="post" action="${connectURL}">
		            <input type="hidden" name="_method" value="delete" />
		            <input class="btn btn-danger" type="submit" value="Disconnect from ${socialType}" />
		        </form>
		    </social:connected>
		
		    <social:notConnected provider="${socialType}">
		        <p>${socialType}에 연결되지 않았습니다.</p>
		        <form method="post" action="${connectURL}">
		        	<c:choose>
		        		<c:when test="${socialType == 'github'}">
		        			 <input type="hidden" name="scope" value="user, repo, gist" />
		        		</c:when>
		        		<c:when test="${socialType == 'facebook'}">
		        			 <input type="hidden" name="scope" value="user_about_me, friends_about_me" />
		        		</c:when>
		        		
		        	</c:choose>
		           
		            <input class="btn btn-primary" type="submit" value="Connect to ${socialType}" />
		        </form>
		    </social:notConnected>
		</section>
	</body>
</html>


