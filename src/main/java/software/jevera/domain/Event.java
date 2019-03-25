package software.jevera.domain;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public  class Event {

    private Long id;
    private String description;
    private Room room;
    private User eventOwner;
    private List<User> invited = new ArrayList<>();
    private LocalTime timeFrom;
    private LocalTime timeTo;

    public Event(LocalTime timeFrom, LocalTime timeTo, Room room) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.room = room;
    }

    public void id (Long id) {
        this.id = id;
    }

    public boolean checkDate(LocalDate startTime, LocalDate endTime){
        return false;
    }
}



