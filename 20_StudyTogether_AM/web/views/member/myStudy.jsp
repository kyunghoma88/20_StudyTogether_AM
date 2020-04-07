<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.kh.study.model.vo.Study, com.kh.join.model.vo.StudyJoin, java.util.List" %>
<%@ include file="/views/member/myPageHeader.jsp" %>
<%
	List<Study> scList = (List)request.getAttribute("scList");
	List<Study> sjList = (List)request.getAttribute("sjList");
	System.out.println("내스터디도착");
%>

<div class="row">
	<h4>내가 개설한 스터디</h4>
</div>
<%if(scList.size()==0){ %>
<div class="row">
	<h4><%=loginMember.getUserId()%>님께서는 아직 개설한 스터디가 없습니다</h4>
</div>
<div class="row">
	<a href="<%=request.getContextPath() %>/study/studyList">
		<p>새 스터디 만들러 가기!</p>
	</a>
</div>
<%}else{ %>
<!-- 개설한 스터디가 있다면... -->
<div class="row">
  <form action="" class="form-group col" name="frm1">
    <table class="table">
          <thead class="text-center">
            <tr>
              <th>등록 번호</th>
              <th>스터디명</th>
              <th>카테고리</th>
              <th>최대 인원수</th>
              <th>등록일</th>
            </tr>
          </thead>
          <tbody class="text-center">
          <%for(Study s:scList){ %>
            <tr>
                <td class=""><%=s.getStudyNo() %></td>
                <td class=""><%=s.getStudyName() %></td>
                <td class=""><%=s.getStudyCategory() %></td>
                <td class=""><%=s.getMaxMember() %></td>
                <td class="">
                  <div class="form-check-inline">
                     <%=s.getEnrollDate() %>
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
    <h4>내가 참여한 스터디</h4>
</div>
<%if(sjList.size()==0){ %>
<div class="row">
	<h6><%=loginMember.getUserId()%>님께서는 아직 참여한 스터디가 없습니다</h6>
</div>
<%}else{ %>
<div class="row">
	<a href="<%=request.getContextPath() %>/study/studyList">
		<p>새 스터디 보러가기!</p>
	</a>
</div>
<!-- 개설한 스터디가 있다면... -->
<div class="row">
  <form action="" class="form-group col" name="frm1">
    <a href="<%=request.getContextPath() %>/lector/lectorList">
	</a>
    <table class="table">
          <thead class="text-center">
            <tr>
              <th>등록 번호</th>
              <th>스터디명</th>
              <th>카테고리</th>
              <th>최대 인원수</th>
              <th>등록일</th>
            </tr>
          </thead>
          <tbody class="text-center">
          <%for(Study s:sjList){ %>
            <tr>
                <td class=""><%=s.getStudyNo() %></td>
                <td class=""><%=s.getStudyName() %></td>
                <td class=""><%=s.getStudyCategory() %></td>
                <td class=""><%=s.getMaxMember() %></td>
                <td class="">
                  <div class="form-check-inline">
                     <%=s.getEnrollDate() %>
                  </div>                        
                </td>
            </tr>      
            <%} %>
          </tbody>
      </table>
    </form>
</div>
<%} %>


<%@ include file="/views/member/myPageFooter.jsp" %>