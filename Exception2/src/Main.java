public class Main {


    public static void main(String[] args) {

        int[] a = new int[5];

        System.out.println("Die Arraylänge beträgt: " + a.length);

        try {
            crashArray(a);
        } catch (MyArrayException e) {
            System.out.println("Exception weitergereicht und in der Main gefangen.");
        }


        crashArrayAndHandleException(a);

    }


    static void crashArray(int[] array) throws MyArrayException {

        for(int i = 0; i < array.length + 1; i++) {

            if(array.length <= i) {
                throw new MyArrayException();
            }


            System.out.println("Bin an Stelle: " + i);
        }
    }

    static void crashArrayAndHandleException(int[] array) {

        for(int i = 0; i < array.length + 1; i++) {

            try {

                if(array.length <= i) {
                    throw new MyArrayException();
                }

            } catch (MyArrayException e) {

                System.out.println("Exception direkt in der Methode gefangen");
            }

            System.out.println("Bin an Stelle: " + i);
        }
    }


}
