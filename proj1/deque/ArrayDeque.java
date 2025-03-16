package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int capacity;
    private int head;
    private int tail;

    public ArrayDeque() {
        items = (T[]) new Object[1];
        size = 0;  // record the amount of items
        capacity = 1;  // record the capacity of the deque
        head = 0;  // index of the first item
        tail = 0;  // index of the tail item
    }

    @Override
    public void addFirst(T item) {
        if (isFull()) {
            resize(size * 2);
        }

        int newHead = (head - 1 + capacity) % capacity;
        items[newHead] = item;
        head = newHead;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(size * 2);
        }

        int newTail = (tail + 1) % capacity;
        items[newTail] = item;
        tail = newTail;
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T removedItem = items[head];

        head = (head + 1) % capacity;
        size--;

        // Check if the deque is too empty
        if (tooEmpty()) {
            resize(capacity / 4);
        }
        return removedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removedItem = items[tail];

        tail = (tail - 1 + capacity) % capacity;
        size--;

        // Check if the deque is too empty
        if (tooEmpty()) {
            resize(capacity / 4);
        }
        return removedItem;
    }

    private void resize(int newCapacity) {
        T[] a = (T[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            a[i] = items[(head + i) % capacity];
        }

        items = a;
        head = 0;
        tail = head + size - 1;
        capacity = newCapacity;
    }

    /* Returns whether the deque is full */
    private boolean isFull() {
        return size == capacity;
    }

    // Returns whether more than 75% of the deque is empty
    private boolean tooEmpty() {
        if (size == 0) {
            return false;
        }
        return size <= capacity / 4;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int printed = 0;
        if (head > tail) {
            for (int i = head; i < size; i++) {
                printItem(printed, i);
                printed++;
            }

            for (int i = 0; i < head; i++) {
                printItem(printed, i);
                printed++;
            }
        } else {
            for (int i = head; i <= tail; i++) {
                printItem(printed, i);
                printed++;
            }
        }
    }

    private void printItem(int printed, int i) {
        if (printed == size - 1) {
            System.out.print(items[i]);
            System.out.println();
        } else {
            System.out.print(items[i] + " ");
        }
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        return items[(head + index) % capacity];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        ArrayDequeIterator() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deque) {
            Deque d = (Deque) o;
            if (d.size() != this.size()) {
                return false;
            }

            for (int i = 0; i < this.size(); i++) {
                if (!d.get(i).equals(this.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
