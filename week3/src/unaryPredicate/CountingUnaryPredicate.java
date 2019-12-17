package unaryPredicate;

import genericMethods.ArraysFuncs;

/**
 *
 * A UnaryPredicate implementation which utilises ArraysFunc.Satisfies
 * to provide a count which passes the predicate which is being implemented.
 *
 * @author Ben Scott
 * @version December 2019
 */

public abstract class CountingUnaryPredicate<T> implements UnaryPredicateCount<T>
{
    @Override
    public int numberSatisfying(T[] array)
    {
        return ArraysFuncs.Satisfies(array, this);
    }
}