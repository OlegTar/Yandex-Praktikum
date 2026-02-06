import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(reader.readLine());
        Map<String, Integer> sections = new HashMap<>();
        int j = 0;
        
        for (int i = 0; i < n; i++) {
            String name = reader.readLine();
            sections.putIfAbsent(name, j++);
        }

        // Сортируем по значению (индексу вставки), затем выводим ключи
        sections.entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .forEach(System.out::println);
    }
}
