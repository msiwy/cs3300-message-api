package restful;

import com.mysql.jdbc.Blob;

/**
 * Created by samford on 6/24/15.
 */
public class Document {
    private int documentId;
    private String type;
    private String name;
    private Blob content;
    private String filepath;

    public Document(int documentId, String name, String type, String filepath) {
        this.documentId = documentId;
        this.type = type;
        this.name = name;
//        this.content = content;
        this.filepath = filepath;
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

    public String getFilepath() {
        return filepath;
    }
}

