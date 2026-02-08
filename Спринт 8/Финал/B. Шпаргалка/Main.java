/*
Url успешной посылки: 
Алгоритмическая сложность: O(n * a * L), где n - длина строки, а "a" - среднее число колизий на хэш
(в идеале нет колизий и "a" == 1, L - средняя длина слова).
Пространственная сложность: O(n)
HashMap для хранения хэшей строк - хэш -> список хэшей.
Set - для хранения обработанных позиций.

Форматирования кода выполнено автоматически в Intellij Idea

Описание алгоритма.
Считываем текст.
Считываем слова и для каждой строки вычисляем хэш.
Хэш нужен для быстрого поиска нужной строки.
хэш и строку кладем в Map<Long, List<String>>
Где ключ - это хэш, а строка кладется в список (список на случай колизий).

Для вычисления хэша используем полиномиальный хэш из спринта 4.
По рекомендации этого спринта используем q - большое простое число (для уменьшения колизий).
R - большое число.
hash = (hash * q + (ch - 'a' + 1)) % R;
добавляем 1, чтобы строки "a" и "aa" - имели разный хэш (без единицы оба хэша будут 0).

Далее делаем dfs с помощью стека.
используем мемоизацию для запоминания позиции в строке, которую обрабатывали ранее, чтобы
не повторяться.

Кладем сначала в стек позицию 0.
В цикле достаем последнюю позицию из стека.
Идём от этой позиции либо до конца строки, либо до длины самого длинного слова (смотря что наступит
раньше). (цикл от i = pos ... )
Вычисляем полиномиальный хэш для текущей позиции (как в спринте 4),
тут же ищем этот хэш в Хэшмапе хэшей.

Если находим, достаем слова из по этому хэшу.
i + 1 - это будет длина от позиции, которую достали с вершины стека (в коде она "pos")
до i включительно.
Проверяем, что подстрока от [pos до i+1) равна слову, вытащенному из хэшмапы
(на случай, если вдруг произошла колизия, и это ложное срабатывание). Если это так, то
в стек записываем следующую позицию: stack.push(i + 1)
Однако, если i + 1 == text.length() (т.е. мы дошли до конца текста) и мы нашли
слово (word) по вычисленному хэшу от pos до i и это не колизия
(text.substring(pos, i + 1).equals(word)),
значит мы успешно разбили текст на слова, надо поставить флаг found = true и тут же выйти.

В конце пишем разбивается ли строка или нет.
 */

import java.util.*;

public class CheatSheet {
    private static final Set<Integer> memo = new HashSet<>();
    private static final Map<Long, List<String>> hashes = new HashMap<>();
    private static final int q = 1000000007;
    private static final int R = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();

        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            String word = scanner.nextLine();
            maxLength = Math.max(maxLength, word.length());
            long hash = computeHash(word);
            if (!hashes.containsKey(hash)) {
                hashes.put(hash, new ArrayList<>());
            }
            hashes.get(hash).add(word);
        }

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        boolean found = false;
        while (!stack.isEmpty() && !found) {
            int pos = stack.pop();

            if (memo.contains(pos)) {
                continue;
            }

            long hash = 0;
            int end = Math.min(text.length(), pos + maxLength);
            for (int i = pos; i < end; i++) {
                hash = ((hash * q) + (text.charAt(i) - 'a' + 1)) % R;

                if (hashes.containsKey(hash)) {
                    for (String word : hashes.get(hash)) {
                        if (!text.substring(pos, i + 1).equals(word)) {
                            //вдруг произошла колизия?
                            continue;
                        }
                        if ((i + 1) == text.length()) {
                            found = true;
                            break;
                        }
                        stack.push(i + 1);
                    }
                }
            }

            memo.add(pos);
        }

        System.out.println(found ? "YES" : "NO");
    }

    public static long computeHash(String str) {
        long hash = 0;
        for (char ch : str.toCharArray()) {
            hash = (hash * q + (ch - 'a' + 1)) % R;
        }
        return hash;
    }
}
