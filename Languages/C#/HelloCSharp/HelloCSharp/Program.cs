using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HelloCSharp
{

    class HelloCSharpApp
    {
        static void Main(string[] args)
        {
            print hcs = new print();
            bodmas bm = new bodmas();
            string line = Console.ReadLine();
            hcs.display(Convert.ToString(bm.calculate(line)));
        }

    }

    class print
    {
        public string cat(string[] parameters)
        {
            string phrase = null;
            for(int i = 0; i < parameters.Length; i++)
            {
                phrase += " " + parameters[i];
            }
            return phrase;
        }

        public string display(string parameter)
        {
            Console.WriteLine(parameter);
            Console.WriteLine("---------Done--------");
            Console.ReadLine();
            return null;
        }
    }


    class bodmas
    {
        public int calculate(string calculation)
        {
            if (calculation.Contains("*"))
            {
                Console.WriteLine(Convert.ToInt32(calculation[calculation.IndexOf("*") - 1]));
                return multiply(Convert.ToInt32(calculation[calculation.IndexOf("*") - 1]),
                    Convert.ToInt32(calculation[calculation.IndexOf("*") + 1]));
            }else if (calculation.Contains("+"))
            {
                return add(Convert.ToInt32(calculation[calculation.IndexOf("+") - 1]),
                    Convert.ToInt32(calculation[calculation.IndexOf("+") + 1]));
            }else if (calculation.Contains("-"))
            {
                return subtract(Convert.ToInt32(calculation[calculation.IndexOf("-") - 1]),
                    Convert.ToInt32(calculation[calculation.IndexOf("-") + 1]));
            }
            else if (calculation.Contains("/"))
            {
                return divide(Convert.ToInt32(calculation[calculation.IndexOf("/") - 1]),
                    Convert.ToInt32(calculation[calculation.IndexOf("/") + 1]));
            }

            return 0;
        }

        public int multiply(int first, int second)
        {
            Console.WriteLine(first+", "+second);
            return Convert.ToInt32(first) * Convert.ToInt32(second);
        }

        public int divide(int first, int second)
        {
            return Convert.ToInt32(first / second);
        }

        public int add(int first, int second)
        {
            return Convert.ToInt32(first + second);
        }

        public int subtract(int first, int second)
        {
            return Convert.ToInt32(first - second);
        }
    }
}
