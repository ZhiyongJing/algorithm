package template;

import java.util.ArrayList;
import java.util.List;
public class Testing {

    /**
     * Write a program that outputs the string representation of numbers from 1 to n.
     * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”.
     * For numbers which are multiples of both three and five output “FizzBuzz”.
     */

// if n% 3 == 0, we will print Fizz
// if n% 5 == 0, we will print Buzz
// if n% 5 == 0  and if n% 3 == 0, we will print FizzBuzz
// else we print num as string
    public static String getBuzzFizz(int n) {
        String result = "";
        if (n % 5 == 0 && n % 3 == 0) {
            result = "FizzBuzz";
        } else if (n % 5 == 0) {
            result = "Buzz";
        } else if (n % 3 == 0) {
            result = "Fizz";
        } else {
            result = String.valueOf(n);
        }
        return result;
    }

    /**
     * Write a function that returns the Fibonacci number up to a given index in the series. E.g.)
     * Fibonacci Sequence: 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89… If the index given is 3 the output should be 2.
     */
    public static int findFib(int n){
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 1;
        }
        int prevPrev = findFib(n-2);
        int prev = findFib(n -1);

        return prev + prevPrev;

    }

    public static int findFib2(int n){
        List<Integer> fibs = new ArrayList<>();
        if(n == 1 || n == 2){
            return 1;
        }
        int result = -1;
        fibs.add(0);
        fibs.add(1);

        for(int i = 2; i <= n; i++){
            fibs.add(fibs.get(i -1) + fibs.get(i - 2));
        }
        result = fibs.get(n);

        return result;
    }






    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 5, 15, 60, 61};
//        for (int i : arr) {
//            System.out.println(getBuzzFizz(i));
//        }
        System.out.println(findFib2(4));

    }

}
