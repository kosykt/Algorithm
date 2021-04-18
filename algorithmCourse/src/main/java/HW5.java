import java.util.ArrayList;
import java.util.Collection;

public class HW5 {

    public static void main(String[] args) {
        //Task 5.1
//        Примеры рекурсии в математике:
//           Метод Гаусса — Жордана для решения систем линейных алгебраических уравнений является рекурсивным.
//           Уже упоминавшийся факториал целого неотрицательного числа.
//           Числа Фибоначчи определяются с помощью рекуррентного соотношения.

//        В программировании рекурсия — вызов функции (процедуры) из неё же самой,
//        непосредственно (простая рекурсия) или через другие функции (сложная или косвенная рекурсия),
//        например, функция A вызывает функцию B, а функцияB — функцию A.
//        Количество вложенных вызовов функции или процедуры называется глубиной рекурсии.
//        Рекурсивная программа позволяет описать повторяющееся или даже потенциально бесконечное
//        вычисление, причём без явных повторений частей программы и использования циклов.
//
//        https://ru.wikipedia.org/wiki/%D0%A0%D0%B5%D0%BA%D1%83%D1%80%D1%81%D0%B8%D1%8F

        System.out.println("task 5.1: " + task5_1(4) + "\n--------------------");

        //Task 5.2
        task5_21();
        System.out.println("--------------------");
        task5_22(1);
        System.out.println("--------------------");

        //Task 5.3
        MyStack5 myStack5 = new MyStack5();
        myStack5.add(new Person5("Ivanov", 20));
        myStack5.add(new Person5("Petrov", 30));

        while (!myStack5.empty()){
            myStack5.pop();
        }
        System.out.println("task 5.3-1: Stack is empty" + "\n--------------------");

        MyStack5 myRecursiveStack5 = new MyStack5();
        myRecursiveStack5.add(new Person5("Sidorov", 20));
        myRecursiveStack5.add(new Person5("Mikhailov", 30));

        myRecursiveStack5.popAllRecursively();
        myRecursiveStack5.peek();
        System.out.println("task 5.3-2: Recursive stack is empty" + "\n--------------------");

        //task 5.4
        int result = 1, n = 5;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        System.out.println("task 5.4-1: Факториал 5! равен:" + result + "\n--------------------");
        System.out.println("task 5.4-2: Факториал 4! равен:" + task5_1(4) + "\n--------------------");

        //task 5.5

    }

    private static int task5_1(int n) {
        int result = 1;
        if (n == 1 || n == 0) {
            return result;
        }
        result = n * task5_1(n-1);
        return result;
    }

    private static void task5_21() {
        System.out.println("task 5.2-1");
//        recurs5_21();
    }

    private static int task5_22(int i) {
        System.out.println("task 5.2-2");
        if (i == 1){
            return i;
        }
        return task5_22(i);
    }
}

class Person5 {
    String name;
    int age;

    public Person5(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class MyStack5 {
    private final ArrayList<Person5> stack = new ArrayList<>();
    private int top = -1;

    public MyStack5() {
    }

    public void add(Person5 el) {
        this.stack.add(el);
        this.top++;
    }

    public boolean empty() {
        return (this.top == -1);
    }

    public void pop() {
        this.peek();
        this.stack.remove(this.top);
        this.top--;
    }

    public void popAllRecursively() {
        this.peek();
        this.stack.remove(this.top);
        this.top--;
        if (!this.empty()) {
            this.popAllRecursively();
        }
    }

    public void peek() {
        if (this.top != -1) {
            this.stack.get(this.top);
        }
    }
}