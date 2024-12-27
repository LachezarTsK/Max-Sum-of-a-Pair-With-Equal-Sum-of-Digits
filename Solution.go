

package main

import (
    "fmt"
    "math"
)

var RANGE_OF_VALUES = [2]int{1, int(math.Pow10(9))}

const NO_TWO_ELEMENTS_WITH_SAME_SUM_OF_DIGITS_FOUND = -1
const HAS_NO_ELEMENT = 0

func maximumSum(input []int) int {
    /*
    If the max value is not pow(10, 9) but some random integer, then
    'sizeSumOfDigitsToValues = 1 + max((numberOfDigitsInMaxValue - 1) x 9, sumOfDigitsInMaxValue)'

    To keep things simple, since the given max value is guaranteed to be pow(10, 9),
    the current method is applied, which is basically 'sizeSumOfDigitsToValues = 1 + (numberOfDigitsInMaxValue - 1) x 9'
    */
    sizeSumOfDigitsToValues := 1 + calculateSumOfDigits(RANGE_OF_VALUES[1]-1)
    sumOfDigitsToValues := make([]int, sizeSumOfDigitsToValues)

    maxSumOfPairWithEqualSumOfDigits := NO_TWO_ELEMENTS_WITH_SAME_SUM_OF_DIGITS_FOUND
    for _, value := range input {
        sumOfDigits := calculateSumOfDigits(value)
        if sumOfDigitsToValues[sumOfDigits] == HAS_NO_ELEMENT {
            sumOfDigitsToValues[sumOfDigits] = value
            continue
        }
        maxSumOfPairWithEqualSumOfDigits = max(maxSumOfPairWithEqualSumOfDigits, sumOfDigitsToValues[sumOfDigits]+value)
        sumOfDigitsToValues[sumOfDigits] = max(sumOfDigitsToValues[sumOfDigits], value)
    }
    return maxSumOfPairWithEqualSumOfDigits
}

func calculateSumOfDigits(value int) int {
    sumOfDigits := 0
    for value > 0 {
        sumOfDigits += value % 10
        value /= 10
    }
    return sumOfDigits
}
