<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List,com.kh.study.model.vo.Study" %>
	
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/studyList.css" type="text/css"/>

<%
	List<Study> list=(List)request.getAttribute("list");
 	int cPage=(int)request.getAttribute("cPage");
	String area=(String)request.getAttribute("area");
	String day=request.getParameter("day");
	String type=request.getParameter("searchType");
%>


<section>
  <div class="make">
    <a href="<%=request.getContextPath()%>/study/studyOpen"><img src="<%=request.getContextPath() %>/images/owl.JPG" width="100px" height="auto" ><br><p>스터디개설하기</p></a>
  </div>
  
  <div id=studytitle>스터디 찾기 </div>
	<div class="main_list">
	 <!-- 상단 제목 -->
		<div class="main_title">
<form action="<%=request.getContextPath() %>/study/studyFinder" method="post">
	<select name="area" > 
    <option value="지역 선택" >지역</option>
    <optgroup label="서울" <%=area!=null&&area.equals("서울")?"selected":"" %>>
    <option value="강남" <%=area!=null&&area.equals("강남")?"selected":"" %>>강남</option>
    <option value="건대" <%=area!=null&&area.equals("건대")?"selected":"" %>>건대</option>
    <option value="신촌" <%=area!=null&&area.equals("신촌")?"selected":"" %>>신촌</option>
    <option value="잠실" <%=area!=null&&area.equals("잠실")?"selected":"" %>>잠실</option>
    <option value="홍대" <%=area!=null&&area.equals("홍대")?"selected":"" %>>홍대</option>
</optgroup>
<optgroup label="경기도">   
    <option value="수원"<%=area!=null&&area.equals("수원")?"selected":"" %>>수원</option>
    <option value="안산" <%=area!=null&&area.equals("안산")?"selected":"" %>>안산</option>
    <option value="안양" <%=area!=null&&area.equals("안양")?"selected":"" %>>안양</option>
    <option value="일산" <%=area!=null&&area.equals("일산")?"selected":"" %>>일산</option>
</optgroup>
<optgroup label="강원도"> 
    <option value="강릉" <%=area!=null&&area.equals("강릉")?"selected":"" %>>강릉</option>
    <option value="속초" <%=area!=null&&area.equals("속초")?"selected":"" %>>속초</option>

</optgroup>
<optgroup label="충청도"> 
    <option value="대전" <%=area!=null&&area.equals("대전")?"selected":"" %>>대전</option>
    <option value="천안" <%=area!=null&&area.equals("천안")?"selected":"" %>>천안</option>
</optgroup>
<optgroup label="전라도"> 
    <option value="광주" <%=area!=null&&area.equals("광주")?"selected":"" %>>광주</option>
    <option value="전주" <%=area!=null&&area.equals("전주")?"selected":"" %>>전주</option>
</optgroup>
<optgroup label="경상도"> 
    <option value="대구" <%=area!=null&&area.equals("대구")?"selected":"" %>>대구</option>
    <option value="부산" <%=area!=null&&area.equals("부산")?"selected":"" %>>부산</option>
    <option value="포항" <%=area!=null&&area.equals("포항")?"selected":"" %>>포항</option>
</optgroup>
</select>
	
      <!-- 카테고리 -->
   <select id="searchType" name="searchType">
     	<option >강좌 카테고리</option>
     	  <optgroup label="어학,회화">
          <option value="영어" <%=type!=null&&type.equals("영어")?"selected":"" %>>영어</option>
          <option value="일본어" <%=type!=null&&type.equals("일본어")?"selected":"" %>>일본어</option>
          <option value="스페인어" <%=type!=null&&type.equals("스페인어")?"selected":"" %>>스페인어</option>
          <option value="불어" <%=type!=null&&type.equals("불어")?"selected":"" %>>불어</option>
      	</optgroup>
       <optgroup label="자격증">
          <option value="제빵" <%=type!=null&&type.equals("제빵")?"selected":"" %>>제빵</option>
          <option value="정보처리기사" <%=type!=null&&type.equals("정보처리기사")?"selected":"" %>>정보처리기사</option>
          <option value="컴퓨터활용" <%=type!=null&&type.equals("컴퓨터활용")?"selected":"" %>>컴퓨터활용</option>
          <option value="토익" <%=type!=null&&type.equals("토익")?"selected":"" %>>토익</option>
      </optgroup>
      <optgroup label="IT">
          <option value="알고리즘" <%=type!=null&&type.equals("알고리즘")?"selected":"" %>>알고리즘</option>
          <option value="데이터베이스" <%=type!=null&&type.equals("데이터베이스")?"selected":"" %>>데이터베이스</option>
          <option value="자바프로그래밍" <%=type!=null&&type.equals("자바프로그래밍")?"selected":"" %>>자바프로그래밍</option>
      </optgroup>
      </select>
      
       <select name="day" > 
          <option value="가능시간">가능시간</option>
              <option value="평일" <%=day!=null&&day.equals("자바프로그래밍")?"selected":"" %>>평일</option>
              <option value="주말" <%=day!=null&&day.equals("자바프로그래밍")?"selected":"" %>>주말</option>
      </select> 
     	<input type="submit" value="검색">
    </form>
</div>
</div>
<%if(list.isEmpty()){ %>
	<div class="list_start">
		<h3>검색된 강좌가 없습니다.</h3>
<%}else{ %>
	<%for(Study s:list){ %>
  	<a href="<%=request.getContextPath()%>/study/studyView?no=<%=s.getStudyNo()%>">
    	<div class="list_detail">
      		<h4><%=s.getStudyCategory() %></h4>
     <%if(s.getOriImg()!=null){ %>
      	<div>
    <img src="<%=request.getContextPath() %>/upload/study/<%=s.getOriImg() %>" class="" width="190px" height="120px">
      </div>
      <%} %>
      <div id="lectorTitle"><%=s.getStudyName() %><br/>
      <%=s.getStudyArea() %>&nbsp;|&nbsp;<%=s.getStudyPossibleDay() %><br>
      <%-- 모집인원<%=s.getMaxMember() %>/0 명 --%></div>
    모집마감&nbsp;~<%=s.getEndDate() %>까지
    </div>
  </a>
  <%}
 }%>
</div>

 	<ul class="pagination" style="margin-left:500px;margin-bottom:50px; margin-top:100px;">
		<%=request.getAttribute("pageBar") %>
	</ul> 
	
</section>

    <script>
    $( document ).ready( function() {
  var jbOffset = $( '.mymenu' ).offset();
  $( window ).scroll( function() {
    if ( $( document ).scrollTop() > jbOffset.top ) {
      $( '.mymenu' ).addClass( 'jbFixed' );
    }
    else {
      $( '.mymenu' ).removeClass( 'jbFixed' );
    }
  });
} );
    // 단순fadein효과주기
    
    $(document).ready(function() {
        /* 1 */
        $(window).scroll( function(){
            /* 2 */
            $('.list_detail').each( function(i){
                var bottom_of_object = $(this).offset().top + $(this).outerHeight();
                var bottom_of_window = $(window).scrollTop() + $(window).height();
                /* 3 */
                if( bottom_of_window > bottom_of_object/2 ){
                    $(this).animate({'opacity':'1'},500);
                }
            }); 
        });
    });
    </script>
    
    
<%@ include file="/views/common/footer.jsp"%>
