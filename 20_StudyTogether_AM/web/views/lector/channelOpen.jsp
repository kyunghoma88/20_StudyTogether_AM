<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.lector.model.vo.Lector" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/lecStuOpen.css" type="text/css"/>
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

</style>
<form style="margin:0 auto;" id="regFrm" action="<%=request.getContextPath()%>/lector/lectorChannelOpenEnd" method="post" enctype="multipart/form-data">
<br><h2 id="openTitle">강좌 등록</h2>
	<table class="openWrite">
    	<input type="hidden" name="no" value="<%=l.getLectorNo() %>">
    	<tr>
			<td>강사명</td>
			<td><%=l.getLectorWriter()%><input type="hidden" name="writer" value="<%=l.getLectorWriter()%>" readonly></td>
		</tr>
   		<tr>
			<td>강좌 이름</td>
			<td><input type="text" name="title" ></td>
		</tr>  
	<!-- 등록날짜 --><input type="hidden" name="date" value="<%=l.getLectorDate() %>" ><br>
     	 <tr>
			<td>상세 소개</td>
			<td><textarea name="detail" cols="80" rows="10" style="resize: none;"  placeholder=""></textarea></td>
		</tr>  
        <tr>
			<td>강좌<br>비디오<br>첨부</td>
			<td> <input type="file" name="video"  readonly/></td>
		</tr>   
        <tr>
			<td>가격</td>
			<td><input type="number" name="price" value="<%=l.getLectorPrice() %>"readonly >원</td>
		</tr>
        </table><br>
        <div align="center">
           <button type="reset" id="openCancleBtn">취소</button>
           <button type="submit" id="openEnrollBtn">등록</button>
 		</div> 
         
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
