package pitexample;

import org.junit.Test;
import static org.junit.Assert.*;

public class OBBNTests {

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

    @Test
    public void UOITest1() {
        MyClass sut = new MyClass();
        assertEquals(sut.incrementByOne(1) ,2 );
        assertEquals(sut.incrementByOne(2) ,3 );
        assertEquals(sut.incrementByOne(3) ,4 );
        assertEquals(sut.incrementByOne(4) ,5 );
    }

    @Test
    public void UOITest2() {
        MyClass sut = new MyClass();
        assertEquals(sut.decrementByOne(1) ,0 );
        assertEquals(sut.decrementByOne(2) ,1 );
        assertEquals(sut.decrementByOne(3) ,2 );
        assertEquals(sut.decrementByOne(4) ,3 );
    }
}