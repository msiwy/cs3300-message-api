package restful;

/**
 * Created by samford on 6/24/15.
 */
public class DocumentDao {
    public Document getDocument(int documentId) {
        String query = "SELECT * FROM Document WHERE documentId = %d";
        String SQL = String.format(query, documentId);
        Document document = RDS.getTemplate().queryForObject(SQL, new DocumentMapper());
        return document;
    }

    public Document addDocument(String name, String type) {
        int documentId = (name + type).hashCode();
        String query = "INSERT INTO Documents (documentId, name, type) VALUES (%d, %s, %s)";
        String SQL = String.format(query, documentId, name, type);
        RDS.getTemplate().update(SQL);
        return new Document(documentId, name, type);
    }
}
