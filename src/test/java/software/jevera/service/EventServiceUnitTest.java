package software.jevera.service;

import org.junit.Before;
import org.junit.Test;
import software.jevera.dao.EventRrepository;
import software.jevera.domain.*;

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
    public void createOnceEvent() {

        OnceTimeEvent onceEvent = new OnceTimeEvent(LocalTime.now(),LocalTime.now().plusHours(1l)
                , new Room("LOL"),LocalDate.now());
        User user = new User();
        when(eventRrepository.save(onceEvent)).thenReturn(onceEvent);
        Event result = eventService.createOnceEvent(onceEvent, user);
        assertNotNull(result);
        assertEquals(result, onceEvent);
    }

    @Test
    public void createPeriodicEvent() {
        PeriodicTimeEvent periodicEvent = new PeriodicTimeEvent(LocalDate.now()
                ,LocalDate.now().plusDays(5),"TUESDAY", LocalTime.now(), LocalTime.now().plusHours(2)
                , new Room("Room name"));
        User user = new User();
        when(eventRrepository.save(periodicEvent)).thenReturn(periodicEvent);
        Event result = eventService.createPeriodicEvent(periodicEvent, user);
        assertNotNull(result);
        assertEquals(result, periodicEvent);
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