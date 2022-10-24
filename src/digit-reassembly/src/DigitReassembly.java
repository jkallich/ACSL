import java.math.BigInteger;

public class DigitReassembly {

    static boolean isTesting= false;

    public static void main(String[] args) {
        String result= evaluate("265472 5");
        System.out.println(result);
    }
    
    public static String evaluate (String input) {
        String[] inputs= input.split(" ");
        int length= Integer.parseInt(inputs[1]);
        if(isTesting) {
            System.out.println(input);
            System.out.println(inputs[0]);
            System.out.println(inputs[1]);
            System.out.println();
        }

        if(isTesting) {
            System.out.println("string: " + inputs[0] + "  length: " + inputs[0].length());
            System.out.println("len: " + length);
        }

        long sum= 0;
        int start= 0;
        for(int i= 1; i <= inputs[0].length(); i++){
            if(inputs[0].substring(start).length() < length) {
                String s= inputs[0].substring(start);
                long l= Long.parseLong(s);

                l *= Math.pow(10, length-s.length());

                if(isTesting) {
                    System.out.println("len: " + length);
                    System.out.println("s length: " + s.length());
                    System.out.println("need to add: " + (length-s.length()));

                    System.out.println("s: " + s);
                    System.out.println("l: " + l);
                }

                sum+= l;
                break;
            }

            if(i%length == 0) {
                String s= inputs[0].substring(start, i);
                long n= Long.parseLong(s);
                sum+= n;

                if(isTesting) {
                    System.out.println("s: " + s);
                    System.out.println("n: " + n);
                }
                start= i;
            }


        }

        return "" + sum;
    }
}
