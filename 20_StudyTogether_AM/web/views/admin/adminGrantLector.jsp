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
                          <img class="img-thumbnail" style="width:100px;height:100px;" src="data:image/jpeg;base64,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" alt="">
                      </td>
                      <td>
                        <a href="<%=request.getContextPath() %>/lector/lectorView?pNo=<%=l.getLectorNo()%>"><%=l.getLectorTitle() %></a>
                      </td>
                      <td><%=l.getLectorWriter() %></td>
                      <td><%=l.getLectorPrice() %>원</td>
                      <td>
                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" id="optradio<%=l.getLectorNo()%>" name="optradio" value="Y">허용
                            </label>
                        </div>
                        <div class="form-check-inline">
	                        <label class="form-check-label">
	                          <input type="radio" class="form-check-input" id="optradio<%=l.getLectorNo()%>" name="optradio" value="N">거부
	                        </label>
                        </div>
                      </td>
                      <td>
                          <button type="button" class="btn btn-primary btn-sm" onclick="confirmGrant(<%=l.getLectorNo()%>);">전송</button>
                      </td>
                  </tr>      
		          </form>
                  <%} %>      
                </tbody>
              </table>
        </div>
		<script>
			function confirmGrant(no){
				
				var str="".concat(String(no));
				let id='lec'.concat(str);
				let form=document.getElementById(id);
				let opt="optradio".concat(str);
				let val=document.getElementById(opt).value;
				var check=false;
				var isChecked=$('input[name=optradio]').is(':checked');
				
				if(isChecked){
					check=confirm(str+"번 강좌 개설을 승인하시겠습니까?");
					if(check) form.submit();
					else return;
				} else {
					check=confirm(str+"번 강좌 개설을 거절하시겠습니까?");
					if(check) form.submit();
					else return;
				}

			}
		</script>
<%@ include file="/views/admin/adminFooter.jsp" %>
