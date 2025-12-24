import java.util.List;
import java.util.ArrayList;


public class LinkedListDeque61B<T> implements Deque61B<T> {

    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

        size = 0;
    }

    private class Node {
        T item;
        Node next;
        Node prev;

        public Node(T i, Node n, Node p){
            item = i;
            next = n;
            prev = p;
        }
    }

    @Override
    public void addFirst(T x) {
        Node oldFirst = sentinel.next;

        Node newNode = new Node(x, oldFirst, sentinel);

        oldFirst.prev = newNode;

        sentinel.next = newNode;

        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node oldLast = sentinel.prev;

        Node newLast = new Node(x, sentinel, oldLast);

        oldLast.next = newLast;

        sentinel.prev = newLast;

        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> lst = new ArrayList<>();
        Node currNode = sentinel.next;

        for (int i = 0; i < size; i++) {
            lst.add(currNode.item);
            currNode = currNode.next;
        }

        return lst;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        // 1. 排除空列表情况
        if (isEmpty()){
            return null;
        }

        Node oldFirstNode = sentinel.next;
        Node newFirstNode = oldFirstNode.next;
        T res = oldFirstNode.item;

        // 与哨兵节点连接
        newFirstNode.prev = oldFirstNode.prev;
        sentinel.next = newFirstNode;

        size -= 1;

        return res;
    }

    @Override
    public T removeLast() {
        if (isEmpty()){
            return null;
        }

        Node currLastNode = sentinel.prev;
        Node newLastNode = currLastNode.prev;
        newLastNode.next = sentinel;
        sentinel.prev = newLastNode;

        size -= 1;

        return currLastNode.item;
    }

    @Override
    public T get(int index) {
        // 1. 如果索引大于size或为负数，直接返回null
        if (index >= size || index < 0){
            return null;
        }

        // 2. 如果索引合法
        Node currNode = sentinel.next;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode.item;
    }

    @Override
    public T getRecursive(int index) {
        // 1. 基本情况
        if (index >= size || index < 0) {
            return null;
        }

        Node currNode = sentinel.next;

        return getRecursiveHelper(currNode, index);
    }

    private T getRecursiveHelper(Node p, int index){
        // 2. 最小事实：
        if (index == 0) {
            return p.item;
        }

        return getRecursiveHelper(p.next, index - 1);
    }


}
