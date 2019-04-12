package software.jevera.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Setter @Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "login")
@ToString
public class User {
    private String login;
    @JsonIgnore
    private String passwordHash;

    public User() {
    }

}
