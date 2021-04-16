import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class HW3 {

    public static void main(String[] args) {
        Integer[] integer = {0,1,2,3,4,5,6,7,8,9};
        task3_12(integer);
        task3_3();
        task3_4();
    }

    private static void task3_4() {
    }

    private static void task3_3() {
        OneDirectionLinkedList<String> hobbiesArrayList = new OneDirectionLinkedList<>();

        System.out.println("OneDirectionLinkedList is empty: " + String.valueOf(hobbiesArrayList.empty()));

        hobbiesArrayList.add("Swimming");
        hobbiesArrayList.add("Reading");
        hobbiesArrayList.add("Travelling");
        hobbiesArrayList.add("Programming");
        hobbiesArrayList.add("Cooking");
        hobbiesArrayList.print();
        System.out.println("OneDirectionLinkedList is empty: " + String.valueOf(hobbiesArrayList.empty()));

        String programming = "Programming";
        hobbiesArrayList.rem(programming);
        hobbiesArrayList.print();
        String elemToFind = "Reading";
        System.out.println(elemToFind + " found in OneDirectionLinkedList: " + String.valueOf(hobbiesArrayList.inList(elemToFind)));
        System.out.println(programming + " found in OneDirectionLinkedList: " + String.valueOf(hobbiesArrayList.inList(programming)) + "\n");
    }

    private static void task3_12(Integer[] integer) {
        //Task 3.1
        long l1 = System.nanoTime();
        List<Integer> list = Arrays.asList(integer);
        System.out.println(System.nanoTime() - l1);

        long l2 = System.nanoTime();
        Collection<Integer> collection = Arrays.asList(integer);
        System.out.println(System.nanoTime() - l2);

        //Task 3.2
        long l3 = System.nanoTime();
        System.out.println(list.get(9));
        System.out.println(list.size());
        list.set(9, 11);
        System.out.println(list);
        System.out.println(System.nanoTime() - l3);
    }
}

class ListElement<T> {
    private T data;
    private ListElement<T> next;

    public ListElement(T data) { this.data = data; }

    public T getData() {
        return data;
    }

    public ListElement<T> getNext() {
        return next;
    }

    public void setNext(ListElement<T> next) {
        this.next = next;
    }
}

class OneDirectionLinkedList<T> {
    private ListElement<T> first;

    public OneDirectionLinkedList() {
        first = null;
    }

    public boolean empty() {
        return (first == null);
    }

    public void add(T elem) {
        ListElement<T> el = new ListElement<>(elem);
        el.setNext(first);
        this.first = el;
    }

    public ListElement<T> fetch(T elemToFind) {
        ListElement<T> finding = new ListElement<>(elemToFind);
        ListElement<T> el = first;
        while (el != null) {
            if (el.getData().equals(finding.getData())) {
                return el;
            }
            el = el.getNext();
        }
        return null;
    }

    public ListElement<T> fetchPrevious(ListElement<T> elemToFind) {
        if (elemToFind.equals(this.first)) {
            return this.first;
        } else {
            ListElement<T> el = first;
            while (el != null) {
                if (el.getNext().getData().equals(elemToFind.getData())) {
                    return el;
                }
                el = el.getNext();
            }
            return null;
        }
    }

    public boolean inList(T elem) {
        return fetch(elem) != null;
    }

    public void rem(T elem) {
        ListElement<T> elemToRemove = fetch(elem);
        ListElement<T> elemPrev = fetchPrevious(elemToRemove);
        if (elemToRemove != elemPrev) {
            elemPrev.setNext(elemToRemove.getNext());
        }
        else {
            this.first = elemToRemove.getNext();
        }
    }

    public void print() {
        ListElement<T> elem = first;
        if (elem == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder display = new StringBuilder("[");
        while (elem != null) {
            display.append(String.valueOf(elem.getData())).append(", ");
            elem = elem.getNext();
        }
        display.delete(display.length()-2, display.length());
        display.append("]");
        System.out.println(display.toString());
    }
}