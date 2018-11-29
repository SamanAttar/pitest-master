package pitexample;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyClassTest {

    @Test
    public void testMe() {
        MyClass sut = new MyClass();
        assertTrue(sut.myMethod(1, true));
        assertTrue(sut.myMethod(2, true));
    }

    @Test
    public void testMe2() {
        MyClass sut = new MyClass();
        assertTrue(sut.myMethod(1, false));
        assertTrue(sut.myMethod(2, false));
        assertFalse(sut.myMethod(0,false));
    }

    @Test
    public void ABSTest1() {
        MyClass sut = new MyClass();
        assertEquals(sut.negateVariable(1), -1);
        assertEquals(sut.negateVariable(2), -2);
    }

    @Test
    public void ABSTest2() {
        MyClass sut = new MyClass();
        assertEquals(sut.negateVariable(-1), 1);
        assertEquals(sut.negateVariable(-2), 2);
    }

    @Test
    public void OBBNTest1() {
        MyClass sut = new MyClass();
        assertTrue(sut.bitWiseOr(true, true));
        assertTrue(sut.bitWiseOr(true, false));
        assertTrue(sut.bitWiseOr(false, true));
        assertFalse(sut.bitWiseOr(false, false));
    }

    @Test
    public void OBBNTest2() {
        MyClass sut = new MyClass();
        assertTrue(sut.bitWiseAnd(true, true));
        assertFalse(sut.bitWiseAnd(true, false));
        assertFalse(sut.bitWiseAnd(false, true));
        assertFalse(sut.bitWiseAnd(false, false));
    }
}