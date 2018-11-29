package pitexample;

import org.junit.Test;
import static org.junit.Assert.*;

public class UOITests {

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