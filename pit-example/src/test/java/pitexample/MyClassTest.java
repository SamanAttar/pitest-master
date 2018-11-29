package pitexample;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyClassTest {

    @Test
    public void testMe() {
        MyClass sut = new MyClass();
        assertTrue(sut.myMethod(1));
        assertTrue(sut.myMethod(2));
    }

    @Test
    public void testMe2() {
        MyClass sut = new MyClass();
        assertTrue(sut.myMethod(1));
        assertTrue(sut.myMethod(2));
        assertFalse(sut.myMethod(0));
    }
}