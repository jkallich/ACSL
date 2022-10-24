import java.util.HashMap;

public class Walk {

    private static int[][] grid= new int[8][8];
    private static HashMap<String, Integer> directions= new HashMap<>();
    private static boolean isTesting= false;

    public static void main(String[] args) {
        initializeGrid("4F9D39, DEB456, 3DA8B9, A57CA7, 26A84A, 2FCFA3, 3AAB09, 89CBF5");
    }

    public static void initializeGrid(String hexa) {
        if(isTesting){
            System.out.println("========== Begin initializeGrid() Debugging ==========");
        }
        String[] hexaCodes= hexa.split(", ");

        if (isTesting) {
            System.out.println("Original Hexadecimal String: " + hexa);
            System.out.println("\nHexadecimal String in List:");
            for (String s : hexaCodes) {
                System.out.println(s);
            }
        }

        String[] octalCodes= new String[8];

        for (int i=0 ; i < hexaCodes.length; i++) {
            int num = Integer.parseInt(hexaCodes[i], 16);
            octalCodes[i]=  Integer.toString(num, 8);
        }

        if (isTesting) {
            System.out.println("\nOctal Codes:");
            for (String s : octalCodes) {
                System.out.println(s);
            }
        }

        for(int i= 7; i >= 0; i--) {
            String code= octalCodes[7-i];
            for (int j = 0; j < 8; j++) {
                String ltr= code.substring(j, j+1);
                grid[i][j]= Integer.parseInt(ltr);
            }
        }

        if (isTesting) {
            System.out.println("\nGrid:");
            for (int[] nums : grid) {
                for (int n : nums) {
                    System.out.print(n + "    ");
                }
                System.out.println("\n");
            }
        }
        if (isTesting) {
            System.out.println("=========== End initializeGrid() Debugging ===========");
        }

        directions.put("A", 0);
        directions.put("R", 90);
        directions.put("B", 180);
        directions.put("L", 270);
    }

    public static String walk(String input) {
        if(isTesting) {
            System.out.println("=============== Begin walk() Debugging ===============");
        }

        int startingRow= Integer.parseInt(input.substring(0,1));
        int startingCol= Integer.parseInt(input.substring(3,4));
        int direction= directions.get(input.substring(6,7).toUpperCase());
        int repetitions= Integer.parseInt(input.substring(9));

        if (isTesting) {
            System.out.println("Command: " + input);
            System.out.println("startingRow: " + startingRow);
            System.out.println("startingCol: " + startingCol);
            System.out.println("direction: " + direction);
            System.out.println("repetitions: " + repetitions);
        }

        int row= startingRow;
        int col= startingCol;
        int rDirection= 0;
        int cDirection= 0;
        for (int i= 0; i < repetitions; i++) {
            if (isTesting) {
                System.out.println("__ Begin iteration __");
            }

            int angle= getAngle(direction, grid[8-row][col-1]);

            if(isTesting) {
                System.out.println("angle: "+ angle);
                System.out.println("direction: " + direction);
            }

            if (angle == 0 || angle == 180) {
                cDirection= 0;
            }else if (angle < 180) {
                cDirection= 1;
            } else {
                cDirection= -1;
            }

            if (angle == 90 || angle == 270) {
                rDirection= 0;
            } else if (angle < 90 || angle > 270) {
                rDirection= 1;
            } else {
                rDirection= -1;
            }

            if (isTesting) {
                System.out.println("rDirection: " + rDirection);
                System.out.println("cDirection: " + cDirection);
                System.out.println("row: " + row);
                System.out.println("col: " + col);
            }

            row+= rDirection;
            col+= cDirection;

            if (isTesting) {
                System.out.println("new row: " + row);
                System.out.println("new col: " + col);
            }

            if (row > 8) {
                row= 1;
            } else if (row <= 0) {
                row= 8;
            }

            if (col > 8) {
                col= 1;
            } else if (col <= 0) {
                col= 8;
            }

            if (isTesting) {
                System.out.println("new row (for overflow): " + row);
                System.out.println("new col (for overflow): " + col);
            }

            if (angle >= 180) {
                direction= angle - 180;
            } else {
                direction= angle + 180;
            }

            if (isTesting) {
                System.out.println("new direction: " + direction);
                System.out.println("___ End iteration ___");
            }
        }

        if (isTesting) {
            System.out.println("================ End walk() Debugging ================");
        }
        return row + ", " + col;
    }

    private static int getAngle(int direction, int gridNum) {

        int angle= direction;

        angle+= gridNum*45;
        angle%= 360;
        return angle;
    }
}
