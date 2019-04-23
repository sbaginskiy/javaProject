package software.jevera.domain.dto;


import software.jevera.domain.OnceTimeEvent;

public class EventMapper {
    public EventMapper() {
    }

    public OnceTimeEvent toOnceTimeEvent(OnceEventDto onceEventDto) {
        if (onceEventDto == null) {
            return null;
        } else {
            OnceTimeEvent onceTimeEvent = new OnceTimeEvent();
            onceTimeEvent.setDescription(onceEventDto.getDescription());
            onceTimeEvent.setRoom(onceEventDto.getRoom());
            return onceTimeEvent;
        }
    }
}