import java.util.ArrayList;

public class NinetyNine {

    private static boolean isTesting= false;

    public static void main(String[] args) {
        String[] input1= { "8, 9, Q, 6, 7, K, A, 5, 9, 8",
                          "75, J, 7, Q, T, A, 6, 2, 3, 4, 5",
                          "50, 7, K, T, 8, 3, Q, 9, 7, 2, 3",
                          "63, 3, 6, 8, T, 7, 7, T, 3, 5, 8",
                          "79, A, 9, 7, T, A, 9, T, A, 6, 4",
                          "50, A, T, Q, A, T, K, J, T, A, 7"};

        String[] input2= { "A, K, K, 4, 9, K, A, Q, 9, T",
                          "70, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7",
                          "84, A, 7, 9, T, K, 7, Q, 9, 5, 6",
                          "55, T, A, A, K, K, Q, Q, J, J, T",
                          "51, 7, 7, 7, 7, 6, 6, 4, 4, 4, 4",
                          "30, T, Q, 7, 7, A, Q, J, K, Q, A"};

        ArrayList<String> output= play(input2);

        for(String s : output) {
            System.out.println(s);
        }
    }

    public static ArrayList<String> play (String[] input) {
        if (isTesting) {
            System.out.println("Beginning play()...");
        }
        ArrayList<String> output= new ArrayList<>();
        ArrayList<Integer> player1Cards= new ArrayList<>();
        ArrayList<Integer> player2Cards= new ArrayList<>();
        
        String[][] inputs= new String[input.length][10];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i]= input[i].split(", ");
        }

        int turn;
        for (int i = 1; i < inputs.length; i++) {
            int total= Integer.parseInt(inputs[i][0]);
            String winner= "";
            turn= 1;

            if(isTesting) {
                System.out.println("Game " + i + "-----------\nTOTAL: " + total);
            }

            player1Cards.clear();
            for (int k = 0; k < 5; k++) {
                player1Cards.add(evaluate(inputs[0][k]));
            }

            player2Cards.clear();
            for (int k = 5; k < inputs[0].length; k++) {
                player2Cards.add(evaluate(inputs[0][k]));
            }

            int j= 0;
            while (true) {
                j++;
                sort(player1Cards);
                sort(player2Cards);

                if(isTesting){
                    System.out.print("Player 1 Cards: ");
                    print(player1Cards);

                    System.out.print("Player 2 Cards: ");
                    print(player2Cards);
                }

                int toPlace;
                if(turn > 0) {
                    toPlace= player1Cards.remove(2);
                    if(j<inputs[i].length) {
                        player1Cards.add(evaluate(inputs[i][j]));
                    }
                } else {
                    toPlace= player2Cards.remove(2);
                    if(j<inputs[i].length) {
                        player2Cards.add(evaluate(inputs[i][j]));
                    }
                }

                if (isTesting) {
                    System.out.println("Player " + turn + "'s turn >> They have a card with the value: " + toPlace);

                    if (j < inputs[i].length) {
                        System.out.println("Player " + turn + " draws a " + evaluate(inputs[i][j]) + " to add to their cards.");
                    } else {
                        System.out.println("Ran out of cards to draw. :/");
                    }
                }
                
                if (toPlace == 7 && (total < 100 && total+7 >= 100)) {
                    toPlace = 1;
                } else if (toPlace == 9) {
                    toPlace= 0;
                } else if (toPlace == 10) {
                    toPlace= -10;
                }
                
                if((total <= 33 && total+toPlace >= 34) || (total <= 55 && total+toPlace >= 56) || (total <= 77 && total+toPlace >= 78)) {
                    toPlace+= 5;

                    if(isTesting) {
                        System.out.println("Crossing Threshold... added 5 to points added to Total.");
                    }
                }
                
                total+= toPlace;
                turn*= -1;

                if(isTesting) {
                    System.out.println("The final value added to the total is " + toPlace);
                    System.out.println("TOTAL: " + total);
                }
                
                if(total > 99) {
                    if (turn > 0) {
                        winner= ", Player #1";
                    } else {
                        winner= ", Player #2";
                    }
                    break;
                }
            }

            output.add(total + winner);

            if(isTesting) {
                System.out.println();
            }
        }

        return output;
    }

    private static void print (ArrayList<Integer> arr) {
        for(Integer i : arr) {
            System.out.print(i + " ");
        }

        System.out.println();
    }

    private static int evaluate (String faceVal) {
        if (faceVal.equals("T")){
            return 10;
        } else if (faceVal.equals("J")) {
            return 11;
        } else if (faceVal.equals("Q")) {
            return 12;
        } else if (faceVal.equals("K")) {
            return 13;
        } else if (faceVal.equals("A")) {
            return 14;
        }

        return Integer.parseInt(faceVal);
    }

    public static void sort(ArrayList<Integer> arr){
        for (int i = 0; i < arr.size() - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < arr.size(); j++){
                if (arr.get(j) < arr.get(index)){
                    index = j;//searching for lowest index
                }
            }
            int smallerNumber = arr.get(index);
            arr.set(index, arr.get(i));
            arr.set(i, smallerNumber);
        }
    }
}
