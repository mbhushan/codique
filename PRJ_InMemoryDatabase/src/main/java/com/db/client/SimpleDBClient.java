package main.java.com.db.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main.java.com.db.DBManager;

public class SimpleDBClient {
	
	private static final String END = "END";
	private DBManager mgr;
	
	public SimpleDBClient() {
		mgr = new DBManager();
	}

	public static void main(String[] args) {
		SimpleDBClient client = new SimpleDBClient();
		client.run();
	}
	
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			try {
				String cmd = br.readLine().trim();
				if (cmd.equalsIgnoreCase(END)) {
					System.out.println("BYE :)");
					break;
				}
				mgr.execute(cmd);
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
}
