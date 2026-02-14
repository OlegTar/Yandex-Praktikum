using System;
using System.Linq;
using System.Collections.Generic;

public class Program
{
    public static void Main(string[] args)
    {
        var line = Console.ReadLine();
        bool result = false;
        if (line.Contains("."))
        {
            result = CheckIPv4(line);
            if (result)
            {
                Console.WriteLine("IPv4");
            }
            else
            {
                Console.WriteLine("Error");
            }
            return;
        }

        result = CheckIPv6(line);
        if (result)
        {
            Console.WriteLine("IPv6");
        }
        else
        {
            Console.WriteLine("Error");
        }
    }

    public static bool CheckIPv4(string line)
    {
        var parts = line.Split(".").ToArray();
        if (parts.Length != 4)
        {
            return false;
        }

        foreach (var part in parts)
        {
            if (part.Length == 0)
            {
                return false;
            }

            if (part.Length > 3)
            {
                return false;
            }

            if (part != "0" && part.StartsWith("0"))
            {
                return false;
            }

            int partI = 0;
            if (int.TryParse(part, out partI))
            {
                if (partI > 255 || partI < 0)
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }

        return true;
    }

    public static bool CheckIPv6(string line)
    {
        var parts = line.ToLower().Split(":").ToArray();
        if (parts.Length != 8)
        {
            return false;
        }

        foreach (var part in parts)
        {
            if (part.Length == 0)
            {
                return false;
            }

            if (part.Length > 4)
            {
                return false;
            }

            if (!CheckPartHex(part))
            {
                return false;
            }
        }

        return true;
    }

    public static bool CheckPartHex(string part)
    {
        foreach (var ch in part)
        {
            if (!(
                ('0' <= ch && ch <= '9') || ('a' <= ch && ch <= 'f')
            ))
            {
                return false;
            }
        }

        return true;
    }
}
