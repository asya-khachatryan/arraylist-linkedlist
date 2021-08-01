import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrList<T> implements Iterable<T> {
    private T[] array;
    private int elements;

    public ArrList() {
        this.array = (T[]) new Object[10];
        this.elements = 0;
    }

    public ArrList(int size) {
        this.array = (T[]) new Object[size];
        this.elements = 0;
    }

    public void add(T object) {
        if (arrayIsFull()) {
            T[] newArray = (T[]) biggerArray();
            newArray[elements] = object;
            this.array = newArray;
            elements++;
        } else {
            this.array[elements] = object;
            elements++;
        }
    }

    private boolean arrayIsFull() {
        if (elements >= array.length) {
            return true;
        }
        return false;
    }

    private Object[] biggerArray() {
        T[] newArray = Arrays.copyOf(array, elements + 1);
        return newArray;
    }

    public void remove(T object) {
        for (int i = 0; i < this.elements; i++) {
            if (object.equals(array[i])) {
                array[i] = null;
                elements--;
                copyArrayWithoutNull();
            }
        }
    }

    private void copyArrayWithoutNull() {
        T[] newArray = (T[]) new Object[elements];
        for (int i = 0, k = 0; i < elements + 1; i++) {
            if (array[i] == null) {
                continue;
            } else {
                newArray[k] = array[i];
                k++;
            }
        }
        this.array = newArray;
    }

    public T get(int index) {
        return array[index];
    }

    public boolean contains(T object) {
        for (int i = 0; i < this.elements; i++) {
            if (object.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return this.elements;
    }

    private T[] currentItems() {
        T[] current = (T[]) new Object[elements];
        for (int i = 0; i < elements; i++) {
            current[i] = this.array[i];
        }

        return current;
    }

    @Override
    public String toString() {
        return Arrays.toString(currentItems());
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < elements;
            }

            @Override
            public T next() {
                return array[currentIndex++];
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
