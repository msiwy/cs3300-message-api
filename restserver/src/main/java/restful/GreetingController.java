package restful;

import java.sql.*;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;


/**
 * Created by shauvik on 6/20/15.
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {

        String gName = String.format(template, name);

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        int last_inserted_id = -1;
        try {
            conn = Application.getDatasource().getConnection();
            String sql = "INSERT INTO Greeting (name) VALUES (?)";

            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, gName);

            stmt.executeUpdate();
            rset = stmt.getGeneratedKeys();

            if(rset.next())
            {
                last_inserted_id = rset.getInt(1);
            }

        } catch (SQLException sqe) {
            System.err.println("SQL ERROR");
            sqe.printStackTrace();
        } finally {
            try { if (rset != null) rset.close(); } catch(Exception e) { }
            try { if (stmt != null) stmt.close(); } catch(Exception e) { }
	        try { if (conn != null) conn.close(); } catch(Exception e) { }
        }

        return new Greeting(last_inserted_id,
                String.format(template, name));
    }
}