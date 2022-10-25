import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        PrefixEvaluation.evaluate("* + 3 2 20");

        System.out.println(PrefixEvaluation.elements);
    }
}
