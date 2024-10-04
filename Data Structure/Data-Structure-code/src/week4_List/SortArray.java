
import java.util.PriorityQueue;

public class SortArray<T extends Comparable<? super T>> {
	public T[] array;
	/*
	 * swaps the array entries a[i] and a[j]
	 * @param a: array of Comparable objects
	 * @param i: index
	 * @param j: index
	 */
	public SortArray(T[] a) {
		array = a.clone();
	}
	private void swap(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j]; a[j]=temp;
	}
	/**
	 * iterative Bubble sorting
	 */

	public T[] bubbleI() {
		T[] result = array.clone();
		for(int i = result.length -1 ; i >0 ; i--) {
			for(int j=0; j < i ; j++)
				if(result[j].compareTo(result[j+1])>0) swap(result, j, j+1);
		}
		return result;
	}
	/**
	 * Recursive Bubble sorting
	 */
	public T[] bubbleR() {
		T[] result = array.clone();
		if(array.length<2) return result;
		for(int i = result.length -1 ; i > 0 ; i--) 
			bubbleSort(result, 0, i);
		return result;
	}
	/*
	 * Helper method for a recursive selection sorting	
	 * @param a: array of Comparable objects
	 * @param n: an integer >0
	 */
	private void bubbleSort(T[] a, int n1, int n2) {
		if(n1 >= n2) return;
		if(a[n1].compareTo(a[n1+1])>0) swap(a, n1, n1+1);
		bubbleSort(a, n1+1, n2);
	}
	/**
	 * Iterative selection sorting 
	 */
	public T[] selectionI() {
		T[] result = array.clone();
		for(int i=result.length-1 ; i > 0 ; i--) {
			swap(result, i, getMaxIndex(result, i));
		}	
		return result;
	}
	private int getMaxIndex(T[] a, int n) {
		int maxIndex = 0;
		T max=a[0];
		for(int i = 1 ; i <= n ; i++) {
			if(max.compareTo(a[i])<0) {
				max = a[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	/**
	 * Recursive selection sorting 
	 */
	public T[] selectionR() {
		T[] result = array.clone();
		selectionSort(result, result.length-1);
		return result;
		
	}
	/**
	 * Helper method for a recursive selection sorting	
	 * @param a: array of Comparable objects
	 * @param n: the largest integer >0
	 */
	private void selectionSort(T[] a, int n) {
		if(n==0) return;
		swap(a, n, getMaxIndex(a, n));
		selectionSort(a, n-1);
	}
	/**
	 * Iterative insertion sorting 
	 */
	public T[] insertionI() {
		T[] result = array.clone();
		for(int i=1 ; i < result.length ; i++) {
			for(int j = i-1 ; j >= 0 && result[j+1].compareTo(result[j])<=0; j--){
				if(result[j+1].compareTo(result[j])<0) {
					swap(result, j, j+1);
				}
			}
		}	
		return result;
	}
	/**
	 * Recursive insertion sorting 
	 */
	public T[] insertionR() {
		T[] result = array.clone();
		for(int i=1 ; i < result.length ; i++) {
			if(result[i].compareTo(result[i-1])<0)
				insertionSort(result, result[i], i-1);
		}
		return result;
		
	}
	/**
	 * Helper method for a recursive insertion sorting	
	 * @param a: array of Comparable objects
	 * @param n: the largest integer >0
	*/
	private void insertionSort(T[] a, T value, int n) {
		if(n>=0 && value.compareTo(a[n])<0) {
			swap(a, n, n+1);
			insertionSort(a, value, n-1);
		} else	return;
	}
	/**
	 * Iterative shell sorting 
	 */
	public T[] shellI() {
		T[] result = array.clone();
		for(int d=result.length/2 ; d >0 ; d=d/2) {
			for(int c = 0 ; c < d ; c++ )
				insertionI (result, c, d);		
		}
		return result;
	}
	private void insertionI(T[] a, int c, int d) {
		for(int i=c+d ; i < a.length ; i+=d) {
			for(int j = i-d ; j >= c && a[j+d].compareTo(a[j])<=0; j--){
				if(a[j+d].compareTo(a[j])<0) {
					swap(a, j+d, j);
				}
			}
		}	
	}
	public T[] mergeSort() {
		T[] result = array.clone();
		mergeSort(result, 0, result.length-1);
		return result;
	}
	private void mergeSort(T[] a, int begin, int last) {
		if (begin<last) {
			int mid = (begin+last)/2;
			mergeSort(a, begin, mid);
			mergeSort(a, mid+1, last);
			merge(a, begin, mid, last);
		}
	}
	private void merge(T[] a, int begin, int mid, int last) {
		int i = begin, j=mid+1, k=0;
		T[] temp = (T[]) (new Comparable[last+1-begin]);
		for(; i <= mid && j <= last && k<temp.length;k++) {
			if(a[i].compareTo(a[j])<0)	{
				temp[k]= a[i];
				i++;
			} else {
				temp[k] = a[j];
				j++;
			}			
		}
		if(i <= mid )
			for(;i <= mid && k<temp.length; i++, k++) 
				temp[k]=a[i];			
		else if(j <= last)
			for(;j <= last && k<temp.length; j++, k++) 
				temp[k]=a[j];
		for(k=0; k<temp.length;k++) 
			a[begin+k]=temp[k];
	}		
	public T[] quickSort() {
		T[] result = array.clone();
		quickSort(result, 0, result.length-1);
		return result;
	}
	private void quickSort(T[] a, int begin, int last) {
		if(last<=begin)return;
		int pivotIndex = begin;
		boolean forward = false;
		T pivot = a[begin];
		int i = begin+1, j=last;
		while(i<=j) {
			if(forward) {
				if(pivot.compareTo(a[i])<0) {
					swap(a, i, pivotIndex);
					pivotIndex = i;
					forward = false;
				}
				i++;				
			} else {
				if(pivot.compareTo(a[j])>0) {
					swap(a, j, pivotIndex);
					pivotIndex = j;
					forward = true;
				}
				j--;
			}
		}
		quickSort(a, begin, pivotIndex-1);
		quickSort(a, pivotIndex+1, last);
	}
	public T[] heapSort() {
		T[] result = array.clone();		
		PriorityQueue<T> heap = new PriorityQueue<>();
		for(int i = 0 ; i<array.length ; i++)
			heap.add(array[i]);
		for(int i = 0 ; !heap.isEmpty()&&i<result.length ; i++)
			result[i]=heap.poll();
		return result;

	}
}
