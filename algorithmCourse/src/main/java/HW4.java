import java.util.Stack;

public class HW4 {

    public static void main(String[] args) {
        Stack<MyClass4> stack = new Stack<>();

        stack.push(new MyClass4(0, 1));
        stack.push(new MyClass4(2, 3));

        while (!stack.empty()){
            stack.pop().print();
        }
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
