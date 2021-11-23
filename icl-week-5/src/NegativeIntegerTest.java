import org.junit.Test;

import static org.junit.Assert.*;

public class NegativeIntegerTest {
    @Test
    public void getTest() {
        NegativeInteger nni = new NegativeInteger();
        NegativeInteger nni2 = new NegativeInteger();

        assertEquals(nni.get(), -1);

        nni.set(-5);
        assertEquals(nni.get(), -5);

        try {
            nni2.set(4);
        } catch (IllegalArgumentException e) {
            assertEquals(nni2.get(), -1);
            nni2.set(-5);
            assertEquals(nni2.get(), -5);
        }
    }

    @Test
    public void setTest() {
        NegativeInteger nni = new NegativeInteger();

        try {
            nni.set(-1);
        } catch (IllegalArgumentException e) {
            nni.set(5);
            assertEquals(nni.get(), 5);
            nni.set(25);
            assertEquals(nni.get(), 25);
        }
    }

    @Test
    public void equalsTest() {
        NegativeInteger nni = new NegativeInteger();
        NegativeInteger nni2 = new NegativeInteger();

        assertTrue(nni.equals(nni2));
        assertTrue(nni2.equals(nni));

        nni.set(5);
        nni2.set(5);

        assertTrue(nni.equals(nni2));
        assertTrue(nni2.equals(nni));

        nni.set(3);
        nni2.set(25);

        assertFalse(nni.equals(nni2));
        assertFalse(nni2.equals(nni));
    }
}