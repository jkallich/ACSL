public class IR {
    public final float a;
    public final float b;

    public IR (float a, float b) {
        this.a = Math.round(a*100)/100;
        this.b = Math.round(b*100)/100;
    }

    public static IR add(IR num1, IR num2) {
        return new IR(num1.a + num2.a, num1.b + num2.b);
    }

    public static IR multiply(IR num1, IR num2) {
        float ac= num1.a * num2.a;
        float newB= (num1.a * num2.b) + (num1.b * num2.a); //complex coefficient, b
        float bd= -1 * (num1.b * num2.b);
        float newA= ac + bd;

        return new IR(newA, newB);
    }

    public static IR square(IR num) {
        return num.multiply(num, num);
    }

    public static double abs(IR num) {
        float a2= num.a * num.a;
        float b2= num.b * num.b;
        return Math.sqrt(a2 + b2);
    }

    public String toString () {
        if(b < 0) {
            return a + " + " + (-b) + "i";
        }

        return a + " + " + b + "i";
    }
}
