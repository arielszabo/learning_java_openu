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

        BigNumber b5 = new BigNumber(900000009L);
        BigNumber b6 = new BigNumber(123456789L);
        assertEquals(1, b5.compareTo(b6));
        assertEquals(-1, b6.compareTo(b5));

        BigNumber b7 = new BigNumber(123496789L);
        assertEquals(1, b7.compareTo(b6));

        BigNumber b8 = new BigNumber(123496788L);
        assertEquals(1, b7.compareTo(b8));

        BigNumber b9 = new BigNumber(223496788L);
        assertEquals(1, b9.compareTo(b8));

        BigNumber b10 = new BigNumber(223496788L);
        assertEquals(0, b10.compareTo(b9));


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

        BigNumber b5 = new BigNumber(100000087L);
        assertEquals("23456702", b5.subtractBigNumber(b2).toString());


        BigNumber b6 = new BigNumber(32L);
        BigNumber b7 = new BigNumber(27L);
        assertEquals("5", b6.subtractBigNumber(b7).toString());


        BigNumber b8 = new BigNumber(1000032L);
        BigNumber b9 = new BigNumber(27L);
        assertEquals("1000005", b8.subtractBigNumber(b9).toString());
        
        
        BigNumber b10 = new BigNumber(3005L);
        BigNumber b11 = new BigNumber(3003L);
        assertEquals("2", b11.subtractBigNumber(b10).toString());
        assertEquals("0", b11.subtractBigNumber(b11).toString());
    }

    @Test
    public void multBigNumber() {
        BigNumber b1 = new BigNumber();
        BigNumber b2 = new BigNumber(123456789L);
        assertEquals("0", b2.multBigNumber(b1).toString());
        assertEquals("0", b1.multBigNumber(b2).toString());

        BigNumber b3 = new BigNumber(1087L);
        assertEquals("134197529643", b3.multBigNumber(b2).toString());

        BigNumber bigNumber1 = new BigNumber(150);
        BigNumber bigNumber2 = new BigNumber(10);
        assertEquals("1500", bigNumber1.multBigNumber(bigNumber2).toString());

        BigNumber bigNumber3 = new BigNumber(5879);
        assertEquals("8818500", bigNumber3.multBigNumber(bigNumber2.multBigNumber(bigNumber1)).toString());

        BigNumber bigNumber4 = new BigNumber(1);
        BigNumber bigNumber5 = new BigNumber(10879);
        assertEquals("10879", bigNumber4.multBigNumber(bigNumber5).toString());

        BigNumber bigNumber6 = new BigNumber(0);
        BigNumber bigNumber7 = new BigNumber(10879);
        assertEquals("0", bigNumber7.multBigNumber(bigNumber6).toString());
        assertEquals("0", bigNumber6.multBigNumber(bigNumber7).toString());

        BigNumber bigNumber8 = new BigNumber(8912379123L);
        assertEquals("96957772479117", bigNumber8.multBigNumber(bigNumber7).toString());

        BigNumber bigNumber9 = new BigNumber(0);
        BigNumber bigNumber10 = new BigNumber(0);
        assertEquals("0", bigNumber10.multBigNumber(bigNumber9).toString());

        BigNumber bigNumber11 = new BigNumber(2);
        BigNumber bigNumber12 = new BigNumber(4);
        assertEquals(0, bigNumber11.multBigNumber(bigNumber11).compareTo(bigNumber12));

        bigNumber11 = bigNumber11.addBigNumber(new BigNumber(bigNumber11));
        bigNumber12 = bigNumber12.multBigNumber(bigNumber12);
        assertEquals(0, bigNumber11.multBigNumber(bigNumber11).compareTo(bigNumber12));

        bigNumber11 = bigNumber11.addBigNumber(new BigNumber(bigNumber11));
        assertEquals(1, bigNumber11.multBigNumber(bigNumber11).compareTo(bigNumber12));

        BigNumber bigNumber13 = new BigNumber(999);
        BigNumber bigNumber14 = new BigNumber(1003);
        assertEquals("1001997", bigNumber13.multBigNumber(bigNumber14).toString());

    }


    //  123
    //   11
    //  ___
    //  123
    // 1230
}
