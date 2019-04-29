import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;

@RestController
@EnableAutoConfiguration(exclude=[ActiveMQAutoConfiguration.class])
class WebApp{
      @GetMapping("/")
      String welcome(){
        "<h1><font face="verdana">Spring Boot Rocks!</font></h1>"
     }
}
