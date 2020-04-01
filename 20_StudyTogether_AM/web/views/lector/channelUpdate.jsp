<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.lector.model.vo.LectorChannel" %>
<%@ include file="/views/common/header.jsp"%>
<%
	LectorChannel lc=(LectorChannel)request.getAttribute("lc");
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


     <h2>강좌 수정</h2><br>
   <form id="regFrm" action="<%=request.getContextPath()%>/lector/channelUpdateEnd" method="post" enctype="multipart/form-data">
    	<input type="hidden" name="no" value="<%=lc.getChannelNo() %>" />
    	<input type="hidden" name="noRef" value="<%=lc.getChannelNoRef()%>">
     강사명<input type="text" name="lectorWriter" value="<%=lc.getChannelWriter()%>" readonly><br>
        강좌 이름 <input type="text" name="lectorTitle" value="<%=lc.getChannelTitle()%>"><br><br>
       <br>
            <br>
            상세 소개<br>
            <textarea name="intro" cols="100" rows="20" style="resize: none;"  placeholder=""><%=lc.getChannelDetail()%></textarea><br>
            <br>
     	강좌<input type="file" name="lectorVideo" />
 		<%if(lc.getChannelOriginalVideo()!=null){ %><span id="fname"><%=lc.getChannelOriginalVideo() %></span>
             <input type="hidden" name="lectorVideo" value="<%=lc.getChannelOriginalVideo()%>">
           <%} %><br><br>
            
            가격
            <input type="number" name="price" value="<%=lc.getChannelPrice() %>"readonly >원<br><br>
            <input type="hidden" name="date" value="<%=lc.getChannelEnrollDate() %>">
                
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
