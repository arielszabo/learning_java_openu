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
        _head = new IntNode(other._head.getValue(), other._head.getNext());
    }

    public String toString() {
        return toString(_head);
    }

    private String toString(IntNode node) {
        IntNode nextNode = node.getNext();
        if (nextNode == null) {
            return "" + node.getValue();  // adding the empty string to convert the node integer value to string
        }
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
        BigNumber newBigNumber = new BigNumber();
        newBigNumber._head.setValue(newHead.getValue());
        newBigNumber._head.setNext(newHead.getNext());
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
        return null;
    }

    public BigNumber multBigNumber (BigNumber other) {
        return null;
    }

    private int length() {
        int counter = 0;
        IntNode temp = _head;
        while (temp != null) {
            temp = temp.getNext();
            counter++;
        }
        return counter;
    }
}