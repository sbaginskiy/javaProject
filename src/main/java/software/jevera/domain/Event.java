package main.java.software.jevera.domain;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Event {

    private Long id;
    private EventType type;
    private Instant startTime;
    private Instant endTime;
    private User user;
    private List<User> invited = new ArrayList<>();
    private String description;

    public Event(Instant startTime, Instant endTime, User user, List<User> invited, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.invited = invited;
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(startTime, event.startTime) &&
                Objects.equals(endTime, event.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getInvited() {
        return invited;
    }

    public void setInvited(List<User> invited) {
        this.invited = invited;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
}





