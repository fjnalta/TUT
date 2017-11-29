import java.io.Serializable;

public class TestObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String content;

    public TestObject() {
        this.content = "";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
