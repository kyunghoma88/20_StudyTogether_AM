<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/lecStuOpen.css" type="text/css"/>

       
<form style="margin:0 auto;" id="frmOpen" action="<%=request.getContextPath()%>/lector/lectorOpenEnd" method="post" enctype="multipart/form-data">
<br><h2 id="openTitle">강좌 개설</h2> 	 
	<table class="openWrite">
		<tr>
			<td>강사명</td>
			<td><%=loginMember.getUserId()%><input type="hidden" name="lectorWriter" value="<%=loginMember.getUserId()%>" readonly required/></td>
		</tr>
     	<tr>
			<td>강좌 이름</td>
			<td><input type="text" name="lectorTitle" maxlength="20" required></td>
		</tr>
		<tr>
			<td>강좌 분야</td>
			<td> 
			<select id="searchType" name="searchType"> 
     		 <option value="카테고리">카테고리</option>
     		<optgroup label="어학,회화">
         	 <option value="영어">영어</option>
         	 <option value="일본어">일본어</option>
         	 <option value="스페인어">스페인어</option>
         	 <option value="불어">불어</option>
    	  	</optgroup>
     		<optgroup label="자격증">
	          <option value="제빵">제빵</option>
	          <option value="정보처리기사" >정보처리기사</option>
	          <option value="컴퓨터활용">컴퓨터활용</option>
	          <option value="토익" >토익</option>
      		</optgroup>
     		<optgroup label="IT">
	          <option value="알고리즘" >알고리즘</option>
	          <option value="데이터베이스" >데이터베이스</option>
	          <option value="자바프로그래밍">자바프로그래밍</option>
      		</optgroup>
     	 </select>
      </td>
	</tr>
	<tr>
		<td>상세 소개</td>
		<td><textarea name="intro" cols="80" rows="10" style="resize: none;"  placeholder="*이 강좌에 대해 자세히 소개해주세요."></textarea></td>
	</tr>	
    <tr>
		<td>썸네일<br>이미지</td>
		<td> <input type="file" name="lectorImg" /></td>
	</tr>	
  	<tr>
		<td>강좌<br>비디오<br>첨부</td>
		<td><input type="file" name="lectorVideo" /></td>
	</tr>	
    <tr>
		<td>가격</td>
		<td><input type="number" name="price" placeholder="숫자만 입력">원</td>
	</tr>	
           
     </table><br>
     <div align="center">
           <button type="reset" onclick = "cancelChk()" id="openCancleBtn">취소</button>
           <button type="submit" onclick="" id="openEnrollBtn">승인 요청</button>
     </div>
 </form>
 <script>
     function cancelChk(){
         if (confirm("정말 취소하시겠습니까??") == true){    //확인
               location.replace("<%=request.getContextPath()%>/lector/lectorList");
           }else{   //취소
              return false;
           }
       }
</script>
<%@ include file="/views/common/footer.jsp"%>
