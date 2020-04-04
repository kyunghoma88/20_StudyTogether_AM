<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kh.board.model.vo.Board" %>
<%
	List<Board> list=(List)request.getAttribute("list");
	int cPage=(int)request.getAttribute("cPage");
	List<Board> replyList=(List)request.getAttribute("replyList");
%>
<%@ include file="/views/board/aside.jsp"%>
		<div class="board_content">
			<span class="category_name">서버에서 넘어올 커뮤니티 이름</span>
			<table class="table">
        		<thead>
            	<tr>
	            	<th></th>  
	                <th>제목</th>
	                <th>작성자</th>
	                <th>작성일</th>
	                <th>조회</th>
	                <th>좋아요</th>
	                <th>싫어요</th>
            	</tr>
	            </thead>
	            <tbody>
	            <%if(list==null||list.isEmpty()) {%>
	            	<tr>
	                	<td colspan="7">등록된 게시물이 존재하지 않습니다.</td>
	                </tr>
	            <%}else {%>
	            <%for(Board b : list) {%>  
	            <%if(b.getReply_level()==1) {%>          	
		        	<tr>
						<td><%=b.getBoard_no() %></td>
						<td>
							<a class="board_title" href="javascript:boardView(<%=b.getBoard_no()%>,<%=cPage%>)">
								<%=b.getTitle() %>
							</a>
						</td>
						<td><%=b.getNickname() %></td>
						<td><%=b.getWrite_date() %></td>
						<td><%=b.getCnt() %></td>
						<td><%=b.getGood_cnt() %></td>
						<td><%=b.getBad_cnt() %></td>
					</tr>
	            	<%}
	            	for(Board reply : replyList){
	            		if(b.getBoard_no()==reply.getReply_no()){%>
	            	<tr>
						<td colspan="2" style="text-align: right;">
							<a class="board_title" href="javascript:boardView(<%=reply.getBoard_no()%>,<%=cPage%>)">
								ㄴ<%=reply.getTitle() %>
							</a>
						</td>
						<td><%=reply.getNickname() %></td>
						<td><%=reply.getWrite_date() %></td>
						<td><%=reply.getCnt() %></td>
						<td><%=reply.getGood_cnt() %></td>
						<td><%=reply.getBad_cnt() %></td>
					</tr>
	            	<%		}
	            		}
	            	}
	             }%>
	            </tbody>
    		</table>
    		<div style="text-align: right;">
            	<a id="write_btn" class="write_btn" href="<%=request.getContextPath()%>/board/boardWrite"><i class="fas fa-edit"></i>글쓰기</a>
			</div>
			<div>
				<ul class="pagination justify-content-center" id="page">
					 <%=request.getAttribute("pageBar") %>
				</ul>
				<div>
						<select name="searchDate" id="searchDate" style="background-color:white;">
							<option value="full"selected>전체기간</option>
							<option value="week">1주일</option>
							<option value="month">1개월</option>
							<option value="halfYear">6개월</option>
							<option value="year">1년</option>
						</select>
						<select name="searchContent" id="searchContent" style="background-color:white;">
							<option value="titleContent" selected>제목+내용</option>
							<option value="title">제목만</option>
							<option value="writer">글작성자</option>
							<option value="replyContent">댓글내용</option>
							<option value="replyWriter">댓글작성자</option>
						</select>
						<input type="text" id="searchText" placeholder="검색어를 입력해주세요" size="30"><!--
						--><input type="submit" id="searchBtn" class="search_btn" value="검색"/>
					
				</div>
			</div>
		</div>
		<form name="paging">
	    	<input type="hidden" name="no"/>
	        <input type="hidden" name="cPage"/>
	        <input type="hidden" name="date"/>
	        <input type="hidden" name="content"/>
	        <input type="hidden" name="searchText"/>
	    </form>
	</div>
	<script>
		$(document).ready(function(){
			$("#write_btn").on({
				mouseenter:function(){
					$(this).css({
						color:"black"
					});
				}
			});
			$("#board_title").on({
				mouseenter:function(){
					$(this).css({
						color:"black",
						textDecoration:"none"
					});
				}
			});
			$("#searchText").on({
				keypress:function(e){
					if(e.originalEvent.key=='Enter'){
						if($("#searchText").val().trim()==""){
							alert("검색어를 입력해주세요!");
							return false;
						}
						$.ajax({
							url:"<%=request.getContextPath()%>/ajax/search",
							type:"post",
							dataType:"json",
							data:{"cPage":<%=cPage%>,
								  "date":$("#searchDate").val(),
								  "content":$("#searchContent").val(),
								  "searchText":$("#searchText").val().trim()},
							success:function(data){
								$("#page").html(data.pageBar);
								var list = data.list;
								if(list.length==0){
									$(".table>tbody").html("<tr><td colspan='7'>등록된 게시물이 존재하지 않습니다.</td></tr>");
								} 
								var td;
								for(let i=0;i<list.length;i++){
									td += "<tr><td>"+list[i].board_no+"</td>";
									td+="<td><a class='board_title' href='javascript:boardView("+list[i].board_no+","+<%=cPage%>+")'>"+
									list[i].title+"</a></td>";
									td+="<td>"+list[i].nickname+"</td>";
									td+="<td>"+list[i].write_date+"</td>";
									td+="<td>"+list[i].cnt+"</td>";
									td+="<td>"+list[i].good_cnt+"</td>";
									td+="<td>"+list[i].bad_cnt+"</td></tr>";
								}
								$(".table>tbody").html(td);
							}
						})
					}
				}
			});
			$("#searchBtn").on({
				click:function(){
					$.ajax({
						url:"<%=request.getContextPath()%>/ajax/search",
						type:"post",
						dataType:"json",
						data:{"cPage":<%=cPage%>,
							  "date":$("#searchDate").val(),
							  "content":$("#searchContent").val(),
							  "searchText":$("#searchText").val().trim()},
						success:function(data){
							console.log(data);
							$("#page").html(data.pageBar);
							var list = data.list;
							if(list.length==0){
								$(".table>tbody").html("<tr><td colspan='7'>등록된 게시물이 존재하지 않습니다.</td></tr>");
							} 
							var td;
							for(let i=0;i<list.length;i++){
								td += "<tr><td>"+list[i].board_no+"</td>";
								td+="<td><a class='board_title' href='javascript:boardView("+list[i].board_no+","+<%=cPage%>+")'>"+
								list[i].title+"</a></td>";
								td+="<td>"+list[i].nickname+"</td>";
								td+="<td>"+list[i].write_date+"</td>";
								td+="<td>"+list[i].cnt+"</td>";
								td+="<td>"+list[i].good_cnt+"</td>";
								td+="<td>"+list[i].bad_cnt+"</td></tr>";
							}
							$(".table>tbody").html(td);
						}
					})
				}
			})
		})
	function boardView(no, cPage){
    	var f=document.paging;
    	f.no.value=no;
    	f.cPage.value=cPage;
    	f.action="<%=request.getContextPath()%>/board/boardView";
    	f.method="post";
    	f.submit();
    }
    function boardList(cPage){
    	var f=document.paging;
    	f.cPage.value=cPage;
    	f.action="<%=request.getContextPath()%>/board/boardList";
    	f.method="post";
    	f.submit();
	} 	
    function boardFind(cPage,date,content,searchText){
    	$.ajax({
			url:"<%=request.getContextPath()%>/ajax/search",
			type:"post",
			dataType:"json",
			data:{"cPage":cPage,
				  "date":date,
				  "content":content,
				  "searchText":searchText},
			success:function(data){
				console.log(data);
				$("#page").html(data.pageBar);
				var list = data.list;
				if(list.length==0){
					$(".table>tbody").html("<tr><td colspan='7'>등록된 게시물이 존재하지 않습니다.</td></tr>");
				} 
				var td;
				for(let i=0;i<list.length;i++){
					td += "<tr><td>"+list[i].board_no+"</td>";
					td+="<td><a class='board_title' href='javascript:boardView("+list[i].board_no+","+<%=cPage%>+")'>"+
					list[i].title+"</a></td>";
					td+="<td>"+list[i].nickname+"</td>";
					td+="<td>"+list[i].write_date+"</td>";
					td+="<td>"+list[i].cnt+"</td>";
					td+="<td>"+list[i].good_cnt+"</td>";
					td+="<td>"+list[i].bad_cnt+"</td></tr>";
				}
				$(".table>tbody").html(td);
			}
		})
	}
    </script>
<%@ include file="/views/common/footer.jsp"%>