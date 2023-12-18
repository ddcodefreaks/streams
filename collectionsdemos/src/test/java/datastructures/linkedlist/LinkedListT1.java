package datastructures.linkedlist;

public class LinkedListT1 {
	
	private NodeT1 head;
	private NodeT1 tail;
	private int length;
	
	
	
	

	public LinkedListT1(int value) {
		NodeT1 newNode = new NodeT1(value);
		head = newNode;
		tail = newNode;
		length = 1;
	}





	public static void main(String[] args) {
		
		LinkedListT1 myLinkedList = new LinkedListT1(4);
		

	}

}
