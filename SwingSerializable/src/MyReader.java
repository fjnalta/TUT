import java.io.*;

public class MyReader {

    TestObject myContent;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public MyReader() {
        myContent = new TestObject();
        oos = null;
        ois = null;
    }

    public void writeObjectToFile(String text) {
        // reset Stream
        oos = null;

        TestObject to = new TestObject();
        to.setContent(text);

        // create Output Stream
        try {
            oos = new ObjectOutputStream(new FileOutputStream("out.ser"));

            oos.writeObject(to);
            System.out.println(to.getContent() + " written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readObjectFromFile() {
        // reset Stream
        ois = null;
        TestObject finalObject = new TestObject();
        // create Input Stream
        try {
            ois = new ObjectInputStream(new FileInputStream("out.ser"));
            TestObject to = (TestObject) ois.readObject();
            finalObject = to;
            System.out.println(to.getContent() + " read");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return finalObject.getContent();
    }
 }
