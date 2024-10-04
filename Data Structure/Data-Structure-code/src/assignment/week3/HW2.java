
public class HW2<T extends Comparable<T>> {
	public T max(T a, T b) {
		return (a.compareTo(b) >= 0) ? a : b;
	}
}
