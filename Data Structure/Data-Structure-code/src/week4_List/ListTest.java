
public class ListTest {
	public static void main(String[] args) {
		List<String> shoppingList = new Array_List<>();
		shoppingList.add("Brussels sprout");
		System.out.println("What I should buy are "+ shoppingList.size() + " items:"+shoppingList);

		shoppingList.add("tofu");
		System.out.println("What I should buy are "+ shoppingList.size() + " items:"+shoppingList);

		shoppingList.add("water");
		System.out.println("What I should buy are "+ shoppingList.size() + " items:"+shoppingList);

		shoppingList.add(2, "yogurt");
		System.out.println("The 0th item is "+ shoppingList.get(0));
		System.out.println("tofu is located in "+ shoppingList.indexOf("tofu"));

		System.out.println("What I should buy are "+ shoppingList.size() + " items:"+shoppingList);
		System.out.println("I got "+shoppingList.remove(1));

		System.out.println("What I should buy are "+ shoppingList.size() + " items:"+shoppingList);
		if(shoppingList.remove("tofu")) System.out.println("I remove tofu.");
		System.out.println("What I should buy are "+ shoppingList.size() + " items:"+shoppingList);

		shoppingList.clear();
		System.out.println("What I should buy are "+ shoppingList.size() + " items:"+shoppingList);
	}
}
