import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FlattenedIterator implements Iterator<Integer> {

    public FlattenedIterator(List<Iterator<Integer>> iterators){
        mQueue = new LinkedList<>();

        for (Iterator<Integer> it : iterators) {
            if (it.hasNext()) {
                mQueue.add(it);
            }
        }
    }

    @Override
    public boolean hasNext() {
        if (!mQueue.isEmpty()) {
            if (!mQueue.peek().hasNext()) {
                throw new IllegalStateException("Iterator unexpectedly empty");
            }
        }
        return !mQueue.isEmpty();
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            Iterator<Integer> it = mQueue.remove();
            Integer value = it.next();
            if (it.hasNext()) {
                mQueue.add(it);
            }
            return value;
        } else {
            return null;
        }
    }

    private Queue<Iterator<Integer>> mQueue;
}
