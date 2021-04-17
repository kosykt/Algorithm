import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
        System.out.println("Task 4.1 time: " + (System.nanoTime() - t1) + "\n-------------");

        //Task 4.2
        Queue<MyClass4> queue = new LinkedList<>();

        long t2 = System.nanoTime();
        queue.add(new MyClass4(0, 1));
        queue.add(new MyClass4(2, 3));

        while (!queue.isEmpty()){
            queue.poll().print();
        }
        System.out.println("Task 4.2 time: " + (System.nanoTime() - t2) + "\n-------------");

        

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
