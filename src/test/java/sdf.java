import org.testng.annotations.Test;
import java.util.Arrays;

public class sdf {

    public static void main(String[] args) {

//        int[] arr = {1, 2, 3, 4, 5};
//
//        System.out.println(Arrays.toString(test(arr)));

//        System.out.println(ispalindrom("Nurses Run"));
//        System.out.println(Arrays.toString(fix34(new int[]{5, 3, 5, 4, 5, 4, 5, 4, 3, 5, 3, 5})));

//
//
//        System.out.println(reverse("Sky is blue"));
//        System.out.println(reversewords("noon"));

    }


    public static int[] test(int... arr) {

        int[] result = new int[arr.length];

        for (int i = 0, b = arr.length - 1; i < arr.length; i++, b--) {
            result[i] = arr[b];
            System.out.println(result[i]);
        }

        return result;
    }


    public static boolean hamlet(boolean a, boolean b) {

        return a || b;
    }

    public static boolean err(String str) {
        String[] arr = str.split(" ");

        return arr[0].equalsIgnoreCase("error");
    }

    public static boolean isSorted(int... arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (!(arr[i] <= arr[i + 1])) {
                return false;
            }
        }
        return true;
    }

    public static boolean ispalindrom(String str) {

        String palindrom = "";
        str = str.trim().replace(" ", "");

        for (int i = str.length() - 1; i >= 0; i--) {
            palindrom += str.charAt(i);
        }
        System.out.println(palindrom);
        return palindrom.equalsIgnoreCase(str);
    }


    public static boolean ispalindrom2(String str) {
        int n = str.length();
        String palindrom = "";
        str = str.toLowerCase().replace(" ", "").trim();

        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n - 1)) {
                return false;
            }
            n--;
        }
        return true;
    }

    public static boolean badP(int[] arr, int limit) {

        int counter = 0;
        for (int el : arr) {
            if (el == 0 && counter < limit) {
                counter++;
            }
        }
        return counter < limit;
    }


    public static StringBuilder replaceWith(String[] arr, String a, String b) {

        StringBuilder st = new StringBuilder();
        for (String el : arr) {
            st.append(el.equals(a) ? el.replace(a, b) : el);
        }
        return st;
    }

    public static int[] fix34(int[] arr) {

        int[] copy = Arrays.copyOf(arr, arr.length);

        int temp = 0;
        for (int i = 0; i < copy.length; i++) {
            if (copy[i] == 3) {

                for (int j = 0; j < copy.length; j++) {

                    if (copy[j] == 4) {
                        temp = arr[i + 1];
                        arr[i + 1] = copy[j];
                        copy[j] = 0;
                        arr[j] = temp;
                        break;
                    }
                }


            }
        }
        return arr;
    }


    public static String reverse(String str) {

        String[] words = str.split(" ");

        String result = "";

        for (String word : words) {
            result = word + " " + result;
        }

        return result.trim();
    }


    public static boolean reversewords(String word) {

        String result = "";
        word = word.replace(" ", "");

        for (int i = word.length() - 1; i >= 0; i--) {
            result += word.charAt(i);

        }

        return word.equals(result);
    }


    @Test
    public void test() {
        String a = "";
        String b = "a";

        System.out.println(a.matches(b));
    }



}
