import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        IR c1= new IR(5, 2);
        IR c2= new IR(3, -6);
        IR c3= new IR(3, 4);

        // System.out.println(IR.add(c1, c2));  // sum
        // System.out.println(IR.multiply(c1, c2));  // product
        // System.out.println(IR.abs(c3));  // absolute value

        IR c4= new IR((float) 0.1, (float) 0.2);
        IR c5= new IR((float) 0.4, (float) 0.35);
        Result.initializeGraphPoints(c4, c5, (float) 0.075);
        System.out.printf("graphPoints: [%d][%d]\n", Result.graphPoints.length, Result.graphPoints[0].length);

        Result.numFibonacciCycles(c4, c5, (float) 0.075);
    }
}
