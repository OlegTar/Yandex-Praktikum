using System;
using System.Collections;
using System.Collections.Generic; 

public class Solution {
    public static Node CloneGraph(Node node) {
        if (node == null) return null;
        
        Dictionary<Node, Node> map = new Dictionary<Node, Node>();
        Queue<Node> queue = new Queue<Node>();
        
        // Инициализируем клон стартового узла
        map[node] = new Node(node.val);
        queue.Enqueue(node);
        
        while (queue.Count > 0) {
            Node current = queue.Dequeue();
            
            foreach (Node neighbor in current.neighbours) {
                // Если сосед ещё не клонирован — создаём копию и добавляем в очередь
                if (!map.ContainsKey(neighbor)) {
                    map[neighbor] = new Node(neighbor.val);
                    queue.Enqueue(neighbor);
                }
                // Добавляем клон соседа в список соседей текущего клона
                map[current].neighbours.Add(map[neighbor]);
            }
        }
        
        return map[node];
    }
}
