<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<style>
fieldset {
    box-sizing: border-box;
    font-family: Sunflower;
    width: 700px;
    height: 300px;
    margin: 0 auto;
}
button{
    /* background-color: #4CAF50; */
    color: black;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    float: right;
}
</style>
       <h2>강좌 개설</h2><br>
    <form id="frmOpen" action="<%=request.getContextPath()%>/lector/lectorOpenEnd" method="post" enctype="multipart/form-data">
     강사명 <input type="text" name="lectorWriter"><br>
       <input type="hidden" name="lectorDate" ><br>
        강좌 이름 <input type="text" name="lectorTitle" required><br><br>
        강좌 분야
      <select id="searchType" name="searchType"> 
     	<option value="카테고리">카테고리</option>
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
            <br>
            <br>
            상세 소개<br>
            <textarea name="intro" cols="100" rows="20" style="resize: none;"  placeholder="*이 강좌에 대해 자세히 소개해주세요."></textarea><br>
            <br>
  	썸네일 이미지<br>
            <input type="file" name="lectorImg" /><br><br>

           강좌 비디오 첨부<br>
            <input type="file" name="lectorVideo" /><br><br> 
            <!-- 영상 첨부 추가 될 수 있도록!!!! 변경 -->
            가격<br>
            <input type="number" name="price" placeholder="숫자만 입력">원<br><br>
            
            <input type="submit" value="등록">
                <input type="reset" value="취소">
     </form>
<%-- <%--  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lecStuOpen.css" type="text/css"/>
<form style="margin:0 auto;" action="<%=request.getContextPath() %>/lector/">
 <br>
 <h2 id="openTitle">강좌 개설</h2>
         <table class = "openWrite">
                 <tr>
                     <td>강좌 이름</td>
                     <td><input type = text name = name size=50> </td>
                 </tr>

                 <tr>
                     <td>강좌 분야</td>
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

             

                <input type="submit" value="등록">
                <input type="reset" value="취소">
       </form>

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
                     <td>한줄 소개</td>
                     <td> <textarea name="intro1" cols="85" rows="2" style="resize: none;"  placeholder="*이 강좌의 특성을 간단하게 소개해주세요."></textarea></td>
                 </tr>

                 <tr>
                     <td>상세 소개</td>
                     <td><textarea name="intro1"cols="85" rows="10" style="resize: none;"  placeholder="*이 강좌에 대해 자세히 소개해주세요."></textarea></td>
                 </tr>
                 <br>
                 <tr>
                     <td>썸네일<br>이미지</td>
                     <td><input type="file" name="upfile" accept="image/*"/></td>
                 </tr>
                 <tr>
                     <td>강좌<br>비디오<br>첨부</td>
                     <td> <input type="file" name="upfile" accept="video/*"/></td>
                 </tr>
                 <tr>
                     <td>가격</td>
                     <td><input type="number" name="price" placeholder="숫자만 입력">원<br><br>
                     </td>
                 </tr>
                 </table>
					<br>
                 <div align="center">
                     <button type="reset" onclick = "cancelChk()" id="openCancleBtn">취소</button>
                     <button type="submit" onclick="" id="openEnrollBtn">승인요청</button>
                 </div> --%> --%>
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
