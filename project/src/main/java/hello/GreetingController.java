package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="Developers") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    private static final String template1 = "This is an error page, %s";
    private final AtomicLong counter1 = new AtomicLong();

    @RequestMapping("/error1")
    public Error error(@RequestParam(value="id", defaultValue="default") String name) {
        return new Error(counter1.incrementAndGet(),
                String.format(template1, name));
    }


}
