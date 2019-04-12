package software.jevera.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.jevera.domain.Event;
import software.jevera.domain.OnceTimeEvent;
import software.jevera.domain.User;
import software.jevera.domain.dto.EventMapper;
import software.jevera.domain.dto.OnceEventDto;
import software.jevera.service.EventService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class EventResource {

    private final EventService eventService;
    private final HttpSession session;
    private final EventMapper eventMapper;

    public EventResource(EventService eventService, HttpSession session, EventMapper eventMapper) {
        this.eventService = eventService;
        this.session = session;
        this.eventMapper = eventMapper;
    }

    @PostMapping("/event")
    public Event create (OnceEventDto onceEventDto){

        return eventService.createOnceEvent(eventMapper.toOnceTimeEvent(onceEventDto), getUser());
    }

    private User getUser() {
        return (User)session.getAttribute("user");
    }
}
