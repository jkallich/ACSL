import java.util.HashMap;

public class MapNavigation {

    private static boolean isTesting = false;

    public static void main(String[] args) {
        HashMap<Character, Integer> distances= new HashMap<>();
        initialize(distances);
        evaluate("C,F,11,PM,2,AM,50,60,");
    }

    public static String evaluate (String input) {
        HashMap<Character, Integer> distances= new HashMap<>();
        initialize(distances);
        String[] iParts= split(input);

        if(isTesting){
            System.out.println("distances:");
            for(Character c : distances.keySet()) {
                System.out.println(c + ":" + distances.get(c));
            }

            System.out.println("\n\niParts:");
            for(String s : iParts) {
                System.out.println(s);
            }
            System.out.println();
        }

        // get variables
        int totalDistance= getDistance(iParts[0].charAt(0), iParts[1].charAt(0), distances);
        int startTime1= getTime(iParts[2], iParts[3]);
        int startTime2= getTime(iParts[4], iParts[5]);
        int speed1= Integer.parseInt(iParts[6]);
        int speed2= Integer.parseInt(iParts[7]);
        int difference= Math.abs(startTime2 - startTime1);

        if (isTesting) {
            System.out.println("Difference: " + difference);
        }

        if (Math.abs(startTime2 - startTime1) < 24 - Math.abs(startTime2 - startTime1)) {
            difference= Math.abs(startTime2 - startTime1);
        } else {
            difference= 24 - Math.abs(startTime2 - startTime1);
        }

        if (isTesting) {
            System.out.println("Total Distance: " + totalDistance);
            System.out.println("First Traveler Start Time: " + startTime1 + "   First Traveler Starting City: " + iParts[0] + "   First Traveler Speed: " + speed1);
            System.out.println("Second Traveler Start Time: " + startTime2 + "   Second Traveler Starting City: " + iParts[1] + "   Second Traveler Speed: " + speed2);
            System.out.println("Time Difference: " + difference);
            System.out.println("time = (totalDistance - speed2*difference) / (speed1 + speed2)");
            System.out.printf("time = (%d - %d*%d) / (%d + %d)\n", totalDistance, speed2, difference, speed1, speed2);
        }

        double time = (double) (totalDistance + speed2*difference) / (speed1 + speed2);

        int hours= (int) time;
        int minutes= (int) Math.round((time % 1) * 100.0/100.0 * 60.0);

        if (isTesting) {
            System.out.println("Time Double: " + time);
            System.out.println("Hours: " + hours);
            System.out.println("Minutes: " + minutes);
        }

        if (hours > 12) {
            hours-= 12;
        } else if (hours < 0) {
            hours+= 12;
        }

        if (minutes < 0) {
            minutes+= 60;
        }

        if (isTesting) {
            System.out.println("Time Double: " + time);
            System.out.println("Hours: " + hours);
            System.out.println("Minutes: " + minutes);
        }

        String output= hours + ":";
        if(minutes >= 0 && minutes < 10) {
            output+= "0";
        }
        output+= minutes;

        if (isTesting) {
            System.out.println("Output: " + output);
        }

        return output;
    }

    /**
     * Initializes HashMap distances.
     */
    private static void initialize (HashMap<Character, Integer> distances) {
        distances.put('A', 450); // A -> B
        distances.put('B', 140); // B -> C
        distances.put('C', 125); // C -> D
        distances.put('D', 365); // D -> E
        distances.put('E', 250); // E -> F
        distances.put('F', 160); // F -> G
        distances.put('G', 380); // G -> H
        distances.put('H', 235); // H -> J
        distances.put('J', 320); // J -> K
    }

    private static String[] split (String toSplit) {
        String[] parts= toSplit.split(",");
        for(int i= 0; i < parts.length; i++) {
            parts[i]= parts[i].trim();
        }

        return parts;
    }

    /**
     * given a time and an AM/PM, returns a number representing the original time in military hours
     * @param number consists of a number indicating time (in morning/afternoon)
     * @param time AM or PM
     * @return
     */
    private static int getTime (String number, String time) {
        if (time.equals("PM")) {
            return Integer.parseInt(number) + 12;
        }

        return Integer.parseInt(number);
    }

    private static int getDistance (char point1, char point2, HashMap<Character, Integer> distances) {
        if(isTesting){
            System.out.println("Getting distance...");
        }
        int distance= 0;
        for(char i= point1; i < point2; i++) {
            if (i == 'J' - 1){
                continue;
            }

            distance+= distances.get(i);
        }

        return distance;
    }
}
