package genericMethods;

import org.junit.jupiter.api.Test;
import unaryPredicate.IsEven;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArraysFuncsTest {
private Integer[] ints() {
	return new Integer[] { 16, 9, 3, 10, 20, 14, 12, 7, 19, 8, 11, 17, 6, 2, 5, 18, 1, 13, 15, 4 };
}

@Test
public void testSwap() {
	Integer[] array = ints();							//Generate a standard sample array.
	int e1 = (int)(Math.random() * array.length);			//Pick a random index from the array.
	int e2 = (int)(Math.random() * (array.length - 1));	//Pick another random index from the array.

	if (e2 >= e1)
		e2 = e2 + 1;									//Prevent the same index from being chosen for both indicies.

	int expectedA = array[e2];							//Item at e1 should be the item at e2 after operation.
	int expectedB = array[e1];							//Item at e2 should be the item at e1 after operation.

	ArraysFuncs.Swap(array, e1, e2);					//Swap the two items.
	assertTrue(array[e1] == expectedA && array[e2] == expectedB);	//Test if the predictions were correct.
}

    @Test
    public void testSatisfies()
    {
        Integer[] array = ints();
        IsEven predicate = new IsEven();

        assertTrue(ArraysFuncs.Satisfies(array, predicate) == 10);
    }

    private int testMax(Integer[] array, int e1, int e2)
    {
        return ArraysFuncs.max(array, e1, e2);
    }
    @Test
    public void testMaxAll()
    {
        Integer[] array = ints();
        int e1 = 0;
        int e2 = array.length - 1;
        int expected = 20;

        assertTrue(testMax(array, e1, e2) == expected);
    }

    @Test
    void testMaxIntegerFullRange() {
        Integer[] array = {3,8,2,9,6,5,1};
        assertEquals(new Integer(9), ArraysFuncs.max(array,0,7));
    }


    @Test
    void testMaxIntegerPartialRange() {
        Integer[] array = {3,8,2,9,6,5,1};
        assertEquals(new Integer(8), ArraysFuncs.max(array,0,3));
    }

    @Test
    void testMaxStringFullRange() {
        String[] array = {"antelope","a","aardvark"};
        assertEquals("antelope", ArraysFuncs.max(array,0,3));
    }

    @Test
    void testMaxStringPartialRange() {
        String[] array = {"antelope","a","aardvark"};
        assertEquals("aardvark", ArraysFuncs.max(array,1,3));
    }

    // Test errors

    @Test
    void testNullArrayException() {
        assertThrows(NullPointerException.class,()->
                ArraysFuncs.max(null,1,4));
    }

    @Test
    void testNegativeIndexException() {
        Integer[] array = {3,8,2,9,6,5,1};
        assertEquals(new Integer(8), ArraysFuncs.max(array,0,3));
        assertThrows(ArrayIndexOutOfBoundsException.class,()->
                ArraysFuncs.max(array,-1,4));
    }

    @Test
    void testHighIndexException() {
        Integer[] array = {3,8,2,9,6,5,1};
        assertEquals(new Integer(8), ArraysFuncs.max(array,0,3));
        assertThrows(ArrayIndexOutOfBoundsException.class,()->
                ArraysFuncs.max(array,2,8));
    }

    @Test
    void testIndicesReversedException() {
        Integer[] array = {3,8,2,9,6,5,1};
        assertEquals(new Integer(8), ArraysFuncs.max(array,0,3));
        assertThrows(ArrayIndexOutOfBoundsException.class,()->
                ArraysFuncs.max(array,5,2));
    }

    @Test
    void    testNegativeIndicesEqualException() {
        Integer[] array = {3,8,2,9,6,5,1};
        assertEquals(new Integer(8), ArraysFuncs.max(array,0,3));
        assertThrows(ArrayIndexOutOfBoundsException.class,()->
                ArraysFuncs.max(array,4,4));
    }

    /**
     * Create own comparable class to test max
     * Class "boxes" two comparables
     */
    private class Box<A extends Comparable<A>,B extends Comparable<B>> implements Comparable<ArraysFuncsTest.Box<A,B>> {
        private A first;
        private B second;

        Box(A first, B second) {
            this.first = first;
            this.second = second;
        }

        A getFirst() {
            return first;
        }

        B getSecond() {
            return second;
        }

        /**
         * In comparisons first entry takes precedence over second
         */
        public int compareTo(ArraysFuncsTest.Box<A,B> box) {
            if (getFirst().compareTo(box.getFirst())!=0) {
                return getFirst().compareTo(box.getFirst());
            } else {
                return getSecond().compareTo(box.getSecond());
            }
        }

        /**
         * Need to define equality as well
         */
        public boolean equals(Object object) {
            if (!(object instanceof ArraysFuncsTest.Box<?,?>)) {
                return false;
            }
            ArraysFuncsTest.Box<?,?> box = (ArraysFuncsTest.Box<?,?>) object;
            return getFirst().equals(box.getFirst()) && getSecond().equals(box.getSecond());
        }


    }

    @Test
    void testBox1() {
        @SuppressWarnings("unchecked") // can't create array of Box<String,Integer>[], so create Box[] instead
                ArraysFuncsTest.Box<String,Integer>[] array = new ArraysFuncsTest.Box[5];
        array[0] = new ArraysFuncsTest.Box<>("antelope", 7);
        array[1] = new ArraysFuncsTest.Box<>("aardwolf", 7);
        array[2] = new ArraysFuncsTest.Box<>("aardvark", 7);
        array[3] = new ArraysFuncsTest.Box<>("aardvark", 9);
        array[4] = new ArraysFuncsTest.Box<>("aardvark", 7);
        assertEquals(new ArraysFuncsTest.Box<>("antelope", 7), ArraysFuncs.max(array,0,5));
    }

    @Test
    void testBox2() {
        @SuppressWarnings("unchecked") // can't create array of Box<String,Integer>[], so create Box[] instead
                ArraysFuncsTest.Box<String,Integer>[] array = new ArraysFuncsTest.Box[5];
        array[0] = new ArraysFuncsTest.Box<>("aardvark", 8);
        array[1] = new ArraysFuncsTest.Box<>("aardvark", 3);
        array[2] = new ArraysFuncsTest.Box<>("aardvark", 1);
        array[3] = new ArraysFuncsTest.Box<>("aardvark", 9);
        array[4] = new ArraysFuncsTest.Box<>("aardvark", 5);
        assertEquals(new ArraysFuncsTest.Box<>("aardvark", 9), ArraysFuncs.max(array,0,5));
    }

    @Test
    void testBox3() {
        @SuppressWarnings("unchecked") // can't create array of Box<String,Integer>[], so create Box[] instead
                ArraysFuncsTest.Box<String,Integer>[] array = new ArraysFuncsTest.Box[5];
        array[0] = new ArraysFuncsTest.Box<>("aardvark", 5);
        array[1] = new ArraysFuncsTest.Box<>("aardvark", 5);
        array[2] = new ArraysFuncsTest.Box<>("aardvark", 5);
        array[3] = new ArraysFuncsTest.Box<>("aardvark", 5);
        array[4] = new ArraysFuncsTest.Box<>("aardvark", 5);
        assertEquals(new ArraysFuncsTest.Box<>("aardvark", 5), ArraysFuncs.max(array,0,5));
    }
}