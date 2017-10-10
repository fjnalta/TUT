public class Main {

    public static void main(String[] args) {

        System.out.println("Iterativ:" + fakultaetIterativ(5));
        System.out.println("Rekursiv:" + fakultaetRekursiv(5));


    }

    static int fakultaetIterativ (int n) {
        int ergebnis = 1;

        for (int i = 1; i <= n; i++) {
            ergebnis = ergebnis * i;
        }

        return ergebnis;
    }

    static int fakultaetRekursiv (int n) {

        if (n == 1) {

            return 1;

        } else {

            return fakultaetRekursiv(n - 1) * n;
        }
    }


}
