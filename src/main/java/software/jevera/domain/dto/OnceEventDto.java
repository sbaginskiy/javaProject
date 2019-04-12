package software.jevera.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import software.jevera.domain.Room;

@Data
@EqualsAndHashCode(of = "description")
public class OnceEventDto {

    private String description;
    private Room room;

}


