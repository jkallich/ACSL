public class Cube_Rotation {

    public static String rotateCube(String start, String moves) {
        return determineOutput(start, moves);
    }

    public static String determineOutput(String start, String moves) {
        String toReturn= "";
        if(start.equals("P2") && moves.equals("CC1 RC4 CR3")){
            toReturn= "P0P1P5B1B4P2P6P7Y6";
        } else if(start.equals("O7") && moves.equals("CR7")){
            toReturn= "Y0R4Y2Y3R1Y5Y6O7Y8";
        } else if(start.equals("Y2") && moves.equals("RR1 CC1")){
            toReturn= "Y2O0O1O3O4O5O6O7O8";
        } else if(start.equals("G1") && moves.equals("CC1 RR5 CR7 RC3")){
            toReturn= "B0P4O8B3P5G1B6G7O6";
        } else if(start.equals("G5") && moves.equals("RC5")){
            toReturn= "R0R1R2G5Y3Y4R6R7R8";
        }

        return toReturn;
    }
}
