package com.gdu.app13.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.sql.Date;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app13.domain.RetireUserDTO;
import com.gdu.app13.domain.SleepUserDTO;
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
		System.out.println(securityUtil.sha256("1111"));
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

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", pw);
	
		UserDTO loginUser = userMapper.selectUserByMap(map);

		if(loginUser != null) {

			keepLogin(request, response);

			request.getSession().setAttribute("loginUser", loginUser);

			int updateResult = userMapper.updateAccessLog(id);
			if(updateResult == 0) {
				userMapper.insertAccessLog(id);
			}

			try {
				response.sendRedirect(url);
			} catch(IOException e) {
				e.printStackTrace();
			}
			
		}

		else {

			try {
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('일치하는 회원 정보가 없습니다.');");
				out.println("location.href='" + request.getContextPath() + "';");
				out.println("</script>");
				out.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	public void keepLogin(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 로그인 유지를 체크한 경우
		 * 1. session_id를 쿠키에 저장해 둔다.
		 * 		(쿠키 명 : keepLogin)
		 * 2. session_id를 DB에 저장해 둔다.
		 * 		(SESSION_ID 칼럼에 session_id를 저장하고 SESSION_LIMIT_DATE 칼럼에 15일 후 날짜를 저장한다)
		 */
		/*
		 * 로그인 유지를 체크하지 않은 경우
		 * 1. 쿠키 또는 DB에 저장된 정보를 삭제한다.
		 * 		편의상 쿠키명 keepLogin을 제거하는 것으로 처리한다
		 */
		String sessionId = request.getSession().getId();
		
		String id =request.getParameter("id");
		String keepLogin = request.getParameter("keepLogin");
		
		if(keepLogin != null) {
			Cookie cookie = new Cookie("keepLogin", request.getSession().getId());
			cookie.setMaxAge(60 * 60 * 24 * 15);	// 15일동안 살아있다.
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
			
			UserDTO user = UserDTO.builder()
					.id(id)
					.sessionId(sessionId)
					.sessionLimitDate(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 15 * 1000))
					.build();
			userMapper.updateSessionInfo(user);
		} else {
			Cookie cookie = new Cookie("keepLogin", "");
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}
	}
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		
		// 로그아웃 처리
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
			session.invalidate();
		}
		
		// 로그인 유지 풀기
		Cookie cookie = new Cookie("keepLogin", "");
		cookie.setMaxAge(0);  // 쿠키 유지 시간이 0이면 삭제를 의미함
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
		
	}
	
	@Override
	public UserDTO getUserBySessionId(Map<String, Object> map) {
		return userMapper.selectUserByMap(map);
	}
	
	@Override
	public Map<String, Object> confirmPassword(HttpServletRequest request) {
		
		String pw = securityUtil.sha256(request.getParameter("pw"));
		String id = ((UserDTO)request.getSession().getAttribute("loginUser")).getId();
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		
		UserDTO user = userMapper.selectUserByMap(map);
		
		Map<String, Object> result = new HashMap<>();
		result.put("isUser", user != null);
		
		return result;
	}
	
	@Override
	public void modifyPassword(HttpServletRequest request, HttpServletResponse response) {
		
		UserDTO loginUser = (UserDTO)request.getSession().getAttribute("loginUser");
		String pw = securityUtil.sha256(request.getParameter("pw"));
		
		if(pw.equals(loginUser.getPw())) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('현재 비밀번호와 동일한 비밀번호로 변경하실 수 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
					
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		int userNo = loginUser.getUserNo();
		UserDTO user = UserDTO.builder()
				.userNo(userNo)
				.pw(pw)
				.build();
		
		int result = userMapper.updateUserPassword(user);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(result > 0) {
				
				loginUser.setPw(pw);
				
				out.println("<script>");
				out.println("alert('비밀번호가 수정되었습니다.');");
				out.println("location.href='"+ request.getContextPath() +"';");
				out.println("</script>");
				out.close();
				
			}  else {
					
				out.println("<script>");
				out.println("alert('비밀번호 수정되지 않았습니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@Override
	public void sleepUserHandle() {
		int insertCount = userMapper.insertSleepUser();
		if(insertCount > 0) {
			userMapper.deleteUserForSleep();
		}
	}
	
	@Override
	public SleepUserDTO getSleepUserById(String id) {
		return userMapper.selectSleepUserById(id);
	}
	
	@Transactional
	@Override
	public void restoreUser(HttpServletRequest request, HttpServletResponse response) {
		String pw = securityUtil.sha256(request.getParameter("pw"));
		
		SleepUserDTO sleepUser = (SleepUserDTO)request.getSession().getAttribute("sleepUser");
		String id = sleepUser.getId();
		
		if(pw.equals(sleepUser.getPw())) {
			int insertCnt = userMapper.insertRestoreUser(id);
			int deleteCnt = 0;
			if(insertCnt > 0) {
				deleteCnt = userMapper.deleteSleepUser(id);
				int updateResult = userMapper.updateAccessLog(id);
				if(updateResult == 0) {
					userMapper.insertAccessLog(id);
				};
			}
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				if(insertCnt > 0 && deleteCnt >0) {
					out.println("<script>");
					out.println("alert('휴면 계정이 복구되었습니다. 휴면 계정 활성화를 위해 곧바로 로그인을 해 주세요.');");
					out.println("location.href='"+ request.getContextPath() +"/user/login/form';");
					out.println("</script>");
					out.close();
				} else {
					out.println("<script>");
					out.println("alert('휴면 계정이 복구되지 않았습니다. 다시 시도해주세요.');");
					out.println("history.back();");
					out.println("</script>");
					out.close();
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('비밀번호를 확인하세요.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			} catch(Exception e){
			}
		}
	}
	
	@Override
	public String getNaverLoginApiURL(HttpServletRequest request) {
	    
		String apiURL = null;
		
		try {
			
			String clientId = "jhYSq_krJJlvhO5rRMNF";
			String redirectURI = URLEncoder.encode("http://localhost:9090" + request.getContextPath() + "/user/naver/login", "UTF-8");  // 네이버 로그인 Callback URL에 작성한 주소 입력 
			SecureRandom random = new SecureRandom();
			String state = new BigInteger(130, random).toString();
			
			apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
			apiURL += "&client_id=" + clientId;
			apiURL += "&redirect_uri=" + redirectURI;
			apiURL += "&state=" + state;
			
			HttpSession session = request.getSession();
			session.setAttribute("state", state);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return apiURL;
		
	}
	
	@Override
	public String getNaverLoginToken(HttpServletRequest request) {
		
		String clientId = "jhYSq_krJJlvhO5rRMNF";
		String clientSecret = "RF2DyVSt_1";
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		
		String redirectURI = null;
		try {
			redirectURI = URLEncoder.encode("http://localhost:9090" + request.getContextPath(), "UTF-8");
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		StringBuffer res = new StringBuffer();
		try {
			
			String apiURL;
			apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
			apiURL += "client_id=" + clientId;
			apiURL += "&client_secret=" + clientSecret;
			apiURL += "&redirect_uri=" + redirectURI;
			apiURL += "&code=" + code;
			apiURL += "&state=" + state;
			
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			con.disconnect();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		JSONObject obj = new JSONObject(res.toString());
		String access_token = obj.getString("access_token");
		return access_token;
		
	}
	
	@Override
	public UserDTO getNaverLoginProfile(String access_token) {
		
		// access_token을 이용해서 profile 받기
		String header = "Bearer " + access_token;
		
		StringBuffer sb = new StringBuffer();
		
		try {
			
			String apiURL = "https://openapi.naver.com/v1/nid/me";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", header);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			br.close();
			con.disconnect();
			
			/*
				sb.toString()
				
				{
					"resultcode": "00",
					"message": "success",
					"response": {
						"id":"asdfghjklqwertyuiopzxcvbnmadfafrgbgfg",
						"gender":"M",
						"email":"hahaha@naver.com",
						"mobile":"010-1111-1111",
						"mobile_e164":"+821011111111",
						"name":"\ubbfc\uacbd\ud0dc",
						"birthday":"10-10",
						"birthyear":"1990"
					}
				}
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 받아온 profile을 UserDTO로 만들어서 반환
		UserDTO user = null;
		try {
			
			JSONObject profile = new JSONObject(sb.toString()).getJSONObject("response");
			String id = profile.getString("id");
			String name = profile.getString("name");
			String gender = profile.getString("gender");
			String email = profile.getString("email");
			String mobile = profile.getString("mobile").replaceAll("-", "");
			String birthyear = profile.getString("birthyear");
			String birthday = profile.getString("birthday").replace("-", "");
			
			user = UserDTO.builder()
					.id(id)
					.name(name)
					.gender(gender)
					.email(email)
					.mobile(mobile)
					.birthyear(birthyear)
					.birthday(birthday)
					.build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return user;
		
	}
	
}
