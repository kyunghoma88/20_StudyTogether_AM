<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.board.model.vo.Board,java.util.List,com.kh.board.model.vo.Comment" %>
<%
	Board b=(Board)request.getAttribute("board");
	int maxNo=(int)request.getAttribute("maxNo");
	int minNo=(int)request.getAttribute("minNo");
	Board preView=(Board)request.getAttribute("preView");
	Board nextView=(Board)request.getAttribute("nextView");
	int cPage=(int)request.getAttribute("cPage");
	List<Comment> commentList=(List)request.getAttribute("commentList");
	String category=(String)request.getAttribute("category");
%>
<%@ include file="/views/board/aside.jsp"%>
		<div class="view_content">
			<div class="category_name">커뮤니티</div>
			<div class="view_content2">	
				<ul class="view_topMenu" style="text-align: right;">
					<li style="color: blue;"><a href="<%=request.getContextPath()%>/"><i class="fas fa-home"></i></a></li>
					<li><a class="view_category" href="<%=request.getContextPath()%>/board/boardList">묻고 답하기</a></li>
					<li style="color: red; font-weight: 600;">커뮤니티</li>
				</ul>		
				<div class="view_title">
					<%=b.getTitle() %>
				</div>
				<div style="padding: 5px 0px; border-bottom: 1px solid gray;">
					<ul class="view_topMenu" style="margin-bottom: 0px; padding-left: 20px;">
						<li><%=b.getNickname() %>&nbsp;</li>
						<li><%=b.getWrite_date() %> &nbsp;</li>
						<li>조회수 <%=b.getCnt() %>&nbsp;</li>
						<li>좋아요 <%=b.getGood_cnt() %>&nbsp;</li>
						<li>싫어요 <%=b.getBad_cnt() %>&nbsp;</li>
					</ul>
				</div>
				<div style="border-bottom: 1px solid gray;">
				<%if(b.getFile_upload() != null) {
					String file[]=b.getFile_upload().split(","); 
					for(int i=0;i<file.length;i++){
					if(file[i].equals("")||file[i].equals("null")) continue;%>
					<div style="text-align: right;">
						<a class="fileDown" href="<%=request.getContextPath() %>/board/fileDown?filePath=<%=file[i] %>">
						<i class="fas fa-download"></i>
						<%=file[i] %>
						</a>
					</div>
				<%
					} 
				}%>
					<p style="text-indent: 1em; margin-top: 16px;"><%=b.getContent() %></p>
					<div style="text-align: center;">
						<a class="good" href="javascript:void(0)">
							<i style="color: red"class="far fa-thumbs-up"></i><br>
							<span id="goodAdd"><%=b.getGood_cnt() %></span><br>
							좋아요
						</a>
						<a class="bad" href="javascript:void(0)">
							<i style="color: blue"class="far fa-thumbs-down"></i><br>
							<span id="badAdd"><%=b.getBad_cnt() %></span><br>
							싫어요
						</a>
					</div>
					<div style="padding-top: 50px;">
						<ul class="view_topMenu" style="padding-left: 20px;">
							<li>댓글 1</li>
							<li>최신순</li>
						</ul>
					</div>
					<div style="background-color: lightgray; padding: 10px 26px 16px 16px;">
					<%if(commentList!=null && !commentList.isEmpty()) {
						int i=1;
						for(Comment c : commentList){
							if(c.getComment_level()==1){%>
						<div class="comment_area">
						<ul class="view_comment">
							<li style="font-size: 14px; font-weight: bold;"><%=c.getComment_writer() %></li>
							<%if(b.getNickname().equals(c.getComment_writer())) {%>
							<li>
								<span style="color:red; border-radius:8px; border:1px solid red; padding: 2px 5px;">작성자</span>
							</li>
							<%} %>
							<li><%=c.getComment_date() %></li>
							<li><a id="comment<%=i %>" class="comment" href="javascript:void(0)" value="<%=c.getComment_no()%>">
								<i class="fas fa-reply fas-lg"></i>답글</a>
							</li>
							<li style="float:right;">
							<%if(loginMember!=null&&loginMember.getUserId().equals(c.getComment_writer())) {%>
								<a class="comment_delete" href="javascript:comment_delete(<%=c.getComment_no()%>)" style="text-decoration:none;">삭제</a>
							</li>
							<%} %>
						</ul>
						<p class="comment_content"><%=c.getComment_content() %></p>
						</div>
						<%		i++;}else{%>
						<div class="comment_area">
						<ul class="view_comment2">
							ㄴ<li style="font-size: 14px; font-weight: bold;"><%=c.getComment_writer() %></li>
							<%if(b.getNickname().equals(c.getComment_writer())) {%>
							<li>
								<span style="color:red; border-radius:8px; border:1px solid red; padding: 2px 5px;">작성자</span>
							</li>
							<%} %>
							<li><%=c.getComment_date() %></li>
							<%if(loginMember!=null&&loginMember.getUserId().equals(c.getComment_writer())) {%>
							<li style="float:right;">
								<a class="comment_delete" href="javascript:comment_delete(<%=c.getComment_no()%>)" style="text-decoration:none;">삭제</a>
							</li>
							<%} %>
						</ul>
						<p class="comment_content" style="padding-left:98px;"><%=c.getComment_content() %></p>
						</div>			
						<%		}
							}
						}%>
						<!-- <div style="padding-bottom: 16px;">
							댓글에대한 답글
						</div> -->
						<div class="comment_container">
							<textarea cols="50" rows="3" class="comment_text" id="comment_text"></textarea>
							<div style="float: left; margin-left: 20px;">
								<a href="javascript:commentInsert()" class="comment_insert">등록</a>
							</div>
						</div>
						<form name="commentInsert" method="post" action="<%=request.getContextPath()%>/board/comment">
							<input type="hidden" name="board_ref" value="<%=b.getBoard_no()%>">
							<%if(loginMember!=null) {%>
							<input type="hidden" name="comment_writer" value="<%=loginMember.getUserId()%>">
							<%} %>
							<input type="hidden" name="comment_no_ref" value="0">
							<input type="hidden" name="comment_level" value="1">
							<input type="hidden" name="comment_text">
						</form>
					</div>
				</div>
			</div>
			<div style="padding-top: 15px;">
				<ul class="listbtn" style="float: left;">
					<%if(loginMember!=null) {%>
            			<li><a id="write_btn" href="<%=request.getContextPath()%>/board/boardWrite?id=<%=loginMember.getUserId()%>&category=<%=category %>"><i class="fas fa-edit"></i>글쓰기</a></li>
					<%}else{ %>
						<li><a id="write_btn" href="<%=request.getContextPath()%>/board/boardWrite?category=<%=category %>"><i class="fas fa-edit"></i>글쓰기</a></li>
					<%} %>
					<li><a href="javascript:boareReply(<%=b.getBoard_no()%>,'<%=category%>')"><i class="fas fa-comment-dots"></i> 답글</a></li>
					<li><a href="javascript:boardList(<%=cPage%>,'<%=category%>')">목록</a></li>
				</ul>
				<%if(loginMember!=null&&loginMember.getUserId().equals(b.getNickname())) {%>
				<ul class="listbtn" style="float: right;">
					<li><a href="javascript:boardDelete(<%=b.getBoard_no()%>,'<%=category%>')">삭제</a></li>
					<li><a href="javascript:boardUpdate(<%=b.getBoard_no()%>,'<%=category%>')">수정</a></li>
				</ul>
				<%} %>
			</div>
			<table class="table" style="clear: both; width: 100%; font-size: 13px;">
                <%if(b.getBoard_no()!=maxNo) {%>
                    <tr>
                        <td style="width: 100px;"><a href="javascript:boardView(<%=b.getBoard_no()+1 %>,'<%=category%>')" style="text-decoration: none; color:black;">
                        <i class="fas fa-angle-up" style="color: orange;"></i> 	
                        	이전글</a>
                       	</td>
                        <td style="width: 530px;">
                        	<a href="javascript:boardView(<%=b.getBoard_no()+1 %>,'<%=category%>')" style="text-decoration: none; color:black;"><%=preView.getTitle() %></a>
                        </td>
                        <td><%=preView.getNickname() %></td>
                        <td style="text-align: center;"><%=preView.getWrite_date() %></td>
                    </tr>
                 <%} %>
                 <%if(b.getBoard_no()!=1) {%>
                    <tr>
                        <td><a href="javascript:boardView(<%=b.getBoard_no()-1 %>,'<%=category%>')" style="text-decoration: none; color:black;">
                        <i class="fas fa-angle-down" style="color: orange;"></i> 
                        	다음글</a>
                        </td>
                        <td>
                        	<a href="javascript:boardView(<%=b.getBoard_no()-1 %>,'<%=category%>')" style="text-decoration: none; color:black;"><%=nextView.getTitle() %></a>
                        </td>
                        <td><%=nextView.getNickname() %></td>
                        <td style="text-align: center;"><%=nextView.getWrite_date() %></td>
                    </tr>
                <%} %>
                </table>
                <form name="paging">
	                	<input type="hidden" name="no"/>
	                	<input type="hidden" name="cPage"/>
	                	<input type="hidden" name="category"/>
	                	<input type="hidden" name="id"/>
	                	<input type="hidden" name="comment_no"/>
	            </form>
		</div>
	</div>
	<script>
	var comment_no;
		$(document).ready(function(){
			$("a[class='good']").on({
				mouseenter:function(){
					$(this).css({
						color:"black",
						textDecoration:"none"
					})
				},
				click:function(){
					$.ajax({
						url:"<%=request.getContextPath()%>/ajax/goodUpdate",
						type:"post",
						data:{"no":<%=b.getBoard_no()%>,
							  "id":"<%=loginMember!=null?loginMember.getUserId():""%>"},
						success:function(data){
							$("#goodAdd").html(data);
							console.log($(".view_topMenu>li"));
							$(".view_topMenu>li").eq(6).html("좋아요 "+data+"&nbsp;");
						}
					});
				}
			});
			$("a[class='bad']").on({
				mouseenter:function(){
					$(this).css({
						color:"black",
						textDecoration:"none"
					})
				},
				click:function(){
					$.ajax({
						url:"<%=request.getContextPath()%>/ajax/badUpdate",
						type:"post",
						data:{"no":<%=b.getBoard_no()%>,
							  "id":"<%=loginMember!=null?loginMember.getUserId():""%>"},
						success:function(data){
							$("#badAdd").html(data);
							$(".view_topMenu>li").eq(7).html("싫어요 "+data+"&nbsp;");
						}
					});
				}
			});
			$(".comment").on({
				click:function(e){
					$(this).css({
						color:"gray",
						textDecoration:"none"
					})
					if($(this).html().includes("취소")){
						$(this).html("<i class='fas fa-reply fas-lg'></i>답글");
						$(this).parents().eq(2).find(".comment_container").hide();
					}else{
						comment_no = $(this).attr("value");
						$(".comment_container").not(".comment_container:last").remove();
						var target=e.target;
						$(".view_comment>li>a").not(event.target).css("color","gray")
						.html("<i class='fas fa-reply fas-lg'></i>답글");
						$(this).css({
							color:"black",
							textDecoration:"none"
						}).html("<i class='fas fa-reply fas-lg'></i>답글취소");
						//console.log($(".comment_area>ul>li").find("a"));
						var div=$("<div class='comment_container' style='padding-left: 100px; padding-bottom: 20px; border-bottom: 1px dotted blue;'>");
						var textarea="ㄴ<textarea cols='50' rows='3' class='comment_text' id='comment_text'></textarea>"+
									"<div style='float: left; margin-left: 20px;'>"+
									"<a href='javascript:commentInsert2()' class='comment_insert' id='comment_insert2'>등록</a>"+
									"</div>";
						div.append(textarea);
						$(this).parents().eq(2).append(div);
						console.log($(this).next());
					}
				}
			});
			
			
			<%-- setInterval(function(){
				$.ajax({
					url:"<%=request.getContextPath()%>/board/boardView",
					type:"post",
					data:{"cPage":<%=cPage%>,
						  "no":<%=b.getBoard_no()%>},
					success:function(data){
						console.log("test");
					}
				})
			},700) --%>
			
		})
		function boardView(no, category){
	    	var f=document.paging;
	    	f.no.value=no;
	    	f.category.value=category;
	    	f.action="<%=request.getContextPath()%>/board/boardView";
	    	f.method="post";
	    	f.submit();
	    }
		function boardUpdate(no, category){
			var f=document.paging;
	    	f.no.value=no;
	    	f.category.value=category;
	    	f.action="<%=request.getContextPath()%>/board/update";
	    	f.method="post";
	    	f.submit();
		}
		function boardList(cPage, category){
	    	var f=document.paging;
	    	f.cPage.value=cPage;
	    	f.category.value=category;
	    	f.action="<%=request.getContextPath()%>/board/boardList";
	    	f.method="post";
	    	f.submit();
		}
		function boardDelete(no, category){
			var f=document.paging;
	    	f.no.value=no;
	    	f.category.value=category;
	    	f.action="<%=request.getContextPath()%>/board/delete";
	    	f.method="post";
	    	f.submit();
		}
		function boareReply(no, category, id){
			var f=document.paging;
	    	f.no.value=no;
	    	f.category.value=category;
	    	<%if(loginMember!=null){%>	    		
	    		f.id.value="<%=loginMember.getUserId()%>";
	    	<%}%>
	    	f.action="<%=request.getContextPath()%>/board/replyWrite";
	    	f.method="post";
	    	f.submit();
		}
		function comment_delete(no){
	    	$.ajax({
	    		url:"<%=request.getContextPath()%>/comment/delete",
	    		type:"post",
				dataType:"json",
				data:{"no":no},
				success:function(data){
					console.log(data);
					if(data>0){						
						alert("댓글 삭제 성공");
						location.reload();
					}else{
						alert("댓글 삭제 실패");
					}
				}
	    	})
		}
		function commentInsert(){
			<%if(loginMember==null){%>
			alert("로그인후 이용 부탁 드립니다.");
			<%}else{%>
			$.ajax({
				url:"<%=request.getContextPath()%>/board/comment",
				type:"post",
				dataType:"json",
				data:{"comment_text":$("#comment_text").val(),
					  "board_ref":<%=b.getBoard_no()%>,
					  "comment_writer":"<%=loginMember!=null?loginMember.getUserId():"null"%>",
					  "comment_no_ref":0,
					  "comment_level":1},
				success:function(data){
					console.log(data.comment.comment_content);
					if(data.comment.comment_content==""){
						alert("댓글을 입력하세요!");
					}else{						
						alert("댓글 등록 성공!");
						location.reload();
					}
					
					$("#comment_text").val("");
					$("#comment_text").focus();
				}
			});
			<%}%>
		};
		function commentInsert2(){
			<%if(loginMember==null){%>
			alert("로그인후 이용 부탁 드립니다.");
			<%}else{%>
			$.ajax({
				url:"<%=request.getContextPath()%>/board/comment",
				type:"post",
				dataType:"json",
				data:{"comment_text":$("#comment_text").val(),
					  "board_ref":<%=b.getBoard_no()%>,
					  "comment_writer":"<%=loginMember!=null?loginMember.getUserId():"null"%>",
					  "comment_no_ref":comment_no,
					  "comment_level":2},
				success:function(data){
					console.log(data.comment.comment_content);
					if(data.comment.comment_content==""){
						alert("댓글을 입력하세요!");
					}else{						
						alert("댓글 등록 성공!");
						location.reload();
					}
					
				}
			});
			<%}%>
		};
		
	</script>
<%@ include file="/views/common/footer.jsp"%>