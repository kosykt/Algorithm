import java.util.Arrays;
import java.util.Random;

public class HW2 {

    public static void main(String[] args) {
        HomeWork hw = new HomeWork();
        ArrStage arrStage = new ArrStage();
        hw.task2_1(arrStage.getArr());
        hw.task2_2(arrStage.getArr());
        hw.task2_3(arrStage.getArr());
        hw.task2_4(arrStage.getArr());
        hw.task2_5(arrStage.getArr());
        hw.task2_6(arrStage.getArr());
    }
}

class ArrStage{
    Random random = new Random();
    private int[] arr = new int[400];

    public int[] getArr() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10);
        }
        return arr;
    }
}

class HomeWork{
    public void task2_1(int[] array) {
//  Task 2.1
        long l = System.nanoTime();
        int[] arrT;
        arrT = array;
        Arrays.sort(arrT);
        System.out.println(array.equals(arrT));
        System.out.println("Время task2.1 " + (System.nanoTime() - l));
    }

    public void task2_2(int[] array) {
//  Task 2.2
        long l = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 3){
                System.out.println("Success!");
                break;
            }
        }
        System.out.println("Линейный поиск " + (System.nanoTime() - l));

        Arrays.sort(array);
        int elemToBegin = 0;
        int elemToEnd = array.length - 1;
        while (elemToBegin <= elemToEnd) {
            int middleElemIndex = (elemToBegin + elemToEnd) / 2;
            if (array[middleElemIndex] == 3) {
                System.out.println("Success!");
                break;
            } else if (array[middleElemIndex] < 3) {
                elemToBegin = middleElemIndex + 1;
            } else if (array[middleElemIndex] > 3) {
                elemToEnd = middleElemIndex - 1;
            }
        }
        System.out.println("Двоичный поиск " + (System.nanoTime() - l));
    }

    public void task2_3(int[] bigArray) {
        long l = System.nanoTime();
        Arrays.sort(bigArray);
        System.out.println("Сортировка через sort " + (System.nanoTime() - l));
    }

    public void task2_4(int[] array) {
        long l = System.nanoTime();
        int check = 1;
        while (check != 0) {
            check = 0;
            for (int i = 0; i < array.length-1; i++) {
                if (array[i] > array[i+1]){
                    check += 1;
                    int buff = array[i];
                    array[i] = array[i+1];
                    array[i + 1] = buff;
                }
            }
        }
        System.out.println("Сортировка пузырьком " + (System.nanoTime() - l));

        int lastIndex = array.length - 1;
        int firstIndex = 0;
        int numberOfShifts = 1;
        while (numberOfShifts != 0) {
            numberOfShifts = 0;
            for (int i = firstIndex; i < lastIndex; i++) {
                if (array[i] > array[i+1]){
                    numberOfShifts += 1;
                    int buff = array[i];
                    array[i] = array[i+1];
                    array[i+1] = buff;
                }
            }
            lastIndex--;

            for (int i = lastIndex+1; i > firstIndex; i--) {
                if (array[i] < array[i-1]){
                    numberOfShifts += 1;
                    int buff = array[i];
                    array[i] = array[i-1];
                    array[i-1] = buff;
                }
            }
            firstIndex++;
        }
        System.out.println("Улучшенная сортировка пузырьком " + (System.nanoTime() - l));
    }

    public void task2_5(int[] bigArr) {
        long l = System.nanoTime();
        int min;
        int index = 0;
        for (int i = 0; i < bigArr.length - 1; i++) {
            min = bigArr[i];
            for (int j = i+1; j < bigArr.length; j++) {
                if (bigArr[j] <= min) {
                    min = bigArr[j];
                    index = j;
                }
            }
            int buff = bigArr[i];
            bigArr[i] = min;
            bigArr[index] = buff;
        }
        System.out.println("Сортировка методом выбора " + (System.nanoTime() - l));
    }

    public void task2_6(int[] bigArr) {
        long l = System.nanoTime();
        int num;
        for (int i = 1; i < bigArr.length; i++) {
            num = bigArr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (bigArr[j] > num) {
                    bigArr[j+1] = bigArr[j];
                }
                else {
                    break;
                }
            }
            bigArr[j+1] = num;
        }
        System.out.println("Сортировка методом вставки " + (System.nanoTime() - l));
    }
}
