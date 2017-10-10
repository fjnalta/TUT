public class Main {

    public static void main(String[] args) {

        String a = "Hallo";
        String b = "Welt";

        int x = 5;
        int y = 9;

        swap(a,b);

        System.out.println(a + " " + b);

        swap(x,y);

        System.out.println(x + " " + y);

    }

    private static void swap(int x, int y) {
        int tmp = x;
        x = y;
        y = tmp;
    }

    private static void swap(String a, String b) {
        String tmp = a;
        a = b;
        b = tmp;
    }
}
