public class HW6 {

    public static void main(String[] args) {
        //Task 6.1
        System.out.println("Примеры использования древовидной структуры:\n" +
                "\t- Форматы данных типа json, xml, html;\n" +
                "\t- Деревья решений в машинном обучении;\n" +
                "\t- Большинство файловых систем.\n");

        //Task 6.2

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

    }

    public Node find (int key){

    }

    public  boolean delete (int id){

    }

    public void displayTree(){

    }
}