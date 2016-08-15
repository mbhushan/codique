package com.manib.server;

import java.util.Arrays;

/*
There are at most eight servers in a data center. Each server has got a capacity/memory limit. 
There can be at most 8 tasks that need to be scheduled on those servers. Each task requires certain 
capacity/memory to run, and each server can handle multiple tasks as long as the capacity limit is 
not hit. Write a program to see if all of the given tasks can be scheduled or not on the servers? 

Example 1: 
Servers capacity limits: 8, 16, 8, 32   
Tasks capacity needs: 18, 4, 8, 4, 6, 6, 8, 8   
For this example, the program should say 'true'. 
*/

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
