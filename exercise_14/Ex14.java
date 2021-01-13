/**
 * Class represanting the 14th exercise question answers
 * Author : Ariel Szabo
 * Version : 10/01/2021
 *
 */


public class Ex14 {

    /**
     * Question number 1
     *
     * This method returns the value which has no adjacent pair to it.
     *
     *
     * This method is an implementation on binary-search so the time-complexity of this method is O(log n).
     * The memory-complexity of this method is O(1) because we only store integer values in variables.
     *
     * The motivation for the solution came from this:
     * The array contains pairs of numbers adjacent to each other, but one number has no pair.
     * so the original array most have odd number of elements.
     * we'll try to eliminate the "valid" sub-arrays (sub array where all the numbers have pairs) by making sure the
     * size of the sub array is even* and the closest elements to the edge** of the sub-array are equal.
     * (
     *      * The even-length check is beacuse there can only be one number without a pair.
     *      ** the edge is refaring to the last place we sliced the array to create the sub-array
     * )
     * if they are not equal we conclude the number without a pair is in the current sub-array and that the other part
     * of the array is "valid".
     *
     *
     * another assumtion we have is that if we are in an even index (denote it as 'i') in the array then there are
     * 'i' elements before this index and 'n-i-1' elements after it (both must be even - n is odd, i is even,
     * so n-i-1 is even too).
     *
     * In this binary-search implementation at each iteration we eliminate the first half of the array by cheking if
     * the 2 values left to the middle are equal (if left of the middle sub-array is "valid").
     * we eliminate the last half of the array by cheking if the 2 values right to the middle are equal.
     * in case both options are false the middle value is the number without a pair.
     *
     * At each iteration of the while loop we decrase the arrays' size (creating sub-arrays) by changing the start and
     * end indexes.
     * By eliminating an even amount of elemnt at each time we keep the array (sub-array) size even.
     *
     * When we encaounter an array of length 1 or 3 we have a simple deterministic if-else method for finding the number.
     *
     *
     * @param a an array of numbers. Each number appers two times in adjacent places, while only one number has no pair.
     * @return the value which has no pair.
     */
    public static int findSingle (int [] a) {
        int startIndex = 0;
        int endIndex = a.length - 1;

        int length;
        while (true) {
            length = endIndex - startIndex + 1;
            if (length == 1) {
                return a[startIndex];
            }

            if (length == 3) {
                // 2 following places must be equal so if the first two are not than the last two are
                if (a[startIndex] == a[startIndex + 1]) {
                    return a[startIndex + 2]; // first two are equal - last one is the number without a pair
                } else {
                    return a[startIndex]; // last two are equal - last first is the number without a pair
                }
            }

            
            int middle = length / 2;
            if (middle % 2 != 0) { // make sure we are in an even index
                middle -= 1;
            }


            if (a[startIndex + middle - 1] == a[startIndex + middle - 2]){  // check if the left part is "valid"
                startIndex += middle;
            } else if (a[startIndex + middle + 1] == a[startIndex + middle + 2]) { // check if the right part is "valid"
                endIndex = startIndex + middle;
            } else {
                return a[startIndex + middle];
            }
        }
    }

    /**
     * Question number 2
     *
     * This method finds what is the smallest sub-array (if exists) of the input array which values sum to a bigger
     * number than x (sub-array is a slice of the original array).
     *
     * if no subarray found the method returns -1.
     *
     *
     * Although we use a for-loop inside a for-loop the time-complexity of this method is O(n) and not O(n^2):
     * we are using two "pointers" pointing to the two ends of the subarray.
     * A "head" which points to the end index of the subarray and a "tail" which points to the start index of the
     * subarray.
     * At each step of the outside loop the "head" moves forward by one (so that's N steps).
     * at each step we can ge inside the inner loop where we'll increment the tail by one at each inner-loop, meaning
     * the "tail" can move forward up to the pointer of the "head".
     * Like a snake growing and shrinking - so, in any case this is 2*N steps, which is O(n).
     *
     * The memory-complexity of this method is O(1) because we only store integer values in variables.
     *
     * @param arr array with positive integer values
     * @param x a positive integer
     * @return smallest subarray which values sum is bigger than x, if not found returns -1
     */
    public static int smallestSubSum(int arr[], int x) {


        int minSubarrayLength = Integer.MAX_VALUE; // default value denoting no subarray found
        int subArrayStart = 0;
        int subArraySum = 0;
        for (int subArrayEnd = 0; subArrayEnd < arr.length; subArrayEnd++) { // move the "head" by one
            subArraySum += arr[subArrayEnd];
            if (subArraySum > x) {
                // found a subarray which sums to a bigger number than x
                // lets find if there is a smaller sub array
                for (int i = subArrayStart; i <= subArrayEnd; i++) { // move from the last "tail" index to "head" index
                    minSubarrayLength = Math.min(minSubarrayLength, subArrayEnd - subArrayStart + 1);

                    subArraySum -= arr[subArrayStart];
                    subArrayStart += 1; // move the "tail" forward towards the "head"
                    if (subArraySum <= x) {
                        break;
                    }
                }
            }
        }

        if (minSubarrayLength == Integer.MAX_VALUE){ // no subarray found so the default value wasn't changed
            return -1;
        }
        return minSubarrayLength;
    }

    /**
     * Question number 3
     * This method counts and prints all the combinations of 3 numbers (between 1 and 10) which sum to the given number.
     *
     * @param num a number used for counting combinations of 3 numbers which sum to it.
     * @return the amount of options for a 3 number combinations which sum to the given number
     */
    public static int solutions(int num) {
        if (num < 3 || num > 30) {
            return 0;
        }
        // overloading which assigns the minimal value for 2 out of the 3 numbers in the addition:
        // onlyIncreaseB is used to avoid the recursion tree rsults to overlap (explained more inside the method)
        return solutions(num, 1, 1, false);
    }

    private static int solutions(int num, int a, int b, boolean onlyIncreaseB) {
        int c = num - a - b; // calcualte the complement value to the given a and b based on the num
        if (a > 10 || b > 10) { // make sure a and b have not bypassed the maximum valid value
            return 0;
        }

        int validSolutionsCount;
        if (c >= 1 && c <= 10) {
            System.out.println(a + " + " + b + " + " + c);
            validSolutionsCount = 1;

        } else { // c has no valid value so all the three numbers are not a valid solution
            validSolutionsCount = 0;
        }

        // onlyIncreaseB is a flag used for denoting when only the second parameter b needs to be increased.
        // this is used to prevent two of the recursion calles to calculate the same a and b parameters in inside calls
        if (onlyIncreaseB) {
            validSolutionsCount += solutions(num, a, b + 1, true);
        } else {
            validSolutionsCount += solutions(num, a + 1, b, false) + solutions(num, a, b + 1, true);
        }

        return validSolutionsCount;
    }


    /**
     * Question number 4
     * Counts the amount of "true regions" - a group of adjacent places in the matrix (diagonal not included) with true
     * values.
     *
     * @param mat a matrix containing true and false values.
     * @return number of "true regions" in the matrix
     */
    public static int cntTrueReg (boolean[][]mat) {
        // overloading which starts at the left top corner and recursivly "iterates" through the matrix counting "true regions":
        return cntTrueReg(mat, 0, 0);
    }

    private static int cntTrueReg (boolean[][]mat, int rowIndex, int columnIndex) {
        if (columnIndex == mat.length) { // we reached the matrix end column-wise so let's jump to the next row left end
            columnIndex = 0;
            rowIndex += 1;
        }

        if (rowIndex == mat.length) { // we reached the matrix end row-wise (it's the end of the matrix, because if we
            // changed the rowIndex this means that we entered the if before wich indicated we reaced the end of the
            // previous row)
            return 0;
        }

        int regionCount;
        if (mat[rowIndex][columnIndex]) {
            // recursivly change the associated true region to be false so we won't count them again:
            changeNeighbors(mat, rowIndex, columnIndex);
            regionCount = 1;
        } else {
            regionCount = 0;
        }

        return regionCount + cntTrueReg(mat, rowIndex, columnIndex + 1); // step to the next (right) column in the row
    }

    private static void changeNeighbors (boolean[][]mat, int rowIndex, int columnIndex) {
        // recursivly change the adjacent places in the matrix (diagonal not included) if they contain true values:
        // this way once we enter this function we recursivly change all the true region.
        int leftRowIndex = rowIndex - 1;
        int rightRowIndex = rowIndex + 1;

        if (leftRowIndex >= 0 && mat[leftRowIndex][columnIndex]) {
            mat[leftRowIndex][columnIndex] = false;
            changeNeighbors(mat, leftRowIndex, columnIndex);
        }

        if (rightRowIndex < mat.length && mat[rightRowIndex][columnIndex]){
            mat[rightRowIndex][columnIndex] = false;
            changeNeighbors(mat, rightRowIndex, columnIndex);
        }

        int aboveColumnIndex = columnIndex - 1;
        int belowColumnIndex = columnIndex + 1;

        if (aboveColumnIndex >= 0 && mat[rowIndex][aboveColumnIndex]){
            mat[rowIndex][aboveColumnIndex] = false;
            changeNeighbors(mat, rowIndex, aboveColumnIndex);
        }

        if (belowColumnIndex < mat.length && mat[rowIndex][belowColumnIndex]){
            mat[rowIndex][belowColumnIndex] = false;
            changeNeighbors(mat, rowIndex, belowColumnIndex);
        }
    }
}