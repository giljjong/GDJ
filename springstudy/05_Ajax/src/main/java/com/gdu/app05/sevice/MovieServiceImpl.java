package com.gdu.app05.sevice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class MovieServiceImpl implements MovieService {

	@Override
	public String getBoxoffice(String targetDt) {
		
		String key = "eb1b642402d8510fc538c189d7573fdd";
		String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=" + key + "&targetDt=" + targetDt;
		
		String responseData = null;
		
		StringBuilder sb = new StringBuilder();
		
		try {

			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			
			BufferedReader reader = null;
			
			if(con.getResponseCode() == 200) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			reader.close();
			con.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		responseData = sb.toString();
		System.out.println(sb.toString());
		return responseData;
		
	}

}
