<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/reviewBoard.css" type="text/css"/>
 <%@ page import="java.util.List, com.kh.lector.model.vo.Lector" %>
<%
	List<Lector> list = (List)request.getAttribute("list");
%>
<form style="margin:0 auto;" name="LetureWrite" method="post" action="<%=request.getContextPath() %>/lecture/reviewLecFormEnd">
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
                   <%--  <%for(Lector l : list) {%>
                    <option value="<%l.getLectorTitle()%>"><%l.getLectorTitle()%></option>
                    <%} %> --%>
                </select>
            </td>
        </tr>
        <tr>
          <td>분야</td>
          <td><select name="field" > 
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
            <td>만족도</td>
            <input type="text" id="starCnt" name="starCnt">
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
           <td><textarea name="content" cols=85 rows=10 style="resize:none;"></textarea></td>
        </tr>
          
   </table>
   <br>
 		<div id="revWBtn">
          <button type="reset" onclick ="cancelChk()" id="revWCancelBtn" >취소</button>
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
	          var form = $("#LetureWrite");            
	          $("#starCnt").attr({type:'text',name:'starCnt',value:starCnt}).appendTo(form);
	
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
       		  <%-- location.replace("<%=request.getContextPath()%>/review/reviewLecture/reviewLectureList"); --%>
          }
      </script>
<%@ include file="/views/common/footer.jsp"%>