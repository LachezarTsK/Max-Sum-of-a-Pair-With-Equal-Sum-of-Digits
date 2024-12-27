
public class Solution {

    private static final int[] RANGE_OF_VALUES = {1, (int) Math.pow(10, 9)};
    private static final int NO_TWO_ELEMENTS_WITH_SAME_SUM_OF_DIGITS_FOUND = -1;
    private static final int HAS_NO_ELEMENT = 0;

    public int maximumSum(int[] input) {
        /*
        If the max value is not pow(10, 9) but some random integer, then
        'sizeSumOfDigitsToValues = 1 + max((numberOfDigitsInMaxValue - 1) x 9, sumOfDigitsInMaxValue)'

        To keep things simple, since the given max value is guaranteed to be pow(10, 9), 
        the current method is applied, which is basically 'sizeSumOfDigitsToValues = 1 + (numberOfDigitsInMaxValue - 1) x 9'
         */
        int sizeSumOfDigitsToValues = 1 + calculateSumOfDigits(RANGE_OF_VALUES[1] - 1);
        int[] sumOfDigitsToValues = new int[sizeSumOfDigitsToValues];

        int maxSumOfPairWithEqualSumOfDigits = NO_TWO_ELEMENTS_WITH_SAME_SUM_OF_DIGITS_FOUND;
        for (int value : input) {
            int sumOfDigits = calculateSumOfDigits(value);
            if (sumOfDigitsToValues[sumOfDigits] == HAS_NO_ELEMENT) {
                sumOfDigitsToValues[sumOfDigits] = value;
                continue;
            }
            maxSumOfPairWithEqualSumOfDigits = Math.max(maxSumOfPairWithEqualSumOfDigits, sumOfDigitsToValues[sumOfDigits] + value);
            sumOfDigitsToValues[sumOfDigits] = Math.max(sumOfDigitsToValues[sumOfDigits], value);
        }
        return maxSumOfPairWithEqualSumOfDigits;
    }

    private int calculateSumOfDigits(int value) {
        int sumOfDigits = 0;
        while (value > 0) {
            sumOfDigits += value % 10;
            value /= 10;
        }
        return sumOfDigits;
    }
}
