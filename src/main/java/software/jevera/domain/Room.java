package software.jevera.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@ToString @AllArgsConstructor
@EqualsAndHashCode(of = "type")
@NoArgsConstructor
public class Room {

 String type;

}
