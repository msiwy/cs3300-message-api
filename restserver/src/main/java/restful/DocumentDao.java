package restful;

import com.mysql.jdbc.Blob;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Types;

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

    public Document create(String name, String type, String filepath ) {
        try {
            int documentId = (name + type).hashCode();
            File image = new File(filepath);
//            File image = new File("/Users/samford/Desktop/sprite.png");
            System.out.println("\n\nFile Created\n\n");
            InputStream imageIs = new FileInputStream(image);
            System.out.println("\n\nInputStream\n\n");
            LobHandler lobHandler = new DefaultLobHandler();
            System.out.println("\n\nLobHandler\n\n");
            String query = "INSERT INTO Document (documentId, name, type, content) VALUES (?, ?, ?, ?)";
            RDS.getTemplate().update(
                    query,
                    new Object[]{
                            documentId, name, type, new SqlLobValue(imageIs, (int) image.length(), lobHandler)
                    },
                    new int[]{
                            Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.BLOB
                    }
            );


            return new Document(documentId, name, type, filepath);
        } catch (Exception e) {
            System.out.println("\n\nStack Trace:::: ");
            e.printStackTrace();
            System.out.println("\n\n");
            return new Document(-1, null, null, null);
        }
    }

}
