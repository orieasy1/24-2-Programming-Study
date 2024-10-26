## 제네릭이란?

ArrayList의 경우 기본적으로 모든 객체를 담아야하기 때문에 내부에 Object 배열을 가지고 있다. Object의 경우 모든 클래스의 부모 클래스이다보니 모든 타입의 배열을 넣을 수 있다. 그래서 ArrayList에서 String 타입만 취급하고 싶음에도 불구하고 다른 여러 가지의 타입의 객체들이 들어갈 수 있다.

ArrayList의 안정성을 보장하고자 사용하는 것이 Generic이다. (특정한 타입만 넣을 수 있도록 함) 내가 담고자 하는 데이터 타입을 제한해주는 역할을 한다.

```java
ArrayList<String>
```

⬆️ 위와 같이 작성한다면  ArrayList에 들어가는 데이터 타입을 String으로 제한할 수 있다.

자바 제네릭은 자바에서 데이터 타입을 일반화하는 바법이다.

제네릭을 사용하면 컬렉션, 메서드, 클래스 등에서 사용하는 데이터 타입을 런타임시에 결정할 수 있다.  **< >기호**를 사용하여 표시한다.

### 왜 제네릭을 사용해야하는가?

제일 큰 이유는 **코드의 재사용성이 높아지기 때문**이다.

하나의 클래스를 만들어두고 타입을 변경하며 사용할 수 있다면, 하나의 클래스로 여러 개의 클래스를 만드는 효과를 얻을 수 있다.

```java
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
```

```java
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
```

ObjectArray클래스의 T는 아직 데이터 타입의 형태가 결정되어있지 않은 가변형이라고 보면 된다. GenericTest 클래스에서 T대신 String을 작성한 것을 볼 수 있는데 이렇게 되면 array라는 객체를 만들 때 ObjectArray클래스에서 T는 모두 String으로 대체된다.

그 과정을 한 번 살펴보면(ObjectArray 클래스의 생성자를 살펴보자)

```java
public ObjectArray(int size) {
        array = (T[]) new Object[size];
    }
```

우선 객체를 생성할 때(=new를 사용할 때)는 Object로 생성을 하고 타입이 결정되면 해당 타입으로 Downcasting하는 것을 알 수 있다.

ObjectArray클래스에서 제네릭을 사용했기 때문에 String말고도 아래와 같이 Integer타입을 넣어서도 클래스를 재사용할 수 있다.

```java
public class GenericTest2 {
	public static void main(String[] args) {
		ObjectArray<Integer> array = new ObjectArray<>(7);

		array.set(0, 0);
		array.set(1, 1);
		array.set(2, 2);
		array.set(3, 3);
		array.set(4, 4);
		array.set(5, 5);
		array.set(6, 6);

		for(int i = 0; i < array.size(); i++){
			System.out.println(array.get(i));
		}
	}
}
```

즉 어떤 타입이든 T자리에 넣어서 ObjectArray 클래스를 여러 번 재사용할 수 있다는 것이다.

### 자바 제네릭 타입이란?

제네릭 타입이란 클래스, 인터페이스, 메소드 등에서 사용될 수 있는 타입 매개변수(parameter)이다.

예를 들어, ArrayList 클래스는 제네릭 타입을 사용하여 요소의 타입을 지정할 수 있다.
이때 요소의 타입은 ArrayList의 인스턴스를 생성할 때 매개변수로 전달된다.

```java
ArrayList<String> list = new ArrayList<String>();
```

으로 list를 만들면 요소의 타입으로 String을 지정한다.

위 코드에서 <String>은 제네릭 타입 매개변수로 ArrayList에서 사용할 요소의 타입으로 대체된다. 이를 통해 ArrayLists는 요소의 타입을 명시적으로 지정할 수 있으며 타입 안전성(type safty)을 보장할 수 있다.

만약 <>안에 타입을 써주지 않는다면 Object 배열이 만들어지게 된다. 이렇게 되면 모든 타입의 데이터를 넣을 수 있기 때문에 타입 안전성을 보장하기 어렵다.

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/8599081c-d21b-4f56-bb73-677f3b45a067/0d24fff9-7384-49e9-a523-14f8a9e9f077/image.png)

위 사진에서도 기본적으로 Object 타입이 들어가는 것을 확인할 수 있다.

또 아래처럼 서로 다른 타입의 요소들을 넣어도 에러가 나지 않는 것을 확인할 수 있다.

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/8599081c-d21b-4f56-bb73-677f3b45a067/2c078b99-cd77-4fdc-a8d5-02e4abbea5fe/image.png)

위 처럼 여러 타입의 요소들이 저장된다면 나중에 출력을 하거나 2차 가공을 할 때 문제가 발생할 수 도 있다.

### 제네릭 멀티 타입 파라미터

제네릭 타입을 여러 개 선언하여 사용하는 것을 말한다

```java
public class Pair<K, V> {
    private K key;
    private V value;
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
```

```java
public class PairGenericTest {
    public static void main(String[] args) {
        Pair<String, Integer> pair = new Pair<>("hello",1);
        System.out.println("key: " + pair.getKey());
        System.out.println("value: " + pair.getValue);
    }
}
```

위 코드에선느 Pair 클래스가 K와 V라는 두 개의 제네릭 타입 파라미터를 가지고 있다.
Pair 클래스의 생성자에서는 K와 V를 가각 인자로 받아들이며 객체의 key와 value 필드에 저장한다.

### 04. 제네릭 제한된 타입 파라미터

특정한 타입으로 제한된 제네릭 타입 파라미터를 말한다.
이를 통해 제네릭 클래스나 메서드에서 사용할 수 있는 타입을 제한할 수 있다.

```java
public class AverageCalculator<T> {
    private T[] numbers;

    public AverageCalculator(T[] numbers) {
        this.numbers = numbers;
    }

    public double calculateAverage() {
        doble sum = 0.0;

        for(T number : numbers) {
            sum += number.doubleValue();
        }

        return sum / numbers.length;
    }
}

```

```java

public class GenericLimitTest{
    public static void main(String[] args) {
        Integer integers = {1, 2, 3, 4, 5};
        Double[] doubles = {1.0, 2.0, 3.0, 4.0, 5.0};

        AverageCalculator<Integer> integerCalculator = new AverageCalculator<>(integers);
        double integerAverage = integerCalculator.calculateAverage();
        System.out.println("Integer average: " + integerAverage);

        AverageCalculator<Double> doubleCalculator = new AverageCalculator<>(doubles);
        double doubleAverage = doubleCalculator.calculateAverage();
        System.out.println("Double average: " + doubleAverage);
    }
}

```

위 코드에서 <T extends Number>는 T가 Number 클래스 또는 Number 클래스의 하위 클래스인 타입만 사용할 수 있다는 것을 의미한다.
따라서 이 메서드를 호출할 때 T에는 Integer, Double, Float 등의 숫자 타입만 사용할 수 있다.