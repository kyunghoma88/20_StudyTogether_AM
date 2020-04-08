<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.kh.lector.model.vo.Lector" %>
<%@ include file="/views/admin/adminHeader.jsp" %>

<%
	List<Lector> list=(List)request.getAttribute("list");
%>
<div class="row">
  <form action="" class="form-group col" name="frm1">
    <h3>개설강좌 목록</h3>
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
           	<%for(Lector l:list){ %>
            <tr>
                <td class=""><input type="checkbox" name="checked" value="<%=l.getLectorNo() %>"></td>
                <td class=""><%=l.getLectorNo() %></td>
                <td class=""><%=l.getLectorWriter() %></td>
                <td class=""><%=l.getLectorTitle()%></td>
                <td class="">
                  <div class="form-check-inline">
                     <%=l.getLectorDate() %>
                  </div>                        
                </td>
                <td class="">
                  <div class="form-check-inline">
                    <label class="form-check-label">
                      <button type="button" class="form-check-input btn btn-primary btn-sm" onclick="close(this);">전송</button>
                    </label>
                  </div>
                </td>
            </tr>
            <%} %>      
          </tbody>
      </table>
      <button type="button" onclick="closeCheckedAll();"></button>
    </form>
    <script>
    	function close(e){
    		
    	}
    	function closecheckedAll(e){
    		
    	}
    </script>
</div>
<%@ include file="/views/admin/adminFooter.jsp" %>