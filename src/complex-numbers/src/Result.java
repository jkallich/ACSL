import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'numFibonacciCycles' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. FLOAT realPartLL
     *  2. FLOAT imagPartLL
     *  3. FLOAT realPartUR
     *  4. FLOAT imagPartUR
     *  5. FLOAT incr
     */

    public static ArrayList<ArrayList<IR>> cycles= new ArrayList<>();
    public static IR[][] graphPoints;

    public static int numFibonacciCycles(float realPartLL, float imagPartLL, float realPartUR, float imagPartUR, float incr) {
        IR LL= new IR(realPartLL, imagPartLL);
        IR UR= new IR(realPartUR, imagPartUR);

        createGraph(LL, UR, incr);
        printGraph();

        return Integer.MIN_VALUE;
    }

    public static int numFibonacciCycles(IR LL, IR UR, float incr) {
        return numFibonacciCycles(LL.a, LL.b, UR.a, UR.b, incr);
    }

    public static void createGraph (IR LL, IR UR, float incr) {
        initializeGraphPoints(LL, UR, incr);

        float x= LL.a;
        float y= LL.b;
        for(int i= graphPoints.length-1; i >= 0; i--) {
            for(int j= 0; j < graphPoints[i].length; j++) {
                graphPoints[i][j]= new IR(x, y);
                x+= incr;
            }
            x= LL.a;
            y+= incr;
        }
    }

    public static void initializeGraphPoints (IR LL, IR UR, float incr) {
        float x= LL.a;
        float y= LL.b;

        int r= 0;
        int c= 0;
        while(x <= UR.a || y <= UR.b) {
            if(x < UR.a) {
                x+= incr;
                c++;
            }

            if(y < UR.b) {
                y+= incr;
                r++;
            }
        }

        graphPoints= new IR[r+1][c];
    }

    public static void printGraph() {
        for(IR[] row : Result.graphPoints) {
            for(IR point : row) {
                System.out.print(point + "\t\t");
            }
            System.out.println();
        }
    }
}