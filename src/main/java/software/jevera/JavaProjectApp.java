package software.jevera;


import software.jevera.domain.Event;
import software.jevera.domain.User;
import software.jevera.domain.UserDto;

import static software.jevera.configuration.ApplicationFactory.eventService;
import static software.jevera.configuration.ApplicationFactory.userService;

public class JavaProjectApp {

    public static void main(String[] args) {
        User user = userService.registerUser(new UserDto("userlogin1", "password"));
        eventService.createEvent(new Event(), user);
        System.out.println(eventService.getAllEvents());;
    }

}
