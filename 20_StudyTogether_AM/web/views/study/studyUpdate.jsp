<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>

 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lecStuOpen.css" type="text/css"/>
<form style="margin:0 auto;" id="frmOpen" action="<%=request.getContextPath()%>/study/studyOpenEnd" method="post" enctype="multipart/form-data">
 <br>
 <h2 id="openTitle">스터디 개설</h2>
         <table class="openWrite">
                 <tr>
                     <td>스터디<br>이름</td>
                     <td>개설자<input type="text" name="studyWriter" value=""></td>
                     <td>스터디명<input type = "text" name = "studyName" size=50> </td>
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
                             <option value="남양주">남양주</option>
                             <option value="수원">수원</option>
                             <option value="안산">안산</option>
                             <option value="안양">안양</option>
                             <option value="일산">일산</option>
                         </optgroup>
                         <optgroup label="강원도"> 
                             <option value="강릉">강릉</option>
                             <option value="속초">속초</option>
                             <option value="인제">인제</option>
                             <option value="평창">평창</option>
                             <option value="횡성">횡성</option>
                         </optgroup>
                         <optgroup label="충청도"> 
                             <option value="논산">논산</option>
                             <option value="대전">대전</option>
                             <option value="아산">아산</option>
                             <option value="보령">보령</option>
                             <option value="천안">천안</option>
                         </optgroup>
                         <optgroup label="전라도"> 
                             <option value="광주">광주</option>
                             <option value="군산">군산</option>
                             <option value="부안">부안</option>
                             <option value="순창">순창</option>
                             <option value="전주">전주</option>
                         </optgroup>
                         <optgroup label="경상도"> 
                             <option value="대구">대구</option>
                             <option value="부산">부산</option>
                             <option value="안동">안동</option>
                             <option value="울산">울산</option>
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
                             <option value="불어">불어</option>
                             <option value="스페인어">스페인어</option>
                             <option value="영어">영어</option>
                             <option value="일본어">일본어</option>
                             <option value="중국어">중국어</option>
                             <option value="어학 기타">어학 기타</option>
                         </optgroup>
                         
                         <optgroup label="자격증">
                             <option value="공인중개사">제빵</option>
                             <option value="미용">미용</option>
                             <option value="정보처리기사">정보처리기사</option>
                             <option value="컴퓨터활용">컴퓨터활용</option>
                             <option value="토익">토익</option>
                             <option value="자격증 기타">자격증 기타</option>
                         </optgroup>
             
                         <optgroup label="취미">
                             <option value="공예">공예</option>
                             <option value="맛집탐방">맛집탐방</option>
                             <option value="밴드">밴드</option>
                             <option value="요리">요리</option>
                             <option value="운동">운동</option>
                             <option value="취미 기타">취미 기타</option>
                         </optgroup>
                 
             
                         <optgroup label="IT">
                             <option value="알고리즘">알고리즘</option>
                             <option value="데이터베이스">데이터베이스</option>
                             <option value="자바프로그래밍">자바프로그래밍</option>
                             <option value="운영체제">운영체제</option>
                             <option value="HTML">HTML</option>
                             <option value="IT 기타">IT 기타</option>
                         </optgroup>
             
                         <optgroup label="대학생">
                             <option value="취업">취업</option>
                             <option value="면접">면접</option>
                             <option value="인문학과">인문학과</option>
                             <option value="자연과학">자연과학</option>
                             <option value="예체능">예체능</option>
                             <option value="대학생 기타">대학생 기타</option>
                         </optgroup>
                         </select>
                         </td>
                 </tr>
                 <tr>
                     <td>희망 일자</td>
                     <td> 
                         <input type="checkbox" id="week" name="day">평일
                         <input type="checkbox" id="weekend" name="day">주말
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

<%@ include file="/views/common/footer.jsp"%>
