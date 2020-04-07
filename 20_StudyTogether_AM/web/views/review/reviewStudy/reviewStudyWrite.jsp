<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/reviewBoard.css" type="text/css"/>
 <%@ page import="java.util.List, com.kh.study.model.vo.Study" %>
<%
	List<Study> list = (List)request.getAttribute("list");
%>
  <!-- 참여한 강좌가 없으면 row가 0이면..등록이 안되도록
참여한 강좌 리스트에는 로그인 되어있는 강좌만 표시 되도롣!!!! -->
<form style="margin:0 auto;" name="studyWrite" method="post" action="<%=request.getContextPath() %>/review/reviewStuFormEnd">
<br>
<h2 id="revWTitle">스터디후기작성</h2>

   <table class="revWrite">
      <tr>
        <td>작성자</td>
        <td><%=loginMember.getUserId() %><input type="hidden" name="writer" value="<%=loginMember.getUserId()%>" readonly required/></td>
       </tr>
        <tr>
            <td>스터디</td>
            <td>
                <select name="allStudy" > 
                    <option value="스터디 선택">스터디 선택</option>
                    <%for(Study s : list){ %>
                   	 <option value="<%=s.getStudyName()%>"><%=s.getStudyName()%></option>
                   	 <%} %>
                </select>
            </td>
        </tr>
        <tr>
            <td>만족도</td>
            <input type="hidden" id="starCnt" name="starCnt">
            <td><p id="star_grade" name="star">
                <a href="#">★</a>
                <a href="#">★</a>
                <a href="#">★</a>
                <a href="#">★</a>
                <a href="#">★</a>
             </p></td>
        </tr>
        <tr>
           <td>내용</td>
           <td><textarea name="content" cols=80 rows=10 style="resize:none;"></textarea></td>
        </tr>
          
   </table>
   <br>
 		<div id="revWBtn">
          <button type="reset" onclick ="cancelChk()" id="revWCancelBtn" >취소</button>
          <%if(list.size()!=0){ %>
          <button type="submit" id="revWenrollBtn">등록</button>
          <%}else{ %>
          <button onclick="revWChk()" type="button" id="revWenrollBtn">등록</button>
          <%} %>
       	</div>

</form>
      <script language=javascript>
          var starCnt=0;
          $('#star_grade a').click(function(){
              $(this).parent().children("a").removeClass("on");  /* 별점의 on 클래스 전부 제거 */ 
              $(this).addClass("on").prevAll("a").addClass("on"); /* 클릭한 별과, 그 앞 까지 별점에 on 클래스 추가 */
              starCnt =  $(this).addClass("on").prevAll("a").addClass("on").length+1;
				//별 개수 jsp로 보내기
              var form = $("#studyWrite");            
              $("#starCnt").attr({type:'hidden',name:'starCnt',value:starCnt}).appendTo(form);

          });
  
          function cancelChk(){
             if (confirm("정말 취소하시겠습니까??") == true){    //확인
                   location.replace("<%=request.getContextPath()%>/review/reviewStudy/reviewStudyList");
               }else{   //취소
                  return false;
               }
           }
          
          function revWChk(){
       		  alert("참여중인 스터디가 없습니다. 후기 등록을 할 수 없습니다.");
       		  location.replace("<%=request.getContextPath()%>/review/reviewStudy/reviewStudyList");
          }
          
      </script>
<%@ include file="/views/common/footer.jsp"%>