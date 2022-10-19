package ex07_naver_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PapagoServlet")
public class PapagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 클라이언트 아이디, 시크릿
		String clientId = "JWjsCvqsBmDBeZ67F3_a";
		String clientSecret = "b1CC7q5NHa";
		
		request.setCharacterEncoding("UTF-8");
		String source = request.getParameter("source");
		String target = request.getParameter("target");
		String text = request.getParameter("text");
		
		try {
			URLEncoder.encode(text, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("번역할 텍스트 인코딩 실패");
			out.close();
		}
		
		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			
		} catch(MalformedURLException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API URL이 잘못 되었습니다.");
			out.close();
		} catch(IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API URL 연결 실패");
			out.close();
		}
		
		try {
			// 요청헤더
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			// 요청 메소드
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			// 요청 파라미터 보내기
			String params = "source=" + source + "&target=" + target + "&text=" + text;
			OutputStream outputstream = con.getOutputStream();
			outputstream.write(params.getBytes());
			outputstream.close();
		} catch(IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 요청이 실패했습니다.");
			out.close();
		}
		
		BufferedReader reader = null;
		
		try {
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
		} catch(IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 응답 스트림 생성이 실패했습니다.");
			out.close();
		}
		
		StringBuilder sb = new StringBuilder();
		String line = null;
		
		try {
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch(IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 응답을 읽는 데 실패했습니다.");
			out.close();
		}
		
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(sb);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
