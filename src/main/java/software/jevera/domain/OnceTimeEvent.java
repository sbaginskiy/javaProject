package software.jevera.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class OnceTimeEvent extends Event {

    private LocalDate date;

    public OnceTimeEvent(LocalTime timeFrom, LocalTime timeTo, Room room, LocalDate date) {
        super(timeFrom, timeTo, room);
        this.date = date;
    }

    public OnceTimeEvent room(Room room){
        this.setRoom(room);
        return this;
    }
    public OnceTimeEvent date(LocalDate date){
        this.setDate(date);
        return this;
    }
    public OnceTimeEvent timeFrom(LocalTime timeFrom){
        this.setTimeFrom(timeFrom);
        return this;
    }
    public OnceTimeEvent timeTo(LocalTime timeTo){
        this.setTimeTo(timeTo);
        return this;
    }


    @Override
    public boolean checkDate(LocalDate startTime, LocalDate endTime) {
        return false;
    }
}




