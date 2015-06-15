package main.java.hello;

/**
 * Created by gatsby on 6/4/15.
 */
public class Error {
    private final long id;
    private final String content;

    public Error(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
