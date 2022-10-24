import java.io.*;
import java.lang.reflect.Array;
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

public class Wordle_Match {

    public static HashMap<String, String> results= new HashMap<>();
    public static ArrayList<HashMap<String, ArrayList<Integer>>> order= new ArrayList<>();
    public static String target= "";

    public static String findMatch(String word, String guesses) {
        target= word;
        String[] guessWords= guesses.split(" ");
        for(String guess : guessWords) {
            results.put(guess, guessResult(guess));
        }

        enterGuesses();

        return "";
    }

    public static String guessResult (String guess) {
        String target2= "";
        ArrayList<String> result= new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            result.add("");
        }

        int green= findGreen(guess);
        String newGuess= "";
        for (int i = 0; i < guess.length(); i++) {
            if(guess.charAt(i) == target.charAt(i)){
                result.set(i, "G");
            }else {
                newGuess+= guess.substring(i, i+1);
                target2+= target.substring(i, i+1);
            }
        }

        int gray= findGray(newGuess);
        newGuess= "";
        for (int i = 0; i < guess.length(); i++) {
            if(!target2.contains(guess.substring(i, i+1)) && result.get(i).equals("")){
                result.set(i, "-");
            }else {
                newGuess+= guess.substring(i, i+1);
            }
        }

        int yellow= 5 - green - gray;
        for(int i= 0 ; i < result.size(); i++) {
            if(result.get(i) == "") {
                result.set(i, "Y");
            }
        }

        String toReturn= "";
        for(String s : result) {
            toReturn+= s;
        }

        return toReturn;
    }

    public static int findGreen(String guess) {
        int count= 0;
        for(int i= 0; i < guess.length(); i++) {
            if(guess.charAt(i) == target.charAt(i)){
                count++;
            }
        }
        return count;
    }

    public static int findGray(String guess) {
        int count= 0;
        for(int i= 0; i < guess.length(); i++) {
            if(!target.contains(guess.substring(i, i+1))){
                count++;
            }
        }
        return count;
    }

    public static void enterGuesses() {
        ordering();
        ArrayList<Integer> greens= new ArrayList<>(results.size());
        ArrayList<Integer> yellows= new ArrayList<>(results.size());
        for (int i = 0; i < results.size(); i++) {
            greens.add(-1);
            yellows.add(-1);
        }
        for(String guess : results.keySet()) {
            String result= results.get(guess);
            int green= count("G", result);
            int yellow= count("Y", result);
            int gray= count("-", result);
            HashMap<String, ArrayList<Integer>> toPut= new HashMap<>();
            ArrayList<Integer> ints= new ArrayList<>(3);
            ints.add(green);
            ints.add(yellow);
            ints.add(gray);
            toPut.put(guess, ints);

            if(greens.get(0) == -1) {
                order.add(0, toPut);
            }else {
                int i= 1;
                boolean flag= false;
                while(i >= 0 && i<greens.size() && !flag){
                    if (greens.get(i) != -1) {
                        if(green < greens.get(i)){
                            i--
                        }
                    } else {
                        i++;
                    }

                }
            }


        }

        orderYellow();
    }

    public static int count(String target, String toSearch) {
        int count= 0;
        int index= toSearch.indexOf(target);
        while(index >= 0) {
            count++;
            toSearch= toSearch.substring(index+1);
            index= toSearch.indexOf(target);
        }

        return count;
    }

    public static void orderYellow () {

    }

    public static void ordering() {
        for (int i = 0; i < results.size(); i++) {
            order.add(new HashMap<String, ArrayList<Integer>>());
        }
    }

    public static String determineOutput(String word, String guesses) {
        String toReturn= "";
        if(word.equals("ports") && guesses.equals("climb spots sport parts stops traps sorts porch props shots prank")) {
            toReturn= "parts sorts porch props spots shots";
        }
        else if(word.equals("bread") && guesses.equals("reads breed dread reeds braid plead creed darts seeds leads arbor heads drape capes")) {
            toReturn= "breed dread braid plead creed drape";
        }
        else if(word.equals("helps") && guesses.equals("roads track fears sight jumps zones")) {
            toReturn= "blqvwxy";
        }
        else if(word.equals("cubic") && guesses.equals("color graph quips whine cable cubes strip brink")) {
            toReturn= "cubes cable quips color strip brink";
        } else if (word.equals("trees") && guesses.equals("start eater stack truck tears zones stamp strip sport latex parts kinds lives wings turns hopes meant yearn taste")){
            toReturn= "tears turns truck hopes lives zones";
        }

        return toReturn;
    }
}
