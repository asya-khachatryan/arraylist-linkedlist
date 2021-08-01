import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class LinkList<T> implements Iterable<T>{
    private int elements;
    private Node<T> head;

    private static class Node<T> {
        private T element;
        private Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        public Node(T element) {
            this.element = element;
            this.next = null;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    public LinkList() {
        this.elements = 0;
        this.head = null;
    }

    public T get(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    public void add(T element) {
        Node<T> newNode = new Node(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        elements++;
    }

    public void remove(int index) {
        Node<T> current = head;
        if (index == 0) {
            head = head.next;
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        elements--;
    }

    public boolean contains(T element) {
        Node<T> current = head;
        for (int i = 0; i < elements; i++) {
            if (current == null) {
                return false;
            } else if (current.element == element) {
                return true;
            } else {
                current = current.next;
            }
        }
        return false;
    }

    public int size() {
        return elements;
    }

    public boolean isEmpty() {
        return elements == 0;
    }

    @Override
    public String toString() {
        String str = "[" + head.element;
        Node<T> current = head.next;
        for (int i = 0; i < elements - 1; i++) {
            str += ", " + current.element;
            current = current.next;
        }
        str += "]";
        return str;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size();
            }

            @Override
            public T next() {
                return get(currentIndex++);
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
