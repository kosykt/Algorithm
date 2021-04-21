import java.util.LinkedList;
import java.util.Queue;

public class HW7 {
    
    public static void main(String[] args) {
        // Task 7.1
        // Московский метрополитен

        // Task 7.2
        Graph graph = new Graph();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');
        graph.addVertex('G');

        graph.addEdge(1,3);
        graph.addEdge(3,5);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(6,7);
        graph.addEdge(3,7);

        // Task 7.3
        long startTime = System.nanoTime();
        graph.dfs();
        long endTime = System.nanoTime();
        System.out.println("Время обхода в глубину: " + (endTime - startTime));


        // Task 7.4
        startTime = System.nanoTime();
        graph.bfs();
        endTime = System.nanoTime();
        System.out.println("Время обхода в ширину: " + (endTime - startTime));

    }
}

class Graph {
    private final int MAX_VERTS = 35;
    private Vertex[] vertexList;
    private int[][] adjMat;
    private int size;
    private Stack stack = new Stack(MAX_VERTS);

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        size = 0;
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
    }

    public void addVertex(char label) {
        vertexList[size++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void displayVertex(int vertex) {
        System.out.println(vertexList[vertex].label);
    }

    private int getAdjUnvisitedVertex(int vertex){
        for (int i = 0; i < size; i++) {
            if(adjMat[vertex][i] == 1 && vertexList[i].wasVisited == false){
                return i;
            }
        }
        return -1;
    }

    public void dfs(){
        vertexList[0].wasVisited = true;
        displayVertex(0);
        stack.push(0);

        while (!stack.isEmpty()){
            int v = getAdjUnvisitedVertex(stack.peek());

            if (v == -1){
                stack.pop();
            } else {
                vertexList[v].wasVisited = true;
                displayVertex(v);
                stack.push(v);
            }
        }

        for (int i = 0; i < size; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    public void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        vertexList[0].wasVisited = true;
        displayVertex(0);
        queue.add(0);
        int v2;

        while (!queue.isEmpty()){
            int v1 = queue.remove();
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1){
                vertexList[v2].wasVisited = true;
                displayVertex(v2);
                queue.add(v2);
            }
        }

        for (int i = 0; i < size; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    private class Vertex {
        private char label;
        private boolean wasVisited = false;

        public Vertex(char label) {
            this.label = label;
            this.wasVisited = false;
        }
    }

    private class Stack {
        private int maxSize;
        private int[] stackArr;
        private int top;

        private Stack(int size) {
            this.maxSize = size;
            this.stackArr = new int[size];
            this.top = -1;
        }

        private void push(int i) {
            stackArr[++top] = i;
        }

        private int pop(){
            return stackArr[top--];
        }

        private boolean isEmpty(){
            return (top == -1);
        }

        private int peek(){
            return stackArr[top];
        }
    }
}