package software.jevera.dao.inmemory;

import org.junit.Test;
import software.jevera.domain.OnceTimeEvent;
import software.jevera.domain.PeriodicTimeEvent;

import static org.junit.Assert.*;

public class EventInMemoryRepositoryIntTest {

    private EventInMemoryRepository eventInMemoryRepository = new EventInMemoryRepository();

    @Test
    public void save() {
        OnceTimeEvent onceTimeEvent = new OnceTimeEvent();
        PeriodicTimeEvent periodicTimeEvent = new PeriodicTimeEvent();
        assertEquals(onceTimeEvent, eventInMemoryRepository.save(onceTimeEvent));
        assertEquals(periodicTimeEvent, eventInMemoryRepository.save(periodicTimeEvent));
    }

    @Test
    public void findAllPeriodic() {
        PeriodicTimeEvent periodicTimeEvent = new PeriodicTimeEvent();
        PeriodicTimeEvent periodicTimeEvent2 = new PeriodicTimeEvent();
        PeriodicTimeEvent periodicTimeEvent3 = new PeriodicTimeEvent();
        PeriodicTimeEvent periodicTimeEvent4 = new PeriodicTimeEvent();
        PeriodicTimeEvent periodicTimeEvent5 = new PeriodicTimeEvent();
        eventInMemoryRepository.save(periodicTimeEvent);
        eventInMemoryRepository.save(periodicTimeEvent2);
        eventInMemoryRepository.save(periodicTimeEvent3);
        eventInMemoryRepository.save(periodicTimeEvent4);
        eventInMemoryRepository.save(periodicTimeEvent5);
        OnceTimeEvent onceTimeEvent = new OnceTimeEvent();
        eventInMemoryRepository.save(onceTimeEvent);
        assertEquals(5, eventInMemoryRepository.findAllPeriodic().size());
    }

    @Test
    public void findAllOnce() {
        OnceTimeEvent onceTimeEvent = new OnceTimeEvent();
        OnceTimeEvent onceTimeEvent2 = new OnceTimeEvent();
        OnceTimeEvent onceTimeEvent3 = new OnceTimeEvent();
        OnceTimeEvent onceTimeEvent4 = new OnceTimeEvent();
        eventInMemoryRepository.save(onceTimeEvent);
        eventInMemoryRepository.save(onceTimeEvent2);
        eventInMemoryRepository.save(onceTimeEvent3);
        eventInMemoryRepository.save(onceTimeEvent4);
        assertEquals(4, eventInMemoryRepository.findAllOnce().size());
    }

    @Test
    public void findAll() {
        OnceTimeEvent onceTimeEvent = new OnceTimeEvent();
        OnceTimeEvent onceTimeEvent2 = new OnceTimeEvent();
        OnceTimeEvent onceTimeEvent3 = new OnceTimeEvent();
        OnceTimeEvent onceTimeEvent4 = new OnceTimeEvent();
        eventInMemoryRepository.save(onceTimeEvent);
        eventInMemoryRepository.save(onceTimeEvent2);
        eventInMemoryRepository.save(onceTimeEvent3);
        eventInMemoryRepository.save(onceTimeEvent4);
        PeriodicTimeEvent periodicTimeEvent = new PeriodicTimeEvent();
        PeriodicTimeEvent periodicTimeEvent2 = new PeriodicTimeEvent();
        PeriodicTimeEvent periodicTimeEvent3 = new PeriodicTimeEvent();
        PeriodicTimeEvent periodicTimeEvent4 = new PeriodicTimeEvent();
        PeriodicTimeEvent periodicTimeEvent5 = new PeriodicTimeEvent();
        eventInMemoryRepository.save(periodicTimeEvent);
        eventInMemoryRepository.save(periodicTimeEvent2);
        eventInMemoryRepository.save(periodicTimeEvent3);
        eventInMemoryRepository.save(periodicTimeEvent4);
        eventInMemoryRepository.save(periodicTimeEvent5);
        assertEquals(9, eventInMemoryRepository.findAll().size());
    }

    @Test
    public void findById() {
        OnceTimeEvent onceTimeEvent = new OnceTimeEvent();
        OnceTimeEvent onceTimeEvent2 = new OnceTimeEvent();
        OnceTimeEvent onceTimeEvent3 = new OnceTimeEvent();
        OnceTimeEvent onceTimeEvent4 = new OnceTimeEvent();
        eventInMemoryRepository.save(onceTimeEvent);
        eventInMemoryRepository.save(onceTimeEvent2);
        eventInMemoryRepository.save(onceTimeEvent3);
        eventInMemoryRepository.save(onceTimeEvent4);
        assertEquals(onceTimeEvent, eventInMemoryRepository.findById(onceTimeEvent.getId()));
    }
}