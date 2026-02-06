/*
https://contest.yandex.ru/contest/23815/run-report/154160656/

Разбиваем решение на 2 части.
1я часть - находим сдвиг с помощью бинарного поиска.
2я часть - находим с помощью бинарного поиска искомый индекс с учетом сдвига.

Обе части решаются за O(log n) следовательно общее решение - O(log n), так как 
O(2 log n) = O(log n)
Решение проходит все тесты.

Так как по условию задачи количество элементов не превосходит 10000, 
мы можем использовать более простую формулу для вычисления mid
(mid = (left + right) / 2), вместо
mid = left + (right - left) / 2, переполнения у нас не будет.

1я часть - находим сдвиг.
Она состоит из 2х подзадач: 

1) поиск индекса максимального элемента,
2) вычисление сдвига (с помощью формулы, ниже)

Поиск индекса максимального элемента производим так.
Сначала предполагаем, что никакого сдвига нет,
и индекс максимального элемента - это индекс последнего элемента
(maxIndex = array.length - 1).

В каждой итерации делим массив пополам и берем средний элемент (array[mid]).
Если array[mid] <= array[maxIndex],
значит максимальный элемент всё ещё остается под индексом maxIndex,
и мы отсекаем правую половину массива (та, которая [mid + 1..right) ), это мы 
делаем присваивая right = mid.

Если же на какой-то итерации оказалось, что array[mid] > array[maxIndex],
то значит mid - новый кандидат на индекс максимального элемента.
Сохраняем mid в maxIndex (maxIndex = mid), и продолжаем поиск в правой 
половине, отсекая левую часть ( [left, mid) ) (left = mid + 1), так как 
если оказалось, что array[mid] больше чем array[maxIndex],
то максимальный элемент находится в диапазоне от mid (включительно) 
до maxIndex (не включительно, он уже не указывает на максимальный элемент).

После того, как мы нашли maxIndex, нам надо вычислить сдвиг.
Мы можем вычислить сдвиг как вправо, так и влево.
Вычислим сдвиг вправо, так как потом его будет удобно применять для вычисления
нового индекса.
((индекс + сдвиг) % количество элементов)

Если maxIndex = array.length - 1, сдвиг равен 0,
если maxIndex = 0, сдвиг равен 1,
если maxIndex = 1, сдвиг равен 2,
если maxIndex = 2, сдвиг равен 3,

Таким образом сдвиг вычисляется по формуле (1 + maxIndex) % array.length;

Получение остатка от деления (% array.length), нужно, чтобы получить из
maxIndex, равного array.length - 1 (т.е. когда сдвига нет), 0.

Сдвиг сохраняем в переменной shiftRight

2я часть - находим индекс искомого элемента.

Мы производим обычный бинарный поиск.
но после вычисления mid, мы вычисляем mid в сдвинутом массиве:

int shiftedMid = (mid + shiftRight) % array.length;
и именно элемент под этим индексом мы сравниваем с k

if (array[shiftedMid] == k) {
     return shiftedMid;
} else if (array[shiftedMid] < k) {
     left = mid + 1;
} else {
     right = mid;
}
*/

public class Solution {
    public static int brokenSearch(int[] array, int k) {
        int maxIndex = array.length - 1;
        int left = 0;
        int right = array.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (array[mid] <= array[maxIndex]) {
                right = mid;
            } else {
                maxIndex = mid;
                left = mid + 1;
            }
        }

        int shiftRight = (1 + maxIndex) % array.length;

        left = 0;
        right = array.length;

        while (left < right) {
            int mid = (left + right) / 2;
            int shiftedMid = (mid + shiftRight) % array.length;

            if (array[shiftedMid] == k) {
                return shiftedMid;
            } else if (array[shiftedMid] < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return -1;
    }

    private static void test() {
        int[] arr = {19, 21, 100, 101, 1, 4, 5, 7, 12};
        assert 6 == brokenSearch(arr, 5);
    }
}
