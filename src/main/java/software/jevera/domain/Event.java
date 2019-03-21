package software.jevera.domain;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event {

    private Long id;
    private String description;
    private Room room;
    private User eventOwner;         // например EventOnce и EventPeriodic
    private List<User> invited = new ArrayList<>();
    private LocalTime timeFrom; // +
    private LocalTime timeTo; // +
    //  private LocalDate date;  // +
    //  private String day;  // -            // поле day использую только для Event с типом ONCE
    //   private LocalDate startTime;     //  поля startTime и endTime только для Event с типом PERIODIC
    //   private LocalDate endTime;       // Вопрос: есть ли смысл делать класс Event классом для наследывания для двух других


    public Event(LocalTime timeFrom, LocalTime timeTo, Room room) {
        this.timeFrom = timeFrom;
    }

    public  List<Event> getEventInTime(){
        return Collections.emptyList();
    }
}



