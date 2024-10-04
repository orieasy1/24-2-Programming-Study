public class GenericTest {
	public static void main(String[] args) {
		ObjectArray<String> array = new ObjectArray<>(7);

		array.set(0, "2024Fall Class");
		array.set(1, "Business Process Management");
		array.set(2, "Open Source Software");
		array.set(3, "Communication");
		array.set(4, "Data Structure");
		array.set(5, "Engineering Math");
		array.set(6, "Computer System");

		for(int i = 0; i < array.size(); i++){
			System.out.println(array.get(i));
		}
	}
}