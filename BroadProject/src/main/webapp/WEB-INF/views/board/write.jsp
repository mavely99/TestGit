<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- 절대경로 -->
<c:set var="root" value="${pageContext.request.contextPath }/" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미니 프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<!-- 상단 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp" />

<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
				
					<form:form action="${root }board/write_pro" modelAttribute="writeContentBean" method="post" enctype="multipart/form-data">
					<!-- board_info_idx번호로 SQL에서 확인이 가능하다. -->
					
					<form:hidden path="content_board_idx"/>
					<!-- 자유게시판/정치/유머 .. 등을 눌렀을때 해당 게시판의 content_board_idx를 가지고 온 idx값을 숨겨놓은 것이다.
					이게 없다면 마지막에 서밋과정(작성하기 버튼)에서 현재의 idx가 사라지게된다.꼭 필수이다!! 
					
					form:hidden 해주는 이유는 idx는 DB에서 게시판의 기본키로 지정되어있기 때문에 
					히든으로 해놓지 않으면 1,2,3,4 이렇게 뜨기때문에 히든으로 숨긴다.
					-->
					
					<div class="form-group">
						<form:label path="content_subject">제목</form:label>
						<form:input path="content_subject" class="form-control"/>
						<form:errors path="content_subject" style="color:red;" />
					</div>
					<div class="form-group">
						<form:label path="content_text">내용</form:label>
						<form:textarea path="content_text" class="form-control" rows="10" style="resize:none"></form:textarea>
						<form:errors path="content_text" style="color:red;" />
					</div>
					<div class="form-group">
						<form:label path="upload_file">첨부 이미지</form:label>
						<form:input type="file" path="upload_file" class="form-control" accept="image/*"/>
					</div>
					<div class="form-group">
						<div class="text-right">
							<form:button type="submit" class="btn btn-primary">작성하기</form:button>
						</div>
					</div>
					
					</form:form>
					
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>

<!-- 하단 -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>
