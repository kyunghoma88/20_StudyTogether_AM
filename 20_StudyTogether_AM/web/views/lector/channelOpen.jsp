<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.lector.model.vo.Lector" %>

<%@ include file="/views/common/header.jsp"%>

<%
	Lector l=(Lector)request.getAttribute("lector");
%>
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


     <h2>강좌 등록</h2><br>
   <form id="regFrm" action="<%=request.getContextPath()%>/lector/lectorChannelOpenEnd" method="post" enctype="multipart/form-data">
    	<input type="hidden" name="no" value="<%=l.getLectorNo() %>" />
     강사명<input type="text" name="writer" value="<%=l.getLectorWriter()%>" readonly><br>
	<!-- 등록날짜 --><input type="hidden" name="date" value="<%=l.getLectorDate() %>" ><br>
        강좌 이름 <input type="text" name="title" ><br><br>
            <br>
            상세 소개<br>
            <textarea name="detail" cols="100" rows="20" style="resize: none;"  placeholder=""></textarea><br>
  		<br>
           <!-- 강좌 비디오 첨부 -->
            
            <input type="file" name="video"  readonly/><br><br>
		
            가격<br>
            <input type="number" name="price" value="<%=l.getLectorPrice() %>"readonly >원<br><br>
                
                <input type="submit" value="등록">
                <input type="reset" value="취소">
       </form>
       <script>
    	$(function(){
    		$("input[name='lectorImg']").change(function(){
    			if($(this).val()==""){
    				$("#fname").show();
    			}else{
    				$("#fname").hide();
    			}
    		})
    	})    
  </script>

<%@ include file="/views/common/footer.jsp"%>
