<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>HTTP Status 403 - Access is denied</title>
	</head>	
	<body>
		<h1>HTTP Status 403 - Access is denied</h1>
		<c:choose>
			<c:when test="${empty userName}">
				<h2>You donot have permission to access this page!</h2>
			</c:when>
			<c:otherwise>
				<h2>Username: ${userName} You donot have permission to access this page!<br> </h2>
			</c:otherwise>
		</c:choose>
	</body>
</html>