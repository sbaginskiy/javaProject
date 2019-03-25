package software.jevera.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter @Getter
@NoArgsConstructor
public class OnceTimeEvent extends Event {

    private LocalDate date;

    public OnceTimeEvent(LocalTime timeFrom, LocalTime timeTo, Room room, LocalDate date) {
        super(timeFrom, timeTo, room);
        this.date = date;
    }


    @Override
    public boolean checkDate(LocalDate startTime, LocalDate endTime) {
        return false;
    }
}




