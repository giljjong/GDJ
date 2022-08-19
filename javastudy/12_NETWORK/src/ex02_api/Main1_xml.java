package ex02_api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main1_xml {

	// 요청
	// 1. Request
	// 2. 클라이언트 -> 서버
	
	// 파라미터
	// 1. Parameter
	// 2. 보내는 데이터(변수 개념)
	
	// 응답
	// 1. Response
	// 2. 서버 -> 클라이언트
	
	public static void m1() {
		
		// 전국 종량제봉투가격표준데이터
		
		// API 주소 (주소 + 요청 파라미터)
		
		String apiURL = "http://api.data.go.kr/openapi/tn_pubr_public_weighted_envlp_api";
		
		try {
			String serviceKey = "dHpK1saSupC5iBT1N94HBODDS8fiDXg80LdnGGJFOywnKTxQnfRdotV8h4ViKikMaqUriTHFg2YKK71LX+BcWA==";
			apiURL += "?pageNo=" + URLEncoder.encode("0", "UTF-8");
			apiURL += "&numOfRows=" + URLEncoder.encode("100", "UTF-8");
			apiURL += "&type=" + URLEncoder.encode("xml", "UTF-8");
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
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
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
				sb.append(line + "\n");
			}
			// 스트림 종료
			reader.close();
			
		} catch (IOException e) {
			System.out.println("API 응답 실패");
		}
		
		// API로부터 전달받은 xml 데이터
		String response = sb.toString();
		File file = new File("C:\\storage", "api1.xml");
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
		StringBuilder sb = new StringBuilder();
		try {
			String serviceKey = "dHpK1saSupC5iBT1N94HBODDS8fiDXg80LdnGGJFOywnKTxQnfRdotV8h4ViKikMaqUriTHFg2YKK71LX+BcWA==";
			sb.append("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson");
			sb.append("?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8"));
			sb.append("&startCreateDt=" + URLEncoder.encode("20220808", "UTF-8"));
			sb.append("&endCreateDt=" + URLEncoder.encode("20220812", "UTF-8"));
			sb.append("&resultCode=" + URLEncoder.encode("00", "UTF-8"));
			sb.append("&resultMsg=" + URLEncoder.encode("NORMAL SERVICE", "UTF-8"));
			sb.append("&numOfRows=" + URLEncoder.encode("10", "UTF-8"));
			sb.append("&pageNo=" + URLEncoder.encode("1", "UTF-8"));
			sb.append("&totalCount=" + URLEncoder.encode("6", "UTF-8"));
			
		} catch(UnsupportedEncodingException e) {
			System.out.println("인코딩 실패");
		}
		
		String apiURL =  sb.toString();
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml;");
			
		} catch(MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch(IOException e) {
			System.out.println("API 주소 접속 실패");
		}
		
		BufferedReader reader = null;
		StringBuilder build = new StringBuilder();
		
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String line = null;
			while((line = reader.readLine()) != null) {
				build.append(line + "\n");
			}
			reader.close();
		} catch(IOException e) {
			System.out.println("API 응답 실패");
		}
		
		String response = build.toString();
		File file = new File("C:\\storage", "api2.xml");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void m3() {
		
		File file = new File("C:\\storage", "api2.xml");
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();
			
			StringBuilder itembuild = new StringBuilder();
			
			NodeList items = root.getElementsByTagName("item"); 	// 태그 이름으로 찾기
			
			for(int i = 0; i < items.getLength(); i++) {
				Node item = items.item(i);
				NodeList itemChildren = item.getChildNodes();
				for(int j = 0; j < itemChildren.getLength(); j++) {
					Node itemChild = itemChildren.item(j);
					if(itemChild.getNodeName().equals("stateDt")) {
						itembuild.append(" 날짜 :").append(itemChild.getTextContent());
					} else if(itemChild.getNodeName().equals("decideCnt")) {
						itembuild.append(" 확진자 수 : ").append(itemChild.getTextContent());
					} else if(itemChild.getNodeName().equals("deathCnt")) {
						itembuild.append(" 사망자 수 : ").append(itemChild.getTextContent());
					}
				}
				// Node stateDt				 == <stateDt>20220812</stateDt>
				// stateDt.getNodeName()	 == stateDt (태그이름)
				// stateDt.getTextContent()	 == 20220812 (태그내부텍스트)
				itembuild.append("\n");
			}
			System.out.println(itembuild.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void m4() {
		StringBuilder urlBuild = new StringBuilder();
		
		try {
			String serviceKey = "+UhPrA5BQW+3S+oERDypQZUBguzTrc0mDZPyIBQAfX2J4TBwkfTP1vPmMjwNLW8bbD2/DcsaiMGMgzhfFAZ4Qg==";
			urlBuild.append("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");
			urlBuild.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			urlBuild.append("&base_date=20220818");
			urlBuild.append("&base_time=1100");
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
				sb.append(line + "\n");
			}
			reader.close();
			
		} catch(IOException e) {
			System.out.println("API 응답 실패");
		}
		
		String response = sb.toString();
		File file = new File("C:\\storage", "api3.xml");
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void m5() {
		File file = new File("C:\\storage", "api3.xml");
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();
			NodeList items = root.getElementsByTagName("item");
			
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < items.getLength(); i++) {
				Element item = (Element)items.item(i);
				Node category = item.getElementsByTagName("category").item(0);
				Node obsrValue = item.getElementsByTagName("obsrValue").item(0);
				String strCategory = null;
				
				switch(category.getTextContent()) {
				case "PTY" : strCategory = "강수형태"; break;
				case "REH" : strCategory = "습도"; break;
				case "RN1" : strCategory = "강수량(1시간)"; break;
				case "T1H" : strCategory = "기온"; break;
				case "UUU" : strCategory = "동서바람성분"; break;
				case "VEC" : strCategory = "풍향"; break;
				case "VVV" : strCategory = "남북바람성분"; break;
				case "WSD" : strCategory = "풍속"; break;
				}
				System.out.println(strCategory + " : " + obsrValue.getTextContent());
				// sb.append(category.getTextContent() + " : " + obsrValue.getTextContent() + "\n");
			}
			// System.out.println(sb.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void m6() {
		
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
				sb.append(line + "\n");
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
	}
	
	public static void m7() {
		
		File file = new File("C:\\storage", "api4.xml");
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();
			
			StringBuilder sb = new StringBuilder();
			Node title = root.getElementsByTagName("title").item(0);
			Node pubDate = root.getElementsByTagName("pubDate").item(0);
			sb.append(title.getTextContent() + "\n");
			sb.append(pubDate.getTextContent() + "\n");
			
			NodeList datas = root.getElementsByTagName("data");
			for(int i = 0; i < datas.getLength(); i++) {
				Element data = (Element) datas.item(i);
				NodeList dataChildren = data.getChildNodes();
				for(int j = 0; j < dataChildren.getLength(); j++) {
					Node dataChild = dataChildren.item(j);
					switch(dataChild.getNodeName()) {
					case "hour" : sb.append(dataChild.getTextContent() + "시 "); break;
					case "temp" : sb.append(dataChild.getTextContent() + "도 "); break;
					case "wfKor" :  sb.append(dataChild.getTextContent()); break;
					}
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void m8() {
		File file = new File("C:\\storage", "sfc_web_map.xml");
		
		try {
			
			StringBuilder sb = new StringBuilder();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();
			Element weather = (Element)root.getElementsByTagName("weather").item(0);
			
			sb.append(weather.getAttribute("year") + "년 ");
			sb.append(weather.getAttribute("month") + "월 ");
			sb.append(weather.getAttribute("day") + "일 ");
			sb.append(weather.getAttribute("hour") + "시\n");
			
			NodeList locals = root.getElementsByTagName("local");
			
			for(int i = 0; i < locals.getLength(); i++) {
				Element local = (Element) locals.item(i);
				sb.append(local.getTextContent() + " : ");
				sb.append(local.getAttribute("ta") + "℃ ");
				sb.append(local.getAttribute("desc") + "\n");
			}
			
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		m7();
	}

}
