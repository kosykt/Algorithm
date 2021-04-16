import java.util.*;

public class HW3 {

    public static void main(String[] args) {
        Integer[] integer = {0,1,2,3,4,5,6,7,8,9};
        task3_12(integer);
        task3_3();
        task3_45();
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

    private static void task3_3() {
        //Task 3.3
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

    private static void task3_45() {
        //Task 3.4
        LinkedList<MyClass3> myClassLinkedList = new LinkedList<>();
        myClassLinkedList.add(new MyClass3(10, 'S', 599.0, true, "Socks", new String[]{"red", "black", "blue"}));
        myClassLinkedList.add(new MyClass3(0, 'L', 2199.90, false, "Hat", new String[]{"yellow"}));
        myClassLinkedList.add(new MyClass3(6, 'S', 999.0, true, "Gloves", new String[]{"black"}));
        myClassLinkedList.add(new MyClass3(1, 'M', 10_000.0, true, "Jacket", new String[]{"red", "black", "blue"}));
        myClassLinkedList.addFirst(new MyClass3(3, 'S', 7499.0, true, "Dress", new String[]{"violet", "grey", "pink"}));
        myClassLinkedList.addLast(new MyClass3(40, 'M', 2999.0, true, "Jeans", new String[]{"light blue", "blue"}));

        System.out.println("MyClassLinkedList: ");

        for (MyClass3 item : myClassLinkedList) {
            item.printer();
            System.out.println("");
        }
        System.out.println("MyClassLinkedList has size of " + String.valueOf(myClassLinkedList.size()));

        myClassLinkedList.pollFirst();
        myClassLinkedList.pollLast();
        myClassLinkedList.remove(2);

        System.out.println("MyClassLinkedList has size of " + String.valueOf(myClassLinkedList.size()));
        System.out.println("");

        //Task 3.5
        ListIterator<MyClass3> listIter = myClassLinkedList.listIterator();

        System.out.println("MyClassLinkedList objects from left to right: ");
        while(listIter.hasNext()){
            System.out.println(String.valueOf(listIter.next().id));
        }

        System.out.println("MyClassLinkedList objects from right to left: ");
        long l = System.nanoTime();
        listIter.set(myClassLinkedList.getLast());
        while(listIter.hasPrevious()){
            System.out.println(String.valueOf(listIter.previous().id));
        }
        System.out.println(System.nanoTime() - l);
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

class MyClass3 {
    //1. Primitive:
    int id;
    int quantity;
    char size;
    double price;
    boolean inStock;
    //2. Non-primitive:
    String name;
    String[] coloursAvailable;

    //Random id constructor
    public MyClass3(int quantity, char size, double price, boolean inStock, String name, String[] coloursAvailable) {
        this.id = 10_000 + (int) (Math.random() * 90_000);
        this.quantity = quantity;
        this.size = size;
        this.price = price;
        this.inStock = inStock;
        this.name = name;
        this.coloursAvailable = coloursAvailable;
    }

    //Id given constructor
    public MyClass3(int id, int quantity, char size, double price, boolean inStock, String name, String[] coloursAvailable) {
        this.id = id;
        this.quantity = quantity;
        this.size = size;
        this.price = price;
        this.inStock = inStock;
        this.name = name;
        this.coloursAvailable = coloursAvailable;
    }

    public void printer() {
        System.out.print("id: " + id + "\n");
        System.out.print("name: " + name + "\n");
        System.out.print("size: " + size + "\n");
        System.out.print("colours: " + Arrays.toString(coloursAvailable) + "\n");
        System.out.print("price: " + price + "\n");
        System.out.print("in stock: " + inStock + "\n");
        System.out.print("quantity: " + quantity + "\n");
    }

    public boolean equals(MyClass3 item) {
        return ((this.id == item.id) &&
                (this.quantity == item.quantity) &&
                (this.size == item.size) &&
                (this.price == item.price) &&
                (this.inStock == item.inStock) &&
                (this.name.equals(item.name)) &&
                (Arrays.equals(this.coloursAvailable, item.coloursAvailable)));
    }
}