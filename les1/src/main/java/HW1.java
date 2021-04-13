public class HW1 {

    /*
    Task 1.1
        Приготовление чая:
            1. Вскипятить чайник.
            2. Взять кружку и заварку.
            3. Налить заварку, затем воду.
            4. Добавить сахар.
            5. Перемешать.

    Task 1.2
        Алгоритмы:
            1. Алгоритм поиска
            2. Алгоритм сортировки
            3. Алгиритм удаления
            4. Алгоритм сляния
        Cтруктуры данных:
            1. Линейные
            2. Иерархические
            3. Сетевые
            4. Табличные
     */

    public static void main(String[] args) {

//  Task 1.3
        int i = 101;
        String s = "Hello";
        MyClass myClass = new MyClass(010, "World");

    }
}

class MyClass{
    private int num;
    private String txt;

    public MyClass(int num, String txt) {
        this.num = num;
        this.txt = txt;
    }

    public int getNum() {
        return num;
    }

    public String getTxt() {
        return txt;
    }
}