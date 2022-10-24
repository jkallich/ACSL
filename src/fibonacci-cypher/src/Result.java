import java.util.*;

import static java.util.stream.Collectors.joining;


class Result {

    /*
     * Complete the 'fibCypher' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. CHARACTER option
     *  2. INTEGER num1
     *  3. INTEGER num2
     *  4. CHARACTER key
     *  5. STRING msg
     */

    private static ArrayList<Character> messageCharacters;
    private static ArrayList<Long> fibonacciSequence;
    private static ArrayList<Integer> nums;
    private static ArrayList<Character> alphabet;

    private static boolean isTesting= true;

    public static String fibCypher(char option, int num1, int num2, char key, String msg) {
        messageCharacters= new ArrayList<>();
        fibonacciSequence= new ArrayList<>();
        nums= new ArrayList<>();
        alphabet= new ArrayList<>();

        createAlphabet();

        if (isTesting) {
            System.out.println("option: " + option);
            System.out.println("num1: " + num1);
            System.out.println("num1: " + num2);
            System.out.println("key: " + key);
            System.out.println("msg: " + msg);
            System.out.println();
        }

        if (option == 'E' || option == 'e') {
            interpretMsg(msg, true);
            createFibonacci(num1, num2, msg.length());
            return encode(key);
        } else {
            interpretMsg(msg, false);
            createFibonacci(num1, num2, msg.split(" ").length);
            return decode(key);
        }
    }

    /**
     * initializes the alphabet list with all of the lowercase letter of the alphabet
     */
    private static void createAlphabet () {
        alphabet.add('a');
        alphabet.add('b');
        alphabet.add('c');
        alphabet.add('d');
        alphabet.add('e');
        alphabet.add('f');
        alphabet.add('g');
        alphabet.add('h');
        alphabet.add('i');
        alphabet.add('j');
        alphabet.add('k');
        alphabet.add('l');
        alphabet.add('m');
        alphabet.add('n');
        alphabet.add('o');
        alphabet.add('p');
        alphabet.add('q');
        alphabet.add('r');
        alphabet.add('s');
        alphabet.add('t');
        alphabet.add('u');
        alphabet.add('v');
        alphabet.add('w');
        alphabet.add('x');
        alphabet.add('y');
        alphabet.add('z');

        if(isTesting) {
            System.out.println("Printing alphabet...");
            System.out.println(alphabet);
            System.out.println();
        }
    }

    /**
     * using the indicated first two numbers of the sequence, creates a fibonacci sequence of the length indicated
     * @param n1 first number of the fibonacci sequence
     * @param n2 second number of the fibonacci sequence
     * @param length length of the fibonacci sequence
     */
    private static void createFibonacci(int n1, int n2, int length) {
        fibonacciSequence.add((long) n1);
        fibonacciSequence.add((long) n2);
        long prevBy2= n1;
        long prevBy1= n2;

        for (int i= 0; i < length; i++) {
            long n= prevBy2 + prevBy1;
            fibonacciSequence.add(prevBy2+prevBy1);
            prevBy2= prevBy1;
            prevBy1= n;
        }

        if (isTesting) {
            System.out.println("Printing fibonacciSequence...");
            System.out.println(fibonacciSequence);
            System.out.println();
        }
    }

    /**
     * takes a string and puts each character into the stringChars list if encoding
     * or puts each number into the nums list
     * @param s message to encode
     * @param encode true if encoding, false if decoding
     */
    private static void interpretMsg(String s, boolean encode) {

        //        for(int i= 0; i < stringNums.length; i++) {
        //            if (encode){
        //                messageCharacters.add(s.charAt(i));
        //            } else {
        //
        //            }
        //
        //        }

        if (encode) {
            for (int i = 0; i < s.length(); i++) {
                messageCharacters.add(s.charAt(i));
            }
        } else {
            String[] stringNums= s.split(" ");
            for (int i = 0; i < stringNums.length; i++) {
                nums.add(Integer.parseInt(stringNums[i]));
            }
        }

        if (isTesting) {
            if (encode) {
                System.out.println("Printing stringChars...");
                System.out.println(messageCharacters);
                System.out.println();
            } else{
                System.out.println("Print nums...");
                System.out.println(nums);
                System.out.println();
            }

        }
    }

    /**
     * encodes the message from characters into numbers
     * @param key the key to encode the message
     * @return the encoded message
     */
    private static String encode (char key) {

        for (int i = 0; i < messageCharacters.size(); i++) {
            char ltr= messageCharacters.get(i);
            long fibNum= fibonacciSequence.get(i);

            long offset;
            if (i%2 == 0) {
                offset= alphabet.indexOf(key) + fibNum;
            } else {
                offset= alphabet.indexOf(key) - fibNum;
            }

            if (offset < 0) {
				// offset= alphabet.size() - (Math.abs(offset))%alphabet.size();
                offset= alphabet.size() + offset%alphabet.size();
				// offset= alphabet.size() + offset;
            }

            if (isTesting) {
                System.out.println("Encoding...");
                System.out.println("i: " + i);
                System.out.println("key: " + key);
                System.out.println("key index: " + alphabet.indexOf(key));
                System.out.println("fibNum: " + fibNum);
                System.out.println("offset: " + offset);
            }

            if (offset >= alphabet.size()) {
                offset %= alphabet.size();
            }

            //            if (offset < 0) {
            //                offset= Math.abs(offset);
            //            }

            if (isTesting) {
                System.out.println("offset (after overflow check): " + offset);
            }

            char offsetLtr= alphabet.get((int) offset);

            int ltrASCII= ltr;
            int offsetLtrASCII= offsetLtr;
            if (isTesting) {
                System.out.println("ltr (to encode): " + ltr);
                System.out.println("offsetLtr: " + offsetLtr);
                System.out.println("ltr ASCII: " + ltrASCII);
                System.out.println("offsetLtr ASCII: " + offsetLtrASCII);
            }

            int num= ltrASCII + (offsetLtrASCII * 3);

            nums.add(num);

            if(isTesting) {
                System.out.println("Encoded char: " + (ltrASCII + offsetLtrASCII*3));
                System.out.println("--");
            }

        }

        String numString= "";
        for (int i= 0; i < nums.size(); i++) {
            if (i == nums.size() - 1){
                numString= numString + nums.get(i);
            } else {
                numString= numString + nums.get(i) + " ";
            }
        }

        return numString;
    }

    /**
     * decodes the numbers given into a string message
     * @param key used to encode the message
     * @return the decoded message
     */
    private static String decode (char key) {

        String message= "";

        for (int i = 0; i < nums.size(); i++) {
            int num= nums.get(i);
            long fibNum= fibonacciSequence.get(i);

            long offset;
            if (i%2 == 0) {
                offset= alphabet.indexOf(key) + fibNum;
            } else {
                offset= alphabet.indexOf(key) - fibNum;
            }

            if (offset < 0) {
                offset= alphabet.size() - (Math.abs(offset))%alphabet.size();
            }

            if (isTesting) {
                System.out.println("Decoding...");
                System.out.println("key: " + key);
                System.out.println("offset: " + offset);
                System.out.println("num (to decode): " + num);
            }

            if (offset >= alphabet.size()) {
                offset %= alphabet.size();
            }

            if (isTesting) {
                System.out.println("offset (after overflow check): " + offset);
            }

            char offsetLtr= alphabet.get((int) offset);

            int offsetLtrASCII= offsetLtr;
            if (isTesting) {
                System.out.println("offsetLtr: " + offsetLtr);
                System.out.println("offsetLtr ASCII: " + offsetLtrASCII);
            }

            char ltr= (char) (num - (3*offsetLtrASCII));

            message = message + ltr;

            if(isTesting) {
                System.out.println("Decoded char: " + ltr);
                System.out.println("--");
            }

        }

        return message;
    }
}
