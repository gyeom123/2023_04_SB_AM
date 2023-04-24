<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Detali" />
<%@ include file="../common/head.jsp" %>
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<div class="table-box-type-1">
				<table>
					<tbody>
						<c:forEach var="article" items="${article }">
						<tr>
							<th>번호</th>
							<td>${article.id }</td>
						</tr>
						<tr>
							<th>작성 날짜</th>	
							<td>${article.regDate }</td>
						</tr>
						<tr>
							<th>작성자
							<td>${article.writerName }</td>
						</tr>
						<tr>
							<th>제목</th>
							<td>${article.title }</td>
						</tr>
						<tr>
							<th>내용
							<td>${article.body }</td>
						</tr>
						
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>