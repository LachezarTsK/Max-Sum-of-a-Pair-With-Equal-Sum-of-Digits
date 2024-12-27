
import kotlin.math.pow
import kotlin.math.max

class Solution {

    private companion object {
        val RANGE_OF_VALUES = intArrayOf(1, (10.0).pow(9.0).toInt())
        const val NO_TWO_ELEMENTS_WITH_SAME_SUM_OF_DIGITS_FOUND = -1
        const val HAS_NO_ELEMENT = 0
    }

    fun maximumSum(input: IntArray): Int {
        /*
         If the max value is not pow(10, 9) but some random integer, then
         'sizeSumOfDigitsToValues = 1 + max((numberOfDigitsInMaxValue - 1) x 9, sumOfDigitsInMaxValue)'

          To keep things simple, since the given max value is guaranteed to be pow(10, 9),
          the current method is applied, which is basically 'sizeSumOfDigitsToValues = 1 + (numberOfDigitsInMaxValue - 1) x 9'
         */
        val sizeSumOfDigitsToValues = 1 + calculateSumOfDigits(RANGE_OF_VALUES[1] - 1)
        val sumOfDigitsToValues = IntArray(sizeSumOfDigitsToValues)

        var maxSumOfPairWithEqualSumOfDigits = NO_TWO_ELEMENTS_WITH_SAME_SUM_OF_DIGITS_FOUND
        for (value in input) {
            val sumOfDigits = calculateSumOfDigits(value)
            if (sumOfDigitsToValues[sumOfDigits] == HAS_NO_ELEMENT) {
                sumOfDigitsToValues[sumOfDigits] = value
                continue
            }
            maxSumOfPairWithEqualSumOfDigits = max(maxSumOfPairWithEqualSumOfDigits, sumOfDigitsToValues[sumOfDigits] + value)
            sumOfDigitsToValues[sumOfDigits] = max(sumOfDigitsToValues[sumOfDigits], value)
        }
        return maxSumOfPairWithEqualSumOfDigits
    }

    private fun calculateSumOfDigits(value: Int): Int {
        var value = value
        var sumOfDigits = 0
        while (value > 0) {
            sumOfDigits += value % 10
            value /= 10
        }
        return sumOfDigits
    }
}
