# About Project

현대 IT&E 채용확정형 교육 3기를 통해 배운 내용을 바탕으로 진행한 스프링 프레임워크를 이용한 CRUD 게시판 프로젝트 과제입니다.

# ⚒ 사용한 기술

<div>
   <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
   <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
   <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> 
   <img src="https://img.shields.io/badge/maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"> 
   <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
   <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
</div>

# 구현한 기능

<img width="1008" alt="스크린샷 2022-04-19 오후 11 48 00" src="https://user-images.githubusercontent.com/41534475/164031638-4685917f-2c5d-4388-9d98-a387f1e9c371.png">

# ERD 소개

<img width="717" alt="스크린샷 2022-04-19 오후 11 49 20" src="https://user-images.githubusercontent.com/41534475/164031932-11638ca5-2748-468a-8d2f-07308aee2e44.png">

1. BOARD : 게시판 테이블
2. ARTICLE : 게시글 테이블
3. ARTICLE_IMAGE : 게시글에 포함된 이미지 테이블
4. REVIEW : 댓글 테이블

# 위지윅 에디터 커스텀

<img width="1007" alt="스크린샷 2022-04-19 오후 11 57 19" src="https://user-images.githubusercontent.com/41534475/164033634-51f5344d-889a-4321-a626-d0327a1effe2.png">

- Jquery 기반 위지윅 에디터 오픈 소스 라이브러리 `summer note` 를 이용하여 게시글 작성 및 이미지 업로드 및 다운로드 기능 커스텀을 하였습니다.

# 썸네일 만들기

<img width="1068" alt="스크린샷 2022-04-20 오전 12 13 36" src="https://user-images.githubusercontent.com/41534475/164036870-69b903c5-ef58-47ba-9a57-696be1c55dfe.png">

1. Java 라이브러리 `Jsoup`를 이용하여 게시글 본문 html 문자열에서 img 태그 추출
2. img 태그에 src 에서 업로드 된 image URL 에서 이미지 이름 파싱
3. 파싱한 이미지들 중에서 첫 번째 이미지를 썸네일로 지정

# 화면 소개

1. **게시판 목록 화면**
<img width="1337" alt="스크린샷 2022-04-20 오전 12 08 45" src="https://user-images.githubusercontent.com/41534475/164035901-8b3081da-fbb5-4ec9-a64a-e831616192b9.png">

2. **게시글 등록 화면**
<img width="1200" alt="스크린샷 2022-04-20 오전 12 10 13" src="https://user-images.githubusercontent.com/41534475/164036218-659b69f1-ae3b-4116-a177-1356dbed6d8b.png">

3. **게시글 조회 및 댓글 화면**
![게시글 조회](https://user-images.githubusercontent.com/41534475/164039024-bf1fa93a-1052-408a-a0f7-609ce1322f57.gif)


