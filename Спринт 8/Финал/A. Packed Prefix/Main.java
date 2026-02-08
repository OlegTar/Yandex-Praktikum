/*
Packed Prefix

url успешной посылки: https://contest.yandex.ru/contest/26133/run-report/156307302/
Алгоритмическая сложность: O(n!) -
Объяснение.
Допустим какая-то запакованная строка 9[8[7[....]]].
Для подстроки 8[7[....]] будет запускаться рекурсивно функция unpack.
Для подстроки 7[...] будет запускаться рекурсивно функция unpack.
и т.д.
В алгоритме ищется подстрока от открывающей до
закрывающей квадратной скобки того же уровня.

На каждом шаге строка уменьшается на 3 символа (цифра и две скобки).

Получается (без учета повторений): n * (n - 3) * (n - 6) * (n - 9) ...
в худшем случае мы доходим до 1.
получается n * (n - 3) * (n - 6) * (n - 9) ... 1
ещё в более худшем случае у нас все девятки:
9[9[9[9[...]]]
получается 9 * n * 9 * (n - 3) * 9 * (n - 6) * 9 * (n - 9) ... 1
девятка как константа уходит.

Пространственная сложность: O(n * U_max) - n - количество строк, U_max - длина
самой большой распакованной строки.
List<String> unpackedStrings хранит все n распакованных строк
Занимает O(n × U_max) памяти.

Форматирование выполнено автоматически в Intellij Idea.

Описание алгоритма:
Проходим по строкам, распаковываем их (описание алгоритма распаковки ниже).
Считаем длину наименьшей строки (общий префикс не может быть больше чем длина наименьшей
строки).

Далее проходим от i = 0 до длины наименьшей строки.
По условию не может быть 0 строк, хотя бы одна есть.
Мы берем текущий символ первой строки (unpackedStrings.get(0).charAt(i)).
Проверяем во внутреннем цикле, что все строки начиная со второй имеют такой же символ
на i-ом месте. Если находим строку, где это не так, то выходим из цикла,
и из верхнего цикла - тоже.
Если все строки имеют один и тот же символ на i-ом месте, то добавляем его
в префикс (для префика используем StringBuilder).

Выводим префикс.

Распаковка строки.
В коде это функция unpack
Это рекурсивная функция.

Создаем StringBuilder

идём до начала до конца строки.
Базовый кейс:
Если текущий символ НЕ цифра (либо цифра, но за ним не идёт '['), то
просто добавляем символ в объект типа StringBuilder.

Если текущий символ цифра, а следующий символ '['
то значит у нас начался шаблон с повторениями.
По заданию число повторений - это однозная число.
Ищем закрывающий символ ']'.
Однако, одна запакованная строка может содержать другую запакованную строку,
то есть внутри могут встречаться вложенные '[' и ']'.
Нам надо найти не первый попавшийся символ ']', а символ ']' того же уровня.
Для этого мы считаем количество открытых квадратных скобок '['.
Сначала эта переменная (openBrackets) равно 1.
Мы идем от первой открытой квадратной скобки до конца.
Если встречается новая открытая скобка, мы инкрементим количество открытых
скобок, если встречается закрытая скобка, мы декрементим количество открытых
скобок. Если встретили закрывающую квадратную скобку, и если после декремента
количество открытых скобок стало 0, то мы дошли до последнеего символа
запакованной подстроки.
Берем подстроку от открывающей '[' до закрывающей ']' этого уровня, и рекурсивно
для неё вызываем метод unpack.

Мы конкатинируем результат рекурсивного вызова unpack для подстроки
от открывающей '[' до закрывающей ']' этого уровня,
повторенный столько, сколько у нас цифра перед '['.
*/

import java.io.*;
import java.util.*;

public class PackedPrefix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        List<String> unpackedStrings = new ArrayList<>();
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            String unpackedString = unpack(scanner.nextLine());
            unpackedStrings.add(unpackedString);
            len = Math.min(len, unpackedString.length());
        }

        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char ch = unpackedStrings.get(0).charAt(i);
            boolean match = true;
            for (int j = 1; j < unpackedStrings.size(); j++) {
                if (unpackedStrings.get(j).charAt(i) != ch) {
                    match = false;
                    break;
                }
            }
            if (!match) {
                break;
            } else {
                prefix.append(ch);
            }
        }

        System.out.println(prefix);
    }

    public static String unpack(String packedString) {
        StringBuilder sb = new StringBuilder();

        int n = packedString.length();
        for (int i = 0; i < n; i++) {
            if (isDigit(packedString.charAt(i))
                    && i < (n - 1)
                    && packedString.charAt(i + 1) == '[') {

                int openBrackets = 1;
                int j = i + 2;
                for (; j < packedString.length() && openBrackets > 0; j++) {
                    if (packedString.charAt(j) == '[') {
                        openBrackets++;
                    } else if (packedString.charAt(j) == ']') {
                        openBrackets--;
                    }
                }

                String unpackedPart = unpack(packedString.substring(i + 2, j - 1));
                int count = packedString.charAt(i) - '0';
                sb.append(unpackedPart.repeat(count));
                i = j - 1;
            } else {
                sb.append(packedString.charAt(i));
            }
        }

        return sb.toString();
    }

    public static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }
}
