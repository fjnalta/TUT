package Data;

public class Würfel {

    public Würfel() {}

    public int werfen() {
        // Math random from 1.0 - 6.9
        return (int)(Math.random() * 6 + 1);
    }

    public int[] werfen(int[] array) {

        for(int i = 0; i < array.length; i++) {

            System.out.println(array[i]);
        }
        return array;
    }



}
