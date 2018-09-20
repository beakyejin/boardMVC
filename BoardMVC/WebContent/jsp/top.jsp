<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
	<h1>${title}</h1>
</header>
<nav>
	<ul>
		<!-- <li><a href="home">Home</a></li> -->
		<li class="dropdowns"><a href="boardList.bo?btype=0&page=1">Board</a>
			<ul class="submenus">
				<li><a href="boardList.bo?btype=0&page=1">게시판1</a></li>
				<li><a href="boardList.bo?btype=1&page=1">게시판2</a></li>
				<li><a href="boardList.bo?btype=2&page=1">게시판3</a></li>
			</ul>
		</li>
	</ul>
</nav>
