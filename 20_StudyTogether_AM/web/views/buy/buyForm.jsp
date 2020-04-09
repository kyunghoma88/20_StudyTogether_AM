<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kh.cart.model.vo.Cart,java.text.DecimalFormat.*"%>

<%
	List<Cart> list=(List)request.getAttribute("cartList");
	String[] cartNoArr=(String[])request.getAttribute("cartNoArr");
	
	String totalSum=(String)request.getAttribute("totalSum");
	DecimalFormat formatter=new DecimalFormat();
	
	session.setAttribute("cartList", list);
%>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


<!-- header페이지 불러오기 -->
<%@ include file="/views/common/header.jsp"%>
<style>
	.btn_toggle {
		display: inline-block;
		width: 43px;
		height: 43px;
		font-size: 0;
		color: transparent;
		border: none;
		background: url('<%=request.getContextPath()%>/images/btn_toggle2.gif') no-repeat 10px 16px;
		transition: 0.3s;
	}
	.btn_toggle.on {
		background-position: 10px -14px;
	}
	th[name='order_name']{
		background-color:#EAEAEA;
	}
</style>
<script>
	$(document).on('click','.btn_toggle',function(){
		if($(this).hasClass('on')){
			$(this).removeClass('on');
			$('.payInfo').hide(100);
		}else{
			$(this).addClass('on');
			$('.payInfo').show(100);
		}
	})
</script>
<div class="container">
	<div class="row border-bottom mt-5 d-flex justify-content-between">
		<h4>주문자 정보</h4>
	</div>
	<form name="payment" action="<%=request.getContextPath()%>/buy/buyTest" method="post">
		<div class="row">
			<table class="table">
				<tr class="row border-top border-bottom">
					<th class="col-2 border-right text-center" name="order_name">주문자</th>
					<td class="col border-left pl-5">
						<%=loginMember.getUserName()!=null?loginMember.getUserName():"이름없음" %>
						<span class="p-5">|</span> 
						<%=loginMember.getPhone()!=null?loginMember.getPhone():"전화번호없음" %> 
						<span class="p-5">|</span> 
						<%=loginMember.getEmail()!=null?loginMember.getEmail():"이메일없음" %> 
						<input type="hidden" name="order_name" value="<%=loginMember.getUserName()!=null?loginMember.getUserName():"이름없음" %>"> 
						<input type="hidden" name="order_email" value="<%=loginMember.getEmail()!=null?loginMember.getEmail():""%>">
						<input type="hidden" name="order_phone" value="<%=loginMember.getPhone()%>"> 
						<input type="hidden" name="order_id" value="<%=loginMember.getUserId()!=null?loginMember.getUserId():""%>">
					</td>
				</tr>
				<tr class="row border-top border-bottom">
					<th class="col-2 border-right text-center" name="order_name">주소</th>
					<td class="col border-left">
					
                        <!-- DAUM Post API -->
                        <input type="text" id="sample3_postcode" name="sample3_postcode" placeholder="우편번호">
                        <input type="button" onclick="sample3_execDaumPostcode()" name="" value="우편번호 찾기"><br>
                        <input type="text" id="sample3_address" placeholder="주소" name="sample3_address" value="<%=loginMember.getAddress()!=null?loginMember.getAddress():""%>"><br>
                        <input type="text" id="sample3_detailAddress" name="sample3_detailAddress" placeholder="상세주소">
                        <input type="text" id="sample3_extraAddress" name="sample3_extraAddress" placeholder="참고항목">

                        <div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
                            <img src="https://t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
                        </div>
                        
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
                        
                    </td>
				</tr>
			</table>
		</div>
		<div class="row border-bottom mt-5 d-flex justify-content-between">
			<h4>강좌 정보</h4>
			<a href="javascript:;" class="btn_toggle on"></a>
		</div>
		<!-- 강좌정보 반복 출력 -->
		<div class="row mt-5 payInfo">
			<table class="table" id="orderTable" summary="강좌명, 총 가격">
				<caption style="caption-side:top;">주문 세부정보</caption>
				<colgroup>
					<col width="72">
					<col width="40%">
					<col width="15%">
					<col width="15%">
					<col width="16%">
				</colgroup>
				<thead>
					<tr>
						<th scope="col" colspan="2">강좌명</th>
						<th scope="col">강사명</th>
						<th scope="col">카테고리</th>
						<th scope="col">판매가</th>
					</tr>
				</thead>
				<tbody>
				<%if(list.isEmpty()){%>
					<tr>
						<td>결제 요청한 목록이 없습니다.</td>
					</tr>
					<%}else{ %>
					
					<%for(Cart c:list){ %>
					<tr>
						<td id="prodImg0" class="img_cell ver_top">
							<a href="javascript:;" class="book_img" onclick="toView('blank', '9788990247674', 'KOR');"> 
								<img src="http://placehold.it/100x100">
							</a>
						</td>
						<td id="prodName0" class="align_left ver_top">
							<div class="order_name">
								<a href="javascript:location.href='<%=request.getContextPath() %>/lector/lectorView?pNo=<%=c.getLectorNo()%>'">
									<span><%=c.getLectorTitle() %></span>
								</a>
							</div>
						</td>
						<td><%=c.getLectorWriter() %></td>
						<td><%=c.getLectorCategory() %></td>
						<td>
							<strong><%=formatter.format(c.getLectorPrice()) %></strong>원 
						</td>
					</tr>
					<%} %>
				<%} %>
				</tbody>
			</table>
			<input type="hidden" name="totalPrice" value="<%=totalSum%>">
		</div>
	</form>
	<div class="row mb-5">
		<h4 class="col text-right">총 결제금액 :　　　<span><%=formatter.format(Integer.parseInt(totalSum)) %> 원　　　　　</span></h4>
	</div>
	<div class="row d-flex justify-content-center ">
		<button class="btn btn-light w-50" type="button" onclick="exePayment();">결제하기</button>
		<button class="btn btn-light w-50" type="button" onclick="history.go(-1);">이전 페이지로</button>
	</div>
	<script>
		function exePayment(){
			var payment=document.querySelector("form[name='payment']");
			payment.submit();
		}
	</script>
</div>
<%@ include file="/views/common/footer.jsp"%>