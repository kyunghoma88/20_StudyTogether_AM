<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List,com.kh.lector.model.vo.Lector" %>
	
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/lectorList.css" type="text/css"/>

<%
	List<Lector> list=(List)request.getAttribute("list");
	String key=(String)request.getAttribute("key");

	String type=request.getParameter("searchType");
	String keyword=request.getParameter("searchKeyword");
%>
<section>
  <div class="make">
    <a href="<%=request.getContextPath()%>/lector/lectorOpen"><img src="<%=request.getContextPath() %>/images/owl.JPG" width="100px" height="auto" ><br><p>강좌 개설하기</p></a>
  </div>
  	<div id=title>강좌 스터디 찾기 </div>
  	<h3> 검색 결과 입니다.</h3>
	
	<div class="main_list">
	 <!-- 상단 제목 -->
		<div class="main_title">
	
   <form action="<%=request.getContextPath() %>/lector/lectorFinder" method="post">
     <select id="searchType" name="searchType"> 
     	<option value="all" <%=type!=null&&type.equals("all")?"selected":"" %>>전체</option>
     	  <optgroup label="어학,회화">
          <option value="영어" <%=type!=null&&type.equals("영어")?"selected":"" %>>영어</option>
          <option value="일본어" <%=type!=null&&type.equals("일본어")?"selected":"" %>>일본어</option>
          <option value="스페인어" <%=type!=null&&type.equals("스페인어")?"selected":"" %>>스페인어</option>
          <option value="불어" <%=type!=null&&type.equals("불어")?"selected":"" %>>불어</option> --%>
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
      
      <input type="text" name="searchKeyword" value="<%=request.getAttribute("key")%>">
      <input type="submit" value="검색">
     </form>
   <%--    <div id="search-all">
			<form action="<%=request.getContextPath() %>/lector/lectorFinder">
				<input type="hidden" name="searchType" value="전체"/>
				<input type="text" name="searchKeyword" value="<%=type!=null&&type.equals("all")?keyword:""%>" size="25" placeholder="검색할 아이디 입력"/>
				<button type="submit">검색</button>
			</form>
		</div>
      
      <div id="search-Eng">
			<form action="<%=request.getContextPath() %>/lector/lectorFinder">
				<input type="hidden" name="searchType" value="영어"/>
				<input type="text" name="searchKeyword" value="<%=type!=null&&type.equals("영어")?keyword:""%>" size="25" placeholder="검색할 아이디 입력"/>
				<button type="submit">검색</button>
			</form>
		</div>
		

      <div id="search-Jap">
			<form action="<%=request.getContextPath() %>/lector/lectorFinder">
				<input type="hidden" name="searchType" value="일본어"/>
				<input type="text" name="searchKeyword" value="<%=type!=null&&type.equals("일본어")?keyword:""%>" size="25" placeholder="검색할 아이디 입력"/>
				<button type="submit">검색</button>
			</form>
		</div>
		 --%>
		
   </div>
  </div>
<script>
//온로드
	$(function(){
		$("#searchType").change(()=>{
			//select가 변경됐을때 change함수 이용
			let type=$("#searchType").val();
			//userName,userId,gender 값받기
			
			let all=$("#search-all");
			let eng=$("#search-Eng");
			let jap=$("#search-Jap");
			all.hide();//다 닫힌다
			eng.hide();//다 닫힌다
			jap.hide(); //다 닫힌다
			
			$("#search-"+type).css("display","inline-block");
		})
		$("#searchType").trigger("change");//trigger(강제실행)(change)먼저 실행하고 위에 함수 실행
	})


//** jQuery show() , hide(), toggle() 사용법
/*
 $("#tagId").show(); -> display속성을 block으로 바꾼다.
 $("#tagId").hide(); -> display속성을 none으로 바꾼다.
 $("#tagId").toggle(); -> show->"hide" hide->"show"
 */
</script>







  <!-- 리스트 -->

<%if(list.isEmpty()){ %>
	<div class="list_start">
		<h3>검색된 강좌가 없습니다.</h3>
<%}else{ %>
	<%for(Lector lector:list){ %>
  		<a href="<%=request.getContextPath()%>/lector/lectorView?pNo=<%=lector.getLectorNo()%>">
    <div class="list_detail">
      <h4><%=lector.getLectorCategory() %></h4>
      <%if(lector.getLectorOriginalImg()!=null){ %>
     <div>
     <!-- 리네임된 파일업로드  -->
        <img src="<%=request.getContextPath() %>/upload/lector/<%=lector.getLectorOriginalImg() %>" class="" width="200px" height="150px">
     </div>
      <%} %>
      <div id="lectorTitle"><%=lector.getLectorTitle() %><br/><%=lector.getLectorPrice() %>원</div>
    </div>
  </a>
  <%}
	}%>
</div>
	 <%-- <ul class="pagination">
		<%=request.getAttribute("pageBar") %>
	</ul>  --%>
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
