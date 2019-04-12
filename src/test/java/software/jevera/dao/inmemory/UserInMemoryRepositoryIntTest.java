package software.jevera.dao.inmemory;

import org.junit.Test;
import software.jevera.domain.User;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class UserInMemoryRepositoryIntTest {

    UserInMemoryRepository userInMemoryRepository = new UserInMemoryRepository();

    @Test
    public void isUserWithLoginExists() {
        List<User> users = asList(new User("name", "password"),
                                  new User("name1", "password1"));
        users.forEach(userInMemoryRepository::save);
        assertTrue(userInMemoryRepository.isUserWithLoginExists("name"));
        assertFalse(userInMemoryRepository.isUserWithLoginExists("name2"));

    }

    @Test
    public void save() {
        User user = new User("user name", "user password");
        assertEquals(user, userInMemoryRepository.save(user));
    }

    @Test
    public void findUserByLogin() {
        List<User> users = asList(new User("name", "password"),
                new User("name1", "password1"));
        User testUser = new User("user name", "user password");
        users.forEach(userInMemoryRepository::save);
        userInMemoryRepository.save(testUser);
        assertEquals(Optional.of(testUser), userInMemoryRepository.findUserByLogin("user name"));
    }
}