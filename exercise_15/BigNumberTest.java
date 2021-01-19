import org.junit.Test;
import static org.junit.Assert.*;

public class BigNumberTest {

    @Test
    public void toStringOfBigNumber() {

        BigNumber b1 = new BigNumber(1234567895432L);
        assertEquals("1234567895432", b1.toString());

        BigNumber b2 = new BigNumber(123L);
        assertEquals("123", b2.toString());

        BigNumber b3 = new BigNumber();
        assertEquals("0", b3.toString());

        BigNumber b4 = new BigNumber(b1);
        assertEquals("1234567895432", b4.toString());

    }

    @Test
    public void compareTo() {
        BigNumber b1 = new BigNumber();
        assertEquals(0, b1.compareTo(b1));

        BigNumber b2 = new BigNumber(1234567895432L);
        assertEquals(-1, b1.compareTo(b2));
        assertEquals(1, b2.compareTo(b1));

        BigNumber b3 = new BigNumber(1000000009L);
        assertEquals(-1, b3.compareTo(b2));

        BigNumber b4 = new BigNumber(234567895432L);
        assertEquals(-1, b4.compareTo(b2));
    }

    @Test
    public void addBigNumber() {
        BigNumber b1 = new BigNumber();
        BigNumber b2 = b1.addBigNumber(new BigNumber(123456789L));
        assertEquals("123456789", b2.toString());

        BigNumber b3 = b2.addBigNumber(new BigNumber(123456789L));
        assertEquals("246913578", b3.toString());

        BigNumber b4 = b3.addBigNumber(new BigNumber(946913578L));
        assertEquals("1193827156", b4.toString());

        BigNumber b5 = b4.addBigNumber(new BigNumber(100000000000L));
        assertEquals("101193827156", b5.toString());

        BigNumber b6 = b5.addBigNumber(new BigNumber());
        assertEquals("101193827156", b6.toString());

        BigNumber b7 = b6.addBigNumber(new BigNumber(b6));
        assertEquals("202387654312", b7.toString());
    }

    @Test
    public void addLong() {
        BigNumber b1 = new BigNumber();
        BigNumber b2 = b1.addLong(123456789L);
        assertEquals("123456789", b2.toString());

        BigNumber b3 = b2.addLong(123456789L);
        assertEquals("246913578", b3.toString());

        BigNumber b4 = b3.addLong(946913578L);
        assertEquals("1193827156", b4.toString());

        BigNumber b5 = b4.addLong(100000000000L);
        assertEquals("101193827156", b5.toString());

    }

    @Test
    public void subtractBigNumber() {
        BigNumber b1 = new BigNumber();
        BigNumber b2 = new BigNumber(123456789L);
        assertEquals("123456789", b2.subtractBigNumber(b1).toString());
        assertEquals("123456789", b1.subtractBigNumber(b2).toString());


        BigNumber b3 = new BigNumber(1023456789L);
        assertEquals("900000000", b3.subtractBigNumber(b2).toString());

        BigNumber b4 = new BigNumber(10289L);
        assertEquals("123446500", b4.subtractBigNumber(b2).toString());

        BigNumber b4 = new BigNumber(100000087L);
        assertEquals("23456702", b4.subtractBigNumber(b2).toString());
    }

    @Test
    public void multBigNumber() {
        BigNumber b1 = new BigNumber();
        BigNumber b2 = new BigNumber(123456789L);
        assertEquals("0", b2.multBigNumber(b1).toString());
        assertEquals("0", b1.multBigNumber(b2).toString());

        BigNumber b3 = new BigNumber(1000087L);
        assertEquals("123467530000000", b3.multBigNumber(b2).toString());


    }

}
