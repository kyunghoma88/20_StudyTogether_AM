<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List,com.kh.study.model.vo.Study" %>
	
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/studyList.css" type="text/css"/>

<%
	List<Study> list=(List)request.getAttribute("list");
 	int cPage=(int)request.getAttribute("cPage");
%>

<section>
  <div class="make">
    <a href="<%=request.getContextPath()%>/study/studyOpen"><img src="<%=request.getContextPath() %>/images/owl.JPG" width="100px" height="auto" ><br><p>스터디개설하기</p></a>
  </div>
  <div id=studytitle>스터디 찾기 </div>
	<div class="main_list">
	 <!-- 상단 제목 -->
	<div class="main_title">
	<select name="area" > 
    <option value="지역">지역선택</option>
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
	
      <!-- 카테고리 -->
     <select id="searchType" > 
     	<option value="all">전체</option>
     	 <optgroup label="어학,회화">
          <option value="영어">영어</option>
          <option value="일본어">일본어</option>
          <option value="스페인어">스페인어</option>
          <option value="불어">불어</option>
          <option value="기타">기타</option>
      </optgroup>
      <optgroup label="자격증">
          <option value="제빵">제빵</option>
          <option value="정보처리기사" >정보처리기사</option>
          <option value="컴퓨터활용">컴퓨터활용</option>
          <option value="토익" >토익</option>
          <option value="기타" >기타</option>
      </optgroup>

      <optgroup label="IT">
          <option value="알고리즘" >알고리즘</option>
          <option value="데이터베이스" >데이터베이스</option>
          <option value="자바프로그래밍">자바프로그래밍</option>
          <option value="기타" >기타</option>
      </optgroup>
      </select>
      
       <select name="day" > 
          <option value="가능시간">가능시간</option>
              <option value="평일">평일</option>
              <option value="주말">주말</option>
      </select> 
      <button type="submit">검색</button>
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
    <img src="<%=request.getContextPath() %>/upload/study/<%=s.getOriImg() %>" class="" width="200px" height="150px">
      </div>
      <%} %>
      <div id="lectorTitle"><%=s.getStudyName() %><br/></div>
    </div>
  </a>
  <%}
 }%>
</div>



 	<ul class="pagination">
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
