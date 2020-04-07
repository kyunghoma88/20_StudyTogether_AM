<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<%@ page import = "com.kh.study.model.vo.Study, com.kh.join.model.vo.StudyJoin, java.util.List" %>
<%@ include file="/views/admin/adminHeader.jsp" %>
<%
	List<Study> sList = (List)request.getAttribute("sList");
%>
<div class="row">
  <form action="" class="form-group col" name="frm1">
    <h3>개설스터디 목록</h3>
    <table class="table">
          <thead class="text-center">
            <tr>
              <th><input type="checkbox" id="classSelect" onclick="check_class_toggle();"></th>
              <th>등록 번호</th>
              <th>개설자</th>
              <th>강좌명</th>
              <th>등록일</th>
              <th>강좌 폐강</th>
            </tr>
          </thead>
          <tbody class="text-center">
          <%if(sList.size()==0){ %>
          <tr>
          	<td colspan="5">조회된 스터디 목록이 없습니다</td>
          </tr>
          <%}else{ %>
          	<%for(Study s:sList){ %>
            <tr>
                <td class=""><input type="checkbox"></td>
                <td class=""><%=s.getStudyNo() %></td>
                <td class=""><%=s.getStudyWriter() %></td>
                <td class=""><%=s.getStudyName() %></td>
                <td class="">
                  <div class="form-check-inline">
                     <%=s.getEnrollDate() %>
                  </div>                        
                </td>
                <td class="">
                  <div class="form-check-inline">
                    <label class="form-check-label">
                      <button type="button" class="form-check-input btn btn-primary btn-sm">전송</button>
                    </label>
                  </div>
                </td>
            </tr>
            <%} 
          	} %>
          </tbody>
      </table>
    </form>
</div>
<%@ include file="/views/admin/adminFooter.jsp" %>