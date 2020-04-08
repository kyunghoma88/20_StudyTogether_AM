<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.kh.lector.model.vo.Lector, com.kh.join.model.vo.LectorJoin, java.util.List, java.text.DecimalFormat" %>
<%@ include file="/views/member/myPageHeader.jsp" %>
<%
	List<Lector> lcList = (List<Lector>)request.getAttribute("lcList");
	List<Lector> ljList = (List<Lector>)request.getAttribute("ljList");
	
	DecimalFormat formatter=new DecimalFormat();
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
              <th>개설한 날짜</th>
              <th>강좌 폐쇄</th>
            </tr>
          </thead>
          <tbody class="text-center">
          	<%for(Lector l:lcList){ %>
          <form action="<%=request.getContextPath() %>/admin/adminManageLectorEnd" class="form-group col" method="post" name="frm1">
            <tr>
                <td class=""><%=l.getLectorNo() %></td>
                <td class=""><a href="<%=request.getContextPath() %>/lector/lectorView?pNo=<%=l.getLectorNo() %>"><%=l.getLectorTitle() %></a></td>
                <td><%=l.getLectorAssign().equals("N")?"<span class='bg-danger text-white'>승인 거절</span>"
                		:l.getLectorAssign().equals("F")?"<span class='bg-warning text-white'>승인 대기중</span>"
                		:l.getLectorAssign().equals("Y")?"<span class='bg-success text-white'>승인 완료</span>":"잘못된 상태" %></td>
                <td class=""><%=l.getLectorDate() %></td>
                <td class="">
                	<div class="form-check-inline">
                    <label class="form-check-label">
                      <button type="button" class="form-check-input btn-secondary btn-sm target" id="lec<%=l.getLectorNo()%>">전송</button>
                      <input type="hidden" id="btn<%=l.getLectorNo() %>" name="lecNo" value="<%=l.getLectorNo() %>">
                      <input type="hidden" id="" name="lecNoArr" value="">
                    </label>
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
              <th>금액</th>
              <th>등록일</th>
              <th>강좌 탈퇴</th>
            </tr>
          </thead>
          <tbody class="text-center">
          <%for(Lector l:ljList){ %>
            <tr>
                <td class=""><%=l.getLectorNo() %></td>
                <td class=""><%=l.getLectorWriter() %></td>
                <td class=""><a href="<%=request.getContextPath() %>/lector/lectorView?pNo=<%=l.getLectorNo() %>"><%=l.getLectorTitle() %></a></td>
                <td class=""><h4><%=formatter.format(l.getLectorPrice()) %>원</h4></td>
                <td class=""><%=l.getLectorDate() %></td>
                <td class="">
                   <div class="form-check-inline">
                    <label class="form-check-label">
                      <button type="button" class="form-check-input btn-secondary btn-sm target" id="lec<%=l.getLectorNo()%>">전송</button>
                      <input type="hidden" id="btn<%=l.getLectorNo() %>" name="lecNo" value="<%=l.getLectorNo() %>">
                      <input type="hidden" id="" name="lecNoArr" value="">
                    </label>
                  </div>
                </td>
            </tr>      
            <%} %>
          </tbody>
      </table>
    </form>
</div>
<%} %>
<script>
    var target = document.querySelectorAll(".target");
      var targetLength = target.length;
      
      let conf=false;
      
      for(var i=0; i < targetLength; i++){
          target[i].addEventListener("click",function(){
          	console.log(this);
              console.log("#btn"+this.id+"에 이벤트 추가");
      		let id=this.id
      		console.log(id);
      		let form=document.getElementById(id);
              console.log(form);
              let lecNo=id.replace("lec","");
              conf=confirm(id.replace("lec","")+"번 강좌로 진행 하시겠습니까?");
        if(conf){
        	let newForm = document.createElement('form');
   			newForm.method = 'POST';
   			newForm.action = '<%=request.getContextPath()%>/admin/adminManageLectorEnd';
   			newForm.name = 'newForm';

   			let data = document.createElement('input');
   			data.setAttribute('type', 'hidden');
   			data.setAttribute('name', 'lecNoArr');
   			data.setAttribute('value', "");
   			newForm.appendChild(data);
   			
   			data = document.createElement('input');
   			data.setAttribute('type', 'hidden');
   			data.setAttribute('name', 'lecNo');
   			data.setAttribute('value', lecNo);
   			newForm.appendChild(data);
   			
   			document.body.appendChild(newForm);
   			newForm.submit();
        }else{
        	return;
        }
      	});
      }
       
</script>

<%@ include file="/views/member/myPageFooter.jsp" %>