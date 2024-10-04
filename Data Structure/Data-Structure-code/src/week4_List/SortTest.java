import java.util.Arrays;

public class SortTest {
	public static void main(String[] args) {
		Double[] original;
		for(int k = 100; k<=5000;k+=100) {
			System.out.print(k+"\t");
			original = new Double[k];
			//		Double[] original = new Double[8];
			for(int i = 0 ; i < original.length ; i ++) {
				original[i] = Math.random();
			}
			SortArray<Double> sorted = new SortArray<>(original);

			Arrays.sort(original);
			long t0, t1;
			t0=System.currentTimeMillis();
			int same = Arrays.compare(original, sorted.bubbleI());
			t1=System.currentTimeMillis();
			System.out.print((t1-t0)+"\t");

			t0=System.currentTimeMillis();
			same = Arrays.compare(original, sorted.bubbleR());
			t1=System.currentTimeMillis();
			System.out.print((t1-t0)+"\t");

			t0=System.currentTimeMillis();
			same = Arrays.compare(original, sorted.selectionI());
			t1=System.currentTimeMillis();
			System.out.print((t1-t0)+"\t");

			t0=System.currentTimeMillis();
			same = Arrays.compare(original, sorted.selectionR());
			t1=System.currentTimeMillis();
			System.out.print((t1-t0)+"\t");

			t0=System.currentTimeMillis();
			same = Arrays.compare(original, sorted.insertionI());
			t1=System.currentTimeMillis();
			System.out.print((t1-t0)+"\t");

			t0=System.currentTimeMillis();
			same = Arrays.compare(original, sorted.insertionR());
			t1=System.currentTimeMillis();
			System.out.print((t1-t0)+"\t");

			t0=System.currentTimeMillis();
			same = Arrays.compare(original, sorted.shellI());
			t1=System.currentTimeMillis();
			System.out.print((t1-t0)+"\t");

			t0=System.currentTimeMillis();
			same = Arrays.compare(original, sorted.mergeSort());
			t1=System.currentTimeMillis();
			System.out.print((t1-t0)+"\t");

			t0=System.currentTimeMillis();
			same = Arrays.compare(original, sorted.quickSort());
			t1=System.currentTimeMillis();
			System.out.print((t1-t0)+"\t");

			t0=System.currentTimeMillis();
			same = Arrays.compare(original, sorted.heapSort());
			t1=System.currentTimeMillis();
			System.out.print((t1-t0)+"\n");
		}
	}
}
