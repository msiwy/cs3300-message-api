package restful;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by samford on 6/24/15.
 */
public class DocumentMapper implements RowMapper<Document> {
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        Document document = new Document(rs.getInt("documentId"), rs.getString("name"), rs.getString("type"));
        return document;
    }
}
