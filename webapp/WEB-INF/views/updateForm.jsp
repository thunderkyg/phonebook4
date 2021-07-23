<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호 등록품</h1>
	<p>
		수정화면 입니다. 아래 항목을 수정하고 "수정" 버튼을 클릭하세요
	</p>
	
		<form action="./update" method="post">
			<input type="hidden" name="personId" value="${requestScope.personVo.personId}">
			이름(name): <input type="text" name="name" value="${requestScope.personVo.name}"> <br>
			핸드폰(hp): <input type="text" name="hp" value="${requestScope.personVo.hp}"> <br>
			회사(company): <input type="text" name="company" value="${requestScope.personVo.company}"> <br>
			<button type="submit">등록</button>
		</form>
	
			<br> <br> <a href="./list"> 리스트 바로가기</a>
</body>
</html>