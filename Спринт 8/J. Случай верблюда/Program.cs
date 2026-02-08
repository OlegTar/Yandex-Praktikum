using System;
using System.Linq;
using System.Collections.Generic;
using System.Text;

public class Program
{
    public class Node
    {
        public Node[] Children { get; set; }
        public bool IsTerminated { get; set; }
        public List<string> Words { get; set; }

        public Node()
        {
            Children = new Node[26];
            Words = new List<string>();
        }
    }

    public class Trie
    {
        private Node root { get; set; }
        public Trie()
        {
            root = new Node();
        }

        public void Add(string word)
        {
            Node node = root;
            foreach (char ch in word)
            {
                if (ch < 'A' || ch > 'Z')
                {
                    continue;
                }

                int idx = ch - 'A';

                if (node.Children[idx] == null)
                {
                    node.Children[idx] = new Node();
                }

                node = node.Children[idx];
            }

            node.IsTerminated = true;
            node.Words.Add(word);
            //node.Words.Sort();
        }

        public PriorityQueue<string, string> Search(string pattern)
        {
            PriorityQueue<string, string> result = new(Comparer<string>.Create((a, b) => string.CompareOrdinal(a, b)));
            Node node = root;
            bool notFound = false;
            foreach (char ch in pattern)
            {
                if (ch < 'A' || ch > 'Z')
                {
                    continue;
                }

                int idx = ch - 'A';

                if (node.Children[idx] == null)
                {
                    notFound = true;
                    break;
                }

                node = node.Children[idx];
            }



            Stack<Node> stack = new();
            if (!notFound)
            {
                stack.Push(node);
            }
            else
            {
                result.Enqueue(string.Empty, string.Empty);
            }

            while (stack.Count > 0)
            {
                node = stack.Pop();

                if (node.IsTerminated)
                {
                    foreach (var word in node.Words)
                    {
                        result.Enqueue(word, word);
                    }
                }

                for (int i = 0; i < node.Children.Length; i++)
                {
                    if (node.Children[i] != null)
                    {
                        stack.Push(node.Children[i]);
                    }
                }
            }

            return result;
        }
    }

    public static void Main(string[] args)
    {
        Trie trie = new Trie();
        int n = int.Parse(Console.ReadLine());
        for (int i = 0; i < n; i++)
        {
            trie.Add(Console.ReadLine());
        }
        int m = int.Parse(Console.ReadLine());

        for (int i = 0; i < m; i++)
        {
            string request = Console.ReadLine();
            PriorityQueue<string, string> results = trie.Search(request);
            while (results.Count > 0)
            {
                string resultItem = results.Dequeue();
                Console.WriteLine(resultItem);
            }
        }
    }
}
