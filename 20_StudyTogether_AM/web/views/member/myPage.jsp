<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>


<!-- DAUM POSTCODE API -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- header페이지 불러오기 -->
<div class="container">
	<div name="nav" class="container-fluid mt-3 mb-3">
        <div class="row">
            <nav>
                <ul class="navbar-nav flex-row">
                    <li class="nav-item ml-5"><a class="text-decoration-none text-dark" href="<%=request.getContextPath()%>/member/memberView">내 계정 정보</a></li>
                    <li class="nav-item ml-5"><a class="text-decoration-none text-dark" href="#">내 일반스터디</a></li>
                    <li class="nav-item ml-5"><a class="text-decoration-none text-dark" href="#">내 강좌</a></li>
                    <li class="nav-item ml-5"><a class="text-decoration-none text-dark" href="<%=request.getContextPath()%>/payment/paymentForm">구매Test</a></li>
                </ul>
            </nav>
        </div>
    </div>
    <div name="must" class="container-fluid mt-5 mb-3">
        <div class="row">
            <fieldset>
                <legend>필수입력</legend>
                <form method="POST" name="updateForm" class="form-group">
                    <table class="table">
                        <tbody>
                            <tr>
                                <td>
                                    <label for="myPageId">아이디</label>
                                </td>
                                <td>
                                    <input type="text" name="id" id="myPageId" value="<%=loginMember.getUserId()%>" style="display:none;">
                                    <span><%=loginMember.getUserId()%></span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="myPagePwd">이름</label>
                                </td>
                                <td>
                                    <input type="text" name="username" id="myPageUsername" value="<%=loginMember.getUserName()%>" style="display:none;">
                                    <span><%=loginMember.getUserName()%></span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="myPagePwd">비밀번호</label>
                                </td>
                                <td>
                                    <input type="password" name="pwd" id="myPagePwd" value="">　　　　　　　　　　　　
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="myPagePwdCheck">비밀번호 확인</label>
                                </td>
                                <td>
                                    <input type="password" name="pwdCheck" id="myPagePwdCheck">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="myPageEmail">이메일</label>
                                </td>
                                <td>
                                    <input type="email" name="email" id="myPageEmail" value="<%=loginMember.getEmail()%>">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </fieldset>
        </div>
    </div>
    <div name="option" class="container-fluid">
        <div class="row mt-2">
            <fieldset>
                <legend>선택입력</legend>
                <form method="POST" name="updateForm" class="form-group">
                    <table class="table">
                        <tbody>
                            <tr>
                                <td>
                                    <label for="nickname">닉네임</label>
                                </td>
                                <td>
                                    <input type="text" name="nickname" id="nickname" value="<%=loginMember.getNickName()!=null?loginMember.getNickName():""%>">                                
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="gender">성별</label>
                                </td>
                                <td>
                                    <div class="form-check-inline">
                                        <label class="form-check-label">
                                            <input type="radio" class="form-check-input" name="gender" id="gender" value="M" <%=loginMember.getGender()!=null&&loginMember.getGender().equals("M")?"checked":"unchecked"%>>남
                                        </label>
                                    </div>
                                    <div class="form-check-inline">
                                        <label class="form-check-label">
                                            <input type="radio" class="form-check-input" name="gender" id="gender" value="F" <%=loginMember.getGender()!=null&&loginMember.getGender().equals("F")?"checked":"unchecked"%>>여
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="birth">생년월일</label>
                                </td>
                                <td>
                                    <input type="date" name="birth" id="birth" value="<%=loginMember.getBirthDate()!=null?loginMember.getBirthDate():""%>">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="phone">전화번호</label>
                                </td>
                                <td>
                                    <input type="phone" name="phone" id="phone" placeholder="예 : 010XXXX1234" value="<%=loginMember.getPhone()!=null?loginMember.getPhone():""%>">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="address1">주소</label>
                                </td>
                                <td>
                                    <!-- DAUM Post API -->
                                    <input type="text" id="sample3_postcode" name="" placeholder="우편번호">
                                    <input type="button" onclick="sample3_execDaumPostcode()" name="" value="우편번호 찾기"><br>
                                    <input type="text" id="sample3_address" placeholder="주소" name="address1" value="<%=loginMember.getAddress()!=null?loginMember.getAddress():""%>"><br>
                                    <input type="text" id="sample3_detailAddress" name="address2" placeholder="상세주소">
                                    <input type="text" id="sample3_extraAddress" name="" placeholder="참고항목">

                                    <div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
                                        <img src="https://t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
                                    </div>
                                </td>
                            </tr>
                            <script>
                                // 우편번호 찾기 찾기 화면을 넣을 element
                                var element_wrap = document.getElementById('wrap');

                                function foldDaumPostcode() {
                                    // iframe을 넣은 element를 안보이게 한다.
                                    element_wrap.style.display = 'none';
                                }

                                function sample3_execDaumPostcode() {
                                    // 현재 scroll 위치를 저장해놓는다.
                                    var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
                                    new daum.Postcode({
                                        oncomplete: function(data) {
                                            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                                            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                                            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                                            var addr = ''; // 주소 변수
                                            var extraAddr = ''; // 참고항목 변수

                                            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                                            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                                                addr = data.roadAddress;
                                            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                                                addr = data.jibunAddress;
                                            }

                                            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                                            if(data.userSelectedType === 'R'){
                                                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                                                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                                                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                                                    extraAddr += data.bname;
                                                }
                                                // 건물명이 있고, 공동주택일 경우 추가한다.
                                                if(data.buildingName !== '' && data.apartment === 'Y'){
                                                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                                                }
                                                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                                                if(extraAddr !== ''){
                                                    extraAddr = ' (' + extraAddr + ')';
                                                }
                                                // 조합된 참고항목을 해당 필드에 넣는다.
                                                document.getElementById("sample3_extraAddress").value = extraAddr;
                                            
                                            } else {
                                                document.getElementById("sample3_extraAddress").value = '';
                                            }

                                            // 우편번호와 주소 정보를 해당 필드에 넣는다.
                                            document.getElementById('sample3_postcode').value = data.zonecode;
                                            document.getElementById("sample3_address").value = addr;
                                            // 커서를 상세주소 필드로 이동한다.
                                            document.getElementById("sample3_detailAddress").focus();

                                            // iframe을 넣은 element를 안보이게 한다.
                                            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                                            element_wrap.style.display = 'none';

                                            // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                                            document.body.scrollTop = currentScroll;
                                        },
                                        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
                                        onresize : function(size) {
                                            element_wrap.style.height = size.height+'px';
                                        },
                                        width : '100%',
                                        height : '100%'
                                    }).embed(element_wrap);

                                    // iframe을 넣은 element를 보이게 한다.
                                    element_wrap.style.display = 'block';
                                }
                            </script>
                            </tr>
                        </tbody>
                    </table>
                    <div class="row">
                    	<span class="col-10 text-right small">회원에서 탈퇴하시려면 여기로</span>
                    	<input type="button" class="btn btn-light w-10 col btn-sm" id="deactivate" value="회원탈퇴" onclick="">
                    </div>
                    <div class="row mt-4">
                        <input type="button" class="btn btn-light col" id="join" value="회원수정">
                        <script>
                        	$("#join").click(function(){
                        		function updateOK(){
	                        		var formMerge=$("form[name='updateForm']").serialize();
	                        		console.log(formMerge);
	                        		$.ajax({  
	    							    url : "<%=request.getContextPath()%>/member/memberUpdate",  
	    							    type : "POST",  
	    							    data : formMerge,
	    							    dataType: "html",
	    							    success : function(data){  
	    							    	$('#myPagePwd').val('');
	    							    	$('#myPagePwdCheck').val('');
	    							    	alert('회원 정보를 수정 했습니다');
	    							    }, 
	    							    error:function(data){ //ajax 오류인경우  
	    							    	alert('회원 수정에 실패했습니다. 관리자에게 문의하세요');
	        							}  
	    							});  
                        			
                        		}
                        		
                        		if($('#myPagePwdCheck').val()==$('#myPagePwd').val() && $('#myPagePwd').val()=='<%=loginMember.getPassword()%>'){
                        			updateOK();
                        		}else if($('#myPagePwdCheck').val()==""){
                        			alert('회원정보 수정을 위해 비밀번호를 입력하세요.');
                        		}else{
                        			alert('입력한 비밀번호가 서로 일치하지 않습니다.');
                        		}
                        	});
                        	$("#deactivate").click(function(){
								$("div[name='must']").hide();
								$("div[name='option']").hide();
 						 		$("div[name='delForm']").removeClass("d-none");
 							});
                        </script>
                    </div>
                </form>
            </fieldset>
        </div>
    </div>
    <div name="delForm" class="container-fluid d-none">
        <div class="row">
            <fieldset>
                <legend>회원 탈퇴</legend>
                <form method="POST" name="deleteForm" class="form-group">
                    <table class="table">
                        <tbody>
                        	<h5>가입시 입력한 비밀번호를 한번 더 입력하세요</h5>
                        	<input type="hidden" name="dId" value="<%=loginMember.getUserId()%>">
                        	<input type="hidden" name="dPw" value="<%=loginMember.getPassword()%>">
                            <tr>
                                <td>
                                    <label for="deletePwdCheck">비밀번호 확인</label>
                                </td>
                                <td>
                                    <input type="password" name="deletePwdCheck" id="deletePwdCheck" value="">
                                </td>
                            </tr>
                            <tr>
		                        <td colspan="2">
                            		<p>정말 탈퇴하시겠습니까?</p>
      		                        <input type="button" class="btn btn-light col" id="deactiveMember" value="회원탈퇴">
		                        </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <script>
                	$("#deactiveMember").click(function(){
                		if($("#deletePwdCheck").val()!=null && $("#deletePwdCheck").val()=="<%=loginMember.getPassword()%>"){
                			
                			let newForm = document.createElement('form');
                			newForm.method = 'POST';
                			newForm.action = '<%=request.getContextPath()%>/member/deleteMember';
                			newForm.name = 'newForm';

                			let data = document.createElement('input');
                			data.setAttribute('type', 'hidden');
                			data.setAttribute('name', 'id');
                			data.setAttribute('value', '<%=loginMember.getUserId()%>');
                			newForm.appendChild(data);
                			
                			data = document.createElement('input');
                			data.setAttribute('type', 'hidden');
                			data.setAttribute('name', 'pw');
                			data.setAttribute('value', '<%=loginMember.getPassword()%>');
                			newForm.appendChild(data);
                			
                			document.body.appendChild(newForm);
                			newForm.submit();
                			
            	         
                		} else if($("#deletePwdCheck").val()==""){
							alert('비밀번호를 입력하지 않았습니다');
                		} else {
                			alert('비밀번호가 일치하지 않습니다.');
                		}
                	})
                </script>
            </fieldset>
        </div>
    </div>
</div>
<%@ include file="/views/common/footer.jsp" %>
    