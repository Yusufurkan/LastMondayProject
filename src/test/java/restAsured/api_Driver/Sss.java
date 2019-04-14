package restAsured.api_Driver;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sss {
    public static void main(String[] args) {
        System.out.println(findMaxIterateNumber(0, 7007));

    }

    public static int findMaxIterateNumber(int i, int j) {

        int count = 0;
        int maxCount = 0;
        String carpanlar;
        for (int k = i; i < j; i++) {
            k = i;
            count = 0;
            System.out.println("k=  " + k);
            carpanlar = "  ";
            for (int m = 2; m <= i; m++) {
                //System.out.println(Arrays.toString(arr));
                // System.out.print("arr[i]= " + arr[i]+" ");
                if (isPrime(m)) {
                    while (k % m == 0) {
                        k /= m;
                        count++;
                    }
                    if (count != 0) {
                        carpanlar += (m + "^" + count + " * ");
                    }
                    //System.out.println("count= " + count + " j = " + j );
                    maxCount = Math.max(maxCount, count);
                    count = 0;
                }
            }

            System.out.println("carpanlar =" + carpanlar.substring(0, carpanlar.length() - 2));
        }

        return maxCount;
    }

    public static boolean isPrime(int number) {
        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 2; i < sqrt; i++) {
            if (number % i == 0) {
                // number is perfectly divisible - no prime
                return false;
            }
        }
        return true;
    }





    @Test
    public void test() {

        String str = "aaaeeeuuub";


        List<Character> vowels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        List<Character> consonants = new ArrayList<>(Arrays.asList('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'm', 'q', 'r', 's', 't', 'v', 'x', 'z'));

        int vowel = 0;
        int consant = 0;

        str = str.toLowerCase();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                continue;
            }

            if (vowels.contains(str.charAt(i))) {
                vowel++;
            } else if (consonants.contains(str.charAt(i))) {
                consant++;
            }
        }

        System.out.println("vowels " + vowel);
        System.out.println("cons " + consant);
    }
}
