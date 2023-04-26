<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="modify" />
<%@ include file="../common/head.jsp" %>
	<section class="mt-8 text-xl">
		<form action="modify">	
			<div class="container mx-auto px-3">
				<div class="table-box-type-1">
					<table>
						<tbody>
							<c:forEach var="article" items="${article }"  var="loginedMemberId" items="${loginedMemberId }">
							<tr>
								<th>게시글 수정 제목</th>
								<td>
									<input class="w-96" type="text" name="title" placeholder=${article.title }/>
								</td>	
							</tr>
							<tr>
								<th>게시글 수정 내용</th>	
								<td>
									<input class="w-96" type="text" name="body" placeholder=${article.body }/>
								</td>
								<td><button>수정하기</button></td>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</form>
			<div class="btns">
				<button class="btn-text-link" type="button" onclick="history.back();">뒤로가기</button>
			    <c:if test="${article.id == loginedMemberId}">
					<a class="btn-text-link" href="modify?id=${article.id }">수정</a>
					<a class="btn-text-link" href="doDelete?id=${article.id }">삭제</a>
   				 </c:if>
			</div>
			
			
	</section>
<%@ include file="../common/foot.jsp" %>