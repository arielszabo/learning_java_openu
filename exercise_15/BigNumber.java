/**
 *
 */


public class BigNumber {

    private IntNode _head;

    public BigNumber() {
        _head = new IntNode(0);
    }

    public BigNumber(long number) {
        IntNode prevNode = _head;
        while (number > 0) {
            int rightDigit = (int)(number % 10); // TODO: change to ahadot

            IntNode rightDigitNode = new IntNode(rightDigit);

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

    public BigNumber(BigNumber other) {
        _head = null;
        IntNode prevNode = null;

        IntNode otherNode = other._head;
        while (otherNode != null) {
            IntNode newNode = new IntNode(otherNode.getValue());

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

    public String toString() {
        return toString(_head);
    }

    private String toString(IntNode node) {
        if (node == null)
            return "";

        IntNode nextNode = node.getNext();
        return toString(nextNode) + "" + node.getValue(); // adding the empty string to convert the node integer value to string
    }

    public int compareTo (BigNumber other) {
        // default value of false which will change in the following while loop:
        boolean isThisDigitBigger = false;
        boolean isOtherDigitBigger = false;

        IntNode thisNode = _head;
        IntNode otherNode = other._head;

        while (true){
            if (thisNode == null && otherNode == null){
                break;
            }
            if (thisNode == null){ // this node ended but the other node have elements in it
                return -1;
            }

            if (otherNode == null) { // the other node ended but this node have elements in it
                return 1;
            }
//        while (thisNode != null){
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

    public BigNumber addBigNumber (BigNumber other) {
        // iterate and add the numbers pay attention if the numbers add to > 10
        IntNode newHead = null;
        IntNode prevNode = null; // TODO:

        int reminder = 0; // TODO: change name
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

            int newNumber = thisCurrentValue + otherCurrentValue + reminder;
            if (newNumber == 0 & thisCurrent == null & otherCurrent == null) { // reached the end of both numbers
                break;
            }

            if (newNumber > 9) {
                reminder = 1;
                newNumber = newNumber % 10;
            } else {
                reminder = 0;
            }

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

    public BigNumber addLong (long num) {
        // in order to avoid looping twice on the number we won't use the existing method and contractor

        IntNode newHead = null;
        IntNode prevNode = newHead;

        int reminder = 0; // TODO: change name
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

            int newNumber = thisCurrentValue + otherCurrentValue + reminder;
            if (newNumber == 0 & thisCurrent == null & num == 0) { // reached the end of both numbers
                break;
            }

            if (newNumber > 9) {
                reminder = 1;
                newNumber = newNumber % 10;
            } else {
                reminder = 0;
            }

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

    public BigNumber subtractBigNumber (BigNumber other) {
        // detemine who is bigger
        // loop over all nodes in the bigger number and subtract the corresponding place in the samller number
        // subtract the digits and if the value (we add the "reminder" from the last operation) is bigger/equal than 0
        // add it to the new number.
        // if the value is lower than 0 keep it (as a "reminder") and continue to the next iteration
        // when the smaller number ends just keep adding the digits from the bigger number if like the smaller number had 0as digits

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
        IntNode prevNode = null; // TODO:

        IntNode biggerNumberNode = biggerNumber._head;
        IntNode smallerNumberNode = smallerNumber._head;
        int reminder = 0; // TODO: rename
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

            int diff = biggerNumberNodeValue - smallerNumberNodeValue - reminder;

            if (diff == 0 & biggerNumberNode == null & smallerNumberNode == null) { // reached the end of both numbers
                break;
            }

            if (diff == 0){
                if (zeroNodesHead == null){
                    zeroNodesHead = new IntNode(0);  // add the first zero node
                    lastZeroNode = zeroNodesHead;
                } else {
                    IntNode newZeroNode = new IntNode(0);
                    lastZeroNode.setNext(newZeroNode); // add another zero node to the end of the zero node
                    lastZeroNode = newZeroNode;
                }
                reminder = 0;
                continue;
            }

            if (diff > 0){
                reminder = 0;
            } else {
                diff = 10 + diff; // diff is minus number  change number ...
                reminder = 1;
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

    public BigNumber multBigNumber (BigNumber other) {

        if ( this.isNumberEqualsZero() || other.isNumberEqualsZero()) {
            return new BigNumber();
        }

        // Let's denote this BigNumber length by n and other's length as m

        BigNumber newBigNumber = null;

        IntNode otherNode = other._head;
        IntNode firstAdditionalPrefixZero = null;
        IntNode lastAdditionalPrefixZero = null;
        while (otherNode != null) {  // O(m)
            IntNode bigNumberAndDigitResultHead = getBigNumberAndDigitMultiplicationHead(this, otherNode.getValue()); // O(n)
            if (firstAdditionalPrefixZero != null) {

                lastAdditionalPrefixZero.setNext(bigNumberAndDigitResultHead);

                bigNumberAndDigitResultHead = firstAdditionalPrefixZero; // add a zero .. explain

            }

            BigNumber bigNumberAndDigitResult = getBigNumberFromHead(bigNumberAndDigitResultHead);

            if (newBigNumber == null) {
                newBigNumber = bigNumberAndDigitResult;
            } else {
                newBigNumber = newBigNumber.addBigNumber(bigNumberAndDigitResult); // O(n)
            }

            if (lastAdditionalPrefixZero != null ) {
                lastAdditionalPrefixZero.setNext(null);  // remove the link and keep only the zeros
            }

            IntNode zeroNode = new IntNode(0);

            if (firstAdditionalPrefixZero == null) {
                firstAdditionalPrefixZero = zeroNode;
                lastAdditionalPrefixZero = firstAdditionalPrefixZero;
            } else {
                lastAdditionalPrefixZero.setNext(zeroNode);
                lastAdditionalPrefixZero = zeroNode;
            }
            otherNode = otherNode.getNext();
        }

        return newBigNumber;
    }

    private IntNode getBigNumberAndDigitMultiplicationHead(BigNumber bigNumber, int digit) {
        IntNode newHead = null;
        IntNode prevNode = null;

        IntNode node = bigNumber._head;
        int reminder = 0;

        while (node != null) {
            int result = node.getValue() * digit + reminder;

            if (result >= 10) {
                reminder = result / 10;
                result = result % 10;
            } else {
                reminder = 0;
            }

            IntNode newNode = new IntNode(result);
            if (newHead == null) {
                newHead = newNode;
                prevNode = newHead;
            } else {
                prevNode.setNext(newNode);
                prevNode = newNode;
            }
            node = node.getNext();
        }

        if (reminder != 0) {
            prevNode.setNext(new IntNode(reminder));
        }

        return newHead;
    }

    private BigNumber getBigNumberFromHead(IntNode headNode) {
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