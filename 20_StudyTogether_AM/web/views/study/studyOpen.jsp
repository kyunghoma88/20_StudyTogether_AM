<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="/views/common/header.jsp"%>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lecStuOpen.css" type="text/css"/>
<form style="margin:0 auto;" id="frmOpen" action="<%=request.getContextPath()%>/study/studyOpenEnd" method="post" enctype="multipart/form-data">
 <br>
 <h2 id="openTitle">스터디 개설</h2>
        <table class="openWrite">
        		<tr>
        			<td>개설자</td>
        			<td><%=loginMember.getUserId()%><input type="hidden" name="studyWriter" value="<%=loginMember.getUserId()%>"></td>
        		</tr>
                 <tr>
                     <td>스터디<br>이름</td>                    
                     <td><input type = "text" name = "studyName" size=50> </td>
                 </tr>
                 <tr>
                     <td> 희망 지역</td>
                     <td>
                         <select name="studyArea" > 
                         <option value="지역">지역</option>
                         <optgroup label="서울">
                             <option value="강남">강남</option>
                             <option value="건대">건대</option>
                             <option value="신촌">신촌</option>
                             <option value="잠실">잠실</option>
                             <option value="홍대">홍대</option>
                         </optgroup>
                         <optgroup label="경기도">   
                             <option value="수원">수원</option>
                             <option value="안산">안산</option>
                             <option value="안양">안양</option>
                             <option value="일산">일산</option>
                         </optgroup>
                         <optgroup label="강원도"> 
                             <option value="강릉">강릉</option>
                             <option value="속초">속초</option>
                         </optgroup>
                         <optgroup label="충청도"> 
                             <option value="대전">대전</option>
                             <option value="천안">천안</option>
                         </optgroup>
                         <optgroup label="전라도"> 
                             <option value="광주">광주</option>
                             <option value="전주">전주</option>
                         </optgroup>
                         <optgroup label="경상도"> 
                             <option value="대구">대구</option>
                             <option value="부산">부산</option>
                             <option value="포항">포항</option>
                         </optgroup>
                         </select>
                     </td>
                 </tr>

                 <tr>
                     <td>스터디<br>분야</td>
                     <td><select name="studyCategory" > 
                         <option value="카테고리">카테고리</option>
                         <optgroup label="어학,회화">
                             <option value="영어">영어</option>
                             <option value="일본어">일본어</option>
                           	<option value="스페인어">스페인어</option>
                             <option value="불어">불어</option>
                         </optgroup>
                         
                         <optgroup label="자격증">
                             <option value="제빵">제빵</option>
                             <option value="정보처리기사">정보처리기사</option>
                             <option value="컴퓨터활용">컴퓨터활용</option>
                             <option value="토익">토익</option>
                         </optgroup>
             
                         <optgroup label="IT">
                             <option value="알고리즘">알고리즘</option>
                             <option value="데이터베이스">데이터베이스</option>
                             <option value="자바프로그래밍">자바프로그래밍</option>
                         </optgroup>
             
                         </select>
                         </td>
                 </tr>
                 <tr>
                     <td>희망 일자</td>
                     <td> 
                         <input type="checkbox" id="week" name="day" value="평일">평일
                         <input type="checkbox" id="weekend" name="day" value="주말">주말
                     </td>
                 </tr>
                 <tr>
                     <td>최대 인원</td>
                     <td>
                         <input type="number" min="2" max="100" name="maxMember" id="maxMember">명
                     </td>
                 </tr>
                 <tr>
                     <td>모집기간</td>
                     <td>
                          <input type="date" name="endDate" id="endPeriod">까지
                     </td>
                 </tr>
<!-- 
                 <tr>
                     <td>한줄 소개</td>
                     <td> <textarea name="intro1" cols="85" rows="2" style="resize: none;"  placeholder="*이 강좌의 특성을 간단하게 소개해주세요."></textarea></td>
                 </tr> -->

                 <tr>
                     <td>상세 소개</td>
                     <td><textarea name="intro1" cols="85" rows="10" style="resize: none;"  placeholder="*이 강좌에 대해 자세히 소개해주세요."></textarea></td>
                 </tr>
                 <br>
                 <tr>
                     <td>썸네일<br>이미지</td>
                     <td><input type="file" name="thumbnail" accept="image/*"/></td>
                 </tr>
                 </table>
				<br>
                 <div align="center">
                     <button type="reset" onclick = "cancelChk()" id="openCancleBtn">취소</button>
                     <button type="submit" onclick="" id="openEnrollBtn">승인 요청</button>
                 </div>

 </form>
 <script>
  function cancelChk(){
      if (confirm("정말 취소하시겠습니까??") == true){    //확인
            location.replace("<%=request.getContextPath()%>/study/studyList");
        }else{   //취소
           return false;
        }
    }
  //모집 날짜, 오늘 날짜 이후로만 선택 가능
 /*  $(function() {
	  $( "#endPeriod" ).datepicker({ minDate: 0});
	  }); */ // 안됨...
 </script>


<%@ include file="/views/common/footer.jsp"%>
