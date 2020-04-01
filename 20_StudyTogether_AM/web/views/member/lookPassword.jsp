<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<section>
	<h2 id="pwh2">비밀번호 변경 페이지 입니다.<h2>
	<form action="<%=request.getContextPath()%>/updatePassword" method="post" onsubmit="return checkIt()">
		<input type="password" name="pw" id="changePw" placeholder="변경하실 비밀번호를 입력하세요" class="form-pw"><br>
		<input type="password" name="pw2" id="changePw2" placeholder="비밀번호확인" class="form-pw"><br>
		<div class="" id="checkPw">비밀번호가 일치합니다.</div>
		<div class="" id="checkPw2">비밀번호가 일치하지 않습니다.</div>
		<input type="hidden" name="id" value="<%=request.getParameter("id")%>"/>
		<input type="submit" value="변경하기" id="pwSubmit">
	</form>
</section>
<script>
	$(function(){
		$("#checkPw").hide();
		$("#checkPw2").hide();
		$("#changePw2").keyup(function(){
			var pwd1 = $("#changePw").val();
			var pwd2 = $("#changePw2").val();
			if(pwd1 != "" || pwd2 != ""){ 
				if(pwd1 == pwd2){ 
					$("#checkPw").show(); 
					$("#checkPw2").hide(); 
					$("#submit").removeAttr("disabled");
				}else {
					$("#checkPw").hide();
					$("#checkPw2").show();
					$("#submit").attr("disabled", "disabled");
				}
			}
		});
	});
	
	function checkIt(){
		var changePw = document.getElementById("changePw");
		var changePw2 = document.getElementById("changePw2");
		if(changePw.value != changePw2.value){
			alert("비밀번호가 일치하지 않습니다.")
			return false;
		} else{
			alert("비밀번호 변경 완료");
		}
	}

</script>
<%@ include file="/views/common/footer.jsp" %>