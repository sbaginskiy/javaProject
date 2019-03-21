package software.jevera.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter @Getter
public class OnceTimeEvent extends Event implements OnceTimeInterface {

    private LocalDate date;  // +

    public OnceTimeEvent(LocalTime timeFrom, LocalTime timeTo, Room room, LocalDate date) {
        super(timeFrom, timeTo, room);
        this.date = date;
    }


}



