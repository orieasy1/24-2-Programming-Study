
public class Array_List<T> implements List<T> {
	private final T list[];
	private int numberOfEntries;
	private static final int DEFAULT_CAPACITY=25;
	private boolean integrityOK;
	private static final int MAX_CAPACITY = 1000;

	public Array_List(int desiredCapacity) {
		integrityOK= false;
		if(desiredCapacity <= MAX_CAPACITY) {
			@SuppressWarnings("unchecked")
			T[] tempList = (T[])new Object[desiredCapacity];
			list = tempList;
			numberOfEntries=0;
			integrityOK=true;
		} else {
			throw new IllegalStateException("Attemp to create a list whose "+
					"capacity exceeds allowed maximum");
		}
	}
	public Array_List() {
		this(DEFAULT_CAPACITY);
	}
	public boolean add(T newEntry) {
		return add(numberOfEntries, newEntry);
	}
	public boolean add(int givenPosition, T newEntry) {
		checkIntegrity();
		if(numberOfEntries >= MAX_CAPACITY)  return false;
		else {
			for(int i = numberOfEntries ; i >= givenPosition ; i--)
				list[i+1] = list[i];
			list[givenPosition]=newEntry;
			numberOfEntries++;
			return true;
		}
	}
	public boolean remove(T anEntry) {
		checkIntegrity();
		int index = indexOf(anEntry);
		T result = remove(index);
		return anEntry.equals(result);
	}
	public T remove(int givenPosition) {
		T result = null;
		if(numberOfEntries!=0 && givenPosition >=0) {
			result = list[givenPosition];
			for(int i = givenPosition ; i < numberOfEntries - 1; i++) 
				list[i]=list[i+1];
			list[numberOfEntries] = null;
			numberOfEntries--;
		}
		return result;
	}
	public T get(int givenPosition) {
		checkIntegrity();
		return list[givenPosition];
	}
	public int indexOf(T anEntry) {
		checkIntegrity();
		int found = -1;
		for(int i = 0 ; found < 0 && i < numberOfEntries ; i++)
			if(anEntry.equals(list[i])) found = i;
		return found;
	}
	public void clear() {
		checkIntegrity();
		while(numberOfEntries>0)remove(0);
	}
	public int size() {
		return numberOfEntries;
	}
	public String toString() {
		checkIntegrity();
		String s="[";
		for(int i = 0 ; i < numberOfEntries - 1; i++)
			s += list[i].toString()+",";
		if(numberOfEntries > 0) s += list[numberOfEntries-1].toString() + "]";
		else s += "]";
		return s;
	}
	public T[] toArray() {
	    checkIntegrity();
	    @SuppressWarnings("unchecked")
	    T[] result = (T[])new Object[numberOfEntries];
	    for(int i = 0 ; i < numberOfEntries ; i++)
	         result[i] = list[i];
	     return result;
	}
	private void checkIntegrity() {
		if(!integrityOK) throw new SecurityException("Array_List object is corrupt");
	}
	private boolean isFull() {
		return numberOfEntries>=list.length;
	}
}
