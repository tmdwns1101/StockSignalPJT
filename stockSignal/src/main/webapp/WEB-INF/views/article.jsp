<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주식 시그널</title>
<!-- bootstrap css -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<!-- bootstrap js -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#reviewCreatedBtn").click(function() {
			var writer = $("#reviewWriter").val();
			var content = $("#reviewContent").val();
			var password = $("#reviewPassword").val();
			var articleId = $("#articleId").val();
			
			var data = {
				writer,
				content,
				password,
				articleId
			};
			console.log(JSON.stringify(data));
			$.ajax({
				data: JSON.stringify(data),
        		type: "POST",
        		contentType: "application/json; charset=utf-8",
        		datatype: "json",
        		url: "${contextPath}" + "/api/reviews",
        		success: function (review) {
        			var reviewList = $("#reviewList");
        			reviewList.prepend(
        				"<li class='list-group-item'>"+
    					"<input type='hidden' name='reviewId' value='" +review.id +"'/>" +
    						"<div class='row'>" +
    							"<div class='col-10'>" +
    								"<span>" + review.writer + "</span> <span class='fw-lighter'>" + "방금전"+ "</span>" +
    							"</div>" +
    							"<div class='col'>" +
    								"<button class='btn btn-danger'>삭제</button>" +
    							"</div>" +
    						"</div>" +
    						"<div class='row mt-1'>" +
    							"<p>" +review.content + "</p>" +
    						"</div>" +
    					"</li>"
        			);
        		},
        		error: function() {
        			alert("등록 실패");
        		}
			})
		})
	})
</script>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container">
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<c:forEach var="board" items="${boards}">
							<li class="nav-item"><a
								class="nav-link ${boardId == board.id ? 'active' : ''}"
								href="${contextPath}/board?boardId=${board.id}">${board.boardName}</a></li>
						</c:forEach>
					</ul>
				</div>

			</div>
		</nav>
	</header>

	<div class="container mt-5 mb-2">
		<input type="hidden" id="articleId" value="${article.id}" />
		<div class="input-group mb-3">
			<span class="input-group-text">제목</span> <input id="title"
				type="text" class="form-control" value="${article.title}"
				readonly="readonly">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">작성자</span> <input id="writer"
				type="text" class="form-control" value="${article.writer}"
				readonly="readonly">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">작성일</span> <input id="createdAt"
				type="text" class="form-control" value="${article.createdAt}"
				readonly="readonly">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">조회수</span> <input id="viewCount"
				type="text" class="form-control" value="${article.viewCount}"
				readonly="readonly">
		</div>
		<div class="border border-dark p-1">${article.content}</div>

		<!-- 댓글 입력  -->
		<div class="border border-info p-1 mt-2">
			<div class="input-group mb-3">
				<span class="input-group-text">작성자</span> 
				<input id="reviewWriter" type="text" class="form-control" placeholder="내용을 입력해주세요">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">내용</span> 
				<textarea id="reviewContent" class="form-control" ></textarea>
			</div>
			<div class="input-group">
				<span class="input-group-text">비밀번호</span> 
				<input id="reviewPassword" type="password" class="form-control" >
			</div>
			<button id="reviewCreatedBtn" type="button" class="btn btn-success mt-2">등록하기</button>
		</div>
	</div>
	
	<!-- 댓글 리스트 -->
	<div class="container">
		<ul class="list-group" id="reviewList">
			<c:forEach var="review" items="${reviews}">
				<li class="list-group-item ">
					<input type="hidden" name="reviewId" value="${review.id}"/>
					<div class="row">
						<div class="col-10">
							<span>${review.writer}</span> <span class="fw-lighter">${review.createdAt}</span>
						</div>
						<div class="col">
							<button class="btn btn-danger">삭제</button>
						</div>
					</div>
					<div class="row mt-1">
						<p>${review.content}</p>
					</div>
				</li>
			</c:forEach>

		</ul>

	</div>
</body>
</html>