package restful;

import java.sql.*;

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

        String sql = "INSERT INTO Greeting (name) VALUES (?)";
        int last_inserted_id = RDS.updateAndGetGenKey(sql, gName);

        return new Greeting(last_inserted_id,
                String.format(template, name));
    }
}