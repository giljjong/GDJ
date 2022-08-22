package api;

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

import org.json.JSONArray;
import org.json.JSONObject;


public class ApiMain {

	public static void main(String[] args) {
		
		StringBuilder urlBuilder = new StringBuilder();
		
		try {
			
			String serviceKey = "dHpK1saSupC5iBT1N94HBODDS8fiDXg80LdnGGJFOywnKTxQnfRdotV8h4ViKikMaqUriTHFg2YKK71LX+BcWA==";
			urlBuilder.append("http://apis.data.go.kr/B552061/AccidentDeath/getRestTrafficAccidentDeath");
			urlBuilder.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			urlBuilder.append("&searchYear=2021");			
			urlBuilder.append("&siDo=1100");			
			urlBuilder.append("&guGun=1125");			
			urlBuilder.append("&type=json");		
			urlBuilder.append("&numOfRows=10");		
			urlBuilder.append("&pageNo=1");				
			urlBuilder.append("&acc_year=2021");			
			
		} catch (UnsupportedEncodingException e) {
			System.out.println("인코딩 실패");
		}
		
		String apiURL = urlBuilder.toString();
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
		
		StringBuilder txtWriter = new StringBuilder();
		String[] week = {" ", "일", "월", "화", "수", "목", "금", "토"};
		File file = new File("accident.txt");
		BufferedWriter bw = null;
		
		try {
		
			List<Accident> accidents = new ArrayList<Accident>();
			JSONObject obj = new JSONObject(response);
			JSONArray itemList = obj.getJSONObject("items")
									.getJSONArray("item");
			
			for(int i = 0; i < itemList.length(); i++) {
				JSONObject item = itemList.getJSONObject(i);
				
				txtWriter.append("발생일자 " + item.getString("occrrnc_dt") + " ");
				txtWriter.append(week[item.getInt("occrrnc_day_cd")] + "요일, ");
				txtWriter.append("사망자 수 " + item.getInt("dth_dnv_cnt") + "명, ");
				txtWriter.append("부상자 수 " + item.getInt("injpsn_cnt") + "명 \n");
				
				Accident accident = new Accident(item.getString("occrrnc_dt"), item.getString("occrrnc_day_cd"), item.getInt("dth_dnv_cnt"), item.getInt("injpsn_cnt"));
				accidents.add(accident);
				
			}

			bw = new BufferedWriter(new FileWriter(file));
			bw.write(txtWriter.toString());
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
