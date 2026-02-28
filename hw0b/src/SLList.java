public class SLList{
    private IntNode first;
    private IntNode sentinel;
    private int cache;

    public SLList(int x){
        first = new IntNode(x, null);
        sentinel = new IntNode(63, null);
        cache = 1;
    }

    public static class IntNode{
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n){
            item = i;
            next = n;
        }
    }

    public void addFirst(int x){ // 在第一个索引位置新增一个元素
        first = new IntNode(x, first);
        cache += 1;
    }

    public int getFirst(){ // 返回第一个索引位置的元素
        return first.item;
    }

    public void addLast(int x){
        cache += 1;
        IntNode p = sentinel; // 添加索引p
        while(p.next != null){ // 将链表前进到最后一位
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    public int size(){
        return cache;
    }

}