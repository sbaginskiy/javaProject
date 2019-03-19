package software.jevera.dao.inmemory;



import software.jevera.dao.UserRepository;
import software.jevera.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserInMemoryRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public boolean isUserWithLoginExists(String login) {
        return users.stream().allMatch(user -> user.getLogin().equals(login));
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> findUserByLogin(String login) {

        return users.stream().filter(user -> user.getLogin().equals(login)).findAny();

    }

}
