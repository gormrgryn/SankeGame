package utils;

public class DoublyLinkedList<T> {
	public Node head;
	public Node tail;
	public int length = 0;
	
	public class Node {
		public T value;
		public Node next;
		public Node prev;
		
		public Node(T value) {
			this.value = value;
		}
	}
	
	public void AddNode(T value) {
		Node currentNode = head;
		Node newNode = new Node(value);
		
		if (head == null) {
			head = newNode;
			return;
		}
		
		while(currentNode.next != null) {
			currentNode = currentNode.next;
		}
		
		currentNode.next = newNode;
		newNode.prev = currentNode;
		tail = newNode;
		length++;
	}
}
