<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="startPage" value="${articlePagination.startPageNumber}" />


<c:set var="endPage" value="${articlePagination.endPageNumber}" />
<c:set var="nextPage" value="${articlePagination.nextPage}" />
<c:set var="prevPage" value="${articlePagination.prevPage}" />
<c:set var="page" value="${articlePagination.page}" />


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
</head>
<body>


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

	<div class="container mt-3">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">No</th>
					<th scope="col">썸네일</th>
					<th scope="col">Title</th>
					<th scope="col">작성자</th>
					<th scope="col">조회수</th>
					<th scope="col">등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${articlePagination.data}"
					varStatus="status">
					<tr>
						<th scope="row">${(page-1)*articlePagination.limit + status.count}</th>
						<td> 
						<c:if test="${article.thumbnail != null}">
							<img alt="썸네일" src="${contextPath}/image/display/${article.thumbnail.imageName}" width="100" height="100">
						</c:if>
						<c:if test="${article.thumbnail == null}">
							<img src="https://via.placeholder.com/100" alt="샘플이미지">
						</c:if>
						</td>
						<td><a href="${contextPath}/article/${article.id}?boardId=${boardId}">${article.title}</a></td>
						<td>${article.title}</td>
						<td>${article.viewCount}</td>
						<td><fmt:formatDate value="${article.createdAt}" type="date" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- pagination area  -->
		<nav aria-label="Page navigation m-auto">
			<ul class="pagination justify-content-center">
				<li class="page-item ${not isFirst ? '' : 'disabled'}">
      				<a class="page-link" href="${contextPath}/board?boardId=${boardId}&page=${prevPage}" tabindex="-1" aria-disabled="${isFirst}">이전</a>
    			</li>
    			<c:forEach begin="${startPage}" end="${endPage}" var="pg">
    				<li class="page-item ${page == pg ? 'active' : ''}">
    					<a class="page-link" href="${contextPath}/board?boardId=${boardId}&page=${pg}">${pg}</a>
    				</li>
				</c:forEach>
				<li class="page-item ${not isLast ?  '' : 'disabled'}">
      				<a class="page-link" href="${contextPath}/board?boardId=${boardId}&page=${nextPage}" tabindex="-1" aria-disabled="${isLast}">다음</a>
   				 </li>
			</ul>
		</nav>


		<a href="${contextPath}/article/write?boardId=${boardId}">등록하러가기</a>

	</div>

</body>
</html>