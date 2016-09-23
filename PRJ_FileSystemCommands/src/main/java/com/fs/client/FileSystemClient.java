package main.java.com.fs.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main.java.com.fs.FileSystemController;

public class FileSystemClient {

	private FileSystemController fController;
	private static final String END = "END";

	public FileSystemClient() {
		this.fController = new FileSystemController();
	}

	public static void main(String[] args) {
		FileSystemClient fsClient = new FileSystemClient();
		
		fsClient.runFileSystem();
	}

	public void runFileSystem() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			try {
				String cmd = br.readLine().trim();

				if (cmd.equalsIgnoreCase(END)) {
					System.out.println("BYE :)");
					break;
				}
				//execute command on filesytem.
				fController.execute(cmd);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
