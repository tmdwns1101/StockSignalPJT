<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- bootstrap css -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">


<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			height: 300,                 // 에디터 높이
			minHeight: null,             // 최소 높이
			maxHeight: null,             // 최대 높이
			focus: false,                  // 에디터 로딩후 포커스를 맞출지 여부
			lang: "ko-KR",					// 한글 설정
			callbacks: {	//여기 부분이 이미지를 첨부하는 부분
				onImageUpload : function(files) {
					uploadSummernoteImageFile(files[0],this);
				}
			}
		});
		

        $(".note-group-image-url").remove();    //이미지 추가할 때 Image URL 등록 input 삭제 
        
        
        $("#submitBtn").click(function() {
        	var boardId = $("#boardId").val();
        	var title = $("#title").val();
        	var password = $("#password").val();
        	var writer = $("#writer").val();
        	var content = $('textarea[name="content"]').html($('#summernote').summernote('code'));
        	content = $('textarea[name="content"]').val();
        	
        	var data = {
        			boardId,
        			title,
        			writer,
        			password,
        			content
        	}
        	
        	console.log(data);
        	
        	$.ajax({
        		data: JSON.stringify(data),
        		type: "POST",
        		contentType: "application/json; charset=utf-8",
        		datatype: "json",
        		url: "${contextPath}/article/write",
        		success: function(data) {
        			alert("등록이 성공하였습니다.")
        			location.href = "${contextPath}" + data.redirectURL;
        		},
        		error: function() {
        			alert("등록에 실패하였습니다.");
        		}
        	})
        })
	});
	
	function uploadSummernoteImageFile(file, editor) {
		data = new FormData();
		data.append("file", file);
		$.ajax({
			data : data,
			type : "POST",
			url : "${contextPath}/image/upload",
			contentType : false,
			processData : false,
			success : function(data) {
				console.log(data);
				var imageURL = "${contextPath}/image/display/"+data[0];
				$(editor).summernote('insertImage', imageURL);
			},
			error: function() {
				alert("이미지 업로드에 실패하였습니다.");
			}
		});
	}
</script>
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

	<div class="container mt-5">
		<input type="hidden" id="boardId" value="${boardId}"/>
		<div class="input-group mb-3">
			<span class="input-group-addon">제목</span>
			<input id="title" type="text" class="form-control" placeholder="제목을 입력해주세요." >
		</div>
		<div class="input-group mb-3">
			<span class="input-group-addon">작성자</span>
			<input id="writer" type="text" class="form-control" placeholder="게시글 작성자를 입력해주세요." >
		</div>
		<div class="input-group mb-3">
			<span class="input-group-addon" >비밀번호</span>
			<input id="password" type="password" class="form-control" placeholder="비밀번호를 입력해주세요." >
			
		</div>
		<div id="summernote"></div>
		<textarea hidden="hidden" name="content"></textarea>
		<button id="submitBtn" type="button" class="btn btn-success">등록하기</button>
	</div>
</body>
</html>