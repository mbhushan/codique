package hr_DetectCycle;

/*
A linked list is said to contain a cycle if any node is visited more than once while traversing the list.

Complete the function provided in the editor below. It has one parameter: a pointer to a Node object named 
head that points to the head of a linked list. Your function must return a boolean denoting whether or not there 
is a cycle in the list. If there is a cycle, return true; otherwise, return false.
*/

public class DetectCycle {
	public static void main(String[] args) {
		
	}
	
	public boolean hasCycle(Node head) {
		if (head == null) {
			return false;
		}
		Node first = head;
		Node second = head.next;
		
		boolean cycle = true;
		
		while(true) {
			if (second == null) {
				cycle = false;
				break;
			}
			if (second.next != null && second.next.next != null) {
				second = second.next.next;
			} else {
				cycle = false;
				break;
			}
			if (second.equals(first)) {
				cycle = true;
				break;
			}
			first = first.next;
		}
		
		return cycle;
				
	}
}

class Node {
	int value;
	Node next;
	
	Node(int value) {
		this.value = value;
		this.next = null;
	}
}
