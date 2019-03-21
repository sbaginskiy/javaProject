package software.jevera.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Setter @Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "login")
public class User {
    private String login;
    private String passwordHash;

    public User() {
    }

}
