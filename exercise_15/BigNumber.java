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
        if (this.length() < other.length()){
            return -1;
        } else if (this.length() > other.length()) {
            return 1;
        }


        // flip the BigNumber and iterate and compare

        // current
        // prev = 9
        // temp = 8
        // next = 7
        // 8 - 9

        // go to the end
        // go backwords and compare

        // this  1234567891234
        // other 1234657583930

        // this_to_other_diff = 4 - 700

        return 0;
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
            }

            int otherCurrentValue;
            if (otherCurrent == null) {
                otherCurrentValue = 0;
            } else {
                otherCurrentValue = otherCurrent.getValue();
            }

            int newNumber = thisCurrentValue + otherCurrentValue + reminder;
            if (newNumber == 0) {
                break;
            }

            if (newNumber > 9) {
                reminder = 1;
                newNumber = newNumber % 10;
            } else {
                reminder = 0;
            }
            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();

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
        // create a BigNumber
        return null;
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