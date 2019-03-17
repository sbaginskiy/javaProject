package main.java.software.jevera.service;

import main.java.software.jevera.dao.EventRrepository;
import main.java.software.jevera.dao.UserRepository;
import main.java.software.jevera.domain.Event;
import main.java.software.jevera.domain.EventType;
import main.java.software.jevera.domain.User;
import main.java.software.jevera.exceptions.BussinesException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* Весь сервис по факту состоит из методов проверки времяни для создания ивента
*  и методы довольно большие и непонятные - мне кажется, что написал немного говнокод,
*  но дошел только до такого решения.
*
*  Можешь что-то посоветовать в этой ситуации?
*
*  И какие еще методы мне стоит реализовать (Помню ты что-то говорил про удаления определенного
*    дня из периодического иветна) ?
*/

public class EventService {

    private final EventRrepository eventRrepository;
    private final UserRepository userRrepository;

    public EventService(EventRrepository eventRrepository, UserRepository userRrepository) {
        this.eventRrepository = eventRrepository;
        this.userRrepository = userRrepository;
    }

    public Event createEvent(Event event, User user) {
        if (event.getType() == EventType.ONCE) {
            assertTimeForOnce(event, "Selected time already taken");
            event.setEventOwner(user);
            return eventRrepository.save(event);
        } else {
            assertTimeForPeriodic(event, "Selected time already taken");
            event.setEventOwner(user);
            return eventRrepository.save(event);
        }
    }
    //-----
    public void assertTimeForOnce(Event event, String message) {
        List<Event> checkList = new ArrayList<>();
        // находит все ивенты с типом Once в тойже комнате и в тотже день что и новый ивент
        checkList.addAll(getOnceEventsByDateAndRoom(event));
        // находит все ивенты с типом Periodic в тойже комнате и в тотже день что и новый ивент
        checkList.addAll(getPeriodicEventsByDateAndRoom(event));

        // isTimeNotAvailable проходит по всем подходящим ивентам и проверят занято ли время
        // если есть совпадения возвращает true
        if (isTimeNotAvailable(checkList, event)) {
            throw new BussinesException(message);
       }
    }
    //------

    public List<Event> getOnceEventsByDateAndRoom (Event event) {
        return eventRrepository.findAll().stream()
                .filter(it -> it.getType() == EventType.ONCE)
                .filter(it -> it.getDate() == event.getDate())
                .filter(it -> it.getRoom().equals(event.getRoom()))
                .collect(Collectors.toList());
    }

    public List<Event> getPeriodicEventsByDateAndRoom (Event event) {
        return eventRrepository.findAll().stream()
                .filter(it -> it.getType() == EventType.PEREODIC)
                .filter(it -> it.getDay().equals(event.getDate().getDayOfWeek().toString()))
                .filter(it -> it.getRoom() == event.getRoom())
                .collect(Collectors.toList());
    }

    public boolean isTimeNotAvailable (List<Event> eventCheckList, Event event) {
        return eventCheckList.stream().anyMatch(it -> it.getTimeFrom().isAfter(event.getTimeFrom())
                && it.getTimeFrom().isBefore(event.getTimeTo())
                || (it.getTimeTo().isAfter(event.getTimeFrom())
                && it.getTimeTo().isBefore(event.getTimeTo())));

    }
    //-----
    public void assertTimeForPeriodic(Event event, String message) {
        List<Event> checkList = new ArrayList<>();
        // находит все ивенты с типом Periodic которые входят в период резервирования и проводятся в тойже комнате
        checkList.addAll(getAllPeriodicEventsInBounds(event));
        // находит все ивенты с типом Once которые входят в период резервирования и проводятся в тойже комнате
        checkList.addAll(getAllOnceEventsInBounds(event));

        if (isTimeNotAvailable(checkList,event)){
            throw new BussinesException(message);
        }

    }

    public List<Event> getAllPeriodicEventsInBounds (Event event) {
        return eventRrepository.findAll().stream()
                .filter(it -> it.getType() == EventType.PEREODIC)
                .filter(it -> it.getRoom() == event.getRoom())
                .filter(it -> it.getStartTime().isAfter(event.getStartTime())
                        && it.getStartTime().isBefore(event.getEndTime())
                        || (it.getEndTime().isAfter(event.getStartTime())
                        && it.getEndTime().isBefore(event.getEndTime())))
                .filter(it -> it.getDay().equals(event.getDay()))
                .collect(Collectors.toList());
    }

    public List<Event> getAllOnceEventsInBounds (Event event) {
        return eventRrepository.findAll().stream()
                .filter(it -> it.getType() == EventType.ONCE)
                .filter(it -> it.getRoom() == event.getRoom())
                .filter(it -> it.getDate().isAfter(event.getStartTime()) && it.getDate().isBefore(event.getEndTime()))
                .filter(it -> it.getDate().getDayOfWeek().toString().equals(event.getDay()))
                .collect(Collectors.toList());
    }


}
