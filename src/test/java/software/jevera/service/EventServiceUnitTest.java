package software.jevera.service;

import org.junit.Before;
import org.junit.Test;
import software.jevera.dao.EventRrepository;
import software.jevera.domain.Event;
import software.jevera.domain.OnceTimeEvent;
import software.jevera.domain.Room;
import software.jevera.domain.User;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EventServiceUnitTest {

    private EventService eventService;

    private EventRrepository eventRrepository;

    @Before
    public void before() {
       eventRrepository = mock(EventRrepository.class);

       eventService = new EventService(eventRrepository);

    }
    @Test
    public void createOnceEventIsValid() {

        OnceTimeEvent onceEvent = new OnceTimeEvent(LocalTime.now(),LocalTime.now().plusHours(1l)
                , new Room("LOL"),LocalDate.now());
        User user = new User();
        when(eventRrepository.save(onceEvent)).thenReturn(onceEvent);
        eventService.createOnceEvent(onceEvent, user);
        Event result = eventService.createOnceEvent(onceEvent, user);
        assertNotNull(result);
        assertEquals(result, onceEvent);
    }

    @Test
    public void createPeriodicEvent() {
    }

    @Test
    public void getOnceEventsByDateAndRoom() {
    }

    @Test
    public void getPeriodicEventsByDateAndRoom() {
    }

    @Test
    public void isTimeNotAvailable() {
    }

    @Test
    public void assertTimeForPeriodic() {
    }

    @Test
    public void getAllPeriodicEventsInBounds() {
    }

    @Test
    public void getAllOnceEventsInBounds() {
    }

    @Test
    public void getAllEvents() {
    }

    @Test
    public void getEventsById1() {
    }
}