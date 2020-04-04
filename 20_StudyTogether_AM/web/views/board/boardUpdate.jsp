<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.board.model.vo.Board"%>
<%
	Board b=(Board)request.getAttribute("board");
	String files[]=(String[])request.getAttribute("files");
%>
<%@ include file="/views/board/aside.jsp"%>
        <div class="write_content">
            <div class="category_name">카테고리 이름</div>
            <form action="<%=request.getContextPath()%>/board/updateEnd" 
                method="post" enctype="multipart/form-data" onsubmit="return valiwrite();">
                <input type="hidden" name="no" value="<%=b.getBoard_no()%>"/>
                <input type="hidden" name="fileCnt"/>
            <div class="write_title">
                <div class="write_item">
                    <span style="font-weight: bold;">제목</span>
                </div>
                <input type="text" name="title" id="title" value="<%=b.getTitle() %>" size="70"/>
                <div style="padding-left: 106px; margin-top:16px">
                    <textarea name="write_text" id="write_text" style="width: 708px; height:390px; resize:none"> <%=b.getContent().trim() %></textarea>
                </div>
                <div class="write_item" style="float: left; margin-top: 16px;">
                    <span style="font-weight: bold;">첨부파일</span>
                </div>
                <div style="float: left; margin-left: 6px; margin-top: 16px;">
                    <button type="button" class="fileAdd" id="fileAdd">
                        <i class="fas fa-plus-circle"></i>추가하기
                    </button>
                    <button type="button" class="fileDelete" id="fileDelete">
                        <i class="fas fa-times-circle"></i>삭제하기
                    </button>
                </div>
                <div id="fileArea">
                <%if(files.length==0) {%>
                    <div id="data1">
                        <span class="item">파일#1</span> 
                        <input type="file" name="fileup1" id="fileup1">
                    </div>
                <%}else { 
                	for(int i=1;i<=files.length;i++){%>
                	<div id="data<%=i%>">
                        <span class="item">파일#<%=i%></span> 
                        <input type="file" name="fileup<%=i%>" id="fileup<%=i%>">
                        <span id="fname<%=i %>" class="fname" onchange="changeFile();"><%=files[i-1] %></span>
                        <input type="hidden" name="oriFile<%=i %>" value="<%=files[i-1] %>"/>
                    </div>
                <%}
                	} %>
                </div>
                <div id="filelimit"></div>
                <div class="sendArea">
                    <button type="submit" class="write"><i class="fas fa-check"></i> 작성완료</button>
                    <a href="<%=request.getContextPath() %>/board/boardList" class="write" style="background-color: gray; height: 34px;" onclick="return confirm('뒤로 가시겠습니까?');">취소</a>
                </div>
            </div>
            </form>
        </div>
    </div>
    <script>
        $(function(){
            var cnt;
            if(<%=files.length%>==0){
            	cnt=1;
            }else{
            	cnt=<%=files.length%>;
            }
            
            //파일창 추가
            $("#fileAdd").click(event,function(){
                //var span=$("<span class='item'>파일#"+cnt+"</span>");
                var data="<div id='data"+(cnt+1)+"'><span class='item'>파일#"+(cnt+1)+"</span> "+
                "<input type='file' name='fileup"+(cnt+1)+"' id='fileup"+(cnt+1)+"'></div>";
                    if(cnt > 4){
                    $("#filelimit").html("※파일은 최대 5개까지만 등록가능합니다.").
                    css({
                        "color":"red",
                        "font-weight":"bold",
                        "padding-left":"106px",
                        "position":"relative",
                        "top":"16px"
                    });
                    
                    //$("#fileAdd").off("click");
                    event.preventDefault();
                }else{
                    $("#fileArea").append(data);
                }
                if(cnt<5)cnt++;
                //var input=$("<input type='file' name='fileup"+cnt+"' id='fileup"+cnt+"'>");        
                console.log(cnt);
            })
            //파일창 삭제
            $("#fileDelete").click(event,function(){
                if(cnt == 1){
                    //$("#fileDelete").off("click");
                    event.preventDefault();
                }else{
                    $("#data"+cnt).remove();
                }
                if(cnt>1){
                    cnt--;
                }
                if(cnt<5){
                    $("#filelimit").html("").
                    removeAttr("style");
                }
                
            })
        })
        function valiwrite(){
        	var title=document.getElementById("title");
        	var content=document.getElementById("content");
        	console.log("제목 : "+title.value);
        	if(title.value.trim()==""){
        		alert("제목을 입력하세요");
        		title.value="";
        		title.focus();
        		return false;
        	}else if(write_text.value.trim()==""){
        		alert("내용을 입력하세요");
        		write_text.value="";
        		write_text.focus();
        		return false;
        	}else{
        		return true;
        	}
        } 
        $(function(){
            var fileCnt=document.getElementsByName("fileCnt");
            
            $("form").submit(function(){
                fileCnt[0].value=$("input[type='file']").length;
            });
        	$("input[type='file']").change(function(){
        		if($(this).val()==""){
        			$(this).next().show();
        		}else{
        			$(this).next().hide();
        		}
        	});
        });
    </script> 
<%@ include file="/views/common/footer.jsp"%>