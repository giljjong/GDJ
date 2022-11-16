package com.gdu.app13.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app13.domain.RetireUserDTO;
import com.gdu.app13.domain.UserDTO;
import com.gdu.app13.mapper.UserMapper;
import com.gdu.app13.util.SecurityUtil;

@PropertySource(value = {"classpath:/email/email.properties"})
@Service
public class UserServiceImpl implements UserService {

	// 이메일을 보내는 사용자 정보
	@Value(value = "${mail.username}")
	private String username;  // 본인 지메일 주소
	
	@Value(value="${mail.password}")
	private String password;  // 발급 받은 앱 비밀번호
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private SecurityUtil securityUtil;
	
	@Override
	public Map<String, Object> isReduce(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		Map<String, Object> result = new HashMap<>();
		result.put("isUser", userMapper.selectUserByMap(map) != null);
		result.put("isRetireUser", userMapper.selectRetireUserById(id) != null);
		return result;
	}
	
	@Override
	public Map<String, Object> isReduceEmail(String email) {
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		Map<String, Object> result = new HashMap<>();
		result.put("isUser", userMapper.selectUserByMap(map) != null);
		return result;
	}
	
	@Override
	public Map<String, Object> sendAuthCode(String email) {
		String authCode = securityUtil.getAuthCode(6);
		System.out.println("발송된 인증코드 : " + authCode);
		
		// 이메일 전송을 위한 필수 속성을 Properties 객체로 생성
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");	// 구글 메일로 보냄(보내는 메일은 구글 메일만 가능)
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		/*
		 * 	이메일 보내기 API 사용을 위한 사전 작업
		 * 	1. 구글 로그인
		 * 	2. Google 계정 - 보안
		 * 		1) [2단계 인증] - [사용]
		 * 		2) [앱 비밀번호]
		 * 			(1) 앱 선택   : 기타
		 * 			(2) 기기 선택 : windows 컴퓨터
		 * 			(3) 생성 버튼 : 16자리 앱 비밀번호를 생성해 줌(이 비밀번호를 이메일 보낼 때
		 */

		// 사용자 정보를 javax.mail. Session에 저장
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		// 이메일 작성 및 전송
		try {
			
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(username, "인증코드관리자"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("[VolcaNo]인증 요청 메일입니다.");
			message.setContent("인증번호는 <strong>" + authCode + "</strong>입니다.", "text/html; charset=UTF-8");
			
			Transport.send(message);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("authCode", authCode);
		
		return result;
	}
	
	@Transactional
	@Override
	public void join(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String mobile = request.getParameter("mobile");
		String birthyear = request.getParameter("birthyear");
		String birthmonth = request.getParameter("birthmonth");
		String birthdate = request.getParameter("birthdate");
		String postcode = request.getParameter("postcode");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		String detailAddress = request.getParameter("detailAddress");
		String extraAddress = request.getParameter("extraAddress");
		String email = request.getParameter("email");
		String location = request.getParameter("location");
		String promotion = request.getParameter("promotion");
		
		pw = securityUtil.sha256(pw);
		name = securityUtil.preventXSS(name);
		String birthday = birthmonth + birthdate;
		
		int agreeCode = 0;
		if(!location.isEmpty() && promotion.isEmpty()) {
			agreeCode = 1;
		} else if(location.isEmpty() && !promotion.isEmpty()) {
			agreeCode = 2;
		} else if(!location.isEmpty() && !promotion.isEmpty()) {
			agreeCode = 3;
		}
		
		UserDTO user = UserDTO.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.gender(gender)
				.email(email)
				.mobile(mobile)
				.birthyear(birthyear)
				.birthday(birthday)
				.postcode(postcode)
				.roadAddress(roadAddress)
				.jibunAddress(jibunAddress)
				.detailAddress(detailAddress)
				.extraAddress(extraAddress)
				.agreeCode(agreeCode)
				.build();
		
		int result = userMapper.insertUser(user);
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(result > 0) {
				
				request.getSession().setAttribute("loginUser", userMapper.selectUserByMap(map));
				
				// 로그인 기록 남기기
				int updateResult = userMapper.updateAccessLog(id);
				if(updateResult == 0) {
					userMapper.insertAccessLog(id);
				};
				
				out.println("<script>");
				out.println("alert('회원 가입 되었습니다.');");
				out.println("location.href='"+ request.getContextPath() +"';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원 가입이 실패하였습니다.');");
				out.println("history.go(-2);");
				out.println("</script>");
			}
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@Override
	public void retire(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		RetireUserDTO retireUser = RetireUserDTO.builder()
				.userNo(loginUser.getUserNo())
				.id(loginUser.getId())
				.joinDate(loginUser.getJoinDate())
				.build();
		int deleteResult = userMapper.deleteUser(loginUser.getUserNo());
		
		int insertResult = userMapper.insertRetireUser(retireUser);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(deleteResult > 0 && insertResult > 0) {
				// session 초기화(로그인 사용자 loginUser 삭제를 위해서)
				session.invalidate();
				
				out.println("<script>");
				out.println("alert('회원 탈퇴 되었습니다.');");
				out.println("location.href='"+ request.getContextPath() +"';");
				out.println("</script>");
				out.close();
				
			}  else {
					
				out.println("<script>");
				out.println("alert('회원 탈퇴가 실패하였습니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void login(HttpServletRequest request, HttpServletResponse response) {
		
		String url = request.getParameter("url");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		pw = securityUtil.sha256(pw);

		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		UserDTO loginUser = userMapper.selectUserByMap(map);
		
		HttpSession session = request.getSession();
		
		try {
			if(loginUser != null) {
				int updateResult = userMapper.updateAccessLog(id);
				if(updateResult == 0) {
					userMapper.insertAccessLog(id);
				};
				request.getSession().setAttribute("loginUser", loginUser);
				response.sendRedirect(url);
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 아이디 또는 패스워드입니다.');");
				out.println("location.href='"+ request.getContextPath() +"/user/login/form';");
				out.println("</script>");
				out.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
