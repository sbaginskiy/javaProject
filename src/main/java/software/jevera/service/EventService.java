package software.jevera.service;


import org.springframework.stereotype.Service;
import software.jevera.dao.EventRrepository;
import software.jevera.domain.Event;
import software.jevera.domain.OnceTimeEvent;
import software.jevera.domain.PeriodicTimeEvent;
import software.jevera.domain.User;
import software.jevera.exceptions.BussinesException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRrepository eventRrepository;

    static final String TIME_ALREADY_TAKEN = "Selected time already taken";

    public EventService (EventRrepository eventRrepository) {
        this.eventRrepository = eventRrepository;
    }
//+
    public Event createOnceEvent (OnceTimeEvent onceTimeEvent, User user) {
        assertTimeForOnce(onceTimeEvent, TIME_ALREADY_TAKEN);
        onceTimeEvent.setEventOwner(user);
        return eventRrepository.save(onceTimeEvent);
    }
//-
    public Event createPeriodicEvent (PeriodicTimeEvent periodicTimeEvent, User user) {
        assertTimeForPeriodic(periodicTimeEvent, TIME_ALREADY_TAKEN);
        periodicTimeEvent.setEventOwner(user);
        return eventRrepository.save(periodicTimeEvent);
    }
//-
    private void assertTimeForOnce(OnceTimeEvent event, String message) {
        List<Event> checkList = new ArrayList<>();                                    // находит все ивенты с типом Once в тойже комнате и в тотже день что и новый ивент
        checkList.addAll(getOnceEventsByDateAndRoom(event));                          // находит все ивенты с типом Periodic в тойже комнате и в тотже день что и новый ивент
        checkList.addAll(getPeriodicEventsByDateAndRoom(event));                      // isTimeNotAvailable проходит по всем подходящим ивентам и проверят занято ли время если есть совпадения возвращает true
        if (isTimeNotAvailable(checkList, event)) {
            throw new BussinesException(message);
       }
    }

//+
    public List<Event> getOnceEventsByDateAndRoom (OnceTimeEvent event) {
        return getAllOnceEvents().stream()
                .map(it ->(OnceTimeEvent)it)
                .filter(it -> it.getDate().equals(event.getDate()))
                .filter(it -> it.getRoom().equals(event.getRoom()))
                .collect(Collectors.toList());
    }

    //--пока пусть будет так, нужно переделать
//+
    public List<Event> getPeriodicEventsByDateAndRoom (OnceTimeEvent event) {
        return getAllPeriodicEvents().stream()
                .map(it -> (PeriodicTimeEvent) it)
                .filter(it -> it.getDay().equals(event.getDate().getDayOfWeek().toString()))
                .filter(it -> it.getRoom().equals(event.getRoom()))
                .collect(Collectors.toList());
    }
    //----------------------------------------непонятно, слишком много
//+
    public boolean isTimeNotAvailable (List<Event> eventCheckList, Event event) {
        return eventCheckList.stream().anyMatch(it -> (it.getTimeFrom().isAfter(event.getTimeFrom())

                && it.getTimeFrom().isBefore(event.getTimeTo())) // false

                || (it.getTimeTo().isAfter(event.getTimeFrom())

                && it.getTimeTo().isBefore(event.getTimeTo())) ||
                (it.getTimeFrom().isBefore(event.getTimeFrom()) && it.getTimeTo().isAfter(event.getTimeTo())));

    }
//-    //-----
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
//+
    public List<Event> getAllPeriodicEventsInBounds (PeriodicTimeEvent event) {
        return eventRrepository.findAllPeriodic().stream()
                .map(it -> (PeriodicTimeEvent)it)
                .filter(it -> it.getRoom() == event.getRoom())
                .filter(it -> it.checkDate(event.getStartDate(),event.getEndDate()))   // here
                .filter(it -> it.getDay().equals(event.getDay()))
                .collect(Collectors.toList());
    }
//+
    public List<Event> getAllOnceEventsInBounds (PeriodicTimeEvent event) {
        return getAllOnceEvents().stream()
                .map(it -> (OnceTimeEvent)it)
                .filter(it -> it.getRoom() == event.getRoom())
                .filter(it -> it.getDate().isAfter(event.getStartDate()) && it.getDate().isBefore(event.getEndDate()))
                .filter(it -> it.getDate().getDayOfWeek().toString().equals(event.getDay()))
                .collect(Collectors.toList());
    }

    public List<Event> getAllEvents () { return this.eventRrepository.findAll(); }

    public List<OnceTimeEvent> getAllOnceEvents () { return this.eventRrepository.findAllOnce(); }

    public List<PeriodicTimeEvent> getAllPeriodicEvents () { return this.eventRrepository.findAllPeriodic(); }

    public Event getEventsById (Long id) { return this.eventRrepository.findById(id); }


//    public List<Event> getEventsLocalWeek () {
//        LocalDate today = LocalDate.now();
//        LocalDate weekEnd = LocalDate.now().plusDays(7);
//        List<Event> periodic = getAllPeriodicEvents().stream().map(it -> (PeriodicTimeEvent)it).filter(it -> it.checkDate())
//        return this.eventRrepository.findAll().;
//    }


}
