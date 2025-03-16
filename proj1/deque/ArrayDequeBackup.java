package deque;

import java.util.Iterator;

public class ArrayDequeBackup<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int head;
    private int tail;

    public ArrayDequeBackup() {
        items = (T[]) new Object[1];
        size = 0;
        head = 0;  // index of the first item
        tail = 0;  // index of the tail item
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];

        if (head > tail) {
            for (int i = head; i < size; i++) {
                a[i - head] = items[i];
            }

            for (int i = 0; i < head; i++) {
                a[size - head + i] = items[i];
            }
        } else {
            for (int i = head; i <= tail; i++) {
                a[i - head] = items[i];
            }
        }

        items = a;
        head = 0;
        if (size > 0) {
            tail = size - 1;
        } else {
            tail = 0;
        }
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        if (size == 0) {
            items[head] = item;
        } else {
            if (head == 0) {
                head = items.length - 1;
            } else {
                head -= 1;
            }
            items[head] = item;
        }

        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        if (size == 0) {
            items[tail] = item;
        } else {
            tail = (tail + 1) % items.length;
            items[tail] = item;
        }

        size += 1;
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
    public T removeFirst() {
        if ((size < items.length / 4) && (items.length > 4)) {
            resize(items.length / 4);
        }

        if (size == 0) {
            return null;
        } else {
            T firstItem = items[head];
            items[head] = null;
            if (size > 1) {
                head = (head + 1) % items.length;
            }


            size -= 1;
            return firstItem;
        }
    }

    @Override
    public T removeLast() {
        if ((size < items.length / 4) && (size > 4)) {
            resize(items.length / 4);
        }

        if (size == 0) {
            return null;
        } else {
            T tailItem = items[tail];
            items[tail] = null;

            if (tail == 0) {
                if (size > 1) {
                    tail = items.length - 1;
                }
            } else {
                tail -= 1;
            }

            size -= 1;
            return tailItem;
        }
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        } else {
            return items[(head + index) % items.length];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ADIterator();
    }

    private class ADIterator implements Iterator<T> {
        private int index;

        ADIterator() {
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
                if (d.get(i) != this.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
