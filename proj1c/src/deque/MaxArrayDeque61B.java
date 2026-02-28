package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {

    private Comparator<T> comparator;

    public MaxArrayDeque61B(Comparator<T> c){
        super();
        comparator = c;
    }

    public T max() {
        return max(this.comparator);
    }

    public T max(Comparator<T> c) {
        // 1.deque无元素时,直接返回null.
        if (this.isEmpty()) {return null;}

        // 2.若有元素,经过比较,返回最大值.
        T max = this.get(0);
        for (T d : this){
            if (c.compare(max, d) < 0){
                max = d;
            }
        }
        return max;
    }

}
