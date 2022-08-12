package prac1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class pracMain {

	public static void main(String[] args) {
		
		try {
			String apiURL = "https://docs.oracle.com/en/java/javase/11/docs/api/index.html";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			if(con.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("API 접속 실패");
				return;
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
			File file = new File("C:\\storage", "xmlprc.xml");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			String line = null;
			while((line = br.readLine()) != null) {
				bw.write(line + "\n");
			}
			
		} catch (MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch (IOException e) {
			System.out.println("API 서버 오류");
		}
		 

	}

}
