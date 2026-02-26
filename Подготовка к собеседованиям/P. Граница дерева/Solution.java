import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Используем BufferedReader для быстрого ввода
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int rootId = Integer.parseInt(st.nextToken());
        
        // Массивы для хранения левого и правого потомка
        // Индекс массива соответствует ID вершины
        int[] leftChild = new int[n];
        int[] rightChild = new int[n];
        
        // Считываем описание вершин
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            while (line != null && line.trim().isEmpty()) {
                line = br.readLine();
            }
            if (line == null) break;
            
            st = new StringTokenizer(line);
            leftChild[i] = Integer.parseInt(st.nextToken());
            rightChild[i] = Integer.parseInt(st.nextToken());
        }
        
        // Множество для хранения ID граничных вершин (гарантирует уникальность)
        Set<Integer> boundaryVertices = new HashSet<>();
        
        // Очередь для BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(rootId);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevelNodes = new ArrayList<>();
            
            // Обрабатываем все вершины текущего уровня
            for (int i = 0; i < levelSize; i++) {
                int node = queue.poll();
                currentLevelNodes.add(node);
                
                int l = leftChild[node];
                int r = rightChild[node];
                
                // Добавляем потомков в очередь для следующего уровня
                if (l != -1) {
                    queue.offer(l);
                }
                if (r != -1) {
                    queue.offer(r);
                }
            }
            
            // Если уровень не пуст
            if (!currentLevelNodes.isEmpty()) {
                // 1. Самая левая вершина на уровне (первая в списке)
                boundaryVertices.add(currentLevelNodes.get(0));
                
                // 2. Самая правая вершина на уровне (последняя в списке)
                boundaryVertices.add(currentLevelNodes.get(currentLevelNodes.size() - 1));
                
                // 3. Проверяем все вершины уровня на условие "лист"
                for (int node : currentLevelNodes) {
                    if (leftChild[node] == -1 && rightChild[node] == -1) {
                        boundaryVertices.add(node);
                    }
                }
            }
        }
        
        // Выводим результат
        // Сортировка не обязательна по условию, но делает вывод упорядоченным
        List<Integer> result = new ArrayList<>(boundaryVertices);
        Collections.sort(result);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
            if (i < result.size() - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}
