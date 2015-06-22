package restful;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.Objects;

/**
 * Created by shauvik on 6/20/15.
 */
public class RDS {
    private static MysqlDataSource ds;

    private static JdbcTemplate template;

    public static void init() {
        template = new JdbcTemplate(getDatasource());
    }


    public static JdbcTemplate getTemplate() {
        if(template == null) {
            init();
        }
        return template;
    }

    public static MysqlDataSource getDatasource() {
        if(ds == null) {
            ds = new MysqlDataSource();
            ds.setServerName("aaguzohhf6ht13.c1jgqriefhdd.us-east-1.rds.amazonaws.com");
            ds.setDatabaseName("cs3300");
            ds.setUser("cs3300");
            ds.setPassword("J4nOBNgx");
        }
        return ds;
    }

    public static void update(String sql, Object ...args) {

    }

    public static int updateAndGetGenKey(String sql, Object ...args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        int last_inserted_id = -1;
        try {
            conn = RDS.getDatasource().getConnection();

            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int i = 1;

            for(Object arg : args) {
                if(arg instanceof String) {
                    stmt.setString(i++, (String) arg);
                }
            }

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
        return last_inserted_id;
    }

}

