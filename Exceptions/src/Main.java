public class Main {


    public static void main(String[] args) {

        int dividend = 3;
        int divisor = 0;

        try {

            double result = divide(dividend, divisor);

            // Bei einer Exception wird das Println nicht mehr aufgerufen
            System.out.println("Result: " + result);


        } catch (DivisionByZeroException e) {
            e.printStackTrace();
        }
    }

    public static double divide(int dividend, int divisor) throws DivisionByZeroException {
        double result = 0;

        if (divisor != 0) {

            result = dividend / divisor;
        } else {

            // werfe exception wenn durch 0 geteilt wird
            throw new DivisionByZeroException();
        }

        return result;
    }
}
