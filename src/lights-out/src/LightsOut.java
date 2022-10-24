import java.util.HashMap;

public class LightsOut {

    private static boolean[][] initialBoard;
    private static boolean[][] endingBoard;
    private static HashMap<String, String> hexToBinary;
    private static boolean isTesting= true;
    private static boolean isTesting2= true;

    public static void main(String[] args) {
        System.out.println(getBinary("002070C810200000"));
        System.out.println("0000-0000 0010-0000 0111-0000 1100-1000 0001-0000 0010-0000 0000-0000 0000-0000");
        System.out.println(getBinary("002070C810200000").equals("0000000000100000011100001100100000010000001000000000000000000000"));

        System.out.println();

        System.out.println(getBinary("0020 70D8 285c 3810"));
        System.out.println("0000-0000 0010-0000 0111-0000 1101-1000 0010-1000 0101-1100 0011-1000 0001-0000");
        System.out.println(getBinary("0020 70D8 285c 3810").equals("0000000000100000011100001101100000101000010111000011100000010000"));

    }

    public static String lights_out (String initialHex, String endingHex) {
        createHexToBinary();

        createBoard(initialHex, endingHex);

        for (int i = 0; i < initialBoard.length; i++) {
            for (int j = 0; j < initialBoard[i].length; j++) {
                if (initialBoard[i][j] != endingBoard[i][j]) {
                    if (checkSurrounding(i, j)) {
                        clearBoard();
                        return "" + (8 - i) + (j + 1);
                    }
                }
            }
        }

        clearBoard();
        return "u did something wrong :p";
    }
    
    private static void createHexToBinary() {
        hexToBinary= new HashMap<>();
        hexToBinary.put("0", "0000");
        hexToBinary.put("1", "0001");
        hexToBinary.put("2", "0010");
        hexToBinary.put("3", "0011");
        hexToBinary.put("4", "0100");
        hexToBinary.put("5", "0101");
        hexToBinary.put("6", "0110");
        hexToBinary.put("7", "0111");
        hexToBinary.put("8", "1000");
        hexToBinary.put("9", "1001");
        hexToBinary.put("A", "1010");
        hexToBinary.put("B", "1011");
        hexToBinary.put("C", "1100");
        hexToBinary.put("D", "1101");
        hexToBinary.put("E", "1110");
        hexToBinary.put("F", "1111");
    }

    private static void printBoards () {
        for (boolean[] booleans : initialBoard) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    System.out.print("0 ");
                } else {
                    System.out.print("+ ");
                }
            }

            System.out.println();
        }

        System.out.println("\n");

        for (boolean[] booleans : endingBoard) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    System.out.print("0 ");
                } else {
                    System.out.print("+ ");
                }
            }

            System.out.println();
        }
    }

    /**
     * creates the boards based on the hexadeciaml boar codes provided
     * @param initialHex the starting board's hex code
     * @param endingHex the ending board's hex code
     */
    private static void createBoard (String initialHex, String endingHex) {
        initialBoard= new boolean[8][8];
        endingBoard= new boolean[8][8];
        
        String initialBinary= getBinary(initialHex);
        String endingBinary= getBinary(endingHex);
        
        int index= 0;
        // int index= initialBinary.length()-1;
        for(int i= 7; i >= 0; i--) {
            // for(int i= 0; i < initialBoard.length; i++) {
            for (int j = 0; j < initialBoard[i].length; j++) {
                if (initialBinary.charAt(index) == '1') {
                    initialBoard[i][j]= true;
                } else {
                    initialBoard[i][j]= false;
                }

                if (endingBinary.charAt(index) == '1') {
                    endingBoard[i][j]= true;
                } else {
                    endingBoard[i][j]= false;
                }

                index++;
            }
        }

        if(isTesting2) {
            printBoards();
        }

    }
    
    private static String getBinary (String hexCode) {
        createHexToBinary();
        String binary= "";
        hexCode= hexCode.toUpperCase();
        hexCode= hexCode.replaceAll(" ", "");

        if (isTesting) {
            System.out.println("Board Hex Code: " + hexCode);
            System.out.print("Board Binary Code: ");
        }

        for(int i= 0; i < hexCode.length(); i++) {
            String ltr= hexCode.substring(i, i+1);
            binary= binary + hexToBinary.get(ltr);

            if(isTesting) {
                System.out.print(hexToBinary.get(ltr) + " ");
            }
        }

        System.out.println();
        
        return binary;
    }

    /**
     * checks the surrounding tiles of the the given tile if they are inverted in the ending board
     * @param row the row the given tile is in
     * @param col the column the given tile is in
     * @return true if all of the surrounding tiles are inverted + their surrounding tiles are inverted
     */
    public static boolean checkSurrounding (int row, int col){

        boolean topInverted= true;
        boolean bottomInverted= true;
        boolean rightInverted= true;
        boolean leftInverted= true;
        boolean topTopInverted= true;
        boolean topRightInverted= true;
        boolean rightRightInverted= true;
        boolean bottomRightInverted= true;
        boolean bottomBottomInverted= true;
        boolean bottomLeftInverted= true;
        boolean leftLeftInverted= true;
        boolean topLeftInverted= true;

        if (row > 0) {
            topInverted= initialBoard[row-1][col] != endingBoard[row-1][col];
        }
        
        if (row < 7) {
            bottomInverted= initialBoard[row+1][col] != endingBoard[row+1][col];
        }
        
        if (col > 0) {

            rightInverted= initialBoard[row][col-1] != endingBoard[row][col-1];
        }
        
        if (col < 7) {
            leftInverted= initialBoard[row][col+1] != endingBoard[row][col + 1];
        }

        if( row > 1) {
            topTopInverted= initialBoard[row-2][col] != endingBoard[row-2][col];
        }

        if (row > 0 && col > 0) {
            topRightInverted= initialBoard[row-1][col-1] != endingBoard[row-1][col-1];
        }

        if (col > 1) {
            rightRightInverted= initialBoard[row][col-2] != endingBoard[row][col-2];
        }

        if (row < 7 && col > 0) {
            bottomRightInverted= initialBoard[row+1][col-1] != endingBoard[row+1][col-1];
        }

        if (row < 6) {
            bottomBottomInverted= initialBoard[row+2][col] != endingBoard[row+2][col];
        }

        if (row < 7 && col < 7) {
            bottomLeftInverted= initialBoard[row+1][col+1] != endingBoard[row+1][col+1];
        }

        if (col < 6) {
            leftLeftInverted= initialBoard[row][col+2] != endingBoard[row][col+2];
        }

        if (row > 0 && col < 7) {
            topLeftInverted= initialBoard[row-1][col+1] != endingBoard[row-1][col+1];
        }

        return rightInverted && leftInverted && topInverted && bottomInverted && topTopInverted && topRightInverted && rightRightInverted && bottomRightInverted && bottomBottomInverted && bottomLeftInverted && leftLeftInverted && topLeftInverted;

    }

    private static void createTestBoards() {
        initialBoard= new boolean[8][8];
        endingBoard= new boolean[8][8];
        
        initialBoard[3][1]= true;
        initialBoard[3][2]= true;
        initialBoard[4][2]= true;
        initialBoard[4][3]= true;

        endingBoard[2][2]= true;
        endingBoard[3][3]= true;
        endingBoard[4][0]= true;
        endingBoard[4][1]= true;
        endingBoard[4][4]= true;
        endingBoard[5][1]= true;
        endingBoard[5][2]= true;
        endingBoard[5][3]= true;
        endingBoard[6][2]= true;

        if (isTesting2) {
            printBoards();
        }
    }

    private static void clearBoard () {
        for(int i= 0; i < initialBoard.length; i++) {
            for (int j = 0; j < initialBoard[i].length; j++) {
                initialBoard[i][j]= false;
                endingBoard[i][j]= false;
            }
        }
    }
}
