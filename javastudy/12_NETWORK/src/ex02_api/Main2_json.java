package ex02_api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main2_json {
	
	public static void m1() {
		
String apiURL = "http://api.data.go.kr/openapi/tn_pubr_public_weighted_envlp_api";
		
		try {
			String serviceKey = "dHpK1saSupC5iBT1N94HBODDS8fiDXg80LdnGGJFOywnKTxQnfRdotV8h4ViKikMaqUriTHFg2YKK71LX+BcWA==";
			apiURL += "?pageNo=" + URLEncoder.encode("0", "UTF-8");
			apiURL += "&numOfRows=" + URLEncoder.encode("100", "UTF-8");
			apiURL += "&type=" + URLEncoder.encode("json", "UTF-8");
			apiURL += "&CTPRVN_NM=" + URLEncoder.encode("인천광역시", "UTF-8");
			apiURL += "&SIGNGU_NM=" + URLEncoder.encode("계양구", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_TYPE=" + URLEncoder.encode("규격봉투", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_MTHD=" + URLEncoder.encode("소각용", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_PRPOS=" + URLEncoder.encode("생활쓰레기", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_TRGET=" + URLEncoder.encode("기타", "UTF-8");
			apiURL += "&serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");

		} catch(UnsupportedEncodingException e) {
			System.out.println("인코딩 실패");
		}
		
		// API 주소 접속
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		} catch (MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch (IOException e) {
			System.out.println("API 주소 접속 실패");
		}
		
		// 입력 스트림(응답) 생성
		// 1. 응답 성공 시 정상 스트림, 실패 시 에러 스트림
		// 2. 응답 데이터는 StringBuilder에 저장
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			// 스트림 종료
			reader.close();
			
		} catch (IOException e) {
			System.out.println("API 응답 실패");
		}
		
		// API로부터 전달받은 json 데이터
		String response = sb.toString();
		File file = new File("C:\\storage", "api1.json");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
			
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();		// <response> (최상위 태그)
			NodeList nodeList = root.getChildNodes();		// <response>의 자식 태그 <header>, <body>
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);				// <header>와 <body>
				NodeList nodeList2 = node.getChildNodes();	// <header>와 <body>의 자식 태그
				for(int j = 0; j < nodeList2.getLength(); j++ ) {
					Node node2 = nodeList2.item(j);
					System.out.println(node2.getNodeName());
					if(node2.getNodeName().equals("items")) {		// <items> 태그 대상
						NodeList items = node2.getChildNodes();
						for(int k = 0; k < items.getLength(); k++) {
							Node item = items.item(k);
							System.out.println(item.getNodeName());
						}
					}
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 접속 종료
		con.disconnect();
	
		
		
		
	}
	
	public static void m2() {
		
		// JSONOBject 클래스 : { }, 객체를 의미
		// JSONArray 클래스	 : [ ], 배열을 의미
		
		File file = new File("C:\\storage", "api2.json");
		StringBuilder sb = new StringBuilder();
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JSONObject obj = new JSONObject(sb.toString());
		JSONObject obj2 = obj.getJSONObject("response");
		JSONObject obj3 = obj2.getJSONObject("header");
		String resultCode = obj3.getString("resultCode");
		String resultMsg = obj3.getString("resultMsg");
		
		System.out.println(resultCode + ", " + resultMsg);
	}
	
	public static String m3() {
			
			StringBuilder urlBuild = new StringBuilder();
			
			try {
				String serviceKey = "dHpK1saSupC5iBT1N94HBODDS8fiDXg80LdnGGJFOywnKTxQnfRdotV8h4ViKikMaqUriTHFg2YKK71LX+BcWA==";
				urlBuild.append("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");
				urlBuild.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
				urlBuild.append("&base_date=20220819");
				urlBuild.append("&base_time=0600");
				urlBuild.append("&dataType=JSON");
				urlBuild.append("&nx=58");
				urlBuild.append("&ny=125");
				
			} catch(UnsupportedEncodingException e) {
				System.out.println("인코딩 실패");
			}
			
			String apiURL = urlBuild.toString();
			URL url = null;
			HttpURLConnection con = null;
			
			try {
				url = new URL(apiURL);
				con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-Type", "application/json;");

			} catch(MalformedURLException e) {
				System.out.println("API 주소 오류");
			} catch(IOException e) {
				System.out.println("API 주소 접속 실패");
			}
			
			BufferedReader reader = null;
			StringBuilder sb = new StringBuilder();
			
			try {
				if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
					reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else {
					reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				
				String line = null;
				while((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				reader.close();
				
			} catch(IOException e) {
				System.out.println("API 응답 실패");
			}
			
			String response = sb.toString();
			File file = new File("C:\\storage", "api2.json");
			BufferedWriter bw = null;
			
			try {
				bw = new BufferedWriter(new FileWriter(file));
				bw.write(response);
				bw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return response;
	}
		
	public static void m4() {
		
		JSONObject obj = new JSONObject(m3());
		JSONObject response = obj.getJSONObject("response");
		JSONObject body = response.getJSONObject("body");
		JSONObject items = body.getJSONObject("items");
		JSONArray item = items.getJSONArray("item");
		
		for(int i = 0, length = item.length(); i < length; i++) {
			JSONObject element = item.getJSONObject(i);
			System.out.println(element.getString("category") + " : " + element.getString("obsrValue"));
		}

	}
	
	public static String m5() {
		
		
		StringBuilder urlBuild = new StringBuilder();
		
		try {
			String serviceKey = "dHpK1saSupC5iBT1N94HBODDS8fiDXg80LdnGGJFOywnKTxQnfRdotV8h4ViKikMaqUriTHFg2YKK71LX+BcWA==";
			urlBuild.append("http://apis.data.go.kr/B553077/api/open/sdsc2/storeZoneOne");
			urlBuild.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			urlBuild.append("&key=9940");
			urlBuild.append("&type=json");
			
		} catch(UnsupportedEncodingException e) {
			System.out.println("인코딩 실패");
		}
		
		String apiURL = urlBuild.toString();
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json;");

		} catch(MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch(IOException e) {
			System.out.println("API 주소 접속 실패");
		}
		
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			reader.close();
			
		} catch(IOException e) {
			System.out.println("API 응답 실패");
		}
		
		String response = sb.toString();
		File file = new File("C:\\storage", "api3.json");
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return response;
	}
	
	public static void m6() {
		
		JSONObject obj = new JSONObject(m5());
		JSONObject header = obj.getJSONObject("header");
		JSONArray columns = header.getJSONArray("columns");

		
		JSONObject body = obj.getJSONObject("body");
		JSONArray items = body.getJSONArray("items");
		JSONObject item = items.getJSONObject(0);
		
		String[] p = {"trarNo", "mainTrarNm", "ctprvnCd",  "ctprvnNm", "signguCd", "signguNm", "trarArea", "coordNum", "coords", "stdrDt"};
		Map<String, Object> map = new HashMap<String, Object>();
		
		for(int i = 0; i < p.length; i++) {
			map.put(columns.getString(i), item.get(p[i]));
		}
		
		for(String key : map.keySet()) {
			System.out.println(key + " : " + map.get(key));
		}
	}
	
public static String m7() {
		
		String apiURL = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=5013061000";
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml;");
			
		} catch(MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch(IOException e) {
			System.out.println("API 주소 접속 실패");
		}
		
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
		} catch(IOException e) {
			System.out.println("API 응답 실패");
		}
		
		BufferedWriter bw = null;
		File file = new File("C:\\storage", "api4.xml");
		String response = sb.toString();
		
		try {
			bw = new BufferedWriter(new FileWriter(file));
			
			bw.write(response);
			bw.close();
		} catch (IOException e) {
			System.out.println("파일 생성 실패");
		}
		return sb.toString();
	}
	
	public static void m8() {
		
		// XML문서를 JSONObject로 변환
		JSONObject obj = XML.toJSONObject(m7());

		JSONArray dataList = obj.getJSONObject("rss")
								.getJSONObject("channel")
								.getJSONObject("item")
								.getJSONObject("description")
								.getJSONObject("body")
								.getJSONArray("data");
		for(int i = 0; i < dataList.length(); i++) {
			JSONObject weather = dataList.getJSONObject(i);
			System.out.println(weather.getInt("hour") + "시 : " + weather.getInt("temp") + "도, " + weather.getString("wfKor"));
		}
	}

	public static void main(String[] args) {

		m8();
	}

}
