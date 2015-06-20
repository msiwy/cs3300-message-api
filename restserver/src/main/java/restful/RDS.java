package restful;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Created by shauvik on 6/20/15.
 */
public class RDS {
    private static MysqlDataSource ds;
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
}

