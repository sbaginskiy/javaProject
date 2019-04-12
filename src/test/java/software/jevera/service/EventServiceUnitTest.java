package software.jevera.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import software.jevera.dao.EventRrepository;
import software.jevera.domain.*;
import software.jevera.exceptions.BussinesException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceUnitTest {
    @InjectMocks
    private EventService eventService;
    @Mock
    private EventRrepository eventRrepository;

    @Test(expected = BussinesException.class)
    public void createOnceEvent() {
        User user = new User();
        OnceTimeEvent check = preparationToCreateOnceEvent(5, minusLocalTime(2), 1);
        eventService.createOnceEvent(check,user);
    }

    @Test
    public void createOnceEventOk() {
        User user = new User();
        OnceTimeEvent check = preparationToCreateOnceEvent(4, plusLocalTime(5), 6);
        Event result = eventService.createOnceEvent(check,user);
        assertEquals(check,result);
    }

    @Test(expected = BussinesException.class)
    public void createPeriodicEvent() {
        User user = new User();
        PeriodicTimeEvent check = preparationToCreatePeriodicEvent(plusLocalTime(2));
        eventService.createPeriodicEvent(check, user);
    }

    @Test
    public void createPeriodicEventOk() {
        User user = new User();
        PeriodicTimeEvent check = preparationToCreatePeriodicEvent(minusLocalTime(2));
        Event result = eventService.createPeriodicEvent(check, user);
        assertEquals(check,result);
    }

    @Test
    public void getOnceEventsByDateAndRoom() {
        List<OnceTimeEvent> testOnceList = asList(
        new OnceTimeEvent().room(createRoom("type1")).date(plusLocalDate(0)),
        new OnceTimeEvent().room(createRoom("type2")).date(plusLocalDate(0)),
        new OnceTimeEvent().room(createRoom("type3")).date(plusLocalDate(0)));
        OnceTimeEvent check = new OnceTimeEvent().room(createRoom("type3")).date(plusLocalDate(0));

        when(eventRrepository.findAllOnce()).thenReturn(testOnceList);
        OnceTimeEvent result = (OnceTimeEvent) eventService.getOnceEventsByDateAndRoom(check).get(0);
        assertEquals(check.getRoom(),result.getRoom());
        assertEquals(check.getDate(),result.getDate());
    }

    @Test
    public void getPeriodicEventsByDayAndRoom() {
        Room checkRoom = createRoom("type3");
        PeriodicTimeEvent event = new PeriodicTimeEvent().day(getNowDay()).room(createRoom("type1"));
        PeriodicTimeEvent event1 = new PeriodicTimeEvent().day(getNowDay()).room(createRoom("type2"));
        PeriodicTimeEvent event2 = new PeriodicTimeEvent().day(getNowDay()).room(checkRoom);
        OnceTimeEvent check = new OnceTimeEvent().date(plusLocalDate(0)).room(checkRoom);
        List<PeriodicTimeEvent> testList = new ArrayList<>();
        testList.add(event);
        testList.add(event1);
        testList.add(event2);
        when(eventRrepository.findAllPeriodic()).thenReturn(testList);
        PeriodicTimeEvent result = (PeriodicTimeEvent) eventService.getPeriodicEventsByDateAndRoom(check).get(0);
        assertEquals(event2,result);
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


//    @Test
//    public void getAllOnceEventsInBounds() {
//        Room room = new Room("LOL");
//
//        LocalTime time = LocalTime.now();
//        LocalTime timeTo = time.plusHours(2);
//        LocalTime timeFrom = timeTo.minusHours(2);
//        LocalDate date = LocalDate.now();
//        LocalDate dateCheck = plusLocalDate(date, 6);
//        LocalDate startDate = plusLocalDate(date, 2);
//        LocalDate endDate = plusLocalDate(date, 8);
//
//        OnceTimeEvent onceEvent = new OnceTimeEvent(timeFrom,timeTo,room,dateCheck);
//        onceEvent.setId(1L);
//        OnceTimeEvent onceEvent2 = new OnceTimeEvent(timeTo,time,new Room("z"),LocalDate.now());
//        onceEvent2.setId(2L);
//
//        PeriodicTimeEvent checkEvent = new PeriodicTimeEvent(startDate,endDate,"MONDAY",timeFrom,timeTo,room);
//
//        List<OnceTimeEvent> testList = new ArrayList<>();
//        testList.add(onceEvent);
//        testList.add(onceEvent2);
//
//        when(eventRrepository.findAllOnce()).thenReturn(testList);
//        Event result = eventService.getAllOnceEventsInBounds(checkEvent).get(0);
//        assertEquals(onceEvent, result);

//    }

    private String getNowDay() {
    return LocalDate.now().getDayOfWeek().toString();
    }

    private OnceTimeEvent preparationToCreateOnceEvent(int i, LocalTime localTime, int i2) {
        Room checkRoom = createRoom("Test room for once event");
        List<OnceTimeEvent> testOnceList = asList(
                new OnceTimeEvent(plusLocalTime(1), plusLocalTime(6), createRoom("room"), LocalDate.now()),
                new OnceTimeEvent(LocalTime.now(), plusLocalTime(i), checkRoom, LocalDate.now()));

        OnceTimeEvent check = new OnceTimeEvent(localTime, plusLocalTime(i2), checkRoom, LocalDate.now());
        when(eventRrepository.findAllOnce()).thenReturn(testOnceList);
        when(eventRrepository.findAllPeriodic()).thenReturn(Collections.emptyList());
        when(eventRrepository.save(check)).thenReturn(check);
        return check;
    }

    private PeriodicTimeEvent preparationToCreatePeriodicEvent(LocalTime localTime) {
        List<PeriodicTimeEvent> testPeriodicalList = asList(
                new PeriodicTimeEvent(plusLocalDate(0), plusLocalDate(2), LocalDate.now().getDayOfWeek().toString()
                        , minusLocalTime(1), plusLocalTime(3), createRoom("room1")),
                new PeriodicTimeEvent(plusLocalDate(2), plusLocalDate(6), LocalDate.now().getDayOfWeek().toString()
                        , minusLocalTime(1), plusLocalTime(4), createRoom("room2")));

        PeriodicTimeEvent check = new PeriodicTimeEvent(plusLocalDate(3), plusLocalDate(8), LocalDate.now().getDayOfWeek().toString()
                , minusLocalTime(3), localTime, createRoom("room2"));

        when(eventRrepository.findAllPeriodic()).thenReturn(testPeriodicalList);
        when(eventRrepository.findAllOnce()).thenReturn(Collections.emptyList());
        when(eventRrepository.save(check)).thenReturn(check);
        return check;
    }

    private Room createRoom (String type) {
        return new Room(type);
    }

    private LocalDate plusLocalDate(int i) {
        LocalDate date = LocalDate.now();
        LocalDate newDate = date.plusWeeks(i);
        return newDate;
    }
    private LocalDate minusLocalDate(int i) {
        LocalDate date = LocalDate.now();
        LocalDate newDate = date.minusWeeks(i);
        return newDate;
    }
    private LocalTime plusLocalTime(int i) {
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.plusHours(i);
        return newTime;
    }
    private LocalTime minusLocalTime(int i) {
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.minusHours(i);
        return newTime;
    }
}