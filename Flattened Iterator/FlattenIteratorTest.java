import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlattenIteratorTest {
    @Test
    public void testGenericCase() {
        List<Integer> listA = List.of(1, 2, 3);
        List<Integer> listB = List.of(4, 5);
        List<Integer> listC = List.of(6, 7, 8);
        FlattenedIterator it = new FlattenedIterator(List.of(
                listA.iterator(), listB.iterator(), listC.iterator()));
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next());
        assertEquals(Integer.valueOf(4), it.next());
        assertEquals(Integer.valueOf(6), it.next());
        assertEquals(Integer.valueOf(2), it.next());
        assertEquals(Integer.valueOf(5), it.next());
        assertEquals(Integer.valueOf(7), it.next());
        assertEquals(Integer.valueOf(3), it.next());
        assertEquals(Integer.valueOf(8), it.next());
        assertNull(it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void testNoIterators() {
        FlattenedIterator it = new FlattenedIterator(List.of());
        assertFalse(it.hasNext());
        assertNull(it.next());
    }

    @Test
    public void testEmptyIterators() {
        List<Integer> listA = List.of(1, 2);
        List<Integer> listB = List.of();
        FlattenedIterator it = new FlattenedIterator(List.of(listA.iterator(), listB.iterator()));
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next());
        assertEquals(Integer.valueOf(2), it.next());
        assertFalse(it.hasNext());
        assertNull(it.next());
    }
}
