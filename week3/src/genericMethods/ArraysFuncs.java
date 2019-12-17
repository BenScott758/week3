package genericMethods;

import unaryPredicate.UnaryPredicate;

/**
 * A demonstration of a generic method.
 *
 * This static method should take an array and two integer indices in the array,
 * and swap the two entries in the array at those indices.
 *
 * @author Ben Scott
 * @version December 2019
 */
public class ArraysFuncs {

    /**
     *
     * @param elementArray the array the elements are being swapped in.
     * @param e1 the index of the first element to be swapped.
     * @param e2 the index of the second element to be swapped.
     * @param <T> the type of objects/elements in the array.
     * @return the array with the two elements swapped and passed in array parameter.
     */

    public static <T> T[] Swap(T[] elementArray, int e1, int e2)
    {
        T t = elementArray[e2];						//Temporarily store the item at index_b.
        elementArray[e2] = elementArray[e1];			//Set index_b to the item at index_a
        elementArray[e1] = t;							//Set index_a to the item that was at index_b

        return elementArray;								//Return the array so that additional calls may be made on it.
    }

    /***
     *
     * @param array
     * @param from
     * @param to
     * @param <T>
     * @return
     */
    static <T extends Comparable<T>> T max(T[] array,int from,int to) {
        if (from < 0 || to > array.length) {
            throw new ArrayIndexOutOfBoundsException("[from,to) must be a section of [0," + array.length + ")");
        }
        if (to <= from) {
            throw new ArrayIndexOutOfBoundsException("to must be greater than from");
        }
        T currentLargest = array[from]; // current largest value is the first element
        for (int i = from+1; i < to; i++) { // for all values in the array
            if (array[i].compareTo(currentLargest) > 0) { // if this value is larger than the current largest
                currentLargest = array[i]; // update the current largest to the new, larger value
            }
        } // have now checked all values in the array, so current largest is the maximum value in the whole array
        return currentLargest;
    }

    /***
     *
     * @param array
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> int Satisfies(T[] array, UnaryPredicate<T> predicate)
    {
        int count = 0;								//Initialise the count as 0, there are currently no items in the array that have passed the predicate.

        for (T item : array)						//Go through every item in the array.
            if (predicate.test(item))				//If the predicate returns true for this item,
                count++;							//	increase the count;

        return count;								//Return how many items have passed the predicate.
    }
}