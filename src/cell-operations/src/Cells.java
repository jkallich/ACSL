import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Cells {

    public static boolean isTesting= true;
    public static boolean useSampleInputs= true;

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        if (useSampleInputs) {
            cells("DIVIDE, AHBGCEDF");
			// cells("ADD3, AHBGCEDF");
			// cells("SUBTRACT3, AHBGCEDF");
			// cells("UNION, AHBGCEDF, AGBHCFED");
			// cells("INTERSECT, AHBGCEDF, AGBHCFED");
        } else {
            System.out.println("Enter input:");
            String input= scanner.nextLine();
            System.out.println(cells(input));
        }
    }

    public static String cells (String input) {
		// HashMap<Integer, String> numToLetter= new HashMap<>();
		// numToLetter.put(1, "A");
		// numToLetter.put(2, "B");
		// numToLetter.put(3, "C");
		// numToLetter.put(4, "D");
		// numToLetter.put(5, "E");
		// numToLetter.put(6, "F");
		// numToLetter.put(7, "G");
		// numToLetter.put(8, "H");
		//
		// HashMap<String, Integer> letterToNum= new HashMap<>();
		// numToLetter.put("A", 1);
		// numToLetter.put("B", 2);
		// numToLetter.put("C", 3);
		// numToLetter.put("D", 4);
		// numToLetter.put("E", 5);
		// numToLetter.put("F", 6);
		// numToLetter.put("G", 7);
		// numToLetter.put("H", 8);

        if (isTesting){
            System.out.println();
            System.out.println("============= Start Debugging =============");
        }

        ArrayList<String> letters= new ArrayList<>(8);
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");
        letters.add("F");
        letters.add("G");
        letters.add("H");

        input= input.toUpperCase();

        String output= "";

        A: if (input.contains("DIVIDE")) {
            // get the sequence of letters, and split it into two halves
            String cell= input.substring(8);
            String half1= cell.substring(0, cell.length()/2);
            String half2= cell.substring(cell.length()/2);

            if (isTesting) {
                System.out.println("Command: Divide");
                System.out.println("Cell: " + cell);
                System.out.println("First Half: "+ half1);
                System.out.println("Second Half: " + half2);
            }

            System.out.println("Sorting halves. . .");
            half1= alphabetize(half1);
            half2= alphabetize(half2);

            if (isTesting) {
                System.out.println("First Half: " + half1);
                System.out.println("Second Half: "+ half2);
            }

            // set the output variable to two cells containing both halves doubled
            output= half1 + half1 + " and " + half2 +  half2;

        } else if (input.contains("ADD")) {
            // get the number (n) after 'ADD', the cell, and the first n bits of the cell
            String number= input.substring(3, 4);
            int n= Integer.parseInt(number);
            String cell= input.substring(6);
            String firstBits= cell.substring(0, n);

            if (isTesting) {
                System.out.println("Cell: " + cell);
                System.out.println("n: " + n);
                System.out.println("First n Bits: " + firstBits);
            }

            // if n == 0, then assign the cell to output and break out of the if statement.
            if (n == 0) {
                output= cell;
                break A;
            }

            // alphabetize the string
            if (isTesting) {
                System.out.println("Sorting first " + n + " bits. . .");
            }
            String firstBits2= alphabetize(firstBits);

            if (isTesting) {
                System.out.println("First n Bits: " + firstBits2);
            }

            // concatenate the first n bits, the first n bits alphabetized, and the last couple of bits from the cell to make it 8 bits long.
            output= firstBits + firstBits2 + cell.substring(n);
            output= output.substring(0, 8);

        } else if (input.contains("SUBTRACT")) {
            // get n, the cell, and the bit segement excluding the first n bits
            String number= input.substring(8,9);
            int n= Integer.parseInt(number);
            String cell= input.substring(10);
            String noFirstBits= cell.substring(n+1);
            String lastBits= cell.substring(9-n);

            if (isTesting) {
                System.out.println("Cell: " + cell);
                System.out.println("n: " + n);
                System.out.println("Cell Without First n Bits: " + noFirstBits);
                System.out.println("Last n Bits: ");
            }

            // alphabetize the last n bits
            if (isTesting) {
                System.out.println("Alphabetizing bits. . .");
            }
            String lastBits2= alphabetize(lastBits);

            if (isTesting) {
                System.out.println("Last n Bits " + lastBits2);
            }

            // concatenate the cell without the first n bits and the alphabetized last n bits and assign to output.
            output= noFirstBits + lastBits2;

        } else if (input.contains("UNION")) {
            // get the cells, the last half of the first cell, and the first half of the second cell
            String cell1= input.substring(7,15);
            String cell2= input.substring(17);
            String lastHalf= cell1.substring(4);
            String firstHalf= cell2.substring(0,4);

            if (isTesting) {
                System.out.println("First Cell: " + cell1);
                System.out.println("Second Cell: " + cell2);
                System.out.println("Last Half of First Cell: " + lastHalf);
                System.out.println("First Half of Second Cell: " + firstHalf);
            }

            // alphabetize the two halves
            if (isTesting) {
                System.out.println("Alphabetizing halves. . .");
            }
            String lastHalf2= alphabetize(lastHalf);
            String firstHalf2= alphabetize(firstHalf);

            output= lastHalf2 + firstHalf2;

        } else if (input.contains("INTERSECT")) {
            // get the two cells, and delete the middle 4 bits of the first cell and second cell and concatenate the remaining bits of each cell
            String cell1= input.substring(11, 19);
            String cell2= input.substring(21);
            String cell1Bits= cell1.substring(0,2) + cell1.substring(6);
            String cell2Bits= cell2.substring(0,2) + cell2.substring(6);

            if (isTesting) {
                System.out.println("First Cell: " + cell1);
                System.out.println("Second Cell: " + cell2);
                System.out.println("First Cell Remaining Bits: " + cell1Bits);
                System.out.println("Second Cell Remaining Bits: " + cell2Bits);
            }

            // alphabetize the remaining bits of both cells
            if (isTesting) {
                System.out.println("Alphabetizing bits. . .");
            }
            String cell1Bits2= alphabetize(cell1Bits);
            String cell2Bits2= alphabetize(cell2Bits);

            if (isTesting) {
                System.out.println("First Cell Remaining Bits: " + cell1Bits2);
                System.out.println("Second Cell Remaining Bits: " + cell2Bits2);
            }

            // concatenate the first cell's bits to the second cell's bits and assign to output.
            output= cell1Bits2 + cell2Bits2;

        } else {
            return "Input invalid.";
        }


        if (isTesting) {
            System.out.println("============== End Debugging ==============");
            System.out.println();
        }
        return output;
    }

    private static String alphabetize(String bits) {
        ArrayList<String> letters= new ArrayList<>(8);
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");
        letters.add("F");
        letters.add("G");
        letters.add("H");

        // create an array to store the numbers corresponding to the letters in the bit string
        int[] ints= new int[bits.length()];

        // put the number corresponding to the letters into the int array (initialized above)
        for (int i = 0; i < ints.length; i++) {
            int num= letters.indexOf(bits.substring(i, i+1));
            ints[i]= num;
        }

        // sort the int array from least to greatest
        for (int num: ints) {
            for (int i = 1; i < ints.length; i++) {
                if(ints[i] < ints[i-1]){
                    int n= ints[i];
                    ints[i]= ints[i-1];
                    ints[i-1]= n;
                }
            }
        }

        String alphabetized= "";
        // using the int array, convert the organized back into letters
        for (int anInt : ints) {
            alphabetized = alphabetized + letters.get(anInt);
        }

        return alphabetized;
    }
}
