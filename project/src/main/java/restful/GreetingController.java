package restful;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/")
    public String home() {
        return "CS3300 TEAM-API";
    }

    private static final String template = "This is an error page, %s";
    private final AtomicLong counter = new AtomicLong();


}
