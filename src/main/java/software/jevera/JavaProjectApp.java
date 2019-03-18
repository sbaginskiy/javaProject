package main.java.software.jevera;


import main.java.software.jevera.domain.Event;
import main.java.software.jevera.domain.User;
import main.java.software.jevera.domain.UserDto;

import static main.java.software.jevera.configuration.ApplicationFactory.eventService;
import static main.java.software.jevera.configuration.ApplicationFactory.userService;

public class JavaProjectApp {

    public static void main(String[] args) {
        User user = userService.registerUser(new UserDto("userlogin", "password"));
        eventService.createEvent(new Event(), user);
        System.out.println(eventService.getAllEvents());;
    }

}
