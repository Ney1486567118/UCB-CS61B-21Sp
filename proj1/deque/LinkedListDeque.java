package deque;

public class LinkedListDeque<T> {
    private class Node {
        T item;
        Node prev;
        Node next;

        public Node(T i, Node p, Node n) {
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

    public void addFirst(T item) {
        sentinel.next.prev = new Node(item, sentinel, sentinel.next);
        sentinel.next = sentinel.next.prev;

        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev.next = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;

        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

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

    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        } else {
            Node curr = sentinel.next;
            int curr_index = 0;
            while (curr_index < index) {
                curr = curr.next;
                curr_index ++;
            }

            return curr.item;
        }
    }

    public T getRecursion (int index) {
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
}
