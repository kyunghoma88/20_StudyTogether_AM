<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.lector.model.vo.Lector" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/lecStuOpen.css" type="text/css"/>
<%
	Lector l=(Lector)request.getAttribute("lector");
%>
<%@ include file="/views/common/header.jsp"%>

<form id="regFrm" action="<%=request.getContextPath()%>/lector/lectorUpdateEnd" method="post" enctype="multipart/form-data">
	  <br><h2 id="openTitle">강좌 수정</h2>
	  <input type="hidden" name="pNo" value="<%=l.getLectorNo() %>" />
	   <table class="openWrite">
	   <tr>
			<td>강사명</td>
			<td><%=loginMember.getUserId()%><input type="hidden" name="lectorWriter" value="<%=loginMember.getUserId()%>" readonly required/></td>
		</tr>
    	<tr>
			<td>강좌 이름</td>
			<td><input type="text" name="lectorTitle" value="<%=l.getLectorTitle()%>"></td>
		</tr>
		<tr>
		<td>강좌 분야</td>
		<td> 
		<select id="searchType" name="searchType" > 
     			<option value="카테고리">카테고리</option>
     		<optgroup label="어학,회화">
        		<option value="영어" <%=l!=null&&l.getLectorCategory().equals("영어")?"selected":"" %>>영어</option>
         		<option value="일본어" <%=l!=null&&l.getLectorCategory().equals("일본어")?"selected":"" %>>일본어</option>
        		<option value="스페인어" <%=l!=null&&l.getLectorCategory().equals("스페인어")?"selected":"" %>>스페인어</option>
         		<option value="불어" <%=l!=null&&l.getLectorCategory().equals("불어")?"selected":"" %>>불어</option>
      		</optgroup>
	      	<optgroup label="자격증">
	          <option value="제빵" <%=l!=null&&l.getLectorCategory().equals("제빵")?"selected":"" %>>제빵</option>
	          <option value="정보처리기사" <%=l!=null&&l.getLectorCategory().equals("정보처리기사")?"selected":"" %>>정보처리기사</option>
	          <option value="컴퓨터활용" <%=l!=null&&l.getLectorCategory().equals("컴퓨터활용")?"selected":"" %>>컴퓨터활용</option>
	          <option value="토익" <%=l!=null&&l.getLectorCategory().equals("토익")?"selected":"" %>>토익</option>
	      	</optgroup>
	      	<optgroup label="IT">
	          <option value="알고리즘" <%=l!=null&&l.getLectorCategory().equals("알고리즘")?"selected":"" %> >알고리즘</option>
	          <option value="데이터베이스" <%=l!=null&&l.getLectorCategory().equals("데이터베이스")?"selected":"" %>>데이터베이스</option>
	          <option value="자바프로그래밍" <%=l!=null&&l.getLectorCategory().equals("자바프로그래밍")?"selected":"" %>>자바프로그래밍</option>
	      	</optgroup>
      </select>
      </td>
	</tr>
      <script>
     document.regFrm.searchType.value="<%=l.getLectorCategory()%>"    
      </script>
      <tr>
		<td>상세 소개</td>
		<td><textarea name="intro" cols="85" rows="10" style="resize: none;"  placeholder=""><%=l.getLectorDetail()%></textarea></td>
	  </tr>	
      <tr>
		<td>썸네일<br>이미지</td>
		<td>
			<input type="file" name="lectorImg" />
            <%if(l.getLectorOriginalImg()!=null){ %>
            <span id="fname"><%=l.getLectorOriginalImg() %></span>
             <input type="hidden" name="lectorImg1" value="<%=l.getLectorOriginalImg()%>">
           <%} %>
         </td>
	  </tr>	
  	  <tr>
		<td>강좌<br>비디오<br>첨부</td>
		<td> <%if(l.getLectorOriginalVideo()!=null){ %>
            <input type="hidden" name="lectorVideo" value="<%=l.getLectorOriginalVideo() %>" readonly/>
			<%} %>
		</td>
	  </tr>	
    <tr>
		<td>가격</td>
		<td> <input type="number" name="price" value="<%=l.getLectorPrice() %>">원</td>
	</tr>	
	</table>
	<br>
    <div align="center">
        <button type="reset" onclick = "cancelChk()" id="openCancleBtn">취소</button>
        <button type="submit" onclick="" id="openEnrollBtn">등록</button>
    </div>   
          
</form>
       <script>
    	$(function(){
    		$("input[name='lectorImg']").change(function(){
    			if($(this).val()==""){
    				$("#fname").show();
    			}else{
    				$("#fname").hide();
    			}
    		})
    	})    
  </script>

<%@ include file="/views/common/footer.jsp"%>
