import org.junit.Test;

import static org.junit.Assert.*;

public class NonNegativeIntegerTest {
    @Test
    public void getTest() {
        NonNegativeInteger nni = new NonNegativeInteger();
        NonNegativeInteger nni2 = new NonNegativeInteger();

        assertEquals(nni.get(), 0);

        nni.set(5);
        assertEquals(nni.get(), 5);

        try {
            nni2.set(-1);
        } catch (IllegalArgumentException e) {
            assertEquals(nni2.get(), 0);
            nni2.set(5);
            assertEquals(nni2.get(), 5);
        }
    }

    @Test
    public void setTest() {
        NonNegativeInteger nni = new NonNegativeInteger();

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
        NonNegativeInteger nni = new NonNegativeInteger();
        NonNegativeInteger nni2 = new NonNegativeInteger();

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