import java.util.Arrays;
import java.util.Random;

public class HW6 {

    public static void main(String[] args) {
        //Task 6.1
        System.out.println("Примеры использования древовидной структуры:\n" +
                "\t- Форматы данных типа json, xml, html;\n" +
                "\t- Деревья решений в машинном обучении;\n" +
                "\t- Большинство файловых систем." + "\n--------------------");

        //Task 6.2
        Tree tree = new Tree();
        System.out.println("Tree is created" + "\n--------------------");

        //Task 6.3
        tree.insert(new Person6("Ivan", 22, 11));
        tree.insert(new Person6("Igor", 33, 02));
        tree.insert(new Person6("Vladimir", 44, 43));
        tree.insert(new Person6("Viktor", 55, 04));

        tree.find(02).display();
        System.out.println("--------------------");

        //Task 6.4
        System.out.print("Min: ");
        tree.min().display();
        System.out.print("Max: ");
        tree.max().display();
        System.out.println("--------------------");

        //Task 6.5
        tree.delete(02);
        long l1 = System.nanoTime();
        tree.displayTree();
        System.out.println(System.nanoTime() - l1);

        System.out.println("--------------------");

        //Task 6.6
        int[] arrayToSort;
        arrayToSort = new int[10];
        Random rand = new Random();
        for (int i = 0; i<10; i++) {
            arrayToSort[i] = rand.nextInt(10);
        }

        System.out.println(Arrays.toString(arrayToSort));

        MyHeap heap = new MyHeap();
        long l2 = System.nanoTime();

        heap.heapSort(arrayToSort);

        System.out.println(Arrays.toString(arrayToSort));
        System.out.println(System.nanoTime() - l2);
        System.out.println("--------------------");

        //Task 6.7
        System.out.println("Пример сбалансированного дерева:\n" +
                "\t- Красно-чёрное дерево;\n" +
                "\t- АВЛ-дерево;\n" +
                "\t- TreeMap в Java реализован на основе красно-чёрных деревьев.\n");
    }
}

class Person6{
    public String name;
    public int age;
    public int id;

    public Person6(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }
}

class Node{
    public Person6 person6;
    public Node left;
    public Node right;

    public void display(){
        System.out.println("Name: " + person6.name + "; Age: " + person6.age);
    }
}

class Tree{
    private Node root;

    public void insert(Person6 person6){
        Node node = new Node();
        node.person6 = person6;
        if (root == null){
            root = node;
        } else {
            Node current = root;
            Node parent;
            while (true){
                parent = current;
                if (person6.id < current.person6.id){
                    current = current.left;
                    if (current == null){
                        parent.left = node;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null){
                        parent.right = node;
                        return;
                    }
                }
            }
        }
    }

    public Node find (int key){
        Node current = root;
        while (current.person6.id != key){
            if (key < current.person6.id){
                current = current.left;
            }else {
                current = current.right;
            }
            if (current == null){
                return null;
            }
        }
        return current;
    }

    private void preOrder(Node rootNode){
        if (rootNode != null){
            rootNode.display();
            preOrder(rootNode.left);
            preOrder(rootNode.right);
        }
    }

    private void postOrder(Node rootNode){
        if (rootNode != null){
            postOrder(rootNode.left);
            postOrder(rootNode.right);
            rootNode.display();
        }
    }

    private void inOrder(Node rootNode){
        if (rootNode != null){
            inOrder(rootNode.left);
            rootNode.display();
            inOrder(rootNode.right);
        }
    }

    public Node min(){
        Node current, last = null;
        current =root;
        while (current != null){
            last = current;
            current = current.left;
        }
        return last;
    }

    public Node max(){
        Node current, last = null;
        current =root;
        while (current != null){
            last = current;
            current = current.right;
        }
        return last;
    }

    public  boolean delete (int id){
        Node current = root;
        Node parent = root;

        boolean isLeft =true;

        while(current.person6.id != id){
            parent = current;
            if (id < current.person6.id){
                isLeft = true;
                current = current.left;
            } else {
                isLeft = false;
                current = current.right;
            }
            if (current == null){
                return false;
            }
        }

        if (current.left == null && current.right == null){
            if (current == root){
                root = null;
            } else if (isLeft){
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.right == null){
            if (current == null){
                root = current.left;
            } else if (isLeft){
                parent.left = current.left;
            } else {
                parent.right = current.right;
            }
        } else if (current.left == null){
            if (current == null){
                root = current.right;
            } else if (isLeft){
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            Node successor = getSuccessor(current);
            if (current == root){
                root = successor;
            } else if (isLeft){
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    public Node getSuccessor(Node node) {
        Node successorParent = node;
        Node successor = node;
        Node current = node.right;

        while (current != null){
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != node.right){
            successorParent.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }

    public void displayTree(){
        Node current = root;
        System.out.println("Симметнричный");
        inOrder(root);
        System.out.println("Прямой");
        preOrder(root);
        System.out.println("Обратный");
        postOrder(current);
    }
}

class MyHeap {
    private int heapSize;

    public MyHeap() {
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    private static int left(int i) {
        return 2 * i + 1;
    }

    public static void swap(int[] sourceArray, int i, int j) {
        int temp = sourceArray[i];
        sourceArray[i] = sourceArray[j];
        sourceArray[j] = temp;
    }

    public int getHeapSize() {
        return this.heapSize;
    }

    public void buildHeap(int[] sourceArray) {
        this.heapSize = sourceArray.length;
        for (int i = sourceArray.length / 2; i >= 0; i--) {
            heapify(sourceArray, i);
        }
    }

    public void heapify(int[] sourceArray, int i) {
        int leftChild = left(i);
        int rightChild = right(i);
        int largestChild = i;

        if (leftChild < this.heapSize && sourceArray[i] < sourceArray[leftChild]) {
            largestChild = leftChild;
        }

        if (rightChild < this.heapSize && sourceArray[largestChild] < sourceArray[rightChild]) {
            largestChild = rightChild;
        }

        if (i != largestChild) {
            swap(sourceArray, i, largestChild);
            heapify(sourceArray, largestChild);
        }
    }

    public void heapSort(int[] sourceArray) {
        buildHeap(sourceArray);
        while (this.heapSize > 1) {
            swap(sourceArray, 0, this.heapSize - 1);
            this.heapSize--;
            heapify(sourceArray, 0);
        }
    }

}