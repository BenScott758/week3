package unaryPredicate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsOddTest
{
    private IsOdd _predicate = new IsOdd();

    @Test
    public void TestZero()
    {
        assertFalse(_predicate.test(0));
    }

    @Test
    public void TestOne()
    {
        assertTrue(_predicate.test(1));
    }

    @Test
    public void TestTwo()
    {
        assertFalse(_predicate.test(2));
    }

    @Test
    public void TestThree()
    {
        assertTrue(_predicate.test(3));
    }

    @Test
    public void TestBigEven()
    {
        assertFalse(_predicate.test(2*((Integer.MAX_VALUE-1)/2)));
    }

    @Test
    public void TestBigOdd()
    {
        assertTrue(_predicate.test(2*((Integer.MAX_VALUE-1)/2)-1));
    }

    @Test
    public void TestMinusOne()
    {
        assertTrue(_predicate.test(-1));
    }

    @Test
    public void TestMinusTwo()
    {
        assertFalse(_predicate.test(-2));
    }

    @Test
    public void TestMinusThree()
    {
        assertTrue(_predicate.test(-3));
    }

    @Test
    public void TestMinusBigEven()
    {
        assertFalse(_predicate.test(2*((Integer.MIN_VALUE+1)/2)));
    }

    @Test
    public void TestMinusBigOdd()
    {
        assertTrue(_predicate.test(2*((Integer.MIN_VALUE+1)/2)+1));
    }
}