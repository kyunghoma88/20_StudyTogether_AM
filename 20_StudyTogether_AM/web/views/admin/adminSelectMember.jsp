<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.kh.member.model.vo.Member" %>
<%@ include file="/views/admin/adminHeader.jsp" %>
<%
	List<Member> list=(List)request.getAttribute("list");
%>
<div class="row">
  <form action="" class="form-group col" name="frm1">
    <h3>가입회원 조회</h3>
    <table class="table">
          <thead class="text-center">
            <tr>
              <th>아이디</th>
              <th>고객명</th>
              <th>이메일</th>
              <th>별명</th>
              <th>성별</th>
              <th>연락처</th>
              <th>가입일</th>
            </tr>
          </thead>
          <tbody class="text-center">
          <%for(Member m:list){ 
          		if(list.isEmpty()){ %>
          	<tr>
          		<td colspan="7">검색된 회원이 없습니다</td>
          	</tr>
			<%} else { %>
            <tr>
            	<td><%=m.getUserId() %></td>
            	<td><%=m.getUserName() %></td>
            	<td><%=m.getEmail() %></td>
            	<td><%=m.getNickName()!=null?m.getNickName():"미입력" %></td>
            	<%-- <td><%=m.getGender()!=null?(m.getGender().equals("M")?"남":m.getGender().equals("F")?"여":""):"" %></td> --%>
            	<td>
            		<%
	            		switch(m.getGender()!=null?m.getGender():"기본"){
	            			case "M" : %>
	            			<span>남</span> 
	            		<% break;
	            			case "F" : %>
	            			<span>여</span> 
	            		<% break;
	            			case "기본" : %>
	            			<span>미입력</span> 
	            		<% break;
	            		} 
            		%>
            	</td>
            	<td><%=m.getPhone()!=null?m.getPhone():"미입력" %></td>
            	<td><%=m.getEnrollDate()!=null?m.getEnrollDate():"미입력" %></td>
            </tr>
            <%}
            }%>
          </tbody>
      </table>
    </form>
</div>
<%@ include file="/views/admin/adminFooter.jsp" %>