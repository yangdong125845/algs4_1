package One.One_3;

import org.w3c.dom.traversal.NodeFilter;

import java.util.Iterator;

public class Bag<Item> implements  Iterable<Item> {

    private Node first;

    public void add(Item item) {
        Node oldFirst  = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    private class  Node {
        Item item;
        Node next;
    }
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class  ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current !=null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }
}
