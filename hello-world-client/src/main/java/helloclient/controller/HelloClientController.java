package helloclient.controller;

import helloclient.domain.Greeting;
import helloclient.domain.SystemConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloClientController {
    @Autowired
    SystemConfiguration systemConfiguration;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name,
                            @RequestParam(value="id", defaultValue="0") Long id) {
    	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX greeting ("+name+", "+id+") XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    	RestTemplate restTemplate = new RestTemplate();
    	String uri = systemConfiguration.getRemoteURI();
    	Greeting result = restTemplate.getForObject(uri + "/greeting?name="+name, Greeting.class);
        return new Greeting(id, result.getContent());
    }
}
