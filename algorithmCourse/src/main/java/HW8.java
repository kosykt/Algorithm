import java.util.Random;

public class HW8 {

    public static void main(String[] args) {
        //Task 8.1: пример использования хеш-таблиц
        System.out.println("Примеры использования хэш-таблиц:\n" +
                "\t- Базы данных;\n" +
                "\t- Компиляторы;\n" +
                "\t- Ассоциативные массивы по типу 'ключ-значения';\n" +
                "\t- Каталоги для быстрого доступа к физическому расположению объекта\n" +
                "\t(например, в библиотеке или книжном магазине);\n" +
                "\t- Словари;\n" +
                "\t- Цифровая подпись;\n" +
                "\t- Magnet-ссылки\n" +
                "\t- Штрихкоды\n" +
                "\t- Прицнипы формирования автомобильных номеров (по коду региона);\n" +
                "\t- Хранение и передача логинов, паролей и номеров карт в большинстве надёжных систем;\n" +
                "\t- Формирование уникальных ссылок доступа (например, в конференцию Zoom;\n");

        //Task 8.2: примеры ключей и коллизий
        System.out.println("Примеры ключей:\n" +
                "\t- Натуральные числа;\n" +
                "\t- Инициалы, начальные буквы;\n" +
                "\t- Сочетание букв и цифр;\n" +
                "\t- Целые слова (если отсутствует шифрование) - фамилия автора, например;\n" +
                "\t- Исходное значение, прошедшее шифрование по определенному правилу;\n");
        System.out.println("Примеры коллизий:\n" +
                "\t- Хэш-функция возвращает значение, которое ранеее уже было присвоено другому ключу;\n" +
                "\t- Среди авторов книг есть однофамильцы;\n" +
                "\t- Диапазон значений ключей изначально не предполагает, что все ключи уникальны;\n");

        //Task 8.3: популярных и эффективных хеш-функций
        System.out.println("Примеры хеш-функций:\n" +
                "\t- SHA-2 (криптографический алгоритм Агентства национальной безопасности США;\n" +
                "\t- Хеш-сумма;\n" +
                "\t- Контрольные цифры и контрольная сумму (для верификации в документах, банковских картах);\n");

        //Task 8.4
        int size = 20;

        HashTable hashTable = new HashTable(size);
        for (int i = 0; i < 10; i++) {
            hashTable.insert(new Item(new Random().nextInt(100)+1));
        }
        hashTable.display();


        //Task 8.5
        SecondHashTable hashTable2 = new SecondHashTable(size);
        for (int i = 0; i < 10; i++) {
            hashTable2.insert(new Item(new Random().nextInt(100)+1));
        }
        hashTable2.display();
    }
}

class Item {
    private int data;

    public Item(int data) {
        this.data = data;
    }

    public int getKey() {
        return data;
    }
}

class HashTable {
    private Item[] hashArr;
    private int arrSize;
    private Item nonItem;

    public HashTable(int size){
        this.arrSize = size;
        hashArr = new Item[arrSize];
        nonItem = new Item(-1);
    }

    public void display(){
        for (int i = 0; i < arrSize; i++) {
            if (hashArr[i] != null) {
                System.out.println(hashArr[i].getKey());
            }else{
                System.out.println("***");
            }
        }
    }

    public int hashFunc(int key){
        return key % arrSize;
    }

    public void insert(Item item){
        int key = item.getKey();
        int hashVal = hashFunc(key);
        while(hashArr[hashVal] != null && hashArr[hashVal].getKey() != -1){
            ++hashVal;
            hashVal%=arrSize;
        }
        hashArr[hashVal] = item;
    }

    public Item delete(int key){
        int hashVal = hashFunc(key);
        while(hashArr[hashVal] != null){
            if(hashArr[hashVal].getKey() == key){
                Item temp = hashArr[hashVal];
                hashArr[hashVal] = nonItem;
                return temp;
            }
            ++hashVal;
            hashVal%=arrSize;
        }
        return null;
    }

    public Item find(int key){
        int hashVal = hashFunc(key);
        while(hashArr[hashVal] != null){
            if(hashArr[hashVal].getKey() == key){
                return hashArr[hashVal];
            }
            ++hashVal;
            hashVal%=arrSize;
        }
        return null;
    }
}

class SecondHashTable {
    private Item[] hashArr;
    private int arrSize;
    private Item nonItem;

    public SecondHashTable(int size){
        this.arrSize = size;
        hashArr = new Item[arrSize];
        nonItem = new Item(-1);
    }

    public void display(){
        for (int i = 0; i < arrSize; i++) {
            if (hashArr[i] != null) {
                System.out.println(hashArr[i].getKey());
            }else{
                System.out.println("***");
            }
        }
    }

    public int hashFunc(int key){
        return key % arrSize;
    }

    public int hashFuncDouble(int key){
        return 5 - key%5;
    }

    public void insert(Item item){
        int key = item.getKey();
        int hashVal = hashFunc(key);
        int stepSize = hashFuncDouble(key);
        while(hashArr[hashVal] != null && hashArr[hashVal].getKey() != -1){
            hashVal+=stepSize;
            hashVal%=arrSize;
        }
        hashArr[hashVal] = item;
    }

    public Item delete(int key){
        int hashVal = hashFunc(key);
        int stepSize = hashFuncDouble(key);
        while(hashArr[hashVal] != null){
            if(hashArr[hashVal].getKey() == key){
                Item temp = hashArr[hashVal];
                hashArr[hashVal] = nonItem;
                return temp;
            }
            hashVal+=stepSize;
            hashVal%=arrSize;
        }
        return null;
    }

    public Item find(int key){
        int hashVal = hashFunc(key);
        int stepSize = hashFuncDouble(key);
        while(hashArr[hashVal] != null){
            if(hashArr[hashVal].getKey() == key){
                return hashArr[hashVal];
            }
            hashVal+=stepSize;
            hashVal%=arrSize;
        }
        return null;
    }
}