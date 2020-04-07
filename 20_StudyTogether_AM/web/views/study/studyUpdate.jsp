<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.study.model.vo.Study" %>
	
<%@ include file="/views/common/header.jsp"%>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lecStuOpen.css" type="text/css"/>
<%
	Study s=(Study)request.getAttribute("s");


	String[] day=s.getStudyPossibleDay().split(",");
	String[] checked=new String[2];
	for(String d:day){
		switch(d){
		case "평일" :checked[0]="checked";break;
		case "주말" :checked[1]="checked";break;
		}
	}
%>
<form style="margin:0 auto;" id="frmOpen" action="<%=request.getContextPath()%>/study/studyUpdateEnd?no=<%=s.getStudyNo() %>" method="post" enctype="multipart/form-data">
 <br>
 <h2 id="openTitle">스터디 개설</h2>
        <table class="openWrite">
      			<tr>
               
                     <td>개설자</td>
                     <td><%=loginMember.getUserId()%><input type="hidden" name="studyWriter" value="<%=loginMember.getUserId()%>"></td>
        		
                 </tr>
                 <tr>
                     <td>스터디명</td>
                     <td><input type = "text" name = "studyName" value="<%=s.getStudyName()%>"size=50> </td>
                 </tr>
                 <tr>
                     <td> 희망 지역</td>
                     <td>
                         <select name="studyArea" > 
                         <option value="지역">지역</option>
                         <optgroup label="서울">
                             <option value="강남" <%=s!=null&&s.getStudyArea().equals("강남")?"selected":"" %>>강남</option>
                             <option value="건대" <%=s!=null&&s.getStudyArea().equals("건대")?"selected":"" %>>건대</option>
                             <option value="신촌" <%=s!=null&&s.getStudyArea().equals("신촌")?"selected":"" %>>신촌</option>
                             <option value="잠실" <%=s!=null&&s.getStudyArea().equals("잠실")?"selected":"" %>>잠실</option>
                             <option value="홍대" <%=s!=null&&s.getStudyArea().equals("홍대")?"selected":"" %>>홍대</option>
                         </optgroup>
                         <optgroup label="경기도">   
                             <option value="남양주" <%=s!=null&&s.getStudyArea().equals("남양주")?"selected":"" %>>남양주</option>
                             <option value="수원" <%=s!=null&&s.getStudyArea().equals("수원")?"selected":"" %>>수원</option>
                             <option value="안산" <%=s!=null&&s.getStudyArea().equals("안산")?"selected":"" %>>안산</option>
                             <option value="안양" <%=s!=null&&s.getStudyArea().equals("안양")?"selected":"" %>>안양</option>
                             <option value="일산" <%=s!=null&&s.getStudyArea().equals("일산")?"selected":"" %>>일산</option>
                         </optgroup>
                         <optgroup label="강원도"> 
                             <option value="강릉" <%=s!=null&&s.getStudyArea().equals("강릉")?"selected":"" %>>강릉</option>
                             <option value="속초" <%=s!=null&&s.getStudyArea().equals("속초")?"selected":"" %>>속초</option>
                             <option value="인제" <%=s!=null&&s.getStudyArea().equals("인제")?"selected":"" %>>인제</option>
                             <option value="평창" <%=s!=null&&s.getStudyArea().equals("평창")?"selected":"" %>>평창</option>
                             <option value="횡성" <%=s!=null&&s.getStudyArea().equals("횡성")?"selected":"" %>>횡성</option>
                         </optgroup>
                         <optgroup label="충청도"> 
                             <option value="논산" <%=s!=null&&s.getStudyArea().equals("논산")?"selected":"" %>>논산</option>
                             <option value="대전" <%=s!=null&&s.getStudyArea().equals("대전")?"selected":"" %>>대전</option>
                             <option value="아산" <%=s!=null&&s.getStudyArea().equals("아산")?"selected":"" %>>아산</option>
                             <option value="보령" <%=s!=null&&s.getStudyArea().equals("보령")?"selected":"" %>>보령</option>
                             <option value="천안" <%=s!=null&&s.getStudyArea().equals("천안")?"selected":"" %>>천안</option>
                         </optgroup>
                         <optgroup label="전라도"> 
                             <option value="광주" <%=s!=null&&s.getStudyArea().equals("광주")?"selected":"" %>>광주</option>
                             <option value="군산" <%=s!=null&&s.getStudyArea().equals("군산")?"selected":"" %>>군산</option>
                             <option value="부안" <%=s!=null&&s.getStudyArea().equals("부안")?"selected":"" %>>부안</option>
                             <option value="순창" <%=s!=null&&s.getStudyArea().equals("순창")?"selected":"" %>>순창</option>
                             <option value="전주" <%=s!=null&&s.getStudyArea().equals("전주")?"selected":"" %>>전주</option>
                         </optgroup>
                         <optgroup label="경상도"> 
                             <option value="대구" <%=s!=null&&s.getStudyArea().equals("대구")?"selected":"" %>>대구</option>
                             <option value="부산" <%=s!=null&&s.getStudyArea().equals("부산")?"selected":"" %>>부산</option>
                             <option value="안동" <%=s!=null&&s.getStudyArea().equals("안동")?"selected":"" %>>안동</option>
                             <option value="울산" <%=s!=null&&s.getStudyArea().equals("울산")?"selected":"" %>>울산</option>
                             <option value="포항" <%=s!=null&&s.getStudyArea().equals("포항")?"selected":"" %>>포항</option>
                         </optgroup>
                         </select>
                     </td>
                 </tr>

                 <tr>
                     <td>스터디<br>분야</td>
                     <td><select name="studyCategory" > 
                         <option value="카테고리">카테고리</option>
                         <optgroup label="어학,회화">
                             <option value="불어"  <%=s!=null&&s.getStudyCategory().equals("불어")?"selected":"" %>>불어</option>
                             <option value="스페인어" <%=s!=null&&s.getStudyCategory().equals("스페인어")?"selected":"" %>>스페인어</option>
                             <option value="영어" <%=s!=null&&s.getStudyCategory().equals("영어")?"selected":"" %>>영어</option>
                             <option value="일본어" <%=s!=null&&s.getStudyCategory().equals("일본어")?"selected":"" %>>일본어</option>
                             <option value="중국어" <%=s!=null&&s.getStudyCategory().equals("중국어")?"selected":"" %>>중국어</option>
                         </optgroup>
                         
                         <optgroup label="자격증">
                             <option value="공인중개사" <%=s!=null&&s.getStudyCategory().equals("제빵")?"selected":"" %>>제빵</option>
                             <option value="미용" <%=s!=null&&s.getStudyCategory().equals("미용")?"selected":"" %>>미용</option>
                             <option value="정보처리기사" <%=s!=null&&s.getStudyCategory().equals("정보처리기사")?"selected":"" %>>정보처리기사</option>
                             <option value="컴퓨터활용" <%=s!=null&&s.getStudyCategory().equals("컴퓨터활용")?"selected":"" %>>컴퓨터활용</option>
                             <option value="토익" <%=s!=null&&s.getStudyCategory().equals("토익")?"selected":"" %>>토익</option>
                         </optgroup>
             
                         <optgroup label="취미">
                             <option value="공예" <%=s!=null&&s.getStudyCategory().equals("공예")?"selected":"" %>>공예</option>
                             <option value="맛집탐방" <%=s!=null&&s.getStudyCategory().equals("맛집탐방")?"selected":"" %>>맛집탐방</option>
                             <option value="밴드" <%=s!=null&&s.getStudyCategory().equals("밴드")?"selected":"" %>>밴드</option>
                             <option value="요리" <%=s!=null&&s.getStudyCategory().equals("요리")?"selected":"" %>>요리</option>
                             <option value="운동" <%=s!=null&&s.getStudyCategory().equals("운동")?"selected":"" %>>운동</option>
                         </optgroup>
                 
             
                         <optgroup label="IT">
                             <option value="알고리즘" <%=s!=null&&s.getStudyCategory().equals("알고리즘")?"selected":"" %>>알고리즘</option>
                             <option value="데이터베이스" <%=s!=null&&s.getStudyCategory().equals("데이터베이스")?"selected":"" %>>데이터베이스</option>
                             <option value="자바프로그래밍" <%=s!=null&&s.getStudyCategory().equals("자바프로그래밍")?"selected":"" %>>자바프로그래밍</option>
                             <option value="운영체제" <%=s!=null&&s.getStudyCategory().equals("운영체제")?"selected":"" %>>운영체제</option>
                             <option value="HTML" <%=s!=null&&s.getStudyCategory().equals("파이썬")?"selected":"" %>>파이썬</option>
                         </optgroup>
             
                         <optgroup label="대학생">
                             <option value="취업" <%=s!=null&&s.getStudyCategory().equals("취업")?"selected":"" %>>취업</option>
                             <option value="면접" <%=s!=null&&s.getStudyCategory().equals("면접")?"selected":"" %>>면접</option>
                             <option value="인문학과" <%=s!=null&&s.getStudyCategory().equals("인문학과")?"selected":"" %>>인문학과</option>
                             <option value="자연과학" <%=s!=null&&s.getStudyCategory().equals("자연과학")?"selected":"" %>>자연과학</option>
                             <option value="예체능" <%=s!=null&&s.getStudyCategory().equals("예체능")?"selected":"" %>>예체능</option>
                         </optgroup>
                         </select>
                         </td>
                 </tr>
                 <tr>
                     <td>희망 일자</td>
                     <td> 
                         <input type="checkbox" id="week" name="day" value="평일" <%=checked[0] %>>평일
                         <input type="checkbox" id="weekend" name="day" value="주말" <%=checked[1] %>>주말
                     </td>
                 </tr>
                 <tr>
                     <td>최대 인원</td>
                     <td>
                         <input type="number" min="2" max="100" name="maxMember" id="maxMember" value="<%=s.getMaxMember()%>">명
                     </td>
                 </tr>
                 <tr>
                     <td>모집기간</td>
                     <td>
                          <%-- <input type="text" name="endDate" value="<%=s.getEndDate() %>"><br><br> --%>
                          <input type="date" name="endDate" id="endPeriod" value="<%=s.getEndDate() %>">까지
                     </td>
                 </tr>
<!-- 
                 <tr>
                     <td>한줄 소개</td>
                     <td> <textarea name="intro1" cols="85" rows="2" style="resize: none;"  placeholder="*이 강좌의 특성을 간단하게 소개해주세요."></textarea></td>
                 </tr> -->

                 <tr>
                     <td>상세 소개</td>
                     <td><textarea name="intro1" cols="80" rows="10" style="resize: none;"  placeholder="*이 강좌에 대해 자세히 소개해주세요."> <%=s.getStudyDetail() %></textarea>
                    </td>
                 </tr>
                 <br>
                 <tr>
                     <td>썸네일<br>이미지</td>
                     <td>
                    <%--  <%if(s.getOriImg()!=null){ %>
                     	<input type="file" name="thumbnail" value="<%=s.getOriImg()%>"/>
                            <%} %>
                         <span id="fname"><%=s.getOriImg() %></span> --%>
		               <input type="file" name="thumbnail" />
		           	 <%if(s.getOriImg()!=null){ %>
		            <span id="fname"><%=s.getOriImg() %></span>
		             <input type="hidden" name="thumbnail1" value="<%=s.getOriImg() %>">
		           <%} %>
                     </td>
                 </tr>
              
                 </table>
				<br>
                 <div align="center">
                     <button type="reset" onclick = "cancelChk()" id="openCancleBtn">취소</button>
                     <button type="submit" onclick="" id="openEnrollBtn">승인 요청</button>
                 </div>

 </form>


<%@ include file="/views/common/footer.jsp"%>
