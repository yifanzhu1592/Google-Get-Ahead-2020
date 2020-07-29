import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

class TreeTest {
    @Test
    public void testLongestPath_SingleNode() {
        Tree tree = new Tree(1, List.of());
        assertEquals(1, tree.longestPath());
    }

    @Test
    public void testLongestPath_PatStartsInRoot() {
        Tree tree = new Tree(1, List.of(
                new Tree(2, List.of(new Tree(4, List.of()))),
                new Tree(3, List.of())));
        assertEquals(2, tree.longestPath());
    }

    @Test
    public void testLongestPath_PatStartsInChild() {
        Tree tree = new Tree(5, List.of(
                new Tree(6, List.of()),
                new Tree(7, List.of(
                        new Tree(8, List.of(
                                new Tree(9, List.of(
                                        new Tree(15, List.of()),
                                        new Tree(10, List.of()))))),
                        new Tree(12, List.of())))));
        assertEquals(4, tree.longestPath());
    }

}
