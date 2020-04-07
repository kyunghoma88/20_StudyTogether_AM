<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/reviewBoard.css" type="text/css"/>
 <%@ page import="java.util.List, com.kh.lector.model.vo.Lector" %>
<%
	List<Lector> list = (List)request.getAttribute("list");
%>
<form style="margin:0 auto;" name="letureWrite" method="post" action="<%=request.getContextPath() %>/lecture/reviewLecFormEnd">
<br>
<h2 id="revWTitle">강좌 후기작성</h2>
   <table class="revWrite">
      <tr>
        <td>작성자</td>
        <td><%=loginMember.getUserId() %><input type="hidden" name="writer" value="<%=loginMember.getUserId()%>" readonly required/></td>
       </tr>
        
        <tr>
            <td>강좌</td>
            <td>
                <select name="allLecture" > 
                    <option value="강좌 선택">강좌 선택</option>
                    <%for(Lector lec : list){ %>
                   	 <option value="<%=lec.getLectorTitle()%>"><%=lec.getLectorTitle()%></option>
                   	 <%} %>
                </select>
            </td>
        </tr>
        <tr>

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
          <button type="reset" onclick ="cancelChk()" id="revLCancelBtn" >취소</button>
         <%if(list.size()!=0){ %>
          	<button type="submit" id="revLenrollBtn">등록</button>
          <%}else{ %>
         	 <button onclick="revLecChk()" type="button" id="revLenrollBtn">등록</button>
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
	          var form = $("#letureWrite");            
	          $("#starCnt").attr({type:'hidden',name:'starCnt',value:starCnt}).appendTo(form);
	
	      });
  
          function cancelChk(){
             if (confirm("정말 취소하시겠습니까??") == true){    //확인
                   location.replace("<%=request.getContextPath()%>/review/reviewLecture/reviewLectureList");
               }else{   //취소
                  return false;
               }
           }
          function revLecChk(){
       		  alert("참여중인 강의가 없습니다. 후기 등록을 할 수 없습니다.");
       		  location.replace("<%=request.getContextPath()%>/review/reviewLecture/reviewLectureList");
          }
      </script>
<%@ include file="/views/common/footer.jsp"%>