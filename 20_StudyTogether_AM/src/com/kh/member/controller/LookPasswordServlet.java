//비밀번호 찾기 (이메일 인증)

package com.kh.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.Session;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

import oracle.net.aso.p;

/**
 * Servlet implementation class LookPasswordServlet
 */
@WebServlet("/lookforpassword")
public class LookPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LookPasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		Member m = new MemberService().lookforPassword(id, name, email);
		
		
		PrintWriter writer = response.getWriter();
		

			String host = "http://localhost:9090/20_StudyTogether_AM/";
			String from = "studySemiproject@gmail.com";
			String to = email;
			String subject = "비밀번호 변경을 위한 인증 메일입니다.";
			String content = "링크에 접속하여 비밀번호를 변경 해주세요." + "<a href =" + host + "/views/member/lookPassword.jsp?id="+id+">비밀번호 변경하기</a>";

			// SMTP 서버 정보를 설정
			Properties props = new Properties();
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.host", "smtp.googlemail.com");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
				
			if (m!=null&&(m.getUserId().equals(id) && m.getUserName().equals(name)) && m.getEmail().equals(email)) {
				try {
					Authenticator auth = new Google();
					Session ses = Session.getInstance(props, auth);
					ses.setDebug(true);
					MimeMessage msg = new MimeMessage(ses);
					msg.setSubject(subject);
					Address fromAddr = new InternetAddress(from);
					msg.setFrom(fromAddr);
					Address toAddr = new InternetAddress(to);
					msg.addRecipient(Message.RecipientType.TO, toAddr);
					msg.setContent(content, "text/html; charset=UTF-8");
					Transport.send(msg);
					writer.println("<script>");
					writer.println("alert('Complete');location.href='index.jsp'");
					writer.println("</script>");
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
					writer.println("<script>");
					writer.println("alert('exception');location.href='index.jsp'");
					writer.println("</script>");
					writer.close();
				}

			}else {
				writer.println("<script>");
				writer.println("alert('failed')");
				writer.println("alert('exception');location.href='index.jsp'");
				writer.println("</script>");
				writer.close();
			}
				
//				writer.println("<script>");
//				writer.println("alert('Complete. Check your E-MAIL')");
//				writer.println("</script>");
//				writer.println("");
//				writer.close();
			}

		

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
