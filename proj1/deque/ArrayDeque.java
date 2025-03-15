package deque;

public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    public int head;
    public int last;

    public ArrayDeque() {
        items = (T[]) new Object[1];
        size = 0;
        head = 0;  // index of the first item
        last = 0;  // index of the last item
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];

        if (head > last) {
            for (int i = head; i < size; i ++) {
                a[i - head] = items[i];
            }

            for (int i = 0; i < head; i ++) {
                a[size - head + i] = items[i];
            }
        } else {
            for (int i = head; i <= last; i ++) {
                a[i - head] = items[i];
            }
        }

        items = a;
        head = 0;
        if (size > 0) {
            last = size - 1;
        } else {
            last = 0;
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
            items[last] = item;
        } else {
            last = (last + 1) % items.length;
            items[last] = item;
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
        if (head > last) {
            for (int i = head; i < size; i ++) {
                printItem(printed, size, i);
                printed ++;
            }

            for (int i = 0; i < head; i ++) {
                printItem(printed, size, i);
                printed ++;
            }
        } else {
            for (int i = head; i <= last; i ++) {
                printItem(printed, size, i);
                printed ++;
            }
        }

    }

    private void printItem(int printed, int size, int i) {
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
            T lastItem = items[last];
            items[last] = null;

            if (last == 0) {
                if (size > 1) {
                    last = items.length - 1;
                }
            } else {
                last -= 1;
            }

            size -= 1;
            return lastItem;
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
}
