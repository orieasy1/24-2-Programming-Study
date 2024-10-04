public class ObjectArray<T> {
	private T[] array;

	public ObjectArray(int size) {
		array = (T[]) new Object[size];
	}

	public void set(int index, T value) {
		array[index] = value;
	}

	public T get(int index) {
		return array[index];
	}

	public int size() {
		return array.length;
	}
}