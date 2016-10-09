package hr_LinkedListDeleteEvenNode;

public class LinkedListDelete {

	private Node head;
	
	public LinkedListDelete() {
		this.head = null;
	}
	
	public static void main(String[] args) {
		LinkedListDelete ll = new LinkedListDelete();
		
		int [] A = {1,2,3,4,6};
		
		for (int val: A) {
			ll.insert(val);
		}
		
		ll.printList();
		
		ll.deleteEven();
	}
	
	public void deleteEven() {
		Node node = deleteEven(this.head);
		
		for (Node curr=node; curr != null; curr = curr.next) {
			System.out.print(curr.val +" -> ");
		}
		System.out.println("null");
		
	}
	
	public Node deleteEven(Node node) {
		if (node == null) {
			return node;
		}
		
		while ((node != null) && ((node.val % 2) == 0)) {
			node = node.next;
		}
		if (node == null || node.next == null) {
			return node;
		}
		Node curr = node;
		while (curr.next != null) {
			if (curr.next.val % 2 == 0) {
				curr.next = curr.next.next;
			} else {
				curr = curr.next;
			}
		}
		return node;
	}
	
	public void printList() {
		for (Node node=this.head; node != null; node = node.next) {
			System.out.print(node.val +" -> ");
		}
		System.out.println("null");
	}
	
	public void insert(int val) {
		if (this.head == null) {
			this.head = new Node(val);
		} else {
			Node node = new Node(val);
			node.next = this.head;
			this.head = node;
		}
	}
}

class Node {
	int val;
	Node next;
	
	Node(int val) {
		this.val = val;
		this.next = null;
	}
}
