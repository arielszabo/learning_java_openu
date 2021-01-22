
/**
 * This Class represents a very big number as a linked list of digits.
 * This Class provides some basic addition, subtraction, multiply and comparison methods.
 *
 * Author: Ariel Szabo
 * Version : 20/01/2021
 */


public class BigNumber {

    private IntNode _head;

    /**
     * Empty constructor for the BigNumber object.
     * The default number is 0.
     *
     * The time-complexity and space-complexity of this method is O(1) beacuse only add one node to the linked-list.
    */
    public BigNumber() {
        this._head = new IntNode(0);
    }

    /**
     * Constructor for BigNumber object from a long number.
     *
     * The time-complexity of this method is O(1) (see (*) for detail) beacuse we loop over the input number and at each iteration we
     * remove the right digit in the number and adding it to a node in the BigNumber linked-list.
     * The space-complexity of this method is O(1) (see (*) for detail) because we store each digit in the input number as a different node.
     *
     * (*) Because we input a number of type long the complexity should be O(1) because long numbers have a limit of 19
     * digits - so the complexity does not really depends on the input size, it's fixed with the Upper bound of 19.
     *
     * In this method we only loop once over the number.
     *
     * @param number a big number to represent in a linked-list of integers
     */
    public BigNumber(long number) {
        IntNode prevNode = _head;
        while (number > 0) {
            int rightDigit = (int)(number % 10);

            IntNode rightDigitNode = new IntNode(rightDigit);

            // adds the new node to be the next node of to the end node and change the end node to point to the new
            // latest node. if no head node is available this new node needs to be the head and the end node.
            if (_head == null) {
                _head = rightDigitNode;
                prevNode = _head;
            } else {
                prevNode.setNext(rightDigitNode);
                prevNode = rightDigitNode;
            }
            number = number / 10;  // remove the right digit from number
        }
    }

    /**
     * A Copy Constructor for BigNumber object from another BigNumber.
     *
     * The time-complexity of this method is O(n) beacuse we loop over the input BigNumber and at each iteration we
     * set the other number's node value to a new node (to avoid aliassing) and add that new node to out BigNumber
     * object.
     *
     * The space-complexity of this method is O(n) because we store each node in the input BigNumber as a different new
     * node.
     *
     * In this method we only loop once over the number.
     *
     * @param other
     */
    public BigNumber(BigNumber other) {
        _head = null;
        IntNode prevNode = null;

        IntNode otherNode = other._head;
        while (otherNode != null) {
            IntNode newNode = new IntNode(otherNode.getValue());

            // adds the new node to be the next node of to the end node and change the end node to point to the new
            // latest node. if no head node is available this new node needs to be the head and the end node.
             if (_head == null) {
                _head = newNode;
                prevNode = _head;
            } else {
                prevNode.setNext(newNode);
                prevNode = newNode;
            }

            otherNode = otherNode.getNext();
        }
    }

    /**
     * Creates a string representaion of the BigNumber.
     *
     * The time-complexity of this method is O(n) beacuse we recurse down each node in the bigNumber object.
     * The space-complexity of this method is O(n) because we store each node's string representation as part of our
     * recursion tree.
     *
     * In this method we loop twice over the number - once going down in the recursion tree and once when we return from
     * each recursion back to the top.
     *
     * @return string representaion of the BigNumber
     */
    public String toString() {
        return toString(_head);
    }

    private String toString(IntNode node) {
        if (node == null)
            return "";

        IntNode nextNode = node.getNext();
        return toString(nextNode) + "" + node.getValue(); // adding the empty string to convert the node integer value to string
    }

    /**
     * Computes the relationship of this BigNumber and other BigNumber.
     *
     * The time-complexity of this method is O(n) (where n is the biggest number lenth) beacuse we loop over each node
     * in both big numbers and compute their relationship to one and other.
     * The space-complexity of this method is O(1) because we only store boolean variables regardless if the input size.
     *
     * In this method we only loop once over the number.
     *
     * @param other
     * @return 0 if both numbers are equal 1 if this is bigger and -1 if other is bigger.
     */
    public int compareTo (BigNumber other) {

        // This method keeps track (with a boolean variable )for each big number (this and other) whether the last digit
        // which is not equal is bigger (for each big number separately)


        // default value of false which will change in the following while loop:
        boolean isThisDigitBigger = false;
        boolean isOtherDigitBigger = false;

        IntNode thisNode = _head;
        IntNode otherNode = other._head;


        while (true){
            if (thisNode == null && otherNode == null){ // both numbers reached their end.
                break;
            }
            if (thisNode == null){ // this node ended but the other node have elements in it
                return -1;
            }

            if (otherNode == null) { // the other node ended but this node have elements in it
                return 1;
            }

            if (thisNode.getValue() > otherNode.getValue()) {
                isThisDigitBigger = true;
                isOtherDigitBigger = false;

            } else if (thisNode.getValue() < otherNode.getValue()) {
                isThisDigitBigger = false;
                isOtherDigitBigger = true;

            }

            thisNode = thisNode.getNext();
            otherNode = otherNode.getNext();

        }

        if (isThisDigitBigger) {
            return 1;
        } else if (isOtherDigitBigger) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Computes the sum of this number and the other input BigNumber and returns a new BigNumber object.
     *
     *
     * The time and space complexity of this method is O(n) beacuse we loop over the input BigNumber and at each
     * iteration we add the other number's node value to this number's node value and store it in a new node.
     *
     * In this method we only loop once over the number.
     *
     *
     * @param other another BigNumber to add to this.
     * @return a bigNumber which is the sum of this number and the other input BigNumber
     */
    public BigNumber addBigNumber (BigNumber other) {
        // We'll loop over the input BigNumber's nodes and add it to the corresponding node in this big number and
        // create a new node while keeping an overflow variable which will store the value to be added to the next node
        // (the addition result without it's right digit).
        // This overflow variable can't be bigger than 1 beacuse in the worst case when adding two 9 digits the result
        // is 18 and the overflow is 1.
        // If one of the big numbers have reached thier end we'll keep adding zeros instead until both will end.

        IntNode newHead = null;
        IntNode prevNode = null;

        int overflow = 0;
        IntNode thisCurrent = this._head;
        IntNode otherCurrent = other._head;
        while (true) {

            int thisCurrentValue;
            if (thisCurrent == null) {
                thisCurrentValue = 0;
            } else {
                thisCurrentValue = thisCurrent.getValue();
                thisCurrent = thisCurrent.getNext();
            }

            int otherCurrentValue;
            if (otherCurrent == null) {
                otherCurrentValue = 0;
            } else {
                otherCurrentValue = otherCurrent.getValue();
                otherCurrent = otherCurrent.getNext();
            }

            int newNumber = thisCurrentValue + otherCurrentValue + overflow;
            if (newNumber == 0 & thisCurrent == null & otherCurrent == null) { // reached the end of both numbers
                break;
            }

            if (newNumber > 9) {
                overflow = newNumber / 10;
                newNumber = newNumber % 10;
            } else {
                overflow = 0;
            }

            // adds the new node to be the next node of to the end node and change the end node to point to the new
            // latest node. if no head node is available this new node needs to be the head and the end node.
            IntNode node = new IntNode(newNumber);
            if (newHead == null) {
                newHead = node;
                prevNode = newHead;
            } else {
                prevNode.setNext(node);
                prevNode = node;
            }


        }
        BigNumber newBigNumber = getBigNumberFromHead(newHead);
        return newBigNumber;
    }

    /**
     * Computes the sum of this number and the other input long number and returns a new BigNumber object.
     *
     *
     * The time and space complexity of this method is O(n) beacuse we loop over this number's nodes at each iteration
     * we add the right digit value to this number's node value and store it in a new node (we also remove the right
     * digit form the number).
     * if we reach the end of the input long number (long typed numbers have max of 19 digits) we keep adding zeros and
     * this is why time-complexity is O(n).
     *
     * In this method we only loop once over the number.
     *
     *
     * @param num another number to add to this.
     * @return a bigNumber which is the sum of this number and the other input long number
     */
    public BigNumber addLong (long num) {
        // In order to avoid looping twice on the number we won't use the existing contractor and method.
        // We'll loop over the input number and extract the right digit using modulo and remove it from the number by
        // dividing the number by 10.
        // each number will be added to the corresponding node and create a new node while keeping an overflow variable
        // which will store the value to be added to the next node (the addition result without it's right digit).
        // This overflow variable can't be bigger than 1 beacuse in the worst case when adding two 9 digits the result
        // is 18 and the overflow is 1.
        // If either the number have reached the end (remains 0 after dividion) or this big number have reached the end
        // we'll keep adding zeros instead until both number and big number will reach their end.

        IntNode newHead = null;
        IntNode prevNode = newHead;

        int overflow = 0;
        IntNode thisCurrent = this._head;
        while (true) {

            int thisCurrentValue;
            if (thisCurrent == null) {
                thisCurrentValue = 0;
            } else {
                thisCurrentValue = thisCurrent.getValue();
                thisCurrent = thisCurrent.getNext();
            }

            int otherCurrentValue = (int)(num % 10);
            num = num / 10;

            int newNumber = thisCurrentValue + otherCurrentValue + overflow;
            if (newNumber == 0 & thisCurrent == null & num == 0) { // reached the end of both numbers
                break;
            }

            if (newNumber > 9) {
                overflow = newNumber / 10;
                newNumber = newNumber % 10;
            } else {
                overflow = 0;
            }

            // adds the new node to be the next node of to the end node and change the end node to point to the new
            // latest node. if no head node is available this new node needs to be the head and the end node.
            IntNode node = new IntNode(newNumber);
            if (newHead == null) {
                newHead = node;
                prevNode = newHead;
            } else {
                prevNode.setNext(node);
                prevNode = node;
            }


        }
        BigNumber newBigNumber = getBigNumberFromHead(newHead);
        return newBigNumber;
    }

    /**
     * Computes the absolute value of subtraction of this number and another BigNumber.
     *
     *
     * The time and space complexity of this method is O(n) beacuse we loop over the input BigNumber and at each
     * iteration we subtract the other number's node value to this number's node value and store it in a new node.
     *
     * In this method we only loop once over the number.
     *
     *
     * @param other another big number to use in the subtraction.
     * @return The absolute value of subtraction of this number and another BigNumber.
     */
    public BigNumber subtractBigNumber (BigNumber other) {
        // We'll start by determining which big number is bigger.

        // We'll loop over all nodes in the bigger number and subtract the corresponding place in the smaller number.
        // If the subtraction results in a number smaller than 0 we will "borrow" a number from the next bigger big
        // number node by adding an overflow variable which will be used in the next iteration, meanwhile in this
        // iteration we will keep insted of the subtraction results the subtraction results after adding 10 to it (the
        // "borrowed" number).

        // In case the subtraction results in a zero we won't add it to the new number (we will save a new linked list
        // which will track those zersos) until we will reach a subtraction result which is deifferent than 0 and than
        // we'll add the zeros we saved before the new subtraction result. This way we avoid adding trailing zeros to
        // the end of the linked list withoud a reason.


        int thisCompareToResult = this.compareTo(other);
        BigNumber biggerNumber;
        BigNumber smallerNumber;
        if (thisCompareToResult == 1) {

            biggerNumber = this;
            smallerNumber = other;

        } else if (thisCompareToResult == -1) {

            biggerNumber = other;
            smallerNumber = this;

        } else {
            // the numbers are equal so we'll initialize a BigNumber with 0 (the default constractor):
            return new BigNumber();
        }

        IntNode zeroNodesHead = null;
        IntNode lastZeroNode = null;

        IntNode newHead = null;
        IntNode prevNode = null;

        IntNode biggerNumberNode = biggerNumber._head;
        IntNode smallerNumberNode = smallerNumber._head;
        int overflow = 0;
        while (biggerNumberNode != null) {

            int biggerNumberNodeValue = biggerNumberNode.getValue();
            biggerNumberNode = biggerNumberNode.getNext();

            int smallerNumberNodeValue;
            if (smallerNumberNode == null) {
                smallerNumberNodeValue = 0;
            } else {
                smallerNumberNodeValue = smallerNumberNode.getValue();
                smallerNumberNode = smallerNumberNode.getNext();
            }

            int diff = biggerNumberNodeValue - smallerNumberNodeValue - overflow;

            if (diff == 0 & biggerNumberNode == null & smallerNumberNode == null) { // reached the end of both numbers
                break;
            }

            if (diff == 0){
                // start a new linked list whihc stores the zeros untill they are needed (if they are needed)
                if (zeroNodesHead == null){
                    zeroNodesHead = new IntNode(0);
                    lastZeroNode = zeroNodesHead;
                } else {
                    IntNode newZeroNode = new IntNode(0);
                    lastZeroNode.setNext(newZeroNode);
                    lastZeroNode = newZeroNode;
                }
                overflow = 0;
                continue;
            }

            if (diff > 0){
                overflow = 0;
            } else {
                // "borrow" a 10 from the next biggerNumber node
                diff = 10 + diff;
                overflow = 1;
            }


            IntNode node = new IntNode(diff);

            if (newHead == null && lastZeroNode != null) {
                lastZeroNode.setNext(node);  // add the zeros before the number

                newHead = zeroNodesHead;
                prevNode = node;

                // reset the zeros variables to be empty
                zeroNodesHead = null;
                lastZeroNode = null;

            } else if (newHead != null && lastZeroNode != null) {
                lastZeroNode.setNext(node);  // add the zeros before the number

                prevNode.setNext(zeroNodesHead);
                prevNode = node;

                // reset the zeros variables to be empty
                zeroNodesHead = null;
                lastZeroNode = null;

            // adds the new node to be the next node of to the end node and change the end node to point to the new
            // latest node. if no head node is available this new node needs to be the head and the end node.
            } else if (newHead == null) {
                newHead = node;
                prevNode = newHead;
            } else {
                prevNode.setNext(node);
                prevNode = node;
            }
        }

        BigNumber newBigNumber = getBigNumberFromHead(newHead);
        return newBigNumber;
    }

    /**
     * Multiples this big number with another big number.
     *
     * This method's time-complexity is O(n * m) because for each node in the other big number (n digits) we loop over
     * the m digits of this big number and compute the multiplication.
     *
     * This method's space-complexity is O(n + m) beacuse we store the multiplication of the two big numbers and the
     * multiplication of two numbers can't be bigger than their sum of digits.
     *
     * In this method we only loop  n * m times over the number.
     *
     * @param other
     * @return BigNumber representing this number and another BigNumber multiplication result.
     */
    public BigNumber multBigNumber (BigNumber other) {
        if ( this.isNumberEqualsZero() || other.isNumberEqualsZero()) {
            // this only checks the head of each object so it doesn't add additional complexity.
            return new BigNumber();
        }

        // For each node in the other big number we iterate over all this' nodes and compute the multiplication.
        // simmilarly to the addition methods we keep an overflow variable where it's max value can be 8
        // ( (int)(9 * 9 / 10) = 8 ).
        // We also keep a linked list of zeros which we use to add zero paddings in the inner loop.
        // This method is inspired by the vertical multiplication technique.

        IntNode otherNode = other._head;
        IntNode resultHead = new IntNode(0);
        int overflow = 0;
        IntNode zeroNode = null;

        while (otherNode != null) {
            IntNode thisNode = this._head;
            IntNode result = resultHead;
            if (zeroNode != null) { // zeroNode is only null in the first iteration when there is no need to add prefix zeros
                IntNode tempZeroNode = new IntNode(zeroNode.getValue(), zeroNode.getNext()); // duplicate this to allow iterating without changin the original variable for next iterations
                while (tempZeroNode != null) { // loop the amount of zeros to add to the number as prefixes
                    tempZeroNode = tempZeroNode.getNext();
                    result = result.getNext();
                }
            }

            while (thisNode != null) {
                int multiResult = thisNode.getValue() * otherNode.getValue();
                int existingValue = result.getValue();
                int exitingAndMultiResult = existingValue + multiResult + overflow;
                overflow = exitingAndMultiResult / 10;
                result.setValue(exitingAndMultiResult % 10);

                if (
                        result.getNext() == null &&
                        thisNode.getNext() == null &&
                        otherNode.getNext() == null &&
                        overflow == 0
                ){
                    // if we reaced this it means that the next iteration will end our calculation and we don't have
                    // anything else to add to the number (overflow is 0).
                    // In that situation we would like to break from the loop without adding tnything to the result end.
                    break;
                }
                if (result.getNext() == null){ // add the overflow as a new node at the end of result if not exists.
                    result.setNext(new IntNode(overflow));
                    overflow = 0;
                }

                result = result.getNext();
                thisNode = thisNode.getNext();
            }
            zeroNode = new IntNode(0, zeroNode);
            otherNode = otherNode.getNext();
        }

        BigNumber newBigNumber = getBigNumberFromHead(resultHead);
        return newBigNumber;
    }

    private BigNumber getBigNumberFromHead(IntNode headNode) {
        // bulids a BigNumber object from the head given (if it's null returns the default contractor)
        BigNumber bigNumber = new BigNumber();
        if (headNode == null)
            return bigNumber;
        bigNumber._head.setValue(headNode.getValue());
        bigNumber._head.setNext(headNode.getNext());
        return bigNumber;
    }

    private boolean isNumberEqualsZero() {
        if (_head == null) {
            return true;
        }
        if (_head.getNext() == null &&_head.getValue() == 0){
            return true;
        }

        return false;
    }

}