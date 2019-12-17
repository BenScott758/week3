package unaryPredicate;

/**
 * A predicate for classifying odd integers.
 *
 * @author Ben Scott
 * @version December 2019
 */
public class IsOdd extends CountingUnaryPredicate<Integer> {

    /**
     * Test whether a number is odd.
     *
     * @param n the number which is tested
     * @return true if n is odd
     */
    @Override
    public boolean test(Integer n) {
        return n % 2 != 0;
    }
}
