<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.kh.lector.model.vo.Lector" %>
<%@ include file="/views/admin/adminHeader.jsp" %>
<%
	List<Lector> list=(List)request.getAttribute("list");
%>
<div class="container">
	<div class="row">
	    <h3>개설강좌 목록</h3>
	    <table class="table">
	          <thead class="text-center">
	            <tr>
	              <th><input type="checkbox" id="classSelect" onclick="check_class_toggle(this);"></th>
	              <th>등록 번호</th>
	              <th>개설자</th>
	              <th>강좌명</th>
	              <th>등록일</th>
	              <th>강좌 폐강</th>
	            </tr>
	          </thead>
	          <tbody class="text-center">
              <%if(list.size()==0){ %>
	          <tr>
	          	<td colspan="5">조회된 강좌 목록이 없습니다</td>
	          </tr>
	          <%}else{ %>
	           	<%for(Lector l:list){ %>
			  <form action="<%=request.getContextPath() %>/admin/adminManageLectorEnd" class="form-group col" method="post" name="frm1">
	            <tr>
	              <td class=""><input type="checkbox" name="checked" value="<%=l.getLectorNo() %>"></td>
	                <td class=""><%=l.getLectorNo() %></td>
	                <td class=""><%=l.getLectorWriter() %></td>
	                <td class=""><%=l.getLectorTitle()%></td>
	                <td class="">
	                  <div class="form-check-inline">
	                     <%=l.getLectorDate() %>
	                  </div>                        
	                </td>
	                <td class="">
	                  <div class="form-check-inline">
	                    <label class="form-check-label">
	                      <button type="button" class="form-check-input btn-secondary btn-sm target" id="lec<%=l.getLectorNo()%>">전송</button>
	                      <input type="hidden" id="btn<%=l.getLectorNo() %>" name="lecNo" value="<%=l.getLectorNo() %>">
	                      <input type="hidden" id="" name="lecNoArr" value="">
	                    </label>
	                  </div>
	                </td>
	            </tr>
			    </form>
	            <%} 
	            }%>      
	          </tbody>
	      </table>
	</div>
	<div class="row">
	  <div class="col-4"></div>
	 	<input class="col-3 btn btn-secondary text-center" type="button" value="선택한 강좌 폐강하기" id="subBtn"
			onclick="closeCheckedAll();">
	  <div class="col-5"></div>
	</div>
</div>
    <script>
	    var target = document.querySelectorAll(".target");
        var targetLength = target.length;
        
        let conf=false;
        
        for(var i=0; i < targetLength; i++){
            target[i].addEventListener("click",function(){
            	console.log(this);
                console.log("#btn"+this.id+"에 이벤트 추가");
        		let id=this.id
        		console.log(id);
        		let form=document.getElementById(id);
                console.log(form);
                let lecNo=id.replace("lec","");
                conf=confirm(id.replace("lec","")+"번 강좌를 폐강하시겠습니까?");
		        if(conf){
		        	let newForm = document.createElement('form');
	    			newForm.method = 'POST';
	    			newForm.action = '<%=request.getContextPath()%>/admin/adminManageLectorEnd';
	    			newForm.name = 'newForm';

	    			let data = document.createElement('input');
	    			data.setAttribute('type', 'hidden');
	    			data.setAttribute('name', 'lecNoArr');
	    			data.setAttribute('value', "");
	    			newForm.appendChild(data);
	    			
	    			data = document.createElement('input');
	    			data.setAttribute('type', 'hidden');
	    			data.setAttribute('name', 'lecNo');
	    			data.setAttribute('value', lecNo);
	    			newForm.appendChild(data);
	    			
	    			document.body.appendChild(newForm);
	    			newForm.submit();
		        }else{
		        	return;
		        }
        	});
        }
        
        
        function closeCheckedAll() {
        	var chk = document.getElementsByName("checked"); // 체크박스객체를 담는다
        	var len = chk.length;    //체크박스의 전체 개수
        	var checkRow = '';      //체크된 체크박스의 value를 담기위한 변수
        	var checkCnt = 0;        //체크된 체크박스의 개수
        	var checkLast = '';      //체크된 체크박스 중 마지막 체크박스의 인덱스를 담기위한 변수
        	var rowid = '';             //체크된 체크박스의 모든 value 값을 담는다
        	var cnt = 0;                 
        	
        	for(var i=0; i<len; i++){
	        	if(chk[i].checked == true){
		        	checkCnt++;        //체크된 체크박스의 개수
		        	checkLast = i;     //체크된 체크박스의 인덱스
	        	}
        	} 
        	
        	for(var i=0; i<len; i++){
	        	if(chk[i].checked == true){  //체크가 되어있는 값 구분
	        		checkRow = chk[i].value;
	        	}
	        	if(checkCnt == 1){                            //체크된 체크박스의 개수가 한 개 일때,
	        		rowid += ""+checkRow+"";        //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
	        	}else{                                            //체크된 체크박스의 개수가 여러 개 일때,
		        	if(i == checkLast){                     //체크된 체크박스 중 마지막 체크박스일 때,
	        			rowid += ""+checkRow+"";  //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
		        	}else{
		        		if(checkRow!=""){
				        	rowid += ""+checkRow+",";	 //'value',의 형태 (뒤에 ,(콤마)가 붙게)         					        					        			
		        		}
		        	}
        		}
        		cnt++;
        		checkRow = '';    //checkRow초기화.
       		}
        	console.log(rowid);
       		let conf=confirm("선택한 강좌를 삭제하시겠습니까?");    //'value1', 'value2', 'value3' 의 형태로 출력된다.
       		if(conf) {
       			let newForm = document.createElement('form');
    			newForm.method = 'POST';
    			newForm.action = '<%=request.getContextPath()%>/admin/adminManageLectorEnd';
    			newForm.name = 'newForm';

    			let data = document.createElement('input');
    			data.setAttribute('type', 'hidden');
    			data.setAttribute('name', 'lecNoArr');
    			data.setAttribute('value', rowid);
    			newForm.appendChild(data);
    			
    			document.body.appendChild(newForm);
    			newForm.submit();
       		}
   	    }
    </script>
    
<%@ include file="/views/admin/adminFooter.jsp" %>