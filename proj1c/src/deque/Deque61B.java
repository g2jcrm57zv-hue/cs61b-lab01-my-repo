package deque;

import java.util.Iterator;
import java.util.List;

/**
 * Created by hug on 2/4/2017. Methods are provided in the suggested order
 * that they should be completed.
 */
public interface Deque61B<T> extends Iterable<T> {

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    void addFirst(T x);

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    void addLast(T x);

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    List<T> toList();

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    int size();

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    T removeFirst();

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    T removeLast();

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    T get(int index);

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    T getRecursive(int index);

    Iterator<T> iterator();

    default boolean helperEquals(Object o) {
        // 1. 如果是自己跟自己比，直接 true
        if (this == o) {
            return true;
        }

        // 2. 检查 o 是不是一个 Deque61B 类型的对象
        // 注意：这里用 instanceof Deque61B<?>
        if (!(o instanceof Deque61B<?>)) {
            return false;
        }

        // 3. 强转成接口类型
        Deque61B<?> other = (Deque61B<?>) o;

        // 4. 检查大小
        if (this.size() != other.size()) {
            return false;
        }

        // 5. 一个个检查元素 (用迭代器！)
        // 因为两个都实现了 Iterable，所以可以用增强 for 循环或者迭代器
        Iterator<T> myIterator = this.iterator();
        Iterator<?> otherIterator = other.iterator();

        while (myIterator.hasNext()) {
            T myItem = myIterator.next();
            Object otherItem = otherIterator.next();

            // 注意处理 null 的情况 (用 Objects.equals 最好)
            if (myItem == null) {
                if (otherItem != null) return false;
            } else {
                if (!myItem.equals(otherItem)) return false;
            }
        }

        return true;
    }
}