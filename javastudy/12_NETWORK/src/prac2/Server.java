package prac2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Server extends Thread {

	private Socket client;
	private BufferedReader in = null;
	private BufferedWriter out = null;

	public Server(Socket client) {

		try {
			this.client = client;
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage (String message) throws IOException {
		out.write(message);
		out.flush();
	}

	@Override
	public void run() {
		
		try {
			InetSocketAddress address = null;
			String message = null;
			while(true) {
				message = in.readLine();
				if(message.equalsIgnoreCase("exit")) {
					break;
				}
				// 모든 클라이언트에게 메시지 출력
				address = (InetSocketAddress)client.getRemoteSocketAddress();
				ServerMain.sendMessage(address.getHostName() + "의 메시지 : " + message);
			}
			
			// List<Server> servers에서 등록된 서버 제거
			ServerMain.servers.remove(this);
			System.out.println(address.getHostName() + " 채팅 종료");
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
