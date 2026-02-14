<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--localhost szerver elérési útja:  http://localhost:8080/ElsoJavaWebapp/HelloWorld.jsp -->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello World</h1>



	<div class="counter">
		<%
		Integer count = (Integer) session.getAttribute("count");
		if (count == null) {
			count = 0;
		}
		%>
		<h2>
			Counter:
			<%=count%></h2>
	</div>

	<form action="CounterServlet" method="post">
		<button type="submit">Plus +1</button>
	</form>


</body>
</html>