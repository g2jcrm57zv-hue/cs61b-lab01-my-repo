import java.util.List;
import static java.lang.Math.floorMod;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque61B(){
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        items = (T[]) new Object[8];
    }

    @Override
    public void addFirst(T x) {
        items[nextFirst] = x;
        nextFirst = floorMod(nextFirst - 1, items.length);
        size += 1;
    }

    @Override
    public void addLast(T x) {
        items[nextLast] = x;
        nextLast = floorMod(nextLast + 1, items.length);
        size += 1;
    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0){
            return null;
        }
        return items[floorMod(index + nextFirst + 1, items.length)];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
