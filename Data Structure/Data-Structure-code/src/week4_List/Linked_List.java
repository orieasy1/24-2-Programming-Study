
public class Linked_List<T> implements List<T>{
	class Node<T> {
		private T data;
		private Node<T> next;
		Node(T dataPortion) {  // the constructor��s name is Node, not Node<T>
			this(dataPortion, null);
		} // end constructor
		Node(T dataPortion, Node<T> nextNode){
			data = dataPortion;
			next = nextNode;
		} // end constructor
		T getData(){
			return data;
		} // end getData
		Node<T> getNextNode(){
			return next;
		} // end getNextNode
		void setData(T newData){
			data = newData;
		} // end setData
		void setNextNode(Node<T> nextNode){
			next = nextNode;
		} // end setNextNode
	}
	private Node<T> list; // Entry in list
	private int numberOfEntries;
	public Linked_List() {
		clear();
	}
	public boolean add( T newEntry) { // OutOfMemoryError possible
		return add(numberOfEntries, newEntry);
	}
	public boolean add(int givenPosition, T newEntry) { // OutOfMemoryError possible
		if(givenPosition > numberOfEntries) return false;
		numberOfEntries++;
		if(givenPosition == 0)
			list = new Node<T>(newEntry, list);
		else {
			Node<T> p= list;
			for(int i = 0 ; i < givenPosition-1 ; i++) p=p.getNextNode();
			p.setNextNode(new Node<T>(newEntry, p.getNextNode())); 
		}
		return true;
	}
	public boolean remove(T anEntry) {
		if(list == null) return false;
		if(list.getData().equals(anEntry)) {
			list = list.getNextNode();
			numberOfEntries--;
			return true;
		}
		for(Node<T> p= list;p.getNextNode()!=null;p=p.getNextNode())
			if(p.getNextNode().getData().equals(anEntry)) {
				p.setNextNode(p.getNextNode().getNextNode());
				numberOfEntries--;
				return true;
			}
		return false;
	}
	public T remove(int givenPosition) {
		if(list == null ||givenPosition<0) return null;
		if(givenPosition == 0) {
			T temp = list.getData();
			list.setNextNode(list.getNextNode().getNextNode());
			numberOfEntries--;
			return temp;
		}
		int i = 0;
		for(Node<T> p = list; p.getNextNode() != null  && i < givenPosition;p=p.getNextNode(),i++) {
			if (i+1==givenPosition) {
				T temp = p.getNextNode().getData();
				p.setNextNode(p.getNextNode().getNextNode());//delete it
				numberOfEntries--;
				return temp;
			}
		}
		return null;
	}
	public T get(int givenPosition) {
		if(list==null || givenPosition < 0 || givenPosition > numberOfEntries) return null;
		Node<T> p= list;
		for(int i = 0 ; i < givenPosition ; i++, p=p.getNextNode());
		return p.getData();
	}
	public int indexOf(T anEntry) {
		int i = -1;
		for(Node<T> p= list;i<numberOfEntries;i++, p=p.getNextNode())
			if(p.getData().equals(anEntry)) return ++i;
		return i;
	}
	public void clear() {
		list=null;
		numberOfEntries=0;
	}
	public int size() {
		return numberOfEntries;
	}
	public String toString() {
		if(numberOfEntries == 0) return "[]";
		String s="[";
		Node<T> p= list;
		for(; p.getNextNode() != null ;p=p.getNextNode())
			s += p.getData().toString()+",";
		s += p.getData().toString().toString() + "]";
		return s;

	}
}
