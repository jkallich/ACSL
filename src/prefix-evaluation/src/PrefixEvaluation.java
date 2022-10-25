import java.util.ArrayList;
import java.util.HashMap;

public class PrefixEvaluation {

    public static ArrayList<String> elements;
    public static final boolean isTesting= false;

    public static String evaluate (String input) {
        elements= createExpression(input);

        for(int i= elements.size()-1; i >= 0; i--) {
            if (!isNumeric(elements.get(i))) {
                int toRemove= evaluateOperator(i);
                i++;
            }
        }

        return elements.get(0);
    }

    /**
     * using the input, creates an ArrayList that has each operator and operand as a separate element
     * @param input to break down and get operands/operators from
     * @return ArrayList with operators/operands of given input
     */
    public static ArrayList<String> createExpression(String input) {
        if(isTesting) {
            System.out.println("Creating expression...");
        }

        String[] eArray= input.split(" ");
        ArrayList<String> elements= new ArrayList<>();
        for (int i = 0; i < eArray.length; i++) {
            elements.add(eArray[i]);
        }

        if(isTesting) {
            System.out.println("Printing expression operators and operands...");
            for(String e : elements) {
                System.out.println(e);
            }
            System.out.println();
        }

        return elements;
    }

    public static boolean isNumeric(String element) {
        if(isTesting) {
            System.out.println("Checking if numeric...");
            System.out.println("String: " + element);
        }

        boolean isNumeric= true;

        if (element == null) {
            isNumeric= false;
        } else {
            try {
                int n= Integer.parseInt(element);
            } catch (NumberFormatException err) {
                isNumeric= false;
            }
        }

        if(isTesting) {
            System.out.println("Is Numeric: " + isNumeric);
            System.out.println();
        }

        return isNumeric;
    }

    public static int evaluateOperator (int index) {
        String operator= elements.get(index);
        int a;
        int b;
        int c;
        switch (operator) {
            case "|":
                a= Integer.parseInt(elements.get(index+1));
                elements.set(index, "" + Math.abs(a));
                elements.remove(index+1);
                return 1;
            case "+":
                a= Integer.parseInt(elements.get(index+1));
                b= Integer.parseInt(elements.get(index+2));
                elements.set(index, "" + (a+b));
                elements.remove(index+1);
                elements.remove(index+1);
                return 2;
            case "-":
                a= Integer.parseInt(elements.get(index+1));
                b= Integer.parseInt(elements.get(index+2));
                elements.set(index, "" + (a-b));
                elements.remove(index+1);
                elements.remove(index+1);
                return 2;
            case "*":
                a= Integer.parseInt(elements.get(index+1));
                b= Integer.parseInt(elements.get(index+2));
                elements.set(index, "" + (a*b));
                elements.remove(index+1);
                elements.remove(index+1);
                return 2;
            case "@":
                a= Integer.parseInt(elements.get(index+1));
                b= Integer.parseInt(elements.get(index+2));
                c= Integer.parseInt(elements.get(index+3));
                if (a >= 0) {
                    elements.set(index, "" + b);
                } else {
                    elements.set(index, "" + c);
                }
                elements.remove(index+1);
                elements.remove(index+1);
                elements.remove(index+1);
                return 3;
            case ">":
                a= Integer.parseInt(elements.get(index+1));
                b= Integer.parseInt(elements.get(index+2));
                c= Integer.parseInt(elements.get(index+3));
                if(a>b && a>c) {
                    elements.set(index, "" + a);
                } else if (b>a && b>c) {
                    elements.set(index, "" + b);
                } else {
                    elements.set(index, "" + c);
                }
                elements.remove(index+1);
                elements.remove(index+1);
                elements.remove(index+1);
        }

        return 3;
    }
}
