package com.gdu.app13.service;

import java.io.Reader;
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

import org.apache.ibatis.io.Resources;
import org.springframework.stereotype.Service;

import com.gdu.app13.mapper.UserMapper;
import com.gdu.app13.util.SecurityUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;
	private SecurityUtil securityUtil;
	
	@Override
	public Map<String, Object> isReduce(String id) {
		Map<String, Object> result = new HashMap<>();
		result.put("isUser", userMapper.selectUserById(id) != null);
		result.put("isRetireUser", userMapper.selectRetireUserById(id) != null);
		return result;
	}
	
	@Override
	public Map<String, Object> isReduceEmail(String email) {
		Map<String, Object> result = new HashMap<>();
		result.put("isUser", userMapper.selectUserByEmail(email) != null);
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
		
		String resource = "resources/email/email.properties";
		Properties emailProp = new Properties();
		
		try {
			 Reader reader = Resources.getResourceAsReader(resource);
			 emailProp.load(reader);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 이메일을 보내는 사용자 정보
		String username = "doriyet@gmail.com";	// 본인 지메일
		String password = "hsveznwecxhjfmie";	// 발급 받은 앱 비밀번호
		
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
}
