package software.jevera.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor
public class PeriodicTimeEvent extends Event {

    private String day;
    private LocalDate startDate;
    private LocalDate endDate;

    public PeriodicTimeEvent(LocalDate startDate, LocalDate endDate, String day, LocalTime timeFrom, LocalTime timeTo, Room room) {
        super(timeFrom, timeTo, room);
        this.day = day;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PeriodicTimeEvent room(Room room){
        this.setRoom(room);
        return this;
    }
    public PeriodicTimeEvent day(String day){
        this.setDay(day);
        return this;
    }
    public PeriodicTimeEvent timeFrom(LocalTime timeFrom){
        this.setTimeFrom(timeFrom);
        return this;
    }
    public PeriodicTimeEvent timeTo(LocalTime timeTo){
        this.setTimeTo(timeTo);
        return this;
    }

    @Override
    public boolean checkDate(LocalDate startTime, LocalDate endTime) {
//        if (this.getStartDate().isBefore(endTime) || this.getEndDate().isAfter(startTime)){
//            return false;
//        }else {return true;}

        if (this.getStartDate().isAfter(startTime)
                && this.getStartDate().isBefore(endTime)
                || (this.getEndDate().isAfter(startTime)
                && this.getEndDate().isBefore(endTime))){
            return true;
        }else {return false;}
    }

}
