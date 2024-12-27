
function maximumSum(input: number[]): number {
    /*
    If the max value is not pow(10, 9) but some random integer, then
    'sizeSumOfDigitsToValues = 1 + max((numberOfDigitsInMaxValue - 1) x 9, sumOfDigitsInMaxValue)'

    To keep things simple, since the given max value is guaranteed to be pow(10, 9), 
    the current method is applied, which is basically 'sizeSumOfDigitsToValues = 1 + (numberOfDigitsInMaxValue - 1) x 9'
     */
    const RANGE_OF_VALUES = [1, Math.pow(10, 9)];
    const NO_TWO_ELEMENTS_WITH_SAME_SUM_OF_DIGITS_FOUND = -1;
    const HAS_NO_ELEMENT = 0;

    const sizeSumOfDigitsToValues = 1 + calculateSumOfDigits(RANGE_OF_VALUES[1] - 1);
    const sumOfDigitsToValues = new Array(sizeSumOfDigitsToValues).fill(0);

    let maxSumOfPairWithEqualSumOfDigits = NO_TWO_ELEMENTS_WITH_SAME_SUM_OF_DIGITS_FOUND;
    for (let value of input) {
        const sumOfDigits = calculateSumOfDigits(value);
        if (sumOfDigitsToValues[sumOfDigits] === HAS_NO_ELEMENT) {
            sumOfDigitsToValues[sumOfDigits] = value;
            continue;
        }
        maxSumOfPairWithEqualSumOfDigits = Math.max(maxSumOfPairWithEqualSumOfDigits, sumOfDigitsToValues[sumOfDigits] + value);
        sumOfDigitsToValues[sumOfDigits] = Math.max(sumOfDigitsToValues[sumOfDigits], value);
    }
    return maxSumOfPairWithEqualSumOfDigits;
};

function calculateSumOfDigits(value: number): number {
    let sumOfDigits = 0;
    while (value > 0) {
        sumOfDigits += value % 10;
        value = Math.floor(value / 10);
    }
    return sumOfDigits;
}
