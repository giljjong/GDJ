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
			String apiURl = "https://docs.oracle.com/en/java/javase/11/docs/api/index.html";
			URL url = new URL(apiURl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			File file = new File("C:\\storage", "xmlprc.html");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			String line = null;
			while((line = br.readLine()) != null) {
				bw.write(line);
			}
			
			br.close();
			bw.close();
			con.disconnect();
			
		} catch (MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch (IOException e) {
			System.out.println("API 서버 오류");
		}
		 

	}

}
