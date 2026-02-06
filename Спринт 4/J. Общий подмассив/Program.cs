using System;
using System.Collections.Generic;
using System.Linq;

internal class Program
{
    private static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int[] a = Console.ReadLine().Split().Select(int.Parse).ToArray();
        int m = int.Parse(Console.ReadLine());
        int[] b = Console.ReadLine().Split().Select(int.Parse).ToArray();

        Console.WriteLine(LongestCommonSubstringLength(a, b));
    }

    private static int LongestCommonSubstringLength(int[] a, int[] b)
    {
        int lo = 0, hi = Math.Min(a.Length, b.Length);
        int ans = 0;

        while (lo <= hi)
        {
            int mid = (lo + hi) / 2;
            if (ExistsCommonSubstring(a, b, mid))
            {
                ans = mid;
                lo = mid + 1;
            }
            else
            {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private static bool ExistsCommonSubstring(int[] a, int[] b, int len)
    {
        if (len == 0) return true;
        if (a.Length < len || b.Length < len) return false;

        const ulong BASE1 = 131;
        const ulong BASE2 = 13131;
        var hashes = new HashSet<(ulong, ulong)>();

        // --- Первый массив ---
        ulong hash1 = 0, hash2 = 0;
        ulong pow1 = 1, pow2 = 1;

        for (int i = 0; i < len; i++)
        {
            hash1 = hash1 * BASE1 + (ulong)a[i];
            hash2 = hash2 * BASE2 + (ulong)a[i];
            if (i < len - 1)
            {
                pow1 *= BASE1;
                pow2 *= BASE2;
            }
        }
        hashes.Add((hash1, hash2));

        for (int i = len; i < a.Length; i++)
        {
            hash1 = hash1 - (ulong)a[i - len] * pow1;
            hash1 = hash1 * BASE1 + (ulong)a[i];

            hash2 = hash2 - (ulong)a[i - len] * pow2;
            hash2 = hash2 * BASE2 + (ulong)a[i];

            hashes.Add((hash1, hash2));
        }

        // --- Второй массив: проверка ---
        hash1 = 0; hash2 = 0;
        for (int i = 0; i < len; i++)
        {
            hash1 = hash1 * BASE1 + (ulong)b[i];
            hash2 = hash2 * BASE2 + (ulong)b[i];
        }
        if (hashes.Contains((hash1, hash2))) return true;

        for (int i = len; i < b.Length; i++)
        {
            hash1 = hash1 - (ulong)b[i - len] * pow1;
            hash1 = hash1 * BASE1 + (ulong)b[i];

            hash2 = hash2 - (ulong)b[i - len] * pow2;
            hash2 = hash2 * BASE2 + (ulong)b[i];

            if (hashes.Contains((hash1, hash2)))
                return true;
        }

        return false;
    }
}
