import java.io.*;
import java.util.*;

public class ExpensiveNetwork {
    //javafx.util.* в Яндекс.Практикуме не доступен
    public static class Edge {
        private final int vertex;
        private final int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int getVertex() {
            return vertex;
        }

        public int getWeight() {
            return weight;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            String[] parts = line.split(" ");
            int n = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);

            List<List<Edge>> edges = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                edges.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                line = br.readLine();
                parts = line.split(" ");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                int w = Integer.parseInt(parts[2]);

                if (u != v) {
                    edges.get(u).add(new Edge(v, w));
                    edges.get(v).add(new Edge(u, w));
                }
            }

            Queue<Edge> maxHeap = new PriorityQueue<>((e1, e2) -> Integer.compare(e2.getWeight(), e1.getWeight()));
            Set<Integer> notAdded = new HashSet<>();
            for (int i = 2; i <= n; i++) {
                notAdded.add(i);
            }
            List<Edge> neighbors = edges.get(1);
            for (Edge edge : neighbors) {
                maxHeap.offer(edge);
            }

            int cost = 0;
            while (!notAdded.isEmpty() && !maxHeap.isEmpty()) {
                Edge edge = maxHeap.poll();
                int vertex = edge.getVertex();
                if (!notAdded.contains(vertex)) {
                    continue;
                }
                notAdded.remove(vertex);
                cost += edge.getWeight();

                for (Edge neihgborEdge : edges.get(vertex)) {
                    maxHeap.offer(neihgborEdge);
                }
            }

            System.out.println(!notAdded.isEmpty() ? "Oops! I did it again" : cost);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
