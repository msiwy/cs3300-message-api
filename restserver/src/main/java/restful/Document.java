package restful;

/**
 * Created by samford on 6/24/15.
 */
public class Document {
    private int documentId;
    private String type;
    private String name;

    public Document(int documentId, String name, String type) {
        this.documentId = documentId;
        this.type = type;
        this.name = name;
//        this.documentId = (name + type).hashCode();
    }

    public int getDocumentId() {
        return documentId;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

