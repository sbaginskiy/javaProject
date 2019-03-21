package software.jevera.service;


import software.jevera.dao.EventRrepository;
import software.jevera.domain.*;
import software.jevera.exceptions.BussinesException;

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

    static final String TIME_ALREADY_TAKEN = "Selected time already taken";

    public EventService(EventRrepository eventRrepository) {
        this.eventRrepository = eventRrepository;
    }

    public Event createOnceEvent(OnceTimeEvent onceTimeEvent, User user){
        assertTimeForOnce(onceTimeEvent, TIME_ALREADY_TAKEN);
        onceTimeEvent.setEventOwner(user);
        return eventRrepository.save(onceTimeEvent);
    }

    public Event createPeriodicEvent(PeriodicTimeEvent onceTimeEvent, User user){
        assertTimeForPeriodic(onceTimeEvent, TIME_ALREADY_TAKEN);
        onceTimeEvent.setEventOwner(user);
        return eventRrepository.save(onceTimeEvent);
    }

    private void assertTimeForOnce(OnceTimeEvent event, String message) {
        List<Event> checkList = new ArrayList<>();                                    // находит все ивенты с типом Once в тойже комнате и в тотже день что и новый ивент
        checkList.addAll(getOnceEventsByDateAndRoom(event));                          // находит все ивенты с типом Periodic в тойже комнате и в тотже день что и новый ивент
        checkList.addAll(getPeriodicEventsByDateAndRoom(event));                      // isTimeNotAvailable проходит по всем подходящим ивентам и проверят занято ли время если есть совпадения возвращает true
        if (isTimeNotAvailable(checkList, event)) {
            throw new BussinesException(message);
       }
    }


    public List<Event> getOnceEventsByDateAndRoom (OnceTimeEvent event) {
        return eventRrepository.findAllOnce().stream()
                .map(it ->(OnceTimeEvent)it)
                .filter(it -> it.getDate() == event.getDate())
                .filter(it -> it.getRoom().equals(event.getRoom()))
                .collect(Collectors.toList());
    }

    //--пока пусть будет так, нужно переделать

    public List<Event> getPeriodicEventsByDateAndRoom (OnceTimeEvent event) {
        return eventRrepository.findAllPeriodic().stream()
                .map(it -> (PeriodicTimeEvent) it)
                .filter(it -> it.getDay().equals(event.getDate().getDayOfWeek().toString()))
                .filter(it -> it.getRoom() == event.getRoom())
                .collect(Collectors.toList());
    }
    //----------------------------------------непонятно, слишком много

    public boolean isTimeNotAvailable (List<Event> eventCheckList, Event event) {
        return eventCheckList.stream().anyMatch(it -> it.getTimeFrom().isAfter(event.getTimeFrom())
                && it.getTimeFrom().isBefore(event.getTimeTo())
                || (it.getTimeTo().isAfter(event.getTimeFrom())
                && it.getTimeTo().isBefore(event.getTimeTo())));

    }
    //-----
    public void assertTimeForPeriodic(PeriodicTimeEvent event, String message) {
        List<Event> checkList = new ArrayList<>();
        // находит все ивенты с типом Periodic которые входят в период резервирования и проводятся в тойже комнате
        checkList.addAll(getAllPeriodicEventsInBounds(event));
        // находит все ивенты с типом Once которые входят в период резервирования и проводятся в тойже комнате
        checkList.addAll(getAllOnceEventsInBounds(event));

        if (isTimeNotAvailable(checkList, event)){
            throw new BussinesException(message);
        }

    }

    public List<Event> getAllPeriodicEventsInBounds (PeriodicTimeEvent event) {
        return eventRrepository.findAll().stream()
                .map(it -> (PeriodicTimeEvent)it)
                .filter(it -> it.getRoom() == event.getRoom())
                .filter(it -> it.checkEventInBounds(event))   // here
                .filter(it -> it.getDay().equals(event.getDay()))
                .collect(Collectors.toList());
    }

    public List<Event> getAllOnceEventsInBounds (PeriodicTimeEvent event) {
        return eventRrepository.findAllOnce().stream()
                .map(it -> (OnceTimeEvent)it)
                .filter(it -> it.getRoom() == event.getRoom())
                .filter(it -> it.getDate().isAfter(event.getStartTime()) && it.getDate().isBefore(event.getEndTime()))
                .filter(it -> it.getDate().getDayOfWeek().toString().equals(event.getDay()))
                .collect(Collectors.toList());
    }

    public List<Event> getAllEvents () {
        return this.eventRrepository.findAll();
    }

    public Event getEventsById (Long id) {
        return this.eventRrepository.findById(id);
    }


}
