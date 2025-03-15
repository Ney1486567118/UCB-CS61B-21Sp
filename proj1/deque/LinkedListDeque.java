package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        // create without a given element
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        // create with an element
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(item, sentinel, sentinel);

        size = 1;
    }

    @Override
    public void addFirst(T item) {
        sentinel.next.prev = new Node(item, sentinel, sentinel.next);
        sentinel.next = sentinel.next.prev;

        size += 1;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev.next = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;

        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node curr = sentinel.next;
        while (curr != sentinel) {
            if (curr.next == sentinel) {
                System.out.print(curr.item);
            } else {
                System.out.print(curr.item + " ");
            }

            curr = curr.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node first = sentinel.next;
            first.next.prev = sentinel;
            sentinel.next = first.next;

            size -= 1;
            return first.item;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            Node last = sentinel.prev;
            last.prev.next = sentinel;
            sentinel.prev = last.prev;

            size -= 1;
            return last.item;
        }
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        } else {
            Node curr = sentinel.next;
            int currIndex = 0;
            while (currIndex < index) {
                curr = curr.next;
                currIndex++;
            }

            return curr.item;
        }
    }

    public T getRecursion(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        } else {
            if (index == 0) {
                return sentinel.next.item;
            } else {
                return getRecursionHelper(sentinel.next.next, index - 1);
            }
        }
    }

    private T getRecursionHelper(Node curr, int index) {
        if (index == 0) {
            return curr.item;
        } else {
            return getRecursionHelper(curr.next, index - 1);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LinkedListDeque lld) {
            if (lld.size() != this.size()) {
                return false;
            }

            for (int i = 0; i < this.size(); i++) {
                if (lld.get(i) != this.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int index;

        public LinkedListDequeIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T returnItem = get(index);
            index++;
            return returnItem;
        }
    }
}
