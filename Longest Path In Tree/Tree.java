import java.util.Collections;
import java.util.List;

public class Tree {
    private int mValue;
    private List<Tree> mChildren;

    public Tree(int value, List<Tree> children) {
        mValue = value;
        mChildren = Collections.unmodifiableList(children);
    }

    public int longestPath() {
        return longestPath_Rec(Integer.MIN_VALUE, 0);
    }

    public int longestPath_Rec(int valueInParent, int pathLengthInParent) {
        // Check if this node extends parent's path.
        // If not, this node forms a new path of length 1.
        int currentPathLength = (this.mValue == valueInParent + 1)
                ? pathLengthInParent + 1 : 1;
        int maxLength = currentPathLength;
        // Recursively invoke on all children and find if any form
        // an even longer continuous path.
        for (Tree child : mChildren) {
            int maxChildLength = child.longestPath_Rec(
                    this.mValue, currentPathLength);
            maxLength = Math.max(maxLength, maxChildLength);
        }
        // Return length of longest path from this node and its children.
        return maxLength;
    }
}
