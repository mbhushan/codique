package com.manib.server;

import java.util.Arrays;

public class ServerCapacity {

	public static void main(String[] args) {
		ServerCapacity sc = new ServerCapacity();
		
		int [] servers = {8, 16, 8, 32};
		int [] tasks = {18, 4, 8, 4, 6, 6, 8, 8};
		
		System.out.println("assignment possible: " + sc.checkAssignment(servers, tasks));
		System.out.println("servers: " + Arrays.toString(servers));
	}
	
	public boolean checkAssignment(int [] servers, int [] tasks) {
		return checkAssignment(0, servers, tasks);
	}
	
	private boolean checkAssignment(int index, int [] servers, int [] tasks) {
		if (index >= tasks.length) {
			return true;
		}
		
		for (int k=0; k<servers.length; k++) {
			if (servers[k] >= tasks[index]) {
				servers[k] -= tasks[index];
				if (checkAssignment(index+1, servers, tasks)) {
					return true;
				}
				servers[k] += tasks[index];
			}
		}
		return false;
	}
}
