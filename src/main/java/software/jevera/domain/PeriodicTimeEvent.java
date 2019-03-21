package software.jevera.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class PeriodicTimeEvent extends Event implements PeriodicTimeInterface{

    private String day;  // -            // поле day использую только для Event с типом ONCE
    private LocalDate startTime;     //  поля startTime и endTime только для Event с типом PERIODIC
    private LocalDate endTime;       // Вопрос: есть ли смысл делать класс Event классом для наследывания для двух других

    public PeriodicTimeEvent(LocalTime timeFrom, LocalTime timeTo, Room room, String day, LocalDate startTime, LocalDate endTime) {
        super(timeFrom, timeTo, room);
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean checkEventInBounds(PeriodicTimeEvent event) {

        if (this.getStartTime().isAfter(event.getStartTime())
                && this.getStartTime().isBefore(event.getEndTime())
                || (this.getEndTime().isAfter(event.getStartTime())
                && this.getEndTime().isBefore(event.getEndTime()))){
            return true;
        }else {return false;}
    }

}
