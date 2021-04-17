import java.util.*;

public class HW4 {

    public static void main(String[] args) {
        //Task 4.1
        Stack<MyClass4> stack1 = new Stack<>();

        long t1 = System.nanoTime();
        stack1.push(new MyClass4(0, 1));
        stack1.push(new MyClass4(2, 3));

        while (!stack1.empty()){
            stack1.pop().print();
        }
        System.out.println("Task 4.1 nanoTime: " + (System.nanoTime() - t1) + "\n-------------");

        //Task 4.2
        Queue<MyClass4> queue = new LinkedList<>();

        long t2 = System.nanoTime();
        queue.add(new MyClass4(0, 1));
        queue.add(new MyClass4(2, 3));

        while (!queue.isEmpty()){
            queue.poll().print();
        }
        System.out.println("Task 4.2 nanoTime: " + (System.nanoTime() - t2) + "\n-------------");

        //Task 4.3
        Deque<MyClass4> deque = new ArrayDeque<>();

        long t3 = System.nanoTime();
        deque.add(new MyClass4(0, 1));
        deque.add(new MyClass4(2, 3));

        deque.addFirst(new MyClass4(4, 4));
        deque.addLast(new MyClass4(5, 5));

        while (!deque.isEmpty()){
            deque.poll().print();
        }
        System.out.println("Task 4.3 nanoTime: " + (System.nanoTime() - t3) + "\n-------------");

        //Task 4.4
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        long t4 = System.nanoTime();

        priorityQueue.add(1);
        priorityQueue.add(7);
        priorityQueue.add(2);
        priorityQueue.offer(5);

        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll());
        }
        System.out.println("Task 4.4 nanoTime: " + (System.nanoTime() - t4) + "\n-------------");
    }
}

class MyClass4{
    private int x;
    private int y;

    public MyClass4(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void print(){
        System.out.println("X: " + this.x + "; Y: " + this.y);
    }
}