<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>  Book 등록  </h2>

<form action="addBook.do" method="post">
	제목 : <input type="text" id="" name="title"><br/>
	출판사 : <input type="text" id="" name="publisher"><br/>
	가격 : <input type="number" id="" name="price"><br/>
	

	<input type="submit" value="등록">
	<input type="reset">
</form>
</body>
</html>