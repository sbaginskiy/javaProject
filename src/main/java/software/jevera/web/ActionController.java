package software.jevera.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import software.jevera.service.EventService;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequiredArgsConstructor
public class ActionController    {

    private final EventService eventService;
    @RequestMapping(value = "/helloworld", method = GET)
    public String helloworld(EventService eventService, HttpSession session) {
        return "helloworld";
    }

}
