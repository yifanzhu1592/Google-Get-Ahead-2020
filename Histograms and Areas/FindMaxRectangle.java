import java.util.Stack;

public class FindMaxRectangle {
    private int histogram[];
    private int histogramLen;
    private Stack<Integer> stack;
    private int max_area = 0; // Initialize max area
    private int left_index = 0; // The left of the max area rectangle
    private int right_index = 0; // The right index of the max area rectangle

    public FindMaxRectangle(int histogram[]) {
        this.histogram = histogram;
        this.histogramLen = histogram.length;
        this.stack = new Stack<>();
    }

    public int getRightIndex() {
        return this.right_index;
    }

    public int getLeftIndex() {
        return this.left_index;
    }

    private void calculateAndUpdateArea(int top, int current_position) {
        int right_position = current_position - 1;
        int left_position = stack.empty() ? 0 : stack.peek() + 1;
        int rect_width = right_position - left_position + 1;
        int rect_height = histogram[top];
        int area = rect_height * rect_width;
        if (max_area < area) {
            max_area = area;
            left_index = left_position;
            right_index = right_position;
        }
    }

    public int findMaxRectangle() {
        int top; // To store top of stack

        // Run through all bars of given histogram
        for (int current_position = 0;
             current_position < histogramLen;
             current_position++) {

            // If this bar is lower than top of stack, then calculate area
            // of rectangle with stack top as the smallest (or minimum height) bar.
            while (!this.stack.empty() &&
                   (this.histogram[this.stack.peek()] >=
                    this.histogram[current_position])) {
                top = this.stack.peek(); // store the top index
                this.stack.pop(); // pop the top
                // Calculate the area with hist[top] stack as smallest bar
                // update max area, if needed
                calculateAndUpdateArea(top, current_position);
            }
            this.stack.push(current_position);
        }

        // Now pop the remaining bars from stack and calculate area with
        // every popped bar as the smallest bar
        while (!this.stack.empty()) {
            top = this.stack.peek();
            this.stack.pop();
            calculateAndUpdateArea(top, histogramLen);
        }

        return max_area;
    }

    public static void main(String[] args) {
        int hist1[] = {2, 4, 2, 1};
        FindMaxRectangle finder1 = new FindMaxRectangle(hist1);
        int maxRectangle = finder1.findMaxRectangle();
        System.out.println("The maximum area is " + maxRectangle +
                           " with indices " + finder1.getLeftIndex() +
                           " " + finder1.getRightIndex());

        int hist2[] = {2, 4, 2, 1, 10, 6, 10};
        FindMaxRectangle finder2 = new FindMaxRectangle(hist2);
        int maxRectangle2 = finder2.findMaxRectangle();
        System.out.println("The maximum area is " + maxRectangle2 +
                           " with indices " + finder2.getLeftIndex() +
                           " " + finder2.getRightIndex());
    }
}
