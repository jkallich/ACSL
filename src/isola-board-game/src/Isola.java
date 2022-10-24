import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Isola {

    public  boolean[][] board;
    public static HashMap<String, Integer> positions;
    public int[] player1= new int[2];
    public int[] player2= new int[2];
    private final boolean isTesting= false;

    public  String play (String input) {

        createBoard();
        createMap();

        // convert input into an array of integers (positions of removed tiles/players)
        if(isTesting) {
            System.out.println("Getting array of single int positions...");
        }
        String[] numStrings= input.split(", ");
        int[] nums= new int[numStrings.length-1];
        for(int i= 0; i < nums.length; i++) {
            nums[i]= Integer.parseInt(numStrings[i]);

            if(isTesting) {
                System.out.println(nums[i]);
            }
        }
        //

        if(isTesting) {
            System.out.println();
        }

        // get the players' positions and store
        /* player1: Player X
           player2: Player +
         */
        if(isTesting) {
            System.out.println("Getting players' array positions...");
        }

        String p1Pos= getKey(nums[1]);
        String p2Pos= getKey(nums[0]);

        player1[0]= Integer.parseInt(p1Pos.substring(0,1));
        player1[1]= Integer.parseInt(p1Pos.substring(1));
        player2[0]= Integer.parseInt(p2Pos.substring(0,1));
        player2[1]= Integer.parseInt(p2Pos.substring(1));
        //

        if(isTesting) {
            System.out.println("Player 1 (X) Integer Position: " + nums[1]);
            System.out.printf("Player 1 (X) Array Position: [%d][%d]\n", player1[0], player1[1]);
            System.out.println("Player 2 (+) Integer Position: " + nums[0]);
            System.out.printf("Player 2 (+) Array Position: [%d][%d]\n", player2[0], player2[1]);
        }

        // remove all tiles in the nums array
        for (int pos : nums) {
            String rowCol= getKey(pos);
            int r= Integer.parseInt(rowCol.substring(0,1));
            int c= Integer.parseInt(rowCol.substring(1));
            board[r][c]= false;
        }
        //

        // check all of the directions from Player X to see if it can get adjacent to and block the other player
        ArrayList<Integer> tilesToRemove= new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(i==0 && j==0) {
                    continue;
                }
                tilesToRemove= checkDirection(i, j);
                if(tilesToRemove.get(0) != 50) {
                    j= 5;
                    i= 5;
                }
            }
        }
        //

        if(tilesToRemove.get(0) == 50) {
            return "NONE";
        }


        String output= "" + tilesToRemove.get(0);
        for(int i= 1; i < tilesToRemove.size(); i++) {
            output= output + ", " + tilesToRemove.get(i);
        }
        return output;
    }

    /**
     * initializes the board array and sets all values equal to zero
     */
    public  void createBoard() {
        if(isTesting) {
            System.out.println("Creating board and setting all elements to true...");
        }

        board= new boolean[7][7];
        for (boolean[] value : board) {
            Arrays.fill(value, true);
        }

        // if(isTesting) {
        //     System.out.println();
        //     System.out.println("Printing board...");
        //     for (boolean[] booleans : board) {
        //         for (boolean b : booleans) {
        //             System.out.println(b);
        //         }
        //     }
        //     System.out.println();
        // }
    }

    public void createMap () {
        if(isTesting) {
            System.out.println("Creating positions HashMap...");
        }
        positions= new HashMap<>();

        int pos= 1;
        for(int i= 6; i>=0;i--){
            for(int j= 0; j < board[i].length ; j++) {
                positions.put("" + i + j, pos);
                if(isTesting) {
                    System.out.printf("Key: %s, Value: %d\n", "" + i + j, pos);
                }

                pos++;
            }
        }
    }

    public static String getKey (int value) {
        for(String key : positions.keySet()) {
            if(positions.get(key) == value) {
                return key;
            }
        }

        return "no key for given value";
    }

    public ArrayList<Integer> checkDirection(int rDir, int cDir) {
        if(isTesting){
            System.out.printf("Checking dir(r: %d, c: %d)....\n", rDir, cDir);
        }

        boolean[][] copy= new boolean[board.length][board[0].length];
        for(int i= 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                copy[i][j]= board[i][j];
            }
        }

        int row= player1[0] + rDir;
        int col= player1[1] + cDir;

        ArrayList<Integer> path= new ArrayList<>();
        ArrayList<Integer> notWorking= new ArrayList<>();
        notWorking.add(50);

        while (row < board.length && row >= 0 && col < board[row].length && col >= 0) {
            if (!board[row][col]) {
                return notWorking;
            }

            if(isTesting) {
                System.out.println();
            }

            int pos= positions.get("" + row + col);
            path.add(pos);
            copy[row][col]= false;

            if(isAdjacent(row, col) && !canMove(row, col)) {
                return path;
            }

            row+= rDir;
            col+= cDir;
        }

        return notWorking;
    }

    public boolean isAdjacent(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for(int j= -1; j <= 1; j++) {
                if(i == 0 && j == 0) {
                    continue;
                }

                if(row+i==player2[0] && col+j==player2[1]){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean canMove(int row, int col) {
        for(int i= -1; i <= 1; i++) {
            for(int j= -1; j <= 1; j++) {
                if(i == 0 && j == 0) {
                    continue;
                }

                int r= player2[0] + i;
                int c= player2[1] + j;

                if(r >= board.length || r < 0 || c >= board[0].length || c < 0) {
                    continue;
                }

                if(r==row && c==col) {
                    continue;
                }

                if(board[r][c]) {
                    return true;
                }
            }
        }

        return false;
    }
}
