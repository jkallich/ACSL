import java.util.ArrayList;
import java.util.HashMap;

class Pascal {

    /*
     * Complete the 'countUniqueValues' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER fibNumber as parameter.
     */

    private static ArrayList<ArrayList<Long>> pascalTriangle;
    private static ArrayList<Long> fibonacci;
    private static ArrayList<Long> diagonals;
    private static final boolean isTesting= true;

    public static int countUniqueValues(int fibNumber) {
        createFibonacci(fibNumber);
        createPascal();

        getDiagonals();

        return getCount();
    }

    public static void createFibonacci (long max) {
        if(isTesting) {
            System.out.println("Creating fibonacci sequence array...");
        }

        fibonacci= new ArrayList<>();

        long prevNum= 1;
        long num= 1;
        fibonacci.add(prevNum);
        while (num <= max) {
            fibonacci.add(num);
            long temp= num;
            num= prevNum + num;
            prevNum= temp;
        }

        //        if(isTesting) {
        //            for(Integer i : fibonacci){
        //                System.out.println(i);
        //            }
        //        }
    }

    public static void createPascal () {
        if(isTesting) {
            System.out.println("Creating Pascal Triangle...");
        }
        pascalTriangle= new ArrayList<>();
        ArrayList<Long> first= new ArrayList<>();
        first.add((long) 1);
        pascalTriangle.add(first);

        ArrayList<Long> second= new ArrayList<>();
        second.add((long) 1);
        second.add((long) 1);
        pascalTriangle.add(second);

        for (int i = 2; i < fibonacci.size(); i++) {
            ArrayList<Long> prev= pascalTriangle.get(i-1);
            ArrayList<Long> row= new ArrayList<>();
            row.add((long) 1);
            for(int j= 1; j < pascalTriangle.get(i-1).size(); j++) {
                row.add(prev.get(j-1) + prev.get(j));
            }

            row.add((long) 1);
            pascalTriangle.add(row);
        }

        //        if(isTesting) {
        //            System.out.println("Printing Pascal Triangle...");
        //            for(ArrayList<Integer> array : pascalTriangle) {
        //                for(Integer num : array) {
        //                    System.out.print(num + "  ");
        //                }
        //                System.out.println();
        //            }
        //        }
    }

    public static void getDiagonals () {
        if(isTesting) {
            System.out.println("Getting diagonals...");
        }

        diagonals= new ArrayList<>();

        for (int i = 0; i < fibonacci.size(); i++) {
            if(isTesting){
                System.out.println("Diagonal " + (i+1));
            }

            int row= fibonacci.size() - 1 - i;
            int col= 0;
            while (row >= 0 && row < pascalTriangle.size() && col < pascalTriangle.get(row).size()) {
                if(isTesting){
                    System.out.println("Row: " + row);
                    System.out.println("Col: " + col);
                    System.out.println("Current Element: " + pascalTriangle.get(row).get(col));
                }

                diagonals.add(pascalTriangle.get(row).get(col));

                row--;
                col++;
            }
        }

        diagonals.remove(0);

        if(isTesting) {
            System.out.println("Printing diagonal ints...");
            for (Long num : diagonals) {
                System.out.print(num + "  ");
            }
        }
    }

    public static int getCount () {
        if(isTesting) {
            System.out.println("Counting diagonals...");
        }
        HashMap<Long, Long> counts= new HashMap<>();

        for(Long num : diagonals) {
            if(!counts.containsKey(num)){
                counts.put(num, (long) 1);
            } else{
                counts.put(num, counts.get(num) + 1);
            }
        }

        if(isTesting){
            System.out.println("Printing counts of diagonals...");
            for(Long num : counts.keySet()) {
                System.out.println(num + ":" + counts.get(num));
            }
        }

        int count= 0;
        for(Long num : counts.keySet()) {
            if(counts.get(num)  == 1) {
                count++;
            }
        }

        return count;
    }

}
