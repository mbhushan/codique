package main.java.com.db.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main.java.com.db.DBController;

public class InmemoryDBClient {

    private static final String END = "END";
    private DBController controller;

    public InmemoryDBClient() {
    	controller = new DBController();
    }

    public static void main(String[] args) {
        InmemoryDBClient client = new InmemoryDBClient();
        client.runDatabase();
    }

    public void runDatabase() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            try {
                String cmd = br.readLine().trim();
                if (cmd.equalsIgnoreCase(END)) {
                    System.out.println("BYE :)");
                    break;
                }
                controller.execute(cmd);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
