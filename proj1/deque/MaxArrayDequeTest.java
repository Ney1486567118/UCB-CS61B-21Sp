package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {
    @Test
    public void maxNumTest() {
        MaxArrayDeque<Integer> maDeque = new MaxArrayDeque<>((a, b) -> a.compareTo(b));

        maDeque.addFirst(1);
        maDeque.addFirst(4);
        maDeque.addFirst(2);
        maDeque.addFirst(5);
        maDeque.addFirst(3);
        assertEquals((Integer) 5, maDeque.max());
    }
}
