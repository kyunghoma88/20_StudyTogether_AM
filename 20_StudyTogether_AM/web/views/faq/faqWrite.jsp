<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>

<style>
	#faq-container{
		height:800px;
		width : 1000px;
		margin-left : 100px;
	}

	.faq-header{
		margin : 0px;
		margin-top : 50px;
		
	}
	
	.form-control{
		font-size : 30px !important;
	}
	
	.title-con{
		width : 450px !important;
	}
	
	.select-btn{
		width : 300px !important;
	}
	
	
	
	#faq-content{
		margin-top : 5px;
	}
	
	#blank{
		
		margin-right : 650px;
	}
	
	table {
	  border-collapse: separate;
	  border-spacing: 0 15px;
	}
	
	.write-container{
		width : 1000px;
		font-size : 30px;
	
	}
	
	


</style>


<section id="faq-container">
	<div class="row">
		<div class="col-1"></div>
		<div class="container faq-header col-11">
			<p style="font-size:40px; font-weight:bolder;">FAQ 작성</p>
		</div>
	</div>
	
	<div class="write-container container form-group">
		<form action="<%=request.getContextPath() %>/faq/faqWriteEnd" method="post">
			<table>
				<colgroup>
					<col width="150px;" />
					<col width="750px;" />
					<col/>
				</colgroup>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" class="title-con" required></td>
				</tr>
				<tr>
					<th>카테고리</th>
					<!-- <td><input type="text" name="category" required></td> -->
					<td>
						<select class="form-control select-btn" name="category">
							<option value="">카테고리 선택</option>
							<option value="회원">회원</option>
							<option value="스터디개설">스터디개설</option>
							<option value="결제">결제</option>
							<option value="기타">기타</option>
						</select>
					<!-- <input type="text" name="category" required></td> -->
				</tr>
				<tr id="faq-content">
					<th>내용</th>
					<td><textarea rows="8" class="form-control" name="content" style="resize:none;"></textarea></td>
				</tr>
				<!-- <tr>
					<th colspan="2">
						<input type="submit" value="등록하기">
					</th>
				</tr> -->
				
			</table>
			
			<div style="display:flex; margin-top:30px;">
				<div id="blank">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div id="btn-regist">
					<button class="btn btn-outline-dark" style="font-size:25px" type="submit">등록하기</button>
				</div>
			</div>
			
		</form>
	</div>

</section>


<%@ include file="/views/common/footer.jsp"%>
