
public interface List<T> {
	public boolean add(int givenPosition, T newEntry);
	public boolean add(T newEntry);
	public boolean remove(T anEntry);
	public T remove(int givenPosition);
	public T get(int givenPosition);
	public int indexOf(T anEntry);
	public void clear();
	public int size();
}
