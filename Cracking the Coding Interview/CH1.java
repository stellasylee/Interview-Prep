import java.io.*;
import java.util.*;

public class CH1{
    // 1-1
    /***
     * isUnique using hash table of characters
     * @param str string consisted of ASCII
     * @return boolean true when str contains unique characters
     */
    public static boolean isUnique (String str){
        if (str.length() > 128){
            return false;
        }

        boolean [] chars = new boolean [128]; // every element is set to false
        for (int i = 0; i < str.length(); i++){
            int c = (int) str.charAt(i);
            if (chars[c]){ // return false if there was already same character
                return false;
            }
            chars[c] = true;
        }
        return true;
    }

    /***
     * isUnique without using additional data structure
     * @param str string consisted of ASCII
     * @return boolean true when str contains unique characters
     */
    /*
    public static boolean isUnique (String str){
        if (str.length() > 128){
            return false;
        }

        char [] chars = str.toCharArray();
        java.util.Arrays.sort(chars);

        for (int i = 0; i < str.length() - 1; i++){
            int c = (int) str.charAt(i);
            if (chars[c] == chars [c+1]){
                return false;
            }
        }

        return true;
    }
    */

    // 1-2
    public static String sort (String s){
        char [] content = s.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);
    }
    /***
     * checkPermuation using sorting
     * @param str1 string
     * @param str2 string
     * @return boolean true when str2 is permutation of str2
     */
    public static boolean checkPermuation (String str1, String str2){
        if (str1.length() != str2.length()){
            return false;
        }

        return sort(str1).equals(sort(str2));
    }

    /***
     * checkPermuation using hashtable
     * @param str1 string
     * @param str2 string
     * @return boolean true when str2 is permutation of str2
     */
    /*
    public static boolean checkPermuation (String str1, String str2){
        if (str1.length() != str2.length()){
            return false;
        }

        char [] characters = new char [128];
        char [] str1Content = str1.toCharArray();
        for (char c: str1Content){
            characters [c]++;
        }

        for (i = 0; i < str2.length(); i++){
            int c = (int) str2.charAt(i);
            characters [c]--;
            if (characters [c] < 0){
                return false;
            }
        }
        return true;
    }
    */

    // 1-3
    /***
     * URLiff
     * Change every whitespace to '%20' for given string
     * @param str string string must contain enough space for modification
     * @param length int indicating true length of original string
     * @return string that modified every whitespace in str to '%20'
     */
    public static String URLify (String str, int length){
        char [] content = str.toCharArray();
        // count whitespace
        int count = 0;
        for (int i = 0; i < length; i++){
            if (content[i] == ' '){
                count ++;
            }
        }

        int index = length + count * 2;
        // modify character array
        for (int j = length-1; j >= 0 && index != j; j--){
            if (content[j] == ' '){
                content [index - 1] = '0';
                content [index - 2] = '2';
                content [index - 3] = '%';
                index-=3;
            }else {
                content [index - 1] = content [j];
                index--;
            }
        }

        return new String(content); 
    }

    // 1-4
    /***
     * isPermutationOfPalindrome
     * Check if phrase is permutation of palindrome
     * @param phrase string 
     * @return boolean that true if phrase is permutation of palindrome
     */
    public static boolean isPermutationOfPalindrome (String phrase) {
        String toLower = phrase.replaceAll("\\s", "").toLowerCase();
        char [] content = toLower.toCharArray();
        Arrays.sort(content);

        int count = 0;
        int odd = 0;
        for (int i = 0; i < content.length && odd <= 1; i++){
            char temp = content [i];
            count++;
            
            for (int j = i + 1; j < content.length; j++){
                if (content[j] == temp){
                    count++;
                    i = j;
                }else break;
            }

            if (count % 2 == 1){
                odd++;
            }
            count = 0;
        }
        if (odd <= 1){
            return true;
        } return false;
    }

    public static void main (String [] args) throws Exception{
        /*
        // 1-1
        Scanner s = new Scanner (System.in);
        System.out.println ("Enter String with ASCII values");
        String str = s.nextLine();
        boolean result = isUnique (str);

        if (result){
            System.out.println(str + " is consisted of unique characters");
        }else System.out.println(str + " is not consisted of unique characters");

        s.close();

        // 1-2
        Scanner s2 = new Scanner (System.in);
        System.out.println ("Enter first String with ASCII values");
        String str1 = s2.nextLine();
        System.out.println ("Enter next String with ASCII values");
        String str2 = s2.nextLine();
        boolean result2 = checkPermuation (str1, str2);

        if (result2){
            System.out.println(str1 + "and "+ str2+ " is permutaion of each other");
        }else System.out.println(str1 + "and "+ str2+ " is not permutaion of each other");

        s2.close();
        
        // 1-3
        Scanner s3 = new Scanner (System.in);
        System.out.println("Enter string: ");
        String str3 = s3.nextLine();
        int trueLeng = str3.length();
        for (int i = str3.length() -1; i >= 0; i--){
            if (str3.charAt(i) == ' '){
                trueLeng--;
            }else break;
        }
        
        String result3 = URLify(str3, trueLeng);
        result3 = result3.replaceAll(" ", "");
        String answer = str3.substring(0, trueLeng).replaceAll(" ", "%20");
        if (result3.compareTo(answer) == 0){
            System.out.println(str3 + " to " + result3);
        }else System.out.println("Error: " + str3 + " was changed to "+ result3);
        
        s3.close();
        */
        // 1-4
        Scanner s4 = new Scanner (System.in);
        System.out.println("Enter string: ");
        String str4 = s4.nextLine();
        boolean result4 = isPermutationOfPalindrome (str4);
        
        if (result4){
            System.out.println(str4 + " is permutation of palindrome");
        }else System.out.println(str4 + " is not permutation of palindrome");

        s4.close();
    }

}