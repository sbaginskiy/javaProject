package software.jevera.domain;

import lombok.*;


@Setter @Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "login")
@ToString
public class User {
    private String login;
    private String passwordHash;

    public User() {
    }

}
