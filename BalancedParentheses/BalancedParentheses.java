import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Stack;

public class BalancedParentheses {
    public static int longestBalanced(String str) {
        int longest = 0;
        Stack<Integer> unmatchedOpeningBrackets = new Stack<>();
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == '(') {
                unmatchedOpeningBrackets.push(i);
            } else {
                if (unmatchedOpeningBrackets.isEmpty()) {
                    // No unmatched opening bracket, Ignore closing bracket.
                } else {
                    int open_i = unmatchedOpeningBrackets.pop();
                    int length = i - open_i + 1;
                    longest = Math.max(longest, length);
                }
            }
        }
        return longest;
    }

    @Test
    public void testLongestBalanced() {
        assertEquals(0, longestBalanced(""));
        assertEquals(0, longestBalanced("("));
        assertEquals(0, longestBalanced(")"));
        assertEquals(2, longestBalanced("()"));
        assertEquals(4, longestBalanced("(())"));
        assertEquals(4, longestBalanced("())(())"));
        assertEquals(4, longestBalanced(")(()))))(((()"));
    }
}
