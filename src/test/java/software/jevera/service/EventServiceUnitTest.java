package software.jevera.service;

import org.junit.Before;
import org.junit.Test;
import software.jevera.dao.EventRrepository;
import software.jevera.domain.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
        User user = new User();

        Room room = new Room("LOL");
        Room room1 = new Room("LOL1");
        Room checkRoom = new Room("LOL2");

        LocalTime time = LocalTime.now();
        LocalTime checkTimeFrom = time.plusHours(3);
        LocalTime checkTimeTo = time.plusHours(4);
        LocalTime timeTo = time.plusHours(2);
        LocalTime timeTo1 = time.plusHours(4);
        LocalTime timeFrom = time.minusHours(2);

        LocalDate date = LocalDate.now();
        LocalDate dateCheck = date.plusDays(6);
        LocalDate startDate = date.plusDays(2);
        LocalDate endDate = date.plusDays(8);

        OnceTimeEvent event = new OnceTimeEvent(timeFrom,timeTo,room,LocalDate.now());
        event.setId(1L);
        OnceTimeEvent event2 = new OnceTimeEvent(LocalTime.now(),timeTo,checkRoom,LocalDate.now());

        OnceTimeEvent event3 = new OnceTimeEvent(LocalTime.now(),timeTo,room1,LocalDate.now());
        OnceTimeEvent check = new OnceTimeEvent(checkTimeFrom,checkTimeTo,checkRoom,LocalDate.now());
        check.setId(1L);

        PeriodicTimeEvent checkEvent = new PeriodicTimeEvent(startDate,endDate,"MONDAY",timeFrom,timeTo,room);

        List<OnceTimeEvent> testOnceList = new ArrayList<>();
        testOnceList.add(event);
        testOnceList.add(event2);
        testOnceList.add(event3);
        List<PeriodicTimeEvent> testPeriodicList = new ArrayList<>();
        testPeriodicList.add(checkEvent);

        when(eventRrepository.findAllOnce()).thenReturn(testOnceList);
        when(eventRrepository.findAllPeriodic()).thenReturn(testPeriodicList);
        when(eventRrepository.save(check)).thenReturn(check);

        Event result = eventService.createOnceEvent(check,user);

        assertEquals(check,result);
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
        Room room = new Room("LOL");
        Room checkRoom = new Room("LOL1");
        LocalTime timeTo = LocalTime.now();
        timeTo.plusHours(5);

        OnceTimeEvent event = new OnceTimeEvent(LocalTime.now(),timeTo,room,LocalDate.now());
        event.setId(1L);
        OnceTimeEvent event2 = new OnceTimeEvent(LocalTime.now(),timeTo,room,LocalDate.now());

        OnceTimeEvent testEvent = new OnceTimeEvent(LocalTime.now(),timeTo,checkRoom,LocalDate.now());
        OnceTimeEvent check = new OnceTimeEvent(LocalTime.now(),timeTo,checkRoom,LocalDate.now());
        check.setId(1L);

        List<OnceTimeEvent> testList = new ArrayList<>();
        testList.add(event);
        testList.add(testEvent);
        testList.add(event2);

        when(eventRrepository.findAllOnce()).thenReturn(testList);

        OnceTimeEvent result = (OnceTimeEvent) eventService.getOnceEventsByDateAndRoom(check).get(0);
        assertEquals(testEvent.getRoom(),result.getRoom());
        assertEquals(testEvent.getDate(),result.getDate());
    }

    @Test
    public void getPeriodicEventsByDateAndRoom() {
        Room room = new Room("LOL");
        Room checkRoom = new Room("LOL1");
        LocalTime timeTo = LocalTime.now();
       LocalTime time = timeTo.plusHours(5);
       String day = LocalDate.now().getDayOfWeek().toString();

        PeriodicTimeEvent event = new PeriodicTimeEvent(null,null,day
                ,null,null,room);
        PeriodicTimeEvent event1 = new PeriodicTimeEvent(null,null,day
                ,null,null,room);
        PeriodicTimeEvent test = new PeriodicTimeEvent(null,null,day
                ,null,null,checkRoom);

        OnceTimeEvent check = new OnceTimeEvent(LocalTime.now(),time,checkRoom,LocalDate.now());
        check.setId(1L);

        List<PeriodicTimeEvent> testList = new ArrayList<>();
        testList.add(event);
        testList.add(test);
        testList.add(event1);

        when(eventRrepository.findAllPeriodic()).thenReturn(testList);

        PeriodicTimeEvent result = (PeriodicTimeEvent) eventService.getPeriodicEventsByDateAndRoom(check).get(0);
        assertEquals(test.getRoom(),result.getRoom());
        assertEquals(test.getDay(),result.getDay());
    }

    @Test
    public void isTimeNotAvailable() {
         LocalTime time = LocalTime.now();
         LocalTime time1 = LocalTime.now();
         LocalTime timeTo = time.plusHours(2);
         LocalTime timeTo1 = time.plusHours(4);
         LocalTime timeFrom = timeTo.minusHours(2);

        OnceTimeEvent event = new OnceTimeEvent(timeFrom,timeTo,new Room("z"),LocalDate.now());
        event.setId(1L);
        OnceTimeEvent check = new OnceTimeEvent(timeTo,time,new Room("z"),LocalDate.now());
        check.setId(1L);

        List<Event> testList = new ArrayList<>();
        testList.add(event);

        boolean result = eventService.isTimeNotAvailable(testList,check);
        assertEquals(true,result);
    }


    @Test
    public void getAllPeriodicEventsInBounds() {
        Room room = new Room("LOL");
        Room checkRoom = new Room("LOL1");
        LocalTime timeTo = LocalTime.now();
        timeTo.plusHours(5);

        PeriodicTimeEvent event = new PeriodicTimeEvent(null,null,"SUNDAY"
                ,null,null,room);
        PeriodicTimeEvent event1 = new PeriodicTimeEvent(null,null,"SUNDAY"
                ,null,null,room);
        PeriodicTimeEvent test = new PeriodicTimeEvent(null,null,"SUNDAY"
                ,null,null,checkRoom);
    }

    @Test
    public void getAllOnceEventsInBounds() {
        Room room = new Room("LOL");

        LocalTime time = LocalTime.now();
        LocalTime timeTo = time.plusHours(2);
        LocalTime timeFrom = timeTo.minusHours(2);
        LocalDate date = LocalDate.now();
        LocalDate dateCheck = date.plusDays(6);
        LocalDate startDate = date.plusDays(2);
        LocalDate endDate = date.plusDays(8);

        OnceTimeEvent onceEvent = new OnceTimeEvent(timeFrom,timeTo,room,dateCheck);
        onceEvent.setId(1L);
        OnceTimeEvent onceEvent2 = new OnceTimeEvent(timeTo,time,new Room("z"),LocalDate.now());
        onceEvent2.setId(2L);

        PeriodicTimeEvent checkEvent = new PeriodicTimeEvent(startDate,endDate,"MONDAY",timeFrom,timeTo,room);

        List<OnceTimeEvent> testList = new ArrayList<>();
        testList.add(onceEvent);
        testList.add(onceEvent2);

        when(eventRrepository.findAllOnce()).thenReturn(testList);
        Event result = eventService.getAllOnceEventsInBounds(checkEvent).get(0);
        assertEquals(onceEvent, result);
    }
}