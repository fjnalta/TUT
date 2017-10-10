public class Main {

    public static void main(String[] args) {

        Auto myAuto = new Auto();
        Auto myAuto2 = new Auto("red");
        Auto myAuto3 = new Auto(200);
        Auto myAuto4 = new Auto("green", 250);


        System.out.println(myAuto.toString());
        System.out.println(myAuto2.toString());
        System.out.println(myAuto3.toString());
        System.out.println(myAuto4.toString());


    }



}
