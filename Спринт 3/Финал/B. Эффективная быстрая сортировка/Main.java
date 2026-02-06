/*
https://contest.yandex.ru/contest/23815/run-report/154169101/
Сложность O(n^2) в худшем случае, O(n log2) в среднем \(\Theta \) (среднее)

Для удобства было сделан класс Participant,
который содержит в себе имя, количество решенных задач и штрафы.

Также для удобства был реализован интерфейс Comparable с реализацией
сравнения согласно задаче, чтобы потом применять метод
compareTo при сравнении двух объектов, и чтобы сравненине не было громоздким,
особенно, когда повторяется несколько раз.

В качестве приятного побочного эффекта. Сортировка библиотечным методом Array.Sort() 
будет сортировать по нашему алгоритму.

Сначала считываются вводные данные и создается массив из объектов, класса
Participant.
Потом вызывается функция сортировки, и выводятся список участников.

в функции сортировки определяем базовый случай
как if (left >= (right - 1)) return;
У меня диапазон сортируемого массива - это от left включительно, 
до right невключительно
[left, right)
в этом случае left == (right - 1) - 1 элемент.
следовательно left >= (right - 1) - 0 или 1 элемент.

в итерации рандомно выбирается индекс для опорного элемента в 
полуоткрытом диапазоне [left, right)

Случайно, чтобы не получать слишком часто неудачные разбиения.

С помощью описанного в задании алгоритма, мы сдвигаем вправо
левый указатель пока элемент, на который он указывает, меньше опорного.
Потом мы сдвигаем влево правый указатель пока элемент, на который он
указывает, больше опорного.
После сдвигов мы меняем элементы на left и на right позициях местами.
Мы выходим из цикла, когда left станет >= right.

Далее мы 2 раза рекурсивно вызываем quickSortInPlace,
передавя в качестве правой границы последнее значение правого указателя
при первом вызове,
и значение правого указателя + 1 в качестве левой границы при втором вызове.

последнее значение правого указателя - это индекс нового опорного элемента.
(либо можно исполозовать последнее значение левого указателя).
*/
import java.io.*;
import java.util.Random;

class Participant implements Comparable<Participant> {
    private final String name;
    private final int tasks;
    private final int fines;

    public String getName() {
        return name;
    }

    public int getTasks() {
        return tasks;
    }

    public int getFines() {
        return fines;
    }

    public Participant(String name, int tasks, int fines) {
        this.name = name;
        this.tasks = tasks;
        this.fines = fines;
    }

    @Override
    public int compareTo(Participant o) {
        if (tasks > o.tasks) {
            return -1;
        }

        if (tasks < o.tasks) {
            return 1;
        }

        if (fines < o.fines) {
            return -1;
        }

        if (fines > o.fines) {
            return 1;
        }

        return name.compareTo(o.name);
    }
}

public class Main {
    public static void main(String[] args) throws IOException, RuntimeException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            Participant[] participants = new Participant[n];

            for (var i = 0; i < n; i++) {
                String[] parts = reader.readLine().split(" ");
                if (parts.length < 3) {
                    throw new RuntimeException("Неправильный ввод!");
                }
                int tasks = Integer.parseInt(parts[1]);
                int fines = Integer.parseInt(parts[2]);
                participants[i] = new Participant(parts[0], tasks, fines);
            }

            quickSortInPlace(participants, 0, participants.length);

            for (var i = 0; i < n; i++) {
                System.out.println(participants[i].getName());
            }
        }
    }

    private static void quickSortInPlace(Participant[] participants, int left, int right) {
        if (left >= (right - 1)) return;

        int pivotIndex = new Random().nextInt(left, right);
        Participant pivot = participants[pivotIndex];

        int i = left;
        int j = right - 1;

        while (true) {
        	while (participants[i].compareTo(pivot) < 0) { i++; }
        	while (participants[j].compareTo(pivot) > 0) { j--; }
            if (i >= j) break;
            swap(participants, i, j);
        }

        quickSortInPlace(participants, left, i);
        quickSortInPlace(participants, i + 1, right);
    }

    private static void swap(Participant[] participants, int i, int j) {
        Participant temp = participants[i];
        participants[i] = participants[j];
        participants[j] = temp;
    }
}
