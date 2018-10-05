<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<input type="hidden" name="bid" value="${param.bid}">
	<input type="hidden" name="btype" value="${param.btype}">
	<table class="datatable">
	<c:forEach var="vo" items="${data}">
		<tr>
			<td>제목</td>
			<td>${vo.btitle}</td>
		</tr>
		<tr>
			<td>작성일시</td>
			<td>${vo.bregdate}</td>
		</tr>
		<tr>
			<td colspan="2">${vo.bcontent}</td>
		</tr>
	</c:forEach>
	</table>
	<div class="btndiv">
		<input type="button" value="목록" onclick="location.href='boardList.bo?btype=${param.btype}&page=${param.page}'">
		<input type="button" value="수정" onclick="location.href='boardWrite.bo?btype=${param.btype}&bid=${param.bid}&page=${param.page}'">
		<input type="button" value="삭제" onclick="location.href='boardDelete.bo?btype=${param.btype}&bid=${param.bid}&page=${param.page}'">
	</div>
	<form action="commentWrite.bo?btype=${param.btype}&bid=${param.bid}" method="post">
	<div class="comment">
		<input type="hidden" name="bid" value="${param.bid}">
		<input type="hidden" name="btype" value="${param.btype}">
		<div class="sub">
			<textarea id="comment" name="comment"></textarea> 
			<input type="submit" class="cmtbtn" value="댓글 등록">
		</div>
		<div>
		<c:choose>
		<c:when test="${cmtlsize > 0}">
			<table class="cmttable">
			<c:forEach var="vo2" items="${cmtlist}" varStatus="state" >
				<tr>
					<td style="text-align: center">${state.count}</td>
					<td style="text-align: center; width: 900px">${vo2.t_comment}</td>
					<td style="text-align: center">${vo2.cregdate}</td>
					<td>
						<input type="button" value="삭제" onclick="location.href='commentDelete.bo?cid=${vo2.cid}&bid=${vo2.bid}&btype=${vo2.btype}'">
					</td>
				</tr>
			</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<div class="empty"><p>댓글이 없습니다.</p></div>
		</c:otherwise>			
		</c:choose>
		</div>
	</div>
	</form>
	
</div>