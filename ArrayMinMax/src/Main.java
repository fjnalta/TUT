import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    // Create Array
    static int[][] array = new int[3][4];

    public static void main(String[] args) {

        // Fill Array with random numbers
        fillArray(array);

        int result = verarbeite(array);
        // Aufgabe 2A
        System.out.println("Das Ergebnis lautet: " + result);

        // Aufgabe 2B
        System.out.println("Es gibt " + getAnzGroeßerAls(array, result) + " größere Zahlen im Array");

    }


    // Aufgabe 2A
    public static int verarbeite(int[][] array) {

        String menu_selection = "";
        Integer result = null;

        System.out.println("Bitte wählen Sie die gewünschte Aktion aus: ");
        System.out.println("1: Minimum");
        System.out.println("2: Maximum");
        System.out.println("3: Mittelwert");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            menu_selection = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check if whole array != null
        if (array != null) {

            // Menu Selection
            switch (menu_selection) {
                // search maximum
                // Calculate Minimum
                case "1":
                    for (int i = 0; i < array.length; i++) {

                        // check if second dimension exists
                        if (array[i] != null) {

                            // search minimum
                            for (int j=0; j < array[i].length; j++) {

                                if(result == null) {
                                    result = array[i][j];
                                } else {
                                    if(array[i][j] < result) {
                                        result = array[i][j];
                                    }
                                }

                            }

                        } else {
                            i++;
                        }
                    }
                    return result;

                // Calculate Maximum
                case "2":

                    for (int i = 0; i < array.length; i++) {

                        // check if second dimension exists
                        if (array[i] != null) {

                            // search minimum
                            for (int j=0; j < array[i].length; j++) {

                                if(result == null) {
                                    result = array[i][j];
                                } else {
                                    if(array[i][j] > result) {
                                        result = array[i][j];
                                    }
                                }

                            }

                        } else {
                            i++;
                        }
                    }
                    return result;

                // Calculate average
                case "3":
                    result = 0;
                    for (int i = 0; i < array.length; i++) {

                        // check if second dimension exists
                        if (array[i] != null) {

                            // get all values
                            for (int j = 0; j < array[i].length; j++) {
                                if (result == null) {
                                    result = array[i][j];
                                }
                                result = result + array[i][j];
                            }

                            result = result / (array.length + array[i].length);

                        }
                    }
                    return result;

                // Default behaviour - return 0
                default:
                    return 0;
            }

        } else {

            return 0;

        }
    }

    // Aufgabe 2B
    public static int getAnzGroeßerAls(int [][] array, int min) {
        int count = 0;

        for (int i = 0; i < array.length; i++) {

            // check if second dimension exists
            if (array[i] != null) {

                // search values greater than "min"
                for (int j=0; j < array[i].length; j++) {

                    if(min < array[i][j]) {
                        count++;
                    }
                }

            } else {
                i++;
            }
        }
        return count;
    }

    // Helper Methods
    public static void fillArray(int[][] array) {
        for(int i=0; i<array.length; i++) {
            for(int j=0; j<array[i].length; j++) {
                int number = (int)(Math.random()*100);
                array[i][j] = number;
                System.out.print(number + " ");
            }
        }

        System.out.println();
    }
}