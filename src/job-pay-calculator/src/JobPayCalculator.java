import java.util.HashMap;
import java.util.Scanner;

public class JobPayCalculator {

    public static HashMap<String, Double> hours;
    public static boolean isTesting= true;

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        hours= new HashMap<>();
        hours.put("1", 9.0);
        hours.put("2", 9.5);
        hours.put("3", 10.0);
        hours.put("4", 10.5);
        hours.put("5", 11.0);
        hours.put("6", 11.5);
        hours.put("7", 12.0);
        hours.put("8", 12.5);
        hours.put("9", 13.0);
        hours.put("A", 13.5);
        hours.put("B", 14.0);
        hours.put("C", 14.5);
        hours.put("D", 15.0);
        hours.put("E", 15.5);
        hours.put("F", 16.0);
        hours.put("G", 16.5);
        hours.put("H", 17.0);

        System.out.print("Enter employee's code: ");
        String code= scanner.nextLine();

        if (isTesting){
            System.out.println("\n\n\n************ TESTING ************");
        }

        double cost= returnPay(code);

        if(isTesting){
            System.out.println("************   END   ************\n" +
                    "\n" +
                    "\n");
        }

        System.out.println("$" + cost);
    }

    public static double returnPay(String code){
        String[] codes= code.split(", ");

        int loc= Integer.parseInt(codes[0]);
        int day= Integer.parseInt(codes[1]);
        Double h1= hours.get(codes[2]);
        Double h2= hours.get(codes[3]);

        if(isTesting){
            System.out.println("--------Day 1");
        }
        double cost= calculatePay(loc, day, h1, h2);

        loc= Integer.parseInt(codes[4]);
        day= Integer.parseInt(codes[5]);
        h1= hours.get(codes[6]);
        h2= hours.get(codes[7]);

        if(isTesting){
            System.out.println("--------Day 2");
        }
        cost+= calculatePay(loc, day, h1, h2);

        return cost;
    }

    public static double calculatePay(int loc, int day, double h1, double h2) {

        double time= h2-h1;
        double cost= 0;

        if(isTesting){
            System.out.println("Location: " + loc);
            System.out.println("Day: " + day);
            System.out.println("Start: " + h1);
            System.out.println("End: " + h2);
            System.out.println("Total Time: " + time);
        }

        if (loc >= 100 && loc <= 199) {
            if (time <= 5.0) {
                cost+= 10.0*time;
            } else {
                cost+= 10.0 * 5;
                time-= 5;
                cost+= 15.0*time;
            }
        } else if (loc >= 200 && loc <= 299) {
            if (time <= 6) {
                cost+= 7.5 * time;
            } else {
                cost+= 7.5*6;
                time-= 6;
                cost+= 7.5*2*time;
            }
        } else if (loc >= 300 && loc <= 399) {
            if (time <= 4) {
                cost+= 9.25*time;
            } else {
                cost+= 9.25*4;
                time-=4;
                cost+= 10.50 * time;
            }
        }  else if (loc >= 400 && loc <= 499) {
            if(day == 1 || day == 7){
                cost+= 13.50*time;
            }else{
                cost+= 6.75*time;
            }
        } else if (loc >= 500 && loc <= 599) {
            if (time <= 6) {
                cost+= 8.0 * time;
            }
            if(time > 6){
                cost+= 8.0 * 6;
                time-=6;
                cost+= 12.0 * time;
            }
        }

        if(isTesting){
            System.out.println("Cost: " + cost);
        }
        return cost;
    }
}
