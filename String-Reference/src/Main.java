public class Main {

    public static void main(String[] args) {

        String a = "Hallo";
        String b = "Welt";

        swap(a,b);

        System.out.println(a + " " + b);

    }

    private static void swap(String a, String b) {
        String tmp = a;
        a = b;
        b = tmp;
    }
}
