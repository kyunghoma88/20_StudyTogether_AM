<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.kh.lector.model.vo.Lector" %>
<%@ include file="/views/admin/adminHeader.jsp" %>

<%
	List<Lector> list=(List)request.getAttribute("lectorGrantList");
%>
<div class="row">
            <h3>승인요청한 강좌</h3>
            <table class="table">
              <thead class="text-center">
                  <tr>
                    <th>등록 번호</th>
                    <th>이미지</th>
                    <th>강좌명</th>
                    <th>강사명</th>
                    <th>가격</th>
                    <th colspan="2">승인 여부</th>
                    <!-- <th>전송</th> -->
                  </tr>
                </thead>
                <tbody class="text-center">
                  <%for(Lector l:list){ %>
		          <form id="lec<%=l.getLectorNo()%>" action="<%=request.getContextPath() %>/admin/adminGrantLectorEnd" class="form-group col">
                  <tr>
                      <td>
                      	<%=l.getLectorNo()%>
                      	<input type="hidden" name="lecNo" value="<%=l.getLectorNo()%>">
                      </td>
                      <td>
                          <img class="img-thumbnail" style="width:100px;height:100px;" src="http://placehold.it/100x100" alt="">
                      </td>
                      <td>
                        <a href="<%=request.getContextPath() %>/lector/lectorView?pNo=<%=l.getLectorNo()%>"><%=l.getLectorTitle() %></a>
                      </td>
                      <td><%=l.getLectorWriter() %></td>
                      <td><%=l.getLectorPrice() %>원</td>
                      <td>
                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="optradio" value="Y">허용
                            </label>
                        </div>
                        <div class="form-check-inline">
                        <label class="form-check-label">
                          <input type="radio" class="form-check-input" name="optradio" value="N">거부
                        </label>
                        </div>
                      </td>
                      <td>
                          <button type="button" class="btn btn-primary btn-sm" onclick="confirmGrant(this);">전송</button>
                      </td>
                  </tr>      
		          </form>
                  <%} %>      
                </tbody>
              </table>
        </div>
		<script>
			function confirmGrant(){
				console.log(this);
				console.log(this.optradio.value);
				console.log(this.lecNo.value);
				
				var opt=this.optradio.value;
				var check=false;
				if(lecNo=='Y'){
					check=confirm("강좌 개설을 승인하시겠습니까?");
					if(check) document.getElementById(lecNoc).submit();
					else return;
				}else if(lecNo='N'){
					check=confirm("번 강좌 개설을 거절하시겠습니까?");
					if(check) document.getElementById(lecNoc).submit();
					else return;
				}

			}
		</script>
<%@ include file="/views/admin/adminFooter.jsp" %>
