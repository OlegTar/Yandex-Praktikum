import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.stream.Collectors;

public class Solution {

    private static long getMaxFinalCapital(List<Building> buildings, int startCapital, int maxNumberOfBuildings) {
        // Сортируем здания по требуемому капиталу (возрастание)
        buildings.sort((a, b) -> Integer.compare(a.needCapital, b.needCapital));
        
        // Max-heap для хранения прибыли доступных зданий
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        long currentCapital = startCapital;
        int index = 0;
        int n = buildings.size();
        
        // Покупаем не более k зданий
        for (int i = 0; i < maxNumberOfBuildings; i++) {
            // Добавляем в кучу все здания, которые можем себе позволить
            while (index < n && buildings.get(index).needCapital <= currentCapital) {
                maxHeap.offer(buildings.get(index).addedCapital);
                index++;
            }
            
            // Если ничего не можем купить — выходим
            if (maxHeap.isEmpty()) {
                break;
            }
            
            // Покупаем здание с максимальной прибылью
            currentCapital += maxHeap.poll();
        }
        
        return currentCapital;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> nAndK = readTwoNumbers(reader);
            int n = nAndK.get(0);
            int k = nAndK.get(1);
            List<Building> buildings = readBuildings(reader, n);
            int M = readInt(reader);
            System.out.println(getMaxFinalCapital(buildings, M, k));
        }
    }

    private static List<Building> readBuildings(BufferedReader reader, int n) throws IOException {
        List<Building> buildings = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            List<Integer> parameters = readTwoNumbers(reader);
            buildings.add(new Building(parameters.get(0), parameters.get(1)));
        } 
        return buildings;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readTwoNumbers(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
            .stream()
            .map(elem -> Integer.parseInt(elem))
            .collect(Collectors.toList());
    }

    private static class Building {
        public final int needCapital;
        public final int addedCapital;

        public Building(int c, int p) {
            needCapital = c;
            addedCapital = p;
        }
    }
}
