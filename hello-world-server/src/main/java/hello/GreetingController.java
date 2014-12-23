package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String defaultTemplate = "Hello, %s!";
    private static final String templateEnvironmentVariable = "HelloWorldTemplate";
    private static String foundTemplate = null;
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(getTemplate(), name));
    }
    
    private String getTemplate() {
    	if(foundTemplate == null) {
        	String template = System.getenv(templateEnvironmentVariable);
        	if(template == null || template.length() == 0) {
        		foundTemplate = defaultTemplate;
        	} else {
        		foundTemplate = template;
        	}
    	}
    	return foundTemplate;
    }
}
