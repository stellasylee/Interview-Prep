import java.io.*;
import java.util.*;

/**
 * This program finds numbers that the sum of cube of each digits is the number itself
 * i.e. 153 = 1^3 + 5^3 + 3^3
 *
 * https://www.careercup.com/question?id=5703421944922112
 * */

class CubeNums {

  /**
   * Calculate the sum of cube of each digits of num
   * @param num non-negative integer
   * @return cubes the sum of cube of each digits
   * */
  private static int digitCube (int num){
    int cubes = 0;

    while (num > 0){
      cubes+= Math.pow(num % 10, 3);
      num = num/10;
      }

    return cubes;
  }

  public static void main(String[] args) {

    System.out.println ("Enter the upper limit for searching");
    Scanner s = new Scanner (System.in);
    int end = s.nextInt();

    ArrayList<Integer> cubeNums = new ArrayList<Integer>();

    for (int i = 0; i < end; i++){
      if (i == digitCube(i))
        cubeNums.add(i);
    }

    System.out.println ("These are integers that the sum of cube of each digits is equal to itself");
    for (Integer num: cubeNums){
      System.out.print(num+ " ");
    }

    System.out.println("");

  }
}
