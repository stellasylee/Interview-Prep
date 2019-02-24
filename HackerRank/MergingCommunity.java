import java.io.*;
import java.util.*;

/**
 * Merging Community : Union-Find, Disjoint Set
 * 
 *
 *
 * https://www.hackerrank.com/challenges/merging-communities/problem
 * */

class DisjointSet {
    int [] parents; // store the parent
    int [] sizes; // size of the community

    DisjointSet (int numPeople){
        parents = new int [numPeople];
        sizes = new int [numPeople];
        for (int i = 0; i < numPeople; i++){
            parents [i] = i+1;
            sizes [i] = 1;
        }
    }

    int size (int val){
        return sizes [find(val) - 1];
    }

    int find (int val){
        int current = val;
        if (current != parents[current-1]){
            parents[current-1] = find (parents[current-1]); // path compression
        }
        return parents[current-1];
    }

    void union (int x, int y){
    	int rootx = find(x);
    	int rooty = find(y);
    	// union by rank
    	if (rootx != rooty) {
    		if (sizes[rootx-1] <= sizes[rooty-1]) {
    			parents[rootx-1] = rooty;
    			sizes[rooty-1] += sizes[rootx-1];
    		}else {
    			parents[rooty-1] = rootx;
    			sizes[rootx-1] += sizes[rooty-1];
    		}
        }
    }

}

public class MergingCommunity {

    public static void main(String[] args) throws IOException{
        Scanner s = new Scanner (System.in);
        int n = s.nextInt ();
        int q = s.nextInt ();

        DisjointSet network = new DisjointSet (n);

        for (int i = 0; i < q; i++){
            String command = s.next();
            if (command.equals("Q")){
                int which = s.nextInt ();
                System.out.println (network.size(which));
            }else if (command.equals("M")){
                int x = s.nextInt();
                int y = s.nextInt();
                network.union(x, y);
            }
        }

        s.close();
    }
}
