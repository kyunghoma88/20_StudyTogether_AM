package com.kh.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFileDownServlet
 */
@WebServlet("/board/fileDown")
public class BoardFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//파일이 저장되어있는 실제 경로 가져오기
				//인코딩처리(한글로 된 파일은 글자가 깨짐)
				//저장파일을 load해줌 inputstream
				//response정보를 수정 -> contentType=application/octet-stream(파일출력하는것) / Content-Disposition:attachment; filename="파일명"
				//클라이언트에게 보내줌 -> 파일출력처리-output write
				
				String path=getServletContext().getRealPath("/upload/board/");
				//클라이언트가 보낸 파일명
				String file=request.getParameter("filePath");//파일명 가져오기
				
				//가져온 파일명과 일치하는 파일 가져오기
				File downFile=new File(path+file);
				//스트림 열기
				BufferedInputStream bis= new BufferedInputStream(new FileInputStream(downFile));
				//보낼 스트림 가져오기(클라이언트 스트림)
				//OutputStream은 바이트단위로 보낼때 getWriter은 글자같은걸 보낼때
				ServletOutputStream sos=response.getOutputStream();
				BufferedOutputStream bos=new BufferedOutputStream(sos);
				
				//파일명 인코딩 처리하기
				String resFileName="";
				//javascript BOM, user-agent:브라우저 전체정보
				boolean isMSIE=request.getHeader("user-agent").indexOf("MSIE")!=-1||
						request.getHeader("user-agent").indexOf("Trident")!=-1;
				//isMSIE가 true면 IE고 아니면 IE가 아님 
				if(isMSIE) {			
					resFileName=URLEncoder.encode(file,"UTF-8").replaceAll("\\+", "%20");
					//띄어쓰기를 \,+로 표시함 이건 URL인코딩값으로 변경
				}else {
					resFileName=new String(file.getBytes("UTF-8"), "ISO-8859-1");
				}
				//응답하기전 헤더부분 수정 -> 파일형식으로 보내는거야 라고 지정
				response.setContentType("application/octet-stream");
				//attachment : 다운로드할때 새창 열리는것
				//inline(읽어오는것)면 브라우저가 읽을수 있는 파일 : pdf,img,text -> chrome은 지원X
				response.setHeader("Content-Disposition", "attachment;fileName="+resFileName);
				
				//스트림에서 파일을 읽어오고 다시 파일을 클라이언트에게 전송!
				int read=-1;
				
				while((read=bis.read())!=-1) {
					bos.write(read);
				}
				
				bis.close();
				bos.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
