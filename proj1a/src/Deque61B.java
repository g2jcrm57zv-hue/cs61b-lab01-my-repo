import java.util.List;

/**
 * 由 hug 创建于 2017/2/4。
 * 方法已按建议的完成顺序排列。
 */
public interface Deque61B<T> {

    /**
     * 将 {@code x} 添加到双端队列的头部。假设 {@code x} 永不为 null。
     *
     * @param x 要添加的元素
     */
    void addFirst(T x);

    /**
     * 将 {@code x} 添加到双端队列的尾部。假设 {@code x} 永不为 null。
     *
     * @param x 要添加的元素
     */
    void addLast(T x);

    /**
     * 返回该双端队列的 List 副本。不会修改原双端队列。
     *
     * @return 该双端队列的一个新 List 副本。
     */
    List<T> toList();

    /**
     * 返回双端队列是否为空。不会修改原双端队列。
     *
     * @return 如果双端队列没有元素则返回 {@code true}，否则返回 {@code false}。
     */
    boolean isEmpty();

    /**
     * 返回双端队列的大小。不会修改原双端队列。
     *
     * @return 双端队列中的元素数量。
     */
    int size();

    /**
     * 移除并返回双端队列头部的元素（如果存在）。
     *
     * @return 被移除的元素，否则返回 {@code null}。
     */
    T removeFirst();

    /**
     * 移除并返回双端队列尾部的元素（如果存在）。
     *
     * @return 被移除的元素，否则返回 {@code null}。
     */
    T removeLast();

    /**
     * Deque61B 抽象数据类型通常不包含 get 方法，
     * 但我们加入了这个额外操作，旨在为你提供更多的编程练习。
     * 以【迭代】方式获取元素。如果索引越界则返回 null。
     * 不会修改原双端队列。
     *
     * @param index 要获取的索引
     * @return 双端队列中 {@code index} 处的元素
     */
    T get(int index);

    /**
     * 从技术上讲，此方法不应出现在接口中，但为了方便测试而将其放在此处。
     * 以【递归】方式获取元素。如果索引越界则返回 null。
     * 不会修改原双端队列。
     *
     * @param index 要获取的索引
     * @return 双端队列中 {@code index} 处的元素
     */
    T getRecursive(int index);
}