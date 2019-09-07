package three.three_1;

public class SequentialSearchST<Key, Value> {

    public Node first;   //链表首结点

    private class Node {
        //链表结点的定义
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        //查找给定的键，返回相关联的值
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;  //命中
            }
        }
        return null;   //未命中
    }

    public void put(Key key, Value val) {
        //查找给定的键，找到则更新其值，否则在表中新建结点

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;  //命中，更新
                return;
            }
        }
        first = new Node(key, val, first);  //未命中，新建结点
    }


    // remove key-value pair with given key (if it's in the table)
    public void delete(Key key) {
        first = delete(first, key);
    }

    // delete key in linked list beginning at Node x
    // warning: function call stack too large if table is large  //这是递归的删除，以上都是非递归
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }
}
