<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.kh.lector.model.vo.Lector, com.kh.join.model.vo.LectorJoin, java.util.List" %>
<%@ include file="/views/member/myPageHeader.jsp" %>
<%
	List<Lector> lcList = (List<Lector>)request.getAttribute("lcList");
	List<Lector> ljList = (List<Lector>)request.getAttribute("ljList");
	List<LectorJoin> ljMemberList = (List<LectorJoin>)request.getAttribute("ljMemberList");
%>

<div class="row">
	<h4>내가 개설한 강좌</h4>
</div>

<% if(lcList.size()==0){%>
<!-- 개설한 강좌가 없다면... -->
<div class="row">
	<h4><%=loginMember.getUserId()%>님께서는 아직 개설한 강좌가 없습니다</h4>
</div>
<div class="row">
	<a href="<%=request.getContextPath() %>/lector/lectorList">
		<p>새 강좌 개설하러 가기!</p>
	</a>
</div>
<%} else { %>
<!-- 개설한 강좌가 있다면... -->
<div class="row">
  <form action="" class="form-group col" name="frm1">
    <table class="table">
          <thead class="text-center">
            <tr>
              <th>등록 번호</th>
              <th>강좌명</th>
              <th>개설 승인 여부</th>
              <th>현재 참여자 수</th>
              <th>강좌 등록일</th>
            </tr>
          </thead>
          <tbody class="text-center">
          	<%for(Lector l:lcList){ %>
            <tr>
                <td class=""><%=l.getLectorNo() %></td>
                <td class=""><a href="<%=request.getContextPath() %>/lector/lectorView?pNo=<%=l.getLectorNo() %>"><%=l.getLectorTitle() %></a></td>
                <td><%=l.getLectorAssign().equals("N")?"관리자 승인 대기중"
                		:l.getLectorAssign().equals("F")?"관리자 승인거절"
                		:l.getLectorAssign().equals("Y")?"관리자 승인":"잘못된 상태" %></td>
                <td>현재 <%=ljMemberList.size() %>명 수강</td>
                <td class="">
                  <div class="form-check-inline">
                  		<%=l.getLectorDate() %>
                  </div>                        
                </td>
            </tr>
            <%} %>
          </tbody>
      </table>
    </form>
</div>
<%} %>

<div class="row">
    <h4>내가 참여한 강좌</h4>
</div>
<% if(ljList.size()==0){%>
<!-- 참여한 강좌가 없다면... -->
<div class="row">
	<h6><%=loginMember.getUserId()%>님께서는 아직 참여한 강좌가 없습니다</h6>
</div>
<div class="row">
	<a href="<%=request.getContextPath() %>/lector/lectorList">
		<p>새 강좌 보러가기!</p>
	</a>
</div>

<%}else{ %>
<!-- 참여한 강좌가 있다면... -->
<div class="row">
  <form action="" class="form-group col" name="frm1">
    <a href="<%=request.getContextPath() %>/lector/lectorList">
	</a>
    <table class="table">
          <thead class="text-center">
            <tr>
              <th>등록 번호</th>
              <th>강사명</th>
              <th>강좌명</th>
              <th>등록일</th>
              <th>현재 참여자 수</th>
            </tr>
          </thead>
          <tbody class="text-center">
          <%for(Lector l:ljList){ %>
            <tr>
                <td class=""><%=l.getLectorNo() %></td>
                <td class=""><%=l.getLectorWriter() %></td>
                <td class=""><%=l.getLectorTitle() %></td>
                <td class="">
                  <div class="form-check-inline">
                     <%=l.getLectorDate() %>
                  </div>                        
                </td>
                <td>현재 <%=ljMemberList.size() %>명 수강</td>
            </tr>      
            <%} %>
          </tbody>
      </table>
    </form>
</div>
<%} %>


<%@ include file="/views/member/myPageFooter.jsp" %>