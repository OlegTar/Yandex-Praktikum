class Solution
{
    public static Node Reverse(Node head, int left, int right)
    {
        if (left >= right)
        {
            return head;
        }

        Node prev = new Node(0, head);

        int i = 1;
        Node tail = head;
        while (i < left)
        {
            prev = tail;
            tail = tail.Next;
            i++;
        }

        //1, 2, 3, 4, 5,
        //2, 1, 3, 4, 5,
        //3, 2, 1, 4, 5,
        //4, 3, 2, 1, 5,
        //5, 4, 3, 2, 1,


        int count = right - left;
        int counter = 0;
        while (counter < count)
        {
            Node next = tail.Next;
            Node nextNext = next?.Next;
            tail.Next = nextNext;

            Node firstAfterPrev = prev.Next;
            prev.Next = next;
            next.Next = firstAfterPrev;

            counter++;
        }

        if (i == 1)
        {
            return prev.Next;
        }
        return head;
    }
}
