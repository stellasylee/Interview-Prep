import java.io.*;
import java.util.*;

/**
 * Finding the island in the map represented by 2D array
 * 0 indicates water and 1 indicates land
 *
 * Time efficiency: O(n*m) where n is number of rows in the array
 *                                m is number of columns in the array
 *
 * https://www.careercup.com/question?id=4593205218639872
 * */

class Islands {

  /**
   * Calculate the sum of cube of each digits of num
   * @param arr 2D array of 0s or 1s
   * @return int the number of islands in arr
   * */
  private static int countIsland (int arr [][]){
     int count = 0;

     for (int r = 0; r < arr.length; r++){
       for (int c = 0; c < arr[r].length; c++){
         if (arr[r][c] == 1){
           boolean up = true;
           boolean left = true;

           if (r == 0){
             up = true;
           } else if (arr [r-1][c]== 1){
             up = false;
           }

           if (c == 0){
             left = true;
           } else if (arr [r][c-1] == 1){
             left = false;
           }

           if (up && left)
           count++;
         }
       }
     }
     return count;
   }

   private static void printArray (int [][] arr){
     System.out.println ("Map: ");
     for (int i = 0; i < arr.length; i++){
       for (int j = 0; j < arr[i].length; j++){
         System.out.print (arr[i][j]+ " ");
       }
       System.out.println("");
     }
   }

   public static void main (String [] args){
     Scanner s = new Scanner (System.in);

     System.out.println("Enter the number of rows in the map:");
     int row = s.nextInt();
     System.out.println("Enter the number of columns in the map:");
     int col = s.nextInt();

     int arr [] [] = new int [row][col];

     System.out.println("Enter the values for map:");
     System.out.println("0 for water and 1 for land");
     for (int r = 0; r < row; r++){
       System.out.println ("Enter the "+ r + " column");
       for (int c = 0; c < col; c++){
         arr [r][c] = s.nextInt();
       }
     }

     printArray (arr);

     System.out.println (countIsland (arr));
   }

}
