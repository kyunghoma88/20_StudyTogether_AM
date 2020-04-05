<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kh.cart.model.vo.Cart"%>
<%
	System.out.println("buyForm으로 넘어온 내용");
	String[] result= request.getParameterValues("cartList");
	
	for(int i=0; i<result.length; i++){
		System.out.println(result[i]);		
	}
	
%>

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
	<form action="<%=request.getContextPath()%>/buy/buyTest" method="post">
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
					<%-- <%for(Cart c:list){ %>
					<tr>
						<td id="prodImg0" class="img_cell ver_top">
							<a href="javascript:;" class="book_img" onclick="toView('blank', '9788990247674', 'KOR');"> 
								<img src="http://placehold.it/100x100">
							</a>
						</td>
						<td id="prodName0" class="align_left ver_top">
							<div class="order_name">
								<a href="javascript:;" onclick="#">
									<span><%=c.getLectorTitle() %></span>
								</a>
							</div>
						</td>
						<td><%=c.getLectorWriter() %></td>
						<td><%=c.getLectorCategory() %></td>
						<td>
							<strong><%=c.getLectorPrice() %></strong>원 
						</td>
					</tr>
					<%} %> --%>
				</tbody>
			</table>
		</div>
	</form>
	<div class="row d-flex justify-content-center ">
		<button class="btn btn-light w-50" type="button">결제하기</button>
		<button class="btn btn-light w-50" type="button" onclick="history.go(-1);">취소하기</button>
	</div>
</div>
<%@ include file="/views/common/footer.jsp"%>