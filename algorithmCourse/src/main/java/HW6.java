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

        long l = System.nanoTime();
        tree.displayTree();
        System.out.println(System.nanoTime() - l);
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