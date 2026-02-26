import sys
from collections import deque

def solve():
    # Используем быстрый ввод
    input_data = sys.stdin.read().split()
    
    if not input_data:
        return

    iterator = iter(input_data)
    
    try:
        n = int(next(iterator))
        root_id = int(next(iterator))
    except StopIteration:
        return
    
    # Массивы для хранения левого и правого потомка.
    # Индекс массива соответствует ID вершины.
    left_child = [-1] * n
    right_child = [-1] * n
    
    # Считываем описание вершин.
    # Гарантируется, что i-я строка описания соответствует вершине с ID i.
    for i in range(n):
        left_child[i] = int(next(iterator))
        right_child[i] = int(next(iterator))
        
    # Множество для хранения ID граничных вершин (гарантирует уникальность)
    boundary_vertices = set()
    
    # Очередь для BFS: храним (id_вершины, глубина)
    queue = deque([(root_id, 0)])
    
    while queue:
        # Количество вершин на текущем уровне
        level_size = len(queue)
        current_level_nodes = []
        
        # Обрабатываем все вершины текущего уровня
        for _ in range(level_size):
            node, depth = queue.popleft()
            current_level_nodes.append(node)
            
            l = left_child[node]
            r = right_child[node]
            
            # Добавляем потомков в очередь для следующего уровня
            if l != -1:
                queue.append((l, depth + 1))
            if r != -1:
                queue.append((r, depth + 1))
        
        # Если уровень не пуст (всегда истинно для корректного дерева)
        if current_level_nodes:
            # 1. Самая левая вершина на уровне (первая в списке уровня)
            boundary_vertices.add(current_level_nodes[0])
            
            # 2. Самая правая вершина на уровне (последняя в списке уровня)
            boundary_vertices.add(current_level_nodes[-1])
            
            # 3. Проверяем все вершины уровня на условие "лист"
            for node in current_level_nodes:
                if left_child[node] == -1 and right_child[node] == -1:
                    boundary_vertices.add(node)
    
    # Выводим результат. Сортировка не обязательна по условию ("в любом порядке"),
    # но делает вывод детерминированным и удобным для проверки.
    print(*sorted(boundary_vertices))

if __name__ == '__main__':
    solve()
