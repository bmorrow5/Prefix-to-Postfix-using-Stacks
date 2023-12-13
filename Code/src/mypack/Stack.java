package mypack;

/**
 * A class that creates a LIFO stack. This stack contains an inner class 'Node'
 * which are the object elements in the stack
 * 
 * @author Brandon L Morrow
 * @Date 09/27/2022
 */

//Creates a generic stack class of generic type T
public class Stack<T> {

	/**
	 * An inner class "Node" which is the node objects contained within a stack
	 * 
	 * @author Brandon Morrow
	 * @param Generic <T>
	 */
	private class Node<T> {
		private T item; // Generic item that will go into node
		private Node<T> next; // Create generic node that will be the next node

		Node(T item) {
			this.item = item;
		}

		// Returns item stored in the node
		public T getItem() {
			return item;
		}

		// Sets item inside a node
		public void setItem(T item) {
			this.item = item;
		}

		// Gets/Returns next node object
		public Node<T> getNext() {
			return next;
		}

		// Sets next node object
		public void setNext(Node<T> next) {
			this.next = next;
		}
	}// End private class Node()

	// Resume the stack class
	private Node<T> top; // Generic node that is on top
	private int size = 0; // Size of the stack

	// Adds item to a node in a stack
	public void push(T item) {
		size++;
		Node<T> current = new Node<T>(item); // New node current holds new item being inserted
		current.setNext(top); // Next node becomes the top node
		top = current; // Top node is the just inserted current node
	}// End push()

	// Removes top node in the stack
	public T pop() {
		if (isEmpty() == true) {
			return (T) "Cannot pop empty stack!";
		} else {
			size--; // Reduce size of the stack
			T removedItem = top.getItem();
			top = top.getNext(); // Top node clears and is now equal to the next node
			return removedItem; // Returns the removed item from the stack
		}
	}// End pop()

	// Checks if a stack is empty
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else
			return false;
	}

	// Returns stack size
	public int getSize() {
		return size;
	}

	// Returns but does not remove top element in the stack
	public T peek() {
		if (isEmpty() == true) {
			return null;
		} else
			return top.getItem();
	}
}// End class Stack()
