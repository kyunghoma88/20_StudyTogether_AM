<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.kh.study.model.vo.Study, com.kh.member.model.vo.StudyJoinMember" %>
<%@ include file="/views/member/myPageHeader.jsp" %>
<%
	List<Study> scList = (List)request.getAttribute("scList");
	List<Study> sjList = (List)request.getAttribute("sjList");
	List<StudyJoinMember> mList = (List)request.getAttribute("mList");
	
%>
<div class="row">
	<h4>내가 개설한 스터디</h4>
</div>
<%if(scList.size()==0){%>
<div class="row">
	<h4><%=loginMember.getUserId()%>님께서는 아직 개설한 스터디가 없습니다</h4>
</div>
<div class="row">
	<a href="<%=request.getContextPath() %>/study/studyList">
		<p>새 스터디 만들러 가기!</p>
	</a>
</div>
<%}else {%>
<!-- 개설한 스터디가 있다면... -->
<div class="row">
  <form action="" class="form-group col" name="frm1">
    <table class="table">
          <thead class="text-center">
            <tr>
              <th>등록 번호</th>
              <th>카테고리</th>
              <th>스터디명</th>
              <th>최대 인원수</th>
              <th>등록일</th>
              <th>기능</th>
            </tr>
          </thead>
          <tbody class="text-center">
          		<%for(Study s:scList){ %>
	            <tr>
	                <td class=""><%=s.getStudyNo() %></td>
	                <td class=""><%=s.getStudyCategory() %></td>
	                <td class=""><a href="<%=request.getContextPath() %>/study/studyView?no=<%=s.getStudyNo() %>"><%=s.getStudyName() %></a></td>
	                <td class="">
	                	<%=s.getMaxMember()>0&&s.getMaxMember()<10?"<div class='bg-warning text-white'>"+s.getMaxMember()+"</div>"
	   					:s.getMaxMember()>=10&&s.getMaxMember()<30?"<div class='bg-info text-white'>"+s.getMaxMember()+"</div>"
	   					:s.getMaxMember()>=30?"<div class='bg-success text-white'>"+s.getMaxMember()+"</div>"
	   					:"<div class='bg-danger text-white'>"+s.getMaxMember()+"</div>"%>
	                </td>
	                <td class="">
	                  <div class="form-check-inline">
	                     <%=s.getEnrollDate() %>
	                  </div>                        
					</td>
	                <td class="">
		                <div class="form-check-inline">
		                    <label class="form-check-label">
		                      <button type="button" class="form-check-input btn-primary btn-sm" onclick="toggle(<%=s.getStudyNo()%>)">연락처 보기</button>
		                      <button type="button" class="form-check-input btn-secondary btn-sm target" id="stu<%=s.getStudyNo()%>">폐강하기</button>
		                      <input type="hidden" id="btn<%=s.getStudyNo() %>" name="lecNo" value="<%=s.getStudyNo() %>">
		                    </label>
		                  </div>
	                </td>
	            </tr>
			  <%int count=0; %>
	          <%for(int i=0;i<mList.size();i++){
	         		StudyJoinMember sjm=mList.get(i);%>
	        <%if(i==0){ %>
	        <tr class="info<%=s.getStudyNo()%>" style='display:none;'>
	              <th>스터디 번호</th>
	              <th>이름</th>
	              <th>아이디</th>
	              <th>회원명</th>
	              <th>연락처</th>
	              <th>이메일</th>
		     </tr>
	        <%}%>
	        <%if(sjm.getStudyNo()==s.getStudyNo()) {%>
	          <tr class="info<%=s.getStudyNo()%>" style='display:none;'>
	              <td class=""><%=sjm.getStudyNo() %></td>
	              <td class=""><%=sjm.getStudyName() %></td>
	              <td class=""><%=sjm.getUserId() %></td>
	              <td class=""><%=sjm.getUserName() %></td>
	              <td class=""><%=sjm.getPhone()!=null?sjm.getEmail():"미입력" %></td>
	              <td class=""><%=sjm.getEmail()!=null?sjm.getEmail():"미입력" %></td>
		     </tr>
	  	 		<%count++;
	  	 		} else {
	  	 			if(i==mList.size()-1 && count==0){
	  	 			count=0;%>
			<tr class="info<%=s.getStudyNo()%>" style='display:none;'>
			  <td colspan="6">현재 스터디에는 참여한 회원이 없네요</td>
			</tr>
  	 			<%} else {%>
  	 			<%}%>
  	 		<%}%>
 		<%} %>
	<%} %> <!-- scList -->
 <%} %><!-- else -->
          </tbody>
      </table>
    </form>
</div>


<div class="row">
    <h4>내가 참여한 스터디</h4>
</div>
<%if(sjList.size()==0) {%>
<div class="row">
	<h6><%=loginMember.getUserId()%>님께서는 아직 참여한 스터디가 없습니다</h6>
</div>
<div class="row">
	<a href="<%=request.getContextPath() %>/study/studyList">
		<p>새 스터디 보러가기!</p>
	</a>
</div>
<%}else{ %>
<!-- 개설한 스터디가 있다면... -->
<div class="row">
  <form action="" class="form-group col" name="frm1">
    <a href="<%=request.getContextPath() %>/lector/lectorList">
	</a>
    <table class="table">
          <thead class="text-center">
            <tr>
              <th>등록 번호</th>
              <th>카테고리</th>
              <th>스터디명</th>
              <th>최대 인원수</th>
              <th>등록일</th>
              <th>기능</th>
            </tr>
          </thead>
          <tbody class="text-center">
				<%for(Study s:sjList){ %>
            <tr>
                <td class=""><%=s.getStudyNo() %></td>
                <td class=""><%=s.getStudyCategory() %></td>
                <td class=""><a href="<%=request.getContextPath() %>/study/studyView?no=<%=s.getStudyNo() %>"><%=s.getStudyName() %></a></td>
                <td class="">
                <%=s.getMaxMember()>0&&s.getMaxMember()<10?"<div class='bg-warning text-white'>"+s.getMaxMember()+"</div>"
   					:s.getMaxMember()>=10&&s.getMaxMember()<30?"<div class='bg-info text-white'>"+s.getMaxMember()+"</div>"
   					:s.getMaxMember()>=30?"<div class='bg-success text-white'>"+s.getMaxMember()+"</div>"
   					:"<div class='bg-danger text-white'>"+s.getMaxMember()+"</div>"%>
                </td>
                <td class="">
                  <div class="form-check-inline">
                  	<%=s.getEnrollDate() %>
                  </div>                        
				</td>
                <td class="">
					<div class="form-check-inline">
	                    <label class="form-check-label">
	                      <button type="button" class="form-check-input btn-secondary btn-sm target" id="stu<%=s.getStudyNo()%>">탈퇴하기</button>
	                      <input type="hidden" id="btn<%=s.getStudyNo() %>" name="lecNo" value="<%=s.getStudyNo() %>">
	                    </label>
	                  </div>
				</td>
            </tr>
       	  <%} %>
        <%} %>      
          </tbody>
      </table>
    </form>
</div>


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
                let stuNo=id.replace("stu","");
                conf=confirm(stuNo+"번 스터디로 진행 하시겠습니까?");
		        if(conf){
		        	let newForm = document.createElement('form');
	    			newForm.method = 'POST';
	    			newForm.action = '<%=request.getContextPath()%>/admin/adminManageStudyEnd';
	    			newForm.name = 'newForm';

	    			let data = document.createElement('input');
	    			data.setAttribute('type', 'hidden');
	    			data.setAttribute('name', 'stuNoArr');
	    			data.setAttribute('value', "");
	    			newForm.appendChild(data);
	    			
	    			data = document.createElement('input');
	    			data.setAttribute('type', 'hidden');
	    			data.setAttribute('name', 'stuNo');
	    			data.setAttribute('value', stuNo);
	    			newForm.appendChild(data);
	    			
	    			document.body.appendChild(newForm);
	    			newForm.submit();
		        }else{
		        	return;
		        }
        	});
        }
        
    	function toggle(e){
    		var str='info'+String(e);
    		var info=document.getElementsByClassName(str);
    		var infoLength=info.length;
    		console.log(infoLength);
    		
    		for(let i=0; i<infoLength; i++){
	    		if(info[i].classList.contains('on')){
	    			info.item(i).classList.remove('on')
	    			info.item(i).style.display="";
	    		}else{
	    			info.item(i).classList.add('on')
	    			info.item(i).style.display="none";
	    		}
    		}
    	}
        
    </script>
<%@ include file="/views/member/myPageFooter.jsp" %>