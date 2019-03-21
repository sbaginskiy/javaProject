package software.jevera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import software.jevera.domain.OnceTimeEvent;
import software.jevera.domain.User;
import software.jevera.service.EventService;

@SpringBootApplication
public class JavaProjectApp {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JavaProjectApp.class, args);

        EventService eventService = context.getBean(EventService.class);
        eventService.createOnceEvent(new OnceTimeEvent(), new User());
        System.out.println(eventService.getAllEvents());
    }

}
