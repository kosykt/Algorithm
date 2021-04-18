import java.util.*;

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

        //Task 5.6
        SortManager sorter = new SortManager();
        int[] arrayToSortAndSearch = new int[10];
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            arrayToSortAndSearch[i] = rand.nextInt(9);
        }

        int[] arrayToSortAndSearchCopy = arrayToSortAndSearch.clone();

        System.out.println("Unsorted array: " + Arrays.toString(arrayToSortAndSearch));
        long startTimeMergeSort = System.nanoTime();
        int[] arrayToSortSorted = sorter.mergeSort(arrayToSortAndSearch);
        double elapsedNanosMergeSort = (double) (System.nanoTime() - startTimeMergeSort) / 1000000;
        System.out.println("2.3.2. Array sorted by 'mergeSort()': " + Arrays.toString(arrayToSortSorted));
        System.out.println("\tTime elapsed: " + elapsedNanosMergeSort + " milliseconds\n");

        System.out.println("Unsorted array copy: " + Arrays.toString(arrayToSortAndSearchCopy));
        long startTimeSort = System.nanoTime();
        Arrays.sort(arrayToSortAndSearchCopy);
        double elapsedNanosSort = (double) (System.nanoTime() - startTimeSort) / 1000000;
        System.out.println("2.3.2. Array sorted by 'sort()': " + Arrays.toString(arrayToSortAndSearchCopy));
        System.out.println("\tTime elapsed: " + elapsedNanosSort + " milliseconds\n");

        //task 5.5
        SearchManager finder = new SearchManager();

        long startTimeRecursiveBinarySearch = System.nanoTime();
        int index = finder.recursiveBinarySearch(arrayToSortAndSearch, 4, 0, arrayToSortAndSearch.length-1);
        double elapsedNanosRecursiveBinarySearch = (double) (System.nanoTime() - startTimeRecursiveBinarySearch) / 1000000;
        System.out.println("Recursive binary Search found element at index: " + index);
        System.out.println("\tTime elapsed: " + elapsedNanosRecursiveBinarySearch + " milliseconds\n");

        long startTimeBinarySearch = System.nanoTime();
        int indexR = finder.binarySearch(arrayToSortAndSearch, 5);
        double elapsedNanosBinarySearch = (double) (System.nanoTime() - startTimeBinarySearch) / 1000000;
        System.out.println("Simple binary Search found element at index: " + indexR);
        System.out.println("\tTime elapsed: " + elapsedNanosBinarySearch + " milliseconds\n");
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

class SearchManager {

    public SearchManager() {
    }

    public int linearSearch(int[] arr, int elem) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == elem) {
                return i;
            }
        }
        return -1;
    }

    public int recursiveBinarySearch(int[] arr, int elem, int elemToBegin, int elemToEnd) {
        if (elemToBegin > elemToEnd) {
            return -1;
        }

        int middleElemIndex = (elemToBegin + elemToEnd) / 2;

        if (arr[middleElemIndex] == elem) {
            return middleElemIndex;
        } else if (arr[middleElemIndex] < elem) {
            return recursiveBinarySearch(arr, elem, middleElemIndex + 1, elemToEnd);
        } else {
            return recursiveBinarySearch(arr, elem, elemToBegin, middleElemIndex - 1);
        }
    }

    public int binarySearch(int[] arr, int elem) {
        int elemToBegin = 0;
        int elemToEnd = arr.length - 1;
        while (elemToBegin <= elemToEnd) {
            int middleElemIndex = (elemToBegin + elemToEnd) / 2;
            if (arr[middleElemIndex] == elem) {
                return middleElemIndex;
            } else if (arr[middleElemIndex] < elem) {
                elemToBegin = middleElemIndex + 1;
            } else if (arr[middleElemIndex] > elem) {
                elemToEnd = middleElemIndex - 1;
            }
        }
        return -1;
    }
}

class RecursionManager {
    public RecursionManager() {
    }

    public boolean isLinealDescendant(String des, String ans, HashMap<String, String[]> dic) {
        boolean answer = false;
        int i = 0;
        if (!dic.containsKey(des) && !dic.containsKey(ans)) {
            return answer;
        } else if (dic.containsKey(des) && dic.get(des).length > 0) {
            String[] parents = dic.get(des);
            for (String parent : parents) {
                if (parent.equals(ans)) {
                    return true;
                }
            }
            for (String parent : parents) {
                answer = isLinealDescendant(parent, ans, dic);
                if (answer == true) {
                    break;
                }
            }
        }
        return answer;
    }

    public void mayBeIternal(int start, int stop) {
        System.out.println(start);
        if (start == stop + 1) {
            return;
        }
        mayBeIternaCycle(++start, stop);
    }

    public void mayBeIternaCycle(int start, int stop) {
        while (start != stop + 1) {
            System.out.println(start);
            start++;
        }
    }
}

class SortManager {

    public SortManager() {
    }

    //Сортировка слиянием
    public int[] mergeSort(int[] arr) {
        int len = arr.length;
        if (len < 2) {
            return arr;
        }
        int middle = len / 2;
        return merge(mergeSort(Arrays.copyOfRange(arr, 0, middle)), mergeSort(Arrays.copyOfRange(arr, middle, len)));

    }

    public int[] merge(int[] arrA, int[] arrB) {
        int[] result = new int[arrA.length + arrB.length];
        int aInd = 0;
        int bInd = 0;

        for (int i = 0; i < result.length; i++) {
            result[i] = arrA[aInd] < arrB[bInd] ? arrA[aInd++] : arrB[bInd++];
            if (aInd == arrA.length) {
                System.arraycopy(arrB, bInd, result, ++i, arrB.length - bInd);
                break;
            }
            if (bInd == arrB.length) {
                System.arraycopy(arrA, aInd, result, ++i, arrA.length - aInd);
                break;
            }
        }
        return result;
    }

    //Сортировка пузырьком
    public int[] bubbleSort(int[] arr) {
        int numberOfShifts = 1;
        while (numberOfShifts != 0) {
            numberOfShifts = 0;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    numberOfShifts += 1;
                    int buff = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = buff;
                }
            }
        }
        return arr;
    }

    //Улучшенная сортировка пузырьком
    public int[] bubbleSortUpgraded(int[] arr) {
        int lastIndex = arr.length - 1;
        int firstIndex = 0;
        int numberOfShifts = 1;
        while (numberOfShifts != 0) {
            numberOfShifts = 0;
            for (int i = firstIndex; i < lastIndex; i++) {
                if (arr[i] > arr[i + 1]) {
                    numberOfShifts += 1;
                    int buff = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = buff;
                }
            }
            lastIndex--;
            for (int i = lastIndex + 1; i > firstIndex; i--) {
                if (arr[i] < arr[i - 1]) {
                    numberOfShifts += 1;
                    int buff = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = buff;
                }
            }
            firstIndex++;
        }
        return arr;
    }

    //Сортировка методом выбора
    public int[] selectionSort(int[] arr) {
        int min;
        int index = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] <= min) {
                    min = arr[j];
                    index = j;
                }
            }
            int buff = arr[i];
            arr[i] = min;
            arr[index] = buff;
        }
        return arr;
    }

    //Сортировка методом вставки
    public int[] insertionSort(int[] arr) {
        int el;
        for (int i = 1; i < arr.length; i++) {
            el = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > el) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = el;
        }
        return arr;
    }
}